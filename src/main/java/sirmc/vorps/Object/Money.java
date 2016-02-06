package sirmc.vorps.Object;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Money {
	private @Getter String money;
	private @Getter String alias;
	
	public Money(ResultSet resultats) throws SQLException{
		money = resultats.getString(1);
		alias = resultats.getString(2);
	}
}
