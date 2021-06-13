package cn.nukkit.block;

import cn.nukkit.level.Level;

public class BlockDoubleCutCopperSlab extends BlockDoubleSlabBase {

    public BlockDoubleCutCopperSlab() {
        this(0);
    }

    public BlockDoubleCutCopperSlab(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DOUBLE_CUT_COPPER_SLAB;
    }

    @Override
    public String getSlabName() {
        return "Cut Copper";
    }

    @Override
    public int getSingleSlabId() {
        return CUT_COPPER_SLAB;
    }

    @Override
    public int onUpdate(int type) {
        if(type == Level.BLOCK_UPDATE_RANDOM) {
            this.getLevel().setBlock(this, Block.get(this.getId() + 1), true, true);
        }
        return 0;
    }
}
