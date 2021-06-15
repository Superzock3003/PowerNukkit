package cn.nukkit.block;

import cn.nukkit.blockproperty.BlockProperties;

import javax.annotation.Nonnull;

public class BlockLeavesAzalea extends BlockLeaves {

    public static final BlockProperties PROPERTIES = new BlockProperties(PERSISTENT, UPDATE);

    @Override
    public String getName() {
        return "Azalea Leaves";
    }

    @Override
    public int getId() {
        return AZALEA_LEAVES;
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }
}
