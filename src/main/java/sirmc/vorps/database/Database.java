package sirmc.vorps.database;

import java.sql.*;

import lombok.Getter;
import sirmc.vorps.Hub;

public class Database
{

    private String url;
    private String user;
    private String pass;
    private @Getter Connection conn;
    
    public Database(){
    	url = "jdbc:mysql://5.135.1.103/sirelMC";
        user = "root";
        pass = "toor";
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException err){
            err.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Server Connected to Database.");
        }
        catch(SQLException err){
        	err.printStackTrace();
        	System.out.println("Erreur, server no connected to database.");
        }
    }

    public void SendDatabase(String requete)
    {
        try
        {
            Statement state = conn.createStatement();
            state.executeUpdate(requete);
            state.close();
        }
        catch(SQLException err)
        {
            err.printStackTrace();
        }
    }
    public void tryConnectionDatabase(){
		try {
            conn.createStatement().executeQuery("SHOW tables");
		} catch (Exception e) {
			Hub.instance.database = new Database();
		}
    }
}
