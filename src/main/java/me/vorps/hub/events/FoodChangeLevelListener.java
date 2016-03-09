package me.vorps.hub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by Vorps on 29/01/2016.
 */
public class FoodChangeLevelListener implements Listener {

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }
}
