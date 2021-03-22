package cn.nukkit.level.biome.v2;

import cn.nukkit.level.biome.v2.data.BiomeType;
import cn.nukkit.level.biome.v2.data.BiomeTemperatureType;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author GoodLucky777
 */
@AllArgsConstructor
@Getter
public class Biome {

    private final int id;
    private final float baseHeight;
    private final float heightVariation;
    private final float temperature;
    private final BiomeType type;
    private final BiomeTemperatureType temperatureType;
}
