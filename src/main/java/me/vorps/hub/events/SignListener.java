package me.vorps.hub.events;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Project Hub Created by Vorps on 02/05/2016 at 04:18.
 */
public class SignListener implements Listener {

    @EventHandler
    public void onInterractItem(PlayerInteractEvent e){
        Action action = e.getAction();
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        if (action == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getState() instanceof Sign){
                Sign sign = (Sign) block.getState();
                //Dispatcher.connectServer(sign.getLine(0).substring(2), player);
            }
        }
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e){
        /*String[] line = e.getLines();
        server : for(ArrayList<String> serverList : Dispatcher.getListServer().values()){
            for(String name : serverList){
                if(line[0].substring(1, line[0].length()-1).equals(name)) {
                    String map = "";
                    int nbMaxPlayer = 0;
                    if (name.substring(0, name.length() - 3).equals("RV")) {
                        try {
                            ResultSet results = Database.BUNGEE.getDatabase().getData("server", "sv_name = '" + name + "' ");
                            if (results.next()) {
                                nbMaxPlayer = DatabaseManager.getInt(results, 3);
                                ResultSet result = Database.BUNGEE.getDatabase().getData("parameter", "p_parameter = '" + DatabaseManager.getInt(results, 2) + "' ");
                                if (result.next()) {
                                    map = DatabaseManager.getString(result, 2);
                                }
                            }
                        } catch (SQLException err) {
                            //
                        } catch (SqlException err) {
                            err.printStackTrace();
                        }
                    }
                    e.setLine(0, "§6" + name);
                    e.setLine(1, "§e" + map);
                    e.setLine(2, "§a0§7/§a" + nbMaxPlayer);
                    e.setLine(3, "§b" + GameState.WAITING.getLabel());
                    break server;
                }
            }
        }*/
    }
}
