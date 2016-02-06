package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.utils.Purchase;

public class MenuGadgets {
	public static void HubMenuGadgets(PlayerHub playerHub){
		Purchase purchase = new Purchase(playerHub, "ce gadget");
		Inventory menuGadgets = Bukkit.createInventory(null, 45, "§6Boutique > Gadgets");
		menuGadgets.setItem(0, new Item(160).withData((byte) 4).withName(" ").get());
		menuGadgets.setItem(1, new Item(160).withData((byte) 5).withName(" ").get());
		menuGadgets.setItem(2, new Item(160).withData((byte) 1).withName(" ").get());
		menuGadgets.setItem(3, new Item(160).withData((byte) 4).withName(" ").get());
		menuGadgets.setItem(4, new Item(Material.BLAZE_ROD).withName("§6Gadgets").get());
		menuGadgets.setItem(5, new Item(160).withData((byte) 1).withName(" ").get());
		menuGadgets.setItem(6, new Item(160).withData((byte) 4).withName(" ").get());
		menuGadgets.setItem(7, new Item(160).withData((byte) 5).withName(" ").get());
		menuGadgets.setItem(8, new Item(160).withData((byte) 1).withName(" ").get());
		menuGadgets.setItem(9, new Item(160).withData((byte) 1).withName(" ").get());
		menuGadgets.setItem(17, new Item(160).withData((byte) 1).withName(" ").get());
		menuGadgets.setItem(18, new Item(160).withData((byte) 5).withName(" ").get());
		menuGadgets.setItem(21, new Item(383).withData((byte) 65).withName("§6Lance Bat").withLore(purchase.purchase("Gadgets_bat")).get());
		menuGadgets.setItem(23, new Item(Material.SNOW_BALL).withName("§6Lance Couleur").withLore(purchase.purchase("Gadgets_Color")).get());
		menuGadgets.setItem(26, new Item(160).withData((byte) 5).withName(" ").get());
		menuGadgets.setItem(27, new Item(160).withData((byte) 4).withName(" ").get());
		menuGadgets.setItem(35, new Item(160).withData((byte) 1).withName(" ").get());
		menuGadgets.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
		menuGadgets.setItem(37, new Item(160).withData((byte) 5).withName(" ").get());
		menuGadgets.setItem(38, new Item(160).withData((byte) 4).withName(" ").get());
		menuGadgets.setItem(39, new Item(160).withData((byte) 1).withName(" ").get());
		menuGadgets.setItem(40, new Item(160).withData((byte) 5).withName(" ").get());
		menuGadgets.setItem(41, new Item(160).withData((byte) 4).withName(" ").get());
		menuGadgets.setItem(42, new Item(160).withData((byte) 1).withName(" ").get());
		menuGadgets.setItem(43, new Item(160).withData((byte) 5).withName(" ").get());
		menuGadgets.setItem(44, new Item(160).withData((byte) 4).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuGadgets);
	}
}
