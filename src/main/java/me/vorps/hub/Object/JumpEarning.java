package me.vorps.hub.Object;

import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project Hub Created by Vorps on 21/05/2016 at 16:42.
 */
public class JumpEarning {

    private String products;
    private double value;
    private String device;

    public JumpEarning(ResultSet resultSet) throws SQLException{
        products = resultSet.getString( 3);
        value = resultSet.getDouble( 4);
        device = resultSet.getString( 5);
    }

    public void getEarning(Player player){
        /*if(products != null){
            try {
                ResultSet results = Database.CORE.getDatabase().getData("player_product", "pp_player = '" + player.getName() + "' && pp_product = '" + products + "'");
                if(!results.next()){
                    Database.CORE.getDatabase().insertTable("player_product", player.getName(), products, new Timestamp(System.currentTimeMillis()));
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
                Database.CORE.getDatabase().updateTable("player_money", "pm_player = '"+player.getName()+"' && pm_money = '"+device+"'", new DatabaseManager.Values("pm_value", PlayerData.getPlayerData(player.getName()).getMoney().get(device)+value));
                PlayerData.getPlayerData(player.getName()).getMoneyFunction();
                Money money = Money.getMoney(device);
                player.sendMessage("§7Vous avez gagné "+money.getColor()+value+" "+money.getAlias());
            } catch (SqlException e){
                e.printStackTrace();
            }
        }*/
    }
}
