package cn.nukkit.block;

public class BlockOxidizedCopper extends BlockCopperBlock {

    @Override
    public String getName() {
        return "Oxidized Copper";
    }

    @Override
    public int getId() {
        return OXIDIZED_COPPER;
    }

    @Override
    public int onUpdate(int type) {
        return 0;
    }
}
