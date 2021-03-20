package cn.nukkit.level.biome.v2.layer;

/**
 * @author GoodLucky777
 */
public class Layer {

    private final long layerSalt;
    private final long startSalt;
    private final long startSeed;
    //void *noise;
    //void *data;
    private final int scale;
    private final int edge;
    //int (*getMap)(const Layer *, int *, int, int, int, int);
    private final Layer parent, parent2;
}
