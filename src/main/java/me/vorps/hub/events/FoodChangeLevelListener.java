package me.vorps.hub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class FoodChangeLevelListener implements Listener {

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }
}
