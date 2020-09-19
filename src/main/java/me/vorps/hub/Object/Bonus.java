package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.hub.data.DataHub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Bonus{
    private static HashMap<String, Bonus> bonus;

	private @Getter final String name;
	private @Getter final double coefficient;
	private @Getter final String money;

    static {
        Bonus.bonus = new HashMap<>();
        DataHub.loadBonus();
    }

	public Bonus(ResultSet results) throws SQLException {
		this.name = results.getString(1);
        this.coefficient = results.getDouble(2);
        this.money = results.getString(3);
        Bonus.bonus.put(this.name ,this);
	}

	public static Bonus getBonus(String name){
	    return Bonus.bonus.get(name);
    }

    public static void clear(){
        Bonus.bonus.clear();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
