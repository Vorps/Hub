package sirmc.vorps.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sirmc.vorps.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vorps on 01/02/2016.
 */
public class JoinListener implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Hub.instance.database.tryConnectionDatabase();
        LoadVariable.getListBan();
        boolean online = true;
        Player player = e.getPlayer();
        if(PlayerHub.isChangeDoubleCountPlayerDatabases(player)){
            player.kickPlayer("§cLes doubles comptes ne sont pas autorisé sur le serveur §a"+ Settings.getNameServer()+"§c.");
            online = false;
        }
        if(Hub.instance.getListBan().contains(player.getName())){
            try {
                ResultSet resultSet = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Ban WHERE namePlayer = '"+player.getName()+"'");
                resultSet.next();
                if(resultSet.getTimestamp(2).getTime() == 0){
                    if(resultSet.getString(3).length() > 0){
                        player.kickPlayer("§cVous avez été bannie pour raison : §e"+resultSet.getString(3)+"§c définitivement§c.");
                    } else {
                        player.kickPlayer("§cVous avez été bannie définitivement§c.");
                    }
                    online = false;
                } else {
                    if(resultSet.getTimestamp(2).getTime() > System.currentTimeMillis()){
                        if(resultSet.getString(3).length() > 0){
                            player.kickPlayer("§cVous avez été bannie pour raison : §e"+resultSet.getString(3)+"§c jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
                        } else {
                            player.kickPlayer("§cVous avez été bannie jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
                        }
                        online = false;
                    } else {
                        Hub.instance.database.SendDatabase("DELETE FROM ban WHERE namePlayer = '"+player.getName()+"'");
                        player.sendMessage("§eVous êtes débannie !!!.");
                    }
                }
            } catch (SQLException e1) {}
        }
        if(online){
            Hub.instance.getPlayerHub().put(e.getPlayer().getName() , new PlayerHub(e.getPlayer()));
            Hub.instance.getScoreBoard().updatePlayer();
            for(int i = 0; i < Grades.getGradesList().size(); i++){
                if(Grades.getGradesList().get(i).getGrade().equals(Hub.instance.getPlayerHub().get(player.getName()).getGrade())){
                    if(Grades.getGradesList().get(i).isVisibleGrade()){
                        e.setJoinMessage(Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeDisplay()+" "+player.getName()+" §ea rejoint le hub !");
                    } else {
                        e.setJoinMessage("");
                    }
                }
            }
            for(Player playerOnline : Bukkit.getOnlinePlayers()){
                if(!Hub.instance.getPlayerHub().get(playerOnline.getName()).isVisiblePlayer()){
                    Bukkit.getPlayer(Hub.instance.getPlayerHub().get(playerOnline.getName()).getPlayerName()).hidePlayer(player);
                }
            }
        } else {
            return;
        }
    }

}
