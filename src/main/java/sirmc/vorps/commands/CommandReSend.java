package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandReSend {

    public static boolean CommandReSend(String args[], PlayerHub playerHub){
        if(args.length > 0){
            if(playerHub.getPlayerNameMessage() != null){
                if(Hub.instance.getPlayerHub().containsKey(playerHub.getPlayerNameMessage())){
                    String message = new String();
                    for(int i = 0; i < args.length; i++){
                        message += args[i];
                    }

                    Bukkit.getPlayer(playerHub.getPlayerNameMessage()).sendMessage("§5"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§f -> "+message);
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Message renvoyé a §a"+playerHub.getPlayerNameMessage()+".");
                    playerHub.setPlayerNameMessage(null);
                    return true;
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cLe joueur §a"+args[0]+"§c n'est pas en ligne.");
                }
            } else {
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cPersonne ne vous a envoyé de message privé.");
            }
        } else {
            AideCommande.AideReSend(playerHub);
        }
        return false;
    }
}
