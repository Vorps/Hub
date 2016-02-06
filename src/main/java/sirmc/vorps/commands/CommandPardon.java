package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.LoadVariable;
import sirmc.vorps.PlayerHub;

import java.util.ArrayList;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandPardon {

    public static boolean CommandPardon(ArrayList<String> args, PlayerHub playerHub){
        LoadVariable.getListBan();
        if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.pardon")){
            if(args.size() == 1){
                if(Hub.instance.getListBan().contains(args.get(0))){
                    Hub.instance.database.SendDatabase("DELETE FROM Ban WHERE namePlayer = '"+args.get(0)+"'");
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+"§e a bien été débannie.");
                    return true;
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args.get(0)+"§c n'est pas bannie.");
                }
            } else {
                AideCommande.AidePardon(playerHub);
            }
        }
        return false;
    }
}
