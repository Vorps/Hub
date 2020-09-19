package me.vorps.hub.gadget;

import lombok.Getter;
import me.vorps.hub.thread.ThreadCoolDownGadget;
import net.vorps.api.cooldowns.CoolDowns;
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
                if(gadgets.getTimeCooldown() > 1){
                    player.sendMessage("§eVotre gadjet est désactivé pendant §a"+gadgets.getTimeCooldown()+" §esecondes");
                } else {
                    player.sendMessage("§eVotre gadjet est désactivé pendant §a"+gadgets.getTimeCooldown()+" §eseconde");
                }
                new ThreadCoolDownGadget(player).start();
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
