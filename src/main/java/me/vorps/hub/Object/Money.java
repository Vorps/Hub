package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Money {
    private static @Getter HashMap<String, Money> listMoney = new HashMap<>();

	private @Getter String money;
	private @Getter String alias;
	private @Getter String color;
	
	public Money(ResultSet results) throws SqlException {
		money = Database.FORTYCUBE.getDatabase().getString(results, 1);
		alias = Database.FORTYCUBE.getDatabase().getString(results, 2);
		color = Database.FORTYCUBE.getDatabase().getString(results, 3);
        listMoney.put(Database.FORTYCUBE.getDatabase().getString(results, 1), this);
	}

    public static Money getMoney(String nameMoney){
        return listMoney.get(nameMoney);
    }
}
