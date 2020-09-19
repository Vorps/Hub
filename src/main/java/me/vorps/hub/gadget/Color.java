package me.vorps.hub.gadget;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

/**
 * Project Hub Created by Vorps on 26/05/2016 at 18:03.
 */
public class Color extends Gadgets {

    public static final int R = 4;
    public static final Material[] whitelist = {Material.BIRCH_WOOD, Material.JUNGLE_LEAVES,Material.SANDSTONE, Material.CHISELED_SANDSTONE, Material.SMOOTH_SANDSTONE, Material.OAK_WOOD, Material.WHITE_STAINED_GLASS};
//            Material.SPRUCE_WOOD, Material., new ProjectileHitListener.Block(1), new ProjectileHitListener.Block(17, (byte) 3),
//            new ProjectileHitListener.Block(18, (byte) 3), new ProjectileHitListener.Block(7), new ProjectileHitListener.Block(49), new ProjectileHitListener.Block(4), new ProjectileHitListener.Block(14), new ProjectileHitListener.Block(18, (byte) 3)};

    public Color(Player player, me.vorps.hub.Object.Gadgets gadgets){
        super(player, gadgets);
    }

    @Override
    public void runGadget(){
        Snowball ball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
        ball.setShooter(player);
        ball.setVelocity(player.getLocation().getDirection().multiply(1.5));
    }

    public static boolean content(Material blockMaterial){
        for(Material block : whitelist) if(block == blockMaterial) return true;
        return false;
    }
}
