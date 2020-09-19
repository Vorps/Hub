package me.vorps.hub.Object;

import net.vorps.api.databases.Database;
import net.vorps.api.lang.LangSetting;
import me.vorps.hub.PlayerData;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 09/05/2016 at 17:42.
 */
public class Stats {

    public String[] getNomsColonnes(ResultSet result) throws SQLException{
        ResultSetMetaData metaData = result.getMetaData();
        String[] name = new String[metaData.getColumnCount()];
        for(int i = 0; i < name.length; i++){
            String nameColumn = metaData.getColumnName(i+1);
            name[i] = nameColumn;
        }
        return name;
    }

    private HashMap<String, String[]> label = null;

    private String[] lore;

    public Stats(UUID uuid, Database database){
        /*PlayerData playerData = PlayerData.getPlayerData(namePlayer);
        ArrayList<String> lore = new ArrayList<>();
        try {
            ResultSet result = database.getDatabase().getData("stats", "s_player = '"+namePlayer+"' ");
            String[] column = getNomsColonnes(result);
            label = new HashMap<>();
            ResultSet resultSetting = database.getDatabase().getData("stats_setting");
            resultSetting.next();
            for(String langSetting : LangSetting.getListLangSetting()){
                String[] statsLabel = new String[column.length-1];
                for(int i = 0; i < statsLabel.length; i++){
                    statsLabel[i] = resultSetting.getString("s"+column[i+1]+"_"+LangSetting.getLangSetting(langSetting).getColumnId());
                }
                label.put(langSetting, statsLabel);
            }
            if(result.next()){
                int i = 0;
                for(String stats : label.get(playerData.getLang())){
                    if(column[++i].equals("s_time")){
                        Date date = new Date(result.getInt(column[i])*1000);
                        date.setHours(date.getHours()-1);
                        lore.add("§7"+stats+"§f : §a"+new SimpleDateFormat("HH:mm:ss").format(date));
                    } else {
                        lore.add("§7"+stats+"§f : §a"+result.getInt(column[i]));
                    }
                }
            } else {
                int i = 0;
                for(String stats : label.get(playerData.getLang())){
                    if(column[++i].equals("s_time")){
                        lore.add("§7"+stats+"§f : §a00:00:00");
                    } else {
                        lore.add("§7"+stats+"§f : §a0");
                    }
                }
            }
        } catch (SQLException e){
            //
        }
        this.lore = lore.toArray(new String[lore.size()]);*/
    }

    public String[] getStats(){
        return lore;
    }
}
