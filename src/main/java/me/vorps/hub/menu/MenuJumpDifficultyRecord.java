package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.JumpDifficulty;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 19/05/2016 at 17:32.
 */
public class MenuJumpDifficultyRecord extends MenuRecursive{

    private Jumps jumps;

    private MenuJumpDifficultyRecord(UUID uuid, ArrayList<ItemBuilder> list, Jumps jumps){
        super(uuid, jumps.getMenu().getIds(),  Bukkit.createInventory(null, jumps.getMenu().getSize(), jumps.getMenu().getLabel(PlayerData.getLang(uuid))), jumps.getMenu().getModel(), list, PlayerData.getLang(uuid), jumps.getMenu().getLineSize(), jumps.getMenu().getStart(), jumps.getMenu().getExclude(), Type.DYNAMIQUE, Hub.getInstance());
        this.jumps = jumps;
    }

    public void initMenu(UUID uuid, int page){
        super.setItem(4, jumps.getIcon(PlayerData.getLang(uuid)).get());
        super.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu jump"}).get());
    }

    public static void createMenu(UUID uuid, Jumps jumps){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        /*for(JumpDifficulty jumpDifficulty : jumps.getJumpDifficulty()){
            list.add(jumpDifficulty.getIcon().get(PlayerData.getLang(uuid)).withLore(jumpDifficulty.getLore(jumps.getJump())));
        }*/
        new MenuJumpDifficultyRecord(uuid, list, jumps);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        /*JumpDifficulty jumpDifficulty = JumpDifficulty.getJumpDifficultyLabel(itemStack.getItemMeta().getDisplayName(), PlayerData.getLang(uuid));
        if(jumpDifficulty != null){
            MenuJumpRecord.createMenu(uuid, jumps, jumpDifficulty);
        }*/
    }

}
