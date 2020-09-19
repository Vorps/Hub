package me.vorps.hub.menu;


import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
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
public class MenuParty extends MenuRecursive{

	private MenuParty(UUID uuid, ArrayList<ItemBuilder> list){
        super(new byte[] {1}, Bukkit.createInventory(null, 27, "§6Party"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {21, 0}, {23, 0}, {26, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.STATIC, Hub.getInstance());
        initMenu(uuid, 1);
		Bukkit.getPlayer(uuid).openInventory(menu);
	}

    @Override
    public void initMenu(UUID uuid, int page){
        menu.clear();
        /*PlayerData playerData = PlayerData.getPlayerData(uuid);
        menu.setItem(4, new ItemBuilder(Material.MAGMA_CREAM).withName("§6Membres en ligne").get());
        menu.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
        if(playerData.getParty().isState()){
            if(playerData.getParty().getOwner().equals(player.getName())){
                menu.setItem(20, new ItemBuilder(Material.ITEM_FRAME).withName("§6Ajouter un joueur").withLore(new String[] {"§7Ajouter un membre au groupe"}).get());
                menu.setItem(22, new ItemBuilder(Material.PAPER).withName("§6"+ playerData.getParty().getName()).withLore(new String[] {"§7Changer le nom du groupe"}).get());
                menu.setItem(24, new ItemBuilder(Material.BARRIER).withName("§6Supprimer un membre").withLore(new String[] {"§7Supprimer un membre du groupe"}).get());
                menu.setItem(25, new ItemBuilder(Material.DARK_OAK_DOOR).withName("§6Dissoudre le groupe").withLore(new String[] {"§7Quitter le groupe"}).get());
            } else {
                menu.setItem(20, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).withName(" ").get());
                menu.setItem(22, new ItemBuilder(Material.PAPER).withName("§6"+ playerData.getParty().getName()).get());
                menu.setItem(24, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).withName(" ").get());
                menu.setItem(25, new ItemBuilder(Material.ACACIA_DOOR).withName("§6Quitter le groupe").withLore(new String[] {"§7Quitter le groupe"}).get());
            }
        } else {
            menu.setItem(22, new ItemBuilder(Material.ITEM_FRAME).withName("§6Crée une party").withLore(new String[] {"§7Inviter un joueur"}).get());
            menu.setItem(20, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).withName(" ").get());
            menu.setItem(24, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).withName(" ").get());
            menu.setItem(25, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).withName(" ").get());
        }*/
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        PlayerData playerData = PlayerData.getPlayerData(uuid);
        //playerData.updateParty();
        ArrayList<ItemBuilder> list = new ArrayList<>();
        //playerData.getParty().getMembersOnline().forEach((String name) -> list.add(new ItemBuilder(name).withName("§6"+name)));
        new MenuParty(uuid, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        PlayerData playerData = PlayerData.getPlayerData(uuid);
        switch (itemStack.getType()) {
            case ARROW:
                new MenuProfil(uuid);
                break;
            case BARRIER:
                MenuPartyRemove.createMenu(uuid);
                break;
            case DARK_OAK_DOOR:
               // playerData.getParty().leave();
                break;
            case ACACIA_DOOR:
                //playerData.getParty().leave();
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            case ITEM_FRAME:
//                playerData.getParty().createParty();
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
