package me.vorps.hub.utils;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.util.HashMap;

public class PermissionsPlayer {

    private static HashMap<String, PermissionsPlayer> permissionsList = new HashMap<>();

    private String permission;
    private String grade;
    private boolean state;

    public PermissionsPlayer(ResultSet result) throws SqlException{
        permission = Database.FORTYCUBE.getDatabase().getString(result, 1);
        grade = Database.FORTYCUBE.getDatabase().getString(result,2);
        state = Database.FORTYCUBE.getDatabase().getBoolean(result,3);
        permissionsList.put(permission, this);
    }


    public static void permissionGradeInit(PlayerData playerData){
        Player player = Bukkit.getPlayer(playerData.getNamePlayer());
        Permission.getInstance().removeAllPermissions();
        permissionsList.values().forEach((PermissionsPlayer permissionsPlayer) -> {
            if(playerData.getGrade().getGrade().equals(permissionsPlayer.grade)){
                if(permissionsPlayer.state){
                    Permission.getInstance().addPermission(player, permissionsPlayer.permission);
                } else {
                    Permission.getInstance().denyPermission(player, permissionsPlayer.permission);
                }
            }
        });
    }
}

