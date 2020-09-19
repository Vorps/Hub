package me.vorps.hub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntitiesListener implements Listener {
	
	@EventHandler
	public void onPlayerInteractEntities(PlayerInteractEntityEvent e){
		/*Player player = e.getPlayer();
		Entity entity = e.getRightClicked();
        if(entity instanceof Villager){
            Jumps jumps = Jumps.getJump(entity.getCustomName().substring(0, entity.getCustomName().length()-13), Settings.getDefaultLang());
            if(jumps != null){
                MenuJumpDifficultyRecord.createMenu(player.getUniqueId(), jumps);
            }
        }*/
        e.setCancelled(true);
	}
}
