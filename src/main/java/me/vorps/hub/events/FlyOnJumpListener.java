package me.vorps.hub.events;

import me.vorps.fortycube.utils.ParticleEffect;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import me.vorps.hub.thread.ThreadCoolDownDoubleJump;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class FlyOnJumpListener implements Listener {

    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(playerData.isDoubleJumps()) {
            if(playerData.getNbrDoubleJumps() < Settings.getNbrDoubleJumpsMax()){
                playerData.setNbrDoubleJumps(playerData.getNbrDoubleJumps()+1);
                player.setVelocity(player.getVelocity().add(player.getLocation().getDirection().multiply(2).setY(1.1)));
                ParticleEffect.FIREWORKS_SPARK.display(1, 1, 1, 0, 50, player.getLocation(), 1);
                player.playSound(player.getLocation(), Sound.FIZZ, 1, 1);

            } else {
                playerData.setNbrDoubleJumps(0);
                player.setAllowFlight(false);
                player.setFlying(false);
                new ThreadCoolDownDoubleJump(player).start();
            }
            e.setCancelled(true);
        }
    }

}
