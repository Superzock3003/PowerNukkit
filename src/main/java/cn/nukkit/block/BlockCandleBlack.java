package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleBlack extends BlockCandleBase {

    public BlockCandleBlack() {
    }

    @Override
    public String getName() {
        return "Black Candle";
    }

    @Override
    public int getId() {
        return BLACK_CANDLE;
    }

    @Override
    public int getCakeId() {
        return BLACK_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.BLACK_BLOCK_COLOR;
    }
}
