package me.vorps.hub.thread;

import me.vorps.hub.Hub;
import me.vorps.hub.events.ProjectileHitListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 26/05/2016 at 19:25.
 */
public class ThreadColor {
    public ThreadColor(HashMap<Location, ProjectileHitListener.Block> blockHashMap){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Hub.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Location location : blockHashMap.keySet()){
                    Bukkit.getWorlds().get(0).getBlockAt(location).setType(blockHashMap.get(location).getMaterial());
                    Bukkit.getWorlds().get(0).getBlockAt(location).setData(blockHashMap.get(location).getData());
                }
            }}, 200L);
    }
}
