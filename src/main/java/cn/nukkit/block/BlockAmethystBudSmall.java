package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.MinecraftItemID;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.SimpleAxisAlignedBB;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public class BlockAmethystBudSmall extends BlockTransparent {

    @Override
    public String getName() {
        return "Small Amethyst Bud";
    }

    @Override
    public int getId() {
        return SMALL_AMETHYST_BUD;
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return new BlockProperties(CommonBlockProperties.FACING_DIRECTION);
    }

    @Override
    public double getHardness() {
        return 1.5;
    }

    @Override
    public double getResistance() {
        return 1.5;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }

    @Override
    public int getWaterloggingLevel() {
        return 1;
    }

    @Override
    public boolean breaksWhenMoved() {
        return true;
    }

    @Override
    public boolean sticksToPiston() {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        BlockFace face = this.getPropertyValue(CommonBlockProperties.FACING_DIRECTION);
        return new SimpleAxisAlignedBB(face == BlockFace.EAST ? this.getFloorX() + 1 : this.getFloorX(), face == BlockFace.UP ? this.getFloorY() + 1 : this.getFloorY(), face == BlockFace.NORTH ? this.getFloorZ() + 1 : this.getFloorZ(), face == BlockFace.EAST ? this.getFloorX() + 0.9 : this.getFloorX() + 0.1, face == BlockFace.UP ? this.getFloorY() + 0.9 : this.getFloorY() + 0.1, face == BlockFace.NORTH ? this.getFloorZ() + 0.9 : this.getFloorZ() + 0.1);
    }

    @Override
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        if(!(target instanceof BlockSolid || target instanceof BlockSolidMeta)) {
            return false;
        }
        setPropertyValue(CommonBlockProperties.FACING_DIRECTION, face);
        return this.getLevel().setBlock(this, this, true, true);
    }

    @Override
    public int onUpdate(int type) {
        if(type == Level.BLOCK_UPDATE_NORMAL) {
            BlockFace face = this.getPropertyValue(CommonBlockProperties.FACING_DIRECTION).getOpposite();
            if(!(this.getSide(face) instanceof BlockSolid || this.getSide(face) instanceof BlockSolidMeta)) {
                this.getLevel().useBreakOn(this);
            }
            return type;
        }
        return 0;
    }
}
