package me.vorps.hub.events;

import lombok.Getter;
import me.vorps.hub.gadget.Color;
import me.vorps.hub.thread.ThreadColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.HashMap;
import java.util.Random;

/**
 * Project Hub Created by Vorps on 26/05/2016 at 19:05.
 */
public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if(e.getEntity() instanceof Snowball){
            HashMap<Location, Material> blockPlace = new HashMap<>();
            Location location = e.getEntity().getLocation();
            location.add(0,-1,0);
            for(int x = -Color.R ; x < Color.R; x++){
                for(int z = -Color.R ; z < Color.R; z++){
                    for(int y = -Color.R ; y < Color.R; y++){
                        if (location.clone().add(x, y, z).distance(location) <= Color.R){
                            Location locationBlock = new Location(Bukkit.getWorlds().get(0), location.getBlockX()+x, location.getBlockY()+y, location.getBlockZ()+z);
                            Material block =  Bukkit.getWorlds().get(0).getBlockAt(location.getBlockX()+x, location.getBlockY()+y, location.getBlockZ()+z).getType();
                            if(Color.content(block)){
                                Random random =  new Random();
                                Material[] wools = {Material.WHITE_WOOL, Material.ORANGE_WOOL, Material.MAGENTA_WOOL, Material.LIGHT_BLUE_WOOL, Material.YELLOW_WOOL, Material.LIME_WOOL, Material.PINK_WOOL, Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL, Material.CYAN_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL, Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL};
                                blockPlace.put(locationBlock, wools[random.nextInt(15)]);
                            }
                        }
                    }
                }
            }
            new ThreadColor(blockPlace);
        }
    }
}
