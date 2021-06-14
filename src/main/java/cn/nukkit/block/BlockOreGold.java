package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemRawGold;
import cn.nukkit.item.ItemRawIron;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.enchantment.Enchantment;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class BlockOreGold extends BlockOre {

    public BlockOreGold() {
    }

    @Override
    public int getId() {
        return GOLD_ORE;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 15;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public int getToolTier() {
        return ItemTool.TIER_IRON;
    }

    @Override
    public String getName() {
        return "Gold Ore";
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public boolean canSilkTouch() {
        return true;
    }

    @Override
    public Item[] getDrops(Item item) {
        if(item.isPickaxe() && item.getTier() >= getToolTier()) {
            int count = 1;
            Enchantment fortune = item.getEnchantment(Enchantment.ID_FORTUNE_DIGGING);
            if(fortune != null && fortune.getLevel() >= 1) {
                count += ThreadLocalRandom.current().nextInt(fortune.getLevel() + 1);
            }
            return new Item[] {new ItemRawGold(0, count)};
        }
        return Item.EMPTY_ARRAY;
    }
}
