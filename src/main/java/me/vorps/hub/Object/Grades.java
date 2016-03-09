package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.Hub;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class Grades {
	private @Getter String grade;
	private @Getter String gradeDisplay;
	private @Getter String gradeAlias;
	private @Getter String colorGrade;
	private @Getter String colorChat;
	private @Getter boolean visibleGrade;
	private @Getter int levelGrade;
	private @Getter boolean defaultGrade;
	private @Getter int numbersFriends;
	private @Getter int numbersMembers;
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
		defaultGrade =  Database.FORTYCUBE.getDatabase().getBoolean(results, 8);
		numbersFriends = Database.FORTYCUBE.getDatabase().getInt(results, 9);
        numbersMembers = Database.FORTYCUBE.getDatabase().getInt(results, 10);
        gradesList.put(grade, this);
	}

	public String toString() {
		return colorGrade+gradeDisplay;
	}


    public static void gradeDisplayInit(){
        for(Grades grades : gradesList.values()){
            Hub.getInstance().getScoreBoard().createTeam(grades.grade, grades.gradeDisplay);
        }
    }
}


