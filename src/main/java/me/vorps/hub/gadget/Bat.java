package me.vorps.hub.gadget;

import me.vorps.hub.thread.ThreadGadget;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Project Hub Created by Vorps on 26/05/2016 at 14:58.
 */
public class Bat extends Gadgets{

    public Bat(Player player, me.vorps.hub.Object.Gadgets gadgets){
        super(player, gadgets);
    }

    @Override
    public void runGadget(){
        org.bukkit.entity.Bat bat = (org.bukkit.entity.Bat) Bukkit.getWorlds().get(0).spawnEntity(player.getLocation(), EntityType.BAT);
        Vector vector = player.getEyeLocation().getDirection().multiply(5);
        bat.setVelocity(vector);
        new ThreadGadget(bat).start();
    }
}
