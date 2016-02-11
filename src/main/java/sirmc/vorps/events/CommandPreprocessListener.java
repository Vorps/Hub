package sirmc.vorps.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import sirmc.vorps.Commands;
import sirmc.vorps.Hub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandPreprocessListener implements Listener {

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
        Player player = e.getPlayer();
        if(e.getMessage().length() >= 4){
            if(e.getMessage().substring(0, 4).equalsIgnoreCase("/ban")){
                e.setCancelled(true);
            }
        }
        if(e.getMessage().length() >= 7){
            if(e.getMessage().substring(0, 7).equalsIgnoreCase("/pardon")){
                e.setCancelled(true);
            }
        }
        boolean state = Hub.instance.getListCommands().containsKey(e.getMessage().substring(1));
        if(state){
            if(!player.hasPermission(Hub.instance.getListCommands().get(e.getMessage().substring(1)).getPermission())){
                e.setCancelled(true);
            }
        }
        if(!state && !e.getPlayer().isOp()){
            e.setCancelled(true);
        }
    }
}
