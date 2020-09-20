package me.vorps.hub.thread;

import me.vorps.hub.Hub;
import me.vorps.hub.data.SettingsHub;
import net.vorps.api.data.Settings;
import net.vorps.api.nms.ActionBarBuilder;
import org.bukkit.Bukkit;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class ThreadHub extends Thread {
	private int index = 0;

	public ThreadHub(){
		this.index = 0;
	}

	public void run(){
		while(!Hub.getInstance().isInterrupt()){
			if(index == SettingsHub.getMessage_server().size()) index = 0;
			Bukkit.getOnlinePlayers().forEach(p -> new ActionBarBuilder(SettingsHub.getMessage_server().get(this.index++)).sendTo(p));
                /*for(Player player : Bukkit.getOnlinePlayers()){
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
                }*/

			try {
				Thread.sleep(SettingsHub.getTime_thread_message());
			} catch (InterruptedException e) {
                //
            }
		}
		interrupt();
	}
}
