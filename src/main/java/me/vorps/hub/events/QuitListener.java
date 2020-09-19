package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import net.vorps.api.objects.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        Rank rank = PlayerData.getRank(player.getUniqueId());
        e.setQuitMessage(rank.isVisibleRank() ? rank+" "+player.getName()+" §ea quitté le hub !" : "");
        PlayerData.getPlayerData(player.getUniqueId()).removePlayerHub();
    }
}
