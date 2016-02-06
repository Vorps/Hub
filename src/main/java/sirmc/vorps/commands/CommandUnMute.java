package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.LoadVariable;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandUnMute {

    public static boolean CommandUnMute(String args[], PlayerHub playerHub){
        if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.unmute")){
            if(args.length == 1){
                if(Hub.instance.getListMute().contains(args[0])){
                    Hub.instance.database.SendDatabase("DELETE FROM Mute WHERE namePlayer = '"+args[0]+"'");
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+"§e a bien été unmute.");
                    LoadVariable.getListMute();
                    return true;
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[0]+"§c n'est pas mute.");
                }
            } else {
                AideCommande.AideUnmute(playerHub);
            }
        }
        return false;
    }
}
