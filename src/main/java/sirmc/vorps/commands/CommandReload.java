package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.LoadVariable;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandReload {

    public static boolean CommandHubReload(String args[], PlayerHub playerHub , Hub plugin){
        if(args.length == 0){
            LoadVariable.LoadVariables(plugin);
            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§aReload effectué");
        } else {
            AideCommande.AideHubReload(playerHub);
        }
        return false;
    }
}
