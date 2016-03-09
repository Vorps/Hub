package me.vorps.hub.events;

import me.vorps.fortycube.utils.Title;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.menu.MenuBoutique;
import me.vorps.hub.menu.MenuPrincipal;
import me.vorps.hub.menu.MenuProfil;
import me.vorps.hub.menu.Navigator;
import me.vorps.hub.utils.CoolDown;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Vorps on 01/02/2016.
 */
public class InteractListener implements Listener{
    @EventHandler
    public void onInterractItem(PlayerInteractEvent e){
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(e.getAction().equals(Action.PHYSICAL)){
            if(e.getClickedBlock().getType() == Material.GOLD_PLATE){
                Location loc = e.getClickedBlock().getLocation();
                if(!playerData.isInJumps()){
                    Jumps.getListJumps().forEach((Jumps jump) -> {
                        if(loc.getBlockX() == jump.getX() && loc.getBlockY()  == jump.getY() && loc.getBlockZ()  == jump.getZ()){
                            if(playerData.isFly()){
                                player.sendMessage("§6Fly (§4Désactivé§6).");
                            }
                            if(playerData.isDoubleJumps()){
                                player.sendMessage("§6Double Jump (§4Désactivé§6).");
                            }
                            if(playerData.isBuild() || player.getGameMode() == GameMode.CREATIVE){
                                player.sendMessage("§6Mode build (§4Désactivé§6).");
                                player.getInventory().clear();
                                Navigator.navigator(playerData, player);
                                player.setGameMode(GameMode.ADVENTURE);
                            }
                            player.setAllowFlight(false);
                            Location locJump =  new Location(player.getWorld(), jump.getXPlayer(), jump.getYPlayer(), jump.getZPlayer());
                            locJump.setYaw(jump.getYaw());
                            playerData.setLocCheckPoint(locJump);
                            playerData.setInJumps(true);
                            playerData.setNumberCheckPoint(0);
                            playerData.setNumberTrying(0);
                            player.sendMessage(jump.getMessage());
                            playerData.setJump(jump);
                        }
                    });
                } else if(playerData.getJump() != null && playerData.isInJumps()){
                    if(playerData.getNumberCheckPoint() == playerData.getJump().getJumpsSettings().size() && playerData.isInJumps()){
                        playerData.setInJumps(false);
                        playerData.setNumberCheckPoint(0);
                        playerData.setNumberTrying(0);
                        playerData.setJump(null);
                        playerData.setLocCheckPoint(null);
                        playerData.teleportSpawn();
                        Title title = new Title("§aFélicitation !!! ", "§6Vous avez gagné un §ccookie.");
                        title.setStayTime(10);
                        title.send(player);
                    }
                }
            } else if(e.getClickedBlock().getType() == Material.IRON_PLATE){
                if(playerData.getJump() != null){
                    Location loc = e.getClickedBlock().getLocation();
                    if(!loc.equals(playerData.getLocCheckPoint())){
                        playerData.setNumberCheckPoint(playerData.getNumberCheckPoint()+1);
                        playerData.setLocCheckPoint(loc);
                        player.sendMessage("§eCheckPoint n§§c"+ playerData.getNumberCheckPoint()+"§e !!!");
                    }
                    playerData.setInJumps(true);
                    if(playerData.isFly()){
                        playerData.setFly(false);
                    }
                    if(playerData.isDoubleJumps()){
                        playerData.setDoubleJumps(false);
                    }
                    player.setAllowFlight(false);
                    if(playerData.isBuild() || player.getGameMode() == GameMode.CREATIVE){
                        playerData.setBuild(false);
                        player.getInventory().clear();
                        Navigator.navigator(playerData, player);
                        player.setGameMode(GameMode.ADVENTURE);
                    }
                }
            }
        }

        if(!playerData.isBuild() || playerData.isInJumps()){
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
                        new MenuPrincipal(player);
                        break;
                    case SKULL_ITEM:
                        new MenuProfil(player);
                        break;
                    case BLAZE_POWDER:
                        CoolDown.visibilityAction(player, playerData);
                        break;
                    case ENDER_CHEST:
                        new MenuBoutique(player);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
