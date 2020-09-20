package me.vorps.hub.Object;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class JumpsSettings {
	
	private final @Getter int checkPoint;
	private final @Getter float yaw;
	
	public JumpsSettings(ResultSet results) throws SQLException {
		checkPoint = results.getInt("js_checkpoint");
		yaw = results.getFloat("js_yaw");
	}
}
