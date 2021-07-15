package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCandleCyan extends BlockCandleBase {

    public BlockCandleCyan() {
    }

    @Override
    public String getName() {
        return "Cyan Candle";
    }

    @Override
    public int getId() {
        return CYAN_CANDLE;
    }

    @Override
    public int getCakeId() {
        return CYAN_CANDLE_CAKE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.CYAN_BLOCK_COLOR;
    }
}
