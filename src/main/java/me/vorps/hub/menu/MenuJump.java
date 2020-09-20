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
        super(uuid, new byte[] {6},  Bukkit.createInventory(null, 27, "§6Jumps"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.DYNAMIQUE, Hub.getInstance());
	}

    @Override
    public void initMenu(UUID uuid, int page){
        super.setItem(4, new ItemBuilder(Material.LEATHER_BOOTS).withName("§6Jump").withColor(Color.PURPLE).get());
        super.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        if(Jumps.nbrJump() == 1){
            Jumps.getListJumps().forEach((Jumps jump) -> MenuJumpDifficulty.createMenu(uuid, jump));
        } else {
            Jumps.getListJumps().forEach((Jumps jump) -> {
                list.add(jump.getIcon(PlayerData.getLang(uuid)));
            });
            new MenuJump(uuid, list);
        }
    }

    @Override
    protected void back(UUID uuid) {
        MenuPrincipal.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {

        /*Jumps jumps = Jumps.getJump(itemStack.getItemMeta().getDisplayName(), PlayerData.getLang(uuid));
        if(jumps != null){
            MenuJumpDifficulty.createMenu(uuid, jumps);
        }*/
    }
}
