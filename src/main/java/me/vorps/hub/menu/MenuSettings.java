package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.Menu;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuSettings extends Menu{

    private boolean state;

	public MenuSettings(UUID uuid, boolean state){
        super(new byte[] {11}, Bukkit.createInventory(null, 36, "§6Paramètre"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {28, 0}, {29, 0}, {30, 0}, {31, 0}, {32, 0}, {33, 0}, {34, 0}, {35, 0}}, Hub.getInstance());
        this.state = state;
        /*PlayerData playerData = PlayerData.getPlayerData(uuid);
        if(player.hasPermission("CORE.visible_player.me.on" ) || player.hasPermission("CORE.visible_player.me.off")){
			if(playerData.isVisiblePlayer()){
                    menu.setItem(10, new ItemBuilder(Material.LIME_DYE).withName("§6Visiblité des joueurs").withLore(new String[] {"", "§7Afficher ou cacher les joueurs dans le hub", "", "§aCommande §a> §b/joueur"}).get());
            } else {
                    menu.setItem(10, new ItemBuilder(Material.GRAY_DYE).withName("§6Visiblité des joueurs").withLore(new String[] {"", "§7Afficher ou cacher les joueurs dans le hub", "", "§aCommande §a> §b/joueur"}).get());
            }
		}
		if(player.hasPermission("CORE.build.me.on") || player.hasPermission("CORE.build.me.off")){
			if(playerData.isBuild()){
				menu.setItem(19, new ItemBuilder(Material.LIME_DYE).withName("§6Mode build dans le hub").withLore(new String[] {"", "§7Mode build dans le hub", "", "§aCommande §a> §b/build"}).get());
			} else {
				menu.setItem(19, new ItemBuilder(Material.GRAY_DYE).withName("§6Mode build dans le hub").withLore(new String[] {"", "§7Mode build dans le hub", "", "§aCommande §a> §b/build"}).get());
			}
		}
		if(player.hasPermission("CORE.fly.me.on") || player.hasPermission("CORE.fly.me.off")){
			if(playerData.isFly()){
				menu.setItem(12, new ItemBuilder(Material.LIME_DYE).withName("§6Mode fly dans le hub").withLore(new String[] {"", "§7Mode fly dans le hub", "", "§aCommande §a> §b/fly"}).get());
			} else {
				menu.setItem(12, new ItemBuilder(Material.GRAY_DYE).withName("§6Mode fly dans le hub").withLore(new String[] {"", "§7Mode fly dans le hub", "", "§aCommande §a> §b/fly"}).get());
			}
		}
		if(player.hasPermission("CORE.double_jump.me.on") || player.hasPermission("CORE.double_jump.me.off")){
			if(playerData.isDoubleJumps()){
				menu.setItem(21, new ItemBuilder(Material.LIME_DYE).withName("§6Mode jump dans le hub").withLore(new String[] {"", "§7Mode jump dans le hub", "", "§aCommande §a> §b/jump"}).get());
			} else {
				menu.setItem(21, new ItemBuilder(Material.GRAY_DYE).withName("§6Mode jump dans le hub").withLore(new String[] {"", "§7Mode jump dans le hub", "", "§aCommande §a> §b/jump"}).get());
			}
		}

        //if(player.hasPermission("CORE.chat.me.on") || player.hasPermission("CORE.chat.me.off")){
        //    if(playerData.isChat()){
         //       menu.setItem(14, new Item(351).withData((byte) 10).withName("§6Chat").withLore(new String[] {"", "§6Chat", "", "§aCommande §a> §b/chat"}).get());
        //    } else {
        //        menu.setItem(14, new Item(351).withData((byte) 8).withName("§6Chat").withLore(new String[] {"", "§6Chat", "", "§aCommande §a> §b/chat"}).get());
        //    }
       // }
        if(state){
            menu.setItem(27, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        } else {
            menu.setItem(27, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
        }*/
		Bukkit.getPlayer(uuid).openInventory(menu);
	}

    @Override
    public void interractInventory(InventoryClickEvent e){
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()){
            case ARROW:
                if(state) Bukkit.getPlayer(uuid).closeInventory();
                else new MenuProfil(uuid);
                break;
            case GRAY_DYE:
            case GREEN_DYE:
                /*switch (itemStack.getItemMeta().getDisplayName()){
                    case "§6Visiblité des joueurs":
                        new CommandVisiblePlayer(player, new String[] {});
                        new MenuSettings(player, state);
                        break;
                    case "§6Mode build dans le hub":
                        new CommandBuild(player, new String[] {});
                        break;
                    case "§6Mode fly dans le hub":
                        new CommandFly(player, new String[] {});
                        new MenuSettings(player, state);
                        break;
                    case "§6Mode jump dans le hub":
                        new CommandDoubleJumps(player, new String[] {});
                        new MenuSettings(player, state);
                        break;
                    case "§6Chat":
                        // TODO: 11/03/2016 c
                        player.chat("/chat");
                        new MenuSettings(player, state);
                        break;
                    default:
                        break;

                }*/
            default:
                break;
        }
    }
}
