package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleGray extends BlockCandleBase {

    public BlockCandleGray() {
    }

    @Override
    public String getName() {
        return "Gray Candle";
    }

    @Override
    public int getId() {
        return GRAY_CANDLE;
    }

    @Override
    public int getCakeId() {
        return GRAY_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.GRAY_BLOCK_COLOR;
    }
}
