package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.JumpDifficulty;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.Object.PlayerJumpRecord;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 22/05/2016 at 04:01.
 */
public class MenuJumpRecord extends MenuRecursive {
    Jumps jump;
    JumpDifficulty jumpDifficulty;

    private MenuJumpRecord(UUID uuid, Jumps jump, ArrayList<ItemBuilder> list, JumpDifficulty jumpDifficulty){
        super(jumpDifficulty.getMenu().getIds(), Bukkit.createInventory(null, jumpDifficulty.getMenu().getSize(), jumpDifficulty.getMenu().getLabel().get(PlayerData.getLang(uuid))),  jumpDifficulty.getMenu().getModel(), list, PlayerData.getLang(uuid), jumpDifficulty.getMenu().getLineSize(), jumpDifficulty.getMenu().getStart(), jumpDifficulty.getMenu().getExclude(), Type.STATIC, Hub.getInstance());
        this.jump = jump;
        this.jumpDifficulty = jumpDifficulty;
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
    }

    public void initMenu(UUID uuid, int page){
        menu.setItem(27, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        menu.setItem(4, jumpDifficulty.getIcon().get(PlayerData.getLang(uuid)).withLore(jumpDifficulty.getLore(jump.getJump())).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid, Jumps jumps, JumpDifficulty jumpDifficulty){
        //new MenuJumpRecord(uuid, jumps, PlayerJumpRecord.getListPlayer(jumps.getJump(), PlayerData.getLang(uuid), jumpDifficulty.getName()), jumpDifficulty);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                MenuJumpDifficultyRecord.createMenu(uuid, jump);
                break;
            case PAPER:
                initMenu(uuid, ++page);
                break;
            case MAP:
                initMenu(uuid, --page);
                break;
            default:
                break;
        }
    }
}
