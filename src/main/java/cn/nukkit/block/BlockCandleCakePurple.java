package cn.nukkit.block;

public class BlockCandleCakePurple extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Purple Candle Cake";
    }

    @Override
    public int getId() {
        return PURPLE_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return PURPLE_CANDLE;
    }
}
