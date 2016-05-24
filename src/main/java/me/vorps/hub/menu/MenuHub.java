package me.vorps.hub.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.vorps.fortycube.Exceptions.SqlException;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.AllArgsConstructor;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.*;
import me.vorps.hub.Object.PlayerJump;
import me.vorps.hub.Object.Server;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.vorps.hub.Object.Grades;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuHub extends MenuRecursive{
    @AllArgsConstructor
    public enum Charge {
        LOW(45, (byte) 5, "§aCharge faible"),
        MEDIUM(25, (byte) 4, "§eCharge moyenne"),
        HIGH(35, (byte) 1, "§6Charge §levée"),
        FULL(45, (byte) 14, "§cCharge complet");

        private int charge;
        private byte data;
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
	private MenuHub(Player player, ArrayList<Item> list){
        super(new byte[] {10}, Bukkit.createInventory(null, 27, "§6Hub ("+ Data.NB_SERVER+")"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9);
        initMenu(player, 1);
        player.openInventory(menu);
	}

    public void initMenu(Player player, int page){
        menu.clear();
        menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        String[] lore;
        PlayerJump playerJump = PlayerData.getPlayerData(player.getName()).getJump();
        if(playerJump.isInJump()){
            lore = new String[2];
            lore[1] = playerJump.toString(PlayerData.getPlayerData(player.getName()).getLang());
        } else {
            lore = new String[1];
        }
        lore[0] = "§7Retourner au spawn de "+ Settings.getNameServer()+".";
        menu.setItem(22, new Item(Material.ENDER_PEARL).withName("§6Centre du hub").withLore(lore).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        PlayerJump playerJump = PlayerData.getPlayerData(player.getName()).getJump();
        for(Server server : Server.getServerOnline().values()){
            Charge charge = Charge.getCharge(server.getPlayers().length);
            List<String> des = new ArrayList<>();
            des.add("§7Présence : §f"+server.getPlayers().length);
            for(Grades listGrades : Grades.getGradesList().values()){
                int nbrPlayerGrade = 0;
                for(String name : server.getPlayers()){
                    String grade = Settings.getDefaultGrade().getGrade();
                    try {
                        ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_setting WHERE ps_player = '" + name + "'");
                        result.next();
                        grade = Database.FORTYCUBE.getDatabase().getString(result, 2);
                    } catch (SQLException e){
                        //
                    } catch (SqlException e){
                        e.printStackTrace();
                    }
                    if(listGrades.getGrade().equals(grade)){
                        nbrPlayerGrade++;
                    }
                }
                if(nbrPlayerGrade != 0){
                    if(listGrades.getGradeDisplay().equals("")){
                        des.add(listGrades.getColorGrade()+listGrades.getGrade()+"§f "+nbrPlayerGrade);
                    } else {
                        des.add(listGrades.getColorGrade()+listGrades.getGradeDisplay()+"§f "+nbrPlayerGrade);
                    }
                }
            }
            if(playerJump.isInJump()){
                des.add(playerJump.toString(PlayerData.getPlayerData(player.getName()).getLang()));
            }
            list.add(new Item(159).withData(Charge.getCharge(server.getPlayers().length).data).withName("§6Hub N°"+Integer.parseInt(server.getName().substring(4))+ " - "+charge.label).withAmount(Integer.parseInt(server.getName().substring(4))).withLore(des.toArray(new String[des.size()])));
        }
        new MenuHub(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuPrincipal.createMenu(player);
                break;
            case ENDER_PEARL:
                PlayerData.getPlayerData(player.getName()).teleportSpawn();
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
        switch (itemStack.getType().getId()){
            case 159:
                int nbHub = 0;
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
                if(nbHub != Data.NB_SERVER){
                    Server.getServer().get(hub).update();
                    if( Server.getServer().get(hub).isOnline()){
                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
                        out.writeUTF("PlayerCount");
                        out.writeUTF(hub);
                        player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
                    }
                }
                break;
            default:
                break;
        }
    }

    public static void connectServer(Player player, String server, int nbrPlayer){
        Server serverTarget = Server.getServerOnline().get(server);
        if(nbrPlayer < serverTarget.getNbMaxPlayer()){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
        }
    }
}
