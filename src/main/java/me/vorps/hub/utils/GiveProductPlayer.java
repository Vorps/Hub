package me.vorps.hub.utils;

import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.Products;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class GiveProductPlayer {

    public static void giveItemPlayer(UUID uuid, Products products, boolean state){
        GiveProductPlayer.giveItemPlayer(uuid, products, state, products.getItem().get(PlayerData.getLang(uuid)).get());
    }

    public static void giveItemPlayer(UUID uuid, Products products, boolean state, ItemStack itemStack){
        /*PlayerData playerData = PlayerData.getPlayerData(player.getName());

        if(state){
            try {
                Database.CORE.getDatabase().updateTable("player_money", "pm_player = '"+ player.getName()+"' && pm_money = '"+products.getMoney()+"'", new DatabaseManager.Values("pm_value", playerData.getMoney().get(products.getMoney())-products.getPrice()));
                Database.CORE.getDatabase().insertTable("player_product", player.getName(), products.getName(), new Timestamp(System.currentTimeMillis()));
            } catch (SqlException e){
                e.printStackTrace();
            }
            playerData.getMoneyFunction();
            if(products.getTime() > 0){
               player.sendMessage("§eVous avez acheté le produit : §a"+ products.getName()+" §epour une dure de "+ ConvertMillis.convertMillisToTime(products.getTime()));
            } else {
               player.sendMessage("§eVous avez acheté le produit : §a"+ products.getName());
            }
            playerData.getProductsPlayerFunction();
        }
        switch (products.getType()) {
            case 2:
                player.getInventory().setChestplate(new ItemBuilder(products.getItem().get(playerData.getLang())).withLore(new String[] {"§aRetirer ce plastron"}).get());
                player.sendMessage("§eVoici votre plastron !!!");
                player.closeInventory();
                break;
            case 3:
                player.getInventory().setLeggings(new ItemBuilder(products.getItem().get(playerData.getLang())).withLore(new String[] {"§aRetirer ce leggings"}).get());
                player.sendMessage("§eVoici votre leggings !!!");
                player.closeInventory();
                break;
            case 4:
                player.getInventory().setBoots(new ItemBuilder(products.getItem().get(playerData.getLang())).withLore(new String[] {"§aRetirer vos bottes"}).get());
                player.sendMessage("§eVoici vos bottes !!!");
                player.closeInventory();
                break;
            case 5:
                if(!playerData.getBonus().getBonus().equals(products.getName())){
                    try {
                        Database.CORE.getDatabase().updateTable("player_setting", "ps_player = '"+player.getName()+"'", new DatabaseManager.Values("ps_bonus", products.getName()));
                    } catch (SqlException e){
                        e.printStackTrace();
                    }
                    player.closeInventory();
                    playerData.setBonus(products.getName());
                }
                break;
            case 6:
                if(!playerData.getRank().getRank().equals(products.getName())){
                    try {
                        Database.CORE.getDatabase().updateTable("player_setting", "ps_player = '"+player.getName()+"'", new DatabaseManager.Values("ps_grade", products.getName()));
                    } catch (SqlException e){
                        e.printStackTrace();
                    }
                    //playerData.updateGrades();
                    player.sendMessage("§eVous êtes maintenant §a"+playerData.getRank()+"§e.");
                    player.closeInventory();
                    break;
                }
                player.closeInventory();
                break;
            case 7:
                playerData.setParticle(new Particle(player, products.getName()));
                player.sendMessage("§eVoici votre particule");
                player.closeInventory();
                break;
            case 8:
                me.vorps.hub.Object.Gadgets gadgets = me.vorps.hub.Object.Gadgets.getListGadgets().get(products.getName());
                gadgets.setGadgets(player, products.getName());
                player.closeInventory();
                break;
            case 9:
                new MenuPetsCustom(player, itemStack);
                break;
            default:
                if(products.getType() == 1 || products.getType() == 10 || products.getType() == 11){
                    player.getInventory().setHelmet(new ItemBuilder(products.getItem().get(playerData.getLang())).withLore(new String[] {"§aRetirer votre chapeau"}).get());
                    player.sendMessage("§eVoici votre chapeau !!!");
                    player.closeInventory();
                }
                break;
        }
        Navigator.profil(playerData, player, null);
    */}
}
