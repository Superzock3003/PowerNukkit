package cn.nukkit.level.biome.v2;

import cn.nukkit.level.biome.v2.data.BiomeType;
import cn.nukkit.level.biome.v2.data.BiomeTemperatureType;

public class Biome {

    private final int id;
    private final float height;
    private final float temperature;
    private final BiomeType type;
    private final BiomeTemperatureType temperatureType;
    
    public Biome(int id, float height, float temperature, BiomeType type, BiomeTemperatureType temperatureType) {
        this.id = id;
        this.height = height;
        this.temperature = temperature;
        this.type = type;
        this.temperatureType = temperatureType;
    }
}
