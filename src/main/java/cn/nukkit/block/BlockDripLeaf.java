package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockproperty.ArrayBlockProperty;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.BooleanBlockProperty;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockDripLeaf extends BlockTransparent {

    public static final ArrayBlockProperty<DripLeafTilt> TILTS = new ArrayBlockProperty<>("big_dripleaf_tilt", false, DripLeafTilt.class);

    public static final BooleanBlockProperty BIG_HEAD = new BooleanBlockProperty("big_dripleaf_head", false);

    public static final BlockProperties PROPERTIES = new BlockProperties(CommonBlockProperties.DIRECTION, BIG_HEAD, TILTS);

    @Override
    public String getName() {
        return "Big Drip Leaf";
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
    public int getWaterloggingLevel() {
        return 2;
    }

    @Override
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        Block down = this.down();
        if(!(down instanceof BlockSolid || down instanceof BlockSolidMeta || down instanceof BlockDripLeaf)) {
            return false;
        }
        this.setPropertyValue(TILTS, DripLeafTilt.NONE);
        this.setPropertyValue(BIG_HEAD, true);
        this.setPropertyValue(CommonBlockProperties.DIRECTION, player.getDirection().getOpposite());
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
            if(!(this.down() instanceof BlockSolid || this.down() instanceof BlockSolidMeta || this.down() instanceof BlockDripLeaf) || (this.getPropertyValue(BIG_HEAD) == false && !(this.up() instanceof BlockDripLeaf))) {
                this.getLevel().useBreakOn(this);
            }
        }
        return 0;
    }

    public enum DripLeafTilt {

        NONE,
        UNSTABLE,
        PARTIAL_TILT,
        FULL_TILT
    }
}
