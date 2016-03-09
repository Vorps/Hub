package me.vorps.hub.pets;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Project Hub Created by Vorps on 06/03/2016 at 01:12.
 */
public class RideableZombie extends EntityZombie {

    public RideableZombie(World world, Player player) {
        super(world);
        this.rider = player;
    }

    Player rider = null;

    @Override
    public void g(float sideMot, float forMot)
    {
        if ((this.passenger == null) || (!(this.passenger instanceof EntityHuman)))
        {
            super.g(sideMot, forMot);
            this.S = 0.5F;
            return;
        }
        EntityHuman human = (EntityHuman)this.passenger;
        this.lastYaw = (this.yaw = this.passenger.yaw);
        this.pitch = (this.passenger.pitch * 0.5F);
        this.setYawPitch(this.yaw, this.pitch);
        this.aK = (this.aM = this.yaw);
        this.S = 1.0F;


        sideMot = ((EntityLiving)this.passenger).aZ * 0.5F;
        forMot = ((EntityLiving)this.passenger).ba;
        if (forMot <= 0.0F) {
            forMot *= 0.25F;
        }
        sideMot *= 0.75F;

        float speed = 0.35F;


        f(speed);
        super.g(sideMot, forMot);
        try
        {
            Field jump = null;
            jump = EntityLiving.class.getDeclaredField("aY");
            jump.setAccessible(true);
            if ((jump != null) && (this.onGround)) {
                if (jump.getBoolean(this.passenger))
                {
                    double jumpHeight = 0.5D;
                    this.motY = jumpHeight;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
