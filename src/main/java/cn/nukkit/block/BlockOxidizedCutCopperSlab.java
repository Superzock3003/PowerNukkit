package cn.nukkit.block;

public class BlockOxidizedCutCopperSlab extends BlockCutCopperSlab {

    public BlockOxidizedCutCopperSlab() {
        this(0);
    }

    public BlockOxidizedCutCopperSlab(int meta) {
        super(meta, OXIDIZED_DOUBLE_CUT_COPPER_SLAB);
    }

    @Override
    public String getSlabName() {
        return "Oxidized Cut Copper";
    }

    @Override
    public int getId() {
        return OXIDIZED_CUT_COPPER_SLAB;
    }

    @Override
    public int onUpdate(int type) {
        return 0;
    }
}
