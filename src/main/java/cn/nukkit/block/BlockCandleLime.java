package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleLime extends BlockCandleBase {

    public BlockCandleLime() {
    }

    @Override
    public String getName() {
        return "Lime Candle";
    }

    @Override
    public int getId() {
        return LIME_CANDLE;
    }

    @Override
    public int getCakeId() {
        return LIME_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.LIME_BLOCK_COLOR;
    }
}
