package cn.nukkit.entity.mob;

import cn.nukkit.entity.passive.EntitySquid;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class EntityGlowSquid extends EntitySquid {

    public static final int NETWORK_ID = 129;

    public EntityGlowSquid(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public String getName() {
        return "Glow Squid";
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
