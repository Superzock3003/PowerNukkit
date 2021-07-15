package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleBlue extends BlockCandleBase {

    public BlockCandleBlue() {
    }

    @Override
    public String getName() {
        return "Blue Candle";
    }

    @Override
    public int getId() {
        return BLUE_CANDLE;
    }

    @Override
    public int getCakeId() {
        return BLUE_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.BLUE_BLOCK_COLOR;
    }
}
