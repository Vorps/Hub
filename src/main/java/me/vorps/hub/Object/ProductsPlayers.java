package me.vorps.hub.Object;

/**
 * Project Hub Created by Vorps on 06/03/2016 at 00:16.
 */
import lombok.Getter;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsPlayers {

    private @Getter String product;
    private @Getter long date;
    private @Getter long time = 0;

    public ProductsPlayers(ResultSet results, String namePlayer) throws SqlException {
        product = Database.FORTYCUBE.getDatabase().getString(results, 2);
        date = Database.FORTYCUBE.getDatabase().getTimestamp(results, 3).getTime();
        try {
            results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM product LEFT JOIN player_product ON product.product_name = player_product.pp_product WHERE product.product_time > '"+0+"' && player_product.pp_player = '"+namePlayer+"' && product.product_name = '"+product+"'");
            if(results.next()){
                time = Database.FORTYCUBE.getDatabase().getTimestamp(results, 9).getTime();
            }
        } catch (SQLException e){
            //
        }
    }
}