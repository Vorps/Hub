package me.vorps.hub.events;

import me.vorps.hub.Object.Jumps;
import me.vorps.hub.Settings;
import me.vorps.hub.menu.MenuJumpDifficultyRecord;
import me.vorps.hub.menu.MenuJumpRecord;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntities implements Listener {
	
	@EventHandler
	public void onPlayerInteractEntities(PlayerInteractEntityEvent e){
		Player player = e.getPlayer();
		Entity entity = e.getRightClicked();
        if(entity instanceof Villager){
            Jumps jumps = Jumps.getJump(entity.getCustomName().substring(0, entity.getCustomName().length()-13), Settings.getDefaultLang());
            if(jumps != null){
                MenuJumpDifficultyRecord.createMenu(player, jumps);
            }
        }
        e.setCancelled(true);
	}
}
