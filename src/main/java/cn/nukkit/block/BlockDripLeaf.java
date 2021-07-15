package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.blockproperty.ArrayBlockProperty;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.BooleanBlockProperty;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.BlockColor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockDripLeaf extends BlockTransparent {

    public static final ArrayBlockProperty<DripLeafTilt> TILTS = new ArrayBlockProperty<>("big_dripleaf_tilt", false, DripLeafTilt.class);

    public static final BooleanBlockProperty BIG_HEAD = new BooleanBlockProperty("big_dripleaf_head", false);

    public static final BlockProperties PROPERTIES = new BlockProperties(CommonBlockProperties.DIRECTION, BIG_HEAD, TILTS);

    @Override
    public String getName() {
        return "Big Dripleaf";
    }

    @Override
    public int getId() {
        return BIG_DRIPLEAF;
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public double getHardness() {
        return 0.1;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public int getWaterloggingLevel() {
        return 2;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    public void setTilt(DripLeafTilt tilt) {
        if(this.getPropertyValue(TILTS) == tilt) {
            return;
        }
        this.setPropertyValue(TILTS, tilt);
        this.getLevel().setBlock(this, this, true, true);
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.GREEN_BLOCK_COLOR;
    }

    @Override
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        Block down = this.down();
        if(!(down.isSolid() || down instanceof BlockDripLeaf)) {
            return false;
        }
        this.setPropertyValue(TILTS, DripLeafTilt.NONE);
        this.setPropertyValue(BIG_HEAD, true);
        this.setPropertyValue(CommonBlockProperties.DIRECTION, player != null ? player.getDirection().getOpposite() : BlockFace.NORTH);
        if(down instanceof BlockDripLeaf) {
            this.setPropertyValue(CommonBlockProperties.DIRECTION, down.getPropertyValue(CommonBlockProperties.DIRECTION));
            if(this.getLevel().setBlock(this, this, true, true)) {
                down.setPropertyValue(BIG_HEAD, false);
                down.getLevel().setBlock(down, down, true, true);
                return true;
            }
        }
        return this.getLevel().setBlock(this, this, true, true);
    }

    @Override
    public int onUpdate(int type) {
        if(type == Level.BLOCK_UPDATE_NORMAL) {
            Block down = this.down();
            if(!(down.isSolid() || down instanceof BlockDripLeaf) || (!this.getPropertyValue(BIG_HEAD) && !(this.up() instanceof BlockDripLeaf))) {
                this.getLevel().useBreakOn(this);
            }
            return type;
        }
        return 0;
    }

    @Override
    public boolean onActivate(@Nonnull Item item, Player player) {
        if(!item.isFertilizer()) {
            return false;
        }
        for(int i = 1; i < 256 - this.getY(); i++) {
            Block up = this.up(i);
            if(up instanceof BlockAir) {
                if(player != null && (player.gamemode & 0x01) == 0) {
                    item.count--;
                }
                BlockDripLeaf dripLeaf = new BlockDripLeaf();
                dripLeaf.position(up);
                dripLeaf.place(dripLeaf.toItem(), up, up.down(), BlockFace.DOWN, 0.5, 0.5, 0.5, player);
                this.level.addParticle(new BoneMealParticle(this));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onProjectileHit(@Nonnull Entity projectile, @Nonnull Position position, @Nonnull Vector3 motion) {
        if(!this.getPropertyValue(BIG_HEAD)) {
            return false;
        }
        this.setTilt(DripLeafTilt.FULL_TILT);
        Server.getInstance().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int currentTick) {
                Block block = getLevelBlock();
                if(block instanceof BlockDripLeaf && block.getPropertyValue(BIG_HEAD)) {
                    setTilt(DripLeafTilt.NONE);
                }
            }
        }, 80);
        return true;
    }

    @Override
    public void onEntityCollide(Entity entity) {
        if(!this.getPropertyValue(BIG_HEAD) || this.isGettingPower() || this.getPropertyValue(TILTS) != DripLeafTilt.NONE) {
            return;
        }
        Server.getInstance().getScheduler().scheduleDelayedTask(new Task() {
            @Override
            public void onRun(int currentTick) {
                Block block = getLevelBlock();
                if(block instanceof BlockDripLeaf && block.getPropertyValue(BIG_HEAD)) {
                    setTilt(DripLeafTilt.PARTIAL_TILT);
                    Server.getInstance().getScheduler().scheduleDelayedTask(new Task() {
                        @Override
                        public void onRun(int currentTick) {
                            Block block = getLevelBlock();
                            if(block instanceof BlockDripLeaf && block.getPropertyValue(BIG_HEAD)) {
                                setTilt(DripLeafTilt.FULL_TILT);
                                Server.getInstance().getScheduler().scheduleDelayedTask(new Task() {
                                    @Override
                                    public void onRun(int currentTick) {
                                        Block block = getLevelBlock();
                                        if(block instanceof BlockDripLeaf && block.getPropertyValue(BIG_HEAD)) {
                                            setTilt(DripLeafTilt.NONE);
                                        }
                                    }
                                }, 80);
                            }
                        }
                    }, 10);
                }
            }
        }, 20);
    }

    public enum DripLeafTilt {

        NONE,
        UNSTABLE,
        PARTIAL_TILT,
        FULL_TILT
    }
}
