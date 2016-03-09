package me.vorps.hub.events;

import me.vorps.hub.Hub;
import org.bukkit.Bukkit;

/**
 * Project Hub Created by Vorps on 06/03/2016 at 08:10.
 */
public class EventsManager {

    public static void registerEvents(){
        Hub plugin = Hub.getInstance();
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropPlayerListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new FlyOnJumpListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new InterractInventoryListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new WeatherListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new FoodChangeLevelListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new MoveListener(), plugin);
    }
}
