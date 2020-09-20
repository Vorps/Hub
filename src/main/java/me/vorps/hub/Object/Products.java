package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.hub.data.DataHub;
import net.vorps.api.lang.LangSetting;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.objects.Item;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Products{
    private static HashMap<String, Products> listProducts;

    private @Getter final String name;
    private @Getter final double price;
    private @Getter final String money;
    private @Getter final long time;
    private @Getter final int type;
    private @Getter final String item;
    private @Getter final int level;
    private @Getter Game game;

    private @Getter ArrayList<String> productsGrades;

    public static Products getProduct(String nameProduct){
        return listProducts.get(nameProduct);
    }

    static {
        Products.listProducts = new HashMap<>();
        DataHub.loadListProduct();
    }
    /*public static Products getProductItem(ItemStack itemStack, String lang){
        for(Products products : Products.listProducts.values()){
            if(products.getItem().get(lang).get().isSimilar(itemStack)){
                return products;
            }
        }
        return null;
    }*/

    public boolean accessProductGrade(String nameGrade){
        return productsGrades.contains(nameGrade);
    }

    public boolean isProductGrade(){
        return productsGrades.size() > 0;
    }

    public Products(ResultSet results) throws SQLException {
        this.productsGrades = new ArrayList<>();
        this.name = results.getString(1);
        this.price = results.getDouble(2);
        this.money = results.getString(3);
        this.time = results.getLong(4);
        this.type = results.getInt(5);
        this.item = results.getString(6);
        this.level = results.getInt(7);
        /*String game = results.getString(8);
        if(game != null){
            this.game  = Game.getGame(game);
        }*/
        /*ResultSet resultsGrade = results.getData("product_grade", "pg_product = '"+results.getString(1)+"'");
        try {
            while (resultsGrade.next()){
                productsGrades.add(resultsGrade.getString(2));
            }
        } catch (SQLException e){
            //
        }*/
        Products.listProducts.put(this.name, this);
    }

    /*public static ArrayList<Products> getProduct(Game game){
        ArrayList<Products> list = new ArrayList<>();
        trieProduct.values().forEach((Products product) -> {
            if(product.game != null && product.game.equals(game)){
                list.add(product);
            }
        });
        return list;
    }*/

    public static ArrayList<Products> getProduct(int type){
        return Products.listProducts.values().stream().filter(e -> e.type == type).collect(Collectors.toCollection(ArrayList::new));
    }

    public ItemBuilder getItem(String lang){
        return Item.getItem(this.item, lang);
    }

    public static void clear(){
        listProducts.clear();
    }
}
