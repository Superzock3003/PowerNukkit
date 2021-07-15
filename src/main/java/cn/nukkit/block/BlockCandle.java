package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandle extends BlockCandleBase {

    @Override
    public String getName() {
        return "Candle Cake";
    }

    @Override
    public int getId() {
        return CANDLE;
    }

    @Override
    public int getCakeId() {
        return CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_BLOCK_COLOR;
    }
}
