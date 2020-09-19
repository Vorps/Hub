package me.vorps.hub.thread;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class ThreadHub extends Thread {
	private	int i = 0;
	private int index = 0;

	public void run(){
        long time;
		/*while(!Hub.getInstance().isInterrupt()){
			if(i > Settings.getTimeMessage()){
				i = 0;
				index++;
				if(index == Settings.getMessageServer().size()){
					index = 0;
				}
				Bukkit.getOnlinePlayers().forEach(p -> new ActionBarBuilder(Settings.getMessageServer().get(index)).sendTo(p));

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
			}
			i += Settings.getSpeedThread();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
                //
            }
		}*/
		//interrupt();
	}
}
