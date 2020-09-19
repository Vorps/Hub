package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuJump extends MenuRecursive{

	private MenuJump(UUID uuid, ArrayList<ItemBuilder> list){
        super(new byte[] {6},  Bukkit.createInventory(null, 27, "§6Jumps"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.STATIC, Hub.getInstance());
		initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
	}

    @Override
    public void initMenu(UUID uuid, int page){
        menu.setItem(4, new ItemBuilder(Material.LEATHER_BOOTS).withName("§6Jump").withColor(Color.PURPLE).get());
        menu.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        if(Jumps.nbrJump() == 1){
            Jumps.getListJumps().forEach((Jumps jump) -> MenuJumpDifficulty.createMenu(uuid, jump));
        } else {
            Jumps.getListJumps().forEach((Jumps jump) -> {
                list.add(jump.getIcon().get(PlayerData.getLang(uuid)));
            });
            new MenuJump(uuid, list);
        }
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                MenuPrincipal.createMenu(uuid);
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
        Jumps jumps = Jumps.getJump(itemStack.getItemMeta().getDisplayName(), PlayerData.getLang(uuid));
        if(jumps != null){
            MenuJumpDifficulty.createMenu(uuid, jumps);
        }
    }
}
