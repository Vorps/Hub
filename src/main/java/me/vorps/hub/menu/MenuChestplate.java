package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.hub.utils.Purchase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuChestplate extends MenuRecursive {

    private MenuChestplate(UUID uuid, ArrayList<ItemBuilder> list) {
        super(new byte[] {14, 5}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > "), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {36, 1}, {44, 1}, {46, 1}, {47, 0}, {48, 1}, {49, 0}, {50, 1}, {51, 0}, {52, 1}, {53, 0}}, list, PlayerData.getLang(uuid), 7, 9, new int[]{12, 13, 14, 19, 25}, Type.STATIC, Hub.getInstance());
		initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
	}

    @Override
    public void initMenu(UUID uuid, int page){
        menu.clear();
        menu.setItem(4, new ItemBuilder(Material.GOLDEN_CHESTPLATE).withName("§6Plastrons").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(13, new ItemBuilder(Material.BARRIER).withName("§6Retirer mon plastron").withLore(new String[]{"§7Retire votre plastron actuel"}).get());
        menu.setItem(45, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Products.getProduct(2).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getLang(uuid)).withLore(new Purchase(uuid, "ce plastron sur votre torse").purchase(product.getName()))));
        new MenuChestplate(uuid, list);
    }
    
    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                new MenuCostume(uuid);
                break;
            case BARRIER:
                if(Bukkit.getPlayer(uuid).getInventory().getChestplate() != null){
                    Bukkit.getPlayer(uuid).getInventory().setChestplate(new ItemStack(Material.AIR));
                    Bukkit.getPlayer(uuid).sendMessage("§ePlastron retiré");
                    MenuChestplate.createMenu(uuid);
                }
                break;
            case PAPER:
                initMenu(uuid, page+1);
                break;
            case MAP:
                initMenu(uuid, page-1);
                break;
            default:
                break;
        }
    }
}
