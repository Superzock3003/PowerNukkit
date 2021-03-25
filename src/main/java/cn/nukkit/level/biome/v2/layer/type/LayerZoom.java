package cn.nukkit.level.biome.v2.layer.type;

import cn.nukkit.level.biome.v2.layer.Layer;
import cn.nukkit.level.biome.v2.layer.LayerHelper;

/**
 * @author GoodLucky777
 */
public class LayerZoom extends Layer {

    @Override
    public int[] generateBiomeValues(int x, int z, int width, int height) {
        return new int[width * height]:
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
