package cn.nukkit.level.generator.populator.impl;

import cn.nukkit.block.BlockID;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.generator.object.ore.DeepslateOreType;
import cn.nukkit.level.generator.populator.type.Populator;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.math.NukkitRandom;

public class PopulatorOreDeepslate extends Populator {

    private DeepslateOreType[] oreTypes;

    public PopulatorOreDeepslate(DeepslateOreType[] oreTypes) {
        this.oreTypes = oreTypes;
    }

    @Override
    public void populate(ChunkManager level, int chunkX, int chunkZ, NukkitRandom random, FullChunk chunk) {
        int sx = chunkX << 4;
        int ex = sx + 15;
        int sz = chunkZ << 4;
        int ez = sz + 15;
        for (DeepslateOreType type : this.oreTypes) {
            for (int i = 0; i < type.clusterCount; i++) {
                int x = NukkitMath.randomRange(random, sx, ex);
                int z = NukkitMath.randomRange(random, sz, ez);
                int y = NukkitMath.randomRange(random, type.minHeight, type.maxHeight);
                int id = level.getBlockIdAt(x, y, z);
                if (id != BlockID.STONE && id != BlockID.DEEPSLATE && id != BlockID.TUFF) {
                    continue;
                }
                type.spawn(level, random, BlockID.STONE, x, y, z);
            }
        }
    }
}
