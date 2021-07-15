package cn.nukkit.entity.mob;

import cn.nukkit.Player;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.item.Item;
import cn.nukkit.api.Since;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

import javax.annotation.Nonnull;

/**
 * @author PikyCZ
 */
public class EntityEnderDragon extends EntityMob {

    public static final int NETWORK_ID = 53;

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    public EntityEnderDragon(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public float getWidth() {
        return 13f;
    }

    @Override
    public float getHeight() {
        return 4f;
    }

    @Override
    public void initEntity() {
        super.initEntity();
        this.setMaxHealth(200);
    }

    @Override
    protected boolean applyNameTag(@Nonnull Player player, @Nonnull Item item) {
        return false;
    }

    @PowerNukkitOnly
    @Deprecated
    @Override
    public boolean applyNameTag(Item item) {
        return false;
    }

    @PowerNukkitOnly
    @Since("1.5.1.0-PN")
    @Override
    public String getOriginalName() {
        return "Ender Dragon";
    }
    
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    @Override
    public boolean isBoss() {
        return true;
    }
}
