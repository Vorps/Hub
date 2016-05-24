package me.vorps.hub.Object;

import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.databases.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.util.HashMap;

public class Permission {

    private static HashMap<String, Permission> permissionsList = new HashMap<>();

    private String permission;
    private String grade;

    public Permission(ResultSet result) throws SqlException{
        permission = Database.FORTYCUBE.getDatabase().getString(result, 1);
        grade = Database.FORTYCUBE.getDatabase().getString(result,2);
        permissionsList.put(permission, this);
    }


    public static void permissionGradeInit(PlayerData playerData){
        Player player = Bukkit.getPlayer(playerData.getNamePlayer());
        me.vorps.hub.utils.Permission.getInstance().removeAllPermissions();
        permissionsList.values().forEach((Permission permissionsPlayer) -> {
            if(playerData.getGrade().getGrade().equals(permissionsPlayer.grade)){
                me.vorps.hub.utils.Permission.getInstance().addPermission(player, permissionsPlayer.permission);
            }
        });
    }

    public static void clear(){
        permissionsList.clear();
    }
}

