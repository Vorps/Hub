package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuCostume extends Menu{

	public MenuCostume(UUID uuid){
        super(new byte[] {3, 14, 1}, Bukkit.createInventory(null, 45, "§6Boutique > Costumes"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 2}, {5, 2}, {6, 0}, {7, 1}, {8, 0}, {9, 2}, {17, 2}, {18, 1}, {26, 1}, {27, 2}, {35, 2}, {37, 2}, {38, 0}, {39, 2}, {40, 1}, {41, 2}, {42, 0}, {43, 2}, {44, 0}}, Hub.getInstance());
        this.menu.setItem(4, new ItemBuilder(Material.DIAMOND_CHESTPLATE).withName("§6Costumes").get());
        this.menu.setItem(19, new ItemBuilder(Material.LEATHER_HELMET).withName("§6Chapeau").withLore(new String[] {"§7Chapeaux"}).get());
        this.menu.setItem(21, new ItemBuilder(Material.LEATHER_CHESTPLATE).withName("§6Plastron").withLore(new String[] {"§7Plastron"}).get());
        this.menu.setItem(13, new ItemBuilder(Material.BARRIER).withName("§6Retire votre costume").withLore(new String[] {"§7Retire votre costume"}).get());
        this.menu.setItem(23, new ItemBuilder(Material.LEATHER_LEGGINGS).withName("§6Pantalon").withLore(new String[] {"§7Pantalon"}).get());
        this.menu.setItem(25, new ItemBuilder(Material.LEATHER_BOOTS).withName("§6Bottes").withLore(new String[] {"§7Bottes"}).get());
        this.menu.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        Bukkit.getPlayer(uuid).openInventory(this.menu);
	}


    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                MenuBoutique.createMenu(uuid);
                break;
            case LEATHER_HELMET:
                MenuHelmetHat.createMenu(uuid);
                break;
            case LEATHER_CHESTPLATE:
                MenuChestplate.createMenu(uuid);
                break;
            case LEATHER_LEGGINGS:
                MenuLeggings.createMenu(uuid);
                break;
            case LEATHER_BOOTS:
                MenuBoots.createMenu(uuid);
                break;
            case BARRIER:
                if(Bukkit.getPlayer(uuid).getInventory().getHelmet() != null){
                    Bukkit.getPlayer(uuid).getInventory().setHelmet(new ItemStack(Material.AIR));
                }
                if(Bukkit.getPlayer(uuid).getInventory().getChestplate() != null){
                    Bukkit.getPlayer(uuid).getInventory().setChestplate(new ItemStack(Material.AIR));
                }
                if(Bukkit.getPlayer(uuid).getInventory().getLeggings() != null){
                    Bukkit.getPlayer(uuid).getInventory().setLeggings(new ItemStack(Material.AIR));
                }
                if(Bukkit.getPlayer(uuid).getInventory().getBoots() != null){
                    Bukkit.getPlayer(uuid).getInventory().setBoots(new ItemStack(Material.AIR));
                }
                if(Bukkit.getPlayer(uuid).getInventory().getHelmet() != null
                        ||Bukkit.getPlayer(uuid).getInventory().getChestplate() != null
                        ||Bukkit.getPlayer(uuid).getInventory().getLeggings() != null
                        ||Bukkit.getPlayer(uuid).getInventory().getBoots() != null){
                    Bukkit.getPlayer(uuid).sendMessage("§eCostume retiré !!!");
                }
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            default:
                break;
        }
    }
}
