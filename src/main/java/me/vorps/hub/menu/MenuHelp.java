package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.Menu;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.BookHelp;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import me.vorps.hub.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuHelp extends MenuRecursive {

	private MenuHelp(Player player, ArrayList<Item> list){
        super(new byte[] {12, 15}, Bukkit.createInventory(null, 27, "§6Aide"), new int[][] {{4, 0}, {13, 1}, {19, 1}, {20, 0}, {21, 1}, {22, 0}, {23, 1}, {24, 0}, {25, 1}, {26, 0}}, list , PlayerData.getPlayerData(player.getName()).getLang(), 9, 0, new int[] {4, 9, 10, 11, 12, 13, 14, 15, 16, 17});
        initMenu(player, 1);
        player.openInventory(menu);
	}


    @Override
    public void initMenu(Player player, int page){
        int index = (page-1)*9;
        for(int i = 0; i < list.size() && i < 9; i++){
            if(i >= 4){
                menu.setItem(i+10, BookHelp.getBookHelp(list.get(index++).get().getItemMeta().getDisplayName()).getBook(PlayerData.getPlayerData(player.getName()).getLang()));
            } else {
                menu.setItem(i+9, BookHelp.getBookHelp(list.get(index++).get().getItemMeta().getDisplayName()).getBook(PlayerData.getPlayerData(player.getName()).getLang()));
            }
        }
        menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        getPage(page, Menu.Type.STATIC);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        BookHelp.getTrieBookHelp().values().forEach((BookHelp bookHelp) ->  {
            list.add(bookHelp.getItem().get(PlayerData.getPlayerData(player.getName()).getLang()));
            System.out.println(bookHelp.getLevel());
        });
        new MenuHelp(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuPrincipal.createMenu(player);
                break;
            case WRITTEN_BOOK:
                player.getInventory().setItem(4, itemStack);
                player.closeInventory();
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
