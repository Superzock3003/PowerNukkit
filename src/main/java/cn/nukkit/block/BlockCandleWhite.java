package cn.nukkit.block;

public class BlockCandleWhite extends BlockCandleBase {

    public BlockCandleWhite() {
    }

    @Override
    public String getName() {
        return "White Candle";
    }

    @Override
    public int getCakeId() {
        return WHITE_CANDLE_CAKE;
    }

    @Override
    public int getId() {
        return WHITE_CANDLE;
    }
}
