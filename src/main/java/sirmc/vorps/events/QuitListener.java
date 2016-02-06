package sirmc.vorps.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import sirmc.vorps.Grades;
import sirmc.vorps.Hub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        for(int i = 0; i < Grades.getGradesList().size(); i++){
            if(Grades.getGradesList().get(i).getGrade().equals(Hub.instance.getPlayerHub().get(player.getName()).getGrade())){
                if(Grades.getGradesList().get(i).isVisibleGrade()){
                    e.setQuitMessage(Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeDisplay()+" "+player.getName()+" §ea quitté le hub !");
                } else {
                    e.setQuitMessage("");
                }
            }
        }
        Hub.instance.getPlayerHub().get(player.getName()).removePlayerHub(true);
        Hub.instance.getScoreBoard().updatePlayer();
        Hub.instance.database.SendDatabase("UPDATE Players SET Online = '"+0+"' WHERE namePlayer = '"+player.getName()+"'");
    }
}
