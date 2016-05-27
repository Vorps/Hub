package me.vorps.hub.thread;

import me.vorps.hub.Hub;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 12/05/2016 at 02:31.
 */
public class ThreadCoolDownDoubleJump extends Thread {

    public ThreadCoolDownDoubleJump(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Hub.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (playerData.isDoubleJumps()) {
                    player.setAllowFlight(true);
                }
            }
        }, Settings.getCoolDownDoubleJump()*20L);
    }
}
