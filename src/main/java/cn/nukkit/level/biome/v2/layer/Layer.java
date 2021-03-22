package cn.nukkit.level.biome.v2.layer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GoodLucky777
 */
@Getter
@Setter
public abstract class Layer {

    private long layerSalt;
    private long startSalt;
    private long startSeed;
    //void *noise;
    //void *data;
    private int scale;
    private int edge;
    private Layer parent;
    private Layer parent2;
    
    public abstract int[] generateBiomeValues(int x, int z, int width, int height, int[] parentValues);
    
    /*public int[] generateBiomeData(int x, int z, int width, int height) {
        return generateBiomeData(x, z, width, height, null);
    }
    
    public int[] generateBiomeData(int x, int z, int width, int height, int[] parentData) {
        int[] data = this.generateBiomeValues(x, z, width, height, parentData);
        
        if (this.parent != null) {
            data = parent.generateBiomeData(x, z, width, height, data);
        }
        
        return data;
    }*/
}
