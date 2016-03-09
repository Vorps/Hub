package me.vorps.hub.commands;

import me.vorps.hub.Settings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class CommandHub extends Commands{
    
    public CommandHub(CommandSender sender, String args[]){
        super(sender, "fortycube.hub");
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0 && sender.hasPermission(getPermission()+".spawn")) {
                player.teleport(Settings.getSpawnHub());
                setStateExec(true);
            }
        }
    }

    protected void help(){
        if(!isStateExec()){
            if(getSender().hasPermission(getPermission()+".spawn")){
                getSender().sendMessage("§e✴--------------------- §aHelp hub§e ---------------------✴");
                getSender().sendMessage("§e/hub §f> §eVous téléport au spawn du hub");
                getSender().sendMessage("§e✴--------------------------------------------------✴");
            }
        }
    }
}
