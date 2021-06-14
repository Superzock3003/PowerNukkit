package cn.nukkit.block;

import cn.nukkit.item.ItemTool;

public class BlockRawIron extends BlockSolid {

    @Override
    public String getName() {
        return "Raw Iron Block";
    }

    @Override
    public int getId() {
        return RAW_IRON_BLOCK;
    }

    @Override
    public double getHardness() {
        return 5;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public int getToolTier() {
        return ItemTool.TIER_STONE;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
