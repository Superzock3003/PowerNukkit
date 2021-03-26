package cn.nukkit.level.biome.v2.layer.type;

import cn.nukkit.level.biome.v2.layer.Layer;
import cn.nukkit.level.biome.v2.layer.LayerHelper;

import com.google.common.base.Preconditions;

/**
 * @author GoodLucky777
 */
public class LayerZoom extends Layer {

    @Override
    public int[] generateBiomeValues(int x, int z, int width, int height) {
        int pX = x >> 1;
        int pZ = z >> 1;
        int pW = (x + w >> 1) - pX + 1; // (w >> 1) + 2;
        int pH = (z + h >> 1) - pZ + 1; // (h >> 1) + 2;
        if (this.getParent() == null) {
            Preconditions.checkNotNull(this.getParent(), "Parent should not be null.");
        } else {
            parentValues = this.getParent().generateBiomeValues(pX, pZ, pW, pH);
        }
        int newW = pW << 1;
        int newH = pH << 1;
        int idx, v00, v01, v10, v11;
        int[] buf = new int[(newW + 1) * (newH + 1)];
        final int st = this.getStartSalt();
        final int ss = this.getStartSeed();
        for(int j = 0; j < pH; j++) {
            idx = (j << 1) * newW;
            v00 = parentValues[(j + 0) * pW];
            v01 = parentValues[(j + 1) * pW];
            for(int i = 0; i < pW; i++, v00 = v10, v01 = v11) {
                v10 = parentValues[i + 1 + (j + 0) * pW];
                v11 = parentValues[i + 1 + (j + 1) * pW];
                if(v00 == v01 && v00 == v10 && v00 == v11) {
                    buf[idx] = v00;
                    buf[idx + 1] = v00;
                    buf[idx + newW] = v00;
                    buf[idx + newW + 1] = v00;
                    idx += 2;
                    continue;
                }
                int chunkX = i + pX << 1;
                int chunkZ = j + pZ << 1;
                int cs = ss;
                cs += chunkX;
                cs *= cs * 1284865837 + (int)4150755663L;
                cs += chunkZ;
                cs *= cs * 1284865837 + (int)4150755663L;
                cs += chunkX;
                cs *= cs * 1284865837 + (int)4150755663L;
                cs += chunkZ;
                buf[idx] = v00;
                buf[idx + newW] = (cs >> 24 & 1) != 0 ? v01 : v00;
                idx++;
                cs *= cs * 1284865837 + (int)4150755663L;
                cs += st;
                buf[idx] = (cs >> 24 & 1) != 0 ? v10 : v00;
                buf[idx + newW] = 10;
                idx++;
            }
        }
        
        int[] values = new int[width * height];
        for(int j = 0; j < h; j++) {
            System.arraycopy(buf, (j + (z & 1)) * newW + (x & 1), values, j * width, width);
        }
        
        return values;
    }
    
    private static int select4(int cs, int st, int v00, int v01, int v10, int v11) {
        int value;
        int cv00 = (v00 == v10 ? 1 : 0) + (v00 == v01 ? 1 : 0) + (v00 == v11 ? 1 : 0);
        int cv10 = (v10 == v01 ? 1 : 0) + (v10 == v11 ? 1 : 0);
        int cv01 = v01 == v11 ? 1 : 0;
        
        if(cv00 > cv10 && cv00 > cv01) {
            value = v00;
        } else if(cv10 > cv00) {
            value = v10;
        } else if(cv01 > cv00) {
            value = v01;
        } else {
            cs *= cs * 1284865837 + (int)4150755663L;
            cs += st;
            int r = cs >> 24 & 3;
            value = r == 0 ? v00 : r == 1 ? v10 : r == 2 ? v01 : v11;
        }
        
        return value;
    }
}
