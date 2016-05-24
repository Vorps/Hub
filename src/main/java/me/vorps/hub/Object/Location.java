package me.vorps.hub.Object;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 17/05/2016 at 20:20.
 */
public class Location{

    private static HashMap<String, org.bukkit.Location> listLocation = new HashMap<>();

    public Location(ResultSet result) throws SqlException{
        listLocation.put(Database.FORTYCUBE.getDatabase().getString(result, 1), new org.bukkit.Location(Bukkit.getWorlds().get(0), Database.FORTYCUBE.getDatabase().getDouble(result, 2), Database.FORTYCUBE.getDatabase().getDouble(result, 3), Database.FORTYCUBE.getDatabase().getDouble(result, 4), Database.FORTYCUBE.getDatabase().getFloat(result, 5), Database.FORTYCUBE.getDatabase().getFloat(result, 6)));
    }

    public static void clear(){
        listLocation.clear();
    }

    public static org.bukkit.Location getLocation(String name){
        return listLocation.get(name);
    }
}
