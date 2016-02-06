package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.events.WeatherListener;
import sirmc.vorps.menu.Navigator;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandBuild {

    public static boolean CommandBuild(String args[], PlayerHub playerHub){
        if(args.length == 0){
            if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.build.me")){
                if(playerHub.isBuild()){
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Mode build (§4Désactivé§6).");
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().clear();
                    Navigator.HubNavigator(playerHub);
                    Bukkit.getPlayer(playerHub.getPlayerName()).setGameMode(GameMode.ADVENTURE);
                    playerHub.setBuild(false);
                    if(playerHub.isDoubleJumps()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(true);
                    }
                    return true;
                } else {
                    if(!playerHub.isInJumps()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Mode build (§2Activé§6).");
                        Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().clear();
                        Bukkit.getPlayer(playerHub.getPlayerName()).setGameMode(GameMode.CREATIVE);
                        Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                        playerHub.setBuild(true);
                        return true;
                    }
                }
            }
        } else if(args.length == 1){
            if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.build.player")){
                if(Hub.instance.getPlayerHub().containsKey(args[0])){
                    if(Hub.instance.getPlayerHub().get(args[0]).isBuild()){
                        Hub.instance.getPlayerHub().get(args[0]).setBuild(false);
                        Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§6Mode build (§4Désactivé§6).");
                        Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).getInventory().clear();
                        Navigator.HubNavigator(Hub.instance.getPlayerHub().get(args[0]));
                        Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).setGameMode(GameMode.ADVENTURE);
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Mode build (§4Désactivé§6) pour le joueur §a"+Hub.instance.getPlayerHub().get(args[0]).getPlayerName()+"§6.");
                        Hub.instance.getPlayerHub().get(args[0]).setBuild(false);
                        if(Hub.instance.getPlayerHub().get(args[0]).isDoubleJumps()){
                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).setAllowFlight(true);
                        }
                        return true;
                    } else {
                        if(!Hub.instance.getPlayerHub().get(args[0]).isInJumps()){
                            Hub.instance.getPlayerHub().get(args[0]).setBuild(true);
                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§6Mode build (§2Activé§6).");
                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).getInventory().clear();
                            Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).setGameMode(GameMode.CREATIVE);
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Mode build (§2Activé§6) pour le joueur §a"+Hub.instance.getPlayerHub().get(args[0]).getPlayerName()+"§6.");
                            Hub.instance.getPlayerHub().get(args[0]).setBuild(true);
                            return true;
                        }
                    }
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cLe joueur n'est pas en ligne.");
                }
            }
        } else {
            AideCommande.AideBuild(playerHub);
        }
        return false;
    }
}
