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
public class CommandDoubleJumps extends CommandsAction {

    @Override
    public boolean stateOnlineFunction() {
        return getPlayerData().isDoubleJumps();
    }

    @Override
    public boolean stateOfflineFunction(String namePlayer) {
        try {
            ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_setting WHERE ps_player = '" + namePlayer + "'");
            result.next();
            return Database.FORTYCUBE.getDatabase().getBoolean(result, 5);
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
        if(!state) {
            if(!getPlayerData().isBuild() || getPlayer().getGameMode() != GameMode.CREATIVE){
                getPlayer().setAllowFlight(false);
            }
            getPlayerData().setDoubleJumps(false);
            stateAction = true;
        } else {
            if(!getPlayerData().getJump().isInJump()){
                if(getPlayerData().isFly()){
                    new CommandFly(getSender(), new String[] {});
                }
                getPlayer().setFlying(false);
                getPlayer().setAllowFlight(true);
                getPlayerData().setNbrDoubleJumps(0);
                getPlayerData().setDoubleJumps(true);
                stateAction = true;
            }
        }
        getPlayerData().setDoubleJumps(state);
        setStateExec(true);
        return stateAction;
    }

    @Override
    public void setOfflineFunction(byte state, String namePlayer){
        try {
            Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_build = '" + state + "' WHERE ps_player = '" + namePlayer + "'");
            setStateExec(true);
        } catch (SqlException e){
            e.printStackTrace();
        }
    }

    @Override
    public void help() {
        ArrayList<String> messages = new ArrayList<>();
        if(getSender() instanceof Player){
            if(getSender().hasPermission(getPermission()+".me.on")){
                messages.add("§a/build §e<on> §f> Active le build");
            }
            if(getSender().hasPermission(getPermission()+".me.off")){
                messages.add("§a/build §e<off> §f> Désactive le double jump");
            }
        }
        if(getSender().hasPermission(getPermission()+".player.on")){
            messages.add("§a/build §e<on> <Joueur> §f> Active le build au joueur");
        }
        if(getSender().hasPermission(getPermission()+".player.off")){
            messages.add("§a/build §e<off> <Joueur> §f> Désactive le build au joueur");
        }
        if(messages.size() != 0){
            getSender().sendMessage("§e✴----------------- §aHelp DoubleJump§e ------------------✴");
            messages.forEach((String message) -> getSender().sendMessage(message));
            getSender().sendMessage("§e✴--------------------------------------------------✴");
        }
    }

    public CommandDoubleJumps(CommandSender sender, String args[]) {
        super(sender, args, "Double jump", Command.DOUBLE_JUMP.getPermissions());
    }
}
