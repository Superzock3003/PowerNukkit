package cn.nukkit.block;

public class BlockCandleCakeYellow extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Yellow Candle Cake";
    }

    @Override
    public int getId() {
        return YELLOW_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return YELLOW_CANDLE;
    }
}
