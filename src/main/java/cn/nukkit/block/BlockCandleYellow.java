package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleYellow extends BlockCandleBase {

    public BlockCandleYellow() {
    }

    @Override
    public String getName() {
        return "Yellow Candle";
    }

    @Override
    public int getId() {
        return YELLOW_CANDLE;
    }

    @Override
    public int getCakeId() {
        return YELLOW_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.YELLOW_BLOCK_COLOR;
    }
}
