package cn.nukkit.block;

public class BlockCandleCakeOrange extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Orange Candle Cake";
    }

    @Override
    public int getId() {
        return ORANGE_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return ORANGE_CANDLE;
    }
}
