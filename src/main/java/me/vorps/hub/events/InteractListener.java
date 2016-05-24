package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.menu.MenuBoutique;
import me.vorps.hub.menu.MenuPrincipal;
import me.vorps.hub.menu.MenuProfil;
import me.vorps.hub.utils.CoolDown;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class InteractListener implements Listener{

    @EventHandler
    public void onInterractItem(PlayerInteractEvent e){
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(e.getAction().equals(Action.PHYSICAL)){
            playerData.getJump().jumpManager(player,  e.getClickedBlock());
        }
        if(!playerData.isBuild() || playerData.getJump().isInJump()){
            e.setCancelled(true);
        }
        if(!e.getAction().equals(Action.PHYSICAL) && e.isCancelled() && e.getItem() != null){
            ItemStack is = e.getItem();
            Action action = e.getAction();
            if(is.getType().equals(Material.WRITTEN_BOOK)){
                if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                    player.getInventory().clear(4);
                }
                e.setCancelled(false);
            } else if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)){
                switch(is.getType()){
                    case COMPASS:
                        MenuPrincipal.createMenu(player);
                        break;
                    case SKULL_ITEM:
                        new MenuProfil(player);
                        break;
                    case BLAZE_POWDER:
                        CoolDown.visibilityAction(player, playerData);
                        break;
                    case ENDER_CHEST:
                        MenuBoutique.createMenu(player);
                        break;
                    case BARRIER:
                       playerData.getJump().stopJump(player, true);
                        break;
                    case ARROW:
                        playerData.getJump().failManager(player);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
