package sirmc.vorps.Object;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JumpsSettings {
	
	private @Getter	int checkPoint;
	private @Getter float Yaw;
	
	public JumpsSettings(ResultSet resultats) throws SQLException{
		checkPoint = resultats.getInt(2);
		Yaw = resultats.getFloat(3);
	}
}
