package sirmc.vorps.utils;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.Grades;
import sirmc.vorps.Hub;
import sirmc.vorps.Object.Products;

public class RemovePlayerProduit {
	public static void BoutiqueRevovePlayerProduit(PlayerHub playerHub, Products products, String nameProduit){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("�7Votre produit : "+nameProduit+" a �t� retir�.");
		Hub.instance.database.SendDatabase("DELETE FROM PlayerProduit WHERE namePlayer = '"+playerHub.getPlayerName()+"' && produit = '"+(String) nameProduit+"'");
		switch (products.getType()) {
		case 1:
			if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getHelmet() != null){
    			if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getHelmet().getItemMeta().getLore().get(0).substring(2).equals(nameProduit)){
    				Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setHelmet(new ItemStack(Material.AIR));
    			}
			}
			break;
		case 2:
			if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getChestplate() != null){
    			if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getChestplate().getItemMeta().getLore().get(0).substring(2).equals(nameProduit)){
    				Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setChestplate(new ItemStack(Material.AIR));
    			}
			}
			break;
		case 3:
			if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getLeggings() != null){
    			if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getLeggings().getItemMeta().getLore().get(0).substring(2).equals(nameProduit)){
    				Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setLeggings(new ItemStack(Material.AIR));
    			}
			}
			break;
		case 4:
			if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getBoots() != null){
    			if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getBoots().getItemMeta().getLore().get(0).substring(2).equals(nameProduit)){
    				Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setBoots(new ItemStack(Material.AIR));
    			}
			}
			break;
		case 5:
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET bonus = '' WHERE namePlayer = '"+playerHub.getPlayerName()+"'");
			playerHub.getBonusFunction();
			break;
		case 6:
			if(playerHub.getGrade().equals(nameProduit)){
				for(int i = 0; i < Grades.getGradesList().size(); i++){
					if((Grades.getGradesList().get(i).isDefaultGrade())){
						Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET grade = '"+Grades.getGradesList().get(i).getGrade()+"' WHERE namePlayer = '"+playerHub.getPlayerName()+"'");
						if(Grades.getGradesList().get(i).getGradeDisplay().equals("")){
							Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("�eVous �tes maintenant �a"+Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGrade()+"�e.");
						} else {
							Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("�eVous �tes maintenant �a"+Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeDisplay()+"�e.");
						}
						playerHub.UpdateGrades();
						break;
					}
				}
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
