package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Project Hub Created by Vorps on 04/03/2016 at 16:25.
 */
public class CancelledEventListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(!PlayerData.isBuild(e.getPlayer().getUniqueId())) e.setCancelled(true);
    }

    @EventHandler
    public void onDropPlayer(PlayerDropItemEvent e){
        if(!PlayerData.isBuild(e.getPlayer().getUniqueId())) e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherEvent(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }
}
