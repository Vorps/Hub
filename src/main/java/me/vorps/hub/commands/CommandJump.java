package me.vorps.hub.commands;

import me.vorps.hub.PlayerData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 03/03/2016 at 02:27.
 */
public class CommandJump extends Commands{

    public CommandJump(CommandSender sender, String args[]){
        super(sender, Command.JUMP.getPermissions());
        if(args.length == 1 && sender instanceof Player && sender.hasPermission(getPermission()+".me")){
            if(args[0].equalsIgnoreCase("end")){
                Player player = (Player) sender;
                PlayerData playerData = PlayerData.getPlayerData(player.getName());
                if(playerData.getJump().isInJump()){
                    playerData.getJump().stopJump((Player) sender, true);
                }
            }
        } else if(args.length == 2 && sender.hasPermission(getPermission()+".player")){
            if(args[0].equalsIgnoreCase("end")){
                if(PlayerData.isPlayerDataExits(args[1])){
                    PlayerData playerData = PlayerData.getPlayerData(args[1]);
                    if(playerData.getJump().isInJump()){
                        playerData.getJump().stopJump((Player) sender, true);
                        sender.sendMessage("§aFin du jump pour le joueur "+args[1]+".");
                    } else {
                        sender.sendMessage("§cLe joueur §a"+args[1]+"§c n'est ne joue pas au jump.");
                    }
                } else {
                    sender.sendMessage("§cLe joueur §a"+args[1]+"§c n'est pas en ligne.");
                }
            }
        }
    }

    @Override
    protected void help(){
        ArrayList<String> messages = new ArrayList<>();
        if(getSender().hasPermission(getPermission()+".me") && getSender() instanceof Player){
            messages.add("§a/jump end §f> §eFin du jump");
        }
        if(getSender().hasPermission(getPermission()+".player")){
            messages.add("§a/jump end §e<Joueur> §f> §eFin du jump pour le joueur");
        }
        getSender().sendMessage("§e✴-------------------- §aHelp jump§e --------------------✴");
        messages.forEach((String message) -> getSender().sendMessage(message));
        getSender().sendMessage("§e✴--------------------------------------------------✴");
    }
}
