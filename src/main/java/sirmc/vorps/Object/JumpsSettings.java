package sirmc.vorps.Object;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JumpsSettings {
	
	private @Getter	int checkPoint;
	private @Getter float Yaw;
	
	public JumpsSettings(ResultSet results) throws SQLException{
		checkPoint = results.getInt(2);
		Yaw = results.getFloat(3);
	}
}
