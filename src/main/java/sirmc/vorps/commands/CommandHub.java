package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandHub {
    
    public static boolean CommandHub(String args[], PlayerHub playerHub){
        if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.hub")){
            if(args.length == 0){
                Bukkit.getPlayer(playerHub.getPlayerName()).teleport(Hub.instance.getSpawnHub());
                return true;
            } else if(args.length == 1){
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cPas disponible.");
            } else {
                AideCommande.AideHub(playerHub);
            }
        }
        return false;
    }
}
