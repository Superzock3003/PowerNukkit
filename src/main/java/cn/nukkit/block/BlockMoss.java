package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.generator.object.ObjectTallGrass;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.math.NukkitRandom;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockMoss extends BlockSolid {

    @Override
    public String getName() {
        return "Moss Block";
    }

    @Override
    public int getId() {
        return MOSS;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_HOE;
    }

    @Override
    public double getHardness() {
        return 0.1;
    }

    @Override
    public double getResistance() {
        return 0.1;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(@Nonnull Item item) {
        return this.onActivate(item, null);
    }

    @Override
    public boolean onActivate(@Nonnull Item item, @Nullable Player player) {
        if(item.isFertilizer()) {
            if(player != null && (player.gamemode & 0x01) == 0) {
                item.count--;
            }
            this.level.addParticle(new BoneMealParticle(this));
            ObjectTallGrass.growGrass(this.getLevel(), this, new NukkitRandom());
            return true;
        }
        return false;
    }
}
