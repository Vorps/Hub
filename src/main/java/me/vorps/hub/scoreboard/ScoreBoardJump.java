package me.vorps.hub.scoreboard;

import lombok.Getter;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.Object.PlayerJump;
import org.bukkit.scoreboard.DisplaySlot;

/**
 * Project Hub Created by Vorps on 04/05/2016 at 02:24.
 */
public class ScoreBoardJump {

    private @Getter me.vorps.fortycube.scoreboard.ScoreBoard scoreBoard;

    public ScoreBoardJump(Jumps jumps, PlayerJump playerJump){
        String lang = playerJump.getPlayerData().getLang();
        scoreBoard = new me.vorps.fortycube.scoreboard.ScoreBoard(DisplaySlot.SIDEBAR, jumps.toString(lang));
        scoreBoard.add("1", "", 15);
        scoreBoard.add("checkPoint_0", "§a1 §7-> §700:00", 14);
        scoreBoard.add("2", " ", 6);
        scoreBoard.add("timeAll", "§bTemps total §7: 00:00", 5);
        scoreBoard.add("2", "   ", 4);
        scoreBoard.add("mode", "§eMode §7: "+playerJump.getJumpDifficulty().toString(lang), 3);
        scoreBoard.add("life", "§aVie : §6"+playerJump.getLife(), 2);
        scoreBoard.add("2", "  ", 1);
        scoreBoard.add("giveUp", "§7Abandon : §f/jump end", 0);
    }

}
