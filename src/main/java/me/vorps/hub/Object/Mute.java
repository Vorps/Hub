package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.hub.Hub;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 05/03/2016 at 05:47.
 */
public class Mute {
    private static HashMap<String, Mute> listMute = new HashMap<>();

    private String namePlayer;
    private @Getter String author;
    private @Getter long time;
    private @Getter String reason;

    public Mute(ResultSet result) throws SqlException {
        namePlayer = Database.FORTYCUBE.getDatabase().getString(result, 1);
        author = Database.FORTYCUBE.getDatabase().getString(result, 2);
        time = Database.FORTYCUBE.getDatabase().getLong(result, 3);
        reason = Database.FORTYCUBE.getDatabase().getString(result, 4);
        listMute.put(namePlayer, this);
    }

    public static boolean isMute(String namePlayer){
        return listMute.containsKey(namePlayer);
    }

    public static Mute getMute(String namePlayer){
        return listMute.get(namePlayer);
    }

    public String toString(){
        if(time == 0){
            if(reason.length() > 0){
                return "§cVous avez été mute pour raison : §e" + reason + "§c définitivement§c.";
            } else {
                return "§cVous avez été mute définitivement§c.";
            }
        } else {
            if(time > System.currentTimeMillis()) {
                if (reason.length() > 0) {
                    return "§cVous avez été mute pour raison : §a" +reason + "§c jusqu'au : §a" + Hub.getInstance().getDATE_FORMAT().format(time) + "§c.";
                } else {
                    return "§cVous avez été mute jusqu'au : §a" + Hub.getInstance().getDATE_FORMAT().format(time) + "§c.";
                }
            } else {
                try {
                    Database.FORTYCUBE.getDatabase().sendDatabase("DELETE FROM mute WHERE mute_name = '" +namePlayer+ "'");
                } catch (SqlException e){
                    e.printStackTrace();
                }
                listMute.remove(namePlayer);
                return "Vous êtes unmute";
            }
        }
    }
}
