package cn.nukkit.level.biome.v2.layer;

import cn.nukkit.level.biome.v2.data.LayerType;
import cn.nukkit.level.biome.v2.layer.type.*;

/**
 * @author GoodLucky777
 */
public class LayerManager implements LayerType {

    private LayerStack layerStack;
    
    public int[] generateBiomeData(int x, int z, int width, int height) {
        return this.layerStack.generateBiomeData(x, z, width, height);
    }
    
    public Layer[] getLayers() {
        return this.layerStack.getLayers();
    }
    
    public LayerStack getLayerStack() {
        return this.layerStack;
    }
    
    public void init() {
        this.registerVanillaLayers();
    }
    
    public void initVanillaLayers(Layer[] layers) {
        layers[ISLAND_4096] = new LayerIsland();
        layers[ZOOM_2048] = new LayerZoomIsland();
        layers[ADD_ISLAND_2048] = new LayerAddIsland();
        layers[ZOOM_1024] = new LayerZoom();
    }
    
    public void registerLayer(Layer layer, Layer parent, int salt) {
        layer.setLayerSalt(salt > 0 ? LayerHelper.getLayerSalt(salt) : salt);
        layer.setStartSalt(0);
        layer.setStartSeed(0);
        layer.setParent(parent);
        layer.setParent2(null);
        layer.setScale(0);
        layer.setEdge(0);
        
        //layer.setNoise = null;
        //layer.setData = null;
    }
    
    public void registerVanillaLayers() {
        this.layerStack = new LayerStack();
        layerStack.setLayers(new Layer[/*LAYER_COUNT*/4]);
        Layer[] layers = layerStack.getLayers();
        
        this.initVanillaLayers(layers);
        
        this.registerLayer(layers[ISLAND_4096], null, 1);
        this.registerLayer(layers[ZOOM_2048], layers[ISLAND_4096], 2000);
        this.registerLayer(layers[ADD_ISLAND_2048], layers[ZOOM_2048], 1);
        this.registerLayer(layers[ZOOM_1024], layers[ADD_ISLAND_2048], 2001);
        
        this.layerStack.setLayers(layers);
    }
}
