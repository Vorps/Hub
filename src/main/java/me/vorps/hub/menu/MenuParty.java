package me.vorps.hub.menu;


import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuParty extends MenuRecursive{

	private MenuParty(Player player, ArrayList<Item> list){
        super(new byte[] {1}, Bukkit.createInventory(null, 27, "§6Party"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {21, 0}, {23, 0}, {26, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9);
        initMenu(player, 1);
		player.openInventory(menu);
	}

    @Override
    public void initMenu(Player player, int page){
        menu.clear();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        menu.setItem(4, new Item(Material.MAGMA_CREAM).withName("§6Membres en ligne").get());
        menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
        if(playerData.getParty().isState()){
            if(playerData.getParty().getOwner().equals(player.getName())){
                menu.setItem(20, new Item(Material.ITEM_FRAME).withName("§6Ajouter un joueur").withLore(new String[] {"§7Ajouter un membre au groupe"}).get());
                menu.setItem(22, new Item(Material.PAPER).withName("§6"+ playerData.getParty().getName()).withLore(new String[] {"§7Changer le nom du groupe"}).get());
                menu.setItem(24, new Item(Material.BARRIER).withName("§6Supprimer un membre").withLore(new String[] {"§7Supprimer un membre du groupe"}).get());
                menu.setItem(25, new Item(Material.DARK_OAK_DOOR_ITEM).withName("§6Dissoudre le groupe").withLore(new String[] {"§7Quitter le groupe"}).get());
            } else {
                menu.setItem(20, new Item(160).withData((byte) 1).withName(" ").get());
                menu.setItem(22, new Item(Material.PAPER).withName("§6"+ playerData.getParty().getName()).get());
                menu.setItem(24, new Item(160).withData((byte) 1).withName(" ").get());
                menu.setItem(25, new Item(Material.ACACIA_DOOR_ITEM).withName("§6Quitter le groupe").withLore(new String[] {"§7Quitter le groupe"}).get());
            }
        } else {
            menu.setItem(22, new Item(Material.ITEM_FRAME).withName("§6Crée une party").withLore(new String[] {"§7Inviter un joueur"}).get());
            menu.setItem(20, new Item(160).withData((byte) 1).withName(" ").get());
            menu.setItem(24, new Item(160).withData((byte) 1).withName(" ").get());
            menu.setItem(25, new Item(160).withData((byte) 1).withName(" ").get());
        }
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        playerData.updateParty();
        ArrayList<Item> list = new ArrayList<>();
        playerData.getParty().getMembersOnline().forEach((String name) -> list.add(new Item(name).withName("§6"+name)));
        new MenuParty(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        switch (itemStack.getType()) {
            case ARROW:
                new MenuProfil(player);
                break;
            case BARRIER:
                MenuPartyRemove.createMenu(player);
                break;
            case DARK_OAK_DOOR_ITEM:
                playerData.getParty().disoudreParty();
                break;
            case ACACIA_DOOR_ITEM:
                playerData.getParty().quitParty();
                player.closeInventory();
                break;
            case ITEM_FRAME:
                playerData.getParty().createParty();
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
