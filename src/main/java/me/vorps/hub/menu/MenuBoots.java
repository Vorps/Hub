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
public class MenuBoots extends MenuRecursive {
    private MenuBoots(Player player, ArrayList<Item> list){
        super(new byte[] {1, 11, 5}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > "), new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 0}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 2}, {35, 2}, {36, 1}, {44, 1}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}, {52, 1}, {53, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9, new int[]{12, 13, 14, 19, 25});
		initMenu(player, 1);
        player.openInventory(menu);
	}

    @Override
    public void initMenu(Player player, int page){
        menu.clear();
        menu.setItem(4,new Item(Material.GOLD_BOOTS).withName("§6Bottes").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mes bottes").withLore(new String[] {"§7Retire vos bottes actuel"}).get());
        menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        Products.getProduct(4).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getPlayerData(player.getName()).getLang()).withLore(new Purchase(player.getName(), "ces bottes a vos pieds").purchase(product.getName()))));
        new MenuBoots(player, list);
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
                if(player.getInventory().getBoots() != null){
                    player.getInventory().setBoots(new ItemStack(Material.AIR));
                    player.sendMessage("§eBottes retiré");
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
