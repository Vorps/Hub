package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import net.vorps.api.objects.BookHelp;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuHelp extends MenuRecursive {

	private MenuHelp(UUID uuid, ArrayList<ItemBuilder> list){
        super(new byte[] {12, 15}, Bukkit.createInventory(null, 27, "§6Aide"), new int[][] {{4, 0}, {13, 1}, {19, 1}, {20, 0}, {21, 1}, {22, 0}, {23, 1}, {24, 0}, {25, 1}, {26, 0}}, list , PlayerData.getLang(uuid), 9, 0, new int[] {4, 9, 10, 11, 12, 13, 14, 15, 16, 17}, Type.STATIC, Hub.getInstance());
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
	}


    @Override
    public void initMenu(UUID uuid, int page){
        int index = (page-1)*9;
        for(int i = 0; i < list.size() && i < 9; i++){
            if(i >= 4){
                menu.setItem(i+10, BookHelp.getBookHelp(list.get(index++).get().getItemMeta().getDisplayName()).getBook(PlayerData.getLang(uuid)));
            } else {
                menu.setItem(i+9, BookHelp.getBookHelp(list.get(index++).get().getItemMeta().getDisplayName()).getBook(PlayerData.getLang(uuid)));
            }
        }
        menu.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        BookHelp.getTrieBookHelp().values().forEach((BookHelp bookHelp) ->  {
            list.add(bookHelp.getItem().get(PlayerData.getLang(uuid)));
            System.out.println(bookHelp.getLevel());
        });
        new MenuHelp(uuid, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                MenuPrincipal.createMenu(uuid);
                break;
            case WRITTEN_BOOK:
                Bukkit.getPlayer(uuid).getInventory().setItem(4, itemStack);
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            case PAPER:
                initMenu(uuid, page+1);
                break;
            case MAP:
                initMenu(uuid, page-1);
                break;
            default:
                break;
        }
    }
}
