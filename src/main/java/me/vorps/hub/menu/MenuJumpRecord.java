package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.JumpDifficulty;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.Object.Menu;
import me.vorps.hub.Object.PlayerJumpRecord;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 22/05/2016 at 04:01.
 */
public class MenuJumpRecord extends MenuRecursive {
    Jumps jump;
    JumpDifficulty jumpDifficulty;

    private MenuJumpRecord(Player player, Jumps jump, ArrayList<Item> list, JumpDifficulty jumpDifficulty){
        super(jumpDifficulty.getMenu().getIds(), Bukkit.createInventory(null, jumpDifficulty.getMenu().getSize(), jumpDifficulty.getMenu().getLabel().get(PlayerData.getPlayerData(player.getName()).getLang())),  jumpDifficulty.getMenu().getModel(), list, PlayerData.getPlayerData(player.getName()).getLang(), jumpDifficulty.getMenu().getLineSize(), jumpDifficulty.getMenu().getStart(), jumpDifficulty.getMenu().getExclude());
        this.jump = jump;
        this.jumpDifficulty = jumpDifficulty;
        initMenu(player, 1);
        player.openInventory(menu);
    }

    public void initMenu(Player player, int page){
        menu.setItem(27, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        menu.setItem(4, jumpDifficulty.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang()).withLore(jumpDifficulty.getLore(jump.getJump())).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player, Jumps jumps, JumpDifficulty jumpDifficulty){
        new MenuJumpRecord(player, jumps, PlayerJumpRecord.getListPlayer(jumps.getJump(), PlayerData.getPlayerData(player.getName()).getLang(), jumpDifficulty.getName()), jumpDifficulty);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuJumpDifficultyRecord.createMenu(player, jump);
                break;
            case PAPER:
                initMenu(player, ++page);
                break;
            case EMPTY_MAP:
                initMenu(player, --page);
                break;
            default:
                break;
        }
    }
}
