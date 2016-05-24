package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.utils.Lang;
import me.vorps.fortycube.utils.LangSetting;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 20/05/2016 at 00:15.
 */
public class JumpDifficulty implements Comparable<JumpDifficulty> {

    private static HashMap<String, JumpDifficulty> listJumpDifficulty = new HashMap<>();

    private @Getter String name;
    private @Getter HashMap<String, me.vorps.fortycube.menu.Item> icon;
    private int level;
    private HashMap<String, String> label;
    private @Getter Menu menu;

    public int compareTo(JumpDifficulty jumpDifficulty){
        return this.level >= jumpDifficulty.level ? 1 : -1;
    }

    public JumpDifficulty(ResultSet result) throws SqlException{
        icon = new HashMap<>();
        label = new HashMap<>();
        name = Database.FORTYCUBE.getDatabase().getString(result, 1);
        for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
            label.put(langSetting.getName(), Lang.getMessage(Database.FORTYCUBE.getDatabase().getString(result, 2), langSetting.getName()));
            icon.put(langSetting.getName(), me.vorps.hub.Object.Item.getItem(Database.FORTYCUBE.getDatabase().getString(result, 3), langSetting.getName()));
        }
        level = Database.FORTYCUBE.getDatabase().getInt(result, 4);
        menu = Menu.getMenu(Database.FORTYCUBE.getDatabase().getString(result, 5));
        listJumpDifficulty.put(name, this);
    }


    public String[] getLore(String jump){
        ArrayList<PlayerJumpRecord> list = PlayerJumpRecord.getPlayerDifficulty(jump, name);
        String[] listPlayer = new String[list.size()];
        int i = 0;
        for(PlayerJumpRecord playerJumpRecord : list){
            Date date = new Date(playerJumpRecord.getTime()*1000);
            SimpleDateFormat simpleDateFormat;
            if(playerJumpRecord.getTime() >= 3600){
                date.setHours(date.getHours()-1);
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            } else {
                simpleDateFormat = new SimpleDateFormat("mm:ss");
            }
            listPlayer[i++] = "§7"+playerJumpRecord.getNamePlayer()+" :§6 "+simpleDateFormat.format(date);
        }
        return listPlayer;
    }

    public static  JumpDifficulty getJumpDifficulty(String name){
        return listJumpDifficulty.get(name);
    }

    public static JumpDifficulty getJumpDifficultyLabel(String label, String lang){
        for(JumpDifficulty jumpDifficulty : listJumpDifficulty.values()){
            if(jumpDifficulty.icon.get(lang).get().getItemMeta().getDisplayName().equals(label)){
                return jumpDifficulty;
            }
        }
        return null;
    }

    public String toString(String lang){
        return label.get(lang);
    }
    public static void clear(){
        listJumpDifficulty.clear();
    }

}
