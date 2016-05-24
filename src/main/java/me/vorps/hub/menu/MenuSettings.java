package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Menu;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import me.vorps.hub.commands.CommandBuild;
import me.vorps.hub.commands.CommandDoubleJumps;
import me.vorps.hub.commands.CommandFly;
import me.vorps.hub.commands.CommandVisiblePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuSettings extends Menu{

	public MenuSettings(Player player){
        super(new byte[] {11}, Bukkit.createInventory(null, 36, "§6Paramètre"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {28, 0}, {29, 0}, {30, 0}, {31, 0}, {32, 0}, {33, 0}, {34, 0}, {35, 0}});
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(player.hasPermission("fortycube.visible_player.me.on" ) || player.hasPermission("fortycube.visible_player.me.off")){
			if(playerData.isVisiblePlayer()){
                    menu.setItem(10, new Item(351).withData((byte) 10).withName("§6Visiblité des joueurs").withLore(new String[] {"", "§7Afficher ou cacher les joueurs dans le hub", "", "§aCommande §a> §b/joueur"}).get());
            } else {
                    menu.setItem(10, new Item(351).withData((byte) 8).withName("§6Visiblité des joueurs").withLore(new String[] {"", "§7Afficher ou cacher les joueurs dans le hub", "", "§aCommande §a> §b/joueur"}).get());
            }
		}
		if(player.hasPermission("fortycube.build.me.on") || player.hasPermission("fortycube.build.me.off")){
			if(playerData.isBuild()){
				menu.setItem(11, new Item(351).withData((byte) 10).withName("§6Mode build dans le hub").withLore(new String[] {"", "§7Mode build dans le hub", "", "§aCommande §a> §b/build"}).get());
			} else {
				menu.setItem(11, new Item(351).withData((byte) 8).withName("§6Mode build dans le hub").withLore(new String[] {"", "§7Mode build dans le hub", "", "§aCommande §a> §b/build"}).get());
			}
		}
		if(player.hasPermission("fortycube.fly.me.on") || player.hasPermission("fortycube.fly.me.off")){
			if(playerData.isFly()){
				menu.setItem(20, new Item(351).withData((byte) 10).withName("§6Mode fly dans le hub").withLore(new String[] {"", "§7Mode fly dans le hub", "", "§aCommande §a> §b/fly"}).get());
			} else {
				menu.setItem(20, new Item(351).withData((byte) 8).withName("§6Mode fly dans le hub").withLore(new String[] {"", "§7Mode fly dans le hub", "", "§aCommande §a> §b/fly"}).get());
			}
		}
		if(player.hasPermission("fortycube.double_jump.me.on") || player.hasPermission("fortycube.double_jump.me.off")){
			if(playerData.isDoubleJumps()){
				menu.setItem(13, new Item(351).withData((byte) 10).withName("§6Mode jump dans le hub").withLore(new String[] {"", "§7Mode jump dans le hub", "", "§aCommande §a> §b/jump"}).get());
			} else {
				menu.setItem(13, new Item(351).withData((byte) 8).withName("§6Mode jump dans le hub").withLore(new String[] {"", "§7Mode jump dans le hub", "", "§aCommande §a> §b/jump"}).get());
			}
		}

        if(player.hasPermission("fortycube.chat.me.on") || player.hasPermission("fortycube.chat.me.off")){
            if(playerData.isDoubleJumps()){
                menu.setItem(15, new Item(351).withData((byte) 10).withName("§6Chat").withLore(new String[] {"", "§6Chat", "", "§aCommande §a> §b/chat"}).get());
            } else {
                menu.setItem(15, new Item(351).withData((byte) 8).withName("§6Chat").withLore(new String[] {"", "§6Chat", "", "§aCommande §a> §b/chat"}).get());
            }
        }
        menu.setItem(27, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
		player.openInventory(menu);
	}

    @Override
    public void interractInventory(InventoryClickEvent e){
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()){
            case ARROW:
                new MenuProfil(player);
                break;
            default:
                break;
        }
        if(itemStack.getData().getItemTypeId() == 351){
            if(itemStack.getItemMeta().getDisplayName().equals("§6Visiblité des joueurs")){
                new CommandVisiblePlayer(player, new String[] {});
                new MenuSettings(player);
            } else if(itemStack.getItemMeta().getDisplayName().equals("§6Mode build dans le hub")){
                new CommandBuild(player, new String[] {});
            } else if(itemStack.getItemMeta().getDisplayName().equals("§6Mode fly dans le hub")){
                new CommandFly(player, new String[] {});
                new MenuSettings(player);
            } else if(itemStack.getItemMeta().getDisplayName().equals("§6Mode jump dans le hub")){
                new CommandDoubleJumps(player, new String[] {});
                new MenuSettings(player);
            } else if(itemStack.getItemMeta().getDisplayName().equals("§6Chat")){
                // TODO: 11/03/2016 c
                player.chat("/chat");
                new MenuSettings(player);
            }
        }
    }
}
