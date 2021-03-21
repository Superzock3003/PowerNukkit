package cn.nukkit.level.biome.v2.layer;

/**
 * @author GoodLucky777
 */
public class LayerManager {

    public void init() {
        
    }
    
    public void registerLayer(Layer layer, Layer parent, int salt) {
        layer.setSalt = salt > 0 ? getLayerSalt(salt) : salt;
        layer.setStartSalt = 0;
        layer.setStartSeed = 0;
        layer.setParnet = parent;
        layer.setParent2 = null;
        layer.setScale = 0;
        layer.setEdge = 0;
        layer.setGetMap = getMap;
        layer.setNoise = null;
        layer.setData = null;
    }
}
