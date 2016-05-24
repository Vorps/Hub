package me.vorps.hub.Object;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.PlayerData;
import me.vorps.hub.utils.GiveProductPlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Project Hub Created by Vorps on 21/05/2016 at 16:42.
 */
public class JumpEarning {

    private String products;
    private double value;
    private String device;

    public JumpEarning(ResultSet resultSet) throws SqlException{
        products = Database.FORTYCUBE.getDatabase().getString(resultSet, 3);
        value = Database.FORTYCUBE.getDatabase().getDouble(resultSet, 4);
        device = Database.FORTYCUBE.getDatabase().getString(resultSet, 5);
    }

    public void getEarning(Player player){
        if(products != null){
            try {
                ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_product WHERE pp_player = '" + player.getName() + "' && pp_product = '" + products + "'");
                if(!results.next()){
                    Database.FORTYCUBE.getDatabase().sendDatabase("INSERT INTO player_product VALUES ('"+player.getName()+"', '"+products+"', '"+new Timestamp(System.currentTimeMillis())+"')");
                    PlayerData.getPlayerData(player.getName()).getProductsPlayerFunction();
                }
            } catch (SqlException e){
                e.printStackTrace();
            } catch (SQLException e){
                //
            }
            GiveProductPlayer.giveItemPlayer(player, Products.getProduct(this.products), false);
        }
        if(device != null){
            try {
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_money SET pm_value = '"+(PlayerData.getPlayerData(player.getName()).getMoney().get(device)+value)+"' WHERE pm_player = '"+player.getName()+"' && pm_money = '"+device+"'");
                PlayerData.getPlayerData(player.getName()).getMoneyFunction();
                Money money = Money.getMoney(device);
                player.sendMessage("§7Vous avez gagné "+money.getColor()+value+" "+money.getAlias());
            } catch (SqlException e){
                e.printStackTrace();
            }
        }
    }
}
