package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockproperty.ArrayBlockProperty;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.BooleanBlockProperty;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockPointedDripstone extends BlockSolid {

    public static final ArrayBlockProperty<DripstoneThickness> THICKNESS = new ArrayBlockProperty<>("dripstone_thickness", false, DripstoneThickness.class);

    public static final BooleanBlockProperty HANGING = new BooleanBlockProperty("hanging", false);

    public static final BlockProperties PROPERTIES = new BlockProperties(THICKNESS, HANGING);

    @Override
    public String getName() {
        return "Pointed Dripstone";
    }

    @Override
    public int getId() {
        return POINTED_DRIPSTONE;
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public double getHardness() {
        return 1.5;
    }

    @Override
    public double getResistance() {
        return 3;
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
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        if(face != BlockFace.UP && face != BlockFace.DOWN || !(target instanceof BlockSolid || target instanceof BlockSolidMeta)) {
            return false;
        }
        if(face == BlockFace.DOWN) {
            this.setPropertyValue(HANGING, true);
        } else {
            this.setPropertyValue(HANGING, false);
        }
        if(target instanceof BlockPointedDripstone) {
            int i = 0;
            while(true) {
                Block side = target.getSide(face.getOpposite(), i);
                if(!(side instanceof BlockPointedDripstone)) {
                    break;
                }
                side.setPropertyValue(THICKNESS, side.getPropertyValue(THICKNESS).next());
                side.getLevel().setBlock(side, side, true, true);
                i++;
            }
        }
        Block side = this.getSide(face);
        if(side instanceof BlockPointedDripstone) {
            this.setPropertyValue(THICKNESS, DripstoneThickness.MERGE);
            side.setPropertyValue(THICKNESS, DripstoneThickness.MERGE);
            side.getLevel().setBlock(side, side, true, true);
        } else {
            this.setPropertyValue(THICKNESS, DripstoneThickness.TIP);
        }
        return this.getLevel().setBlock(this, this, true, true);
    }

    @Override
    public int onUpdate(int type) {
        if(type == Level.BLOCK_UPDATE_NORMAL) {
            boolean hanging = this.getPropertyValue(HANGING);
            BlockFace face = hanging ? BlockFace.UP : BlockFace.DOWN;
            Block side = this.getSide(face);
            if(!(side instanceof BlockAir)) {
                return 0;
            }
            this.getLevel().useBreakOn(this);
            int i = 1;
            DripstoneThickness lastThickness = DripstoneThickness.TIP;
            while(true) {
                Block sideOfSide = side.getSide(face, i);
                if(!(sideOfSide instanceof BlockPointedDripstone)) {
                    break;
                }
                sideOfSide.setPropertyValue(THICKNESS, lastThickness);
                sideOfSide.getLevel().setBlock(sideOfSide, sideOfSide, true, true);
                lastThickness = lastThickness.next();
                i++;
            }
            return type;
        }
        return 0;
    }

    public enum DripstoneThickness {

        TIP,
        FRUSTUM,
        MIDDLE,
        BASE,
        MERGE;

        public DripstoneThickness next() {
            switch(this) {
                case TIP:
                    return FRUSTUM;
                case FRUSTUM:
                    return MIDDLE;
                case MIDDLE:
                case BASE:
                    return BASE;
            }
            return TIP;
        }
    }
}
