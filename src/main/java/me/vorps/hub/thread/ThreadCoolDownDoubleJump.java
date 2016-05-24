package me.vorps.hub.thread;

import me.vorps.fortycube.cooldown.CoolDowns;
import me.vorps.hub.PlayerData;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 12/05/2016 at 02:31.
 */
public class ThreadCoolDownDoubleJump extends Thread {

    private Player player;

    public ThreadCoolDownDoubleJump(Player player){
        this.player = player;
        run();
    }

    @Override
    public void run(){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        while(CoolDowns.hasCoolDown(player.getName(), "double_jump")){
            CoolDowns coolDowns = CoolDowns.getCoolDown(player.getName(), "double_jump");
            if(coolDowns.getSecondsLeft() > 0 && playerData.isDoubleJumps()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                coolDowns.removeCoolDown();
            }
        }
        if(playerData.isDoubleJumps()){
            player.setAllowFlight(true);
        }
        interrupt();
    }
}
