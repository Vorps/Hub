package sirmc.vorps.menu;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.Object.Products;
import sirmc.vorps.utils.ConvertMillis;
import sirmc.vorps.Hub;
import sirmc.vorps.utils.Item;

public class MenuTransactions {

	public static void HubmenuTransactions(PlayerHub playerHub, int page){
		Inventory menuTransactions;
		if(playerHub.getProducts().size() < page*45){
			menuTransactions = Bukkit.createInventory(null, 54, "§6Transactions éffectuées");
		} else {
			menuTransactions = Bukkit.createInventory(null, 54, "§6Transactions éffectuées : §a"+page);
		}
		int index = 0;
		for(int i = (page*45)-45; i < page*45 && i < playerHub.getProducts().size(); i++){
			Products products = Hub.instance.getListProducts().get(playerHub.getProducts().get(i).getProduct());
			List<String> des = new ArrayList<>();
			des.add("§7Prix : §a"+products.getPrice()+"§7 "+products.getMoney());
			if(products.getTime() > 0){
				des.add("§7Temps : §a"+ConvertMillis.ConvertMillisToDate(products.getTime()));
				des.add("§7Temps restant: §a"+ConvertMillis.ConvertMillisToDate((playerHub.getProducts().get(i).getDate()+products.getTime())-System.currentTimeMillis()));
			}
			des.add("§7Date de l'achat : §a"+ Hub.instance.getDATE_FORMAT().format(new Timestamp(playerHub.getProducts().get(i).getDate())));
			menuTransactions.setItem(index, new Item(Material.PAPER).withName("§6"+playerHub.getProducts().get(i).getProduct()).withLore(des.toArray(des.toArray(new String[des.size()]))).get());
			des.clear();
			index++;
		}
		menuTransactions.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
		if(page > 1){
			menuTransactions.setItem(46, new Item(Material.EMPTY_MAP).withName("§6<-page précedente").withLore(new String[] {"§7Page précédente transactions", ""+--page}).get());
		}
		if(playerHub.getProducts().size() > page*45){
			menuTransactions.setItem(53, new Item(Material.ARROW).withName("§6Page suivante ->").withLore(new String[] {"§7Page suivante transactions", ""+page++}).get());
		}
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuTransactions);
	}
}
