package me.vorps.hub.pets;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityIronGolem;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.IronGolem;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class IronGolemMount extends EntityIronGolem {

    public IronGolemMount(World world) {
        super(world);

        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);

            bField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
            bField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void g(float sideMot, float forMot) {
        if (this.passenger != null && this.passenger instanceof EntityHuman) {
            this.lastYaw = this.yaw = this.passenger.yaw;
            this.pitch = this.passenger.pitch * 0.5F;
            this.setYawPitch(this.yaw, this.pitch);
            this.aI = this.aG = this.yaw;
            sideMot = ((EntityLiving) this.passenger).aZ * 0.3F;
            forMot = ((EntityLiving) this.passenger).ba;
            if (forMot <= 0.0F) {
                forMot *= 5.25F;
            }

            Field jump = null;
            try {
                jump = EntityLiving.class.getDeclaredField("aY");
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            } catch (SecurityException e1) {
                e1.printStackTrace();
            }
            jump.setAccessible(true);

            if (jump != null && this.onGround) {
                try {
                    if (jump.getBoolean(this.passenger)) {
                        double jumpHeight = 0.5D;
                        this.motY = jumpHeight;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            this.S = 1.0F;
            this.aK = (float) (this.bh * 0.1F);
            if (!this.world.isClientSide) {
                this.j((int) 0.35F);
                super.g(sideMot, forMot);
            }

            this.ay = this.az;// Some extra things
            double d0 = this.locX - this.lastX;
            double d1 = this.locZ - this.lastZ;
            float f4 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
            if (f4 > 1.0F) {
                f4 = 1.0F;
            }

            this.az += (f4 - this.az) * 0.4F;
            this.aA += this.az;
        } else {
            this.S = 0.5F;
            this.aK = 0.02F;
            super.g(sideMot, forMot);
        }
    }

    public static IronGolem spawn(Location loc) {
        World mcWorld = (World) ((CraftWorld) loc.getWorld()).getHandle();
        final IronGolemMount customEnt = new IronGolemMount(mcWorld);
        customEnt.setLocation(loc.getX(), loc.getY(), loc.getZ(), (float) -179.1, (float) 1.7);
        ((CraftLivingEntity) customEnt.getBukkitEntity()).setRemoveWhenFarAway(false);
        mcWorld.addEntity(customEnt, SpawnReason.CUSTOM);
        return (IronGolem) customEnt.getBukkitEntity();
    }

}
