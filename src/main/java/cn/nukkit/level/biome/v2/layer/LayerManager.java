package cn.nukkit.level.biome.v2.layer;

import cn.nukkit.level.biome.v2.data.LayerType;
import cn.nukkit.level.biome.v2.layer.type.*;

/**
 * @author GoodLucky777
 */
public class LayerManager implements LayerType {

    private Layer[] layers;
    
    public void init() {
        this.registerVanillaLayers();
    }
    
    public void initLayers(Layer[] layers) {
        layers[ISLAND_4096] = new LayerIsland();
    }
    
    public void registerLayer(Layer layer, Layer parent, int salt) {
        layer.setLayerSalt(salt > 0 ? LayerHelper.getLayerSalt(salt) : salt);
        layer.setStartSalt(0);
        layer.setStartSeed(0);
        layer.setParent(parent);
        layer.setParent2(null);
        layer.setScale(0);
        layer.setEdge(0);
        //layer.setGetMap = getMap;
        //layer.setNoise = null;
        //layer.setData = null;
    }
    
    public void registerVanillaLayers() {
        LayerStack layerStack = new LayerStack();
        Layer[] layers = layerStack.getLayers();
        
        this.initLayers(layers);
        
        this.registerLayer(layers[ISLAND_4096], null, 1);
        this.registerLayer(layers[ZOOM_2048], layers[ISLAND_4096], 2000);
        
    }
}
