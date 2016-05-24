package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.utils.LangSetting;
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
    private @Getter HashMap<String, Item> icon;
    private @Getter Database database;
    private @Getter ArrayList<Item> listMap;
    private @Getter String type;

    public Game(ResultSet result) throws SqlException{
        icon = new HashMap<>();
        gameMenu = Menu.getMenu(Database.FORTYCUBE.getDatabase().getString(result, 2));
        shopMenu =  Menu.getMenu(Database.FORTYCUBE.getDatabase().getString(result, 3));
        signLoc = me.vorps.hub.Object.Location.getLocation(Database.FORTYCUBE.getDatabase().getString(result, 4));
        for(LangSetting langSetting : LangSetting.getListLangSetting().values()){
            icon.put(langSetting.getName(), me.vorps.hub.Object.Item.getItem(Database.FORTYCUBE.getDatabase().getString(result, 5), langSetting.getName()));
        }
        database = Database.valueOf(Database.FORTYCUBE.getDatabase().getString(result, 6));
        type = Database.FORTYCUBE.getDatabase().getString(result, 7);
        getMap();
        gameList.put(Database.FORTYCUBE.getDatabase().getString(result, 1), this);
    }

    public static Game getGame(String game){
        return getGameList().get(game);
    }

    private void getMap(){
        listMap = new ArrayList<>();
        try {
            ResultSet results = database.getDatabase().getData("SELECT * FROM map ");
            while(results.next()){
                listMap.add(new Item(358).withName("§6"+database.getDatabase().getString(results, 1)));
            }
        } catch (SQLException e){
            //
        } catch (SqlException e){
            e.printStackTrace();
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
