package me.vorps.hub.menu;

import me.vorps.hub.Object.Bonus;
import me.vorps.hub.PlayerData;
import me.vorps.hub.data.SettingsHub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.objects.Money;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Navigator {

    public static void navigator(UUID uuid){
        Bukkit.getPlayer(uuid).getInventory().setItem(0, new ItemBuilder(Material.COMPASS).withName("§6Menu Principal").withLore(new String[] {"§7Accéder au menu principal"}).get());
        profil(uuid, null);
        /*if(playerData.getGadget() != null){
            player.getInventory().setItem(4, Products.getProduct(playerData.getGadget().getGadgets().getName()).getItem().get(PlayerData.getLang(uuid)).withLore(new String[] {}).get());
        }*/
        playerVisible(uuid);
        Bukkit.getPlayer(uuid).getInventory().setItem(8, new ItemBuilder(Material.ENDER_CHEST).withName("§6Boutique").withLore(new String[] {"§7Accéder au menu Boutique"}).get());
    }

    public static void playerVisible(UUID uuid){
        if(PlayerData.isVisible(uuid)){
            Bukkit.getPlayer(uuid).getInventory().setItem(7, new ItemBuilder(Material.BLAZE_POWDER).withName("§6Joueurs : (§aActivé§6)").withLore(new String[] {"§aPermet de masquer", "§a -§7 Les joueurs (Sauf Amis/Membre/Staff)"}).get());
        } else {
            Bukkit.getPlayer(uuid).getInventory().setItem(7, new ItemBuilder(Material.BLAZE_POWDER).withName("§6Joueurs : (§cDésactivé§6)").withLore(new String[] {"§aPermet de masquer", "§a -§7 Les joueurs (Sauf Amis/Membre/Staff)"}).get());
        }
    }

    public static void profil(UUID uuid, Inventory inv){
        List<String> des = new ArrayList<>();
        des.add("§6§l"+PlayerData.getPlayerData(uuid).getName());
        des.add("§7Grade : "+PlayerData.getRank(uuid));;
        Bonus bonus = PlayerData.getBonus(uuid);
        Money.getMoneys().stream().map(Money::getMoney).forEach((Money money) -> {
            des.add("§7"+money.getMoney()+" : "+money.getColor()+ PlayerData.getMoney(uuid, money.getMoney())+" "+money.getAlias()+" §bGains : §c"+100*(money.getMoney().equals(bonus.getMoney()) ? bonus.getCoefficient() : 1.0)+"%");
        });

        des.add("§eBonus : "+(bonus.getName().equals(SettingsHub.getDefaultBonus().getName()) ? "§cDésactivé" : "§a"+ bonus.getName()));

        if(inv == null){
            Bukkit.getPlayer(uuid).getInventory().setItem(1, new ItemBuilder(Bukkit.getPlayer(uuid).getName()).withName("§6Résumé du profil").withLore(des.toArray(des.toArray(new String[des.size()]))).get());
        } else {
            inv.setItem(22, new ItemBuilder(Bukkit.getPlayer(uuid).getName()).withName("§6Résumé du profil").withLore(des.toArray(des.toArray(new String[des.size()]))).get());
        }
        des.clear();
    }
}
