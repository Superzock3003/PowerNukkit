package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandlePurple extends BlockCandleBase {

    public BlockCandlePurple() {
    }

    @Override
    public String getName() {
        return "Purple Candle";
    }

    @Override
    public int getId() {
        return PURPLE_CANDLE;
    }

    @Override
    public int getCakeId() {
        return PURPLE_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.PURPLE_BLOCK_COLOR;
    }
}
