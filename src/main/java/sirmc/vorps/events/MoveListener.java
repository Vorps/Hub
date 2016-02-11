package sirmc.vorps.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.Settings;
import sirmc.vorps.utils.Cooldowns;

/**
 * Created by Vorps on 05/02/2016.
 */
public class MoveListener implements Listener{

    @EventHandler
    public void onMovePlayer(PlayerMoveEvent e){
        Player player = e.getPlayer();
        PlayerHub playerHub = Hub.instance.getPlayerHub().get(player.getName());
        Location loc = player.getLocation();
        if(!player.isOnGround()) {
            if(Hub.instance.getPlayerHub().get(player.getName()).isDoubleJumps()){
                Cooldowns.DoubleJumps(playerHub);
            }
        }
        boolean state = false;
        if(loc.getX() < Settings.getZone()[0]){
            state = true;
        }
        if(loc.getX() > Settings.getZone()[1]){
            state = true;
        }
        if(loc.getY() < Settings.getZone()[2]){
            state = true;
        }
        if(loc.getY() > Settings.getZone()[3]){
            state = true;
        }
        if(loc.getZ() < Settings.getZone()[4]){
            state = true;
        }
        if(loc.getZ() > Settings.getZone()[5]){
            state = true;
        }
        if(state){
            playerHub.teleportSpawn();
            player.sendMessage("§7Ne vous éloignez pas trop");
        }
    }
}
