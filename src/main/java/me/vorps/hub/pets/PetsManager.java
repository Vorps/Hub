package me.vorps.hub.pets;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Project Hub Created by Vorps on 05/03/2016 at 21:10.
 */
public enum PetsManager {
    BAT("Bat", 65, EntityType.BAT, EntityBat.class, RideableBat.class),
    ZOMBIE("Zombie", 53, EntityType.ZOMBIE, EntityZombie.class, RideableZombie.class);

    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;

    PetsManager(String name, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass) {
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;

    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public Class<? extends EntityInsentient> getNMSClass() {
        return this.nmsClass;
    }
    public Class<? extends EntityInsentient> getCustomClass() {
        return this.customClass;
    }

    public static void regissterEntities() {
        for (PetsManager entity : values()) {
            try {
                Method a = EntityTypes.class.getDeclaredMethod("a", new Class<?>[] { Class.class, String.class, int.class });
                a.setAccessible(true);
                a.invoke(null, entity.getCustomClass(), entity.getName(), entity.getID()); //error here, ID already registered
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /*
    public static void registerEntities()
      {
        for (Register entity : values()) {
          a(entity.getCustomClass(), entity.getName(), entity.getID());
        }
        try
        {
         BiomeBase[]  biomes = (BiomeBase[])getPrivateStatic(BiomeBase.class, "biomes");
        }
        catch (Exception exc)
        {
          BiomeBase[] biomes;
          return;
        }
        BiomeBase[] biomes;
        BiomeBase[] arrayOfBiomeBase1 = biomes;int k = biomes.length;
        for (k = 0; biomes.length < k; k++)
        {
          BiomeBase biomeBase = arrayOfBiomeBase1[k];
          if (biomeBase == null) {
            break;
          }
          for (String field : new String[] { "as", "at", "au", "av" }) {
            try
            {
              Field list = BiomeBase.class.getDeclaredField(field);
              list.setAccessible(true);

              List<BiomeMeta> mobList = (List)list
                .get(biomeBase);
              int i2;
              int i1;
              for (Iterator localIterator = mobList.iterator(); localIterator.hasNext(); i1 > i2)
              {
                BiomeMeta meta = (BiomeMeta)localIterator.next();
                Register[] arrayOfRideAbleEntityType2;
                i2 = (arrayOfRideAbleEntityType2 = values()).length;i1 = 0; continue;Register entity = arrayOfRideAbleEntityType2[i1];
                if (entity.getNMSClass().equals(meta.b)) {
                  meta.b = entity.getCustomClass();
                }
                i1++;
              }
            }
            catch (Exception e)
            {
              e.printStackTrace();
            }
          }
        }
      }
*/

    private static Object getPrivateStatic(
            @SuppressWarnings("rawtypes") Class clazz, String f)
            throws Exception {
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }
    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
    private static void a(Class paramClass, String paramString, int paramInt) {
        try {
            ((Map) getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
            ((Map) getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
            ((Map) getPrivateStatic(EntityTypes.class, "e")).put(Integer.valueOf(paramInt), paramClass);
            ((Map) getPrivateStatic(EntityTypes.class, "f")).put(paramClass, Integer.valueOf(paramInt));
            ((Map) getPrivateStatic(EntityTypes.class, "g")).put(paramString, Integer.valueOf(paramInt));
        } catch (Exception localException) {
        }
    }

    // MAKING
    public static double mountSpeed = 0.2D;
    private static double maxHealth = 2.0D;

    private static void make(EntityLiving nmsEntity, Player player) {
        if (!canSummonMount(player.getLocation())) {
            player.sendMessage("§cVous ne pouvez pas utiliser cette monture ici.");
            return;
        }
        LivingEntity mount = (LivingEntity) nmsEntity.getBukkitEntity();
        // Adulte
        if (mount instanceof EntityAgeable) ((EntityAgeable)mount).setAge(0);
        Location location = player.getLocation();
        World nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
        nmsEntity.setPosition(location.getX(), location.getY() + 0.3, location.getZ());
        nmsWorld.addEntity(nmsEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        // LA VIE
        mount.setMaxHealth(maxHealth);;
        mount.setPassenger(player);
        player.closeInventory();
    }

    public static boolean canSummonMount(Location location) {
        return true; // TODO: improve
    }

    public static void rideBat(Player player) {
        Location loc = player.getLocation();
        World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableBat(nmsWorld, player), player);
    }


    public static void rideZombie(Player player) {
        Location loc = player.getLocation();
        World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableZombie(nmsWorld, player), player);
    }

}