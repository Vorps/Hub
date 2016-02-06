package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.utils.Purchase;

public class MenuLeggings {

    public static void HubMenuLeggings(PlayerHub playerHub){
        Purchase purchase = new Purchase(playerHub, "ce pantalon sur vos jambes.");
        Inventory menuLeggings = Bukkit.createInventory(null, 54, "§6Boutique > Costumes > ");
        menuLeggings.setItem(0, new Item(160).withData((byte) 11).withName(" ").get());
        menuLeggings.setItem(1, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(2, new Item(160).withData((byte) 3).withName(" ").get());
        menuLeggings.setItem(3, new Item(160).withData((byte) 11).withName(" ").get());
        menuLeggings.setItem(4, new Item(Material.GOLD_LEGGINGS).withName("§6Pantalons").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menuLeggings.setItem(5, new Item(160).withData((byte) 11).withName(" ").get());
        menuLeggings.setItem(6, new Item(160).withData((byte) 3).withName(" ").get());
        menuLeggings.setItem(7, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(8, new Item(160).withData((byte) 11).withName(" ").get());
        menuLeggings.setItem(9, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(10, new Item(Material.CHAINMAIL_LEGGINGS).withName("§6Cotte de maille").withLore(purchase.purchase("Chainmail_leggings")).get());
        menuLeggings.setItem(11, new Item(Material.GOLD_LEGGINGS).withName("§6Or").withLore(purchase.purchase("Gold_leggings")).get());
        menuLeggings.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mon pantalon").withLore(new String[] {"§7Retire votre pantalon actuel"}).get());
        menuLeggings.setItem(15, new Item(Material.IRON_LEGGINGS).withName("§6Fer").withLore(purchase.purchase("Iron_leggings")).get());
        menuLeggings.setItem(16, new Item(Material.DIAMOND_LEGGINGS).withName("§6Diamond").withLore(purchase.purchase("Diamond_leggings")).get());
        menuLeggings.setItem(17, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(18, new Item(160).withData((byte) 3).withName(" ").get());

        menuLeggings.setItem(21,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir coloré").withColor(Color.AQUA).withLore(purchase.purchase("Leather_leggings_aqua")).get());
        menuLeggings.setItem(22,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.BLACK).withLore(purchase.purchase("Leather_leggings_black")).get());
        menuLeggings.setItem(23,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.BLUE).withLore(purchase.purchase("Leather_leggings_blue")).get());
        menuLeggings.setItem(26, new Item(160).withData((byte) 3).withName(" ").get());
        menuLeggings.setItem(27, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(28,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.FUCHSIA).withLore(purchase.purchase("Leather_leggings_fuchsia")).get());
        menuLeggings.setItem(29,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.GRAY).withLore(purchase.purchase("Leather_leggings_gray")).get());
        menuLeggings.setItem(30,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.GREEN).withLore(purchase.purchase("Leather_leggings_green")).get());
        menuLeggings.setItem(31,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.LIME).withLore(purchase.purchase("Leather_leggings_lime")).get());
        menuLeggings.setItem(32,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.MAROON).withLore(purchase.purchase("Leather_leggings_maroon")).get());
        menuLeggings.setItem(33,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.NAVY).withLore(purchase.purchase("Leather_leggings_navy")).get());
        menuLeggings.setItem(34,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.OLIVE).withLore(purchase.purchase("Leather_leggings_olive")).get());
        menuLeggings.setItem(35, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(36, new Item(160).withData((byte) 3).withName(" ").get());
        menuLeggings.setItem(37,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.ORANGE).withLore(purchase.purchase("Leather_leggings_orange")).get());
        menuLeggings.setItem(38,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.PURPLE).withLore(purchase.purchase("Leather_leggings_purple")).get());
        menuLeggings.setItem(39,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.RED).withLore(purchase.purchase("Leather_leggings_red")).get());
        menuLeggings.setItem(40,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.SILVER).withLore(purchase.purchase("Leather_leggings_silver")).get());
        menuLeggings.setItem(41,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.TEAL).withLore(purchase.purchase("Leather_leggings_teal")).get());
        menuLeggings.setItem(42,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.WHITE).withLore(purchase.purchase("Leather_leggings_white")).get());
        menuLeggings.setItem(43,  new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.YELLOW).withLore(purchase.purchase("Leather_leggings_yellow")).get());
        menuLeggings.setItem(44, new Item(160).withData((byte) 3).withName(" ").get());
        menuLeggings.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
        menuLeggings.setItem(46, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(47, new Item(160).withData((byte) 3).withName(" ").get());
        menuLeggings.setItem(48, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(49, new Item(160).withData((byte) 11).withName(" ").get());
        menuLeggings.setItem(50, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(51, new Item(160).withData((byte) 3).withName(" ").get());
        menuLeggings.setItem(52, new Item(160).withData((byte) 5).withName(" ").get());
        menuLeggings.setItem(53, new Item(160).withData((byte) 11).withName(" ").get());
        Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuLeggings);
	}
}
