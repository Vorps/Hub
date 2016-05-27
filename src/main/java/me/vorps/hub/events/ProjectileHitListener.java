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

    public static class Block {
        private @Getter Material material;
        private @Getter byte data;

        public Block(int material){
            this(material, (byte) -1);
        }

        public Block(int material, byte data){
            this.material = Material.getMaterial(material);
            this.data = data;
        }
        public Block(org.bukkit.block.Block block){
            this.material = block.getType();
            this.data = block.getData();
        }
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if(e.getEntity() instanceof Snowball){
            HashMap<Location, Block> blockPlace = new HashMap<>();
            Location location = e.getEntity().getLocation();
            location.add(0,-1,0);
            for(int x = -Color.R ; x < Color.R; x++){
                for(int z = -Color.R ; z < Color.R; z++){
                    for(int y = -Color.R ; y < Color.R; y++){
                        if (location.clone().add(x, y, z).distance(location) <= Color.R){
                            Location locationBlock = new Location(Bukkit.getWorlds().get(0), location.getBlockX()+x, location.getBlockY()+y, location.getBlockZ()+z);
                            org.bukkit.block.Block block =  Bukkit.getWorlds().get(0).getBlockAt(location.getBlockX()+x, location.getBlockY()+y, location.getBlockZ()+z);
                            if(Color.content(block)){
                                blockPlace.put(locationBlock, new Block(block));
                                block.setType(Material.WOOL);
                                Random random =  new Random();
                                block.setData((byte)  random.nextInt(15));
                            }
                        }
                    }
                }
            }
            new ThreadColor(blockPlace);
        }
    }
}
