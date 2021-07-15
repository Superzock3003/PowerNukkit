package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleLightGray extends BlockCandleBase {

    public BlockCandleLightGray() {
    }

    @Override
    public String getName() {
        return "Light Gray Candle";
    }

    @Override
    public int getId() {
        return LIGHT_GRAY_CANDLE;
    }

    @Override
    public int getCakeId() {
        return LIGHT_GRAY_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.LIGHT_GRAY_BLOCK_COLOR;
    }
}
