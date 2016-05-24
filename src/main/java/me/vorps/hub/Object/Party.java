package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class Party {
    private @Getter boolean state;
    private @Getter String name;
    private @Getter String owner;
    private @Getter HashMap<String, Long> members = new HashMap<>();
    private @Getter ArrayList<String> membersOnline = new ArrayList<>();
    private @Getter String message;
    private @Getter long date;
    private ArrayList<String> pendingMembersList = new ArrayList<>();
    private ArrayList<String> requestMembersList = new ArrayList<>();

	public Party(String namePlayer) {
        try {
            ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM party WHERE party_member = '" + namePlayer + "' || party_leader = '" + namePlayer + "'");
            if (result.next()) {
                name = Database.FORTYCUBE.getDatabase().getString(result, 1);
                owner = Database.FORTYCUBE.getDatabase().getString(result, 5);
                message = Database.FORTYCUBE.getDatabase().getString(result, 6);
                date = Database.FORTYCUBE.getDatabase().getTimestamp(result, 4).getTime();
                do {
                    switch (Database.FORTYCUBE.getDatabase().getInt(result, 3)) {
                        case 0:
                            if(namePlayer.equals(owner)){
                                pendingMembersList.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
                            } else {
                                requestMembersList.add(Database.FORTYCUBE.getDatabase().getString(result, 5));
                            }
                            break;
                        case 1:
                            members.put(Database.FORTYCUBE.getDatabase().getString(result, 2), Database.FORTYCUBE.getDatabase().getTimestamp(result, 7).getTime());
                            if(PlayerData.isPlayerDataExits(Database.FORTYCUBE.getDatabase().getString(result, 2))){
                                membersOnline.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
                            }
                            break;
                        default:
                            break;
                    }
                } while (result.next());
            }
            if(!members.isEmpty()){
                state = true;
            }
        } catch (SQLException e){
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
	}

    public boolean isMember(String member){
        return members.containsKey(member);
    }

    public void deleteMenber(Player player, String member) {
        try {
            Database.FORTYCUBE.getDatabase().sendDatabase("DELETE FROM party WHERE party_member = '" + member + "'");
        } catch (SqlException e) {
            e.printStackTrace();
        }
        player.sendMessage("§eLe membre §a" + member + " §e été supprimé du groupe.");
        if(member.length() == 1){
            player.sendMessage("§eVous avez dissout la party.");
        }
        PlayerData.getPlayerData(player.getName()).updateParty();
    }

    public void disoudreParty(){

    }

    public void quitParty(){

    }

    public void createParty(){

    }

    public void list(Player player){
        player.sendMessage("§6✴ §eMembres en ligne : §a" + membersOnline.size() + "§e/§b" + members.size());
        String newMembers =  "✴§eNouveau membres : [";
        String friendsRequest = "✴§eDemande de party : [";
        for(String member : members.keySet()){
            if (PlayerData.isPlayerDataExits(member)) {
                player.sendMessage("✴§a" + member + "§f <> §aOnline§e.");
            } else {
                player.sendMessage("✴§a" + member + "§f <> §7Offline§e.");
            }
            if(members.get(member) >= System.currentTimeMillis()-259200000000L){
                newMembers += "§7"+member+"§a, ";
            }
        }
        for(String owner : requestMembersList){
            friendsRequest += "§7"+owner+"§a, ";
        }
        if(newMembers.length() > 23){
            player.sendMessage(newMembers.substring(0, newMembers.length()-2)+ "§e]");
        }

        if(!requestMembersList.isEmpty()){
            player.sendMessage(friendsRequest.substring(0, friendsRequest.length()-2)+ "§e]");
        }
        if(owner != null){
            if(owner.equals(player.getName())){
                String friendsPending = "§eMembres en attente : [";
                for(String member : pendingMembersList){
                    friendsPending += "§a"+member+"§a, ";
                }
                if(!pendingMembersList.isEmpty()){
                    player.sendMessage(friendsPending.substring(0, friendsPending.length()-2)+ "§e]");
                }
            }
        }
    }
}