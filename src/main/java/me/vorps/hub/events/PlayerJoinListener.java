package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import net.vorps.api.databases.Database;
import net.vorps.api.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class PlayerJoinListener implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        UUID uuid = e.getPlayer().getUniqueId();

        PlayerData.updatePlayerDataDataBase(uuid);

        PlayerData playerData =  new PlayerData(uuid);

        Rank rank = PlayerData.getRank(uuid);
        e.setJoinMessage(rank.isVisibleRank() ? rank.getColorRank()+rank.getRankDisplay()+" "+playerData+" §ea rejoint le hub !" : "");

    }
}
