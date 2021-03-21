package cn.nukkit.level.biome.v2.layer;

/**
 * @author GoodLucky777
 */
public class LayerHelper {

    public static long mcStepSeed(long s, long salt) {
        return s * (s * 6364136223846793005L + 1442695040888963407L) + salt;
    }
    
    public static int mcFirstInt(long salt, int mod) {
        int returnValue = (int)((salt >> 24) % mod);
        if (returnValue < 0) {
            returnValue += mod;
        }
        return returnValue;
    }
    
    public static boolean mcFirstIsZero(long salt, int mod) {
        return (int)((salt >> 24) % mod) == 0;
    }
    
    public static long getChunkSeed(long startSeed, int x, int z) {
        long chunkSeed = startSeed + x;
        chunkSeed = mcStepSeed(chunkSeed, z);
        chunkSeed = mcStepSeed(chunkSeed, x);
        chunkSeed = mcStepSeed(chunkSeed, z);
        return chunkSeed;
    }
    
    public static long getLayerSalt(long salt) {
        long layerSalt = mcStepSeed(salt, salt);
        layerSalt = mcStepSeed(layerSalt, salt);
        layerSalt = mcStepSeed(layerSalt, salt);
        return layerSalt;
    }
}
