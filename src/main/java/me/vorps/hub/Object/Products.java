package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.utils.LangSetting;
import org.apache.commons.codec.language.bm.Lang;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Products{
    private static HashMap<String, Products> listProducts = new HashMap<>();

    private @Getter String name;
    private @Getter double price;
    private @Getter String money;
    private @Getter long time;
    private @Getter int type;
    private @Getter HashMap<String, me.vorps.fortycube.menu.Item> item;
    private @Getter int level;
    private @Getter Game game;

    private static @Getter TreeMap<String,Products> trieProduct = new TreeMap<>(new ComparatorProduct(listProducts));

    private @Getter ArrayList<String> productsGrades;


    private static class ComparatorProduct implements Comparator<String> {
        Map<String, Products> base;
        private ComparatorProduct(Map<String, Products> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            return base.get(a).level >= base.get(b).level ? 1 : -1;
        }
    }


    public static Products getProduct(String nameProduct){
        return listProducts.get(nameProduct);
    }

    public static Products getProductItem(ItemStack itemStack, String lang){
        for(Products products : Products.listProducts.values()){
            if(products.getItem().get(lang).get().isSimilar(itemStack)){
                return products;
            }
        }
        return null;
    }

    public boolean accessProductGrade(String nameGrade){
        return productsGrades.contains(nameGrade);
    }

    public boolean isProductGrade(){
        return productsGrades.size() > 0;
    }

    public Products(ResultSet results) throws SqlException {
        item = new HashMap<>();
        this.productsGrades = new ArrayList<>();
        name = Database.FORTYCUBE.getDatabase().getString(results, 1);
        price = Database.FORTYCUBE.getDatabase().getDouble(results, 2);
        money = Database.FORTYCUBE.getDatabase().getString(results, 3);
        time = Database.FORTYCUBE.getDatabase().getLong(results, 4);
        type = Database.FORTYCUBE.getDatabase().getInt(results, 5);
        for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
            item.put(langSetting.getName(), Item.getItem(Database.FORTYCUBE.getDatabase().getString(results, 6), langSetting.getName()));
        }
        level = Database.FORTYCUBE.getDatabase().getInt(results, 7);
        String game = Database.FORTYCUBE.getDatabase().getString(results, 8);
        if(game != null){
            this.game  = Game.getGame(game);
        }
        ResultSet resultsGrade = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM product_grade WHERE pg_product = '"+Database.FORTYCUBE.getDatabase().getString(results, 1)+"'");
        try {
            while (resultsGrade.next()){
                productsGrades.add(Database.FORTYCUBE.getDatabase().getString(resultsGrade, 2));
            }
        } catch (SQLException e){
            //
        }
        listProducts.put(name, this);
    }

    public static void trieProduct(){
        trieProduct.clear();
        trieProduct.putAll(listProducts);
    }

    public static ArrayList<Products> getProduct(Game game){
        ArrayList<Products> list = new ArrayList<>();
        trieProduct.values().forEach((Products product) -> {
            if(product.game != null && product.game.equals(game)){
                list.add(product);
            }
        });
        return list;
    }

    public static ArrayList<Products> getProduct(int type){
        ArrayList<Products> list = new ArrayList<>();
        trieProduct.values().forEach((Products product) -> {
            if(product.type == type){
                list.add(product);
            }
        });
        return list;
    }

    public static void clear(){
        listProducts.clear();
    }
}
