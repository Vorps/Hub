package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.Menu;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.Game;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuBoutique extends MenuRecursive{

	public MenuBoutique(Player player, ArrayList<Item> list){
        super(new byte[] {11, 5}, Bukkit.createInventory(null, 45, "§6Boutique"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {4, 0}, {6, 0}, {7, 1}, {8, 0}, {18, 0}, {26, 1}, {37, 1}, {38, 0}, {40, 0}, {42, 0}, {43, 1}, {44, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9);
        initMenu(player, 1);
        player.openInventory(menu);
	}

    public void initMenu(Player player, int page){
        menu.setItem(3, new Item(322).withData((byte) 1).withName("§6Obtenir des étoiles").withLore(new String[] {"§7Obtenir des étoiles"}).get());
        menu.setItem(5, new Item(Material.PAPER).withName("§6Transactions éffectuées").withLore(new String[] {"§7Historique des achats"}).get());
        menu.setItem(9, new Item(Material.BONE).withName("§6Pets").withLore(new String[] {"§7Acheter un pets"}).get());
        menu.setItem(17, new Item(Material.BLAZE_ROD).withName("§6Gadgets").withLore(new String[] {"§7Acheter des gadgets"}).get());
        menu.setItem(27, new Item(Material.GLOWSTONE_DUST).withName("§6Particules").withLore(new String[] {"§7Acheter des effets de particules"}).get());
        menu.setItem(35, new Item(Material.DIAMOND_CHESTPLATE).withName("§6Costumes").withLore(new String[] {"§7Acheter des costumes"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        menu.setItem(39, new Item(Material.ENCHANTMENT_TABLE).withName("§6Grades").withLore(new String[] {"§7Obtenire un grade"}).get());
        menu.setItem(41, new Item(PotionType.STRENGTH).withName("§6Bonus").withLore(new String[] {"§7Obtenire des bonus"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        Game.getGameList().values().forEach((Game game) -> list.add(game.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang())));
        new MenuBoutique(player, list);
    }


    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case PAPER:
                MenuTransactions.createMenu(player);
                break;
            case BONE:
                MenuPets.createMenu(player);
                break;
            case BLAZE_ROD:
                MenuGadgets.createMenu(player);
                break;
            case GLOWSTONE_DUST:
                MenuParticles.createMenu(player);
                break;
            case DIAMOND_CHESTPLATE:
                new MenuCostume(player);
                break;
            case ENCHANTMENT_TABLE:
                MenuGrades.createMenu(player);
                break;
            case POTION:
                MenuBonus.createMenu(player);
                break;
            case ARROW:
                player.closeInventory();
                break;
            default:
                Game game = Game.getGame(itemStack.getType().getId(), PlayerData.getPlayerData(player.getName()).getLang());
                if(game != null){
                    MenuShopGame.createMenu(player, game);
                }
                break;
        }
        switch (itemStack.getType().getId()){
            case 322:
                player.sendMessage("§6Boutique en ligne : §b§n"+ Settings.getSiteWeb());
                player.closeInventory();
                break;
            default:
                break;
        }
    }
}
