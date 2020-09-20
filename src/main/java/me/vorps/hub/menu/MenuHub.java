package me.vorps.hub.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.AllArgsConstructor;
import net.vorps.api.databases.Database;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.*;
import me.vorps.hub.Object.PlayerJump;
import net.vorps.api.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuHub extends MenuRecursive{
    @AllArgsConstructor
    public enum Charge {
        LOW(45, Material.LIME_TERRACOTTA, "§aCharge faible"),
        MEDIUM(25, Material.YELLOW_TERRACOTTA, "§eCharge moyenne"),
        HIGH(35, Material.ORANGE_TERRACOTTA, "§6Charge §levée"),
        FULL(45, Material.RED_TERRACOTTA, "§cCharge complet");

        private int charge;
        private Material data;
        private String label;

        public static Charge getCharge(int nbr){
            for(Charge charge : Charge.values()){
                if(nbr <= charge.charge){
                    return charge;
                }
            }
            return Charge.FULL;
        }

    }
	private MenuHub(UUID uuid, ArrayList<ItemBuilder> list){
        super(uuid, new byte[] {10}, Bukkit.createInventory(null, 27, "§6Hub ("+ 0/*DataHub.NB_SERVER*/+")"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.DYNAMIQUE, Hub.getInstance());
	}

    public void initMenu(UUID uuid, int page){
        super.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        String[] lore;
        PlayerJump playerJump = PlayerData.getPlayerData(uuid).getJump();
        /*if(playerJump.isInJump()){
            lore = new String[2];
            lore[1] = playerJump.toString(PlayerData.getLang(uuid));
        } else {
            lore = new String[1];
        }
        lore[0] = "§7Retourner au spawn de "+ Settings.getNameServer()+".";
        menu.setItem(22, new ItemBuilder(Material.ENDER_PEARL).withName("§6Centre du hub").withLore(lore).get());
        */
    }

    public static void createMenu(UUID uuid){
        /*ArrayList<ItemBuilder> list = new ArrayList<>();
        PlayerJump playerJump = PlayerData.getPlayerData(player.getName()).getJump();
        for(Server server : Server.getServerOnline().values()){
            Charge charge = Charge.getCharge(server.getPlayers().length);
            List<String> des = new ArrayList<>();
            des.add("§7Présence : §f"+server.getPlayers().length);
            for(Rank listGrades : Rank.getRankList().values()){
                int nbrPlayerGrade = 0;
                for(String name : server.getPlayers()){
                    String grade = Settings.getDefaultRank().getRank();
                    try {
                        ResultSet result = Database.CORE.getDatabase().getData("player_setting", "ps_player = '" + name + "'");
                        result.next();
                        grade = Database.CORE.getDatabase().getString(result, 2);
                    } catch (SQLException e){
                        //
                    } catch (SqlException e){
                        e.printStackTrace();
                    }
                    if(listGrades.getRank().equals(grade)){
                        nbrPlayerGrade++;
                    }
                }
                if(nbrPlayerGrade != 0){
                    if(listGrades.getRankDisplay().equals("")){
                        des.add(listGrades.getColorRank()+listGrades.getRank()+"§f "+nbrPlayerGrade);
                    } else {
                        des.add(listGrades.getColorRank()+listGrades.getRankDisplay()+"§f "+nbrPlayerGrade);
                    }
                }
            }
            if(playerJump.isInJump()){
                des.add(playerJump.toString(PlayerData.getPlayerData(player.getName()).getLang()));
            }

            list.add(new ItemBuilder(Charge.getCharge(server.getPlayers().length).data).withName("§6Hub N°"+Integer.parseInt(server.getName().substring(4))+ " - "+charge.label).withAmount(Integer.parseInt(server.getName().substring(4))).withLore(des.toArray(new String[des.size()])));
        }
        new MenuHub(player, list);*/
    }


    @Override
    protected void back(UUID uuid) {
        MenuPrincipal.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        switch (type) {
            case ENDER_PEARL:
                //PlayerData.getPlayerData(uuid).teleportSpawn();
                break;
            default:
                break;
        }
        //switch (itemStack.getType().getId()){
        //    case 159:
                /*int nbHub = 0;
                System.out.println(itemStack.getItemMeta().getDisplayName().substring(8, 9));
                try {
                    nbHub = Integer.parseInt(itemStack.getItemMeta().getDisplayName().substring(8, 9));
                } catch (Exception err){
                    try {
                        nbHub = Integer.parseInt(itemStack.getItemMeta().getDisplayName().substring(8, 10));
                    } catch (Exception err2){
                        //
                    }
                }
                String hub;
                if(nbHub < 10){
                    hub = "HUB_0"+nbHub;
                } else {
                    hub = "HUB_"+nbHub;
                }
                if(nbHub != DataHub.NB_SERVER){
                    Server.getServer().get(hub).update();
                    if( Server.getServer().get(hub).isOnline()){
                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
                        out.writeUTF("PlayerCount");
                        out.writeUTF(hub);
                        player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
                    }
                }*/
            //    break;
            //default:
            //    break;
        //}
    }

    public static void connectServer(UUID uuid, String server, int nbrPlayer){
        /*Server serverTarget = Server.getServerOnline().get(server);
        if(nbrPlayer < serverTarget.getNbMaxPlayer()){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
        }*/
    }
}
