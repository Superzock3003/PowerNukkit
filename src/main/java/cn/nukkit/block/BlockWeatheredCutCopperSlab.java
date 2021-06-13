package cn.nukkit.block;

public class BlockWeatheredCutCopperSlab extends BlockCutCopperSlab {

    public BlockWeatheredCutCopperSlab() {
        this(0);
    }

    public BlockWeatheredCutCopperSlab(int meta) {
        super(meta, WEATHERED_DOUBLE_CUT_COPPER_SLAB);
    }

    @Override
    public String getSlabName() {
        return "Weathered Cut Copper";
    }

    @Override
    public int getId() {
        return WEATHERED_CUT_COPPER_SLAB;
    }
}
