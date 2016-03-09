package me.vorps.hub.utils;

import me.vorps.fortycube.utils.ConvertMillis;
import me.vorps.hub.Object.Grades;
import me.vorps.hub.Object.Money;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.shop.ShopManager;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Purchase {

    private ArrayList<String> des = new ArrayList<>();
    private String namePlayer;
    private String message;
    private PlayerData playerData;
    private Products products;

    public Purchase(String namePlayer, String message){
        this.namePlayer = namePlayer;
        this.message = message;
        playerData = PlayerData.getPlayerData(namePlayer);
    }

    public String[] purchase(ShopManager shopManager){
        des.clear();
        String nameProduct = shopManager.getNameProduct();
        products = Products.getProduct(nameProduct);
        if(Products.isExits(nameProduct)){
            try {
                ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_product WHERE pp_player = '" + namePlayer + "' && pp_product = '" + nameProduct + "'");
                if (results.next()) {
                    if (Bukkit.getPlayer(namePlayer).getInventory().getHelmet() != null){
                        if (Bukkit.getPlayer(namePlayer).getInventory().getHelmet().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)) {
                            des.add("§9" + nameProduct);
                            des.add("§aVous avez déjà " + message);
                            return des.toArray(new String[des.size()]);
                        }
                    }
                    if (Bukkit.getPlayer(namePlayer).getInventory().getChestplate() != null) {
                        if (Bukkit.getPlayer(namePlayer).getInventory().getChestplate().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)) {
                            des.add("§9" + nameProduct);
                            des.add("§aVous avez déjà " + message);
                            return des.toArray(new String[des.size()]);
                        }
                    }
                    if (Bukkit.getPlayer(namePlayer).getInventory().getLeggings() != null) {
                        if (Bukkit.getPlayer(namePlayer).getInventory().getLeggings().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)) {
                            des.add("§9" + nameProduct);
                            des.add("§aVous avez déjà " + message);
                            return des.toArray(new String[des.size()]);
                        }
                    }
                    if (Bukkit.getPlayer(namePlayer).getInventory().getBoots() != null) {
                        if (Bukkit.getPlayer(namePlayer).getInventory().getBoots().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)) {
                            des.add("§9" + nameProduct);
                            des.add("§aVous avez déjà " + message);
                            return des.toArray(new String[des.size()]);
                        }
                    }
                    des.add("§9" + nameProduct);
                    des.add("§aMetre " + message);
                    return des.toArray(new String[des.size()]);
                }
            } catch (SQLException e){
                //
            } catch (SqlException e) {
                e.printStackTrace();
            }

            if(!products.isProductGrade()){
                testMoneyFunction(nameProduct);
            } else {
                if(products.accessProductGrade(playerData.getGrade().getGrade())){
                    testMoneyFunction(nameProduct);
                } else {
                    des.add("§cCe produit est reservé au grade :");
                    Products.getProduct(nameProduct).getProductsGrades().forEach((String grade) -> des.add(Grades.getGrades(grade).toString()));
                }
            }
            if(products.getTime() > 0){
                des.add("§7Ce produit n'est pas a vie | §4Duré§e : ");
                des.add("§c"+ ConvertMillis.convertMillisToDate(products.getTime()));
            }
        } else {
            des.add("§cProduit non disponible");
        }
        return des.toArray(new String[des.size()]);
    }

    private void testMoneyFunction(String nameProduct){
        Money money = Money.getMoney(products.getMoney());
        if(playerData.getMoney().get(products.getMoney()) >= products.getPrice()){
            des.add("§9"+nameProduct);
            des.add("§aClic gauche : §eAcheter -> §e"+ money.getColor()+products.getPrice()+" "+money.getMoney());
        } else {
            des.add("§cVous n'avez pas assez de §a"+money.getColor()+money.getMoney());
            des.add("§cpour acheter le produit §a"+nameProduct);
        }
    }

    public String[] purchaseBonus(ShopManager shopManager){
        des.clear();
        playerData.getBonusFunction();
        if(playerData.getBonus().getBonus().equals("default")){
            return purchase(shopManager);
        } else {
            des.add("§cVous possedez déjà un bonus : §a"+ playerData.getBonus().getBonus());
        }
        return des.toArray(new String[des.size()]);
    }

    public String[] purchaseGrade(ShopManager shopManager){
        des.clear();
        if(playerData.getGrade().getGrade().equals(shopManager.getNameProduct())){
            des.add("§aVous possédez déjà "+message);
            return des.toArray(new String[des.size()]);
        } else {
            Grades gradePlayer = Grades.getGradesList().get(playerData.getGrade().getGrade());
            if(Grades.getGradesList().get(shopManager.getNameProduct()).getLevelGrade() < gradePlayer.getLevelGrade()){
                des.add("§aVous possédez déjà le grade : "+gradePlayer.toString());
                return des.toArray(new String[des.size()]);
            }
        }
        return purchase(shopManager);
    }
}
