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
 * Project Hub Created by Vorps on 03/03/2016 at 18:26.
 */
public class MenuPartyRemove extends MenuRecursive {

    private MenuPartyRemove(UUID uuid, ArrayList<ItemBuilder> list){
        super(uuid, new byte[] {14}, Bukkit.createInventory(null, 27, "§6Supprimer un membre"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.DYNAMIQUE, Hub.getInstance());
    }

    @Override
    public void initMenu(UUID uuid, int page){
        super.setItem(4, new ItemBuilder(Material.BARRIER).withName("§6Supprimer un membre").withLore(new String[] {"§7Supprimer un membre de votre party"}).get());
        super.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu party"}).get());
    }

    public static void createMenu(UUID uuid){
        PlayerData playerData = PlayerData.getPlayerData(uuid);
        ArrayList<ItemBuilder> list = new ArrayList<>();
        /*playerData.updateParty();
        playerData.getParty().getMembers().keySet().forEach((String name) -> list.add(new ItemBuilder(name).withName("§c"+ name).withLore(new String[] {"§7Supprimer ce membre", "§eClic gauche -> §aSupprimer"})));
        */new MenuPartyRemove(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        new MenuProfil(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        switch (type) {
            case SKELETON_SKULL:
                //String member = itemStack.getItemMeta().getDisplayName().substring(2);
                /*if(playerData.getParty().isMember(member)){
                    playerData.getParty().deleteMenber(player, member);
                    player.closeInventory();
                } else {
                    createMenu(player);
                }*/
                break;
            default:
                break;
        }
    }
}
