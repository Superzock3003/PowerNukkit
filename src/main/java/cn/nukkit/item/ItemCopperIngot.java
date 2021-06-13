package cn.nukkit.item;

public class ItemCopperIngot extends Item {

    public ItemCopperIngot() {
        this(0);
    }

    public ItemCopperIngot(Integer meta) {
        this(meta, 1);
    }

    public ItemCopperIngot(Integer meta, int count) {
        super(COPPER_INGOT, meta, count, "Copper Ingot");
    }
}
