package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.BlockColor;

/**
 * @author PleaseInsertNameHere
 * @since 28/06/2021
 */

@PowerNukkitOnly
@Since("1.4.0.0-PN")
public class BlockBasaltSmooth extends BlockSolid {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockBasaltSmooth() {

    }

    @Override
    public String getName() {
        return "Smooth Basalt";
    }

    @Override
    public int getId() {
        return BlockID.SMOOTH_BASALT;
    }

    @Override
    public double getHardness() {
        return 1.25;
    }

    @Override
    public double getResistance() {
        return 4.2;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.GRAY_BLOCK_COLOR;
    }
}
