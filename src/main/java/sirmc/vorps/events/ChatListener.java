package sirmc.vorps.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import sirmc.vorps.Grades;
import sirmc.vorps.Hub;
import sirmc.vorps.LoadVariable;
import sirmc.vorps.PlayerHub;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vorps on 01/02/2016.
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        LoadVariable.getListMute();
        PlayerHub playerHub = Hub.instance.getPlayerHub().get(player.getName());
        StringBuilder messageBuild = new StringBuilder(e.getMessage());
        if(player.hasPermission("hub.chatcolor")){
           for(int i = 0; i < messageBuild.length(); i++) {
               if(messageBuild.charAt(i) == '&'){
                   messageBuild.replace(i, i+1, "§");
               }
           }
        }
        String message = messageBuild.toString().trim();
        e.setFormat("%s "+ Grades.GetGrade(playerHub).getColorChat()+": "+message);
        if(Hub.instance.getListMute().contains(player.getName())){
            try {
                ResultSet resultSet = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Mute WHERE namePlayer = '"+player.getName()+"'");
                resultSet.next();
                if(resultSet.getTimestamp(2).getTime() == 0){
                    e.setCancelled(true);
                    if(resultSet.getString(3).length() > 0){
                        player.sendMessage("§cVous avez été muté pour raison : §e"+resultSet.getString(3)+"§c définitivement§c.");
                    } else {
                        player.sendMessage("§cVous avez été muté définitivement§c.");
                    }
                } else {
                    if(resultSet.getTimestamp(2).getTime() > System.currentTimeMillis()){
                        e.setCancelled(true);
                        if(resultSet.getString(3).length() > 0){
                            player.sendMessage("§cVous avez été muté pour raison : §e"+resultSet.getString(3)+"§c jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
                        } else {
                            player.sendMessage("§cVous avez été muté jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
                        }
                    } else {
                        Hub.instance.database.SendDatabase("DELETE FROM mute WHERE namePlayer = '"+player.getName()+"'");
                        player.sendMessage("§eVous pouvez maintenant parler.");
                    }
                }
            } catch (SQLException e1) {}
        }
    }
}
