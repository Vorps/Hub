package sirmc.vorps;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vorps on 01/02/2016.
 */
public class ProductsPlayers {

    private @Getter String product;
    private @Getter long date;
    private @Getter long time = 0;

    public ProductsPlayers(ResultSet resultats, PlayerHub playerHub) throws SQLException {
        product = resultats.getString(2);
        date = resultats.getTimestamp(3).getTime();
        resultats = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Produits LEFT JOIN PlayerProduit ON Produits.produit = PlayerProduit.produit WHERE Produits.time > '"+0+"' && PlayerProduit.namePlayer = '"+playerHub.getPlayerName()+"' && Produits.produit = '"+product+"'");
        if(resultats.next()){
            time = resultats.getTimestamp(9).getTime();
        }
    }
}
