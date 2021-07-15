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
public class BlockSlabDeepslateTile extends BlockSlab {

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockSlabDeepslateTile() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockSlabDeepslateTile(int meta) {
        super(meta, DEEPSLATE_TILE_DOUBLE_SLAB);
    }

    @Override
    public int getId() {
        return DEEPSLATE_TILE_SLAB;
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
        return "Deepslate Tile";
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public boolean isSameType(BlockSlab slab) {
        return getId() == slab.getId();
    }
}
