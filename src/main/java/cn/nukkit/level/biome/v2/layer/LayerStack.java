package cn.nukkit.level.biome.v2.layer;

import cn.nukkit.level.generator.noise.vanilla.f.NoiseGeneratorPerlinF;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GoodLucky777
 */
@Getter
@Setter
public class LayerStack {

    private Layer[] layers;
    private Layer entry1;
    private Layer entry4;
    private NoiseGeneratorPerlinF oceanRnd;
    
    public int[] generateBiomeData(int x, int z, int width, int height) {
        return layers[/*layers.length - 1*/1].generateBiomeValues(x, z, width, height);
    }
}
