package me.vorps.hub.commands;

import me.vorps.fortycube.Exceptions.SqlException;
import lombok.Getter;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.Data;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 02/03/2016 at 21:03.
 */
public abstract class CommandsAction extends Commands{

    private @Getter PlayerData playerData;
    private String name;
    private @Getter Player player;

    public CommandsAction(CommandSender sender, String[] args, String name, String permission) {
        super(sender, permission);
        this.name = name;
        if (args.length == 0 && sender instanceof Player) {
            player = (Player) sender;
            playerData = PlayerData.getPlayerData(player.getName());
            if(stateOnlineFunction()){
                if (sender.hasPermission(permission + ".me.off")) {
                    disableFunction();
                }
            } else {
                if (sender.hasPermission(permission + ".me.on")) {
                   enableFunction();
                }
            }
        } else if (args.length == 1){
            if(sender instanceof Player){
                player = (Player) sender;
                playerData = PlayerData.getPlayerData(player.getName());
                if (args[0].equalsIgnoreCase("on")) {
                    if(sender.hasPermission(permission + ".me.on")){
                        enableFunction();
                    }
                } else if (args[0].equalsIgnoreCase("off")) {
                    if (sender.hasPermission(permission + ".me.off")) {
                        disableFunction();
                    }
                }
            }
            if(!isStateExec()){
                if (PlayerData.isPlayerDataExits(args[0])) {
                    player = Bukkit.getPlayer(args[0]);
                    playerData = PlayerData.getPlayerData(args[0]);
                    if(stateOnlineFunction()) {
                        if (sender.hasPermission(permission + ".player.off")) {
                            disablePlayerFunction(args[0]);
                        }
                    } else {
                        if (sender.hasPermission(permission + ".player.off")) {
                            enablePlayerFunction(args[0]);
                        }
                    }
                } else if (Data.isPlayerExists(args[0])){
                    if(stateOfflineFunction(args[0])) {
                        if (sender.hasPermission(permission + ".player.off")) {
                            disablePlayerOfflineFunction(args[0]);
                        }
                    } else {
                        if (sender.hasPermission(permission + ".player.on")) {
                            enablePlayerOfflineFunction(args[0]);
                        }
                    }
                } else {
                    sender.sendMessage("§e" + args[0] + "§c ne s'est jamais connecté sur ce serveur.");
                }
            }
        } else if (args.length == 2){
            if (PlayerData.isPlayerDataExits(args[1])){
                player = Bukkit.getPlayer(args[1]);
                playerData = PlayerData.getPlayerData(args[1]);
                if (args[0].equalsIgnoreCase("on")) {
                    if (sender.hasPermission(permission + ".player.on")) {
                        enablePlayerFunction(args[1]);
                    }
                } else if (args[0].equalsIgnoreCase("off")) {
                    if (sender.hasPermission(permission + ".player.off")) {
                        disablePlayerFunction(args[1]);
                    }
                }
            } else if (Data.isPlayerExists(args[1])){
                if (args[0].equalsIgnoreCase("on")) {
                    if (sender.hasPermission(permission + ".player.on")) {
                        enablePlayerOfflineFunction(args[1]);
                    }
                } else if (args[0].equalsIgnoreCase("off")) {
                    if (sender.hasPermission(permission + ".player.off")) {
                        disablePlayerOfflineFunction(args[1]);
                    }
                }
            } else {
                if(sender.hasPermission(permission + ".player.on") || (sender.hasPermission(permission + ".player.off"))){
                    sender.sendMessage("§e" + args[1] + "§c ne s'est jamais connecté sur ce serveur.");
                }
            }
        }
        if (!isStateExec()) {
            help();
        }
    }

    private void enableFunction(){
        if(setOnlineFunction(true)){
            getSender().sendMessage("§6" + name + " (§aActivé§6).");
        }
    }

    private void disableFunction(){
        if(setOnlineFunction(false)){
            getSender().sendMessage("§6" + name + " (§4Désactivé§6).");
        }
    }

    private void enablePlayerOfflineFunction(String namePlayer){
        setOfflineFunction((byte) 1,namePlayer);
        getSender().sendMessage("§6" + name + " (§aActivé§6) pour §a" +namePlayer + "§6.");
        try {
            String notification = "✴§a"+namePlayer+"§e vous à activé le §a"+name+"§e.";
            Database.FORTYCUBE.getDatabase().sendDatabase("INSERT INTO notification VALUES ('"+namePlayer+"', '"+notification+"')");
        } catch (SqlException e){
            e.printStackTrace();
        }
    }

    private void disablePlayerOfflineFunction(String namePlayer){
        setOfflineFunction((byte) 0,namePlayer);
        getSender().sendMessage("§6" + name + " (§4Désactivé§6) pour §a" +namePlayer + "§6.");
        try {
            String notification = "✴§a"+namePlayer+"§e vous à désactivé le §a"+name+"§e.";
            Database.FORTYCUBE.getDatabase().sendDatabase("INSERT INTO notification VALUES ('"+namePlayer+"', '"+notification+"')");
        } catch (SqlException e){
            e.printStackTrace();
        }
    }

    private void enablePlayerFunction(String namePlayer){
        if(setOnlineFunction(true)){
            if(!player.getName().equals(getSender().getName())){
                getSender().sendMessage("§6" + name + " (§aActivé§6) pour §a" +namePlayer + "§6.");
                Bukkit.getPlayer(namePlayer).sendMessage("§6" + name + " (§aActivé§6) par §a" + getSender().getName() + "§6.");
            } else{
                getSender().sendMessage("§6" + name + " (§aActivé§6).");
            }
        }
    }

    private void disablePlayerFunction(String namePlayer){
        if(setOnlineFunction(false)){
            if(!player.getName().equals(getSender().getName())){
                getSender().sendMessage("§6" + name + " (§4Désactivé§6) pour §a" +namePlayer + "§6.");
                Bukkit.getPlayer(namePlayer).sendMessage("§6" + name + " (§4Désactivé§6) par §a" + getSender().getName() + "§6.");
            } else {
                getSender().sendMessage("§6" + name + " (§4Désactivé§6).");
            }
        }
    }

    abstract boolean stateOnlineFunction();

    abstract boolean stateOfflineFunction(String namePlayer);

    abstract boolean setOnlineFunction(boolean state);

    abstract void setOfflineFunction(byte state, String namePlayer);
}
