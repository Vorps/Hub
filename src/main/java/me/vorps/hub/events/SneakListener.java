package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * Project Hub Created by Vorps on 25/05/2016 at 22:55.
 */
public class SneakListener implements Listener{

    @EventHandler
    public void onSneakEvent(PlayerToggleSneakEvent e){
        PlayerData playerData = PlayerData.getPlayerData(e.getPlayer().getName());
        /*if(playerData.getParticle() != null){
            playerData.getParticle().setSneak(true);
        }*/
    }
}
