package me.vorps.hub.Object;

import lombok.Getter;
import net.vorps.api.databases.Database;
import net.vorps.api.lang.LangSetting;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.objects.Item;
import org.bukkit.Location;
import org.bukkit.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 17/05/2016 at 19:46.
 */
public class Game {

    private static @Getter HashMap<String, Game> gameList = new HashMap<>();

    private @Getter Menu gameMenu;
    private @Getter Menu shopMenu;
    private @Getter Location signLoc;
    private @Getter HashMap<String, ItemBuilder> icon;
    private @Getter Database database;
    private @Getter ArrayList<ItemBuilder> listMap;
    private @Getter String type;

    public Game(ResultSet result) throws SQLException{
        icon = new HashMap<>();
        gameMenu = Menu.getMenu(result.getString(2));
        shopMenu =  Menu.getMenu(result.getString(3));
        signLoc = net.vorps.api.objects.Location.getLocation(result.getString(4));
        for(String langSetting : LangSetting.getListLangSetting()){
            icon.put(langSetting, Item.getItem(result.getString(5), langSetting));
        }
        database = Database.valueOf(result.getString(6));
        type = result.getString(7);
        getMap();
        gameList.put(result.getString( 1), this);
    }

    public static Game getGame(String game){
        return getGameList().get(game);
    }

    private void getMap(){
        listMap = new ArrayList<>();
        try {
            ResultSet results = database.getDatabase().getData("map");
            while(results.next()){
                listMap.add(new ItemBuilder(Material.MAP).withName("§6"+results.getString(1)));
            }
        } catch (SQLException e){
            //
        }
    }

    public static Game getGame(int id, String lang){
        for(Game game : gameList.values()){
            if(game.getIcon().get(lang).get().getType().getId() == id){
                return game;
            }
        }
        return null;
    }

    public static void clear(){
        gameList.clear();
    }

}
