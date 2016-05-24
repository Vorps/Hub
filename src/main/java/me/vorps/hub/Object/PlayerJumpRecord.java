package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.menu.Item;
import me.vorps.hub.Data;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project Hub Created by Vorps on 22/05/2016 at 03:16.
 */
public class PlayerJumpRecord{

    private static ArrayList<PlayerJumpRecord> listPlayerJumpRecord = new ArrayList<>();

    public static ArrayList<Item> getListPlayer(String jump, String lang, String jumpDifficulty){
        listPlayerJumpRecord.sort((o1, o2) -> o1.time >= o2.time ? 1 : -1);
        ArrayList<Item> list = new ArrayList<>();
        ArrayList<PlayerJumpRecord> lisPlayer = getPlayerDifficulty(jump, jumpDifficulty);
        for(int i = 0; i < 10 && i < lisPlayer.size(); i++){
            PlayerJumpRecord playerJumpRecord = lisPlayer.get(i);
            Date date = new Date(playerJumpRecord.time*1000);
            SimpleDateFormat simpleDateFormat;
            if(playerJumpRecord.time >= 3600){
                date.setHours(date.getHours()-1);
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            } else {
                simpleDateFormat = new SimpleDateFormat("mm:ss");
            }
            list.add(new Item(playerJumpRecord.namePlayer).withName("§6"+playerJumpRecord.namePlayer).withLore(new String[] {"§bMode §7: "+JumpDifficulty.getJumpDifficulty(playerJumpRecord.difficulty).toString(lang), "§eTemps : §6"+simpleDateFormat.format(date)}).withAmount(i+1));
        }
        return list;
    }

    public static ArrayList<PlayerJumpRecord> getPlayerDifficulty(String jump, String jumpDifficulty){
        ArrayList<PlayerJumpRecord> list = new ArrayList<>();
        int i = 0;
        for(PlayerJumpRecord playerJumpRecord : listPlayerJumpRecord){
            if(i == 10){
                break;
            }
            if(playerJumpRecord.difficulty.equals(jumpDifficulty) && playerJumpRecord.jump.equals(jump)){
                list.add(playerJumpRecord);
                i++;
            }
        }
        return list;
    }

    private @Getter String namePlayer;
    private String jump;
    private String difficulty;
    private @Getter long time;

    public PlayerJumpRecord(ResultSet result) throws SqlException{
        namePlayer = Database.FORTYCUBE.getDatabase().getString(result, 1);
        jump = Database.FORTYCUBE.getDatabase().getString(result, 2);
        difficulty = Database.FORTYCUBE.getDatabase().getString(result, 3);
        time = Database.FORTYCUBE.getDatabase().getLong(result, 4);
        listPlayerJumpRecord.add(this);
    }

    public static void record(String namePlayer, String jump, String difficulty, long time){
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_jump WHERE pj_player = '" + namePlayer + "' && pj_jump = '" + jump + "' && pj_difficulty = '"+difficulty+"'");
            Date date = new Date(time*1000);
            SimpleDateFormat simpleDateFormat;
            if(time >= 3600){
                date.setHours(date.getHours()-1);
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            } else {
                simpleDateFormat = new SimpleDateFormat("mm:ss");
            }
            if(!results.next()){
                Database.FORTYCUBE.getDatabase().sendDatabase("INSERT INTO player_jump VALUES ('"+namePlayer+"', '"+jump+"', '"+difficulty+"', '"+time+"')");
                Bukkit.getPlayer(namePlayer).sendMessage("§6Nouveau record personnel §7"+simpleDateFormat.format(date));
                Data.getJumpRecord();
            } else {
                if(Database.FORTYCUBE.getDatabase().getDouble(results, 4) > time){
                    Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_jump SET pj_time = '"+time+"' WHERE pj_player = '" + namePlayer + "' && pj_jump = '" + jump + "' && pj_difficulty = '"+difficulty+"'");
                    Bukkit.getPlayer(namePlayer).sendMessage("§6Nouveau record personnel §7"+simpleDateFormat.format(date));
                    Data.getJumpRecord();
                }
            }
        } catch (SqlException e){
            e.printStackTrace();
        } catch (SQLException e){
            //
        }
    }

    public static void clear(){
        listPlayerJumpRecord.clear();
    }
}
