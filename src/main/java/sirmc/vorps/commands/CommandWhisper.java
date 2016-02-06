package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandWhisper {

    public static boolean CommandWhisper(String args[], PlayerHub playerHub){
        if(args.length >= 2){
            if(Hub.instance.getPlayerHub().containsKey(args[0])){
                if(!Bukkit.getPlayer(playerHub.getPlayerName()).getName().equals(args[0])){
                    String message = new String();
                    for(int i = 1; i < args.length; i++){
                        message += args[i];
                    }
                    Hub.instance.getPlayerHub().get(args[0]).setPlayerNameMessage(playerHub.getPlayerName());
                    Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[0]).getPlayerName()).sendMessage("§5"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§f -> "+message);
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Message envoy§ a §a"+args[0]+".");
                    return true;
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cSkyzophrène ?");
                }
            } else {
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cLe joueur §a"+args[0]+"§c n'est pas en ligne.");
            }
        } else {
            AideCommande.AideWhisper(playerHub);
        }
        return false;
    }
}
