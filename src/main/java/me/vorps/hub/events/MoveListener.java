package me.vorps.hub.events;

import me.vorps.fortycube.utils.Limite;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MoveListener implements Listener{

    @EventHandler
    public void onMovePlayer(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if(Limite.limite(player.getLocation(), new double[] {Settings.getXMore(), Settings.getXLess(), Settings.getYMore(), Settings.getYLess(), Settings.getZMore(), Settings.getZLess()})){
            PlayerData.getPlayerData(player.getName()).teleportSpawn();
            player.sendMessage("§7Ne vous éloignez pas trop");
        }

    }
}
