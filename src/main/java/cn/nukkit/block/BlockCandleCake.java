package cn.nukkit.block;

public class BlockCandleCake extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Candle Cake";
    }

    @Override
    public int getId() {
        return CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return CANDLE;
    }
}
