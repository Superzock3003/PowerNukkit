package cn.nukkit.level.biome.v2.layer.type;

import cn.nukkit.level.biome.v2.layer.Layer;
import cn.nukkit.level.biome.v2.layer.LayerHelper;

/**
 * @author GoodLucky777
 */
public class LayerZoomIsland extends Layer {

    @Override
    public int[] generateBiomeValues(int x, int z, int width, int height, int[] parentValues) {
        int[] values;
        if (parentValues == null) {
            values = new int[width * height]; 
        } else {
            values = parentValues;
        }
        
        int pX = x >> 1;
        int pZ = z >> 1;
        int pW = ((x + width) >> 1) - pX + 1;
        int pH = ((z + height) >> 1) - pZ + 1;
        int i, j;
        
        int newW = (pW) << 1;
        int newH = (pH) << 1;
        int idx, v00, v01, v10, v11;
        int buf = (int) malloc((newW + 1) * (newH + 1) * sizeof(buf));
        
        final int st = (int) this.getStartSalt();
        final int ss = (int) this.getStartSeed();
        
        for (j = 0; j < pH; j++) {
            idx = (j << 1) * newW;
            
            v00 = values[(j + 0) * pW];
            v01 = values[(j + 1) * pW];
            
            for (i = 0; i < pW; i++, v00 = v10, v01 = v11) {
                v10 = values[i + 1 + (j + 0) * pW];
                v11 = values[i + 1 + (j + 1) * pW];
                
                if (v00 == v01 && v00 == v10 && v00 == v11) {
                    buf[idx] = v00;
                    buf[idx + 1] = v00;
                    buf[idx + newW] = v00;
                    buf[idx + newW + 1] = v00;
                    idx += 2;
                    continue;
                }
                
                int chunkX = (i + pX) << 1;
                int chunkZ = (j + pZ) << 1;
                
                int cs = ss;
                cs += chunkX;
                cs *= cs * 1284865837 + 4150755663;
                cs += chunkZ;
                cs *= cs * 1284865837 + 4150755663;
                cs += chunkX;
                cs *= cs * 1284865837 + 4150755663;
                cs += chunkZ;
                
                buf[idx] = v00;
                buf[idx + newW] = (cs >> 24) & 1 ? v01 : v00;
                idx++;
                
                cs *= cs * 1284865837 + 4150755663;
                cs += st;
                buf[idx] = (cs >> 24) & 1 ? v10 : v00;
                
                cs *= cs * 1284865837 + 4150755663;
                cs += st;
                int r = (cs >> 24) & 3;
                buf[idx + newW] = r == 0 ? v00 : r == 1 ? v10 : r == 2 ? v01 : v11;
                idx++;
            }
        }
        
        for (j = 0; j < h; j++) {
            memcpy(values[j * width], buf[(j + (z & 1)) * newW + (x & 1)], width * sizeof(int));
        }
        
        //free(buf);
        
        return values;
    }
}
