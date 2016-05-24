package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.utils.Lang;
import me.vorps.fortycube.utils.LangSetting;
import me.vorps.hub.Data;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.enchantments.Enchantment;

import java.sql.ResultSet;
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

    public Menu(ResultSet result) throws SqlException{
        label = new HashMap<>();
        for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
            label.put(langSetting.getName(), Lang.getMessage(Database.FORTYCUBE.getDatabase().getString(result, 2), langSetting.getName()));
        }
        size = Database.FORTYCUBE.getDatabase().getInt(result, 3);
        ids = ids(Database.FORTYCUBE.getDatabase().getString(result, 4));
        model = model(Database.FORTYCUBE.getDatabase().getString(result, 5));
        start = Database.FORTYCUBE.getDatabase().getInt(result, 6);
        lineSize = Database.FORTYCUBE.getDatabase().getInt(result, 7);
        exclude = exclude(Database.FORTYCUBE.getDatabase().getString(result, 8));
        listMenu.put(Database.FORTYCUBE.getDatabase().getString(result, 1), this);
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
