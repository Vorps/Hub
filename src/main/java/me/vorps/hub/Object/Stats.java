package me.vorps.hub.Object;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.utils.LangSetting;
import me.vorps.hub.PlayerData;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

    public Stats(String namePlayer, Database database){
        PlayerData playerData = PlayerData.getPlayerData(namePlayer);
        ArrayList<String> lore = new ArrayList<>();
        try {
            ResultSet result = database.getDatabase().getData("SELECT * FROM stats WHERE s_player = '"+namePlayer+"' ");
            String[] column = getNomsColonnes(result);
            label = new HashMap<>();
            ResultSet resultSetting = database.getDatabase().getData("SELECT * FROM stats_setting");
            resultSetting.next();
            for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
                String[] statsLabel = new String[column.length-1];
                for(int i = 0; i < statsLabel.length; i++){
                    statsLabel[i] = resultSetting.getString("s"+column[i+1]+"_"+langSetting.getColumnId());
                }
                label.put(langSetting.getName(), statsLabel);
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
        } catch (SqlException e){
            e.printStackTrace();
        }
        this.lore = lore.toArray(new String[lore.size()]);
    }

    public String[] getStats(){
        return lore;
    }
}
