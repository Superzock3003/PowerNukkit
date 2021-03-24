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
    
    public abstract int[] generateBiomeValues(int x, int z, int width, int height);
}
