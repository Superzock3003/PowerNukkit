package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.utils.BlockColor;

import javax.annotation.Nonnull;

/**
 * @author Pub4Game
 * @since 28.01.2016
 */
public class BlockHugeMushroomRed extends BlockSolidMeta {

    @PowerNukkitOnly
    @Since("1.5.0.0-PN")
    public static final BlockProperties PROPERTIES = BlockHugeMushroomBrown.PROPERTIES;

    public BlockHugeMushroomRed() {
        this(0);
    }

    public BlockHugeMushroomRed(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Red Mushroom Block";
    }

    @Override
    public int getId() {
        return RED_MUSHROOM_BLOCK;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public double getHardness() {
        return 0.2;
    }

    @Override
    public double getResistance() {
        return 1;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.hasEnchantment(Enchantment.ID_SILK_TOUCH)) {
            return new Item[]{
                    new ItemBlock(Block.get(BlockID.RED_MUSHROOM_BLOCK), this.getDamage() == 10 ? 10 : 14)
            };
        }
        NukkitRandom random = new NukkitRandom();
        if (random.nextRange(1, 20) == 1) {
            return new Item[]{
                    new ItemBlock(Block.get(BlockID.RED_MUSHROOM), 0, random.nextRange(1, 2))
            };
        }
        return Item.EMPTY_ARRAY;
    }

    @Override
    public boolean canSilkTouch() {
        return false;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.RED_BLOCK_COLOR;
    }
}
