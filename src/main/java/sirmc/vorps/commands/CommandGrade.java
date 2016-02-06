package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Grades;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandGrade {

    public static boolean CommandGrade(String args[], PlayerHub playerHub){
        if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.grade")){
            if(args.length == 2){
                for(int i = 0; i < Grades.getGradesList().size(); i++){
                    if((Grades.getGradesList().get(i).getGrade()).equalsIgnoreCase(args[0])){
                        Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET grade = '"+Grades.getGradesList().get(i).getGrade()+"' WHERE namePlayer = '"+args[1]+"'");
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[1]+"§e est maintenant §a"+Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeDisplay()+"§e.");
                        if(Hub.instance.getPlayerHub().containsKey(args[1])){
                            if(Grades.getGradesList().get(i).getGradeDisplay().equals("")){
                                Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[1]).getPlayerName()).sendMessage("§eVous êtes maintenant §a"+Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGrade()+"§e.");
                            } else {
                                Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[1]).getPlayerName()).sendMessage("§eVous êtes maintenant §a"+Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeDisplay()+"§e.");
                            }
                            Hub.instance.getPlayerHub().get(args[1]).UpdateGrades();
                        }
                        return true;
                    }
                }
                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cErreur le grade n'existe pas.");
            } else {
                AideCommande.AideGrade(playerHub);
            }
        }
        return false;
    }
}
