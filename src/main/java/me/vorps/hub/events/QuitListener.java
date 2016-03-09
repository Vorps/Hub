package me.vorps.hub.events;

import me.vorps.hub.Object.Grades;
import me.vorps.hub.Hub;
import me.vorps.hub.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        Grades grade = playerData.getGrade();
        if(grade.isVisibleGrade()){
            e.setQuitMessage(grade+" "+player.getName()+" §ea quitté le hub !");
        } else {
            e.setQuitMessage("");
        }
        playerData.removePlayerHub(true);
        Hub.getInstance().getScoreBoard().updateValue(4, "§aJoueur : "+PlayerData.nbrConnect());
    }
}
