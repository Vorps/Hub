package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class DropPlayerListener implements Listener {
    @EventHandler
    public void OnDropPlayer(PlayerDropItemEvent e){
        if(!PlayerData.getPlayerData(e.getPlayer().getName()).isBuild()){
            e.setCancelled(true);
        }
    }
}
