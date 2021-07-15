package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleGreen extends BlockCandleBase {

    public BlockCandleGreen() {
    }

    @Override
    public String getName() {
        return "Green Candle";
    }

    @Override
    public int getId() {
        return GREEN_CANDLE;
    }

    @Override
    public int getCakeId() {
        return GREEN_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.GREEN_BLOCK_COLOR;
    }
}
