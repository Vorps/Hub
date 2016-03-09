package me.vorps.hub.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

/**
 * Project Hub Created by Vorps on 06/03/2016 at 07:57.
 */
public class CommandManager {

    @EventHandler
    public static boolean onCommand(CommandSender sender, String label, String args[]){
        switch (label){
            case "build":
                return new CommandBuild(sender, args).isStateExec();
            case "fly":
                return new CommandFly(sender, args).isStateExec();
            case "hub":
                return new CommandHub(sender, args).isStateExec();
            case "soublejump":
                return new CommandDoubleJumps(sender, args).isStateExec();
            case "joueur":
                return new CommandVisiblePlayer(sender, args).isStateExec();
            case "reloadHub":
                return new CommandReload(sender, args).isStateExec();
            case "jump":
                return new CommandJump(sender, args).isStateExec();
            default:
                return false;
        }
    }
}
