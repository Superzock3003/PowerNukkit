package cn.nukkit.block;

public class BlockCandleCakeBrown extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Brown Candle Cake";
    }

    @Override
    public int getId() {
        return BROWN_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return BROWN_CANDLE;
    }
}
