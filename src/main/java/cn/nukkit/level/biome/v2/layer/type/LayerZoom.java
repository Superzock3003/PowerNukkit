package cn.nukkit.level.biome.v2.layer.type;

import cn.nukkit.level.biome.v2.layer.Layer;
import cn.nukkit.level.biome.v2.layer.LayerHelper;

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
        int newW = pW << 1;
        int newH = pH << 1;
        int idx, v00, v01, v10, v11;
        int[] values = new int[(newW + 1) * (newH + 1)];
        final int st = this.getStartSalt();
        final int ss = this.getStartSeed();
        for(int j = 0; j < pH; j++) {
            idx = (j << 1) * newW;
            v00 = values[(j + 0) * pW];
            v01 = values[(j + 1) * pW];
            for(int i = 0; i < pW; i++, v00 = v10, v01 = v11) {
                v10 = out.get(i + 1 + (j + 0) * pW);
                v11 = out.get(i + 1 + (j + 1) * pW);
                if(v00 == v01 && v00 == v10 && v00 == v11) {
                    values[idx] = v00;
                    values[idx + 1] = v00;
                    values[idx + newW] = v00;
                    values[idx + newW + 1] = v00;
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
                values[idx] = v00;
                values[idx + newW] = (cs >> 24 & 1) != 0 ? v01 : v00;
                idx++;
                cs *= cs * 1284865837 + (int)4150755663L;
                cs += st;
                values[idx] = (cs >> 24 & 1) != 0 ? v10 : v00;
                values[idx + newW] = 10;
                idx++;
            }
        }
        for(int j = 0; j < h; j++) {
            nnc(nnc(out).shift(j * w)).copyFrom(buf.shift((j + (z & 1)) * newW + (x & 1)), w);
        }
        return values;
    }
    
    private static int select4(int cs, int st, int v00, int v01, int v10, int v11) {
        int v;
        int cv00 = (v00 == v10 ? 1 : 0) + (v00 == v01 ? 1 : 0) + (v00 == v11 ? 1 : 0);
        int cv10 = (v10 == v01 ? 1 : 0) + (v10 == v11 ? 1 : 0);
        int cv01 = v01 == v11 ? 1 : 0;
        if(cv00 > cv10 && cv00 > cv01) {
            v = v00;
        } else if(cv10 > cv00) {
            v = v10;
        } else if(cv01 > cv00) {
            v = v01;
        } else {
            cs *= cs * 1284865837 + (int)4150755663L;
            cs += st;
            int r = cs >> 24 & 3;
            v = r == 0 ? v00 : r == 1 ? v10 : r == 2 ? v01 : v11;
        }
        return v;
    }
}
