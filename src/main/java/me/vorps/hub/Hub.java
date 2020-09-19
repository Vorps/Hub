package me.vorps.hub;

import lombok.Getter;
import me.vorps.hub.commands.CommandManager;
import me.vorps.hub.data.SettingsHub;
import me.vorps.hub.events.*;
import net.vorps.api.API;
import net.vorps.api.data.DataCore;
import net.vorps.api.databases.Database;
import net.vorps.api.listeners.ListenerManager;
import net.vorps.api.message.ServerState;
import net.vorps.hub.channel.PluginMessageReceived;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class Hub extends JavaPlugin{
	private @Getter static Hub instance;

	@Override
	public void onEnable() {
        Hub.instance = this;
        API.setPlugin(this);
        DataCore.setDatabase(Database.HUB.getDatabase());
        SettingsHub.initSettings();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessageReceived());
        ServerState.setState(ServerState.WAITING);
        new ListenerManager(
                new CancelledEventListener(),
                new DamageListener(),
                new FlyOnJumpListener(),
                new InteractListener(),
                new InterractInventoryListener(),
                new MoveListener(),
                new PlayerInteractEntitiesListener(),
                new PlayerJoinListener(),
                new ProjectileHitListener(),
                new QuitListener(),
                new SignListener(),
                new SneakListener());
        CommandManager.init();

        Bukkit.getOnlinePlayers().forEach((Player player) -> new PlayerData(player.getUniqueId()));

        //new ThreadHub().start();
	}

    @Override
    public void onDisable() {
        //ServerState.setState(ServerState.STOP);
        Bukkit.getOnlinePlayers().forEach((Player player) -> PlayerData.getPlayerData(player.getName()).removePlayerHub());
    }
}
