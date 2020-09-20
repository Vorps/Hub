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
        super(uuid, null, Bukkit.createInventory(null, 45, "§6Transactions éffectuées"), null, list, PlayerData.getLang(uuid), 9, 0, Type.DYNAMIQUE, Hub.getInstance());
	}

	@Override
    public void initMenu(UUID uuid, int page){
        super.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        PlayerData.getProductsPlayers(uuid).forEach(productPlayer -> {
            System.out.println("TEST");
            Products product = Products.getProduct(productPlayer.getProduct());
            List<String> des = new ArrayList<>();
            des.add("§7Prix : §a"+product.getPrice()+"§7 "+product.getMoney());
            if(product.getTime() > 0){
                des.add("§7Temps : §a"+ ConvertMillis.convertMillisToTime(product.getTime()));
                des.add("§7Temps restant: §a"+ConvertMillis.convertMillisToTime((productPlayer.getDate()+product.getTime())-System.currentTimeMillis()));
            }
            des.add("§7Date de l'achat : §a"+ Data.FORMAT_DAY_MONTH_YEAR.format(new Timestamp(productPlayer.getDate())));
            list.add(new ItemBuilder(Material.PAPER).withName("§6"+ product.getName()).withLore(des.toArray(des.toArray(new String[0]))));
        });

        new MenuTransactions(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        MenuBoutique.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {

    }
}
