package me.vorps.hub.menu;

import me.vorps.hub.Object.Bonus;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.Money;
import me.vorps.hub.Settings;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import me.vorps.hub.Object.Grades;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Navigator {

    public static void navigator(PlayerData playerData, Player player){
       player.getInventory().setItem(0, new Item(Material.COMPASS).withName("§6Menu Principal").withLore(new String[] {"§7Accéder au menu principal"}).get());
        profil(playerData, player, null);
        playerVisible(playerData, player);
        player.getInventory().setItem(8, new Item(Material.ENDER_CHEST).withName("§6Boutique").withLore(new String[] {"§7Accéder au menu Boutique"}).get());
    }

    public static void playerVisible(PlayerData playerData, Player player){
        if(playerData.isVisiblePlayer()){
            player.getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs : (§aActivé§6)").withLore(new String[] {"§aPermet de masquer", "§7 - Les joueurs"}).get());
        } else {
            player.getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs : (§cDésactivé§6)").withLore(new String[] {"§aPermet de masquer", "§7 - Les joueurs"}).get());
        }
    }

    public static void profil(PlayerData playerData,Player player, Inventory inv){
        Grades grade = playerData.getGrade();
        List<String> des = new ArrayList<>();
        des.add("§6§l"+playerData.getNamePlayer());
        if(grade.getGradeDisplay().equals("")){
            des.add("§7Grade : "+grade.getColorGrade()+grade.getGradeDisplay());
        } else {
            des.add("§7Grade : "+grade.getColorGrade()+grade.getGrade());
        }
        Bonus bonus = playerData.getBonus();
        Money.getListMoney().values().forEach((Money money) -> {
            if(money.getMoney().equals(bonus.getMoney())){
                des.add("§7"+money.getMoney()+" : "+money.getColor()+ playerData.getMoney().get(money.getMoney())+" "+money.getAlias()+" §bGains : §c"+100*bonus.getCoefficient()+"%");
            } else {
                des.add("§7"+money.getMoney()+" : "+money.getColor()+ playerData.getMoney().get(money.getMoney())+" "+money.getAlias()+" §bGains : §c"+100* Settings.getDefaultBonus().getCoefficient()+"%");
            }
        });
        if(bonus.getBonus().equals(Settings.getDefaultBonus().getBonus())){
            des.add("§eBonus : §cDésactivé");
        } else {
            des.add("§eBonus : §a"+ bonus);
        }
        if(inv == null){
            player.getInventory().setItem(1, new Item(player.getName()).withName("§6Résumé du profil").withLore(des.toArray(des.toArray(new String[des.size()]))).get());
        } else {
            inv.setItem(22, new Item(player.getName()).withName("§6Résumé du profil").withLore(des.toArray(des.toArray(new String[des.size()]))).get());
        }
        des.clear();
    }
}
