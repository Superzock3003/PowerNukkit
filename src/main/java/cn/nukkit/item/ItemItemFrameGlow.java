package cn.nukkit.item;

import cn.nukkit.block.BlockItemFrameGlow;

public class ItemItemFrameGlow extends StringItem {

    public ItemItemFrameGlow() {
        super(MinecraftItemID.GLOW_FRAME.getNamespacedId(), "Glow Item Frame");
        this.block = new BlockItemFrameGlow();
    }
}
