package me.vorps.hub.gadget;

import me.vorps.hub.events.ProjectileHitListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

/**
 * Project Hub Created by Vorps on 26/05/2016 at 18:03.
 */
public class Color extends Gadgets {

    public static final int R = 4;
    public static final ProjectileHitListener.Block[] whitelist = new ProjectileHitListener.Block[]{new ProjectileHitListener.Block(5, (byte) 2), new ProjectileHitListener.Block(18, (byte) 3),
            new ProjectileHitListener.Block(24, (byte) 0), new ProjectileHitListener.Block(24, (byte) 1), new ProjectileHitListener.Block(24, (byte) 2), new ProjectileHitListener.Block(17),
            new ProjectileHitListener.Block(95), new ProjectileHitListener.Block(5, (byte) 1), new ProjectileHitListener.Block(159), new ProjectileHitListener.Block(1), new ProjectileHitListener.Block(17, (byte) 3),
            new ProjectileHitListener.Block(18, (byte) 3), new ProjectileHitListener.Block(7), new ProjectileHitListener.Block(49), new ProjectileHitListener.Block(4), new ProjectileHitListener.Block(14), new ProjectileHitListener.Block(18, (byte) 3)};

    public Color(Player player, me.vorps.hub.Object.Gadgets gadgets){
        super(player, gadgets);
    }

    @Override
    public void runGadget(){
        Snowball ball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
        ball.setShooter(player);
        ball.setVelocity(player.getLocation().getDirection().multiply(1.5));
    }

    public static boolean content(Block blockMaterial){
        for(ProjectileHitListener.Block block : whitelist){
            if(block.getMaterial() == blockMaterial.getType()){
                if(block.getData() != -1){
                    if(block.getData() == blockMaterial.getData()){
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
