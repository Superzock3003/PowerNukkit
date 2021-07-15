package cn.nukkit.item;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockPowderSnow;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;

public class ItemBucketPowderSnow extends StringItem {

    public ItemBucketPowderSnow() {
        super(MinecraftItemID.POWDER_SNOW_BUCKET.getNamespacedId(), "Powder Snow Bucket");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        if(player.isAdventure()) {
            return false;
        }

        if(!target.canBeReplaced()) {
            target = target.getSide(face);
            if(target.getId() != BlockID.AIR && !target.canBeReplaced()) {
                return false;
            }
        }

        Block targetBlock = new BlockPowderSnow();
        if(target.canBeReplaced()) {
            level.setBlock(target, targetBlock);
            if(!player.isCreative()) {
                player.getInventory().setItemInHand(Item.get(Item.BUCKET));
            }
            return true;
        }
        return false;
    }
}
