package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockproperty.BlockProperties;

import javax.annotation.Nonnull;

/**
 * @author PleaseInsertNameHere
 * @since 28/06/2021
 */
@PowerNukkitOnly
@Since("FUTURE")
public class BlockSlabDeepslatePolished extends BlockSlab {

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockSlabDeepslatePolished() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockSlabDeepslatePolished(int meta) {
        super(meta, POLISHED_DEEPSLATE_DOUBLE_SLAB);
    }

    @Override
    public int getId() {
        return POLISHED_DEEPSLATE_SLAB;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return SIMPLE_SLAB_PROPERTIES;
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
    public boolean isSameType(BlockSlab slab) {
        return getId() == slab.getId();
    }
}
