package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Project Hub Created by Vorps on 04/03/2016 at 16:25.
 */
public class BlockBreakListener implements Listener {

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e){
        if(!PlayerData.getPlayerData(e.getPlayer().getName()).isBuild()){
            e.setCancelled(true);
        }
    }
}
