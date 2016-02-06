package sirmc.vorps.thread;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.ProductsPlayers;
import sirmc.vorps.utils.ActionBar;
import sirmc.vorps.utils.RemovePlayerProduit;
import sirmc.vorps.Hub;
import sirmc.vorps.Settings;

public class ThreadHub extends Thread {
	private	int i;
	private int index = 0;
	private	long time;
	
	public void run(){
		while(!Hub.instance.isInterrupt()){
			if(i > Settings.getTimeMessage()){
				i = 0;
				index++;
				if(index == Settings.getMessageServer().size()){
					index = 0;
				}
				Bukkit.getOnlinePlayers().forEach(p -> ActionBar.sendActionBar(Settings.getMessageServer().get(index), p));
			}
				time = System.currentTimeMillis();
				for(Player player : Bukkit.getOnlinePlayers()){
					if(Hub.instance.getPlayerHub().containsKey(player.getName())){
						PlayerHub playerHub = Hub.instance.getPlayerHub().get(player.getName());
						for(ProductsPlayers products : playerHub.getProducts()){
							if(products.getTime() > 0){
								if(time >= Hub.instance.getListProducts().get(products.getProduct()).getTime() + products.getDate()){
									RemovePlayerProduit.BoutiqueRevovePlayerProduit(playerHub, Hub.instance.getListProducts().get(products.getProduct()) ,products.getProduct());
									playerHub.getProductsPlayerFunction();
									break;
								}
							}
						}
				}
			}
			i += Settings.getSpeedThread();
			try {
				Thread.sleep(Settings.getSpeedThread());
			} catch (InterruptedException e) {}
		}
		interrupt();
	}
}
