package cn.nukkit.block;

public class BlockCandleCakeGray extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Gray Candle Cake";
    }

    @Override
    public int getId() {
        return GRAY_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return GRAY_CANDLE;
    }
}
