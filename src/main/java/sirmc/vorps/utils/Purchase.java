package sirmc.vorps.utils;

import org.bukkit.Bukkit;
import sirmc.vorps.Grades;
import sirmc.vorps.Hub;
import sirmc.vorps.Object.Products;
import sirmc.vorps.PlayerHub;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Vorps on 05/02/2016.
 */
public class Purchase {

    private ArrayList<String> des = new ArrayList<>();
    private PlayerHub playerHub;
    private String message;

    public Purchase(PlayerHub playerHub, String message){
        this.playerHub = playerHub;
        this.message = message;
    }
    public String[] purchase(String nameProduct){
        des.clear();
        if(Hub.instance.getListProducts().containsKey(nameProduct)){
            try {
                ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM PlayerProduit WHERE namePlayer = '"+playerHub.getPlayerName()+"' && produit = '"+nameProduct+"'");
                if(results.next()){
                    if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getHelmet() != null){
                        if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getHelmet().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)){
                            des.add("§9"+nameProduct);
                            des.add("§aVous avez déjà "+message);
                            return des.toArray(new String[des.size()]);
                        }
                    }
                    if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getChestplate() != null){
                        if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getChestplate().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)){
                            des.add("§9"+nameProduct);
                            des.add("§aVous avez déjà "+message);
                            return des.toArray(new String[des.size()]);
                        }
                    }
                    if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getLeggings() != null){
                        if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getLeggings().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)){
                            des.add("§9"+nameProduct);
                            des.add("§aVous avez déjà "+message);
                            return des.toArray(new String[des.size()]);
                        }
                    }
                    if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getBoots() != null){
                        if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getBoots().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)){
                            des.add("§9"+nameProduct);
                            des.add("§aVous avez déjà "+message);
                            return des.toArray(new String[des.size()]);
                        }
                    }
                    des.add("§9"+nameProduct);
                    des.add("§aMetre "+message);
                    return des.toArray(new String[des.size()]);
                }
            } catch (Exception e) {}

            if(!Hub.instance.getListProductsGrade().containsKey(nameProduct)){
                Products products = Hub.instance.getListProducts().get(nameProduct);
                if(playerHub.getMoney().get(products.getMoney()) >= products.getPrice()){
                    des.add("§9"+nameProduct);
                    des.add("§aClic gauche : §eAcheter -> §e"+ Hub.instance.getListProducts().get(nameProduct).getPrice()+" §a"+Hub.instance.getListProducts().get(nameProduct).getMoney());
                } else {
                    des.add("§cVous n'avez pas assez de §a"+Hub.instance.getListProducts().get(nameProduct).getMoney());
                    des.add("§c pour acheter le produit §a"+nameProduct);
                }
            } else {
                if(Hub.instance.getListProductsGrade().get(nameProduct).contains(playerHub.getGrade())){
                    Products products = Hub.instance.getListProducts().get(nameProduct);
                    if(playerHub.getMoney().get(products.getMoney()) >= products.getPrice()){
                        des.add("§9"+nameProduct);
                        des.add("§aClic gauche : §eAcheter -> §e"+ Hub.instance.getListProducts().get(nameProduct).getPrice()+" §a"+Hub.instance.getListProducts().get(nameProduct).getMoney());
                    } else {
                        des.add("§cVous n'avez pas assez de §a"+Hub.instance.getListProducts().get(nameProduct).getMoney());
                        des.add("§c pour acheter le produit §a"+nameProduct);
                    }
                }
                des.add("§cCe produit est reservé au grade :");
                for(int i = 0; i < Hub.instance.getListProductsGrade().get(nameProduct).size(); i++){
                    Grades grade = Grades.GetGrade(Hub.instance.getListProductsGrade().get(nameProduct).get(i));
                    if(grade.getGradeDisplay().equals("")){
                        des.add(""+grade.getColorGrade()+grade.getGrade());
                    } else {
                        des.add(""+grade.getColorGrade()+grade.getGradeDisplay());
                    }
                }
            }
            if(Hub.instance.getListProducts().get(nameProduct).getTime() > 0){
                des.add("§7Ce produit n'est pas a vie | §4Duré§e : ");
                des.add("§c"+ConvertMillis.ConvertMillisToDate(Hub.instance.getListProducts().get(nameProduct).getTime()));
            }
        } else {
            des.add("§cProduit non disponible");
        }
        return des.toArray(new String[des.size()]);
    }

    public String[] purchaseBonus(String nameProduct){
        des.clear();
        playerHub.getBonusFunction();
        if(playerHub.getBonus() == null){
            return purchase(nameProduct);
        } else {
            des.add("§cVous possedez déjà un bonus : §a"+playerHub.getBonus());
        }
        return des.toArray(new String[des.size()]);
    }

    public String[] purchaseGrade(String nameProduct){
        des.clear();
        ArrayList<Grades> gradeLevel = new ArrayList<>();
        for(int i = 0 ;i < Grades.getGradesList().size(); i++){
            if(Grades.getGradesList().get(i).getLevelGrade() > 0) gradeLevel.add(Grades.getGradesList().get(i));
        }
        if(playerHub.getGrade().equals(nameProduct)){
            des.add("§aVous possédez déjà "+message);
            return des.toArray(new String[des.size()]);
        } else {
            Grades gradePlayer = Grades.GetGrade(playerHub.getGrade());
            if(Grades.GetGrade(nameProduct).getLevelGrade() < gradePlayer.getLevelGrade()){
                des.add("§aVous possédez déjà le grade : "+gradePlayer.toString());
                return des.toArray(new String[des.size()]);
            }
        }
        return purchase(nameProduct);
    }
}
