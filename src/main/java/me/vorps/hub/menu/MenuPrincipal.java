package me.vorps.hub.menu;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.Menu;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Hub;
import me.vorps.hub.Object.Game;
import me.vorps.hub.Object.Server;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuPrincipal extends MenuRecursive{

	private MenuPrincipal(Player player, ArrayList<Item> list){
        super(new byte[] {4, 1}, Bukkit.createInventory(null, 45, "§6Menu Principal"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {4, 0}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {18, 0}, {26, 0}, {37, 1}, {38, 0}, {40, 0}, {42, 0}, {43, 1}, {44, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9);
        initMenu(player, 1);
        player.openInventory(menu);
	}

    @Override
    public void initMenu(Player player, int page){
        menu.setItem(9, new Item(Material.BOOK).withName("§6Aide").withLore(new String[] {"§7Besoin d'aide ?"}).get());
        menu.setItem(17, new Item(Material.NETHER_STAR).withName("§6Hub").withLore(new String[] {"§7Changer de hub"}).get());
        menu.setItem(27, new Item(Material.LEATHER_BOOTS).withName("§6Jump").withColor(Color.PURPLE).withLore(new String[] {"§7Jumps"}).get());
        menu.setItem(35, new Item(Material.DIAMOND).withName("§6Events").withLore(new String[] {"§7Events prochainement"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        menu.setItem(39, new Item(Material.BEACON).withName("§6Zone VIP").withLore(new String[] {"§7Espace réservé exclusivement aux §a§nVIP"}).get());
        menu.setItem(41, new Item(322).withData((byte) 1).withName("§6Mini Jeux en BETA").withLore(new String[] {"§7En développement"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        Game.getGameList().values().forEach((Game game) -> list.add(game.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang())));
        new MenuPrincipal(player, list);

    }
    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case BOOK:
                MenuHelp.createMenu(player);
                break;
            case NETHER_STAR:
                Server.getServer().values().forEach((Server server) -> server.update());
                for(Server server : Server.getServerOnline().values()){
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("PlayerList");
                    out.writeUTF(server.getName());
                    player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
                }
                break;
            case LEATHER_BOOTS:
                MenuJump.createMenu(player);
                break;
            case DIAMOND:
                new MenuEvent(player);
                break;
            case ARROW:
                player.closeInventory();
                break;
            case BEACON:
                break;
            case PAPER:
                initMenu(player, ++page);
                break;
            case EMPTY_MAP:
                initMenu(player, --page);
                break;
            default:
                Game game = Game.getGame(itemStack.getType().getId(), PlayerData.getPlayerData(player.getName()).getLang());
                if(game != null){
                    MenuGame.createMenu(player, game);
                }
                break;
        }
    }
}
