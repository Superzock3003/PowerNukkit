package cn.nukkit.level.biome.v2;

import cn.nukkit.level.biome.v2.data.BiomeID;
import cn.nukkit.level.biome.v2.data.BiomeTemperatureType;
import cn.nukkit.level.biome.v2.data.BiomeType;

import com.google.common.base.Preconditions;

/**
 * @author GoodLucky777
 */
public class BiomeManager {

    public static final int MAX_BIOME_ID = 256;
    
    public Biome[] biomes = new Biome[MAX_BIOME_ID];
    
    public void init() {
        this.registerVanillaBiomes();
    }
    
    protected void registerBiome(int id, float height, float temperature, BiomeType type, BiomeTemperatureType temperatureType) {
        Preconditions.checkArgument(id >= 0 && id < MAX_BIOME_ID, "Biome ID " + id + " is out of range");
        
        biomes[id] = new Biome(id, height, temperature, type, temperatureType);
    }
    
    public void registerVanillaBiomes() {
        registerBiome(BiomeID.PLAINS.getId(), 0.1f, 0.8f, BiomeType.PLAINS, BiomeTemperatureType.LUSH);
    }
}
