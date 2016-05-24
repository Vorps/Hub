package me.vorps.hub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e){
        e.setCancelled(true);
    }

}
