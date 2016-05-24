package me.vorps.hub.menu;

import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import me.vorps.hub.utils.Purchase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuChestplate extends MenuRecursive {

    private MenuChestplate(Player player, ArrayList<Item> list) {
        super(new byte[] {14, 5}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > "), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {36, 1}, {44, 1}, {46, 1}, {47, 0}, {48, 1}, {49, 0}, {50, 1}, {51, 0}, {52, 1}, {53, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9, new int[]{12, 13, 14, 19, 25});
		initMenu(player, 1);
        player.openInventory(menu);
	}

    @Override
    public void initMenu(Player player, int page){
        menu.clear();
        menu.setItem(4, new Item(Material.GOLD_CHESTPLATE).withName("§6Plastrons").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mon plastron").withLore(new String[]{"§7Retire votre plastron actuel"}).get());
        menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        Products.getProduct(2).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getPlayerData(player.getName()).getLang()).withLore(new Purchase(player.getName(), "ce plastron sur votre torse").purchase(product.getName()))));
        new MenuChestplate(player, list);
    }
    
    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                new MenuCostume(player);
                break;
            case BARRIER:
                if(player.getInventory().getChestplate() != null){
                    player.getInventory().setChestplate(new ItemStack(Material.AIR));
                    player.sendMessage("§ePlastron retiré");
                    MenuChestplate.createMenu(player);
                }
                break;
            case PAPER:
                initMenu(player, page+1);
                break;
            case EMPTY_MAP:
                initMenu(player, page-1);
                break;
            default:
                break;
        }
    }
}
