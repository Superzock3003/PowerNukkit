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
    
    public Biome getBiomeFromId(int id) {
        return this.biomes[id == 0 ? 42 : id]; // For backward compatibility (Ocean id changed to 42 from 0
    }
    
    public void init() {
        this.registerVanillaBiomes();
    }
    
    public void registerBiome(int id, float baseHeight, float heightVariation, float temperature, BiomeType type, BiomeTemperatureType temperatureType) {
        Preconditions.checkArgument(id >= 0 && id < MAX_BIOME_ID, "Biome ID " + id + " is out of range");
        
        biomes[id] = new Biome(id, baseHeight, heightVariation, temperature, type, temperatureType);
    }
    
    public void registerVanillaBiomes() {
        registerBiome(BiomeID.PLAINS.getId(), 0.1f, 0.05f, 0.8f, BiomeType.PLAINS, BiomeTemperatureType.LUSH);
        
        registerBiome(BiomeID.OCEAN.getId(), 1.0f, 0.1f, 0.5f, BiomeType.OCEAN, BiomeTemperatureType.OCEANIC);
    }
}
