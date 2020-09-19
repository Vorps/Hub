package me.vorps.hub.menu;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.vorps.api.data.Data;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.Menu;
import net.vorps.api.menu.MenuRecursive;
import net.vorps.api.utils.ConvertMillis;
import me.vorps.hub.Hub;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuTransactions extends MenuRecursive{

	public MenuTransactions(UUID uuid, ArrayList<ItemBuilder> list){
        super(null, Bukkit.createInventory(null, 45, "§6Transactions éffectuées"), null, list, PlayerData.getLang(uuid), 9, 0, Type.STATIC, Hub.getInstance());
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
	}

	@Override
    public void initMenu(UUID uuid, int page){
        menu.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        PlayerData playerData = PlayerData.getPlayerData(uuid);
        ArrayList<ItemBuilder> list = new ArrayList<>();
        for(int i = 0; i < playerData.getProducts().size(); i++){
            Products products = Products.getProduct(playerData.getProducts().get(i).getProduct());
            List<String> des = new ArrayList<>();
            des.add("§7Prix : §a"+products.getPrice()+"§7 "+products.getMoney());
            if(products.getTime() > 0){
                des.add("§7Temps : §a"+ ConvertMillis.convertMillisToTime(products.getTime()));
                des.add("§7Temps restant: §a"+ConvertMillis.convertMillisToTime((playerData.getProducts().get(i).getDate()+products.getTime())-System.currentTimeMillis()));
            }
            des.add("§7Date de l'achat : §a"+ Data.FORMAT_DAY_MONTH_YEAR.format(new Timestamp(playerData.getProducts().get(i).getDate())));
            list.add(new ItemBuilder(Material.PAPER).withName("§6"+ playerData.getProducts().get(i).getProduct()).withLore(des.toArray(des.toArray(new String[des.size()]))));
        }
        new MenuTransactions(uuid, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                MenuBoutique.createMenu(uuid);
                break;
            default:
                break;
        }
    }
}
