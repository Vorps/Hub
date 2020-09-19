package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.PlayerJump;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class DamageListener implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e){
        /*if(e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();
            PlayerJump playerJump = PlayerData.getPlayerData(player.getName()).getJump();
            if(playerJump.isInJump() && e.getDamage() > 4){
                playerJump.failManager(player);
            }
        }*/
        e.setCancelled(true);
    }
}
