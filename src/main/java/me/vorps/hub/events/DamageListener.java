package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import me.vorps.hub.Hub;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class DamageListener implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();
            PlayerData playerData = PlayerData.getPlayerData(player.getName());
            if(playerData.isInJumps() && e.getDamage() > 2){
                Location loc = new Location(player.getWorld(), playerData.getLocCheckPoint().getX(), playerData.getLocCheckPoint().getY(), playerData.getLocCheckPoint().getZ());
                loc.setWorld(player.getWorld());
                if(playerData.getNumberCheckPoint() > 0){
                    loc.setYaw(playerData.getJump().getJumpsSettings().get(playerData.getNumberCheckPoint()-1).getYaw());
                    if(playerData.getJump().getJumpsSettings().get(playerData.getNumberCheckPoint()-1).getYaw() == 0){
                        loc.setZ(loc.getZ()-2);
                    } else if(playerData.getJump().getJumpsSettings().get(playerData.getNumberCheckPoint()-1).getYaw() == 90){
                        loc.setX(loc.getX()+2);
                    } else if(playerData.getJump().getJumpsSettings().get(playerData.getNumberCheckPoint()-1).getYaw() == 180 || playerData.getJump().getJumpsSettings().get(playerData.getNumberCheckPoint()-1).getYaw() == -180){
                        loc.setZ(loc.getZ()+2);
                    } else if(playerData.getJump().getJumpsSettings().get(playerData.getNumberCheckPoint()-1).getYaw() == -90){
                        loc.setX(loc.getX()-2);
                    }
                    player.sendMessage("§eEssaie n°§c"+ playerData.getNumberTrying()+".");
                    playerData.setNumberTrying(playerData.getNumberTrying()+1);
                } else {
                    loc.setYaw(playerData.getJump().getYaw());
                }
                player.teleport(loc);
                if(playerData.getNumberCheckPoint() == 0){
                    playerData.setInJumps(false);
                    if(playerData.isFly()){
                        player.sendMessage("§6Fly (§aActivé§6).");
                        player.setAllowFlight(true);
                    }
                    if(playerData.isDoubleJumps()){
                        player.sendMessage("§6Double Jump (§aActivé§6).");
                        player.setAllowFlight(true);
                        playerData.setNbrDoubleJumps(0);
                    }
                    if(playerData.isBuild()){
                        player.sendMessage("§6Mode build (§aActivé§6).");
                        player.getInventory().clear();
                        player.setGameMode(GameMode.CREATIVE);
                        player.closeInventory();
                    }
                }
            }
        }
        e.setCancelled(true);
    }
}
