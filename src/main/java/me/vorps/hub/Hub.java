package me.vorps.hub;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Vorps on 26/01/2016.
 */
public class Hub extends JavaPlugin {
    public static Hub instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }

}
