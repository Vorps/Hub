package sirmc.vorps.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import sirmc.vorps.Hub;
import sirmc.vorps.Object.Jumps;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.commands.CommandBuild;
import sirmc.vorps.commands.CommandDoubleJumps;
import sirmc.vorps.commands.CommandFly;
import sirmc.vorps.menu.MenuBoutique;
import sirmc.vorps.menu.MenuPrincipal;
import sirmc.vorps.menu.MenuProfil;
import sirmc.vorps.menu.Navigator;
import sirmc.vorps.utils.Cooldowns;
import sirmc.vorps.utils.ParticleEffect;
import sirmc.vorps.utils.Title;

/**
 * Created by Vorps on 01/02/2016.
 */
public class InteractListener implements Listener{
    @EventHandler
    public void onInterractItem(PlayerInteractEvent e){
        Player player = e.getPlayer();
        PlayerHub playerHub = Hub.instance.getPlayerHub().get(player.getName());
        if(e.getAction().equals(Action.PHYSICAL)){
            if(e.getClickedBlock().getType() == Material.GOLD_PLATE){
                Location loc = e.getClickedBlock().getLocation();
                if(!playerHub.isInJumps()){
                    Hub.instance.getListJumps().forEach((Jumps jump) -> {
                        if((int)loc.getX() == jump.getX() && loc.getY() == jump.getY() && loc.getZ() == jump.getZ()){
                            if(playerHub.isFly()){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Fly (§4Désactivé§6).");
                            }
                            if(playerHub.isDoubleJumps()){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Double Jump (§4Désactivé§6).");
                            }
                            if(playerHub.isBuild() || player.getGameMode() == GameMode.CREATIVE){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§6Mode build (§4Désactivé§6).");
                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().clear();
                                Navigator.HubNavigator(playerHub);
                                Bukkit.getPlayer(playerHub.getPlayerName()).setGameMode(GameMode.ADVENTURE);
                            }
                            Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(false);
                            Location locJump =  new Location(player.getWorld(), jump.getXplayer(), jump.getYplayer(), jump.getZplayer());
                            locJump.setYaw(jump.getYaw());
                            playerHub.setLocCheckPoint(locJump);
                            playerHub.setInJumps(true);
                            playerHub.setNumberCheckPoint(0);
                            playerHub.setNumberTrying(0);
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage(jump.getMessage());
                            playerHub.setJump(jump);
                        }
                    });
                } else if(playerHub.getJump() != null && playerHub.isInJumps()){
                    if(playerHub.getNumberCheckPoint() == playerHub.getJump().getJumpSettings().size() && playerHub.isInJumps()){
                        playerHub.setInJumps(false);
                        playerHub.setNumberCheckPoint(0);
                        playerHub.setNumberTrying(0);
                        playerHub.setJump(null);
                        playerHub.setLocCheckPoint(null);
                        playerHub.teleportSpawn();
                        Title title = new Title("§aFélicitation !!! ", "§6Vous avez gagné un §ccookie.");
                        title.setStayTime(10);
                        title.send(player);
                    }
                }
            } else if(e.getClickedBlock().getType() == Material.IRON_PLATE){
                if(playerHub.getJump() != null){
                    Location loc = e.getClickedBlock().getLocation();
                    if(!loc.equals(playerHub.getLocCheckPoint())){
                        playerHub.setNumberCheckPoint(playerHub.getNumberCheckPoint()+1);
                        playerHub.setLocCheckPoint(loc);
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eCheckPoint n§§c"+playerHub.getNumberCheckPoint()+"§e !!!");
                    }
                    playerHub.setInJumps(true);
                    if(playerHub.isFly()){
                        playerHub.setFly(false);
                    }
                    if(playerHub.isDoubleJumps()){
                        playerHub.setDoubleJumps(false);
                    }
                    player.setAllowFlight(false);
                    if(playerHub.isBuild() || player.getGameMode() == GameMode.CREATIVE){
                        playerHub.setBuild(false);
                        Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().clear();
                        Navigator.HubNavigator(playerHub);
                        Bukkit.getPlayer(playerHub.getPlayerName()).setGameMode(GameMode.ADVENTURE);
                    }
                }
            }
        }


        try {
            if(!Hub.instance.getPlayerHub().get(e.getPlayer().getName()).isBuild()){
                e.setCancelled(true);
            }
            if(!e.getAction().equals(Action.PHYSICAL)){
                ItemStack is = e.getItem();
                Action action = e.getAction();
                if(is.getType().equals(Material.WRITTEN_BOOK)){
                    if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                        e.setCancelled(false);
                    } else if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                        Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().clear(4);
                        e.setCancelled(true);
                    }
                } else if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)){
                    switch(is.getType()){
                        case COMPASS:
                            MenuPrincipal.HubMenuPricipal(playerHub);
                            break;
                        case SKULL_ITEM:
                            MenuProfil.HubMenuProfil(playerHub);
                            break;
                        case BLAZE_POWDER:
                            Cooldowns.VisibilityAction(playerHub);
                            break;
                        case ENDER_CHEST:
                            MenuBoutique.HubMenuBoutique(playerHub);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e2) {}
    }
}
