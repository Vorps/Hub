package sirmc.vorps.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import sirmc.vorps.Hub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class BlockBreakListener implements Listener {

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e){
        try {
            if(!Hub.instance.getPlayerHub().get(e.getPlayer().getName()).isBuild()){
                e.setCancelled(true);
            }
        } catch (Exception e2) {
        }
    }
}
