package me.vorps.hub.Object;

import lombok.Getter;
import net.vorps.api.lang.LangSetting;
import net.vorps.api.objects.Item;
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
    private @Getter HashMap<String, net.vorps.api.menu.ItemBuilder> item;
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

    public Products(ResultSet results) throws SQLException {
        item = new HashMap<>();
        this.productsGrades = new ArrayList<>();
        name = results.getString(1);
        price = results.getDouble(2);
        money = results.getString(3);
        time = results.getLong(4);
        type = results.getInt(5);
        for(String langSetting : LangSetting.getListLangSetting()){
            item.put(langSetting, Item.getItem(results.getString(6), langSetting));
        }
        level = results.getInt(7);
        String game = results.getString(8);
        if(game != null){
            this.game  = Game.getGame(game);
        }
        /*ResultSet resultsGrade = results.getData("product_grade", "pg_product = '"+results.getString(1)+"'");
        try {
            while (resultsGrade.next()){
                productsGrades.add(resultsGrade.getString(2));
            }
        } catch (SQLException e){
            //
        }*/
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
