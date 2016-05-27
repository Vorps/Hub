package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.PlayerData;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 26/05/2016 at 16:03.
 */
public class Gadgets {

    private static @Getter HashMap<String, Gadgets> listGadgets = new HashMap<>();
    private String classe;
    private @Getter int max;
    private @Getter int timeCooldown;
    private @Getter String name;

    public Gadgets(ResultSet result) throws SqlException{
        name = Database.FORTYCUBE.getDatabase().getString(result, 1);
        classe = Database.FORTYCUBE.getDatabase().getString(result, 2);
        max = Database.FORTYCUBE.getDatabase().getInt(result, 3);
        timeCooldown = Database.FORTYCUBE.getDatabase().getInt(result, 4);
        listGadgets.put(name, this);
    }

    public void setGadgets(Player player, String gadget) {
        try {
            Class classe = Class.forName(this.classe);
            Constructor constructor = classe.getConstructor(new Class[] {Class.forName("org.bukkit.entity.Player"), Class.forName("me.vorps.hub.Object.Gadgets")});
            me.vorps.hub.gadget.Gadgets instance = (me.vorps.hub.gadget.Gadgets) constructor.newInstance(new Object[] {player, this});
            PlayerData playerData = PlayerData.getPlayerData(player.getName());
            playerData.setGadgets(instance);
            if(!playerData.getJump().isInJump() && !playerData.isBuild()){
                player.getInventory().setItem(4, Products.getProduct(gadget).getItem().get(playerData.getLang()).withLore(new String[] {}).get());
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.out.println("La classe " + classe + " n'existe pas");
        } catch (InstantiationException ie) {
            System.out.println("La classe " + classe + " n'est pas instanciable");
        } catch (IllegalAccessException iae) {
            System.out.println("La classe " + classe + " n'est pas accessible");
        } catch (java.lang.reflect.InvocationTargetException ite) {
            ite.printStackTrace();
            System.out.println("Le constructueur de la classe " + classe + " a leve une exception");
        } catch (NoSuchMethodException nme) {
            nme.printStackTrace();
            System.out.println("Le constructeur de la classe " + classe + " n'existe pas");
        } catch (IllegalArgumentException iae) {
            System.out.println("Un parametre du constructueur de la classe "+ classe + " n'est pas du bon type");
        }
    }
}
