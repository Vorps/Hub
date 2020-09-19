package me.vorps.hub.scoreboard;

import me.vorps.hub.Object.Jumps;
import me.vorps.hub.Object.PlayerJump;
import net.vorps.api.players.PlayerData;
import org.bukkit.scoreboard.DisplaySlot;

/**
 * Project Hub Created by Vorps on 04/05/2016 at 02:24.
 */
public class ScoreBoardJump extends net.vorps.api.scoreboard.ScoreBoard{

    public ScoreBoardJump(Jumps jumps, PlayerJump playerJump){
        super(DisplaySlot.SIDEBAR, "");//jumps.toString(PlayerData.getLang(playerJump.getPlayerData().getUUID())
        /*super.add("1", "", 15);
        super.add("checkPoint_0", "§a1 §7-> §700:00", 14);
        super.add("2", " ", 6);
        super.add("timeAll", "§bTemps total §7: 00:00", 5);
        super.add("2", "   ", 4);
        super.add("mode", "§eMode §7: "+playerJump.getJumpDifficulty().toString(playerJump.getPlayerData().getLang()), 3);
        super.add("life", "§aVie : §6"+playerJump.getLife(), 2);
        super.add("2", "  ", 1);
        super.add("giveUp", "§7Abandon : §f/jump end", 0);*/
    }

}
