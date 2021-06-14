package cn.nukkit.item;

public class ItemRawIron extends Item {

    public ItemRawIron() {
        this(0);
    }

    public ItemRawIron(Integer meta) {
        this(meta, 1);
    }

    public ItemRawIron(Integer meta, int count) {
        super(RAW_IRON, meta, count, "Raw Iron");
    }
}
