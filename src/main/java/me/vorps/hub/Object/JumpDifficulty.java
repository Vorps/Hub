package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.hub.data.DataHub;
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
public class JumpDifficulty {

    private static HashMap<String, JumpDifficulty> jumpDifficulty;

    private @Getter final String name;
    private final String icon;
    private final String label;
    private @Getter final Menu menu;

    public JumpDifficulty(ResultSet result) throws SQLException {
        this.name = result.getString("jd_name");
        this.label = result.getString("jd_label");
        this.icon = result.getString("jd_icon");
        this.menu = Menu.getMenu(result.getString("jd_menu"));
        JumpDifficulty.jumpDifficulty.put(this.name, this);
    }

    public ItemBuilder getIcon(String lang){
        return Item.getItem(this.icon, lang);
    }

    static {
        JumpDifficulty.jumpDifficulty = new HashMap<>();
        DataHub.loadJumpDifficulty();
    }


    @Deprecated
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

    /*public static JumpDifficulty getJumpDifficultyLabel(String label, String lang){
        for(JumpDifficulty jumpDifficulty : JumpDifficulty.jumpDifficulty.values()){
            if(jumpDifficulty.icon.get(lang).get().getItemMeta().getDisplayName().equals(label)){
                return jumpDifficulty;
            }
        }
        return null;
    }*/

    public static JumpDifficulty getJumpDifficulty(String name){
        return JumpDifficulty.jumpDifficulty.get(name);
    }

    public String toString(String lang){
        return Lang.getMessage(this.label, lang);
    }

    public static void clear(){
        JumpDifficulty.jumpDifficulty.clear();
    }

}
