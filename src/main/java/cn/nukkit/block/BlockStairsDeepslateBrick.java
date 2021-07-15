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
public class BlockStairsDeepslateBrick extends BlockStairs {
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockStairsDeepslateBrick() {
        this(0);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public BlockStairsDeepslateBrick(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Deepslate Brick Stairs";
    }

    @Override
    public int getId() {
        return DEEPSLATE_BRICK_STAIRS;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.GRAY_BLOCK_COLOR;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public double getHardness() {
        return 3.5;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
