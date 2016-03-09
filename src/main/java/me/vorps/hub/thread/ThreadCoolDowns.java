package me.vorps.hub.thread;


import lombok.Getter;
import me.vorps.fortycube.cooldown.CoolDowns;
import me.vorps.fortycube.menu.Item;
import me.vorps.hub.PlayerData;
import me.vorps.hub.menu.Navigator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class ThreadCoolDowns extends Thread{

    private @Getter static HashMap<String, ThreadCoolDowns> cooldownsThread = new HashMap<>();

	private Player player;
    private PlayerData playerData;
    String[] des = {"§aPermet de masquer", "§7 - Les joueurs"};

	public ThreadCoolDowns(Player player){
		this.player = player;
        playerData = PlayerData.getPlayerData(player.getName());
	}
	
	public void run(){
        CoolDowns coolDowns = CoolDowns.getCoolDown(player.getName(), "visible_player");
		while(coolDowns.getSecondsLeft() > 0){
			if(PlayerData.isPlayerDataExits(player.getName())){
				if(playerData.isVisiblePlayer()){
					if(coolDowns.getSecondsLeft() >= 2){
						player.getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs (§aActivé§6) §7("+ coolDowns.getSecondsLeft()+" Secondes)§6.").withAmount((int) -coolDowns.getSecondsLeft()).withLore(des).get());
					} else {
						player.getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs (§aActivé§6) §7("+ coolDowns.getSecondsLeft()+" Seconde)§6.").withAmount((int) -coolDowns.getSecondsLeft()).withLore(des).get());
					}
				} else {
					if(coolDowns.getSecondsLeft() >= 2){
						player.getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs (§4Désactivé§6) §7("+ coolDowns.getSecondsLeft()+" Secondes)§6.").withAmount((int) -coolDowns.getSecondsLeft()).withLore(des).get());
					} else {
						player.getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs (§4Désactivé§6) §7("+ coolDowns.getSecondsLeft()+" Seconde)§6.").withAmount((int) -coolDowns.getSecondsLeft()).withLore(des).get());
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
        Navigator.navigator(playerData, player);
        coolDowns.removeCoolDown();
		interrupt();
	}
}
