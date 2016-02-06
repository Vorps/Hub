package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionType;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.utils.Purchase;

public class MenuParticles {

	public static void HubmenuParticle(PlayerHub playerHub){
		Purchase purchase = new Purchase(playerHub, "cette particule");
		Inventory menuParticle = Bukkit.createInventory(null, 45, "§6Boutique > Particules");
		menuParticle.setItem(0, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(1, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(2, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(3, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(4, new Item(Material.GLOWSTONE_DUST).withName("§6Pets").get());
		menuParticle.setItem(5, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(6, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(7, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(8, new Item(160).withData((byte) 4).withName(" ").get());

		menuParticle.setItem(9, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(10, new Item(PotionType.WATER).withName("§6Bulles").withLore(purchase.purchase("Particule_Bulles")).get());
		menuParticle.setItem(11, new Item(Material.FIREBALL).withName("§6Colère").withLore(purchase.purchase("Particule_Colère")).get());
		menuParticle.setItem(12, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(13, new Item(Material.BARRIER).withName("§6Retirer ma particules").withLore(new String[] {"§7Retirer ma particule actuel"}).get());
		menuParticle.setItem(14, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(15, new Item(38).withData((byte) 0).withName("§6Coeurs").withLore(purchase.purchase("Particule_Coeurs")).get());
		menuParticle.setItem(16, new Item(388).withData((byte) 0).withName("§6Particules vertes").withLore(purchase.purchase("Particule_Vertes")).get());
		menuParticle.setItem(17, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(18, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(19, new Item(Material.TORCH).withName("§6Fummèe").withLore(purchase.purchase("Particule_Fumée")).get());
		menuParticle.setItem(21, new Item(PotionType.SPEED).withName("§6Particules de potion").withLore(purchase.purchase("Particule_Potion")).get());
		menuParticle.setItem(22,  new Item(PotionType.STRENGTH).withName("§6Dégâts Magiques").withLore(purchase.purchase("Particule_Dégâts_Magiques")).get());
		menuParticle.setItem(23,  new Item(PotionType.SPEED).withName("§6Particules de spash potion").withLore(purchase.purchase("Particule_Splash_Potion")).get());
		menuParticle.setItem(25, new Item(Material.CAULDRON_ITEM).withName("§6Sorci�re").withLore(purchase.purchase("Particule_Sorcière")).get());
		menuParticle.setItem(26, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(27, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(28, new Item(Material.NOTE_BLOCK).withName("§6Note de musique").withLore(purchase.purchase("Particule_Note_Musique")).get());
		menuParticle.setItem(29, new Item(351).withData((byte) 15).withName("§6Nuage Blanc").withLore(purchase.purchase("Particule_Nuage_Blanc")).get());
		menuParticle.setItem(30, new Item(Material.LAVA_BUCKET).withName("§6Lave").withLore(purchase.purchase("Particule_Lave")).get());
		menuParticle.setItem(31, new Item(Material.SLIME_BALL).withName("§6Slime").withLore(purchase.purchase("Particule_Slime")).get());
		menuParticle.setItem(32, new Item(Material.BLAZE_POWDER).withName("§6Flame").withLore(purchase.purchase("Particule_Flame")).get());
		menuParticle.setItem(33, new Item(Material.IRON_SWORD).withName("§6D�g�ts").withLore(purchase.purchase("Particule_Dégàts")).get());
		menuParticle.setItem(34, new Item(Material.ENCHANTMENT_TABLE).withName("§6Table d'enchantement").withLore(purchase.purchase("Particule_Enchantement")).get());
		menuParticle.setItem(35, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
		menuParticle.setItem(37, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(38, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(39, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(40, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(41, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(42, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(43, new Item(160).withData((byte) 4).withName(" ").get());
		menuParticle.setItem(44, new Item(160).withData((byte) 4).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuParticle);
	}
}
