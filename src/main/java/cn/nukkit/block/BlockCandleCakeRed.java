package cn.nukkit.block;

public class BlockCandleCakeRed extends BlockCandleCakeBase {

    @Override
    public String getName() {
        return "Red Candle Cake";
    }

    @Override
    public int getId() {
        return RED_CANDLE_CAKE;
    }

    @Override
    public int getCandleId() {
        return RED_CANDLE;
    }
}
