package me.vorps.hub.utils;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.utils.ConvertMillis;
import me.vorps.hub.Object.Bonus;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.Products;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.menu.MenuPetsCustom;
import me.vorps.hub.menu.Navigator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.vorps.hub.Object.Grades;

import java.sql.Timestamp;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class GiveProductPlayer {

    public static void giveItemPlayer(Player player, String nameProduct, boolean state){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        Products products = Products.getProduct(playerData.getProductTarget().getItemMeta().getLore().get(0).substring(2));
        if(state){
            try {
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_money SET pm_value = '"+(playerData.getMoney().get(products.getMoney())-products.getPrice())+"' WHERE pm_player = '"+ player.getName()+"' && pm_money = '"+products.getMoney()+"'");
                Database.FORTYCUBE.getDatabase().sendDatabase("INSERT INTO player_product VALUES ('"+player.getName()+"', '"+ playerData.getProductTarget().getItemMeta().getLore().get(0).substring(2)+"', '"+new Timestamp(System.currentTimeMillis())+"')");
            } catch (SqlException e){
                e.printStackTrace();
            }
            playerData.getMoneyFunction();
            if(products.getTime() > 0){
               player.sendMessage("§eVous avez acheté le produit : §a"+ playerData.getProductTarget().getItemMeta().getLore().get(0).substring(2)+" §epour une dure de "+ ConvertMillis.convertMillisToDate(products.getTime()));
            } else {
               player.sendMessage("§eVous avez acheté le produit : §a"+ playerData.getProductTarget().getItemMeta().getLore().get(0).substring(2));
            }
        }
        switch (products.getType()) {
            case 1:
                if(playerData.getProductTarget().getItemMeta().getLore().contains("§aRetirer ce chapeau")){
                   player.getInventory().setHelmet(new ItemStack(Material.AIR));
                } else {
                   player.getInventory().setHelmet(new Item(playerData.getProductTarget()).withLore(new String[] {"§9"+nameProduct, "§aRetirer ce chapeau"}).get());
                   player.sendMessage("§eVoici votre chapeau !!!");
                   player.closeInventory();
                }
                break;
            case 2:
                if(playerData.getProductTarget().getItemMeta().getLore().contains("§aRetirer ce plastron")){
                   player.getInventory().setChestplate(new ItemStack(Material.AIR));
                } else {
                   player.getInventory().setChestplate(new Item(playerData.getProductTarget()).withLore(new String[] {"§9"+nameProduct, "§aRetirer ce plastron"}).get());
                   player.sendMessage("§eVoici votre plastron !!!");
                   player.closeInventory();
                }
                break;
            case 3:
                if(playerData.getProductTarget().getItemMeta().getLore().contains("§aRetirer ce leggings")){
                   player.getInventory().setLeggings(new ItemStack(Material.AIR));
                } else {
                   player.getInventory().setLeggings(new Item(playerData.getProductTarget()).withLore(new String[] {"§9"+nameProduct, "§aRetirer ce leggings"}).get());
                   player.sendMessage("§eVoici votre leggings !!!");
                   player.closeInventory();
                }
                break;
            case 4:
                if(playerData.getProductTarget().getItemMeta().getLore().contains("§aRetirer vos bottes")){
                   player.getInventory().setBoots(new ItemStack(Material.AIR));
                } else {
                   player.getInventory().setBoots(new Item(playerData.getProductTarget()).withLore(new String[] {"§9"+nameProduct, "§aRetirer vos bottes"}).get());
                   player.sendMessage("§eVoici vos bottes !!!");
                   player.closeInventory();
                }
                break;
            case 5:
                if(!playerData.getBonus().equals(nameProduct)){
                    try {
                        Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_bonus = '"+nameProduct+"' WHERE ps_player = '"+player.getName()+"'");
                    } catch (SqlException e){
                        e.printStackTrace();
                    }
                   player.closeInventory();
                    playerData.setBonus(Bonus.getBonus(nameProduct));
                }
                break;
            case 6:
                Grades grade = Grades.getGradesList().get(nameProduct);
                if(!playerData.getGrade().equals(nameProduct)){
                    try {
                        Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_grade = '"+nameProduct+"' WHERE ps_player = '"+player.getName()+"'");
                    } catch (SqlException e){
                        e.printStackTrace();
                    }
                    if(grade.getGradeDisplay().equals("")){
                       player.sendMessage("§eVous êtes maintenant §a"+grade.getColorGrade()+grade.getGrade()+"§e.");
                    } else {
                       player.sendMessage("§eVous êtes maintenant §a"+grade.getColorGrade()+grade.getGradeDisplay()+"§e.");
                    }
                    playerData.updateGrades();
                    break;
                }
               player.closeInventory();
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                new MenuPetsCustom(player, playerData.getProductTarget());
                break;
            default:
                break;
        }
        Navigator.profil(playerData, player, null);
    }
}
