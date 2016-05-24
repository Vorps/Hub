package me.vorps.hub.events;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.type.GameState;
import me.vorps.hub.dispatcher.Dispatcher;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 02/05/2016 at 03:59.
 */
public class SingEvent implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e){
        String[] line = e.getLines();
        server : for(ArrayList<String> serverList : Dispatcher.getListServer().values()){
            for(String name : serverList){
                if(line[0].substring(1, line[0].length()-1).equals(name)) {
                    String map = "";
                    int nbMaxPlayer = 0;
                    if (name.substring(0, name.length() - 3).equals("RV")) {
                        try {
                            ResultSet results = Database.RUSH_VOLCANO.getDatabase().getData("SELECT * FROM server WHERE sv_name = '" + name + "' ");
                            if (results.next()) {
                                nbMaxPlayer = Database.RUSH_VOLCANO.getDatabase().getInt(results, 3);
                                ResultSet result = Database.RUSH_VOLCANO.getDatabase().getData("SELECT * FROM parameter WHERE p_parameter = '" + Database.RUSH_VOLCANO.getDatabase().getInt(results, 2) + "' ");
                                if (result.next()) {
                                    map = Database.RUSH_VOLCANO.getDatabase().getString(result, 2);
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
        }
    }
}
