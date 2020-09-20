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
        super(uuid, new byte[] {3, 14, 1}, Bukkit.createInventory(null, 45, "§6Boutique > Costumes"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 2}, {5, 2}, {6, 0}, {7, 1}, {8, 0}, {9, 2}, {17, 2}, {18, 1}, {26, 1}, {27, 2}, {35, 2}, {37, 2}, {38, 0}, {39, 2}, {40, 1}, {41, 2}, {42, 0}, {43, 2}, {44, 0}}, Hub.getInstance());
        super.setItem(4, new ItemBuilder(Material.DIAMOND_CHESTPLATE).withName("§6Costumes").get());
        super.setItem(19, new ItemBuilder(Material.LEATHER_HELMET).withAction(e -> MenuHelmetHat.createMenu(uuid)).withName("§6Chapeau").withLore(new String[] {"§7Chapeaux"}).get());
        super.setItem(21, new ItemBuilder(Material.LEATHER_CHESTPLATE).withAction(e -> MenuChestplate.createMenu(uuid)).withName("§6Plastron").withLore(new String[] {"§7Plastron"}).get());
        super.setItem(13, new ItemBuilder(Material.BARRIER).withName("§6Retire votre costume").withLore(new String[] {"§7Retire votre costume"}).get());
        super.setItem(23, new ItemBuilder(Material.LEATHER_LEGGINGS).withAction(e -> MenuLeggings.createMenu(uuid)).withName("§6Pantalon").withLore(new String[] {"§7Pantalon"}).get());
        super.setItem(25, new ItemBuilder(Material.LEATHER_BOOTS).withAction(e -> MenuBoots.createMenu(uuid)).withName("§6Bottes").withLore(new String[] {"§7Bottes"}).get());
        super.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
	}

    @Override
    protected void back(UUID uuid) {
        MenuBoutique.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        switch (type) {
            case BARRIER:
                if(Bukkit.getPlayer(uuid).getInventory().getHelmet() != null) Bukkit.getPlayer(uuid).getInventory().setHelmet(new ItemStack(Material.AIR));
                if(Bukkit.getPlayer(uuid).getInventory().getChestplate() != null) Bukkit.getPlayer(uuid).getInventory().setChestplate(new ItemStack(Material.AIR));
                if(Bukkit.getPlayer(uuid).getInventory().getLeggings() != null) Bukkit.getPlayer(uuid).getInventory().setLeggings(new ItemStack(Material.AIR));
                if(Bukkit.getPlayer(uuid).getInventory().getBoots() != null) Bukkit.getPlayer(uuid).getInventory().setBoots(new ItemStack(Material.AIR));
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
