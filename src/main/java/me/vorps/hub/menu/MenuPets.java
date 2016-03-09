package me.vorps.hub.menu;

import me.vorps.hub.shop.Shop;
import me.vorps.hub.shop.ShopManager;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuPets extends Shop {

	public MenuPets(Player player){
        super(new byte[] {0, 8}, Bukkit.createInventory(null, 54, "§6Boutique > Pets"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {46, 1}, {47, 0}, {48, 1}, {49, 0}, {50, 1}, {51, 0}, {52, 1}, {53, 0}}, player.getName(), "ce pets");
		menu.setItem(4, new Item(Material.BONE).withName("§6Pets").get());
		menu.setItem(9, new Item("MHF_Pig").withName("§6Cochon").withLore(getPurchase().purchase(ShopManager.PETS_PIG)).get());
		menu.setItem(10, new Item("MHF_Cow").withName("§6Vache").withLore(getPurchase().purchase(ShopManager.PETS_COW)).get());
		menu.setItem(11, new Item("MHF_Sheep").withName("§6Mouton").withLore(getPurchase().purchase(ShopManager.PETS_SHEEP)).get());
		menu.setItem(12, new Item("MHF_Rabbit").withName("§6Lapin").withLore(getPurchase().purchase(ShopManager.PETS_RABBIT)).get());
		menu.setItem(13, new Item(Material.SADDLE).withName("§6Cheval").withLore(getPurchase().purchase(ShopManager.PETS_HORSE)).get());
		menu.setItem(14, new Item("MHF_SilverFish").withName("§6SilverFish").withLore(getPurchase().purchase(ShopManager.PETS_SILVERFISH)).get());
		menu.setItem(15, new Item("MHF_Zombie").withName("§6Zombie").withLore(getPurchase().purchase(ShopManager.PETS_ZOMBIE)).get());
		menu.setItem(16, new Item("MHF_Spider").withName("§6Araignée").withLore(getPurchase().purchase(ShopManager.PETS_SPIDER)).get());
		menu.setItem(17, new Item("MHF_Creeper").withName("§6Creeper").withLore(getPurchase().purchase(ShopManager.PETS_CREEPER)).get());
		menu.setItem(18, new Item("MHF_Skeleton").withName("§6Squelette").withLore(getPurchase().purchase(ShopManager.PETS_SKELETON)).get());
		menu.setItem(19, new Item("MHF_PigZombie").withName("§6Cochon Zombie").withLore(getPurchase().purchase(ShopManager.PETS_PIGZOMBIE)).get());
		menu.setItem(20, new Item("MHF_CaveSpider").withName("§6Araignée des caves").withLore(getPurchase().purchase(ShopManager.PETS_CAVESPIDER)).get());
		menu.setItem(21, new Item("MHF_Ocelot").withName("§6Ocelot").withLore(getPurchase().purchase(ShopManager.PETS_OCELOT)).get());
		menu.setItem(22, new Item("MHF_Wolf").withName("§6Chien").withLore(getPurchase().purchase(ShopManager.PETS_WOLF)).get());
		menu.setItem(23, new Item("MHF_Cat").withName("§6Chat").withLore(getPurchase().purchase(ShopManager.PETS_CAT)).get());
		menu.setItem(24, new Item("MHF_Chicken").withName("§6Poule").withLore(getPurchase().purchase(ShopManager.PETS_CHICKEN)).get());
		menu.setItem(25, new Item("MHF_Villager").withName("§6Villageois").withLore(getPurchase().purchase(ShopManager.PETS_VILLAGER)).get());
		menu.setItem(26, new Item("MHF_WSkeleton").withName("§6Whither Skeleton").withLore(getPurchase().purchase(ShopManager.PETS_WSKELETON)).get());
		menu.setItem(27, new Item("MHF_Slime").withName("§6Slime").withLore(getPurchase().purchase(ShopManager.PETS_SLIME)).get());
		menu.setItem(28, new Item("MHF_LavaSlime").withName("§6Slime en Lave").withLore(getPurchase().purchase(ShopManager.PETS_LAVASLIME)).get());
		menu.setItem(29, new Item("MHF_Enderman").withName("§6Enderman").withLore(getPurchase().purchase(ShopManager.PETS_ENDERMAN)).get());
		menu.setItem(30, new Item("MHF_Endermite").withName("§6EnderMite").withLore(getPurchase().purchase(ShopManager.PETS_ENDERMITE)).get());
		menu.setItem(31, new Item("MHF_MushtroomCow").withName("§6Vache Champignon").withLore(getPurchase().purchase(ShopManager.PETS_MUSHROOMCOW)).get());
		menu.setItem(32, new Item("MHF_Golem").withName("§6Golem").withLore(getPurchase().purchase(ShopManager.PETS_GOLEM)).get());
		menu.setItem(33, new Item("MHF_SnowGolem").withName("§6Golem des neiges").withLore(getPurchase().purchase(ShopManager.PETS_SNOWGOLEM)).get());
		menu.setItem(34, new Item("MHF_Witch").withName("§6Sorcière").withLore(getPurchase().purchase(ShopManager.PETS_WITCH)).get());
		menu.setItem(35, new Item("MHF_Squid").withName("§6Squid").withLore(getPurchase().purchase(ShopManager.PETS_SQUID)).get());
		menu.setItem(37, new Item(383).withData((byte) 50).withName("§6Creeper").withEnchant(Enchantment.ARROW_DAMAGE, 1).withLore(getPurchase().purchase(ShopManager.PETS_SPAWN_CREEPER)).get());
		menu.setItem(38, new Item("MHF_Blaze").withName("§6Blaze").withLore(getPurchase().purchase(ShopManager.PETS_BLAZE)).get());
		menu.setItem(39, new Item("MHF_Guardian").withName("§6Guardian").withLore(getPurchase().purchase(ShopManager.PETS_GUARDIAN)).get());
		menu.setItem(40, new Item("MHF_Ghast").withName("§6Ghast").withLore(getPurchase().purchase(ShopManager.PETS_GHAST)).get());
		menu.setItem(41, new Item(Material.ROTTEN_FLESH).withName("§6Zombie Géant").withLore(getPurchase().purchase(ShopManager.PETS_ZOMBIE_GIANT)).get());
		menu.setItem(42, new Item("MHF_Wither").withName("§6Wither").withLore(getPurchase().purchase(ShopManager.PETS_WITHER)).get());
		menu.setItem(43, new Item("MHF_EnderDragon").withName("§6Ender Dragon").withLore(getPurchase().purchase(ShopManager.PETS_ENDERDRAGON)).get());
		menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        player.openInventory(menu);
	}
}
