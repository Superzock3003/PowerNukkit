package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleRed extends BlockCandleBase {

    public BlockCandleRed() {
    }

    @Override
    public String getName() {
        return "Red Candle";
    }

    @Override
    public int getId() {
        return RED_CANDLE;
    }

    @Override
    public int getCakeId() {
        return RED_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.RED_BLOCK_COLOR;
    }
}
