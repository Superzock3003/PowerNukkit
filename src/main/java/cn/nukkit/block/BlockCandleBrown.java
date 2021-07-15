package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleBrown extends BlockCandleBase {

    public BlockCandleBrown() {
    }

    @Override
    public String getName() {
        return "Brown Candle";
    }

    @Override
    public int getId() {
        return BROWN_CANDLE;
    }

    @Override
    public int getCakeId() {
        return BROWN_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.BROWN_BLOCK_COLOR;
    }
}
