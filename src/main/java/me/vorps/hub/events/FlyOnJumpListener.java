package me.vorps.hub.events;

import me.vorps.fortycube.cooldown.CoolDowns;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import me.vorps.hub.thread.ThreadCoolDownDoubleJump;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class FlyOnJumpListener implements Listener {

    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(playerData.isDoubleJumps()) {
            if(!CoolDowns.hasCoolDown(player.getName(), "double_jump")){
                if(playerData.getNbrDoubleJumps() < Settings.getNbrDoubleJumpsMax()){
                    playerData.setNbrDoubleJumps(playerData.getNbrDoubleJumps()+1);
                    Vector jump = player.getLocation().getDirection().multiply(2).setY(1.1);
                    player.setVelocity(player.getVelocity().add(jump));
                } else {
                    playerData.setNbrDoubleJumps(0);
                    new CoolDowns(player.getName(), Settings.getCoolDownDoubleJump(), "double_jump");
                    new ThreadCoolDownDoubleJump(player);
                    player.setAllowFlight(false);
                    player.setFlying(false);
                }
            }
            e.setCancelled(true);
        }
    }
}
