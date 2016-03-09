package me.vorps.hub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import me.vorps.hub.Object.*;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.utils.PermissionsPlayer;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class Data {

    private static ArrayList<String> listPlayer = new ArrayList<>();
    private static HashMap<String, String> messageGradePlayer = new HashMap<>();


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
		getListProduct();
		getListMoney();
		getListBonus();
        getListPLayer();
		getListJumps();
		getSettings();
		getGrade();
		getMessagePlayer();
		getBookHelp();
        getListMute();
        Settings.initSettings();
        getPermissions();
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
        ArrayList<String> productsGrade = new ArrayList<>();
		try {
			ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM product");
	        while(results.next()){
                productsGrade.clear();
	        	ResultSet resultsGrade = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM product_grade WHERE pg_product = '"+Database.FORTYCUBE.getDatabase().getString(results, 1)+"'");
                while (resultsGrade.next()){
                    productsGrade.add(Database.FORTYCUBE.getDatabase().getString(resultsGrade, 2));
                }
                new Products(results, productsGrade);
	        }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}


    private static void getListMoney(){
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
			ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM message ");
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

    public static void getListMute(){
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM mute ");
            while(results.next()){
                new Mute(results);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    public static void getPermissions(){
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM permission");
            while(results.next()){
                new PermissionsPlayer(results);
            }
        } catch (SQLException e) {
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
    }
}
