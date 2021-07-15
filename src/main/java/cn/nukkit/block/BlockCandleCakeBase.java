package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.BooleanBlockProperty;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.item.food.Food;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BlockCandleCakeBase extends BlockTransparent {

    public static final BooleanBlockProperty LIT = new BooleanBlockProperty("lit", false);

    public static final BlockProperties PROPERTIES = new BlockProperties(LIT);

    public abstract int getCandleId();

    @Override
    public double getHardness() {
        return 0.5;
    }

    @Override
    public double getResistance() {
        return 0.5;
    }

    public boolean isLit() {
        return this.getBooleanValue(LIT);
    }

    public void setLit(boolean lit) {
        this.setBooleanValue(LIT, lit);
    }

    @Override
    public boolean breaksWhenMoved() {
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean sticksToPiston() {
        return false;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {Item.get(255 - getCandleId())};
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.AIR_BLOCK_COLOR;
    }

    @Override
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        if(this.down().getId() == AIR) {
            return false;
        }
        this.setLit(false);
        return this.getLevel().setBlock(this, this, true, true);
    }

    @Override
    public boolean onActivate(@Nonnull Item item, Player player) {
        if(item.getId() == ItemID.FLINT_AND_STEEL && !this.isLit()) {
            item.useOn(this);
            this.setLit(true);
            this.getLevel().setBlock(this, this, true, true);
            return true;
        }
        if (player != null && (player.isCreative() || player.getFoodData().getLevel() < player.getFoodData().getMaxLevel())) {
            this.getLevel().useBreakOn(this);
            BlockCake cake = new BlockCake();
            cake.setBites(1);
            Food.getByRelative(cake).eatenBy(player);
            this.getLevel().setBlock(this, cake, true, true);
            return true;
        }
        return false;
    }

    @Override
    public int onUpdate(int type) {
        if(type == Level.BLOCK_UPDATE_NORMAL) {
            if(this.down().getId() == AIR) {
                this.getLevel().setBlock(this, Block.get(AIR), true, true);
            }
            return type;
        }
        return 0;
    }
}
