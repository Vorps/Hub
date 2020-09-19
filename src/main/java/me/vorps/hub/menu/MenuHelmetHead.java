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
public class MenuHelmetHead extends MenuRecursive {

    private MenuHelmetHead(UUID uuid, ArrayList<ItemBuilder> list) {
        super(new byte[]{10, 2, 6}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > Têtes"), new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 0}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 2}, {35, 2}, {36, 1}, {44, 1}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}, {52, 1}}, list, PlayerData.getLang(uuid), 7, 9, new int[]{12, 13, 14}, Type.STATIC, Hub.getInstance());
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
    }

    @Override
    public void initMenu(UUID uuid, int page){
        menu.clear();
        menu.setItem(4, new ItemBuilder(Material.GOLDEN_HELMET).withName("§6Chapeaux").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(13, new ItemBuilder(Material.BARRIER).withName("§6Retirer mon chapeaux").withLore(new String[]{"§7Retire votre chapeaux actuel"}).get());
        menu.setItem(45, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[]{"§7Retour au menu Costumes"}).get());
        this.getPage(page);
        System.out.println(menu.getItem(52).getType().getId());
        if(menu.getItem(52).getType().getId() == 160){
            menu.setItem(52, new ItemBuilder(Material.MAP).withName("§6<- Page Précedente -> Chapeaux").withLore(new String[]{"§7Retour au menu Chapeaux"}).get());
        }
        if(menu.getItem(53) == null){
            menu.setItem(53, new ItemBuilder(Material.PAPER).withName("§6Page Suivante -> Block").withLore(new String[]{"§7Page Block"}).get());
        }
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Products.getProduct(10).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getLang(uuid)).withLore(new Purchase(uuid, "ce chapeau sur votre tête.").purchase(product.getName()))));
        new MenuHelmetHead(uuid, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                new MenuCostume(uuid);
                break;
            case MAP:
                if(itemStack.getItemMeta().getDisplayName().equals("§6<- Page Précedente -> Chapeaux")){
                    MenuHelmetHat.createMenu(uuid);
                } else {
                    initMenu(uuid, page-1);
                }
                break;
            case PAPER:
                if(itemStack.getItemMeta().getDisplayName().equals("§6Page Suivante -> Block")){
                    MenuHelmetBlock.createMenu(uuid);
                } else {
                    initMenu(uuid, page+1);
                }
            case BARRIER:
                if(Bukkit.getPlayer(uuid).getInventory().getHelmet() != null){
                    Bukkit.getPlayer(uuid).getInventory().setHelmet(new ItemStack(Material.AIR));
                    Bukkit.getPlayer(uuid).sendMessage("§eChapeau retiré");
                    MenuHelmetHead.createMenu(uuid);
                }
                break;
            default:
                break;
        }
    }
}
