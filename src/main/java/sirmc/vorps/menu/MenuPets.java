package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.utils.Purchase;

public class MenuPets {

	public static void HubMenuPets(PlayerHub playerHub){
		Purchase purchase = new Purchase(playerHub, "ce pets");
		Inventory menuPets = Bukkit.createInventory(null, 54, "§6Boutique > Pets");
		menuPets.setItem(0, new Item(160).withData((byte) 0).withName(" ").get());
		menuPets.setItem(1, new Item(160).withData((byte) 8).withName(" ").get());
		menuPets.setItem(2, new Item(160).withData((byte) 0).withName(" ").get());
		menuPets.setItem(3, new Item(160).withData((byte) 8).withName(" ").get());
		menuPets.setItem(4, new Item(Material.BONE).withName("§6Pets").get());
		menuPets.setItem(5, new Item(160).withData((byte) 8).withName(" ").get());
		menuPets.setItem(6, new Item(160).withData((byte) 0).withName(" ").get());
		menuPets.setItem(7, new Item(160).withData((byte) 8).withName(" ").get());
		menuPets.setItem(8, new Item(160).withData((byte) 0).withName(" ").get());
		menuPets.setItem(9, new Item("MHF_Pig").withName("§6Cochon").withLore(purchase.purchase("Pets_Pig")).get());
		menuPets.setItem(10, new Item("MHF_Cow").withName("§6Vache").withLore(purchase.purchase("Pets_Cow")).get());
		menuPets.setItem(11, new Item("MHF_Sheep").withName("§6Mouton").withLore(purchase.purchase("Pets_Sheep")).get());
		menuPets.setItem(12, new Item("MHF_Rabbit").withName("§6Lapin").withLore(purchase.purchase("Pets_Rabbit")).get());
		menuPets.setItem(13, new Item(Material.SADDLE).withName("§6Cheval").withLore(purchase.purchase("Pets_Horse")).get());
		menuPets.setItem(14, new Item("MHF_SilverFish").withName("§6SilverFish").withLore(purchase.purchase("Pets_SilverFish")).get());
		menuPets.setItem(15, new Item("MHF_Zombie").withName("§6Zombie").withLore(purchase.purchase("Pets_Zombie")).get());
		menuPets.setItem(16, new Item("MHF_Spider").withName("§6Araign�e").withLore(purchase.purchase("Pets_Spider")).get());
		menuPets.setItem(17, new Item("MHF_Creeper").withName("§6Creeper").withLore(purchase.purchase("Pets_Creeper")).get());
		menuPets.setItem(18, new Item("MHF_Skeleton").withName("§6Squelette").withLore(purchase.purchase("Pets_Skeleton")).get());
		menuPets.setItem(19, new Item("MHF_PigZombie").withName("§6Cochon Zombie").withLore(purchase.purchase("Pets_PigZombie")).get());
		menuPets.setItem(20, new Item("MHF_CaveSpider").withName("§6Araign�e des caves").withLore(purchase.purchase("Pets_CaveSpider")).get());
		menuPets.setItem(21, new Item("MHF_Ocelot").withName("§6Ocelot").withLore(purchase.purchase("Pets_Ocelot")).get());
		menuPets.setItem(22, new Item("MHF_Wolf").withName("§6Chien").withLore(purchase.purchase("Pets_Wolf")).get());
		menuPets.setItem(23, new Item("MHF_Cat").withName("§6Chat").withLore(purchase.purchase("Pets_Cat")).get());
		menuPets.setItem(24, new Item("MHF_Chicken").withName("§6Poule").withLore(purchase.purchase("Pets_Chicken")).get());
		menuPets.setItem(25, new Item("MHF_Villager").withName("§6Villageois").withLore(purchase.purchase("Pets_Villager")).get());
		menuPets.setItem(26, new Item("MHF_WSkeleton").withName("§6Whither Skeleton").withLore(purchase.purchase("Pets_WSkeleton")).get());
		menuPets.setItem(27, new Item("MHF_Slime").withName("§6Slime").withLore(purchase.purchase("Pets_Slime")).get());
		menuPets.setItem(28, new Item("MHF_LavaSlime").withName("§6Slime en Lave").withLore(purchase.purchase("Pets_LavaSlime")).get());
		menuPets.setItem(29, new Item("MHF_Enderman").withName("§6Enderman").withLore(purchase.purchase("Pets_Enderman")).get());
		menuPets.setItem(30, new Item("MHF_Endermite").withName("§6EnderMite").withLore(purchase.purchase("Pets_Endermite")).get());
		menuPets.setItem(31, new Item("MHF_MushtroomCow").withName("§6Vache Champignon").withLore(purchase.purchase("Pets_MushroomCow")).get());
		menuPets.setItem(32, new Item("MHF_Golem").withName("§6Golem").withLore(purchase.purchase("Pets_Golem")).get());
		menuPets.setItem(33, new Item("MHF_SnowGolem").withName("§6Golem des neiges").withLore(purchase.purchase("Pets_SnowGolem")).get());
		menuPets.setItem(34, new Item("MHF_Witch").withName("§6Sorci�re").withLore(purchase.purchase("Pets_Witch")).get());
		menuPets.setItem(35, new Item("MHF_Squid").withName("§6Squid").withLore(purchase.purchase("Pets_Squid")).get());
		menuPets.setItem(37, new Item(383).withData((byte) 50).withName("§6Creeper").withEnchant(Enchantment.ARROW_DAMAGE, 1).withLore(purchase.purchase("Pets_SpawnCreeper")).get());
		menuPets.setItem(38, new Item("MHF_Blaze").withName("§6Blaze").withLore(purchase.purchase("Pets_Blaze")).get());
		menuPets.setItem(39, new Item("MHF_").withName("§6Guardian").withLore(purchase.purchase("Pets_Guardian")).get());
		menuPets.setItem(40, new Item("MHF_Ghast").withName("§6Ghast").withLore(purchase.purchase("Pets_Ghast")).get());
		menuPets.setItem(41, new Item(Material.ROTTEN_FLESH).withName("§6Zombie Géant").withLore(purchase.purchase("Pets_ZombieGéant")).get());
		menuPets.setItem(42, new Item("MHF_Wither").withName("§6Wither").withLore(purchase.purchase("Pets_Wither")).get());
		menuPets.setItem(43, new Item("MHF_EnderDragon").withName("§6Ender Dragon").withLore(purchase.purchase("Pets_EnderDragon")).get());
		menuPets.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
		menuPets.setItem(46, new Item(160).withData((byte) 0).withName(" ").get());
		menuPets.setItem(47, new Item(160).withData((byte) 8).withName(" ").get());
		menuPets.setItem(48, new Item(160).withData((byte) 0).withName(" ").get());
		menuPets.setItem(49, new Item(160).withData((byte) 8).withName(" ").get());
		menuPets.setItem(50, new Item(160).withData((byte) 0).withName(" ").get());
		menuPets.setItem(51, new Item(160).withData((byte) 8).withName(" ").get());
		menuPets.setItem(52, new Item(160).withData((byte) 0).withName(" ").get());
		menuPets.setItem(53, new Item(160).withData((byte) 8).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuPets);
	}
}
