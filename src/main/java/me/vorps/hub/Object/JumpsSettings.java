package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.Exceptions.SqlException;

import java.sql.ResultSet;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class JumpsSettings {
	
	private @Getter	int checkPoint;
	private @Getter float yaw;
	
	public JumpsSettings(ResultSet results) throws SqlException {
		checkPoint = Database.FORTYCUBE.getDatabase().getInt(results, 2);
		yaw = Database.FORTYCUBE.getDatabase().getFloat(results, 3);
	}
}
