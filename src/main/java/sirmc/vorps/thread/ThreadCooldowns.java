package sirmc.vorps.thread;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.menu.Navigator;
import sirmc.vorps.utils.Cooldowns;
import sirmc.vorps.Hub;
import sirmc.vorps.utils.Item;

public class ThreadCooldowns extends Thread{
	
	private PlayerHub playerHub;
	private static List<String> des = new ArrayList<String>();

	public ThreadCooldowns(PlayerHub playerHub){
		this.playerHub = playerHub;
	} 
	
	public void run(){
		des.clear();
		des.add("§aPermet de masquer");
		des.add("§7 - Les joueurs");
		while(Cooldowns.getSecondsLeftVisible(playerHub) > 0){
			if(Hub.instance.getPlayerHub().containsKey(playerHub.getPlayerName())){
				if(playerHub.isVisiblePlayer()){
					if(Cooldowns.getSecondsLeftVisible(playerHub) >= 2){
						Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs (§aActivé§6) §7("+Cooldowns.getSecondsLeftVisible(playerHub)+" Secondes)§6.").withAmount((int) -Cooldowns.getSecondsLeftVisible(playerHub)).withLore(des.toArray(new String[des.size()])).get());
					} else {
						Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs (§aActivé§6) §7("+Cooldowns.getSecondsLeftVisible(playerHub)+" Seconde)§6.").withAmount((int) -Cooldowns.getSecondsLeftVisible(playerHub)).withLore(des.toArray(new String[des.size()])).get());
					}
				} else {
					if(Cooldowns.getSecondsLeftVisible(playerHub) >= 2){
						Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs (§4Désactivé§6) §7("+Cooldowns.getSecondsLeftVisible(playerHub)+" Secondes)§6.").withAmount((int) -Cooldowns.getSecondsLeftVisible(playerHub)).withLore(des.toArray(new String[des.size()])).get());
					} else {
						Bukkit.getPlayer(playerHub.getPlayerName()).getInventory().setItem(7, new Item(Material.BLAZE_POWDER).withName("§6Joueurs (§4Désactivé§6) §7("+Cooldowns.getSecondsLeftVisible(playerHub)+" Seconde)§6.").withAmount((int) -Cooldowns.getSecondsLeftVisible(playerHub)).withLore(des.toArray(new String[des.size()])).get());
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			des.clear();
			Navigator.HubNavigator(playerHub);
			Hub.instance.getCooldownsThread().remove(playerHub.getPlayerName());
		}
		interrupt();
	}
}
