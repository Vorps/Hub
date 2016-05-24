package me.vorps.hub;

import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import com.avaje.ebean.annotation.Sql;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.utils.Lang;
import me.vorps.fortycube.utils.LangSetting;
import me.vorps.hub.Object.*;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.Object.Permission;
import org.bukkit.Material;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class Data {

    private static ArrayList<String> listPlayer = new ArrayList<>();
    private static HashMap<String, String> messageGradePlayer = new HashMap<>();
    public static final int NB_SERVER = Integer.parseInt(Paths.get(System.getProperty("user.dir")).getFileName().toString().substring(4));
    public static boolean isPlayerExists(String namePlayer){
        return listPlayer.contains(namePlayer);
    }
    public static boolean isMessageGrade(String nameGrade){
        return messageGradePlayer.containsKey(nameGrade);
    }
    public static String getMessageGrade(String nameGrade){
        return messageGradePlayer.get(nameGrade);
    }

	public static void loadVariables(){
        getLang();
        getMessageTitle();
        getServer();
        getLocation();
        getItem();
        getMenu();
        getGames();
		getListProduct();
		getListMoney();
		getListBonus();
        getListPLayer();
		getGrade();
		getMessagePlayer();
        getSettings();
        Settings.initSettings();
        getJumpDifficulty();
        getListJumps();
        getBookHelp();
        getPermissions();
        getJumpRecord();
	}

    private static void getMessageTitle(){
        MessageTitle.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM message_title ");
            while (results.next()) {
                new MessageTitle(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    private static void getServer(){
        Server.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM server ");
            while (results.next()) {
                new Server(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    private static void getJumpDifficulty(){
        JumpDifficulty.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM jump_difficulty ");
            while (results.next()) {
                new JumpDifficulty(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    private static void getItem(){
        Item.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM item ");
            while (results.next()) {
                new Item(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    public static void getJumpRecord(){
        PlayerJumpRecord.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_jump ");
            while (results.next()) {
                new PlayerJumpRecord(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    private static void getLocation(){
        Location.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM location ");
            while (results.next()) {
                new Location(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    private static void getMenu(){
        Menu.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM menu ");
            while (results.next()) {
                new Menu(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    private static void getGames(){
        Game.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM game");
            while(results.next()){
                new Game(results);
            }
        } catch (SQLException e) {
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
    }


    private static void getSettings(){
		try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM setting ");
            while (results.next()) {
                new Settings(results);
            }
        } catch (SQLException e){
            //
		} catch (SqlException e) {
            e.printStackTrace();
        }
	}

	private static void getListPLayer(){
		listPlayer.clear();
		try {
				ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player ");
		        while(results.next()){
		        	listPlayer.add(Database.FORTYCUBE.getDatabase().getString(results, 1));
		        }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}


    private static void getListJumps(){
        Jumps.clear();
		try {
			ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM jump ");
			while(results.next()){
				new Jumps(results);
			}
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}



    private static void getListProduct(){
        Products.clear();
		try {
			ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM product");
	        while(results.next()){
                new Products(results);
	        }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
        Products.trieProduct();
	}


    private static void getListMoney(){
        Money.clear();
		try {
			ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM money ");
			while(results.next()){
			    new Money(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}

    private static void getListBonus(){
        Bonus.clear();
		try {
			ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM bonus ");
			while(results.next()){
				new Bonus(results);
			}
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}

    private static void getMessagePlayer(){
        messageGradePlayer.clear();
		try {
			ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM message_grade ");
			while(results.next()){
                messageGradePlayer.put(Database.FORTYCUBE.getDatabase().getString(results, 3), Database.FORTYCUBE.getDatabase().getString(results, 2));
			}
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}

    private static void getBookHelp(){
        BookHelp.clear();
		try {
			ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM book ");
			while(results.next()){
				new BookHelp(results);
			}
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
        BookHelp.trieBookHelp();
	}

    public static void getGrade(){
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM grade");
            while(results.next()){
                new Grades(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    public static void getPermissions(){
        Permission.clear();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM permission");
            while(results.next()){
                new Permission(results);
            }
        } catch (SQLException e) {
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
    }

    public static void getLang(){
        Lang.clearLang();
        LangSetting.clearLangSetting();
        ResultSet results;
        try {
            results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM lang_setting ");
            while(results.next()) {
                new LangSetting(results);
            }
            results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM lang ");
            while(results.next()) {
                new Lang(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

}
