package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.JumpDifficulty;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 19/05/2016 at 17:32.
 */
public class MenuJumpDifficultyRecord extends MenuRecursive{

    private Jumps jumps;

    private MenuJumpDifficultyRecord(Player player, ArrayList<Item> list, Jumps jumps){
        super(jumps.getMenu().getIds(),  Bukkit.createInventory(null, jumps.getMenu().getSize(), jumps.getMenu().getLabel().get(PlayerData.getPlayerData(player.getName()).getLang())), jumps.getMenu().getModel(), list, PlayerData.getPlayerData(player.getName()).getLang(), jumps.getMenu().getLineSize(), jumps.getMenu().getStart(), jumps.getMenu().getExclude());
        this.jumps = jumps;
        initMenu(player, 1);
        player.openInventory(menu);
    }

    public void initMenu(Player player, int page){
        menu.setItem(4, jumps.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang()).get());
        menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu jump"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player, Jumps jumps){
        ArrayList<Item> list = new ArrayList<>();
        for(JumpDifficulty jumpDifficulty : jumps.getJumpDifficulty()){
            list.add(jumpDifficulty.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang()).withLore(jumpDifficulty.getLore(jumps.getJump())));
        }
        new MenuJumpDifficultyRecord(player, list, jumps);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                player.closeInventory();
                break;
            case PAPER:
                initMenu(player, page+1);
                break;
            case EMPTY_MAP:
                initMenu(player, page-1);
                break;
            default:
                break;
        }
        JumpDifficulty jumpDifficulty = JumpDifficulty.getJumpDifficultyLabel(itemStack.getItemMeta().getDisplayName(), PlayerData.getPlayerData(player.getName()).getLang());
        if(jumpDifficulty != null){
            MenuJumpRecord.createMenu(player, jumps, jumpDifficulty);
        }
    }

}
