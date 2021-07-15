package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.BooleanBlockProperty;
import cn.nukkit.blockproperty.IntBlockProperty;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.math.BlockFace;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BlockCandleBase extends BlockTransparent {

    public static final IntBlockProperty CANDLES = new IntBlockProperty("candles", false, 3);

    public static final BooleanBlockProperty LIT = new BooleanBlockProperty("lit", false);

    public static final BlockProperties PROPERTIES = new BlockProperties(CANDLES, LIT);

    public abstract int getCakeId();

    public int getCandleCount() {
        return this.getIntValue(CANDLES) + 1;
    }

    public void setCandleCount(int candles) {
        this.setIntValue(CANDLES, candles - 1);
    }

    public boolean isLit() {
        return this.getBooleanValue(LIT);
    }

    public void setLit(boolean lit) {
        this.setBooleanValue(LIT, lit);
    }

    @Override
    public double getResistance() {
        return 0.1;
    }

    @Override
    public double getHardness() {
        return 0.1;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public int getWaterloggingLevel() {
        return 1;
    }

    @Override
    public int getLightLevel() {
        return this.isLit() ? this.getCandleCount() * 3 : 0;
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @Override
    public Item[] getDrops(Item item) {
        Item drop = this.toItem();
        drop.setCount(this.getCandleCount());
        return new Item[] {drop};
    }

    @Override
    public Item toItem() {
        return Item.get(this.getItemId());
    }

    @Override
    public boolean place(@Nonnull Item item, @Nonnull Block block, @Nonnull Block target, @Nonnull BlockFace face, double fx, double fy, double fz, @Nullable Player player) {
        if(target.getId() == this.getId()) {
            BlockCandleBase targetCandle = (BlockCandleBase) target;
            if(targetCandle.getCandleCount() >= 4) {
                return false;
            }
            targetCandle.setCandleCount(targetCandle.getCandleCount() + 1);
            this.getLevel().setBlock(targetCandle, targetCandle, true, true);
            return true;
        }
        if(target.getId() == CAKE_BLOCK && ((BlockCake) target).getBites() == 0) {
            this.getLevel().setBlock(target, Block.get(this.getCakeId()), true, true);
            return true;
        }
        if(!this.down().isSolid()) {
            return false;
        }

        this.setCandleCount(1);
        this.setLit(false);
        return this.getLevel().setBlock(this, this, true, true);
    }

    @Override
    public boolean onActivate(@Nonnull Item item, @Nullable Player player) {
        if(item.getId() == ItemID.FLINT_AND_STEEL && !this.isLit()) {
            item.useOn(this);
            this.setLit(true);
            this.getLevel().setBlock(this, this, true, true);
            return true;
        }
        return false;
    }

    @Override
    public int onTouch(@Nullable Player player, PlayerInteractEvent.Action action) {
        if(player != null && action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && player.getInventory().getItemInHand().isNull()) {
            this.setLit(false);
            this.getLevel().setBlock(this, this, true, true);
        }
        return super.onTouch(player, action);
    }
}
