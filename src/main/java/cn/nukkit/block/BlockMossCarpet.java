package cn.nukkit.block;

import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.CommonBlockProperties;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;

import javax.annotation.Nonnull;

public class BlockMossCarpet extends BlockFlowable {

    public BlockMossCarpet() {
        this(0);
    }

    public BlockMossCarpet(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Moss Carpet";
    }

    @Override
    public int getId() {
        return MOSS_CARPET;
    }

    @Nonnull
    @Override
    public BlockProperties getProperties() {
        return CommonBlockProperties.EMPTY_PROPERTIES;
    }

    @Override
    public double getHardness() {
        return 0.1;
    }

    @Override
    public double getResistance() {
        return 0.1;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (this.down().getId() == Item.AIR) {
                this.getLevel().useBreakOn(this);

                return type;
            }
        }

        return 0;
    }
}