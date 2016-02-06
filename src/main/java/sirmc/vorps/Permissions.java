package sirmc.vorps;

import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Permissions {
	
	public static ArrayList<Permissions> PermissionsList = new ArrayList<Permissions>();

	private String permission;
	private String grade;
	private boolean state;
	
	public Permissions(ResultSet resultats) throws SQLException{
		permission = resultats.getString(1);
		grade = resultats.getString(2);
		state = resultats.getBoolean(3);
	}
	
	public static void InitPermissions(){
		PermissionsList.clear();
        try {
			ResultSet results = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Permissions");
	        while(results.next()){
	        	PermissionsList.add(new Permissions(results));
	        }
		} catch (Exception e) {}
	}
	
	public static void PermissionGradeInit(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).setOp(false);
		Hub.instance.getPermission().removeAllPermissions();
		for(Permissions permissions : PermissionsList){
			if(playerHub.getGrade().equals(permissions.grade)){
				if(permissions.permission.equals("*")){
					Bukkit.getPlayer(playerHub.getPlayerName()).setOp(true);
				} else if(permissions.state){
					Hub.instance.getPermission().addPermission(Bukkit.getPlayer(playerHub.getPlayerName()), permissions.permission);
				} else {
					Hub.instance.getPermission().denyPermission(Bukkit.getPlayer(playerHub.getPlayerName()), permissions.permission);
				}
			}
		}
	}
	
	public static void PermissionGradeQuit(PlayerHub playerHub){
		Hub.instance.getPermission().removeAllPermissions();
		Bukkit.getPlayer(playerHub.getPlayerName()).setOp(false);
	}
}
