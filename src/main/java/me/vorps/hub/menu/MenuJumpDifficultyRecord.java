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
        super(jumps.getMenu().getIds(),  Bukkit.createInventory(null, jumps.getMenu().getSize(), jumps.getMenu().getLabel().get(PlayerData.getLang(uuid))), jumps.getMenu().getModel(), list, PlayerData.getLang(uuid), jumps.getMenu().getLineSize(), jumps.getMenu().getStart(), jumps.getMenu().getExclude(), Type.STATIC, Hub.getInstance());
        this.jumps = jumps;
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
    }

    public void initMenu(UUID uuid, int page){
        menu.setItem(4, jumps.getIcon().get(PlayerData.getLang(uuid)).get());
        menu.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu jump"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid, Jumps jumps){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        for(JumpDifficulty jumpDifficulty : jumps.getJumpDifficulty()){
            list.add(jumpDifficulty.getIcon().get(PlayerData.getLang(uuid)).withLore(jumpDifficulty.getLore(jumps.getJump())));
        }
        new MenuJumpDifficultyRecord(uuid, list, jumps);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            case PAPER:
                initMenu(uuid, page+1);
                break;
            case MAP:
                initMenu(uuid, page-1);
                break;
            default:
                break;
        }
        JumpDifficulty jumpDifficulty = JumpDifficulty.getJumpDifficultyLabel(itemStack.getItemMeta().getDisplayName(), PlayerData.getLang(uuid));
        if(jumpDifficulty != null){
            MenuJumpRecord.createMenu(uuid, jumps, jumpDifficulty);
        }
    }

}
