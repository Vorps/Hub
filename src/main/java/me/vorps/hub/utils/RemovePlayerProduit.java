package me.vorps.hub.utils;


import me.vorps.hub.Object.Grades;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class RemovePlayerProduit {
	public static void boutiqueRemovePlayerProduit(Player player, Products products, String nameProduct){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
		player.sendMessage("§7Votre produit : "+nameProduct+" a été retiré.");
        try{
            Database.FORTYCUBE.getDatabase().sendDatabase("DELETE FROM player_product WHERE pp_player = '"+ player.getName()+"' && pp_product = '"+nameProduct+"'");
        } catch (SqlException e){
            e.printStackTrace();
        }
		switch (products.getType()) {
		case 1:
			if(player.getInventory().getHelmet() != null){
    			if(player.getInventory().getHelmet().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)){
    				player.getInventory().setHelmet(new ItemStack(Material.AIR));
    			}
			}
			break;
		case 2:
			if(player.getInventory().getChestplate() != null){
    			if(player.getInventory().getChestplate().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)){
    				player.getInventory().setChestplate(new ItemStack(Material.AIR));
    			}
			}
			break;
		case 3:
			if(player.getInventory().getLeggings() != null){
    			if(player.getInventory().getLeggings().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)){
    				player.getInventory().setLeggings(new ItemStack(Material.AIR));
    			}
			}
			break;
		case 4:
			if(player.getInventory().getBoots() != null){
    			if(player.getInventory().getBoots().getItemMeta().getLore().get(0).substring(2).equals(nameProduct)){
    				player.getInventory().setBoots(new ItemStack(Material.AIR));
    			}
			}
			break;
		case 5:
            try {
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_bonus = '' WHERE ps_player = '"+ player.getName() +"'");
            } catch (SqlException e){
                e.printStackTrace();
            }
			playerData.getBonusFunction();
			break;
		case 6:
			if(playerData.getGrade().equals(nameProduct)){
                Grades.getGradesList().values().forEach((Grades grade) -> {
                    if((Grades.getGradesList().get(grade).isDefaultGrade())){
                        try {
                            Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_grade = '"+grade.getGrade()+"' WHERE ps_player = '"+ player.getName() +"'");
                        } catch (SqlException e){
                            e.printStackTrace();
                        }
                        if(grade.getGradeDisplay().equals("")){
                            player.sendMessage("§eVous êtes maintenant §a"+grade.getColorGrade()+grade.getGrade()+"§e.");
                        } else {
                            player.sendMessage("§eVous êtes maintenant §a"+grade.getColorGrade()+grade.getGradeDisplay()+"§e.");
                        }
                        playerData.updateGrades();
                    }
                });
			}
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		default:
			break;
		}
	}
}
