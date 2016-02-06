package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandDoubleJumps {

    public static boolean CommandDoubleJumps(String args[], PlayerHub playerHub){
        if(args.length == 0){
            if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.jump.me")){
                if(playerHub.isDoubleJumps()){
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Double Jump (§4Désactivé§6).");
                    if(!playerHub.isBuild() || Bukkit.getPlayer(playerHub.getPlayerName()).getGameMode() != GameMode.CREATIVE){
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(false);
                    }
                    playerHub.setDoubleJumps(false);
                } else {
                    if(!playerHub.isInJumps()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Double Jump (§aActivé§6).");
                        if(playerHub.isFly()){
                            CommandFly.CommandFly(new String[] {}, playerHub);
                        }
                        Bukkit.getPlayer(playerHub.getPlayerName()).setFlying(false);
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(true);
                        playerHub.setNbrDoubleJumps(0);
                        playerHub.setDoubleJumps(true);
                    }
                }
                return true;
            }
        } else if(args.length == 1){
            if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.jump.player")){
                if(Hub.instance.getPlayerHub().get(args[0]).isDoubleJumps()){
                    Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§6Double Jump (§4Désactivé6).");
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Mode jump (§4Désactivé§6) pour le joueur §a"+Hub.instance.getPlayerHub().get(args[0]).getPlayerName()+"§6.");
                    if(!Hub.instance.getPlayerHub().get(args[0]).isBuild() ||Bukkit.getPlayer(playerHub.getPlayerName()).getGameMode() != GameMode.CREATIVE){
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(false);
                    }
                    Hub.instance.getPlayerHub().get(args[0]).setDoubleJumps(false);
                } else {
                    if(Hub.instance.getPlayerHub().get(args[0]).isInJumps()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Double Jump (§aActivé§6).");
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Mode jump (§aActivé§6) pour le joueur §a"+Hub.instance.getPlayerHub().get(args[0]).getPlayerName()+"§6.");
                        Bukkit.getPlayer(playerHub.getPlayerName()).setFlying(false);
                        if(Hub.instance.getPlayerHub().get(args[0]).isFly()){
                            Hub.instance.getPlayerHub().get(args[0]).setFly(false);
                        }
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(true);
                        Hub.instance.getPlayerHub().get(args[0]).setNbrDoubleJumps(0);
                        Hub.instance.getPlayerHub().get(args[0]).setDoubleJumps(true);
                    }
                }
                return true;
            }
        } else {
            AideCommande.AideJumps(playerHub);
        }
        return false;
    }
}
