package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.Object.Money;
import sirmc.vorps.PlayerHub;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandMoney {

    public static boolean CommandMoney(String args[], PlayerHub playerHub){
        if(args.length >= 3){
            ResultSet results;
            boolean state = false;
            for(Money money : Hub.instance.getListMoney()){
                if(money.getMoney().equals(args[1])){
                    state = true;;
                }
            }
            if(!state){
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cLa joueur : §a"+args[1]+"§c n'existe pas.");
                return false;
            }
            double value = 0;
            if(Hub.instance.getListPlayer().contains(args[2])){
                try {
                    results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM PlayerMoney WHERE namePlayer = '"+args[2]+"' && money = '"+args[1]+"'");
                    results.next();
                    value = results.getDouble(3);
                } catch (SQLException e1) {
                }
                if(args[0].equalsIgnoreCase("get") && args.length == 3){
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eLe montant en : §a"+args[1]+"§e du joueur : §a"+args[2]+" §eest de : §a"+value+"§e.");
                } else if(args[0].equalsIgnoreCase("set") && args.length == 4){
                    try {
                        int amount = Integer.parseInt(args[3]);
                        Hub.instance.database.SendDatabase("UPDATE PlayerMoney SET value = '"+amount+"' WHERE namePlayer = '"+args[2]+"' && money = '"+args[1]+"'");
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez set le montant de : §a"+args[1]+"§e a : §a"+amount+"§e au joueur : §a"+args[2]+"§e.");
                    } catch (Exception e) {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cErreur montant invalide.");
                    }
                } else if(args[0].equalsIgnoreCase("add") && args.length == 4){
                    try {
                        int amount = Integer.parseInt(args[3]);
                        Hub.instance.database.SendDatabase("UPDATE PlayerMoney SET value = '"+(value+amount)+"' WHERE namePlayer = '"+args[2]+"' && money = '"+args[1]+"'");
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez ajouté : §a"+amount+"§e de : §a"+args[1]+"§e au joueur : §a"+args[2]+"§e.");
                    } catch (Exception e) {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cErreur montant invalide.");
                    }
                } else if(args[0].equalsIgnoreCase("remove") && args.length == 4){
                    try {
                        int amount = Integer.parseInt(args[3]);
                        Hub.instance.database.SendDatabase("UPDATE PlayerMoney SET value = '"+(value-amount)+"' WHERE namePlayer = '"+args[2]+"' && money = '"+args[1]+"'");
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez enlevé : §a"+amount+"§e de : §a"+args[1]+"§e au joueur : §a"+args[2]+"§e.");
                    } catch (Exception e) {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cErreur montant invalide.");
                    }
                } else {
                    AideCommande.AideMoney(playerHub);
                }
            } else {
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cLe joueur §a"+args[0]+"§c n'existe pas.");
            }
        } else {
            AideCommande.AideMoney(playerHub);
        }
        return false;
    }
}
