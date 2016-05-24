package me.vorps.hub.menu;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import me.vorps.fortycube.menu.Menu;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.fortycube.utils.ConvertMillis;
import me.vorps.fortycube.utils.Data;
import me.vorps.fortycube.utils.Lang;
import me.vorps.hub.Hub;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuTransactions extends MenuRecursive{

	public MenuTransactions(Player player, ArrayList<Item> list){
        super(null, Bukkit.createInventory(null, 45, "§6Transactions éffectuées"), null, list, PlayerData.getPlayerData(player.getName()).getLang(), 9, 0);
        initMenu(player, 1);
        player.openInventory(menu);
	}

    public void initMenu(Player player, int page){
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page, Type.STATIC);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        ArrayList<Item> list = new ArrayList<>();
        for(int i = 0; i < playerData.getProducts().size(); i++){
            Products products = Products.getProduct(playerData.getProducts().get(i).getProduct());
            List<String> des = new ArrayList<>();
            des.add("§7Prix : §a"+products.getPrice()+"§7 "+products.getMoney());
            if(products.getTime() > 0){
                des.add("§7Temps : §a"+ ConvertMillis.convertMillisToDate(products.getTime()));
                des.add("§7Temps restant: §a"+ConvertMillis.convertMillisToDate((playerData.getProducts().get(i).getDate()+products.getTime())-System.currentTimeMillis()));
            }
            des.add("§7Date de l'achat : §a"+ Data.getDATE_FORMAT().format(new Timestamp(playerData.getProducts().get(i).getDate())));
            list.add(new Item(Material.PAPER).withName("§6"+ playerData.getProducts().get(i).getProduct()).withLore(des.toArray(des.toArray(new String[des.size()]))));
        }
        new MenuTransactions(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuBoutique.createMenu(player);
                break;
            default:
                break;
        }
    }
}
