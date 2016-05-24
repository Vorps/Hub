package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuCostume extends Menu{

	public MenuCostume(Player player){
        super(new byte[] {3, 14, 1}, Bukkit.createInventory(null, 45, "§6Boutique > Costumes"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 2}, {5, 2}, {6, 0}, {7, 1}, {8, 0}, {9, 2}, {17, 2}, {18, 1}, {26, 1}, {27, 2}, {35, 2}, {37, 2}, {38, 0}, {39, 2}, {40, 1}, {41, 2}, {42, 0}, {43, 2}, {44, 0}});
        menu.setItem(4, new Item(Material.DIAMOND_CHESTPLATE).withName("§6Costumes").get());
        menu.setItem(19, new Item(Material.LEATHER_HELMET).withName("§6Chapeau").withLore(new String[] {"§7Chapeaux"}).get());
        menu.setItem(21, new Item(Material.LEATHER_CHESTPLATE).withName("§6Plastron").withLore(new String[] {"§7Plastron"}).get());
        menu.setItem(13, new Item(Material.BARRIER).withName("§6Retire votre costume").withLore(new String[] {"§7Retire votre costume"}).get());
        menu.setItem(23, new Item(Material.LEATHER_LEGGINGS).withName("§6Pantalon").withLore(new String[] {"§7Pantalon"}).get());
        menu.setItem(25, new Item(Material.LEATHER_BOOTS).withName("§6Bottes").withLore(new String[] {"§7Bottes"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
		player.openInventory(menu);
	}


    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuBoutique.createMenu(player);
                break;
            case LEATHER_HELMET:
                MenuHelmetHat.createMenu(player);
                break;
            case LEATHER_CHESTPLATE:
                MenuChestplate.createMenu(player);
                break;
            case LEATHER_LEGGINGS:
                MenuLeggings.createMenu(player);
                break;
            case LEATHER_BOOTS:
                MenuBoots.createMenu(player);
                break;
            case BARRIER:
                if(player.getInventory().getHelmet() != null){
                    player.getInventory().setHelmet(new ItemStack(Material.AIR));
                }
                if(player.getInventory().getChestplate() != null){
                    player.getInventory().setChestplate(new ItemStack(Material.AIR));
                }
                if(player.getInventory().getLeggings() != null){
                    player.getInventory().setLeggings(new ItemStack(Material.AIR));
                }
                if(player.getInventory().getBoots() != null){
                    player.getInventory().setBoots(new ItemStack(Material.AIR));
                }
                if(player.getInventory().getHelmet() != null
                        ||player.getInventory().getChestplate() != null
                        ||player.getInventory().getLeggings() != null
                        ||player.getInventory().getBoots() != null){
                    player.sendMessage("§eCostume retiré !!!");
                }
                player.closeInventory();
                break;
            default:
                break;
        }
    }
}
