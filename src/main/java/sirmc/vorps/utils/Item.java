package sirmc.vorps.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.*;

/**
 * Created by Vorps on 02/01/2016.
 */
public class Item {
    private ItemStack item;
    private String name;
    private Material material;
    private int amount;
    private boolean hideEnchant;
    private byte data;
    private int id;
    private List<String> lore = new ArrayList<String>();
    private Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
    private String skullOwnerName = null;
    private Color color;
    private PotionType potionType = null;

    {
        this.amount = 1;
        this.data = (byte) 0;
    }
    /**
     * @param material Material
     */
    public Item(Material material) {
        this.material = material;
    }

    /**
     *
     * @param potionType
     */
    public Item(PotionType potionType){
        this.potionType = potionType;
    }
    /**
     * @param id
     */
    public Item(int id) {
        this.id = id;
    }

    /**
     * Skull Player
     * @param namePlayer
     */
    public Item(String namePlayer) {
        skullOwnerName = namePlayer;
    }

    /**
     * Convert itemstack to item.
     * @param item ItemStack
     */
    public Item(ItemStack item) {
        this.material = item.getType();
        this.amount = item.getAmount();
        if(item.getDurability() != 0) {
            this.data = (byte) item.getDurability();
        }
        try {
            LeatherArmorMeta lam = (LeatherArmorMeta) item.getItemMeta();
            this.color = lam.getColor();
        } catch (Exception e){}
        try {
            SkullMeta sm = (SkullMeta)item.getItemMeta();
            this.skullOwnerName = sm.getOwner();
        } catch (Exception e){}
        if(item.getItemMeta() != null) {
            ItemMeta meta = item.getItemMeta();
            this.lore = meta.getLore();
            this.name = meta.getDisplayName();
            this.enchantments = meta.getEnchants();
            if(meta.getItemFlags() != null) {
                if(meta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS)) {
                    this.hideEnchant = true;
                }
            }
        }
    }
    public Item withColor(Color color){
        this.color = color;
        return this;
    }
    /**
     * @param name String
     * @return Item
     */
    public Item withName(String name) {
        this.name = name;
        return this;
    }
    /**
     * @param amount int
     * @return Item
     */
    public Item withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * @param hideEnchantement boolean
     * @return Item
     */
    public Item hideEnchant(boolean hideEnchantement) {
        this.hideEnchant = hideEnchantement;
        return this;
    }

    /**
     * @param data byte
     * @return Item
     */
    public Item withData(byte data) {
        this.data = data;
        return this;
    }

    /**
     * @param lore String[]
     * @return Item
     */
    public Item withLore(String[] lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    /**
     * @param enchant Enchantment
     * @param level int
     * @return Item
     */
    public Item withEnchant(Enchantment enchant, int level) {
        this.enchantments.put(enchant, level);
        return this;
    }

    /**
     * Return the item.
     * @return ItemStack
     */
    public ItemStack get() {
        if(color != null){
            item = new ItemStack(this.material, 1);
            LeatherArmorMeta lam = (LeatherArmorMeta) item.getItemMeta();
            lam.setColor(this.color);
            item.setItemMeta(lam);
            if(this.name != null) {
                lam.setDisplayName(this.name);
            }
            if(this.enchantments.size() > 0)
                for(Map.Entry<Enchantment, Integer> enchant : this.enchantments.entrySet())
                    lam.addEnchant(enchant.getKey(), enchant.getValue(), true);
            if(this.hideEnchant)
                lam.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            lam.setLore(this.lore);
            item.setItemMeta(lam);
        } else {
            if(skullOwnerName != null){
                item = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

                SkullMeta sm = (SkullMeta)item.getItemMeta();
                sm.setOwner(skullOwnerName);
                if(this.name != null) {
                    sm.setDisplayName(this.name);
                }
                if(this.enchantments.size() > 0)
                    for(Map.Entry<Enchantment, Integer> enchant : this.enchantments.entrySet())
                        sm.addEnchant(enchant.getKey(), enchant.getValue(), true);
                if(this.hideEnchant)
                    sm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                sm.setLore(this.lore);
                item.setItemMeta(sm);
            } else {
                if(id > 0){
                    item = new ItemStack(this.id, this.amount);
                } else {
                    if(potionType != null){
                        Potion potion = new Potion(potionType);
                        item = potion.toItemStack(1);
                    } else {
                        item = new ItemStack(this.material, this.amount);
                    }
                }
                item.setDurability((short) this.data);
                ItemMeta meta = item.getItemMeta();
                meta.setLore(this.lore);
                if(this.name != null) {
                    meta.setDisplayName(this.name);
                }
                if(this.enchantments.size() > 0)
                    for(Map.Entry<Enchantment, Integer> enchant : this.enchantments.entrySet())
                        meta.addEnchant(enchant.getKey(), enchant.getValue(), true);
                if(this.hideEnchant)
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
            }
        }

        return item;
    }
}