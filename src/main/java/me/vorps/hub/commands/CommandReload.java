package me.vorps.hub.commands;

import me.vorps.hub.Data;
import org.bukkit.command.CommandSender;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class CommandReload extends Commands{

    public CommandReload(CommandSender sender, String args[]){
        super(sender, Command.RELOAD_HUB.getPermissions());
        if(args.length == 0 && sender.hasPermission(getPermission()+".plugin")){
            Data.loadVariables();
            sender.sendMessage("§aReload effectué");
        }
    }
    @Override
    protected void help(){
        if(getSender().hasPermission(getPermission())){
            getSender().sendMessage("§e✴------------------- §aHelp reload§e --------------------✴");
            getSender().sendMessage("§e/reload §f> §eReload les variables du plugin hub");
            getSender().sendMessage("§e✴--------------------------------------------------✴");
        }
    }
}
