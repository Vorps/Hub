package me.vorps.hub.Object;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class JumpsSettings {
	
	private @Getter	int checkPoint;
	private @Getter float yaw;
	
	public JumpsSettings(ResultSet results) throws SQLException {
		checkPoint =results.getInt(2);
		yaw = results.getFloat(3);
	}
}
