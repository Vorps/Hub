package me.vorps.hub.thread;

import lombok.Getter;
import net.vorps.api.cooldowns.CoolDowns;
import me.vorps.hub.PlayerData;
import me.vorps.hub.menu.Navigator;
import net.vorps.api.menu.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class ThreadCoolDownsVisiblePlayer extends Thread{

    private @Getter static HashMap<String, ThreadCoolDownsVisiblePlayer> cooldownsThread = new HashMap<>();

	private Player player;
    private PlayerData playerData;
    private String[] des = {"§aPermet de masquer", "§a -§7 Les joueurs (Sauf Amis/Membre/Staff)"};

	public ThreadCoolDownsVisiblePlayer(Player player){
		this.player = player;
        playerData = PlayerData.getPlayerData(player.getName());
	}
	
	public void run(){
		/*while(CoolDowns.hasCoolDown(player.getName(), "visible_player")){
            CoolDowns coolDowns = CoolDowns.getCoolDown(player.getName(), "visible_player");
            if(coolDowns.getSecondsLeft() > 0){
                if(PlayerData.isPlayerDataExits(player.getName())){
                    if(playerData.isVisiblePlayer()){
                        if(coolDowns.getSecondsLeft() >= 2){
                            player.getInventory().setItem(7, new ItemBuilder(Material.BLAZE_POWDER).withName("§6Joueurs (§aActivé§6) §7("+ coolDowns.getSecondsLeft()+" Secondes)§6.").withAmount((int) -coolDowns.getSecondsLeft()).withLore(des).get());
                        } else {
                            player.getInventory().setItem(7, new ItemBuilder(Material.BLAZE_POWDER).withName("§6Joueurs (§aActivé§6) §7("+ coolDowns.getSecondsLeft()+" Seconde)§6.").withAmount((int) -coolDowns.getSecondsLeft()).withLore(des).get());
                        }
                    } else {
                        if(coolDowns.getSecondsLeft() >= 2){
                            player.getInventory().setItem(7, new ItemBuilder(Material.BLAZE_POWDER).withName("§6Joueurs (§4Désactivé§6) §7("+ coolDowns.getSecondsLeft()+" Secondes)§6.").withAmount((int) -coolDowns.getSecondsLeft()).withLore(des).get());
                        } else {
                            player.getInventory().setItem(7, new ItemBuilder(Material.BLAZE_POWDER).withName("§6Joueurs (§4Désactivé§6) §7("+ coolDowns.getSecondsLeft()+" Seconde)§6.").withAmount((int) -coolDowns.getSecondsLeft()).withLore(des).get());
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                coolDowns.removeCoolDown();
            }
		}
        Navigator.playerVisible(playerData, player);
        interrupt();*/
	}
}
