package me.vorps.hub.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Hub Created by Vorps on 14/03/2016 at 05:19.
 */
public class CommandsAutoCompletion implements TabCompleter {
    private Commands.Command command;

    public CommandsAutoCompletion(Commands.Command command){
        this.command = command;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
        List<String> matches = new ArrayList<>();
        String search;
        switch (command){
            case JUMP:
                if(args.length == 1 && sender.hasPermission(Commands.Command.JUMP.getPermissions()+".me")){
                    matches.add("end");
                }
                if(args.length == 2 && sender.hasPermission(Commands.Command.JUMP.getCommand()+".player")){
                    search = args[0].toLowerCase();
                    Bukkit.getOnlinePlayers().forEach((Player player) -> {
                        if(player.getName().toLowerCase().startsWith(search)){
                            matches.add(player.getName());
                        }
                    });
                }
                break;
            case RELOAD_HUB:
                break;
            default:
                if(args.length == 0 || args.length >= 3){
                    return new ArrayList<>();
                }
                boolean state = false;
                if(args.length == 1){
                    search = args[0].toLowerCase();
                    if(sender.hasPermission(command.getPermissions()+".me.on")){
                        matches.add("on");
                    }
                    if(sender.hasPermission(command.getPermissions()+".me.off")){
                        matches.add("off");
                    }
                    if(sender.hasPermission(command.getPermissions()+".player.on") || sender.hasPermission(command.getPermissions()+".player.off")){
                        state = true;
                    }
                } else {
                    search = args[1].toLowerCase();
                    if(args[0].equalsIgnoreCase("on") && sender.hasPermission(command.getPermissions()+".player.on")){
                        state = true;
                    }
                    if(args[0].equalsIgnoreCase("off") && sender.hasPermission(command.getPermissions()+".player.off")){
                        state = true;
                    }
                }
                if(state){
                    Bukkit.getOnlinePlayers().forEach((Player player) -> {
                        if(player.getName().toLowerCase().startsWith(search)){
                            matches.add(player.getName());
                        }
                    });
                }
                break;
        }
        return matches;
    }
}
