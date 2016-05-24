package me.vorps.hub.Object;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.utils.Lang;
import me.vorps.fortycube.utils.LangSetting;
import me.vorps.hub.utils.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 17/05/2016 at 20:51.
 */
public class Item {

    private static HashMap<String, Item> listItem = new HashMap<>();
    private HashMap<String, String> label;
    private HashMap<String, String[]> lore;
    private me.vorps.fortycube.menu.Item item;

    public Item(ResultSet result) throws SqlException{
        label = new HashMap<>();
        me.vorps.fortycube.menu.Item item;
        if(Database.FORTYCUBE.getDatabase().getString(result, 5) != null){
            item = new me.vorps.fortycube.menu.Item(Database.FORTYCUBE.getDatabase().getString(result, 5));
        } else if(Database.FORTYCUBE.getDatabase().getString(result, 6) != null){
            item = new me.vorps.fortycube.menu.Item(PotionType.valueOf(Database.FORTYCUBE.getDatabase().getString(result, 6)));
        } else {
            item = new me.vorps.fortycube.menu.Item(Database.FORTYCUBE.getDatabase().getInt(result, 3));
            item.withData((byte) Database.FORTYCUBE.getDatabase().getInt(result, 4));
        }
        String labelTmp = Database.FORTYCUBE.getDatabase().getString(result, 2);
        for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
            label.put(langSetting.getName(), Lang.getMessage(labelTmp, langSetting.getName()));
        }
        String loreTmp = Database.FORTYCUBE.getDatabase().getString(result, 8);
        if(loreTmp != null){
            lore = new HashMap<>();
            for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
                lore.put(langSetting.getName(), lore(Lang.getMessage(loreTmp,  langSetting.getName())));
            }
        }
        item.withAmount(Database.FORTYCUBE.getDatabase().getInt(result, 7));
        enchant(Database.FORTYCUBE.getDatabase().getString(result, 9), item);
        int durability = Database.FORTYCUBE.getDatabase().getInt(result, 10);
        if(durability != 0){
            item.withDurability(durability);
        }
        String color = Database.FORTYCUBE.getDatabase().getString(result, 11);
        if(color != null){
            item.withColor(Color.valueOf(color).getColor());
        }
        item.hideEnchant(Database.FORTYCUBE.getDatabase().getBoolean(result, 12));
        this.item = item;
        listItem.put(Database.FORTYCUBE.getDatabase().getString(result, 1), this);
    }

    private static String[] lore(String lore){
        ArrayList<String> loreTab = new ArrayList<>();
        int y = 0;
        if(lore != null){
            for(int i = 0; i < lore.length(); i++){
                if(lore.charAt(i) == ';'){
                    loreTab.add(lore.substring(y, i));
                    y = i;
                }
            }
            return loreTab.toArray(new String[loreTab.size()]);
        }
        return new String[0];
    }

    private static void enchant(String enchentment, me.vorps.fortycube.menu.Item item){
        int y = 0;
        int[] var = new int[2];
        var[0] = -1;
        var[1] = -1;
        if(enchentment != null){
            for(int i = 0; i < enchentment.length(); i++){
                if(enchentment.charAt(i) == ':'){
                    if(y != 0){
                        y++;
                    }
                    var[0] = Integer.parseInt(enchentment.substring(y, i));
                    y = i;
                }
                if(enchentment.charAt(i) == ';'){
                    var[1] = Integer.parseInt(enchentment.substring(y+1, i));
                    y = i;
                }
                if(var[0] != -1 && var[1] != -1){
                    item.withEnchant(Enchantment.getById(var[0]), var[1]);
                    var[0] = -1;
                    var[1] = -1;
                }
            }
        }
    }

    private me.vorps.fortycube.menu.Item getItem(String lang){
        me.vorps.fortycube.menu.Item item = new me.vorps.fortycube.menu.Item(this.item);
        item.withName(label.get(lang));
        if(lore != null){
            item.withLore(lore.get(lang));
        }
        return item;
    }

    public static me.vorps.fortycube.menu.Item getItem(String name, String lang){
        return listItem.get(name).getItem(lang);
    }

    public static void clear(){
        listItem.clear();
    }
}
