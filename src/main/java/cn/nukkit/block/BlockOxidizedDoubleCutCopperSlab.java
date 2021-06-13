package cn.nukkit.block;

public class BlockOxidizedDoubleCutCopperSlab extends BlockDoubleCutCopperSlab {

    @Override
    public String getSlabName() {
        return "Oxidized Cut Copper";
    }

    @Override
    public int getId() {
        return OXIDIZED_DOUBLE_CUT_COPPER_SLAB;
    }

    @Override
    public int onUpdate(int type) {
        return 0;
    }
}
