package sirmc.vorps.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.Settings;
import sirmc.vorps.utils.Cooldowns;

/**
 * Created by Vorps on 01/02/2016.
 */
public class FlyOnJumpListener implements Listener {
    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent e) {
        PlayerHub playerHub = Hub.instance.getPlayerHub().get(e.getPlayer().getName());
        if(playerHub.isDoubleJumps()) {
            if(Cooldowns.DoubleJumps(playerHub)){
                if(playerHub.getNbrDoubleJumps() < Settings.getNbrDoubleJumpsMax()){
                    playerHub.setNbrDoubleJumps(playerHub.getNbrDoubleJumps()+1);
                    Vector jump =  Bukkit.getPlayer(playerHub.getPlayerName()).getLocation().getDirection().multiply(2).setY(1.1);
                    Bukkit.getPlayer(playerHub.getPlayerName()).setVelocity(Bukkit.getPlayer(playerHub.getPlayerName()).getVelocity().add(jump));
                } else {
                    playerHub.setNbrDoubleJumps(0);
                    Cooldowns.addCooldownsDoubleJumps(playerHub);
                    Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(false);
                }
            }
            e.setCancelled(true);
        }
    }
}
