package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import me.vorps.hub.Hub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class BlockBreakListener implements Listener {

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(!playerData.isBuild() || playerData.isInJumps()){
            e.setCancelled(true);
        }
    }
}
