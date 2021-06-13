package cn.nukkit.block;

public class BlockOxidizedCutCopper extends BlockCutCopper {

    @Override
    public String getName() {
        return "Oxidized Cut Copper Block";
    }

    @Override
    public int getId() {
        return OXIDIZED_CUT_COPPER;
    }

    @Override
    public int onUpdate(int type) {
        return 0;
    }
}
