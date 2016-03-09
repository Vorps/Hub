package me.vorps.hub.events;

import me.vorps.hub.Hub;
import me.vorps.hub.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Vorps on 01/02/2016.
 */
public class DropPlayerListener implements Listener {
    @EventHandler
    public void OnDropPlayer(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(!playerData.isBuild() || playerData.isInJumps()){
            e.setCancelled(true);
        }
    }
}
