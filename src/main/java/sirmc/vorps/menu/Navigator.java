package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import sirmc.vorps.Grades;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vorps on 03/02/2016.
 */
public class Navigator {

    public static void HubNavigator(PlayerHub playerHub){
        Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(0, new Item(Material.COMPASS).withName("§6Menu Principal").withLore(new String[] {"§7Accéder au menu principal"}).get());
        Profil(playerHub, null);
        PlayerVisible(playerHub);
        Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(8, new Item(Material.ENDER_CHEST).withName("§6Boutique").withLore(new String[] {"§7Accéder au menu Boutique"}).get());
    }

    public static void PlayerVisible(PlayerHub playerHub){
        if(playerHub.isVisiblePlayer()){
            Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs : (§aActivé§6)").withLore(new String[] {"§aPermet de masquer", "§7 - Les joueurs"}).get());
        } else {
            Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs : (§cDésactivé§6)").withLore(new String[] {"§aPermet de masquer", "§7 - Les joueurs"}).get());
        }
    }

    public static void Profil(PlayerHub playerHub, Inventory inv){
        Grades grade = Grades.GetGrade(playerHub);
        List<String> des = new ArrayList<>();

        des.add("§6§l"+Bukkit.getPlayer(playerHub.getPlayerName()).getName());
        if(grade.getGradeDisplay().equals("")){
            des.add("§7Grade : "+grade.getColorGrade()+grade.getGradeDisplay());
        } else {
            des.add("§7Grade : "+grade.getColorGrade()+grade.getGrade());
        }
        for(int i = 0; i < Hub.instance.getListMoney().size(); i++){
            if(playerHub.getBonus() == null){
                des.add("§7"+Hub.instance.getListMoney().get(i).getMoney()+" : §a"+playerHub.getMoney().get(Hub.instance.getListMoney().get(i).getMoney())+" "+Hub.instance.getListMoney().get(i).getAlias()+" §bGains : §c100%");
            } else {
                for(int y = 0; y < Hub.instance.getListBonus().size(); y++){
                    if(Hub.instance.getListBonus().get(y).getBonus().equals(playerHub.getBonus())){
                        if(Hub.instance.getListBonus().get(y).getMoney().equals(Hub.instance.getListBonus().get(i).getMoney())){
                            des.add("§7"+Hub.instance.getListMoney().get(i).getMoney()+" : §a"+playerHub.getMoney().get(Hub.instance.getListMoney().get(i).getMoney())+" "+Hub.instance.getListMoney().get(i).getAlias()+" §bGains : §c"+100*Hub.instance.getListBonus().get(y).getCoeff()+"%");
                        } else {
                            des.add("§7"+Hub.instance.getListMoney().get(i).getMoney()+" : §a"+playerHub.getMoney().get(Hub.instance.getListMoney().get(i).getMoney())+" "+Hub.instance.getListMoney().get(i).getAlias()+" §bGains : §c100%");
                        }
                    }
                }
            }
        }
        if(playerHub.getBonus() == null){
            des.add("§eBonus : §cDésactivé");
        } else {
            des.add("§eBonus : §a"+playerHub.getBonus());
        }
        if(inv == null){
            Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(1, new Item(playerHub.getPlayerName()).withName("§6Résumé du profil").withLore(des.toArray(des.toArray(new String[des.size()]))).get());
        } else {
            inv.setItem(22, new Item(playerHub.getPlayerName()).withName("§6Résumé du profil").withLore(des.toArray(des.toArray(new String[des.size()]))).get());
        }
        des.clear();
    }
}
