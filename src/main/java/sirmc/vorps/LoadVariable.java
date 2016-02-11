package sirmc.vorps;

import java.sql.ResultSet;
import java.util.ArrayList;

import sirmc.vorps.Object.*;

public class LoadVariable {
	
	public static void LoadVariables(Hub plugin){
		getListCommand();
		getListProduit();
		getListMoney();
		getListBonus();
		ListPLayer();
		getListBan();
		getListMute();
		getListJumps();
		getSettings();
		Grades.InitGrade();
		Permissions.InitPermissions();
		Commands.LoadCommand();
		Settings.initSettings();
		getMessagePlayer();
		getBookHelp();
	}
	
	public static void getSettings(){
		try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Settings ");
	        while(results.next()){
	        	Hub.instance.getSettings().put(results.getString(1), new Settings(results));
	        }
		} catch (Exception e) {}
	}
	
	public static void getListCommand(){
		Hub.instance.getListCommands().clear();
		try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Commands ");
	        while(results.next()){
                if(!results.getString(1).equals("*")){
                    Hub.instance.getListCommands().put(results.getString(1), new Commands(results));
                }
	        }
		} catch (Exception e) {}
	}
	
	
	
	public static void getListMute(){
		Hub.instance.getListMute().clear();
		try {
			ResultSet resuls = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Mute ");
			while(resuls.next()){
				Hub.instance.getListMute().add(resuls.getString(1));
			}
		} catch (Exception e) {}
	}
	
	
	
	public static void getListBan(){
		Hub.instance.getListBan().clear();
		try {
			ResultSet resuls = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Ban ");
			while(resuls.next()) {
				Hub.instance.getListBan().add(resuls.getString(1));
			}
		} catch (Exception e) {}
	}
	
	
	
	public static void ListPLayer(){
		Hub.instance.getListPlayer().clear();
		try {
				ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Players ");
		        while(results.next()){
		        	Hub.instance.getListPlayer().add(results.getString(1));
		        }
			} catch (Exception e) {}
	}
	
	
	
	
	public static void getListJumps(){
		Hub.instance.getListJumps().clear();
		try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Jumps ");
			while(results.next()){
				Hub.instance.getListJumps().add(new Jumps(results));
			}
		} catch (Exception e) {}
	}
	
	
	
	public static void getListProduit(){
		Hub.instance.getListProducts().clear();
		Hub.instance.getListProductsGrade().clear();
		boolean encore, encore1;
		try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Produits ");
			encore = results.next();
	        while(encore){
	        	ResultSet resultsGrade = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM ProduitsGrades WHERE produit = '"+results.getString(1)+"'");
	        	encore1 = resultsGrade.next();
	        	if(encore1){
	        		Hub.instance.getListProductsGrade().put(results.getString(1), new ArrayList<>());
	        		while(encore1){
		        		Hub.instance.getListProductsGrade().get(results.getString(1)).add(resultsGrade.getString(2));
		        		encore1 = resultsGrade.next();
		        	}
	        	}
	        	
	        	Hub.instance.getListProducts().put(results.getString(1) , new Products(results));
	            encore = results.next();
	        }
		} catch (Exception e) {}
	}
	
	
	public static void getListMoney(){
		Hub.instance.getListMoney().clear();
		try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Money ");
			while(results.next()){
				Hub.instance.getListMoney().add(new Money(results));
			}
		} catch (Exception e) {}
	}
	
	public static void getListBonus(){
		Hub.instance.getListBonus().clear();
		try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Bonus ");
			while(results.next()){
				Hub.instance.getListBonus().add(new Bonus(results));
			}
		} catch (Exception e) {}
	}
	
	public static void getMessagePlayer(){
		Hub.instance.getMessageGradePlayer().clear();
		try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM MessagePlayer ");
			while(results.next()){
				Hub.instance.getMessageGradePlayer().put(results.getString(3), results.getString(2));
			}
		} catch (Exception e) {}
	}

	public static void getBookHelp(){
		Hub.instance.getBookHelpList().clear();
		try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM BookHelp WHERE page = '"+1+"' ");
			while(results.next()){
				Hub.instance.getBookHelpList().put(results.getString(1), new BookHelp(results));
			}
		} catch (Exception e) {}
	}
}
