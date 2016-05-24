package me.vorps.hub.commands;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import me.vorps.hub.menu.Navigator;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class CommandBuild extends CommandsAction{

    @Override
    public boolean stateOnlineFunction() {
        return getPlayerData().isBuild();
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
        if (!state) {
            getPlayer().getInventory().clear();
            Navigator.navigator(getPlayerData(), getPlayer());
            getPlayer().setGameMode(GameMode.ADVENTURE);
            getPlayerData().setBuild(false);
            if (getPlayerData().isDoubleJumps() || getPlayerData().isFly()) {
                getPlayer().setAllowFlight(true);
            }
        } else {
            getPlayer().getInventory().clear();
            getPlayer().setGameMode(GameMode.CREATIVE);
            getPlayer().closeInventory();
            getPlayerData().setBuild(true);
        }
        getPlayerData().setBuild(state);
        setStateExec(true);
        return true;
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
                messages.add("§a/build §e<off> §f> Désactive le build");
            }
        }
        if(getSender().hasPermission(getPermission()+".player.on")){
            messages.add("§a/build <on> §e<Joueur> §f> Active le build au joueur");
        }
        if(getSender().hasPermission(getPermission()+".player.off")){
            messages.add("§a/build <off> §e<Joueur> §f> Désactive le build au joueur");
        }
        if(messages.size() != 0){
            getSender().sendMessage("§e✴-------------------- §aHelp Chat§e ---------------------✴");
            messages.forEach((String message) -> getSender().sendMessage(message));
            getSender().sendMessage("§e✴--------------------------------------------------✴");
        }
    }

    public CommandBuild(CommandSender sender, String args[]) {
        super(sender, args, "Build", Command.BUILD.getPermissions());
    }
}
