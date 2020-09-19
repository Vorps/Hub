package me.vorps.hub.menu;

import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Hub;
import me.vorps.hub.Object.Game;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuPrincipal extends MenuRecursive{

	private MenuPrincipal(UUID uuid, ArrayList<ItemBuilder> list){
        super(new byte[] {4, 1}, Bukkit.createInventory(null, 45, "§6Menu Principal"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {4, 0}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {18, 0}, {26, 0}, {37, 1}, {38, 0}, {40, 0}, {42, 0}, {43, 1}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.STATIC, Hub.getInstance());
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
	}

    @Override
    public void initMenu(UUID uuid, int page){
        menu.setItem(9, new ItemBuilder(Material.BOOK).withName("§6Aide").withLore(new String[] {"§7Besoin d'aide ?"}).get());
        menu.setItem(17, new ItemBuilder(Material.NETHER_STAR).withName("§6Hub").withLore(new String[] {"§7Changer de hub"}).get());
        menu.setItem(27, new ItemBuilder(Material.LEATHER_BOOTS).withName("§6Jump").withColor(Color.PURPLE).withLore(new String[] {"§7Jumps"}).get());
        menu.setItem(35, new ItemBuilder(Material.DIAMOND).withName("§6Events").withLore(new String[] {"§7Events prochainement"}).get());
        menu.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        menu.setItem(39, new ItemBuilder(Material.BEACON).withName("§6Zone VIP").withLore(new String[] {"§7Espace réservé exclusivement aux §a§nVIP"}).get());
        menu.setItem(41, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).withName("§6Mini Jeux en BETA").withLore(new String[] {"§7En développement"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Game.getGameList().values().forEach((Game game) -> list.add(game.getIcon().get(PlayerData.getLang(uuid))));
        new MenuPrincipal(uuid, list);

    }
    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case BOOK:
                MenuHelp.createMenu(uuid);
                break;
            case NETHER_STAR:
                /*Server.getServer().values().forEach((Server server) -> server.update());
                for(Server server : Server.getServerOnline().values()){
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("PlayerList");
                    out.writeUTF(server.getName());
                    player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
                }*/
                break;
            case LEATHER_BOOTS:
                MenuJump.createMenu(uuid);
                break;
            case DIAMOND:
                new MenuEvent(uuid);
                break;
            case ARROW:
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            case BEACON:
                break;
            case PAPER:
                initMenu(uuid, ++page);
                break;
            case MAP:
                initMenu(uuid, --page);
                break;
            default:
                Game game = Game.getGame(itemStack.getType().getId(), PlayerData.getLang(uuid));
                if(game != null){
                    MenuGame.createMenu(uuid, game);
                }
                break;
        }
    }
}
