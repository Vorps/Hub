package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.Game;
import me.vorps.hub.PlayerData;
import me.vorps.hub.data.SettingsHub;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuBoutique extends MenuRecursive{

	public MenuBoutique(UUID uuid, ArrayList<ItemBuilder> list){
        super(new byte[] {11, 5}, Bukkit.createInventory(null, 45, "§6Boutique"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {4, 0}, {6, 0}, {7, 1}, {8, 0}, {18, 0}, {26, 1}, {37, 1}, {38, 0}, {40, 0}, {42, 0}, {43, 1}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.STATIC, Hub.getInstance());
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(this.menu);
	}

    public void initMenu(UUID uuid, int page){
        menu.setItem(3, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).withName("§6Obtenir des étoiles").withLore(new String[] {"§7Obtenir des étoiles"}).get());
        menu.setItem(5, new ItemBuilder(Material.PAPER).withName("§6Transactions éffectuées").withLore(new String[] {"§7Historique des achats"}).get());
        menu.setItem(9, new ItemBuilder(Material.BONE).withName("§6Pets").withLore(new String[] {"§7Acheter un pets"}).get());
        menu.setItem(17, new ItemBuilder(Material.BLAZE_ROD).withName("§6Gadgets").withLore(new String[] {"§7Acheter des gadgets"}).get());
        menu.setItem(27, new ItemBuilder(Material.GLOWSTONE_DUST).withName("§6Particules").withLore(new String[] {"§7Acheter des effets de particules"}).get());
        menu.setItem(35, new ItemBuilder(Material.DIAMOND_CHESTPLATE).withName("§6Costumes").withLore(new String[] {"§7Acheter des costumes"}).get());
        menu.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        menu.setItem(39, new ItemBuilder(Material.ENCHANTING_TABLE).withName("§6Grades").withLore(new String[] {"§7Obtenire un grade"}).get());
        menu.setItem(41, new ItemBuilder(PotionType.STRENGTH).withName("§6Bonus").withLore(new String[] {"§7Obtenire des bonus"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Game.getGameList().values().forEach((Game game) -> list.add(game.getIcon().get(PlayerData.getLang(uuid))));
        new MenuBoutique(uuid, list);
    }


    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case PAPER:
                MenuTransactions.createMenu(uuid);
                break;
            case BONE:
                MenuPets.createMenu(uuid);
                break;
            case BLAZE_ROD:
                MenuGadgets.createMenu(uuid);
                break;
            case GLOWSTONE_DUST:
                MenuParticles.createMenu(uuid);
                break;
            case DIAMOND_CHESTPLATE:
                new MenuCostume(uuid);
                break;
            case ENCHANTING_TABLE:
                MenuGrades.createMenu(uuid);
                break;
            case POTION:
                MenuBonus.createMenu(uuid);
                break;
            case ARROW:
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            default:
                Game game = Game.getGame(itemStack.getType().getId(), PlayerData.getLang(uuid));
                if(game != null){
                    MenuShopGame.createMenu(uuid, game);
                }
                break;
        }
        switch (itemStack.getType().getId()){
            case 322:
                Bukkit.getPlayer(uuid).sendMessage("§6Boutique en ligne : §b§n"+ SettingsHub.getSite());
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            default:
                break;
        }
    }
}
