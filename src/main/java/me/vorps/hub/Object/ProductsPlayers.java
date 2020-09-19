package me.vorps.hub.Object;

/**
 * Project Hub Created by Vorps on 06/03/2016 at 00:16.
 */
import lombok.Getter;
import net.vorps.api.databases.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsPlayers {

    private @Getter String product;
    private @Getter long date;
    private @Getter long time;

    public ProductsPlayers(ResultSet results, String namePlayer) throws SQLException {
        product = results.getString(2);
        date = results.getTimestamp(3).getTime();
        /*try {
            results = results.getData("product LEFT JOIN player_product ON product.product_name = player_product.pp_product" , "product.product_time > '"+0+"' && player_product.pp_player = '"+namePlayer+"' && product.product_name = '"+product+"'");
            if(results.next()){
                time = results.getTimestamp(11).getTime();
                System.out.println(time);
            }
        } catch (SQLException e){
            //
        }*/
    }
}