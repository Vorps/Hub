package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.hub.data.DataHub;
import me.vorps.hub.data.SettingsHub;
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

    private final String label;
    private @Getter final int size;
    private @Getter final byte[] ids;
    private @Getter final int[][] model;
    private @Getter final int start;
    private @Getter final int lineSize;
    private @Getter final int[] exclude;

    public String getLabel(String lang){
        return Lang.getMessage(this.label, lang);
    }
    public Menu(ResultSet result) throws SQLException {
        this.label = result.getString("menu_label");
        this.size = result.getInt("menu_size");
        this.ids = ids(result.getString("menu_ids"));
        this.model = model(result.getString("menu_model"));
        this.start = result.getInt("menu_start");
        this.lineSize = result.getInt("menu_line_size");
        this.exclude = exclude(result.getString("menu_exclude"));
        Menu.listMenu.put(result.getString("menu_name"), this);
    }

    static {
        Menu.listMenu = new HashMap<>();
        DataHub.loadMenu();
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
        return Menu.listMenu.get(name);
    }

    public static void clear(){
        Menu.listMenu.clear();
    }
}
