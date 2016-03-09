package me.vorps.hub.commands;

import me.vorps.hub.Data;
import org.bukkit.command.CommandSender;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class CommandReload extends Commands{

    public CommandReload(CommandSender sender, String args[]){
        super(sender, "fortycube.reload");
        if(args.length == 0 && sender.hasPermission(getPermission()+".plugin")){
            Data.LoadVariables();
            sender.sendMessage("§aReload effectué");
        }
    }
    @Override
    protected void help(){
        if(getSender().hasPermission(getPermission()+".plugin")){
            getSender().sendMessage("§e✴------------------- §aHelp reload§e --------------------✴");
            getSender().sendMessage("§e/reload §f> §eReload les variables du plugin hub");
            getSender().sendMessage("§e✴--------------------------------------------------✴");
        }
    }
}
