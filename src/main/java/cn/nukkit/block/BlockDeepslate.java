package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.MinecraftItemID;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author LoboMetalurgico
 * @since 08/06/2021
 */

@PowerNukkitOnly
@Since("FUTURE")
public class BlockDeepslate extends BlockSolid {
    @PowerNukkitOnly
    @Since("FUTURE")
    public BlockDeepslate() {
        // Does nothing
    }

    @Override
    public String getName() {
        return "Deepslate";
    }

    @Override
    public int getId() {
        return DEEPSLATE;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @Override
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (!canHarvest(item)) {
            return Item.EMPTY_ARRAY;
        }

        return new Item[]{MinecraftItemID.COBBLED_DEEPSLATE.get(1)};
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return new BlockProperties(CommonBlockProperties.PILLAR_AXIS);
    }

    @Override
    public boolean canSilkTouch() {
        return true;
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
