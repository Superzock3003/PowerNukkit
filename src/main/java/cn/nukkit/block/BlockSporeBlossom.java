package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockSporeBlossom extends BlockFlowable {

    public BlockSporeBlossom() {
        super(0);
    }

    @Override
    public String getName() {
        return "Spore Blossom";
    }

    @Override
    public int getId() {
        return SPORE_BLOSSOM;
    }

    @Override
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        Block up = this.up();
        if(!up.isSolid(BlockFace.DOWN) || up instanceof BlockLeaves) {
            return false;
        }
        return this.getLevel().setBlock(this, this, true, true);
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return CommonBlockProperties.EMPTY_PROPERTIES;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }

    @Override
    public int onUpdate(int type) {
        if(type == Level.BLOCK_UPDATE_NORMAL) {
            Block up = this.up();
            if(up.getId() == AIR || up instanceof BlockLeaves) {
                this.getLevel().useBreakOn(this);
            }
            return type;
        }
        return 0;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.PINK_BLOCK_COLOR;
    }
}
