package cn.nukkit.block;

import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;

public class BlockCutCopperStairs extends BlockStairs {

    public BlockCutCopperStairs() {
        this(0);
    }

    public BlockCutCopperStairs(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Cut Copper Stairs";
    }

    @Override
    public int getId() {
        return CUT_COPPER_STAIRS;
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
            this.getLevel().setBlock(this, this.getCurrentState().withBlockId(this.getId() + 1).getBlock(), true, true);
            return type;
        }
        return 0;
    }
}
