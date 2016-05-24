package me.vorps.hub.Object;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.Hub;
import me.vorps.hub.PlayerData;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class Friends {

	private @Getter HashMap<String, Long> friends;
    private @Getter ArrayList<String> friendsOnline;
    private ArrayList<String> pendingFriendsList;
    private ArrayList<String> requestFriendsList;


	public Friends(String namePlayer){
        friends =  new HashMap<>();
        friendsOnline =  new ArrayList<>();
        pendingFriendsList = new ArrayList<>();
        requestFriendsList = new ArrayList<>();
		try {
			ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM friend WHERE f_name = '"+ namePlayer +"'");
			while(result.next()){
                switch (Database.FORTYCUBE.getDatabase().getInt(result, 3)) {
                    case 0:
                        pendingFriendsList.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
                        break;
                    case 1:
                        friends.put(Database.FORTYCUBE.getDatabase().getString(result, 2), Database.FORTYCUBE.getDatabase().getTimestamp(result, 4).getTime());
                        if(PlayerData.isPlayerDataExits(Database.FORTYCUBE.getDatabase().getString(result, 2))){
                            friendsOnline.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
                        }
                        break;
                    case 2:
                        requestFriendsList.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
                        break;
                    default:
                        break;
                }
			}
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}

    public boolean isFriend(String friend){
        return friends.containsKey(friend);
    }

    public void deleteFriend(Player player, String friend){
        try {
            Database.FORTYCUBE.getDatabase().sendDatabase("DELETE FROM friend WHERE f_name = '" + player.getName() + "' && f_friend = '" + friend + "'");
            Database.FORTYCUBE.getDatabase().sendDatabase("DELETE FROM friend WHERE f_name = '" + friend + "' && f_friend = '" + player.getName() + "'");
        } catch (SqlException err) {
            err.printStackTrace();
        }
        player.sendMessage("§eVous avez suprimé §2" + friend + "§e de vos amis.");
    }

    public void joinFriend(Player player){
        player.sendMessage("§6En developpement");
    }

    public void inviteFriend(Player player, String friends){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(friends);
        out.writeUTF("§bInvitation de §a"+player.getName()+" §6En developpement");
        player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
        player.sendMessage("§6En developpement");
    }

    public void addFriend(Player player){
        TextComponent tc = new TextComponent("§ePour ajouter un ami §b§nclique ici");
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§bAjouter un ami").create()));
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/friend add "));
        player.spigot().sendMessage(tc);
    }

    public void list(Player player){
        player.sendMessage("§6✴§e Amis en ligne : §a" + friendsOnline.size() + "§e/§b" + friends.size());
        String friendsOnline = "§6✴ §eAmis en ligne : [§2";
        String friendsOffline = "§6✴ §7Amis hors-ligne : [";
        String friendsPending = "§6✴ §eAmis en attente : [";
        String friendsRequest = "§6✴ §eDemande d'amis : [";
        String newFriends =  "§6✴ §eNouveau amis : [";
        for(String friend : pendingFriendsList){
            friendsPending += "§a"+friend+"§e, ";
        }

        for(String friend : friends.keySet()){
            if(PlayerData.isPlayerDataExits(friend)){
                friendsOnline += "§a"+friend+"§e, ";
            } else {
                friendsOffline += "§7"+friend+"§e, ";
            }
            if(friends.get(friend) <= System.currentTimeMillis()-259200000000L){
                newFriends += "§a"+friend+"§e, ";
            }
        }
        boolean state = false;
        if(friendsOnline.length() > 26){
            player.sendMessage(friendsOnline.substring(0, friendsOnline.length()-2) + "§e]");
            state = true;
        }
        if(friendsOffline.length() > 26){
            player.sendMessage(friendsOffline.substring(0, friendsOffline.length()-2) + "§7]");
            state = true;
        }
        if(!pendingFriendsList.isEmpty()){
            player.sendMessage(friendsPending.substring(0, friendsPending.length()-2)+"§e]");
            state = true;
        }
        for(String playerName : requestFriendsList){
            friendsRequest += "§7"+playerName+"§a, ";
        }
        if(newFriends.length() > 22){
            player.sendMessage(newFriends.substring(0, newFriends.length()-2)+"§e]");
        }
        if(friendsRequest.length() > 24){
            player.sendMessage(friendsRequest.substring(0, friendsRequest.length()-2)+"§e]");
        }
        if(!state){
            if(friendsRequest.length() < 22){
                player.sendMessage("§eVous n'avez pas d'amis vous pouvez en inviter avec la commande §a/friends add <Joueur>.");
            } else {
                player.sendMessage("§eVous n'avez pas d'amis vous pouvez en accepte la demande avec la commande §a/friends accept <Joueur>.");
            }
        }
    }
}
