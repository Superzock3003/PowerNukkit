package cn.nukkit.block;

public class BlockCandleCakeBlack extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Black Candle Cake";
    }

    @Override
    public int getId() {
        return BLACK_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return BLACK_CANDLE;
    }
}
