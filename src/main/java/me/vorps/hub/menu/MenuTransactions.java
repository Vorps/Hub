package me.vorps.hub.menu;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import me.vorps.fortycube.utils.ConvertMillis;
import me.vorps.hub.Hub;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuTransactions extends Menu{

	public MenuTransactions(Player player, int page){
        super(null, null, null);
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        playerData.getProductsPlayerFunction();
		if(playerData.getProducts().size() < page*45){
			menu = Bukkit.createInventory(null, 54, "§6Transactions éffectuées");
		} else {
            menu = Bukkit.createInventory(null, 54, "§6Transactions éffectuées : §a"+page);
		}
		int index = 0;
		for(int i = (page*45)-45; i < page*45 && i < playerData.getProducts().size(); i++){
			Products products = Products.getProduct(playerData.getProducts().get(i).getProduct());
			List<String> des = new ArrayList<>();
			des.add("§7Prix : §a"+products.getPrice()+"§7 "+products.getMoney());
			if(products.getTime() > 0){
				des.add("§7Temps : §a"+ ConvertMillis.convertMillisToDate(products.getTime()));
				des.add("§7Temps restant: §a"+ConvertMillis.convertMillisToDate((playerData.getProducts().get(i).getDate()+products.getTime())-System.currentTimeMillis()));
			}
			des.add("§7Date de l'achat : §a"+ Hub.getInstance().getDATE_FORMAT().format(new Timestamp(playerData.getProducts().get(i).getDate())));
            menu.setItem(index, new Item(Material.PAPER).withName("§6"+ playerData.getProducts().get(i).getProduct()).withLore(des.toArray(des.toArray(new String[des.size()]))).get());
			des.clear();
			index++;
		}
        menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
		if(page > 1){
            menu.setItem(46, new Item(Material.EMPTY_MAP).withName("§6<-page précedente").withLore(new String[] {"§7Page précédente transactions", ""+--page}).get());
		}
		if(playerData.getProducts().size() > page*45){
            menu.setItem(53, new Item(Material.ARROW).withName("§6Page suivante ->").withLore(new String[] {"§7Page suivante transactions", ""+page+1}).get());
		}
		player.openInventory(menu);
	}
}
