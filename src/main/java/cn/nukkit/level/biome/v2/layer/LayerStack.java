package cn.nukkit.level.biome.v2.layer;

import cn.nukkit.level.biome.v2.data.LayerType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GoodLucky777
 */
@Getter
@Setter
public class Layer implements LayerType {

    private Layer[] layers = new Layer[LAYER_COUNT];
    private Layer entry_1;
    private Layer entry_4;
    //private PerlinNoise oceanRnd;
}
