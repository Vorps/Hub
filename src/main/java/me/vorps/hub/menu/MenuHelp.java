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
        super(uuid, new byte[] {12, 15}, Bukkit.createInventory(null, 27, "§6Aide"), new int[][] {{4, 0}, {13, 1}, {19, 1}, {20, 0}, {21, 1}, {22, 0}, {23, 1}, {24, 0}, {25, 1}, {26, 0}}, list , PlayerData.getLang(uuid), 9, 0, new int[] {4, 9, 10, 11, 12, 13, 14, 15, 16, 17}, Type.DYNAMIQUE, Hub.getInstance());
	}

    @Override
    public void initMenu(UUID uuid, int page){
        int index = (page-1)*9;
        for(int i = 0; i < this.list.size() && i < 9; i++){
            if(i >= 4){
                super.setItem(i+10, BookHelp.getBookHelp(this.list.get(index++).getUuid()).getBook(PlayerData.getLang(uuid)));
            } else {
                super.setItem(i+9, BookHelp.getBookHelp(this.list.get(index++).getUuid()).getBook(PlayerData.getLang(uuid)));
            }
        }
        super.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        BookHelp.getBookHelps().stream().map(BookHelp::getBookHelp).forEach((bookHelp)-> list.add(bookHelp.getIcon(PlayerData.getLang(uuid))));
        new MenuHelp(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        MenuPrincipal.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        switch (type) {
            case WRITTEN_BOOK:
                Bukkit.getPlayer(uuid).getInventory().setItem(4, e.getCurrentItem());
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            default:
                break;
        }
    }
}
