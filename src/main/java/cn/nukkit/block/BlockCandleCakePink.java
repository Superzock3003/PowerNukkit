package cn.nukkit.block;

public class BlockCandleCakePink extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Pink Candle Cake";
    }

    @Override
    public int getId() {
        return PINK_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return PINK_CANDLE;
    }
}
