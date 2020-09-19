package me.vorps.hub.utils;

import me.vorps.hub.Object.Products;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class RemovePlayerProduit {
	public static void boutiqueRemovePlayerProduit(Player player, Products products, String nameProduct){
        /*PlayerData playerData = PlayerData.getPlayerData(player.getName());
		player.sendMessage("§7Votre produit : "+nameProduct+" a été retiré.");
        try{
            Database.CORE.getDatabase().delete("player_product", "pp_player = '"+ player.getName()+"' && pp_product = '"+nameProduct+"'");
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
                Database.CORE.getDatabase().updateTable("player_setting", "ps_player = '"+ player.getName() +"'", new DatabaseManager.Values("ps_bonus", Settings.getDefaultBonus().getBonus()));
            } catch (SqlException e){
                e.printStackTrace();
            }
			playerData.setBonus(Settings.getDefaultBonus().getBonus());
			break;
		case 6:
			if(playerData.getRank().getRank().equals(nameProduct)){
                try {
                    Database.CORE.getDatabase().updateTable("player_setting", "ps_player = '"+ player.getName() +"'", new DatabaseManager.Values("ps_grade", Settings.getDefaultRank().getRank()));
                } catch (SqlException e){
                    e.printStackTrace();
                }
                //playerData.updateGrades();
                player.sendMessage("§eVous êtes maintenant §a"+playerData.getRank()+"§e.");
			}
			break;
		case 7:
            Particle particle =  PlayerData.getPlayerData(player.getName()).getParticle();
            if(particle != null && particle.getParticle().equals(products.getName())){
                PlayerData.getPlayerData(player.getName()).setParticle(null);
            }
			break;
		case 8:
			break;
		case 9:
			break;
		default:
			break;
		}
*/	}
}
