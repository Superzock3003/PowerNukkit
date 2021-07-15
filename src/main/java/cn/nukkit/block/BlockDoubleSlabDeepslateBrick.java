package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

/**
 * @author PleaseInsertNameHere
 * @since 28/06/2021
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockDoubleSlabDeepslateBrick extends BlockDoubleSlabBase {
    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDoubleSlabDeepslateBrick() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDoubleSlabDeepslateBrick(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DEEPSLATE_BRICK_DOUBLE_SLAB;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public String getSlabName() {
        return "Deepslate Brick";
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getSingleSlabId() {
        return DEEPSLATE_BRICK_SLAB;
    }
}
