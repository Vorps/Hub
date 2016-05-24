package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.dispatcher.Dispatcher;
import me.vorps.hub.menu.MenuHub;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Project FortycubeBungee Created by Vorps on 03/05/2016 at 00:23.
 */
public class Server {
    private static int nbUpdate;
    private static @Getter HashMap<String, Server> server = new HashMap<>();
    private static @Getter HashMap<String, Server> serverOnline = new HashMap<>();
    private @Getter String name;
    private @Getter int nbMaxPlayer;
    private @Getter String lang;
    private @Getter boolean online;
    private @Getter String[] players;

    public Server(ResultSet result) throws SqlException {
        nbUpdate = 0;
        name = Database.FORTYCUBE.getDatabase().getString(result, 1);
        nbMaxPlayer = Database.FORTYCUBE.getDatabase().getInt(result, 2);
        lang = Database.FORTYCUBE.getDatabase().getString(result, 3);
        update();
        server.put(name, this);
    }

    public void update(){
        try {
            online = Dispatcher.deSerialisationHub(Dispatcher.getPath()+ Dispatcher.DS+"hub"+Dispatcher.DS+name).getOnline();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(online){
            serverOnline.put(name, this);
        }
    }

    public void updatePlayers(String[] players, Player player){
        try {
            this.players = players;
        } catch (Exception e){
            e.printStackTrace();
        }
        nbUpdate++;
        if(nbUpdate == serverOnline.size()){
            MenuHub.createMenu(player);
            nbUpdate = 0;
        }
    }

    public static void clear(){
        server.clear();
    }
}
