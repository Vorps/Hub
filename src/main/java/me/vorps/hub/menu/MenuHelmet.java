package me.vorps.hub.menu;

import me.vorps.hub.shop.Shop;
import me.vorps.hub.shop.ShopManager;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuHelmet extends Shop {

	public MenuHelmet(Player player, int page){
        super(new byte[] {10, 2, 6}, null , null, player.getName(), "ce chapeau sur votre tête.");
        switch (page) {
            case 1:
                model = new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 0}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 2}, {35, 2}, {36, 1}, {44, 1}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}, {52, 1}};
                menu = Bukkit.createInventory(null, 54, "§6Boutique > Costumes > Chapeaux");
                constructModel();
                menu.setItem(4, new Item(Material.GOLD_HELMET).withName("§6Chapeaux").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
                menu.setItem(10, new Item(Material.CHAINMAIL_HELMET).withName("§6Cotte de maille").withLore(getPurchase().purchase(ShopManager.CHAINMAIL_HELMET)).get());
                menu.setItem(11, new Item(Material.GOLD_HELMET).withName("§6Or").withLore(getPurchase().purchase(ShopManager.GOLD_HELMET)).get());
                menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mon chapeaux").withLore(new String[]{"§7Retire votre chapeaux actuel"}).get());
                menu.setItem(15, new Item(Material.IRON_HELMET).withName("§6Fer").withLore(getPurchase().purchase(ShopManager.IRON_HELMET)).get());
                menu.setItem(16, new Item(Material.DIAMOND_HELMET).withName("§6Diamant").withLore(getPurchase().purchase(ShopManager.DIAMOND_HELMET)).get());
                menu.setItem(21, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.AQUA).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_AQUA)).get());
                menu.setItem(22, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.BLACK).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_BLACK)).get());
                menu.setItem(23, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.BLUE).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_BLUE)).get());
                menu.setItem(28, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.FUCHSIA).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_FUCHSIA)).get());
                menu.setItem(29, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.GRAY).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_GRAY)).get());
                menu.setItem(30, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.GREEN).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_GREEN)).get());
                menu.setItem(31, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.LIME).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_LIME)).get());
                menu.setItem(32, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.MAROON).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_MAROON)).get());
                menu.setItem(33, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.NAVY).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_NAVY)).get());
                menu.setItem(34, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.OLIVE).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_OLIVE)).get());
                menu.setItem(37, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.ORANGE).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_ORANGE)).get());
                menu.setItem(38, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.PURPLE).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_PURPLE)).get());
                menu.setItem(39, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.RED).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_RED)).get());
                menu.setItem(40, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.SILVER).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_SILVER)).get());
                menu.setItem(41, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.TEAL).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_TEAL)).get());
                menu.setItem(42, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.WHITE).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_WHITE)).get());
                menu.setItem(43, new Item(Material.LEATHER_HELMET).withName("§6Cuir coloré").withColor(Color.YELLOW).withLore(getPurchase().purchase(ShopManager.LEATHER_HELMET_YELLOW)).get());
                menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[]{"§7Retour au menu Costumes"}).get());
                menu.setItem(53, new Item(Material.PAPER).withName("§6Page Suivante -> Têtes").withLore(new String[]{"§7Page Têtes"}).get());
                break;
            case 2:
                model = new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 0}, {4, 1}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}};
                menu = Bukkit.createInventory(null, 54, "§6Boutique > Costumes > Têtes");
                constructModel();
                menu.setItem(10, new Item("MHF_Pig").withName("§6Cochon").withLore(getPurchase().purchase(ShopManager.HELMET_PIG)).get());
                menu.setItem(11, new Item("MHF_Cow").withName("§6Vache").withLore(getPurchase().purchase(ShopManager.HELMET_COW)).get());
                menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mon chapeaux").withLore(new String[]{"§7Retire votre chapeaux actuel"}).get());
                menu.setItem(15, new Item("MHF_Sheep").withName("§6Mouton").withLore(getPurchase().purchase(ShopManager.HELMET_SHEEP)).get());
                menu.setItem(16, new Item("MHF_Rabbit").withName("§6Lapin").withLore(getPurchase().purchase(ShopManager.HELMET_RABBIT)).get());
                menu.setItem(18, new Item("MHF_SilverFish").withName("§6SilverFish").withLore(getPurchase().purchase(ShopManager.HELMET_SILVERFISH)).get());
                menu.setItem(19, new Item("MHF_Zombie").withName("§6Zombie").withLore(getPurchase().purchase(ShopManager.HELMET_ZOMBIE)).get());
                menu.setItem(20, new Item("MHF_Spider").withName("§6Araignée").withLore(getPurchase().purchase(ShopManager.HELMET_SPIDER)).get());
                menu.setItem(21, new Item("MHF_Creeper").withName("§6Creeper").withLore(getPurchase().purchase(ShopManager.HELMET_CREEPER)).get());
                menu.setItem(22, new Item("MHF_Skeleton").withName("§6Squelette").withLore(getPurchase().purchase(ShopManager.HELMET_SKELETON)).get());
                menu.setItem(23, new Item("MHF_PigZombie").withName("§6Cochon Zombie").withLore(getPurchase().purchase(ShopManager.HELMET_PIG_ZOMBIE)).get());
                menu.setItem(24, new Item("MHF_CaveSpider").withName("§6Araignée des caves").withLore(getPurchase().purchase(ShopManager.HELMET_CAVE_SPIDER)).get());
                menu.setItem(25, new Item("MHF_Ocelot").withName("§6Ocelot").withLore(getPurchase().purchase(ShopManager.HELMET_OCELOT)).get());
                menu.setItem(26, new Item("MHF_Wolf").withName("§6Chien").withLore(getPurchase().purchase(ShopManager.HELMET_WOLF)).get());
                menu.setItem(27, new Item("MHF_Cat").withName("§6Chat").withLore(getPurchase().purchase(ShopManager.HELMET_CAT)).get());
                menu.setItem(28, new Item("MHF_Chicken").withName("§6Poule").withLore(getPurchase().purchase(ShopManager.HELMET_CHICKEN)).get());
                menu.setItem(29, new Item("MHF_Villager").withName("§6Villageois").withLore(getPurchase().purchase(ShopManager.HELMET_VILLAGER)).get());
                menu.setItem(30, new Item("MHF_WSkeleton").withName("§6Whither Skeleton").withLore(getPurchase().purchase(ShopManager.HELMET_WSKELETON)).get());
                menu.setItem(31, new Item("MHF_Slime").withName("§6Slime").withLore(getPurchase().purchase(ShopManager.HELMET_SLIME)).get());
                menu.setItem(32, new Item("MHF_LavaSlime").withName("§6Slime en Lave").withLore(getPurchase().purchase(ShopManager.HELMET_LAVA_SLIME)).get());
                menu.setItem(33, new Item("MHF_Enderman").withName("§6Enderman").withLore(getPurchase().purchase(ShopManager.HELMET_ENDERMAN)).get());
                menu.setItem(34, new Item("MHF_Endermite").withName("§6EnderMite").withLore(getPurchase().purchase(ShopManager.HELMET_ENDERMITE)).get());
                menu.setItem(35, new Item("MHF_MushtroomCow").withName("§6Vache Champignon").withLore(getPurchase().purchase(ShopManager.HELMET_MUSHROOM_COW)).get());
                menu.setItem(36, new Item("MHF_Golem").withName("§6Golem").withLore(getPurchase().purchase(ShopManager.HELMET_GOLEM)).get());
                menu.setItem(37, new Item("MHF_SnowGolem").withName("§6Golem des neiges").withLore(getPurchase().purchase(ShopManager.HELMET_SNOW_GOLEM)).get());
                menu.setItem(38, new Item("MHF_Witch").withName("§6Sorcière").withLore(getPurchase().purchase(ShopManager.HELMET_WITCH)).get());
                menu.setItem(39, new Item("MHF_Squid").withName("§6Squid").withLore(getPurchase().purchase(ShopManager.HELMET_SQUID)).get());
                menu.setItem(40, new Item("MHF_Blaze").withName("§6Blaze").withLore(getPurchase().purchase(ShopManager.HELMET_BLAZE)).get());
                menu.setItem(41, new Item("MHF_Guardian").withName("§6Guardian").withLore(getPurchase().purchase(ShopManager.HELMET_GUARDIAN)).get());
                menu.setItem(42, new Item("MHF_Ghast").withName("§6Ghast").withLore(getPurchase().purchase(ShopManager.HELMET_GHAST)).get());
                menu.setItem(43, new Item("MHF_Wither").withName("§6Wither").withLore(getPurchase().purchase(ShopManager.HELMET_WITHER)).get());
                menu.setItem(44, new Item("MHF_EnderDragon").withName("§6Ender Dragon").withLore(getPurchase().purchase(ShopManager.HELMET_ENDERDRAGON)).get());
                menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[]{"§7Retour au menu Costumes"}).get());
                menu.setItem(52, new Item(Material.EMPTY_MAP).withName("§6<- Page Précedente -> Chapeaux").withLore(new String[]{"§7Retour au menu Chapeaux"}).get());
                menu.setItem(53, new Item(Material.PAPER).withName("§6Page Suivante -> Block").withLore(new String[]{"§7Page Block"}).get());
                break;
            case 3:
                model = new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 0}, {4, 1}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 2}, {35, 2}, {36, 1}, {44, 1}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}, {52, 1}};
                menu = Bukkit.createInventory(null, 54, "§6Boutique > Costumes > Blocks");
                constructModel();
                menu.setItem(10, new Item(Material.CHEST).withName("§6Coffre").withLore(getPurchase().purchase(ShopManager.HELMET_CHEST)).get());
                menu.setItem(11, new Item(Material.ENCHANTMENT_TABLE).withName("§6Table d'enchante").withLore(getPurchase().purchase(ShopManager.HELMET_ENCHANTMENT_TABLE)).get());
                menu.setItem(12, new Item(Material.ANVIL).withName("§6Enclume").withLore(getPurchase().purchase(ShopManager.HELMET_ANVIL)).get());
                menu.setItem(13, new Item(Material.ENDER_CHEST).withName("§6Coffre de l'end").withLore(getPurchase().purchase(ShopManager.HELMET_ENDER_CHEST)).get());
                menu.setItem(14, new Item(Material.FURNACE).withName("§6Four").withLore(getPurchase().purchase(ShopManager.HELMET_FURNACE)).get());
                menu.setItem(15, new Item(Material.DROPPER).withName("§6Dropper").withLore(getPurchase().purchase(ShopManager.HELMET_DROPPER)).get());
                menu.setItem(16, new Item(Material.DISPENSER).withName("§6Dispenser").withLore(getPurchase().purchase(ShopManager.HELMET_DISPENSER)).get());
                menu.setItem(19, new Item(Material.NOTE_BLOCK).withName("§6Note Book").withLore(getPurchase().purchase(ShopManager.HELMET_NOTE_BLOCK)).get());
                menu.setItem(20, new Item(Material.TNT).withName("§6TNT").withLore(getPurchase().purchase(ShopManager.HELMET_TNT)).get());
                menu.setItem(21, new Item(Material.PISTON_BASE).withName("§6Piston").withLore(getPurchase().purchase(ShopManager.HELMET_PISTON_BASE)).get());
                menu.setItem(22, new Item(Material.SLIME_BLOCK).withName("§6Block de slime").withLore(getPurchase().purchase(ShopManager.HELMET_SLIME_BLOCK)).get());
                menu.setItem(23, new Item(Material.MELON_BLOCK).withName("§6Pasteque").withLore(getPurchase().purchase(ShopManager.HELMET_MELON_BLOCK)).get());
                menu.setItem(24, new Item(Material.HAY_BLOCK).withName("§6Paille").withLore(getPurchase().purchase(ShopManager.HELMET_HAY_BLOCK)).get());
                menu.setItem(25, new Item(58).withData((byte) 0).withName("§6Table de craft").withLore(getPurchase().purchase(ShopManager.HELMET_CRAFT_TABLE)).get());
                menu.setItem(29, new Item(Material.BOOKSHELF).withName("§6Bibliothèque").withLore(getPurchase().purchase(ShopManager.HELMET_BOOKSHELF)).get());
                menu.setItem(30, new Item(Material.GLASS).withName("§6Verre").withLore(getPurchase().purchase(ShopManager.HELMET_GLASS)).get());
                menu.setItem(31, new Item(Material.PUMPKIN).withName("§6Citrouille").withLore(getPurchase().purchase(ShopManager.HELMET_PUMPKIN)).get());
                menu.setItem(32, new Item(Material.JACK_O_LANTERN).withName("§6JACK_O_LANTERN").withLore(getPurchase().purchase(ShopManager.HELMET_JACK_O_LANTERN)).get());
                menu.setItem(33, new Item(100).withData((byte) 0).withName("§6Block champignon").withLore(getPurchase().purchase(ShopManager.HELMET_RED_MUSHROOM)).get());
                menu.setItem(37, new Item(Material.IRON_BLOCK).withName("§6Block de fer").withLore(getPurchase().purchase(ShopManager.HELMET_IRON_BLOCK)).get());
                menu.setItem(38, new Item(Material.LAPIS_BLOCK).withName("§6Lapis").withLore(getPurchase().purchase(ShopManager.HELMET_LAPIS_BLOCK)).get());
                menu.setItem(39, new Item(Material.REDSTONE_BLOCK).withName("§6Block de redstone").withLore(getPurchase().purchase(ShopManager.HELMET_REDSTONE_BLOCK)).get());
                menu.setItem(40, new Item(Material.GOLD_BLOCK).withName("§6Block d'or").withLore(getPurchase().purchase(ShopManager.HELMET_GOLD_BLOCK)).get());
                menu.setItem(41, new Item(Material.EMERALD_BLOCK).withName("§6Block d'émeraude").withLore(getPurchase().purchase(ShopManager.HELMET_EMERALD_BLOCK)).get());
                menu.setItem(42, new Item(Material.DIAMOND_BLOCK).withName("§6Block de diamand").withLore(getPurchase().purchase(ShopManager.HELMET_DIAMOND_BLOCK)).get());
                menu.setItem(43, new Item(Material.BEACON).withName("§6Beacon").withLore(getPurchase().purchase(ShopManager.HELMET_BEACON)).get());
                menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[]{"§7Retour au menu Costumes"}).get());
                menu.setItem(53, new Item(Material.EMPTY_MAP).withName("§6Page Précedente -> Têtes").withLore(new String[]{"§7Retour au menu Têtes"}).get());
                break;
            default:
                break;
        }
        player.openInventory(menu);
	}
}
