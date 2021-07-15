package cn.nukkit.block;

public class BlockCandleCakeBlue extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Blue Candle Cake";
    }

    @Override
    public int getId() {
        return BLUE_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return BLUE_CANDLE;
    }
}
