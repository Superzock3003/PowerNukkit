package cn.nukkit.entity.mob;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

import javax.annotation.Nonnull;

public class EntityGoat extends EntityMob {

    public static final int NETWORK_ID = 128;

    public EntityGoat(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Nonnull
    @Override
    public String getName() {
        return "Goat";
    }

    @Override
    public boolean playerApplyNameTag(@Nonnull Player player, @Nonnull Item item, boolean consume) {
        return super.playerApplyNameTag(player, item, consume);
    }
}
