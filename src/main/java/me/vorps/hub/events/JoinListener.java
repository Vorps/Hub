package me.vorps.hub.events;

import me.vorps.hub.Object.Grades;
import me.vorps.hub.Hub;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.databases.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class JoinListener implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Database.FORTYCUBE.getDatabase().tryConnectionDatabase();
        Player player = e.getPlayer();
        e.setJoinMessage("");
        player.sendMessage("§b✴§3 Bonjour "+player.getName());
        PlayerData playerData = new PlayerData(player.getPlayer());
        Hub.getInstance().getScoreBoard().updateValue(4, "§aJoueur : "+PlayerData.nbrConnect());
        Grades grades = playerData.getGrade();
        if(grades.isVisibleGrade()){
            e.setJoinMessage(grades.getColorGrade()+grades.getGradeDisplay()+" "+player.getName()+" §ea rejoint le hub !");
        }
        Bukkit.getOnlinePlayers().forEach((Player playerOnline) -> {
            if(!playerData.isVisiblePlayer()){
                player.hidePlayer(player);
            }
        });
        Bukkit.getOnlinePlayers().forEach(p -> {
           if(!player.getName().equals(p.getName())){
                if(grades.isVisibleGrade()){
                    p.sendMessage(e.getJoinMessage());
                }
           }
        });
        e.setJoinMessage("");
    }
}
