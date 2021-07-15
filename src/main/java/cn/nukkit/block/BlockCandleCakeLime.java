package cn.nukkit.block;

public class BlockCandleCakeLime extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Lime Candle Cake";
    }

    @Override
    public int getId() {
        return LIME_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return LIME_CANDLE;
    }
}
