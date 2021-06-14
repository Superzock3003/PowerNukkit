package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemRawCopper;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.enchantment.Enchantment;

import java.util.concurrent.ThreadLocalRandom;

public class BlockOreCopper extends BlockOre {

    public BlockOreCopper() {

    }

    @Override
    public String getName() {
        return "Copper Ore";
    }

    @Override
    public int getId() {
        return COPPER_ORE;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 3;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe() && item.getTier() >= getToolTier()) {
            int count = ThreadLocalRandom.current().nextInt(2) + 2;
            Enchantment fortune = item.getEnchantment(Enchantment.ID_FORTUNE_DIGGING);
            if (fortune != null && fortune.getLevel() >= 1) {
                int i = ThreadLocalRandom.current().nextInt(fortune.getLevel() + 1) * (ThreadLocalRandom.current().nextInt(2) + 2);

                count += i;
            }

            return new Item[]{
                    new ItemRawCopper(0, count)
            };
        } else {
            return Item.EMPTY_ARRAY;
        }
    }

    @Override
    public int getToolTier() {
        return ItemTool.TIER_STONE;
    }

    @Override
    public boolean canSilkTouch() {
        return true;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
