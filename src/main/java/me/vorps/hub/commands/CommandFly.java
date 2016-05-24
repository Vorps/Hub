package me.vorps.hub.commands;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class CommandFly extends CommandsAction {

    @Override
    public boolean stateOnlineFunction() {
        return getPlayerData().isFly();
    }

    @Override
    public boolean stateOfflineFunction(String namePlayer) {
        try {
            ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_setting WHERE ps_player = '" + namePlayer + "'");
            result.next();
            return Database.FORTYCUBE.getDatabase().getBoolean(result, 6);
        } catch (SQLException e) {
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean setOnlineFunction(boolean state) {
        boolean stateAction = false;
        if (!state) {
            getPlayer().setFlying(false);
            if(!getPlayerData().isBuild() || getPlayer().getGameMode() != GameMode.CREATIVE){
                getPlayer().setAllowFlight(false);
            }
            getPlayerData().setFly(false);
            stateAction = true;
        } else {
            if(!getPlayerData().getJump().isInJump()){
                if(getPlayerData().isDoubleJumps()){
                    new CommandDoubleJumps(getSender(), new String[] {});
                }
                getPlayer().setAllowFlight(true);
                getPlayerData().setFly(true);
                stateAction = true;
            }
        }
        getPlayerData().setFly(state);
        setStateExec(true);
        return stateAction;
    }

    @Override
    public void setOfflineFunction(byte state, String namePlayer) {
        try {
            Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_fly = '" + state + "' WHERE ps_player = '" + namePlayer + "'");
            setStateExec(true);
        } catch (SqlException e){
            e.printStackTrace();
        }
    }

    @Override
    public void help() {
        ArrayList<String> messages = new ArrayList<>();
        if(getSender() instanceof Player){
            if (getSender().hasPermission(getPermission()+".me.on")) {
                messages.add("§a/fly §e<on> §f> Active le fly");
            }
            if (getSender().hasPermission(getPermission()+".me.off")) {
                messages.add("§a/fly §e<off> §f> Désactive le fly");
            }
        }
        if (getSender().hasPermission(getPermission()+".player.on")) {
            messages.add("§a/fly <on> §e<Joueur> §f> Active le fly au joueur");
        }
        if (getSender().hasPermission(getPermission()+".player.off")) {
            messages.add("§a/fly <off> §e<Joueur> §f> Désactive le fly au joueur");
        }
        if (messages.size() != 0) {
            getSender().sendMessage("§e✴--------------------- §aHelp fly§e ---------------------✴");
            messages.forEach((String message) -> getSender().sendMessage(message));
            getSender().sendMessage("§e✴--------------------------------------------------✴");
        }
    }

    public CommandFly(CommandSender sender, String args[]) {
        super(sender, args, "Fly", Command.FLY.getPermissions());
    }
}
