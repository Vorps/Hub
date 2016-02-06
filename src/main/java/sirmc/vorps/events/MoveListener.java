package sirmc.vorps.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Cooldowns;

/**
 * Created by Vorps on 05/02/2016.
 */
public class MoveListener implements Listener{

    @EventHandler
    public void onMovePlayer(PlayerMoveEvent e){
       if(!e.getPlayer().isOnGround()) {
           PlayerHub playerHub = Hub.instance.getPlayerHub().get(e.getPlayer().getName());
           if(Hub.instance.getPlayerHub().get(e.getPlayer().getName()).isDoubleJumps()){
               Cooldowns.DoubleJumps(playerHub);
           }
       }
    }
}
