package cn.nukkit.item.randomitem;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.math.NukkitRandom;

import java.util.Random;

public class ConstantItemWithRandomEnchantmentSelector extends ConstantItemSelector {

    private Enchantment[] enchantments;

    private int maxEnchantments;

    private boolean maxEnchantLevel;

    private boolean randomDurability;

    private NukkitRandom random;

    public ConstantItemWithRandomEnchantmentSelector(int id, Enchantment[] enchantments, int maxEnchantments, boolean maxEnchantLevel, Selector parent) {
        this(id, 0, enchantments, maxEnchantments, maxEnchantLevel, parent);
    }

    public ConstantItemWithRandomEnchantmentSelector(int id, Integer meta, Enchantment[] enchantments, int maxEnchantments, boolean maxEnchantLevel, Selector parent) {
        this(id, meta, 1, enchantments, maxEnchantments, maxEnchantLevel, parent);
    }

    public ConstantItemWithRandomEnchantmentSelector(int id, Integer meta, int count, Enchantment[] enchantments, int maxEnchantments, boolean maxEnchantLevel, Selector parent) {
        this(Item.get(id, meta, count), enchantments, maxEnchantments, maxEnchantLevel, parent);
    }

    public ConstantItemWithRandomEnchantmentSelector(Item item, Enchantment[] enchantments, int maxEnchantments, boolean maxEnchantLevel, Selector parent) {
        this(item, enchantments, maxEnchantments, maxEnchantLevel, false, parent);
    }

    public ConstantItemWithRandomEnchantmentSelector(int id, Enchantment[] enchantments, int maxEnchantments, boolean maxEnchantLevel, boolean randomDurability, Selector parent) {
        this(id, 0, enchantments, maxEnchantments, maxEnchantLevel, randomDurability, parent);
    }

    public ConstantItemWithRandomEnchantmentSelector(int id, Integer meta, Enchantment[] enchantments, int maxEnchantments, boolean maxEnchantLevel, boolean randomDurability, Selector parent) {
        this(id, meta, 1, enchantments, maxEnchantments, maxEnchantLevel, randomDurability, parent);
    }

    public ConstantItemWithRandomEnchantmentSelector(int id, Integer meta, int count, Enchantment[] enchantments, int maxEnchantments, boolean maxEnchantLevel, boolean randomDurability, Selector parent) {
        this(Item.get(id, meta, count), enchantments, maxEnchantments, maxEnchantLevel, randomDurability, parent);
    }

    public ConstantItemWithRandomEnchantmentSelector(Item item, Enchantment[] enchantments, int maxEnchantments, boolean maxEnchantLevel, boolean randomDurability, Selector parent) {
        super(item, parent);
        this.enchantments = enchantments;
        this.maxEnchantments = maxEnchantments;
        this.maxEnchantLevel = maxEnchantLevel;
        this.random = new NukkitRandom();
        this.randomDurability = randomDurability;
    }

    @Override
    public Object select() {
        if(maxEnchantments < 1) return item;
        if(enchantments.length < 1) return item;
        Item item = getItem().clone();
        if(randomDurability && item.getMaxDurability() >= 1) {
            item.setDamage(random.nextBoundedInt(item.getMaxDurability()));
        }
        if(item != null) {
            Enchantment[] enchantments = this.enchantments.clone();
            int amountEnchantments = random.nextBoundedInt(maxEnchantments) + 1;
            for(int i = 0; i < amountEnchantments; i++) {
                if(enchantments.length < 1) break;
                int index = random.nextBoundedInt(enchantments.length);
                Enchantment enchantment = enchantments[index];
                item.addEnchantment(enchantment.setLevel(maxEnchantLevel ? enchantment.getMaxLevel() : random.nextBoundedInt(enchantment.getMaxLevel()) + 1));
                Enchantment[] newEnchantments = new Enchantment[enchantments.length - 1];
                for(int j = 0; j < newEnchantments.length; j++) {
                    newEnchantments[j] = enchantments[j >= index ? j + 1 : j];
                }
                enchantments = newEnchantments;
            }
        }
        return item;
    }
}
