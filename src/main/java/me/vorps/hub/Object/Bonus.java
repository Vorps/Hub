package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;

import java.sql.ResultSet;
import java.util.HashMap;

public class Bonus {
    private static HashMap<String, Bonus> listBonus = new HashMap<>();

	private @Getter String bonus;
	private @Getter double coefficient;
	private @Getter String money;

    public static Bonus getBonus(String nameBonus){
        return listBonus.get(nameBonus);
    }

	public Bonus(ResultSet results) throws SqlException {
		bonus = Database.FORTYCUBE.getDatabase().getString(results, 1);
        coefficient = Database.FORTYCUBE.getDatabase().getDouble(results, 2);
		money = Database.FORTYCUBE.getDatabase().getString(results, 3);
        listBonus.put(bonus ,this);
	}
}
