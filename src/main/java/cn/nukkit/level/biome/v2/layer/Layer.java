package cn.nukkit.level.biome.v2.layer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GoodLucky777
 */
@Getter
@Setter
public class Layer {

    private long layerSalt;
    private long startSalt;
    private long startSeed;
    //void *noise;
    //void *data;
    private int scale;
    private int edge;
    //int (*getMap)(const Layer *, int *, int, int, int, int);
    private Layer parent, parent2;
    
    public abstract int[] generateBiomeValues(int x, int z, int width, int height, int[] parentValues);
    
    public int[] generateBiomeData(int x, int z, int width, int height, int[] parentValues) {
        int[] data = this.generateBiomeValues(x, z, width, height, parentValues);
        
        if (this.parent != null) {
            data = parent.generateBiomeData(x, z, width, height, data);
        }
        
        return data;
    }
}
