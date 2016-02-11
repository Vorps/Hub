package sirmc.vorps.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sirmc.vorps.Grades;
import sirmc.vorps.Hub;
import sirmc.vorps.Object.Products;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.events.WeatherListener;
import sirmc.vorps.menu.MenuPetsCustom;
import sirmc.vorps.menu.Navigator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vorps on 03/02/2016.
 */
public class GiveProductPlayer {

    public static void GiveItemPlayer(PlayerHub playerHub, String nameProduct, boolean state){
        Products products = Hub.instance.getListProducts().get(playerHub.getProductTarget().getItemMeta().getLore().get(0).substring(2));
        if(state){
            Hub.instance.database.SendDatabase("UPDATE PlayerMoney SET value = '"+(playerHub.getMoney().get(products.getMoney())-products.getPrice())+"' WHERE namePlayer = '"+ Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"' && money = '"+products.getMoney()+"'");
            Hub.instance.database.SendDatabase("INSERT INTO PlayerProduit VALUES ('"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"', '"+playerHub.getProductTarget().getItemMeta().getLore().get(0).substring(2)+"', '"+new Timestamp(System.currentTimeMillis())+"')");
            playerHub.getMoneyFunction();
            if(products.getTime() > 0){
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez acheté le produit : §a"+playerHub.getProductTarget().getItemMeta().getLore().get(0).substring(2)+" §epour une dure de "+ ConvertMillis.ConvertMillisToDate(products.getTime()));
            } else {
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez acheté le produit : §a"+playerHub.getProductTarget().getItemMeta().getLore().get(0).substring(2));
            }
        }
        switch (products.getType()) {
            case 1:
                if(playerHub.getProductTarget().getItemMeta().getLore().contains("§aRetirer ce chapeau")){
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setHelmet(new ItemStack(Material.AIR));
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setHelmet(new Item(playerHub.getProductTarget()).withLore(new String[] {"§9"+nameProduct, "§aRetirer ce chapeau"}).get());
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVoici votre chapeau !!!");
                    Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                }
                break;
            case 2:
                if(playerHub.getProductTarget().getItemMeta().getLore().contains("§aRetirer ce plastron")){
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setChestplate(new ItemStack(Material.AIR));
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setChestplate(new Item(playerHub.getProductTarget()).withLore(new String[] {"§9"+nameProduct, "§aRetirer ce plastron"}).get());
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVoici votre plastron !!!");
                    Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                }
                break;
            case 3:
                if(playerHub.getProductTarget().getItemMeta().getLore().contains("§aRetirer ce leggings")){
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setLeggings(new ItemStack(Material.AIR));
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setLeggings(new Item(playerHub.getProductTarget()).withLore(new String[] {"§9"+nameProduct, "§aRetirer ce leggings"}).get());
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVoici votre leggings !!!");
                    Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                }
                break;
            case 4:
                if(playerHub.getProductTarget().getItemMeta().getLore().contains("§aRetirer vos bottes")){
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setBoots(new ItemStack(Material.AIR));
                } else {
                    Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setBoots(new Item(playerHub.getProductTarget()).withLore(new String[] {"§9"+nameProduct, "§aRetirer vos bottes"}).get());
                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVoici vos bottes !!!");
                    Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                }
                break;
            case 5:
                Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET bonus = '"+nameProduct+"' WHERE namePlayer = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                playerHub.setBonus(nameProduct);
                break;
            case 6:
                if(!playerHub.getGrade().equals(nameProduct)){
                    for(int i = 0; i < Grades.getGradesList().size(); i++){
                        if((Grades.getGradesList().get(i).getGrade()).equalsIgnoreCase(nameProduct)){
                            Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET grade = '"+nameProduct+"' WHERE namePlayer = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                            if(Grades.getGradesList().get(i).getGradeDisplay().equals("")){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous êtes maintenant §a"+Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGrade()+"§e.");
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous êtes maintenant §a"+Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeDisplay()+"§e.");
                            }
                            playerHub.UpdateGrades();
                            break;
                        }
                    }
                }
                Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                MenuPetsCustom.HubPetsCustom(playerHub, playerHub.getProductTarget());
                break;
            default:
                break;
        }
        Navigator.Profil(playerHub, null);
    }
}
