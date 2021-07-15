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
public class BlockWallDeepslateTile extends BlockWallBase {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockWallDeepslateTile() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockWallDeepslateTile(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DEEPSLATE_TILE_WALL;
    }

    @Override
    public String getName() {
        return "Deepslate Tile Wall";
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
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
    public double getHardness() {
        return 3.5;
    }

    @Override
    public double getResistance() {
        return 6.0;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.GRAY_BLOCK_COLOR;
    }
}
