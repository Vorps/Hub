package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.utils.Data;
import me.vorps.hub.PlayerData;

import java.sql.ResultSet;

/**
 * Project Hub Created by Vorps on 10/05/2016 at 16:14.
 */
public class Mute {

    private @Getter String playerName;
    private @Getter String author;
    private @Getter long time;
    private @Getter String reason;

    public Mute(ResultSet result) throws SqlException{
        playerName = Database.FORTYCUBE.getDatabase().getString(result, 1);
        author = Database.FORTYCUBE.getDatabase().getString(result, 2);
        time = Database.FORTYCUBE.getDatabase().getTimestamp(result, 3).getTime();
        reason = Database.FORTYCUBE.getDatabase().getString(result, 4);
    }

    public String toString(){
        if(time == 0){
            if(reason.length() > 0){
                return "§cété mute pour raison : §e" + reason + "§c définitivement§c.";
            } else {
                return "§cété mute définitivement§c.";
            }
        } else {
            if(time > System.currentTimeMillis()) {
                if (reason.length() > 0) {
                    return "§cété mute pour raison : §a" +reason + "§c jusqu'au : §a" + Data.getDATE_FORMAT().format(time) + "§c.";
                } else {
                    return "§cété mute jusqu'au : §a" + Data.getDATE_FORMAT().format(time) + "§c.";
                }
            } else {
                try {
                    Database.FORTYCUBE.getDatabase().sendDatabase("DELETE FROM mute WHERE mute_name = '" +playerName+ "'");
                } catch (SqlException e){
                    e.printStackTrace();
                }
                PlayerData.getPlayerData(playerName).setMute(null);
                return "Vous êtes unmute";
            }
        }
    }
}
