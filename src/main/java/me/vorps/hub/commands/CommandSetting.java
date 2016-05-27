package me.vorps.hub.commands;

import me.vorps.hub.menu.MenuSettings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 25/05/2016 at 22:12.
 */
public class CommandSetting extends Commands{

    public CommandSetting(CommandSender sender, String args[]) {
        super(sender, Command.JUMP.getPermissions());
        if(sender instanceof Player){
            if(args.length == 0){
                new MenuSettings((Player) sender, true);
                setStateExec(true);
            }
        }
    }

    @Override
    protected void help(){
        if(getSender().hasPermission(getPermission()+".me") && getSender() instanceof Player){
            getSender().sendMessage("§e✴----------------- §aHelp Settings§e ------------------✴");
            getSender().sendMessage("§e✴§a/settings §f> Ouvre le menu des parametre");
            getSender().sendMessage("§e✴--------------------------------------------------✴");
        }
    }
}
