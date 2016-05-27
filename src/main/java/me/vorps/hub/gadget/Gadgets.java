package me.vorps.hub.gadget;

import lombok.Getter;
import me.vorps.fortycube.cooldown.CoolDowns;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 26/05/2016 at 14:58.
 */
public abstract class Gadgets extends Thread {

    protected Player player;
    private int number;
    private @Getter me.vorps.hub.Object.Gadgets gadgets;

    protected Gadgets(Player player, me.vorps.hub.Object.Gadgets gadgets){
        this.player = player;
        this.gadgets = gadgets;
        number = 0;
    }

    public abstract void runGadget();

    public void run(){
        if(!CoolDowns.hasCoolDown(player.getName(), "gadget")){
            if(++number == gadgets.getMax()){
                number = 0;
                new CoolDowns(player.getName(), gadgets.getTimeCooldown(), "gadget");
            }
            runGadget();
        } else {
            CoolDowns coolDowns = CoolDowns.getCoolDown(player.getName(), "gadget");
            if(coolDowns.getSecondsLeft() <= 0) coolDowns.removeCoolDown();
        }
    }

    public String toString(){
        return gadgets.getName();
    }
}
