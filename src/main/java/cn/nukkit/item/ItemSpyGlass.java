package cn.nukkit.item;

public class ItemSpyGlass extends Item {

    public ItemSpyGlass() {
        this(0);
    }

    public ItemSpyGlass(Integer meta) {
        this(meta, 1);
    }

    public ItemSpyGlass(Integer meta, int count) {
        super(SPY_GLASS, meta, count, "Spy Glass");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
