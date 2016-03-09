package me.vorps.hub.events;

import me.vorps.hub.Object.BookHelp;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import me.vorps.hub.commands.CommandBuild;
import me.vorps.hub.commands.CommandDoubleJumps;
import me.vorps.hub.commands.CommandFly;
import me.vorps.hub.commands.CommandVisiblePlayer;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.menu.*;
import me.vorps.hub.utils.CoolDown;
import me.vorps.hub.utils.GiveProductPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import me.vorps.hub.Object.Jumps;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class InterractInventoryListener implements Listener {
    private static boolean state;

    @EventHandler
    public void onInterractInventory(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        PlayerData playerData = PlayerData.getPlayerData(e.getWhoClicked().getName());
        if(e.getCurrentItem() != null){
            if(!playerData.isBuild() || playerData.isInJumps()){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getType().equals(Material.AIR) && e.isCancelled()){
                switch (e.getSlot()){
                    case 39:
                        new MenuHelmet(player, 1);
                        break;
                    case 38:
                        new MenuChestplate(player);
                        break;
                    case 37:
                        new MenuLeggings(player);
                        break;
                    case 36:
                        new MenuBoots(player);
                        break;
                    default:
                        break;
                }
            }
            if(e.isCancelled() && !e.getCurrentItem().getType().equals(Material.AIR)){
                state = true;
                ItemStack is = e.getCurrentItem();
                switch(is.getType()){
                    case COMPASS:
                        if(is.getItemMeta().getLore().contains("§7Accéder au menu principal")){
                            new MenuPrincipal(player);
                        }
                        break;
                    case SKULL_ITEM:
                        if(is.getItemMeta().getLore().get(0).equals("Remove Friends")){
                            player.chat("/friends remove "+is.getItemMeta().getDisplayName());
                            new MenuFriendsRemove(player, 1);
                        } else if(is.getItemMeta().getLore().get(0).equals("Remove Membre")){
                            player.chat("/party remove "+is.getItemMeta().getDisplayName());
                            new MenuPartyRemove(player,1);
                        } else if(is.getItemMeta().getDisplayName().equals("§6Résumé du profil")){
                            new MenuProfil(player);
                        }
                        break;
                    case BLAZE_POWDER:
                        if(is.getItemMeta().getLore().get(0).equals("§aPermet de masquer")){
                            CoolDown.visibilityAction(player, playerData);
                        }
                        break;
                    case ENDER_CHEST:
                        if(is.getItemMeta().getLore().contains("§7Accéder au menu Boutique")){
                            new MenuBoutique(player);
                        }
                        break;
                    case MAGMA_CREAM:
                        if(is.getItemMeta().getLore().get(0).equals("§a- §7Affiche les membres du groupe en ligne")){
                            new MenuParty(player, 1);
                        }
                        break;
                    case ARROW:
                        if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu profil")){
                            new MenuProfil(player);
                        }if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu amis")){
                        new MenuFriends(player, 1);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu party")){
                        new MenuParty(player, 1);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Principal")){
                        new MenuPrincipal(player);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au jeu")){
                        player.closeInventory();
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Boutique")){
                        new MenuBoutique(player);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Costumes")){
                        new MenuCostume(player);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Page suivante transactions")){
                        int page = Integer.valueOf(is.getItemMeta().getLore().get(1));
                        new MenuTransactions(player, page);
                    }
                        break;
                    case LEATHER_BOOTS:
                        if(is.getItemMeta().getLore().get(0).equals("§7Jumps")){
                            new MenuJump(player);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Bottes")){
                            new MenuBoots(player);
                        }
                        break;
                    case ITEM_FRAME:
                        if(is.getItemMeta().getLore().get(0).equals("Friends")){
                            player.sendRawMessage("/friends add ");
                        } else if(is.getItemMeta().getLore().get(0).equals("Party")){
                            player.sendRawMessage("/Party add ");
                        }
                        break;
                    case BARRIER:
                        if(is.getItemMeta().getLore().get(0).equals("§7Supprimer un joueur de votre liste d'amis")){
                            new MenuFriendsRemove(player, 1);
                        } else if(is.getItemMeta().getLore().get(0).equals("§6Supprimer un membre")){
                            new MenuPartyRemove(player, 1);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retire votre chapeaux actuel")){
                            if(player.getInventory().getHelmet() != null){
                                player.getInventory().setHelmet(new ItemStack(Material.AIR));
                                player.sendMessage("§eChapeau retiré");
                                new MenuHelmet(player, 1);
                            }
                        } else if(is.getItemMeta().getDisplayName().equals("§c☒ Annuler ☒")){
                            player.closeInventory();
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retire votre plastron actuel")){
                            if(player.getInventory().getChestplate() != null){
                                player.getInventory().setChestplate(new ItemStack(Material.AIR));
                                player.sendMessage("§ePlastron retiré");
                                new MenuChestplate(player);
                            }
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retire votre pantalon actuel")){
                            if(player.getInventory().getLeggings() != null){
                                player.getInventory().setLeggings(new ItemStack(Material.AIR));
                                player.sendMessage("§ePantalon retiré");
                                new MenuLeggings(player);
                            }
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retire vos bottes actuel")){
                            if(player.getInventory().getBoots() != null){
                                player.getInventory().setBoots(new ItemStack(Material.AIR));
                                player.sendMessage("§eBottes retiré");
                                new MenuBoots(player);
                            }
                        }else if(is.getItemMeta().getLore().get(0).equals("§7Retire votre costume")){
                            if(player.getInventory().getHelmet() != null
                                    ||player.getInventory().getChestplate() != null
                                    ||player.getInventory().getLeggings() != null
                                    ||player.getInventory().getBoots() != null){

                                player.getInventory().setHelmet(new ItemStack(Material.AIR));
                                player.getInventory().setChestplate(new ItemStack(Material.AIR));
                                player.getInventory().setLeggings(new ItemStack(Material.AIR));
                                player.getInventory().setBoots(new ItemStack(Material.AIR));
                                player.sendMessage("§eCostume retiré !!!");
                                player.closeInventory();
                            }
                        }

                        break;
                    case DIAMOND:
                        if(is.getItemMeta().getLore().get(0).equals("§7Events prochainement")){
                            new MenuEvent(player);
                        }
                        break;
                    case NETHER_STAR:
                        if(is.getItemMeta().getLore().get(0).equals("§7Changer de hub")){
                            new MenuHub(player);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Jump §a"+ Settings.getNameServer())){
                            Jumps.getListJumps().forEach((Jumps jump) -> {
                                if(jump.getJump().equals(is.getItemMeta().getLore().get(1).substring(2))){
                                    Location locJump = new Location(player.getWorld(), jump.getXPlayer(), jump.getYPlayer(), jump.getZPlayer());
                                    locJump.setYaw(jump.getYaw());
                                    player.teleport(locJump);
                                }
                            });
                        }
                        break;
                    case BOOK:
                        if(is.getItemMeta().getLore().get(0).equals("§7Besoin d'aide ?")){
                            new MenuHelp(player);
                        }
                        break;
                    case WRITTEN_BOOK:
                        player.getInventory().clear(4);
                    case BOOK_AND_QUILL:
                        System.out.println(is.getItemMeta().getDisplayName());
                        player.getInventory().setItem(4, BookHelp.getBookHelp(is.getItemMeta().getDisplayName()).get());
                        player.closeInventory();
                        break;
                    case DIAMOND_SWORD:
                        if(is.getItemMeta().getDisplayName().equals("§6HitIt!")){
                            new MenuHitIt(player);
                        }
                        break;
                    case BED:
                        if(is.getItemMeta().getDisplayName().equals("§6Rush!")){
                            new MenuRush(player);
                        }
                        break;
                    case BOW:
                        if(is.getItemMeta().getDisplayName().equals("§6SkyWars!")){
                            new MenuSkyWars(player);
                        }
                        break;
                    case EXP_BOTTLE:
                        if(is.getItemMeta().getDisplayName().equals("§6LuckyFight!")){
                            new MenuLuckyFight(player);
                        }
                        break;
                    case PAPER:
                        if(is.getItemMeta().getLore().get(0).equals("Party")){
                            new MenuParty(player, Integer.parseInt(is.getItemMeta().getLore().get(1)));
                        } else if(is.getItemMeta().getLore().get(0).equals("Friends")){
                            new MenuFriends(player, Integer.parseInt(is.getItemMeta().getLore().get(1)));
                        } else if(is.getItemMeta().getLore().get(0).equals("Party remove")){
                            new MenuPartyRemove(player, Integer.parseInt(is.getItemMeta().getLore().get(1)));
                        } else if(is.getItemMeta().getLore().get(0).equals("Friends remove")){
                            new MenuFriendsRemove(player, Integer.parseInt(is.getItemMeta().getLore().get(1)));
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Page Têtes")){
                            new MenuHelmet(player, 2);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Page Block")){
                            new MenuHelmet(player, 3);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Historique des achats")){
                            new MenuTransactions(player, 1);
                        }
                        break;
                    case ENDER_PEARL:
                        if(is.getItemMeta().getLore().get(0).equals("§7Retourner au spawn de "+Settings.getNameServer()+".")){
                            player.teleport(Settings.getSpawnHub());
                        }
                        break;
                    case BONE:
                        if(is.getItemMeta().getLore().get(0).equals("§7Acheter un pets")){
                            new MenuPets(player);
                        }
                        break;
                    case GLOWSTONE_DUST:
                        if(is.getItemMeta().getLore().get(0).equals("§7Acheter des effets de particules")){
                            new MenuParticles(player);
                        }
                        break;
                    case ENCHANTMENT_TABLE:
                        if(is.getItemMeta().getLore().get(0).equals("§7Obtenire un grade")){
                            new MenuGrades(player);
                        }
                        break;
                    case BLAZE_ROD:
                        if(is.getItemMeta().getLore().get(0).equals("§7Acheter des gadgets")){
                            new MenuGadgets(player);
                        }
                        break;
                    case POTION:
                        if(is.getItemMeta().getLore().get(0).equals("§7Obtenire des bonus")){
                            new MenuBonus(player);
                        }
                        break;
                    case DIAMOND_CHESTPLATE:
                        if(is.getItemMeta().getLore().get(0).equals("§7Acheter des costumes")){
                            new MenuCostume(player);
                        }
                        break;
                    case LEATHER_HELMET:
                        if(is.getItemMeta().getLore().get(0).equals("§7Chapeaux")){
                            new MenuHelmet(player, 1);
                        }
                        break;
                    case LEATHER_CHESTPLATE:
                        if(is.getItemMeta().getLore().get(0).equals("§7Plastron")){
                            new MenuChestplate(player);
                        }
                        break;
                    case LEATHER_LEGGINGS:
                        if(is.getItemMeta().getLore().get(0).equals("§7Pantalon")){
                            new MenuLeggings(player);
                        }
                        break;
                    case EMPTY_MAP:
                        if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Chapeaux")){
                            new MenuHelmet(player, 1);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Têtes")){
                            new MenuHelmet(player, 2);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Page précédente transactions")){
                            int page = Integer.parseInt(is.getItemMeta().getLore().get(1));
                            new MenuTransactions(player, page);
                        }
                        break;
                    case GOLDEN_APPLE:
                        if(is.getItemMeta().getDisplayName().equals("§b☑ Valider l'achat ☑")){
                            GiveProductPlayer.giveItemPlayer(player, playerData.getProductTarget().getItemMeta().getLore().get(0).substring(2), true);
                        }
                        break;
                    case REDSTONE_COMPARATOR:
                        if(is.getItemMeta().getLore().get(0).equals("§7Préférences")){
                            new MenuSettings(player);
                        }
                        break;
                    case SADDLE:
                        break;
                    default:
                        break;
                }
                if(is.getData().getItemTypeId() == 175){
                    if(is.getItemMeta().getLore().get(0).equals("§a- §7Affiche les amis en ligne")){
                        new MenuFriends(player, 1);
                    }
                } else if(is.getData().getItemTypeId() == 322){
                    if(is.getItemMeta().getLore().get(0).equals("§7Obtenir des étoiles")){
                        player.sendMessage("§eBoutique en ligne : §a"+Settings.getSiteWeb());
                        player.closeInventory();
                    }
                } else if(is.getData().getItemTypeId() == 351){
                    if(is.getItemMeta().getDisplayName().equals("§6Visiblité des joueurs")){
                         new CommandVisiblePlayer(player, new String[] {});
                        new MenuSettings(player);
                    } else if(is.getItemMeta().getDisplayName().equals("§6Mode build dans le hub")){
                        new CommandBuild(player, new String[] {});
                    } else if(is.getItemMeta().getDisplayName().equals("§6Mode fly dans le hub")){
                        new CommandFly(player, new String[] {});
                        new MenuSettings(player);
                    } else if(is.getItemMeta().getDisplayName().equals("§6Mode jump dans le hub")){
                        new CommandDoubleJumps(player, new String[] {});
                        new MenuSettings(player);
                    }
                }

                if(is.getItemMeta().getLore() != null){
                    if(is.getItemMeta().getLore().size() > 0){
                        if(Products.isExits(is.getItemMeta().getLore().get(0).substring(2))){
                            playerData.setProductTarget(is);
                            ResultSet results;
                            try {
                                results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_product WHERE pp_player = '" +player.getName() + "'");
                                while (results.next()) {
                                    if (Database.FORTYCUBE.getDatabase().getString(results, 2).equals(is.getItemMeta().getLore().get(0).substring(2))) {
                                        state = false;
                                    }
                                }
                            } catch (SQLException e1){
                                //
                            } catch (SqlException e1) {
                                e1.printStackTrace();
                            }

                            Products products = Products.getProduct(is.getItemMeta().getLore().get(0).substring(2));
                            if(playerData.getMoney().get(products.getMoney()) >= products.getPrice()){
                                if(state){
                                    new MenuPurchase(player, is);
                                }
                            }
                            if(!state){
                                GiveProductPlayer.giveItemPlayer(player, playerData.getProductTarget().getItemMeta().getLore().get(0).substring(2), false);
                            }
                        }
                    }
                }
            }
        }
    }
}
