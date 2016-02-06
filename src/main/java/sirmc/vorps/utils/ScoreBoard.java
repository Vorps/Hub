package sirmc.vorps.utils;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

import sirmc.vorps.Grades;
import sirmc.vorps.Hub;
import sirmc.vorps.Settings;

import java.util.HashMap;

public class ScoreBoard {
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private @Getter Scoreboard board = manager.getNewScoreboard();
    private @Getter Objective o = board.registerNewObjective("Nom", "mort");
    private @Getter static HashMap<String, Team> teamDisplayName = new HashMap<>();
    private HashMap<String, Score> value = new HashMap<>();

    public ScoreBoard(){
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(Settings.getNameServer());

        value.put("1", o.getScore("§bServeur"));
        value.get("1").setScore(3);

        value.put("2", o.getScore("§eHub N° : "+Hub.instance.getNUMBRE_HUB()));
        value.get("2").setScore(2);

        value.put("3", o.getScore(""));
        value.get("3").setScore(1);

        value.put("4", o.getScore("§aJoueurs : "+Hub.instance.getPlayerHub().size()));
        value.get("4").setScore(0);

        Team gradeTeam;
        for(Grades grade : Grades.getGradesList()){
            gradeTeam = board.registerNewTeam(grade.getGrade());
            gradeTeam.setPrefix(grade.getColorGrade()+grade.getGradeAlias());
            gradeTeam.setNameTagVisibility(NameTagVisibility.ALWAYS);
            teamDisplayName.put(grade.getGrade(), gradeTeam);
        }
    }

    public void updatePlayer(){
        o.getScoreboard().resetScores(value.get("4").getEntry());
        value.replace("4", o.getScore("§aJoueurs : "+Hub.instance.getPlayerHub().size()));
        value.get("4").setScore(0);
    }

}
