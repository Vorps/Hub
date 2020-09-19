package me.vorps.hub.utils;

import net.vorps.api.objects.Money;
import net.vorps.api.objects.Rank;
import net.vorps.api.utils.ConvertMillis;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import net.vorps.api.databases.Database;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Purchase {

    private ArrayList<String> des = new ArrayList<>();
    private UUID uuid;
    private String message;
    private Products products;

    public Purchase(UUID uuid, String message){
        this.uuid = uuid;
        this.message = message;
    }

    public String[] purchase(String nameProduct){
        des.clear();
        products = Products.getProduct(nameProduct);
        /*try {
            ResultSet results = Database.CORE.getDatabase().getData("player_product", "pp_player = '" + namePlayer + "' && pp_product = '" + nameProduct + "'");
            if (results.next()) {
                if (Bukkit.getPlayer(namePlayer).getInventory().getHelmet() != null){
                    if (Bukkit.getPlayer(namePlayer).getInventory().getHelmet().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)) {
                        des.add("§aVous avez déjà " + message);
                        return des.toArray(new String[des.size()]);
                    }
                }
                if (Bukkit.getPlayer(namePlayer).getInventory().getChestplate() != null) {
                    if (Bukkit.getPlayer(namePlayer).getInventory().getChestplate().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)) {
                        des.add("§aVous avez déjà " + message);
                        return des.toArray(new String[des.size()]);
                    }
                }
                if (Bukkit.getPlayer(namePlayer).getInventory().getLeggings() != null) {
                    if (Bukkit.getPlayer(namePlayer).getInventory().getLeggings().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)) {
                        des.add("§aVous avez déjà " + message);
                        return des.toArray(new String[des.size()]);
                    }
                }
                if (Bukkit.getPlayer(namePlayer).getInventory().getBoots() != null) {
                    if (Bukkit.getPlayer(namePlayer).getInventory().getBoots().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)) {
                        des.add("§aVous avez déjà " + message);
                        return des.toArray(new String[des.size()]);
                    }
                }
                if(products.getType() != 0){
                    if(products.getType() == 8 && playerData.getGadget() != null && playerData.getGadget().toString().equals(products.getName())){
                        des.add("§aVous utilisez déja ce gadget ");
                    } else if(products.getType() == 7 && playerData.getParticle() != null && playerData.getParticle().getParticle().equals(products.getName())){
                        des.add("§aVous utilisez déja cette particule ");
                    } else {
                        des.add("§aMettre " + message);
                    }
                } else {
                    des.add("§aVous possedez déja" + message);
                }
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
            if(products.accessProductGrade(playerData.getRank().getRank())){
                testMoneyFunction(nameProduct);
            } else {
                des.add("§cCe produit est reservé au grade :");
                Products.getProduct(nameProduct).getProductsGrades().forEach((String grade) -> des.add(Rank.getRank(grade).toString()));
            }
        }
        if(products.getTime() > 0){
            des.add("§7Ce produit n'est pas a vie | §4Duré§e : ");
            des.add("§c"+ ConvertMillis.convertMillisToTime(products.getTime()));
        }*/
        return des.toArray(new String[des.size()]);
    }

    private void testMoneyFunction(String nameProduct){
        /*Money money = Money.getMoney(products.getMoney());
        if(playerData.getMoney().get(products.getMoney()) >= products.getPrice()){
            des.add("§aClic gauche : §eAcheter -> §e"+ money.getColor()+products.getPrice()+" "+money.getMoney());
        } else {
            des.add("§ePrix "+money.getColor()+products.getPrice()+" "+money.getMoney());
            des.add("§cVous n'avez pas assez de §a"+money.getColor()+money.getMoney());
            des.add("§cpour acheter le produit §a"+nameProduct);
            des.add("§bIl vous manque "+money.getColor()+(products.getPrice()-playerData.getMoney().get(products.getMoney()))+" "+money.getMoney());
        }*/
    }

    public String[] purchaseBonus(String nameProduct){
        /*des.clear();
        playerData.getBonusFunction();
        if(playerData.getBonus().getBonus().equals("default")){
            return purchase(nameProduct);
        } else {
            des.add("§cVous possedez déjà un bonus : §a"+ playerData.getBonus().getBonus());
        }*/
        return des.toArray(new String[des.size()]);
    }


    public String[] purchaseGrade(String nameProduct){
        /*des.clear();
        if(playerData.getRank().getRank().equals(nameProduct)){
            des.add("§aVous possédez déjà "+message);
            return des.toArray(new String[des.size()]);
        } else {
            Rank gradePlayer = Rank.getRankList().get(playerData.getRank().getRank());
            if(Rank.getRankList().get(nameProduct).getLevelRank() < gradePlayer.getLevelRank()){
                des.add("§aVous possédez déjà le grade : "+gradePlayer.toString());
                return des.toArray(new String[des.size()]);
            }
        }*/
        return purchase(nameProduct);
    }
}
