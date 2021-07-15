package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

/**
 * @author PleaseInsertNameHere
 * @since 28/06/2021
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockDoubleSlabDeepslatePolished extends BlockDoubleSlabBase {
    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDoubleSlabDeepslatePolished() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDoubleSlabDeepslatePolished(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return POLISHED_DEEPSLATE_DOUBLE_SLAB;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public String getSlabName() {
        return "Polished Deepslate";
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getSingleSlabId() {
        return POLISHED_DEEPSLATE_SLAB;
    }
}
