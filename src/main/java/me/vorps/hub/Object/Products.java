package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Products {
    private static HashMap<String, Products> listProducts = new HashMap<>();

    private @Getter double price;
    private @Getter String money;
    private @Getter long time;
    private @Getter int type;
    private @Getter ArrayList<String> productsGrades;

    public static Products getProduct(String nameProduct){
            return listProducts.get(nameProduct);
    }

    public static boolean isExits(String nameProduct){
        return listProducts.containsKey(nameProduct);
    }

    public boolean accessProductGrade(String nameGrade){
        return productsGrades.contains(nameGrade);
    }

    public boolean isProductGrade(){
        System.out.println(productsGrades);
        return productsGrades.size() > 0;
    }

    public Products(ResultSet results, ArrayList<String> productsGrade) throws SqlException {
        this.productsGrades = new ArrayList<>();
        price = Database.FORTYCUBE.getDatabase().getDouble(results, 2);
        money = Database.FORTYCUBE.getDatabase().getString(results, 3);
        time = Database.FORTYCUBE.getDatabase().getLong(results, 4);
        type = Database.FORTYCUBE.getDatabase().getInt(results, 5);
        System.out.println(Database.FORTYCUBE.getDatabase().getString(results, 1));
        System.out.println(productsGrade);

        this.productsGrades = productsGrade;
        System.out.println(productsGrades);
        listProducts.put(Database.FORTYCUBE.getDatabase().getString(results, 1), this);
    }
}
