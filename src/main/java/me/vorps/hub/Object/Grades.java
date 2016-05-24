package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.scoreboard.ScoreBoard;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class Grades{
	private @Getter String grade;
	private @Getter String gradeDisplay;
	private @Getter String gradeAlias;
	private @Getter String colorGrade;
	private @Getter String colorChat;
	private @Getter boolean visibleGrade;
	private @Getter int levelGrade;
	private @Getter int numbersFriends;
	private @Getter int numbersMembers;
    private @Getter int pendingGame;
	private static @Getter HashMap<String, Grades> gradesList = new HashMap<>();

    public static Grades getGrades(String nameGrade){
        return gradesList.get(nameGrade);
    }

	public Grades(ResultSet results) throws SqlException {
		grade = Database.FORTYCUBE.getDatabase().getString(results, 1);
		gradeDisplay = Database.FORTYCUBE.getDatabase().getString(results, 2);
		gradeAlias = Database.FORTYCUBE.getDatabase().getString(results, 3);
		colorGrade = Database.FORTYCUBE.getDatabase().getString(results, 4);
		colorChat = Database.FORTYCUBE.getDatabase().getString(results, 5);
		visibleGrade = Database.FORTYCUBE.getDatabase().getBoolean(results, 6);
		levelGrade = Database.FORTYCUBE.getDatabase().getInt(results, 7);
		numbersFriends = Database.FORTYCUBE.getDatabase().getInt(results, 8);
        numbersMembers = Database.FORTYCUBE.getDatabase().getInt(results, 9);
        pendingGame = Database.FORTYCUBE.getDatabase().getInt(results, 10);
        gradesList.put(grade, this);
	}

	public String toString() {
        if(gradeDisplay.isEmpty()){
            return colorGrade+"["+grade+"]";
        } else {
            return colorGrade+gradeDisplay;
        }
	}


    public static void gradeDisplayInit(){
    }

    public static void clear(){
        gradesList.clear();
    }
}


