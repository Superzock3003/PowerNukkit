package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.MinecraftItemID;
import cn.nukkit.level.Sound;

import javax.annotation.Nonnull;

/**
 * @author PleaseInsertNameHere
 * @since 28/06/2021
 */
public class BlockDirtRooted extends BlockSolid {

    public BlockDirtRooted() {
    }

    @Override
    public String getName() {
        return "Rooted Dirt Block";
    }

    @Override
    public int getId() {
        return DIRT_WITH_ROOTS;
    }

    @Override
    public double getHardness() {
        return 0.5;
    }

    @Override
    public double getResistance() {
        return 0.1;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(@Nonnull Item item) {
        return this.onActivate(item, null);
    }

    @Override
    public boolean onActivate(@Nonnull Item item, Player player) {
        if (player == null) {
            return false;
        }

        if (item.isHoe()) {
            item.useOn(this);
            this.getLevel().setBlock(this, get(DIRT), true);
            this.getLevel().dropItem(add(0, 0.5, 0), MinecraftItemID.HANGING_ROOTS.get(1));
            player.getLevel().addSound(player, Sound.USE_GRASS);
        } else if (item.isShovel()) {
            item.useOn(this);
            this.getLevel().setBlock(this, Block.get(BlockID.GRASS_PATH));
            player.getLevel().addSound(player, Sound.USE_GRASS);
        }
        return true;
    }
}
