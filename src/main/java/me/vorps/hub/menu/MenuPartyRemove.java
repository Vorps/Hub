package me.vorps.hub.menu;

import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Project Hub Created by Vorps on 03/03/2016 at 18:26.
 */
public class MenuPartyRemove extends Menu {

    public MenuPartyRemove(Player player, int page){
        super(new byte[] {14}, null, new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}});
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        playerData.updateParty();
        Inventory menuParty;
        if(playerData.getParty().getMembers().size() <= 7){
            menuParty = Bukkit.createInventory(null, 27, "§6Supprimer un membre");
        } else {
            menuParty = Bukkit.createInventory(null, 27, "§6Del un membre|page : §4"+page);
        }
        menuParty.setItem(4, new Item(Material.BARRIER).withName("§6Supprimer un membre").get());
        String[] des = {"Remove Membre"};
        if(playerData.getParty().getMembers().size() <= 7){
            list(10, playerData.getParty().getMembers().size()-(page-1)*7, (page-1)*7, 0, playerData.getParty().getMembers(), des);
        } else {
            for(int i = 0; i < 7; i++){
                menuParty.setItem(10+i, new Item(playerData.getParty().getMembers().get((page-1)*7)+i).withName("§6"+ playerData.getParty().getMembers().get((page-1)*7)+i).withLore(des).get());
            }
        }
        if(page > 1){
            menuParty.setItem(18, new Item(Material.PAPER).withName("§6<- page "+page--).withLore(new String[] {"Party remove back", ""+page--}).get());
        } else {
            menuParty.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu party"}).get());
        }
        if(playerData.getParty().getMembers().size() <= page*7){
            menuParty.setItem(26, new Item(160).withData((byte) 14).withName(" ").get());
        } else {
            menuParty.setItem(26, new Item(Material.PAPER).withName("§6Page "+page+1+" ->").withLore(new String[] {"Party remove", ""+page+1}).get());
        }
        player.openInventory(menuParty);
    }
}
