package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.LoadVariable;
import sirmc.vorps.PlayerHub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandMute {

    public static boolean CommandMute(String args[], PlayerHub playerHub){
        if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.mute.time")){
            long time = 0;
            if(args.length >= 1){
                if(!Hub.instance.getListMute().contains(args[0])){
                    int day = 0;
                    int hour = 0;
                    int minute = 0;
                    int second = 0;
                    for(int i = 1; i < args.length; i++){
                        if(args[i].charAt(args[i].length()-1) == 'j' || args[i].charAt(args[i].length()-1) == 'J'){
                            try {
                                day = Integer.parseInt(args[i].substring(0, args[i].length()-1))*86400000;
                            } catch (Exception e) {
                            }
                        }
                        if(args[i].charAt(args[i].length()-1) == 'h' || args[i].charAt(args[i].length()-1) == 'H'){
                            try {
                                hour = Integer.parseInt(args[i].substring(0, args[i].length()-1))*3600000;
                            } catch (Exception e) {
                            }
                        }
                        if(args[i].charAt(args[i].length()-1) == 'm' || args[i].charAt(args[i].length()-1) == 'M'){
                            try {
                                minute = Integer.parseInt(args[i].substring(0, args[i].length()-1))*60000;
                            } catch (Exception e) {
                            }
                        }
                        if(args[i].charAt(args[i].length()-1) == 's' || args[i].charAt(args[i].length()-1) == 'S'){
                            try {
                                second = Integer.parseInt(args[i].substring(0, args[i].length()-1))*1000;
                            } catch (Exception e) {
                            }
                        }
                    }
                    String raison = new String();
                    for(int i = 1; i < args.length; i++){
                        if(args[i].charAt(args[i].length()-1) != 'j' && args[i].charAt(args[i].length()-1) != 'J' &&
                                args[i].charAt(args[i].length()-1) != 'm' && args[i].charAt(args[i].length()-1) != 'M' &&
                                args[i].charAt(args[i].length()-1) != 'h' && args[i].charAt(args[i].length()-1) != 'H' &&
                                args[i].charAt(args[i].length()-1) != 's' && args[i].charAt(args[i].length()-1) != 'S'){
                            raison += args[i]+" ";
                        }
                    }
                    time = day + hour + minute+ second;
                    if(raison.length() != 0){
                        raison = raison.substring(0, raison.length()-1);
                    }
                    Timestamp timeMute = new Timestamp(System.currentTimeMillis());
                    if(time < 0){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cErreur temps invalide.");
                        return false;
                    } else if(time == 0){
                        if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.mute.def")){
                            timeMute = new Timestamp(0);
                            if(raison.length() > 0){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+"§e a bien été mute définitif pour raison : §a"+raison.substring(0, raison.length()-1)+"§e.");
                                if(Hub.instance.getPlayerHub().containsKey(args[0])){
                                    Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§cVous avez été muté pour raison : §e"+raison.substring(0, raison.length()-1)+"§c définitivement§c.");
                                }
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+"§e a bien été mute définitif§e.");
                                if(Hub.instance.getPlayerHub().containsKey(args[0])){
                                    Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§cVous avez été muté définitivement§c.");
                                }
                            }
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'avez pas la permission du mute définitif veuillez mettre un temps de mute.");
                            return false;
                        }
                    } else if(time > 0){
                        timeMute = new Timestamp(System.currentTimeMillis()+time);
                        if(raison.length() > 0){
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+"§e a bien §t§ mute jusqu'au "+Hub.instance.getDATE_FORMAT().format(timeMute)+"§e pour raison : §a"+raison.substring(0, raison.length()-1)+"§e.");
                            if(Hub.instance.getPlayerHub().containsKey(args[0])){
                                Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§cVous avez été muté pour raison : §e"+raison.substring(0, raison.length()-1)+"§c jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(timeMute)+"§c.");
                            }
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+"§e a bien été mute jusqu'au "+Hub.instance.getDATE_FORMAT().format(timeMute)+"§e.");
                            if(Hub.instance.getPlayerHub().containsKey(args[0])){
                                Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§cVous avez été muté jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(timeMute)+"§c.");
                            }
                        }
                    }

                    Hub.instance.database.SendDatabase("INSERT INTO mute VALUES ('"+args[0]+"', '"+timeMute+"', '"+raison.substring(0, raison.length()-1)+"', '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"')");
                    LoadVariable.getListMute();
                    return true;
                } else {
                    try {
                        ResultSet resultSet = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Mute WHERE namePlayer = '"+args[0]+"'");
                        resultSet.next();
                        if(resultSet.getTimestamp(2).getTime() == 0){
                            if(resultSet.getString(3).length() > 0){
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+" §cest déjà mute définitivement§c.");
                            }
                        } else {
                            if(resultSet.getString(3).length() > 0){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+" §cest déjà mute pour raison : §e"+resultSet.getString(3)+"§c jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+" §cest déjà mute jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
                            }
                        }
                    } catch (SQLException e) {}
                    return true;
                }
            } else {
                AideCommande.AideMute(playerHub);
            }
        }
        return false;
    }
}
