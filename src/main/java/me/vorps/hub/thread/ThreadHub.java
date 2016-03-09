package me.vorps.hub.thread;

import me.vorps.fortycube.utils.ActionBar;
import me.vorps.hub.Hub;
import me.vorps.hub.Object.Products;
import me.vorps.hub.Object.ProductsPlayers;
import me.vorps.hub.PlayerData;
import me.vorps.hub.utils.RemovePlayerProduit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.vorps.hub.Settings;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class ThreadHub extends Thread {
	private	int i;
	private int index = 0;

	public void run(){
        long time;
		while(!Hub.getInstance().isInterrupt()){
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
					if(PlayerData.isPlayerDataExits(player.getName())){
						PlayerData playerData = PlayerData.getPlayerData(player.getName());
						for(ProductsPlayers products : playerData.getProducts()){
							if(products.getTime() > 0){
								if(time >= Products.getProduct(products.getProduct()).getTime() + products.getDate()){
									RemovePlayerProduit.boutiqueRemovePlayerProduit(player, Products.getProduct(products.getProduct()) ,products.getProduct());
									playerData.getProductsPlayerFunction();
									break;
								}
							}
						}
				}
			}
			i += Settings.getSpeedThread();
			try {
				Thread.sleep(Settings.getSpeedThread());
			} catch (InterruptedException e) {
                //
            }
		}
		interrupt();
	}
}
