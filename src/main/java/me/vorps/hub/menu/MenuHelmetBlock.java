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
public class MenuHelmetBlock extends MenuRecursive {

	private MenuHelmetBlock(UUID uuid, ArrayList<ItemBuilder> list) {
        super(uuid, new byte[]{10, 2, 6}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > Blocks"), new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 0}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 2}, {35, 2}, {36, 1}, {44, 1}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}, {52, 1}, {53, 0}}, list, PlayerData.getLang(uuid), 7, 9, new int[]{12, 13, 14}, Type.DYNAMIQUE, Hub.getInstance());
    }

    @Override
    public void initMenu(UUID uuid, int page){
        super.setItem(4, new ItemBuilder(Material.GOLDEN_HELMET).withName("§6Chapeaux").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        super.setItem(13, new ItemBuilder(Material.BARRIER).withName("§6Retirer mon chapeaux").withLore(new String[]{"§7Retire votre chapeaux actuel"}).get());
        super.setItem(45, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[]{"§7Retour au menu Costumes"}).get());
        this.getPage(page);
        if(menu.getItem(52).getType().getId() == 160){
            super.setItem(52, new ItemBuilder(Material.MAP).withName("§6Page Précedente -> Têtes").withLore(new String[]{"§7Retour au menu Têtes"}).get());
        }
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Products.getProduct(11).forEach((Products product) -> list.add(product.getItem(PlayerData.getLang(uuid)).withLore(new Purchase(uuid, "ce chapeau sur votre tête.").purchase(product.getName()))));
        new MenuHelmetBlock(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        new MenuCostume(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        switch (type) {
            case MAP:
                /*if(itemStack.getItemMeta().getDisplayName().equals("§6Page Précedente -> Têtes")){
                    MenuHelmetHead.createMenu(uuid);
                } else {
                    initMenu(uuid, page-1);
                }*/
                break;
            case BARRIER:
                /*if(Bukkit.getPlayer(uuid).getInventory().getHelmet() != null){
                    Bukkit.getPlayer(uuid).getInventory().setHelmet(new ItemStack(Material.AIR));
                    Bukkit.getPlayer(uuid).sendMessage("§eChapeau retiré");
                    MenuHelmetBlock.createMenu(uuid);
                }*/
                break;
            default:
                break;
        }
    }
}
