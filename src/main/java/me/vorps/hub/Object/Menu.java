package me.vorps.hub.Object;

import lombok.Getter;
import net.vorps.api.lang.Lang;
import net.vorps.api.lang.LangSetting;
import org.apache.commons.lang.ArrayUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 17/05/2016 at 20:33.
 */
public class Menu {

    private static HashMap<String, Menu> listMenu = new HashMap<>();

    private @Getter HashMap<String, String> label;
    private @Getter int size;
    private @Getter byte[] ids;
    private @Getter int[][] model;
    private @Getter int start;
    private @Getter int lineSize;
    private @Getter int[] exclude;

    public Menu(ResultSet result) throws SQLException {
        label = new HashMap<>();
        for(String langSetting : LangSetting.getListLangSetting()){
            label.put(langSetting, Lang.getMessage(result.getString(2), langSetting));
        }
        size = result.getInt(3);
        ids = ids(result.getString(4));
        model = model(result.getString(5));
        start = result.getInt(6);
        lineSize = result.getInt(7);
        exclude = exclude(result.getString(8));
        listMenu.put(result.getString(1), this);
    }

    private static byte[] ids(String ids){
        if(!ids.isEmpty()){
            ArrayList<Byte> idsArray = new ArrayList<>();
            int y = 0;
            for(int i = 0; i < ids.length(); i++){
                if(ids.charAt(i) == ';'){
                    idsArray.add(Byte.parseByte(ids.substring(y, i)));
                    y = i+1;
                }
            }
            return ArrayUtils.toPrimitive(idsArray.toArray(new Byte[idsArray.size()]));
        }
        return null;
    }

    private static int[] exclude(String exclude){
        if(!exclude.isEmpty()){
            ArrayList<Integer> excludeArray = new ArrayList<>();
            int y = 0;
            for(int i = 0; i < exclude.length(); i++){
                if(exclude.charAt(i) == ';'){
                    excludeArray.add(Integer.parseInt(exclude.substring(y, i)));
                    y = i;
                }
            }
            return ArrayUtils.toPrimitive(excludeArray.toArray(new Integer[excludeArray.size()]));
        }
        return new int[0];
    }

    private static int[][] model(String model){
        ArrayList<Integer[]> modelArray = new ArrayList<>();
        int y = 0;
        int[] var = new int[2];
        var[0] = -1;
        var[1] = -1;
        if(model != null){
            for(int i = 0; i < model.length(); i++){
                if(model.charAt(i) == ':'){
                    if(y != 0){
                        y++;
                    }
                    var[0] = Integer.parseInt(model.substring(y, i));
                    y = i;
                }
                if(model.charAt(i) == ';'){
                    var[1] = Integer.parseInt(model.substring(y+1, i));
                    y = i;
                }
                if(var[0] != -1 && var[1] != -1){
                    modelArray.add(new Integer[] {var[0], var[1]});
                    var[0] = -1;
                    var[1] = -1;
                }
            }
            int[][] list = new int[modelArray.size()][2];
            for(int i = 0; i < list.length; i++){
                list[i][0] = ArrayUtils.toPrimitive(modelArray.get(i))[0];
                list[i][1] = ArrayUtils.toPrimitive(modelArray.get(i))[1];
            }
            return list;
        }
        return null;
    }

    public static Menu getMenu(String name){
        return listMenu.get(name);
    }

    public static void clear(){
        listMenu.clear();
    }
}
