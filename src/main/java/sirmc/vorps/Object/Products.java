package sirmc.vorps.Object;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vorps on 01/02/2016.
 */
public class Products {
    private @Getter double price;
    private @Getter String money;
    private @Getter long time;
    private @Getter int type;
    private String comments;

    public Products(ResultSet results) throws SQLException{
        price = results.getDouble(2);
        money = results.getString(3);
        time = results.getLong(4);
        type = results.getInt(5);
        comments = results.getString(6);
    }
}
