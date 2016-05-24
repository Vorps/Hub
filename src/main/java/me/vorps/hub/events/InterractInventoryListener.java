package me.vorps.hub.events;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.hub.Object.Grades;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.menu.*;
import me.vorps.hub.utils.CoolDown;
import me.vorps.hub.utils.GiveProductPlayer;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class InterractInventoryListener implements Listener {

    @EventHandler
    public void onInterractInventory(InventoryClickEvent e){
        boolean state;
        Player player = (Player) e.getWhoClicked();
        PlayerData playerData = PlayerData.getPlayerData(e.getWhoClicked().getName());
        if(e.getCurrentItem() != null){
            if(!playerData.isBuild()){
                e.setCancelled(true);
            }
            if(e.isCancelled()){
                switch (e.getSlot()){
                    case 39:
                        if(e.getCurrentItem().getType().equals(Material.AIR)){
                            MenuHelmetHat.createMenu(player);
                        } else {
                            player.getInventory().setHelmet(new ItemStack(Material.AIR));
                        }
                        break;
                    case 38:
                        if(e.getCurrentItem().getType().equals(Material.AIR)){
                            MenuChestplate.createMenu(player);
                        } else {
                            player.getInventory().setChestplate(new ItemStack(Material.AIR));
                        }
                        break;
                    case 37:
                        if(e.getCurrentItem().getType().equals(Material.AIR)){
                            MenuLeggings.createMenu(player);
                        } else {
                            player.getInventory().setLeggings(new ItemStack(Material.AIR));
                        }
                        break;
                    case 36:
                        if(e.getCurrentItem().getType().equals(Material.AIR)){
                            MenuBoots.createMenu(player);
                        } else {
                            player.getInventory().setBoots(new ItemStack(Material.AIR));
                        }
                        break;
                    default:
                        break;
                }
            }
            if(e.isCancelled() && !e.getCurrentItem().getType().equals(Material.AIR)){
                state = true;
                ItemStack is = e.getCurrentItem();
                if(e.getInventory().getSize() == 5 || e.getRawSlot() > e.getInventory().getSize()){
                    switch(is.getType()){
                        case COMPASS:
                            MenuPrincipal.createMenu(player);
                            break;
                        case SKULL_ITEM:
                            new MenuProfil(player);
                            break;
                        case BLAZE_POWDER:
                            CoolDown.visibilityAction(player, playerData);
                            break;
                        case ENDER_CHEST:
                            MenuBoutique.createMenu(player);
                            break;
                        case BARRIER:
                            playerData.getJump().stopJump(player, true);
                            break;
                        case WRITTEN_BOOK:
                            player.getInventory().clear(4);
                            break;
                        case ARROW:
                            playerData.getJump().failManager(player);
                        default:
                            break;
                    }
                }
                Products products = Products.getProductItem(is, playerData.getLang());
                if(products != null){
                    ResultSet results;
                    try {
                        results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_product WHERE pp_player = '" +player.getName() + "'");
                        while (results.next()) {
                            if (Database.FORTYCUBE.getDatabase().getString(results, 2).equals(products.getName())) {
                                state = false;
                            }
                        }
                    } catch (SQLException e1){
                        //
                    } catch (SqlException e1) {
                        e1.printStackTrace();
                    }
                    boolean stateGrade = true;
                    if(products.getType() == 6){
                        Grades gradePlayer = Grades.getGradesList().get(playerData.getGrade().getGrade());
                        if(Grades.getGradesList().get(products.getName()).getLevelGrade() < gradePlayer.getLevelGrade()){
                            stateGrade = false;
                        }
                    }
                    if((products.getType() == 5 && playerData.getBonus().getBonus().equals("default")) || (products.getType() == 6 && !playerData.getGrade().getGrade().equals(products.getName()) && stateGrade) || products.getType() != 5 || products.getType() != 6){
                        if(!state){
                            GiveProductPlayer.giveItemPlayer(player, products, false, is);
                        } else {
                            if(playerData.getMoney().get(products.getMoney()) >= products.getPrice()){
                                new MenuPurchase(player, products);
                            }
                        }
                    }
                }
            }
        }
    }
}
