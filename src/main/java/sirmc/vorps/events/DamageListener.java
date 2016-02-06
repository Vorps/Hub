package sirmc.vorps.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.commands.CommandFly;

/**
 * Created by Vorps on 01/02/2016.
 */
public class DamageListener implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();
            PlayerHub playerHub = Hub.instance.getPlayerHub().get(player.getName());
            if(playerHub.isInJumps()){
                Location loc = new Location(player.getWorld(), playerHub.getLocCheckPoint().getX(), playerHub.getLocCheckPoint().getY(), playerHub.getLocCheckPoint().getZ());
                loc.setWorld(Bukkit.getPlayer(playerHub.getPlayerName()).getWorld());
                if(playerHub.getNumberCheckPoint() > 0){
                    loc.setYaw(playerHub.getJump().getJumpSettings().get(playerHub.getNumberCheckPoint()-1).getYaw());
                    if(playerHub.getJump().getJumpSettings().get(playerHub.getNumberCheckPoint()-1).getYaw() == 0){
                        loc.setZ(loc.getZ()-2);
                    } else if(playerHub.getJump().getJumpSettings().get(playerHub.getNumberCheckPoint()-1).getYaw() == 90){
                        loc.setX(loc.getX()+2);
                    } else if(playerHub.getJump().getJumpSettings().get(playerHub.getNumberCheckPoint()-1).getYaw() == 180 || playerHub.getJump().getJumpSettings().get(playerHub.getNumberCheckPoint()-1).getYaw() == -180){
                        loc.setZ(loc.getZ()+2);
                    } else if(playerHub.getJump().getJumpSettings().get(playerHub.getNumberCheckPoint()-1).getYaw() == -90){
                        loc.setX(loc.getX()-2);
                    }
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eEssaie n°§c"+playerHub.getNumberTrying()+".");
                    playerHub.setNumberTrying(playerHub.getNumberTrying()+1);
                } else {
                    loc.setYaw(playerHub.getJump().getYaw());
                }
                Bukkit.getPlayer(playerHub.getPlayerName()).teleport(loc);
                if(playerHub.getNumberCheckPoint() == 0){
                    playerHub.setInJumps(false);
                    if(playerHub.isFly()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Fly (§aActivé§6).");
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(true);
                    }
                    if(playerHub.isDoubleJumps()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Double Jump (§aActivé§6).");
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(true);
                        playerHub.setNbrDoubleJumps(0);
                    }
                    if(playerHub.isBuild()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Mode build (§2Activé§6).");
                        Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().clear();
                        Bukkit.getPlayer(playerHub.getPlayerName()).setGameMode(GameMode.CREATIVE);
                        Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                    }
                }
            }
        }
        e.setCancelled(true);
    }
}
