package me.vorps.hub.particle;

import lombok.Getter;
import lombok.Setter;
import me.vorps.fortycube.utils.ParticleEffect;
import me.vorps.hub.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 25/05/2016 at 16:01.
 */
public class Particle extends Thread {

    private Player player;
    private @Getter String particle;
    private boolean isRun;
    private @Setter boolean sneak;
    private double phi;

    public Particle(Player player, String particle) {
        this.player = player;
        this.particle = particle;
        isRun = true;
        sneak = false;
        phi = 0;
    }

    @Override
    public void run() {
        while (isRun) {
            if (sneak) {
                final Location location = player.getLocation();
                phi += Math.PI / 10;
                for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 40) {
                    double r = 1.5;
                    double x = r * Math.cos(theta) * Math.sin(phi);
                    double y = r * Math.cos(phi) + 1.5;
                    double z = r * Math.sin(theta) * Math.sin(phi);
                    location.add(x, y, z);
                    ParticleEffect.valueOf(particle.toUpperCase()).display(0, 0, 0, 0, 0,  location, 1);
                    location.subtract(x, y, z);
                }
                if (phi > Math.PI) {
                    phi = 0;
                    sneak = false;
                }
            } else {
                ParticleEffect.valueOf(particle.toUpperCase()).display(1, 1, 1, 0, 1,  player.getLocation(), 1);
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                //
            }
        }
    }

    public void removeParticle() {
        isRun = false;
    }

    public void restart() {
        Particle particle = new Particle(player, this.particle);
        particle.start();
        PlayerData.getPlayerData(player.getName()).setParticle(particle);
    }

    @Override
    public String toString(){
        return particle;
    }

}