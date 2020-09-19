package me.vorps.hub.Object;

import lombok.Getter;
import net.vorps.api.lang.Lang;
import net.vorps.api.lang.LangSetting;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.objects.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 20/05/2016 at 00:15.
 */
public class JumpDifficulty implements Comparable<JumpDifficulty> {

    private static final HashMap<String, JumpDifficulty> jumpDifficulty = new HashMap<>();

    private @Getter final String name;
    private @Getter final HashMap<String, ItemBuilder> icon;
    private final int level;
    private final HashMap<String, String> label;
    private @Getter final Menu menu;

    public int compareTo(JumpDifficulty jumpDifficulty){
        return this.level >= jumpDifficulty.level ? 1 : -1;
    }

    public JumpDifficulty(ResultSet result) throws SQLException {
        this.icon = new HashMap<>();
        this.label = new HashMap<>();
        this.name = result.getString(1);
        for(String langSetting : LangSetting.getListLangSetting()){
            this.label.put(langSetting, Lang.getMessage(result.getString(2), langSetting));
            this.icon.put(langSetting, Item.getItem(result.getString(3), langSetting));
        }
        this.level = result.getInt(4);
        this.menu = Menu.getMenu(result.getString(5));
        JumpDifficulty.jumpDifficulty.put(this.name, this);
    }


    public String[] getLore(String jump){
        /*ArrayList<PlayerJumpRecord> list = PlayerJumpRecord.getPlayerDifficulty(jump, name);
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
        return listPlayer;*/
        return null;
    }

    public static JumpDifficulty getJumpDifficultyLabel(String label, String lang){
        for(JumpDifficulty jumpDifficulty : JumpDifficulty.jumpDifficulty.values()){
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
        JumpDifficulty.jumpDifficulty.clear();
    }

}
