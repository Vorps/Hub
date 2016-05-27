package me.vorps.hub.commands;

import me.vorps.hub.Hub;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

/**
 * Project Hub Created by Vorps on 06/03/2016 at 07:57.
 */
public class CommandManager {

    public CommandManager(){
        Hub plugin = Hub.getInstance();
        plugin.getCommand(Commands.Command.BUILD.getCommand()).setTabCompleter(new CommandsAutoCompletion(Commands.Command.BUILD));
        plugin.getCommand(Commands.Command.DOUBLE_JUMP.getCommand()).setTabCompleter(new CommandsAutoCompletion(Commands.Command.DOUBLE_JUMP));
        plugin.getCommand(Commands.Command.FLY.getCommand()).setTabCompleter(new CommandsAutoCompletion(Commands.Command.FLY));
        plugin.getCommand(Commands.Command.VISIBLE_PLAYER.getCommand()).setTabCompleter(new CommandsAutoCompletion(Commands.Command.VISIBLE_PLAYER));
        plugin.getCommand(Commands.Command.JUMP.getCommand()).setTabCompleter(new CommandsAutoCompletion(Commands.Command.JUMP));
        plugin.getCommand(Commands.Command.RELOAD_HUB.getCommand()).setTabCompleter(new CommandsAutoCompletion(Commands.Command.RELOAD_HUB));
        plugin.getCommand(Commands.Command.SETTING.getCommand()).setTabCompleter(new CommandsAutoCompletion(Commands.Command.SETTING));
    }

    @EventHandler
    public static boolean onCommand(CommandSender sender, String label, String args[]){
        if(label.equalsIgnoreCase(Commands.Command.BUILD.getCommand())){
            return new CommandBuild(sender, args).isStateExec();
        } else if(label.equalsIgnoreCase(Commands.Command.FLY.getCommand())){
            return new CommandFly(sender, args).isStateExec();
        } else if(label.equalsIgnoreCase(Commands.Command.DOUBLE_JUMP.getCommand())){
            return new CommandDoubleJumps(sender, args).isStateExec();
        } else if(label.equalsIgnoreCase(Commands.Command.VISIBLE_PLAYER.getCommand())){
            return new CommandVisiblePlayer(sender, args).isStateExec();
        } else if(label.equalsIgnoreCase(Commands.Command.RELOAD_HUB.getCommand())){
            return new CommandReload(sender, args).isStateExec();
        } else if(label.equalsIgnoreCase(Commands.Command.JUMP.getCommand())){
            return new CommandJump(sender, args).isStateExec();
        } else if(label.equalsIgnoreCase(Commands.Command.SETTING.getCommand())){
            return new CommandSetting(sender, args).isStateExec();
        }
        return false;
    }
}
