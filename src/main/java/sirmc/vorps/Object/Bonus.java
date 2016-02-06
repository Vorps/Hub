package sirmc.vorps.Object;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bonus {
	private @Getter String bonus;
	private @Getter double coeff;
	private @Getter String money;

	public Bonus(ResultSet resultats) throws SQLException{
		bonus = resultats.getString(1);
		coeff = resultats.getDouble(2);
		money = resultats.getString(3);
	}
}
