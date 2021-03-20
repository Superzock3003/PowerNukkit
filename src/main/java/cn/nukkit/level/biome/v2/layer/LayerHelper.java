package cn.nukkit.level.biome.v2.layer;

/**
 * @author GoodLucky777
 */
public class LayerHelper {

    public static long mcStepSeed(long s, long start) {
        return s * (s * 6364136223846793005L + 1442695040888963407L) + salt;
    }
    
    public static long getChunkSeed(long startSeed, int x, int z) {
        long chunkSeed = startSeed + x;
        chunkSeed = mcStepSeed(chunkSeed, z);
        chunkSeed = mcStepSeed(chunkSeed, x);
        chunkSeed = mcStepSeed(chunkSeed, z);
        return chunkSeed;
    }
}
