package me.vorps.hub.utils;

/**
 * Project Hub Created by Vorps on 08/03/2016 at 16:56.
 */
public class CoolDown {
    
    /*public static void visibilityAction(UUID uuid){
        if(CoolDowns.hasCoolDown(playerData.getUUID(), "visible_player")){
            CoolDowns coolDowns = CoolDowns.getCoolDown(playerData.getNamePlayer(), "visible_player");
            if(coolDowns.getSecondsLeft() <= 0){
                coolDowns.removeCoolDown();
                playerData.visiblePlayer(player);
            } else {
                playerData.setNbrClickVisiblePlayer(playerData.getNbrClickVisiblePlayer()+1);
                if(playerData.getNbrClickVisiblePlayer()% Settings.getNbrClickMax() == 0){
                    new ActionBarBuilder("§6Vous avez abusé des bonnes chose !!!").sendTo(player);
                    player.sendMessage("§7Vous avez abusé des bonnes chose !!!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Settings.getTimeConfusion(), 100));
                } else {
                    new ActionBarBuilder("§6§lEn cour de recharge !!!.").sendTo(player);
                }
            }
        } else {
            playerData.setNbrClickVisiblePlayer(0);
            playerData.visiblePlayer(player);
        }
    }*/
}
