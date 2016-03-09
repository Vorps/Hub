package me.vorps.hub;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import lombok.Getter;
import me.vorps.fortycube.scoreboard.ScoreBoard;
import me.vorps.hub.Object.Grades;
import me.vorps.hub.commands.*;
import me.vorps.hub.events.*;
import me.vorps.hub.thread.ThreadHub;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.scoreboard.DisplaySlot;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class Hub extends JavaPlugin{
	private @Getter static Hub instance;
	private final @Getter int NUMBER_HUB = 1;
	private final @Getter DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");
	private @Getter boolean interrupt;

	private @Getter ScoreBoard scoreBoard;

	@Override
	public void onEnable() {
        instance = this;
        EventsManager.registerEvents();
		Data.loadVariables();

        scoreBoard = new ScoreBoard(DisplaySlot.SIDEBAR, Settings.getNameServer());
		scoreBoard.add(1, "§bServeur",3);
        scoreBoard.add(2, "§eHun n°"+NUMBER_HUB, 2);
        scoreBoard.add(3, "", 1);
        Grades.gradeDisplayInit();
        Bukkit.getOnlinePlayers().forEach((Player player) -> new PlayerData(player));
        scoreBoard.add(4, "§aJoueur : "+PlayerData.nbrConnect(), 0);
		new ThreadHub().start();
	}
	
	@Override
	public void onDisable() {
		interrupt = true;
		Bukkit.getOnlinePlayers().forEach((Player player) -> PlayerData.getPlayerData(player.getName()).removePlayerHub(false));
	}

    @EventHandler
    public boolean onCommand(CommandSender sender , Command command, String label, String args[]){
        return CommandManager.onCommand(sender, label, args);
    }
}
