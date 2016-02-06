package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandVisiblePlayer {

    public static boolean CommandVisiblePlayer(String args[], PlayerHub playerHub){
        if(args.length == 0){
            if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.joueur.me")){
                if(playerHub.isVisiblePlayer()){
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Joueur (§4Désactivé§6).");
                    Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPlayer(playerHub.getPlayerName()).hidePlayer(p));
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6joueur (§aActivé§6).");
                    Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPlayer(playerHub.getPlayerName()).showPlayer(p));
                }
                playerHub.setVisiblePlayer(!playerHub.isVisiblePlayer());
            }
            return true;
        } else if(args.length == 0){
            if(Hub.instance.getPlayerHub().containsKey(args[0])){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.joueur.player")){
                    if(Hub.instance.getPlayerHub().get(args[0]).isVisiblePlayer()){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Joueur (§4Désactivé§6) pour le joueur §a"+Hub.instance.getPlayerHub().get(args[0]).getPlayerName()+"§6.");
                        Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§6Joueur (§4Désactivé§6).");
                        Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).hidePlayer(p));
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Joueur ((§aActivé§6) pour le joueur §a"+Hub.instance.getPlayerHub().get(args[0]).getPlayerName()+"§6.");
                        Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§6joueur (§aActivé§6).");
                        Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).showPlayer(p));
                    }
                    Hub.instance.getPlayerHub().get(args[0]).setVisiblePlayer(!Hub.instance.getPlayerHub().get(args[0]).isVisiblePlayer());
                    return true;
                }
            } else {
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cLe joueur §a"+args[0]+"§c n'est pas en ligne.");
            }
        } else {
            AideCommande.AideVisibleJoueur(playerHub);
        }
        return false;
    }
}
