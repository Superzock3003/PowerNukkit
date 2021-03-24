package cn.nukkit.level.biome.v2.layer.type;

import cn.nukkit.level.biome.v2.layer.Layer;
import cn.nukkit.level.biome.v2.layer.LayerHelper;

/**
 * @author GoodLucky777
 */
public class LayerAddIsland extends Layer {

    @Override
    public int[] generateBiomeValues(int x, int z, int width, int height) {
        int[] values;
        if (this.getParent() == null) {
            values = new int[width * height];
        } else {
            values = this.getParent().generateBiomeValues(x, z, width, height);
        }
        
        int pX = x - 1;
        int pZ = z - 1;
        int pW = width + 2;
        int pH = height + 2;
        
        long st = this.getStartSalt();
        long ss = this.getStartSeed();
        long cs;
        
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int v00 = values[i + 0 + (j + 0) * pW];
                int v20 = values[i + 2 + (j + 0) * pW];
                int v02 = values[i + 0 + (j + 2) * pW];
                int v22 = values[i + 2 + (j + 2) * pW];
                int v11 = values[i + 1 + (j + 1) * pW];
                
                int vt0 = values[i + 1 + (j + 0) * pW];
                int vt2 = values[i + 1 + (j + 2) * pW];
                
                int v = v11;
                
                switch (v11) {
                    case 0:
                        if (v00 != 0 || v20 != 0 || v02 != 0 || v22 != 0) {
                            cs = LayerHelper.getChunkSeed(ss, i + x, j + z);
                            int inc = 0;
                            v = 1;
                            
                            if (v00 != 0) {
                                inc++;
                                v = v00;
                                cs = LayerHelper.mcStepSeed(cs, st);
                            }
                            if (v20 != 0) {
                                if (inc++ == 1 || LayerHelper.mcFirstIsZero(cs, 2)) {
                                    v = v20;
                                }
                                cs = LayerHelper.mcStepSeed(cs, st);
                            }
                            if (v02 != 0) {
                                switch (inc++) {
                                    case 1:
                                        v = v02;
                                        break;
                                    case 2:
                                        if (LayerHelper.mcFirstIsZero(cs, 2)) {
                                            v = v02;
                                        }
                                        break;
                                    default:
                                        if (LayerHelper.mcFirstIsZero(cs, 3)) {
                                            v = v02;
                                        }
                                        break;
                                }
                                cs = LayerHelper.mcStepSeed(cs, st);
                            }
                            if (v22 != 0) {
                                switch (++inc) {
                                    case 1:
                                        v = v22;
                                        break;
                                    case 2:
                                        if (LayerHelper.mcFirstIsZero(cs, 2)) {
                                            v = v22;
                                        }
                                        break;
                                    case 3:
                                        if (LayerHelper.mcFirstIsZero(cs, 3)) {
                                            v = v22;
                                        }
                                        break;
                                    default:
                                        if (LayerHelper.mcFirstIsZero(cs, 4)) {
                                            v = v22;
                                        }
                                        break;
                                }
                                cs = LayerHelper.mcStepSeed(cs, st);
                            }
                            
                            if (v != 4 && !LayerHelper.mcFirstIsZero(cs, 3)) {
                                v = 0;
                            }
                        }
                        break;
                    case 4:
                        break;
                    default:
                        if (v00 == 0 || v20 == 0 || v02 == 0 || v22 == 0) {
                            cs = LayerHelper.getChunkSeed(ss, i + x, j + z);
                            if (LayerHelper.mcFirstIsZero(cs, 5)) {
                                v = 0;
                            }
                        }
                        break;
                }
                
                values[i + j * width] = v;
                v00 = vt0;
                vt0 = v20;
                v02 = vt2;
                vt2 = v22;
            }
        }
        
        return values;
    }
}
