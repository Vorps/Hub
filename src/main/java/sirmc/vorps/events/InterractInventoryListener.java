package sirmc.vorps.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import sirmc.vorps.*;
import sirmc.vorps.Object.BookHelp;
import sirmc.vorps.Object.Jumps;
import sirmc.vorps.Object.Products;
import sirmc.vorps.commands.CommandBuild;
import sirmc.vorps.commands.CommandDoubleJumps;
import sirmc.vorps.commands.CommandFly;
import sirmc.vorps.commands.CommandVisiblePlayer;
import sirmc.vorps.menu.*;
import sirmc.vorps.utils.Cooldowns;
import sirmc.vorps.utils.GiveProductPlayer;
import sirmc.vorps.utils.Item;

import java.sql.ResultSet;

/**
 * Created by Vorps on 01/02/2016.
 */
public class InterractInventoryListener implements Listener {
    private static boolean state;

    @EventHandler
    public void onInterractInventory(InventoryClickEvent e){
        PlayerHub playerHub = Hub.instance.getPlayerHub().get(e.getWhoClicked().getName());
        try {
            if(!Hub.instance.getPlayerHub().get(e.getWhoClicked().getName()).isBuild()){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getType().equals(Material.AIR)){
                switch (e.getSlot()){
                    case 39:
                        MenuHelmet.HubmenuHelmetPage1(playerHub);
                        break;
                    case 38:
                        MenuChestplate.HubMenuChestplate(playerHub);
                        break;
                    case 37:
                        MenuLeggings.HubMenuLeggings(playerHub);
                        break;
                    case 36:
                        MenuBoots.HubMenuBoots(playerHub);
                        break;
                    default:
                        break;
                }
            }
            if(e.getCurrentItem() != null){
                state = true;
                ItemStack is = e.getCurrentItem();
                switch(is.getType()){
                    case COMPASS:
                        if(is.getItemMeta().getLore().contains("§7Accéder au menu principal")){
                            MenuPrincipal.HubMenuPricipal(playerHub);
                        }
                        break;
                    case SKULL_ITEM:
                        if(is.getItemMeta().getLore().get(0).equals("Remove Friends")){
                            Bukkit.getPlayer(playerHub.getPlayerName()).chat("/friends remove "+is.getItemMeta().getDisplayName());
                            MenuFriends.HubMenuRemoveAmis(playerHub, 1);
                        } else if(is.getItemMeta().getLore().get(0).equals("Remove Membre")){
                            Bukkit.getPlayer(playerHub.getPlayerName()).chat("/party remove "+is.getItemMeta().getDisplayName());
                            MenuParty.HubMenuRemoveParty(playerHub ,1);
                        } else if(is.getItemMeta().getDisplayName().equals("§6Résumé du profil")){
                            MenuProfil.HubMenuProfil(playerHub);
                        }
                        break;
                    case BLAZE_POWDER:
                        if(is.getItemMeta().getLore().get(0).equals("§aPermet de masquer")){
                            Cooldowns.VisibilityAction(playerHub);
                        }
                        break;
                    case ENDER_CHEST:
                        if(is.getItemMeta().getLore().contains("§7Accéder au menu Boutique")){
                            MenuBoutique.HubMenuBoutique(playerHub);
                        }
                        break;
                    case MAGMA_CREAM:
                        if(is.getItemMeta().getLore().get(0).equals("§a- §7Affiche les membres du groupe en ligne")){
                            MenuParty.HubMenuParty(playerHub, 1);
                        }
                        break;
                    case ARROW:
                        if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu profil")){
                            MenuProfil.HubMenuProfil(playerHub);
                        }if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu amis")){
                        MenuFriends.HubMenuFriends(playerHub, 1);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu party")){
                        MenuParty.HubMenuParty(playerHub, 1);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Principal")){
                        MenuPrincipal.HubMenuPricipal(playerHub);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au jeu")){
                        Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Boutique")){
                        MenuBoutique.HubMenuBoutique(playerHub);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Costumes")){
                        MenuCostume.HubMenuCostume(playerHub);
                    } else if(is.getItemMeta().getLore().get(0).equals("§7Page suivante transactions")){
                        int page = Integer.valueOf(is.getItemMeta().getLore().get(1));
                        MenuTransactions.HubmenuTransactions(playerHub, page);
                    }
                        break;
                    case LEATHER_BOOTS:
                        if(is.getItemMeta().getLore().get(0).equals("§7Jumps")){
                            MenuJump.HubmenuJump(playerHub);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Bottes")){
                            MenuBoots.HubMenuBoots(playerHub);
                        }
                        break;
                    case ITEM_FRAME:
                        if(is.getItemMeta().getLore().get(0).equals("Friends")){
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendRawMessage("/friends add ");
                        } else if(is.getItemMeta().getLore().get(0).equals("Party")){
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendRawMessage("/Party add ");
                        }
                        break;
                    case BARRIER:
                        if(is.getItemMeta().getLore().get(0).equals("§7Supprimer un joueur de votre liste d'amis")){
                            MenuFriends.HubMenuRemoveAmis(playerHub, 1);
                        } else if(is.getItemMeta().getLore().get(0).equals("§6Supprimer un membre")){
                            MenuParty.HubMenuRemoveParty(playerHub, 1);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retire votre chapeaux actuel")){
                            if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getHelmet() != null){
                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setHelmet(new ItemStack(Material.AIR));
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eChapeau retiré");
                                MenuHelmet.HubmenuHelmetPage1(playerHub);
                            }
                        } else if(is.getItemMeta().getDisplayName().equals("§c☒ Annuler ☒")){
                            Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retire votre plastron actuel")){
                            if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getChestplate() != null){
                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setChestplate(new ItemStack(Material.AIR));
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§ePlastron retiré");
                                MenuChestplate.HubMenuChestplate(playerHub);
                            }
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retire votre pantalon actuel")){
                            if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getLeggings() != null){
                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setLeggings(new ItemStack(Material.AIR));
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§ePantalon retiré");
                                MenuLeggings.HubMenuLeggings(playerHub);
                            }
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retire vos bottes actuel")){
                            if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getBoots() != null){
                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setBoots(new ItemStack(Material.AIR));
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eBottes retiré");
                                MenuBoots.HubMenuBoots(playerHub);
                            }
                        }else if(is.getItemMeta().getLore().get(0).equals("§7Retire votre costume")){
                            if(Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getHelmet() != null
                                    || Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getChestplate() != null
                                    || Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getLeggings() != null
                                    || Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().getBoots() != null){

                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setHelmet(new ItemStack(Material.AIR));
                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setChestplate(new ItemStack(Material.AIR));
                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setLeggings(new ItemStack(Material.AIR));
                                Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setBoots(new ItemStack(Material.AIR));
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eCostume retiré !!!");
                                Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                            }
                        }

                        break;
                    case DIAMOND:
                        if(is.getItemMeta().getLore().get(0).equals("§7Events prochainement")){
                            MenuEvent.HubmenuEvent(playerHub);
                        }
                        break;
                    case NETHER_STAR:
                        if(is.getItemMeta().getLore().get(0).equals("§7Changer de hub")){
                            MenuHub.HubmenuHub(playerHub);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Jump §a"+ Settings.getNameServer())){
                            Hub.instance.getListJumps().forEach((Jumps jump) -> {
                                if(jump.getJump().equals(is.getItemMeta().getLore().get(1).substring(2))){
                                    Location locJump = new Location(Bukkit.getPlayer(playerHub.getPlayerName()).getWorld(), jump.getXplayer(), jump.getYplayer(), jump.getZplayer());
                                    locJump.setYaw(jump.getYaw());
                                    Bukkit.getPlayer(playerHub.getPlayerName()).teleport(locJump);
                                }
                            });
                        }
                        break;
                    case BOOK:
                        if(is.getItemMeta().getLore().get(0).equals("§7Besoin d'aide ?")){
                            MenuHelp.HubMenuHelp(playerHub);
                        }
                        break;
                    case WRITTEN_BOOK:
                        Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().clear(4);
                    case BOOK_AND_QUILL:
                        System.out.println(is.getItemMeta().getDisplayName());
                        System.out.println(Hub.instance.getBookHelpList().size());
                        Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(4, Hub.instance.getBookHelpList().get(is.getItemMeta().getDisplayName()).get());
                        Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                        break;
                    case DIAMOND_SWORD:
                        if(is.getItemMeta().getDisplayName().equals("§6HitIt!")){
                            MenuHitIt.HubmenuHitIt(playerHub);
                        }
                        break;
                    case BED:
                        if(is.getItemMeta().getDisplayName().equals("§6Rush!")){
                            MenuRush.HubmenuRush(playerHub);
                        }
                        break;
                    case BOW:
                        if(is.getItemMeta().getDisplayName().equals("§6SkyWars!")){
                            MenuSkyWars.HubmenuSkyWars(playerHub);
                        }
                        break;
                    case EXP_BOTTLE:
                        if(is.getItemMeta().getDisplayName().equals("§6LuckyFight!")){
                            MenuLuckyFight.HubmenuLuckyFight(playerHub);
                        }
                        break;
                    case PAPER:
                        if(is.getItemMeta().getLore().get(0).equals("Party")){
                            MenuParty.HubMenuParty(playerHub, Integer.parseInt(is.getItemMeta().getLore().get(1)));
                        } else if(is.getItemMeta().getLore().get(0).equals("Friends")){
                                MenuFriends.HubMenuFriends(playerHub, Integer.parseInt(is.getItemMeta().getLore().get(1)));
                        } else if(is.getItemMeta().getLore().get(0).equals("Party remove")){
                            MenuParty.HubMenuRemoveParty(playerHub, Integer.parseInt(is.getItemMeta().getLore().get(1)));
                        } else if(is.getItemMeta().getLore().get(0).equals("Friends remove")){
                            MenuFriends.HubMenuRemoveAmis(playerHub, Integer.parseInt(is.getItemMeta().getLore().get(1)));
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Page Têtes")){
                            MenuHelmet.HubMenuHelmetPage2(playerHub);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Page Block")){
                            MenuHelmet.HubMenuHelmetPage3(playerHub);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Historique des achats")){
                            MenuTransactions.HubmenuTransactions(playerHub, 1);
                        }
                        break;
                    case ENDER_PEARL:
                        if(is.getItemMeta().getLore().get(0).equals("§7Retourner au spawn de "+Settings.getNameServer()+".")){
                            Bukkit.getPlayer(playerHub.getPlayerName()).teleport(Hub.instance.getSpawnHub());
                        }
                        break;
                    case BONE:
                        if(is.getItemMeta().getLore().get(0).equals("§7Acheter un pets")){
                            MenuPets.HubMenuPets(playerHub);
                        }
                        break;
                    case GLOWSTONE_DUST:
                        if(is.getItemMeta().getLore().get(0).equals("§7Acheter des effets de particules")){
                            MenuParticles.HubmenuParticle(playerHub);
                        }
                        break;
                    case ENCHANTMENT_TABLE:
                        if(is.getItemMeta().getLore().get(0).equals("§7Obtenire un grade")){
                            MenuGrades.HubMenuGrades(playerHub);
                        }
                        break;
                    case BLAZE_ROD:
                        if(is.getItemMeta().getLore().get(0).equals("§7Acheter des gadgets")){
                            MenuGadgets.HubMenuGadgets(playerHub);
                        }
                        break;
                    case POTION:
                        if(is.getItemMeta().getLore().get(0).equals("§7Obtenire des bonus")){
                            MenuBonus.HubMenuBonus(playerHub);
                        }
                        break;
                    case DIAMOND_CHESTPLATE:
                        if(is.getItemMeta().getLore().get(0).equals("§7Acheter des costumes")){
                            MenuCostume.HubMenuCostume(playerHub);
                        }
                        break;
                    case LEATHER_HELMET:
                        if(is.getItemMeta().getLore().get(0).equals("§7Chapeaux")){
                            MenuHelmet.HubmenuHelmetPage1(playerHub);
                        }
                        break;
                    case LEATHER_CHESTPLATE:
                        if(is.getItemMeta().getLore().get(0).equals("§7Plastron")){
                            MenuChestplate.HubMenuChestplate(playerHub);
                        }
                        break;
                    case LEATHER_LEGGINGS:
                        if(is.getItemMeta().getLore().get(0).equals("§7Pantalon")){
                            MenuLeggings.HubMenuLeggings(playerHub);
                        }
                        break;
                    case EMPTY_MAP:
                        if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Chapeaux")){
                            MenuHelmet.HubmenuHelmetPage1(playerHub);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Retour au menu Têtes")){
                            MenuHelmet.HubMenuHelmetPage2(playerHub);
                        } else if(is.getItemMeta().getLore().get(0).equals("§7Page précédente transactions")){
                            int page = Integer.parseInt(is.getItemMeta().getLore().get(1));
                            System.out.println(page);
                            MenuTransactions.HubmenuTransactions(playerHub, page);
                        }
                        break;
                    case GOLDEN_APPLE:
                        if(is.getItemMeta().getDisplayName().equals("§b☑ Valider l'achat ☑")){
                            GiveProductPlayer.GiveItemPlayer(playerHub, playerHub.getProductTarget().getItemMeta().getLore().get(0).substring(2), true);
                        }
                        break;
                    case REDSTONE_COMPARATOR:
                        if(is.getItemMeta().getLore().get(0).equals("§7Préférences")){
                            MenuSettings.HubMenuSettings(playerHub);
                        }
                        break;
                    default:
                        break;
                }
                if(is.getData().getItemTypeId() == 175){
                    if(is.getItemMeta().getLore().get(0).equals("§a- §7Affiche les amis en ligne")){
                        MenuFriends.HubMenuFriends(playerHub, 1);
                    }
                } else if(is.getData().getItemTypeId() == 322){
                    if(is.getItemMeta().getLore().get(0).equals("§7Obtenir des étoiles")){
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eBoutique en ligne : §a§nwww.sirelmc.fr");
                        Bukkit.getPlayer(playerHub.getPlayerName()).closeInventory();
                    }
                } else if(is.getData().getItemTypeId() == 351){
                    if(is.getItemMeta().getDisplayName().equals("§6Visiblité des joueurs")){
                        CommandVisiblePlayer.CommandVisiblePlayer(new String[] {}, playerHub);
                        MenuSettings.HubMenuSettings(playerHub);
                    } else if(is.getItemMeta().getDisplayName().equals("§6Mode build dans le hub")){
                        CommandBuild.CommandBuild(new String[] {}, playerHub);
                    } else if(is.getItemMeta().getDisplayName().equals("§6Mode fly dans le hub")){
                        CommandFly.CommandFly(new String[] {}, playerHub);
                        MenuSettings.HubMenuSettings(playerHub);
                    } else if(is.getItemMeta().getDisplayName().equals("§6Mode jump dans le hub")){
                        CommandDoubleJumps.CommandDoubleJumps(new String[] {}, playerHub);
                        MenuSettings.HubMenuSettings(playerHub);
                    }
                }

                if(Hub.instance.getListProducts().containsKey(is.getItemMeta().getLore().get(0).substring(2))){
                    playerHub.setProductTarget(is);
                    ResultSet results;
                    try {
                        results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM PlayerProduit WHERE namePlayer = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                        while(results.next()){
                            if(results.getString(2).equals(is.getItemMeta().getLore().get(0).substring(2))){
                                is.getItemMeta().getLore().get(0).charAt(0);
                                state = false;
                            }
                        }
                    } catch (Exception exception) {}

                    Products products = Hub.instance.getListProducts().get(is.getItemMeta().getLore().get(0).substring(2));
                    if(playerHub.getMoney().get(products.getMoney()) >= products.getPrice()){
                        if(state){
                            MenuPurchase.HubmenuPurchase(playerHub, is);
                        }
                    }
                    if(!state){
                        GiveProductPlayer.GiveItemPlayer(playerHub, playerHub.getProductTarget().getItemMeta().getLore().get(0).substring(2), false);
                    }
                }
            }
        } catch (Exception e2) {}
    }
}
