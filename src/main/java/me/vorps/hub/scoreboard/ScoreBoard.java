package me.vorps.hub.scoreboard;

import me.vorps.hub.Data;
import me.vorps.hub.Object.Money;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import org.bukkit.scoreboard.DisplaySlot;

/**
 * Project Hub Created by Vorps on 03/05/2016 at 19:50.
 */
public class ScoreBoard extends me.vorps.fortycube.scoreboard.ScoreBoard{

    private PlayerData playerData;

    public ScoreBoard(PlayerData playerData){
        super(DisplaySlot.SIDEBAR, Settings.getNameServer());
        this.playerData = playerData;
        super.add("1", "§7----§bServeur§7----", 15);
        super.add("2", " §eHub n°§6"+ Data.NB_SERVER, 14);
        super.add("player", " §aJoueurs : §6"+ PlayerData.nbrConnect(), 13);
        super.add("3", "", 12);
        super.add("4", "§7-----§cInfos§7-----", 11);
        super.add("6", "   ", 1);
        super.add("grade", " §7Grade : ", 10);
        int i = 9;
        for(Money money : Money.getListMoney().values()){
            super.add(money.getMoney(), " §7"+money.getMoney()+" : "+money.getColor()+0+" "+money.getAlias(), i);
            i--;
        }
        super.add("7", "§eplay.fortycube.fr", 0);
    }

    public void updateGrade(){
        super.updateValue("grade", " §7Grade : "+playerData.getGrade());
    }

    public void updateMoney(){
        for(Money money : Money.getListMoney().values()){
            super.updateValue(money.getMoney(), " §7"+money.getMoney()+" : "+money.getColor()+playerData.getMoney().get(money.getMoney())+" "+money.getAlias());
        }
    }

    public void updateBonus(){
        if(!playerData.getBonus().getBonus().equals(Settings.getDefaultBonus().getBonus())){
            super.add("bonus", " §eBonus : §a"+playerData.getBonus().getBonus(), 2);
        } else {
            try {
                super.remove("bonus");
            } catch (Exception e){
                //
            }
        }
    }
}
