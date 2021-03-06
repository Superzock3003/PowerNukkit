package cn.nukkit.block;

import cn.nukkit.api.PowerNukkitDifference;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.Faceable;

/**
 * @author CreeperFace
 */
public class BlockPistonHead extends BlockTransparentMeta implements Faceable {

    public BlockPistonHead() {
        this(0);
    }

    public BlockPistonHead(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return PISTON_HEAD;
    }

    @Override
    public String getName() {
        return "Piston Head";
    }

    @Override
    public double getResistance() {
        return 2.5;
    }

    @Override
    public double getHardness() {
        return 0.5;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[] {Item.get(BlockID.PISTON)};
    }

    @Override
    @PowerNukkitDifference(info = "Remove BlockEntity of piston on break.", since = "1.4.0.0-PN")
    public boolean onBreak(Item item) {
        this.level.setBlock(this, Block.get(BlockID.AIR), true, true);
        Block side = getSide(getBlockFace().getOpposite());

        if (side instanceof BlockPistonBase && ((BlockPistonBase) side).getBlockFace() == this.getBlockFace()) {
            BlockPistonBase piston = (BlockPistonBase) side;
            piston.onBreak(item);

            if(piston.getBlockEntity() != null) piston.getBlockEntity().close();
        }
        return true;
    }

    @Override
    public BlockFace getBlockFace() {
        BlockFace face = BlockFace.fromIndex(this.getDamage());

        return face.getHorizontalIndex() >= 0 ? face.getOpposite() : face;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean canBePulled() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Since("1.3.0.0-PN")
    @PowerNukkitOnly
    @Override
    public boolean isSolid(BlockFace side) {
        return false;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(BlockID.AIR));
    }
}
