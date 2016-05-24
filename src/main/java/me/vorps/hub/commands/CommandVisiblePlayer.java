package me.vorps.hub.commands;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class CommandVisiblePlayer extends CommandsAction {

    @Override
    public boolean stateOnlineFunction() {
        return getPlayerData().isVisiblePlayer();
    }

    @Override
    public boolean stateOfflineFunction(String namePlayer) {
        try {
            ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_setting WHERE ps_player = '" + namePlayer + "'");
            result.next();
            return Database.FORTYCUBE.getDatabase().getBoolean(result, 4);
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
            Bukkit.getOnlinePlayers().forEach((Player playerVisible) -> {
                PlayerData playerDataGrade = PlayerData.getPlayerData(playerVisible.getName());
                if(!getPlayerData().getFriends().getFriendsOnline().contains(playerVisible.getName())
                        || !getPlayerData().getParty().getMembersOnline().contains(playerVisible.getName())
                        || (!playerDataGrade.getGrade().isVisibleGrade() &&  playerDataGrade.getGrade().getLevelGrade() != 0)
                        ){
                    getPlayer().hidePlayer(playerVisible);
                }
            });
        } else {
            Bukkit.getOnlinePlayers().forEach((Player playerServer) ->  getPlayer().showPlayer(playerServer));
        }
        getPlayerData().setVisiblePlayer(state);
        setStateExec(true);
        return true;
    }

    @Override
    public void setOfflineFunction(byte state, String namePlayer) {
        try {
            Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_visible_player = '" + state + "' WHERE ps_player = '" + namePlayer + "'");
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
                messages.add("§a/visibleplayer §e<on> §f> Active les joueur");
            }
            if (getSender().hasPermission(getPermission()+".me.off")) {
                messages.add("§a/visibleplayer §e<off> §f> Désactive les joueur");
            }
        }
        if (getSender().hasPermission(getPermission()+".player.on")) {
            messages.add("§a/visibleplayer <on> §e<Joueur> §f> Active les joueur au joueur");
        }
        if (getSender().hasPermission(getPermission()+".player.off")) {
            messages.add("§a/visibleplayer <off> §e<Joueur> §f> Désactive les joueur au joueur");
        }
        if (messages.size() != 0) {
            getSender().sendMessage("§e✴---------------- §aHelp visible joueur§e -----------------✴");
            messages.forEach((String message) -> getSender().sendMessage(message));
            getSender().sendMessage("§e✴--------------------------------------------------✴");
        }
    }

    public CommandVisiblePlayer(CommandSender sender, String args[]) {
        super(sender, args, "Joueur", Command.VISIBLE_PLAYER.getPermissions());
    }
}
