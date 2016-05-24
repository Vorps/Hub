package me.vorps.hub.utils;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.utils.ConvertMillis;
import me.vorps.hub.Object.Bonus;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.Products;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.menu.MenuPetsCustom;
import me.vorps.hub.menu.Navigator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.Timestamp;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class GiveProductPlayer {

    public static void giveItemPlayer(Player player, Products products, boolean state){
        GiveProductPlayer.giveItemPlayer(player, products, state, products.getItem().get(PlayerData.getPlayerData(player.getName()).getLang()).get());
    }

    public static void giveItemPlayer(Player player, Products products, boolean state, ItemStack itemStack){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());

        if(state){
            try {
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_money SET pm_value = '"+(playerData.getMoney().get(products.getMoney())-products.getPrice())+"' WHERE pm_player = '"+ player.getName()+"' && pm_money = '"+products.getMoney()+"'");
                Database.FORTYCUBE.getDatabase().sendDatabase("INSERT INTO player_product VALUES ('"+player.getName()+"', '"+ products.getName()+"', '"+new Timestamp(System.currentTimeMillis())+"')");
            } catch (SqlException e){
                e.printStackTrace();
            }
            playerData.getMoneyFunction();
            if(products.getTime() > 0){
               player.sendMessage("§eVous avez acheté le produit : §a"+ products.getName()+" §epour une dure de "+ ConvertMillis.convertMillisToDate(products.getTime()));
            } else {
               player.sendMessage("§eVous avez acheté le produit : §a"+ products.getName());
            }
            playerData.getProductsPlayerFunction();
        }
        switch (products.getType()) {
            case 2:
                player.getInventory().setChestplate(new Item(products.getItem().get(playerData.getLang())).withLore(new String[] {"§aRetirer ce plastron"}).get());
                player.sendMessage("§eVoici votre plastron !!!");
                player.closeInventory();
                break;
            case 3:
                player.getInventory().setLeggings(new Item(products.getItem().get(playerData.getLang())).withLore(new String[] {"§aRetirer ce leggings"}).get());
                player.sendMessage("§eVoici votre leggings !!!");
                player.closeInventory();
                break;
            case 4:
                player.getInventory().setBoots(new Item(products.getItem().get(playerData.getLang())).withLore(new String[] {"§aRetirer vos bottes"}).get());
                player.sendMessage("§eVoici vos bottes !!!");
                player.closeInventory();
                break;
            case 5:
                if(!playerData.getBonus().getBonus().equals(products.getName())){
                    try {
                        Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_bonus = '"+products.getName()+"' WHERE ps_player = '"+player.getName()+"'");
                    } catch (SqlException e){
                        e.printStackTrace();
                    }
                    player.closeInventory();
                    playerData.setBonus(products.getName(), true);
                }
                break;
            case 6:
                if(!playerData.getGrade().getGrade().equals(products.getName())){
                    try {
                        Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_grade = '"+products.getName()+"' WHERE ps_player = '"+player.getName()+"'");
                    } catch (SqlException e){
                        e.printStackTrace();
                    }
                    playerData.updateGrades();
                    player.sendMessage("§eVous êtes maintenant §a"+playerData.getGrade()+"§e.");
                    player.closeInventory();
                    break;
                }
               player.closeInventory();
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                new MenuPetsCustom(player, itemStack);
                break;
            default:
                if(products.getType() == 1 || products.getType() == 10 || products.getType() == 11){
                    player.getInventory().setHelmet(new Item(products.getItem().get(playerData.getLang())).withLore(new String[] {"§aRetirer votre chapeau"}).get());
                    player.sendMessage("§eVoici votre chapeau !!!");
                    player.closeInventory();
                }
                break;
        }
        Navigator.profil(playerData, player, null);
    }
}
