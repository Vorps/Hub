package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Jumps {
    private static @Getter ArrayList<Jumps> listJumps = new ArrayList<>();

    private @Getter ArrayList<JumpsSettings> jumpsSettings = new ArrayList<>();
	private @Getter String jump;
	private @Getter int X;
	private @Getter int Y;
	private @Getter int Z;
	private @Getter float Yaw;
	private @Getter String message;
	private @Getter double xPlayer;
	private @Getter double yPlayer;
	private @Getter double zPlayer;

	
	public Jumps(ResultSet results) throws SqlException {
		jump =  Database.FORTYCUBE.getDatabase().getString(results, 1);
		X =  Database.FORTYCUBE.getDatabase().getInt(results, 2);
		Y =  Database.FORTYCUBE.getDatabase().getInt(results, 3);
		Z =  Database.FORTYCUBE.getDatabase().getInt(results, 4);
		Yaw =  Database.FORTYCUBE.getDatabase().getFloat(results, 5);
		message = Database.FORTYCUBE.getDatabase().getString(results, 6);
        xPlayer =  Database.FORTYCUBE.getDatabase().getDouble(results, 7);
        yPlayer =  Database.FORTYCUBE.getDatabase().getDouble(results, 8);
        zPlayer =  Database.FORTYCUBE.getDatabase().getDouble(results, 9);
		try {
			results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM jump_setting WHERE js_jump = '"+jump+"'");
			while(results.next()){
                jumpsSettings.add(new JumpsSettings(results));
			}
		} catch (SQLException e) {
            //
        }
        listJumps.add(this);
	}
}
