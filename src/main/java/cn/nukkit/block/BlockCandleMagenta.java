package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleMagenta extends BlockCandleBase {

    public BlockCandleMagenta() {
    }

    @Override
    public String getName() {
        return "Magenta Candle";
    }

    @Override
    public int getId() {
        return MAGENTA_CANDLE;
    }

    @Override
    public int getCakeId() {
        return MAGENTA_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.MAGENTA_BLOCK_COLOR;
    }
}
