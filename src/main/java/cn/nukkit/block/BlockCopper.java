package cn.nukkit.block;

import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;

public class BlockCopper extends BlockSolid {

    @Override
    public String getName() {
        return "Copper Block";
    }

    @Override
    public int getId() {
        return COPPER_BLOCK;
    }

    @Override
    public double getHardness() {
        return 3;
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

    @Override
    public int onUpdate(int type) {
        if(type == Level.BLOCK_UPDATE_RANDOM) {
            this.getLevel().setBlock(this, Block.get(getId() + 1), true, true);
            return type;
        }
        return 0;
    }
}
