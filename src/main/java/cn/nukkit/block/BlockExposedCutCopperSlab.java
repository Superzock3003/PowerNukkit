package cn.nukkit.block;

public class BlockExposedCutCopperSlab extends BlockCutCopperSlab {

    public BlockExposedCutCopperSlab() {
        this(0);
    }

    public BlockExposedCutCopperSlab(int meta) {
        super(meta, EXPOSED_DOUBLE_CUT_COPPER_SLAB);
    }

    @Override
    public String getSlabName() {
        return "Exposed Cut Copper";
    }

    @Override
    public int getId() {
        return EXPOSED_CUT_COPPER_SLAB;
    }
}
