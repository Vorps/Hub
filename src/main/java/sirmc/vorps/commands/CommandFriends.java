package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.ChatInteract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandFriends {

    public static boolean CommandFriends(String args[], PlayerHub playerHub){
        playerHub.UpdateFriends();
        if(args.length == 1 && args[0].equalsIgnoreCase("list")){
            if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.friends.list")){
                String amisOnline, amisOffline;
                amisOnline = "§eAmis en ligne : [§2";
                amisOffline = "§7Amis en Hors-Ligne : §7[";
                String amisAttente = "§eAmis en attente : [";
                String amisAccept = "§eNouveau amis : [";

                for(int i = 0; i < playerHub.getFriends().getPendingFriendsList().size(); i++){
                    ResultSet resultSet;
                    try {
                        resultSet = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Friends WHERE namePlayer = '"+playerHub.getFriends().getFriendsOnLineList().get(i)+"' && friend = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                        resultSet.next();
                        if(resultSet.getInt(3) == 1){
                            amisAccept += "§a"+playerHub.getFriends().getPendingFriendsList().get(i)+"§e,";
                        } else {
                            amisAttente += "§a"+playerHub.getFriends().getPendingFriendsList().get(i)+"§e,";
                        }
                    } catch (SQLException e) {}
                }
                for(int i = 0; i < playerHub.getFriends().getFriends().size(); i++){
                    if(Hub.instance.getPlayerHub().containsKey(playerHub.getFriends().getFriends().get(i))){
                        amisOnline += "§a"+playerHub.getFriends().getFriends().get(i)+"§a, ";
                    } else {
                        amisOffline += "§7"+playerHub.getFriends().getFriends().get(i)+"§a, ";
                    }
                }
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage(amisOnline+"§e]");
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage(amisOffline+"§7]");
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage(amisAccept+"]");
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage(amisAttente+"]");
                return true;
            }
        } else if(args.length == 2){
            if(args[0].equalsIgnoreCase("add")){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.friends.add")){
                    if(!playerHub.getFriends().getFriends().contains(args[1])){
                        if(!playerHub.getFriends().getPendingFriendsList().contains(args[1])){
                            if(playerHub.getFriends().getFriends().size() <= playerHub.getNumbersFriends()){
                                if(!Bukkit.getPlayer(playerHub.getPlayerName()).getName().equals(args[1])){
                                    if(Hub.instance.getListPlayer().contains(args[1])){
                                        Hub.instance.database.SendDatabase("INSERT INTO Friends VALUES ('"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"', '"+args[1]+"', '"+0+"', '"+new Timestamp(System.currentTimeMillis())+"')");
                                        Hub.instance.database.SendDatabase("INSERT INTO Friends VALUES ('"+args[1]+"', '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"', '"+2+"', '"+new Timestamp(System.currentTimeMillis())+"')");
                                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eDemande d'ami envoyée a §2"+args[1]+".");
                                        if(Hub.instance.getPlayerHub().containsKey(args[1])){
                                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[1]).getPlayerName()).sendMessage("§2"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§e vous a demand§ en ami.");
                                            ChatInteract.ChatInteractAcceptAmi(Hub.instance.getPlayerHub().get(args[1]), Bukkit.getPlayer(playerHub.getPlayerName()).getName());
                                        }
                                        return true;
                                    } else {
                                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e"+args[1]+"§c ne s'est jamais connecté sur ce serveur.");
                                    }
                                } else {
                                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous ne pouvez pas vous ajouter en amis.");
                                }
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez atteind le nombre maximal d'amis.");
                            }
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez déjà envoy§ une invitation d'amis a §a"+args[1]+"§e.");
                            ChatInteract.ChatInteractAcceptAmi(playerHub, args[1]);
                        }
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[1]+"§cest déjà votre ami.");
                    }
                }
            } else if(args[0].equalsIgnoreCase("remove")){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.friends.remove")){
                    if(playerHub.getFriends().getFriends().contains(args[1])){
                        Hub.instance.database.SendDatabase("DELETE FROM Friends WHERE namePlayer = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"' && friend = '"+args[1]+"'");
                        Hub.instance.database.SendDatabase("DELETE FROM Friends WHERE namePlayer = '"+args[1]+"' && friend = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez suprimé §2"+args[1]+"§e de vos amis.");
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eCe joueur n'est pas votre ami.");
                    }
                    return true;
                }
            } else if(args[0].equalsIgnoreCase("accept")){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.friends.accept")){
                    if(playerHub.getFriends().getFriendsRequestList().contains(args[1])){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[1]+"§e a bien été ajouté a votre liste d'amis.");
                        Hub.instance.database.SendDatabase("UPDATE friends SET variable = '"+1+"', date = '"+new Timestamp(System.currentTimeMillis())+"' WHERE namePlayer = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"' && friend = '"+args[1]+"'");
                        if(Hub.instance.getPlayerHub().containsKey(args[1])){
                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[1]).getPlayerName()).sendMessage("§a"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§e vous a accepté en ami.");
                        }
                        return true;
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[1]+"§e ne vous a pas demandé en ami.");
                    }
                }
            } else {
                AideCommande.AideFriends(playerHub);
            }
        } else {
            AideCommande.AideFriends(playerHub);
        }
        playerHub.UpdateFriends();
        return false;
    }

}
