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
        super(uuid, jumpDifficulty.getMenu().getIds(), Bukkit.createInventory(null, jumpDifficulty.getMenu().getSize(), jumpDifficulty.getMenu().getLabel(PlayerData.getLang(uuid))),  jumpDifficulty.getMenu().getModel(), list, PlayerData.getLang(uuid), jumpDifficulty.getMenu().getLineSize(), jumpDifficulty.getMenu().getStart(), jumpDifficulty.getMenu().getExclude(), Type.DYNAMIQUE, Hub.getInstance());
        this.jump = jump;
        this.jumpDifficulty = jumpDifficulty;
    }

    public void initMenu(UUID uuid, int page){
        super.setItem(27, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        //menu.setItem(4, jumpDifficulty.getIcon().get(PlayerData.getLang(uuid)).withLore(jumpDifficulty.getLore(jump.getJump())).get());
    }

    public static void createMenu(UUID uuid, Jumps jumps, JumpDifficulty jumpDifficulty){
        //new MenuJumpRecord(uuid, jumps, PlayerJumpRecord.getListPlayer(jumps.getJump(), PlayerData.getLang(uuid), jumpDifficulty.getName()), jumpDifficulty);
    }

    @Override
    protected void back(UUID uuid) {
        MenuJumpDifficultyRecord.createMenu(uuid, jump);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
    }
}
