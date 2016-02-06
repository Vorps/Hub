package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandFly {
    public static boolean CommandFly(String args[], PlayerHub playerHub){
        if(args.length == 0){
            if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.fly.me")){
                if(playerHub.isFly()){
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Fly (§4Désactivé§6).");
                    Bukkit.getPlayer(playerHub.getPlayerName()).setFlying(false);
                    if(!playerHub.isBuild() || Bukkit.getPlayer(playerHub.getPlayerName()).getGameMode() != GameMode.CREATIVE){
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(false);
                    }
                    playerHub.setFly(false);
                } else {
                    if(!playerHub.isInJumps()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Fly (§aActivé§6).");
                        if(playerHub.isDoubleJumps()){
                            CommandDoubleJumps.CommandDoubleJumps(new String[] {}, playerHub);
                        }
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(true);
                        playerHub.setFly(true);
                    }
                }
                return true;
            }
        } else if(args.length == 1){
            if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.fly.player")){
                if(Hub.instance.getPlayerHub().containsKey(args[0])){
                    if(Hub.instance.getPlayerHub().get(args[0]).isFly()){
                        Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§6Fly (§4Désactivé§6).");
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Fly (§4Désactivé§6) pour le joueur §a"+args[0]+"§6.");
                        Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).setFlying(false);
                        if(!Hub.instance.getPlayerHub().get(args[0]).isBuild() || Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).getGameMode() != GameMode.CREATIVE){
                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).setAllowFlight(false);
                        }
                        Hub.instance.getPlayerHub().get(args[0]).setFly(false);
                    } else {
                        if(!Hub.instance.getPlayerHub().get(args[0]).isInJumps()){
                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§6Fly (§aActivé§6).");
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Fly (§aActivé§6) pour le joueur §a"+args[0]+"§6.");
                            if(Hub.instance.getPlayerHub().get(args[0]).isDoubleJumps()){
                                Hub.instance.getPlayerHub().get(args[0]).setDoubleJumps(false);
                            }
                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).setAllowFlight(true);
                            Hub.instance.getPlayerHub().get(args[0]).setFly(true);
                        }
                    }
                    return true;
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cCe joueur n'est pas en ligne.");
                }
            }
        } else {
            AideCommande.AideFly(playerHub);
        }
        return false;
    }

}
