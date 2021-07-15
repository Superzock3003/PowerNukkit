package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.event.block.BlockGrowEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.MinecraftItemID;
import cn.nukkit.level.Level;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.math.*;
import cn.nukkit.utils.BlockColor;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author PleaseInsertNameHere
 * @since 28/06/2021
 */
public class BlockCaveVines extends BlockTransparent {
    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final BlockProperties PROPERTIES = new BlockProperties(BlockCrops.GROWTH);

    @PowerNukkitOnly
    public BlockCaveVines() {

    }

    @Override
    public int getId() {
        return CAVE_VINES;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public String getName() {
        return "Cave Vines";
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.FOLIAGE_BLOCK_COLOR;
    }

    @Override
    public int getBurnChance() {
        return 30;
    }

    @Override
    public int getBurnAbility() {
        return 60;
    }

    @Override
    public boolean sticksToPiston() {
        return false;
    }

    @Override
    public boolean breaksWhenMoved() {
        return true;
    }

    @Override
    public boolean canBeClimbed() {
        return true;
    }

    @Override
    public boolean canBeFlowedInto() {
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public double getResistance() {
        return 0;
    }

    @Override
    public double getHardness() {
        return 0;
    }

    @Override
    public boolean onActivate(@Nonnull Item item, Player player) {
        if (item.isFertilizer()) {
            int downId = this.down().getId();
            BlockGrowEvent event = new BlockGrowEvent(this, Block.get(downId != CAVE_VINES ? CAVE_VINES_HEAD_WITH_BERRIES : CAVE_VINES_BODY_WITH_BERRIES));
            if (!event.isCancelled()) {
                this.getLevel().setBlock(this, event.getNewState(), true, true);
                this.getLevel().addParticle(new BoneMealParticle(this));
            }
            return true;
        }

        return false;
    }

    @Override
    public int onUpdate(int type) {
        Level level = this.getLevel();
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            level.scheduleUpdate(this, 0);
            return type;
        }

        if (type == Level.BLOCK_UPDATE_SCHEDULED) {
            if (!isSupportValid(up())) {
                level.useBreakOn(this);
            }
            if (up().getId() == Block.CAVE_VINES_HEAD_WITH_BERRIES) {
                level.setBlock(up(), Block.get(Block.CAVE_VINES_BODY_WITH_BERRIES), true, true);
            }
            return type;
        }

        if (type == Level.BLOCK_UPDATE_RANDOM
                && ThreadLocalRandom.current().nextInt(5) == 0
                && level.getFullLight(add(0, 1, 0)) >= BlockCrops.MINIMUM_LIGHT_LEVEL) {
            if (this.down().getId() == Block.AIR) {
                level.setBlock(this.down(), this.clone());
            }
        }
        return 0;
    }

    @Override
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, Player player) {
        if (isSupportValid(up())) {
            if (up().getId() == Block.CAVE_VINES_HEAD_WITH_BERRIES) {
                this.getLevel().setBlock(up(), Block.get(Block.CAVE_VINES_BODY_WITH_BERRIES), true, true);
            }
            this.getLevel().setBlock(block, this, true);
            return true;
        }
        return false;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public static boolean isSupportValid(Block block) {
        return block.isSolid() || block.getId() == Block.CAVE_VINES || block.getId() == Block.CAVE_VINES_HEAD_WITH_BERRIES || block.getId() == Block.CAVE_VINES_BODY_WITH_BERRIES;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public Item toItem() {
        return MinecraftItemID.GLOW_BERRIES.get(1);
    }
}
