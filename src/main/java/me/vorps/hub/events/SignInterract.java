package me.vorps.hub.events;

import me.vorps.hub.dispatcher.Dispatcher;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Project Hub Created by Vorps on 02/05/2016 at 04:18.
 */
public class SignInterract implements Listener {

    @EventHandler
    public void onInterractItem(PlayerInteractEvent e){
        Action action = e.getAction();
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        if (action == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getState() instanceof Sign){
                Sign sign = (Sign) block.getState();
                Dispatcher.connectServer(sign.getLine(0).substring(2), player);
            }
        }
    }
}
