package me.vorps.hub.pets;

import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Player;

public class RideableBat extends EntityBat {

    public RideableBat(World world, Player player) {
        super(world);
        this.rider = player;
        this.setSize(10, 10);
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
