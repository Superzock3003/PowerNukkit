package cn.nukkit.block;

public class BlockOxidizedCutCopperStairs extends BlockExposedCutCopperStairs {

    @Override
    public String getName() {
        return "Oxidized Cut Copper Stairs";
    }

    @Override
    public int getId() {
        return OXIDIZED_CUT_COPPER_STAIRS;
    }

    @Override
    public int onUpdate(int type) {
        return 0;
    }
}
