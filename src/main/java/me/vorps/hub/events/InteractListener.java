package me.vorps.hub.events;

import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.gadget.Gadgets;
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

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class InteractListener implements Listener{

    @EventHandler
    public void onInteractItem(PlayerInteractEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        if(PlayerData.isBuild(uuid) /*|| playerData.getJump().isInJump()*/){
            return;
        }
        if(e.getAction().equals(Action.PHYSICAL)){
            //playerData.getJump().jumpManager(player,  e.getClickedBlock());
            return;
        }

        if(e.getItem() != null){
            ItemStack is = e.getItem();
            Action action = e.getAction();
            /*Products products = Products.getProductItem(is, PlayerData.getLang(uuid));
            if(products != null){
                if(products.getType() == 8){
                    if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)){
                        Gadgets gadgets =  playerData.getGadget();
                        if(gadgets != null){
                            gadgets.run();
                        }
                        player.updateInventory();
                    } else {
                        player.getInventory().clear(4);
                        playerData.setGadgets(null);
                        player.updateInventory();
                    }
                }
            }*/
            /*if(is.getType().equals(Material.WRITTEN_BOOK)){
                if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                    player.getInventory().clear(4);
                }
                e.setCancelled(false);
            }*/
            if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)){
                switch(is.getType()){
                    case COMPASS:
                        MenuPrincipal.createMenu(uuid);
                        break;
                    case SKELETON_SKULL:
                        new MenuProfil(uuid);
                        break;
                    case BLAZE_POWDER:
                        //CoolDown.visibilityAction(player, playerData);
                        break;
                    case ENDER_CHEST:
                        MenuBoutique.createMenu(uuid);
                        break;
                    case BARRIER:
                        //playerData.getJump().stopJump(player, true);
                        break;
                    case ARROW:
                        //playerData.getJump().failManager(player);
                        break;
                    default:
                        break;
                }
            }
        }
        e.setCancelled(true);
    }
}
