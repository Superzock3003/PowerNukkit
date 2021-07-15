package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author PleaseInsertNameHere
 * @since 28/06/2021
 */
public class BlockDeepslateInfested extends BlockSolid {

    public static final BlockProperties PROPERTIES = new BlockProperties(CommonBlockProperties.PILLAR_AXIS);

    public BlockDeepslateInfested() {

    }

    @Override
    public String getName() {
        return "Infested Deepslate";
    }

    @Override
    public int getId() {
        return Block.INFESTED_DEEPSLATE;
    }

    @Override
    public double getHardness() {
        return 0.75;
    }

    @Override
    public double getResistance() {
        return 3.75;
    }

    @Override
    public Item[] getDrops(Item item) {
        return Item.EMPTY_ARRAY;
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.DEEPSLATE_GRAY;
    }

    @Override
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        setPropertyValue(CommonBlockProperties.PILLAR_AXIS, face.getAxis());
        return super.place(item, block, target, face, fx, fy, fz, player);
    }
}
