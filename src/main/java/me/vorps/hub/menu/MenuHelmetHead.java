package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.MenuRecursive;
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

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuHelmetHead extends MenuRecursive {

    private MenuHelmetHead(Player player, ArrayList<Item> list) {
        super(new byte[]{10, 2, 6}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > Têtes"), new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 0}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 2}, {35, 2}, {36, 1}, {44, 1}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}, {52, 1}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9, new int[]{12, 13, 14});
        initMenu(player, 1);
        player.openInventory(menu);
    }

    @Override
    public void initMenu(Player player, int page){
        menu.clear();
        menu.setItem(4, new Item(Material.GOLD_HELMET).withName("§6Chapeaux").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mon chapeaux").withLore(new String[]{"§7Retire votre chapeaux actuel"}).get());
        menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[]{"§7Retour au menu Costumes"}).get());
        this.getPage(page);
        System.out.println(menu.getItem(52).getType().getId());
        if(menu.getItem(52).getType().getId() == 160){
            menu.setItem(52, new Item(Material.EMPTY_MAP).withName("§6<- Page Précedente -> Chapeaux").withLore(new String[]{"§7Retour au menu Chapeaux"}).get());
        }
        if(menu.getItem(53) == null){
            menu.setItem(53, new Item(Material.PAPER).withName("§6Page Suivante -> Block").withLore(new String[]{"§7Page Block"}).get());
        }
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        Products.getProduct(10).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getPlayerData(player.getName()).getLang()).withLore(new Purchase(player.getName(), "ce chapeau sur votre tête.").purchase(product.getName()))));
        new MenuHelmetHead(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                new MenuCostume(player);
                break;
            case EMPTY_MAP:
                if(itemStack.getItemMeta().getDisplayName().equals("§6<- Page Précedente -> Chapeaux")){
                    MenuHelmetHat.createMenu(player);
                } else {
                    initMenu(player, page-1);
                }
                break;
            case PAPER:
                if(itemStack.getItemMeta().getDisplayName().equals("§6Page Suivante -> Block")){
                    MenuHelmetBlock.createMenu(player);
                } else {
                    initMenu(player, page+1);
                }
            case BARRIER:
                if(player.getInventory().getHelmet() != null){
                    player.getInventory().setHelmet(new ItemStack(Material.AIR));
                    player.sendMessage("§eChapeau retiré");
                    MenuHelmetHead.createMenu(player);
                }
                break;
            default:
                break;
        }
    }
}
