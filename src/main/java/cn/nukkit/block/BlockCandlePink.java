package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandlePink extends BlockCandleBase {

    public BlockCandlePink() {
    }

    @Override
    public String getName() {
        return "Pink Candle";
    }

    @Override
    public int getId() {
        return PINK_CANDLE;
    }

    @Override
    public int getCakeId() {
        return PINK_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.PINK_BLOCK_COLOR;
    }
}
