package me.vorps.hub;

import com.avaje.ebean.annotation.Sql;
import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.Object.Grades;
import me.vorps.hub.commands.*;
import me.vorps.hub.dispatcher.Parameter;
import me.vorps.hub.dispatcher.ServerParameter;
import me.vorps.hub.events.*;
import me.vorps.hub.scoreboard.ScoreBoard;
import me.vorps.hub.thread.ThreadHub;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class Hub extends JavaPlugin{
	private @Getter static Hub instance;
	private @Getter boolean interrupt;

	@Override
	public void onEnable() {
        instance = this;
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        EventsManager.registerEvents();
		Data.loadVariables();
        Grades.gradeDisplayInit();
        Bukkit.getOnlinePlayers().forEach((Player player) -> new PlayerData(player));
		new ThreadHub().start();
        new CommandManager();
        ServerParameter.serialisation(new Parameter(true));
	}
	
	@Override
	public void onDisable() {
		interrupt = true;
		Bukkit.getOnlinePlayers().forEach((Player player) -> PlayerData.getPlayerData(player.getName()).removePlayerHub(false));
        ServerParameter.serialisation(new Parameter(false));
	}

    @EventHandler
    public boolean onCommand(CommandSender sender , Command command, String label, String args[]){
        return CommandManager.onCommand(sender, label, args);
    }

}
