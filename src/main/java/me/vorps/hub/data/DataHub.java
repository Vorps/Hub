package me.vorps.hub.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.vorps.api.data.Data;
import net.vorps.api.data.DataReload;
import net.vorps.api.objects.Item;
import me.vorps.hub.Object.*;
import net.vorps.api.databases.Database;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class DataHub extends Data {

    @DataReload
    public static void loadBonus(){
        Bonus.clear();
        try {
            ResultSet results = Database.HUB.getDatabase().getData("bonus");
            while (results.next()) {
                new Bonus(results);
            }
        } catch (SQLException e){
            //
        }
    }

    @DataReload
    public static void loadGadget(){
        Gadgets.clear();
        try {
            ResultSet results = Database.HUB.getDatabase().getData("gadget");
            while (results.next()) {
                new Gadgets(results);
            }
        } catch (SQLException e){
            //
        }
    }

    @DataReload
    public static void loadJumpDifficulty(){
        JumpDifficulty.clear();
        try {
            ResultSet results = Database.HUB.getDatabase().getData("jump_difficulty");
            while (results.next()) {
                new JumpDifficulty(results);
            }
        } catch (SQLException e){
            //
        }
    }

    @DataReload
    public static void loadItem(){
        Item.clear();
        try {
            ResultSet results = Database.HUB.getDatabase().getData("item");
            while (results.next()) {
                new Item(results);
            }
        } catch (SQLException e){
            //
        }
    }

    /*@DataReload
    public static void loadJumpRecord(){
        PlayerJumpRecord.clear();
        try {
            ResultSet results = Database.HUB.getDatabase().getData("player_jump");
            while (results.next()) {
                new PlayerJumpRecord(results);
            }
        } catch (SQLException e){
            //
        }
    }*/

    @DataReload
    public static void loadMenu(){
        Menu.clear();
        try {
            ResultSet results = Database.HUB.getDatabase().getData("menu");
            while (results.next()) {
                new Menu(results);
            }
        } catch (SQLException e){
            //
        }
    }

    @DataReload
    public static void loadGames(){
        Game.clear();
        try {
            ResultSet results = Database.HUB.getDatabase().getData("game");
            while(results.next()){
                new Game(results);
            }
        } catch (SQLException e) {
            //
        }
    }


    @DataReload
    public static void loadListJumps(){
        Jumps.clear();
		try {
			ResultSet results = Database.HUB.getDatabase().getData("jump");
			while(results.next()){
				new Jumps(results, Database.HUB);
			}
        } catch (SQLException e){
            //
        }
	}

    @DataReload
    public static void loadListProduct(){
        Products.clear();
		try {
			ResultSet results = Database.HUB.getDatabase().getData("product");
	        while(results.next()){
                new Products(results);
	        }
        } catch (SQLException e){
            //
        }
	}
}
