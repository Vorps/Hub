package sirmc.vorps;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	private @Getter int numbersMembres;
	private static @Getter ArrayList<Grades> GradesList = new ArrayList<Grades>();

	public Grades(ResultSet resultats) throws SQLException{
		grade = resultats.getString(1);
		gradeDisplay = resultats.getString(2);
		gradeAlias = resultats.getString(3);
		colorGrade = resultats.getString(4);
		colorChat = resultats.getString(5);
		visibleGrade = resultats.getBoolean(6);
		levelGrade = resultats.getInt(7);
		defaultGrade =  resultats.getBoolean(8);
		numbersFriends = resultats.getInt(9);
		numbersMembres = resultats.getInt(10);
	}

	public String toString(){
		return colorGrade+gradeDisplay;
	}
	public static void InitGrade(){
		GradesList.clear();
        try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Grades");
	        while(results.next()){
	        	GradesList.add(new Grades(results));
	        }
		} catch (Exception e) {}
	}

	
	public static Grades GetGrade(PlayerHub playerHub){
		for(Grades grade : GradesList){
			if(grade.grade.equals(playerHub.getGrade())){
				return grade;
			}
		}
		return null;
	}
	
	public static Grades GetGrade(String nameGrade){
		for(Grades grade : GradesList){
			if(grade.grade.equals(nameGrade)){
				return grade;
			}
		}
		return null;
	}
}


