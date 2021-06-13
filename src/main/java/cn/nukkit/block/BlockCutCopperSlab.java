package cn.nukkit.block;

import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;

import javax.annotation.Nonnull;

public class BlockCutCopperSlab extends BlockSlab {

    public BlockCutCopperSlab() {
        this(0);
    }

    public BlockCutCopperSlab(int meta) {
        this(meta, DOUBLE_CUT_COPPER_SLAB);
    }

    public BlockCutCopperSlab(int meta, int doubleSlab) {
        super(meta, doubleSlab);
    }

    @Override
    public int getId() {
        return CUT_COPPER_SLAB;
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return SIMPLE_SLAB_PROPERTIES;
    }

    @Override
    public String getSlabName() {
        return "Cut Copper";
    }

    @Override
    public boolean isSameType(BlockSlab slab) {
        return slab.getId() == getId();
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
    public int onUpdate(int type) {
        if(type == Level.BLOCK_UPDATE_RANDOM) {
            this.getLevel().setBlock(this, this.getCurrentState().withBlockId(this.getId() + 1).getBlock(), true, true);
        }
        return 0;
    }
}
