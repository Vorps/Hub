package sirmc.vorps.Object;

import lombok.Getter;
import sirmc.vorps.Hub;
import sirmc.vorps.Object.JumpsSettings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Jumps {
	
	private @Getter String jump;
	private @Getter int X;
	private @Getter int Y;
	private @Getter int Z;
	private @Getter float Yaw;
	private @Getter String message;
	private @Getter double Xplayer;
	private @Getter double Yplayer;
	private @Getter double Zplayer;
	
	private @Getter ArrayList<JumpsSettings> jumpSettings = new ArrayList<JumpsSettings>();
	
	public Jumps(ResultSet results) throws SQLException{
		jump =  results.getString(1);
		X =  results.getInt(2);
		Y =  results.getInt(3);
		Z =  results.getInt(4);
		Yaw =  results.getFloat(5);
		message = results.getString(6);
		Xplayer =  results.getDouble(7);
		Yplayer =  results.getDouble(8);
		Zplayer =  results.getDouble(9);
		try {
			results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM JumpsSettings WHERE name = '"+jump+"'");
			while(results.next()){
				jumpSettings.add(new JumpsSettings(results));
			}
		} catch (Exception e) {}
	}
}
