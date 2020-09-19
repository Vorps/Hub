package me.vorps.hub.thread;

import lombok.Getter;
import net.vorps.api.scoreboard.ScoreBoard;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.PlayerJump;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project Hub Created by Vorps on 21/05/2016 at 11:56.
 */
public class ThreadJump extends Thread {

    private ScoreBoard scoreBoard;
    private String namePlayer;
    private @Getter long time;
    private long timeChecPoint;

    public ThreadJump(ScoreBoard scoreBoard, String namePlayer){
        this.scoreBoard = scoreBoard;
        this.namePlayer = namePlayer;
    }

    public void resetTimeCheckPoint(){
        timeChecPoint = 0;
    }

    @Override
    public void run(){
        /*PlayerJump playerJump = PlayerData.getPlayerData(namePlayer).getJump();
        while (PlayerData.isPlayerDataExits(namePlayer) && playerJump.isInJump()){
            Date date = new Date(time++*1000);
            Date dateCheckPoint = new Date(timeChecPoint++*1000);
            SimpleDateFormat simpleDateFormat;
            if(time >= 3600){
                date.setHours(date.getHours()-1);
                dateCheckPoint.setHours(date.getHours()-1);
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            } else {
                simpleDateFormat = new SimpleDateFormat("mm:ss");
            }
            scoreBoard.updateValue("timeAll", "§bTemps total §7: "+simpleDateFormat.format(date));
            scoreBoard.updateValue("checkPoint_"+playerJump.getCheckPoint(), "§a"+(playerJump.getCheckPoint()+1)+" §7-> §7"+simpleDateFormat.format(dateCheckPoint));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                //
            }
        }*/
    }
}
