package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.LoadVariable;
import sirmc.vorps.PlayerHub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandBan {

    public static boolean CommandBan(ArrayList<String> args, PlayerHub playerHub){
        LoadVariable.getListBan();
        if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.ban.time")){
            long time;
            if(args.size() >= 1){
                if(!Hub.instance.getListBan().contains(args.get(0))){
                    int day = 0;
                    int hour = 0;
                    int minute = 0;
                    int second = 0;
                    for(int i = 1; i < args.size(); i++){
                        if(args.get(i).charAt(args.get(i).length()-1) == 'j' || args.get(i).charAt(args.get(i).length()-1) == 'J'){
                            try {
                                day = Integer.parseInt(args.get(i).substring(0, args.get(i).length()-1))*86400000;
                            } catch (Exception e) {}
                        }
                        if(args.get(i).charAt(args.get(i).length()-1) == 'h' || args.get(i).charAt(args.get(i).length()-1) == 'H'){
                            try {
                                hour = Integer.parseInt(args.get(i).substring(0, args.get(i).length()-1))*3600000;
                            } catch (Exception e) {}
                        }
                        if(args.get(i).charAt(args.get(i).length()-1) == 'm' || args.get(i).charAt(args.get(i).length()-1) == 'M'){
                            try {
                                minute = Integer.parseInt(args.get(i).substring(0, args.get(i).length()-1))*60000;
                            } catch (Exception e) {}
                        }
                        if(args.get(i).charAt(args.get(i).length()-1) == 's' || args.get(i).charAt(args.get(i).length()-1) == 'S'){
                            try {
                                second = Integer.parseInt(args.get(i).substring(0, args.get(i).length()-1))*1000;
                            } catch (Exception e) {}
                        }
                    }
                    String reason = new String();
                    for(int i = 1; i < args.size(); i++){
                        if(args.get(i).charAt(args.get(i).length()-1) != 'j' && args.get(i).charAt(args.get(i).length()-1) != 'J' &&
                                args.get(i).charAt(args.get(i).length()-1) != 'm' && args.get(i).charAt(args.get(i).length()-1) != 'M' &&
                                args.get(i).charAt(args.get(i).length()-1) != 'h' && args.get(i).charAt(args.get(i).length()-1) != 'H' &&
                                args.get(i).charAt(args.get(i).length()-1) != 's' && args.get(i).charAt(args.get(i).length()-1) != 'S'){
                            reason += args.get(i)+" ";
                        }
                    }
                    time = day + hour + minute+ second;
                    if(reason.length() != 0){
                        reason = reason.substring(0, reason.length()-1);
                    }
                    Timestamp timeMute = new Timestamp(System.currentTimeMillis());
                    if(time < 0){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cErreur temps invalide.");
                        return false;
                    } else if(time == 0){
                        if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.ban.def")){
                            timeMute = new Timestamp(0);
                            if(reason.length() > 0){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+"§e a bien été ban définitif pour raison : §a"+reason.substring(0, reason.length()-1)+"§e.");
                                if(Hub.instance.getPlayerHub().containsKey(args.get(0))){
                                    Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args.get(0)).getPlayerName()).sendMessage("§cVous avez été ban pour raison : §e"+reason.substring(0, reason.length()-1)+"§c définitivement§c.");
                                }
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+"§e a bien été bannie définitif§e.");
                                if(Hub.instance.getPlayerHub().containsKey(args.get(0))){
                                    Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args.get(0)).getPlayerName()).sendMessage("§cVous avez été bannie définitivement§c.");
                                }
                            }
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'avez pas la permission de banir définitivement veuillez mettre un temps de bannissement.");
                            return false;
                        }
                    } else if(time > 0){
                        timeMute = new Timestamp(System.currentTimeMillis()+time);
                        if(reason.length() > 0){
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+"§e a bien été ban jusqu'au "+Hub.instance.getDATE_FORMAT().format(timeMute)+"§e pour raison : §a"+reason.substring(0, reason.length()-1)+"§e.");
                            if(Hub.instance.getPlayerHub().containsKey(args.get(0))){
                                Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args.get(0)).getPlayerName()).sendMessage("§cVous avez été ban pour raison : §e"+reason.substring(0, reason.length()-1)+"§c jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(timeMute)+"§c.");
                            }
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+"§e a bien été ban jusqu'au "+Hub.instance.getDATE_FORMAT().format(timeMute)+"§e.");
                            if(Hub.instance.getPlayerHub().containsKey(args.get(0))){
                                Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args.get(0)).getPlayerName()).sendMessage("§cVous avez été ban jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(timeMute)+"§c.");
                            }
                        }
                    }

                    Hub.instance.database.SendDatabase("INSERT INTO Ban VALUES ('"+args.get(0)+"', '"+timeMute+"', '"+reason.substring(0, reason.length()-1)+"', '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"')");
                    return true;
                } else {
                    try {
                        ResultSet resultSet = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Ban WHERE namePlayer = '"+args.get(0)+"'");
                        resultSet.next();
                        if(resultSet.getTimestamp(2).getTime() == 0){
                            if(resultSet.getString(3).length() > 0){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+" §cest déjà bannie pour raison : §e"+resultSet.getString(3)+"§c définitivement§c.");
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+" §cest déjà bannie définitivement§c.");
                            }
                        } else {
                            if(resultSet.getString(3).length() > 0){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+" §cest déjà bannie pour raison : §e"+resultSet.getString(3)+"§c jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+" §cest déjà bannie jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
                            }
                        }
                    } catch (SQLException e) {}
                }
            } else {
                AideCommande.AideBan(playerHub);
            }
        }
        return false;
    }
}
