package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleOrange extends BlockCandleBase {

    public BlockCandleOrange() {
    }

    @Override
    public String getName() {
        return "Orange Candle";
    }

    @Override
    public int getId() {
        return ORANGE_CANDLE;
    }

    @Override
    public int getCakeId() {
        return ORANGE_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.ORANGE_BLOCK_COLOR;
    }
}
