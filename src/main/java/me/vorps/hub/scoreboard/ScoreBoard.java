package me.vorps.hub.scoreboard;

import me.vorps.hub.PlayerData;
import me.vorps.hub.data.SettingsHub;
import net.vorps.api.data.Data;
import net.vorps.api.objects.Money;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Project Hub Created by Vorps on 03/05/2016 at 19:50.
 */
public class ScoreBoard extends net.vorps.api.scoreboard.ScoreBoard{

    public ScoreBoard(UUID uuid){
        super(DisplaySlot.SIDEBAR, SettingsHub.getServerName());
        super.add("1", "§7----§bServeur§7----", 15);
        super.add("2", " §eHub n°§6"+ Data.SERVER_NAME.split("_")[1], 14);
        super.add("player", " §aJoueurs : §6"+ net.vorps.api.players.PlayerData.getNB_PlayerOnline(), 13);
        super.add("3", "", 12);
        super.add("4", "§7-----§cInfos§7-----", 11);
        this.updateGrade(uuid);
        int i = 9;
        for(Money money : Money.getMoneys().stream().map(Money::getMoney).collect(Collectors.toList())){
            super.add(money.getMoney(), " §7"+money.getMoney()+" : "+money.getColor()+0+" "+money.getAlias(), i);
            i--;
        }
        this.updateBonus(uuid);
        super.add("6", "   ", 1);
        super.add("7", "§e"+ SettingsHub.getIP(), 0);
    }

    public void updateGrade(UUID uuid){
        super.add("grade", " §7Grade : "+ PlayerData.getRank(uuid), 10);
    }

    public void updateMoney(UUID uuid, Money money){
        super.updateValue(money.getMoney(), " §7"+money.getMoney()+" : "+money.getColor()+PlayerData.getMoney(uuid, money.getMoney())+" "+money.getAlias());
    }

    public void updateBonus(UUID uuid){
        if(!PlayerData.getBonus(uuid).getName().equals(SettingsHub.getDefaultBonus().getName())) super.add("bonus", " §eBonus : §a"+PlayerData.getBonus(uuid), 2);
        else super.remove("bonus");
    }
}