package sirmc.vorps.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.Settings;

public class Cooldowns {

	private static HashMap<String, Long> cooldownsVisible = new HashMap<String, Long>();
	private static HashMap<String, Long> cooldownsDoubleJumps = new HashMap<String, Long>();
	
	public static void addCooldownsVisible(PlayerHub playerHub){
		cooldownsVisible.put(playerHub.getPlayerName(), System.currentTimeMillis());
	}
	
	public static boolean hasCooldownsVisible(PlayerHub playerHub){
		return cooldownsVisible.containsKey(playerHub.getPlayerName()) ? true : false;
	}
	
	public static void removeCooldownsVisible(PlayerHub playerHub){
		cooldownsVisible.remove(playerHub.getPlayerName());
	}
	
	public static Long getSecondsLeftVisible(PlayerHub playerHub){
		long secondsLeft = ((cooldownsVisible.get(playerHub.getPlayerName()) / 1000) + Settings.getCooldownVisible()) - System.currentTimeMillis() / 1000;
		return secondsLeft;
	}
	
	public static void addCooldownsDoubleJumps(PlayerHub playerHub){
		cooldownsVisible.put(playerHub.getPlayerName(), System.currentTimeMillis());
	}
	
	public static boolean hasCooldownsDoubleJumps(PlayerHub playerHub){
		return cooldownsVisible.containsKey(playerHub.getPlayerName()) ? true : false;
	}
	
	public static void removeCooldownsDoubleJumps(PlayerHub playerHub){
		cooldownsVisible.remove(playerHub.getPlayerName());
	}
	
	public static Long getSecondsLeftDoubleJumps(PlayerHub playerHub){
		long secondsLeft = ((cooldownsVisible.get(playerHub.getPlayerName()) / 1000) + Settings.getCooldownDoubleJump()) - System.currentTimeMillis() / 1000;
		return secondsLeft;
	}
	
	public static void VisibilityAction(PlayerHub playerHub){
		if(Cooldowns.hasCooldownsVisible(playerHub)){
			if(Cooldowns.getSecondsLeftVisible(playerHub) <= 0){
				Cooldowns.removeCooldownsVisible(playerHub);
				PlayerHub.VisiblePlayer(playerHub);
			} else {
				playerHub.setNbrClickVisiblePlayer(playerHub.getNbrClickVisiblePlayer()+1);
				if(playerHub.getNbrClickVisiblePlayer()%Settings.getNbrClickMax() == 0){
					ActionBar.sendActionBar("�6Vous avez abus� des bonnes chose !!!", Bukkit.getPlayer(playerHub.getPlayerName()));
					Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("�7Vous avez abus� des bonnes chose !!!");
					Bukkit.getPlayer(playerHub.getPlayerName()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Settings.getTimeConfusion(), 100));
				} else {
						ActionBar.sendActionBar("�6�lEn cour de recharge !!!.", Bukkit.getPlayer(playerHub.getPlayerName()));
				}
			}
		} else {
			playerHub.setNbrClickVisiblePlayer(0);
			PlayerHub.VisiblePlayer(playerHub);
		}
	}
	
	public static boolean DoubleJumps(PlayerHub playerHub){
		if(Cooldowns.hasCooldownsDoubleJumps(playerHub)){
			if(Cooldowns.getSecondsLeftDoubleJumps(playerHub) <= 0){
				Cooldowns.removeCooldownsDoubleJumps(playerHub);
				Bukkit.getPlayer(playerHub.getPlayerName()).setAllowFlight(true);
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
}
