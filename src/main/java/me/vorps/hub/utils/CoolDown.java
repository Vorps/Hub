package me.vorps.hub.utils;

import me.vorps.fortycube.cooldown.CoolDowns;
import me.vorps.fortycube.utils.ActionBar;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Project Hub Created by Vorps on 08/03/2016 at 16:56.
 */
public class CoolDown {
    
    public static void visibilityAction(Player player, PlayerData playerData){
        if(CoolDowns.hasCoolDown(playerData.getNamePlayer(), "visible_player")){
            CoolDowns coolDowns = CoolDowns.getCoolDown(playerData.getNamePlayer(), "visible_player");
            if(coolDowns.getSecondsLeft() <= 0){
                coolDowns.removeCoolDown();
                playerData.visiblePlayer(player);
            } else {
                playerData.setNbrClickVisiblePlayer(playerData.getNbrClickVisiblePlayer()+1);
                if(playerData.getNbrClickVisiblePlayer()% Settings.getNbrClickMax() == 0){
                    ActionBar.sendActionBar("§6Vous avez abusé des bonnes chose !!!", player);
                    player.sendMessage("§7Vous avez abusé des bonnes chose !!!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Settings.getTimeConfusion(), 100));
                } else {
                    ActionBar.sendActionBar("§6§lEn cour de recharge !!!.", player);
                }
            }
        } else {
            playerData.setNbrClickVisiblePlayer(0);
            playerData.visiblePlayer(player);
        }
    }
}
