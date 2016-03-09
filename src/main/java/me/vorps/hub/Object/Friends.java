package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:44.
 */
public class Friends {

	private @Getter ArrayList<String> friends = new ArrayList<>();
	private @Getter ArrayList<String> friendsOnLineList = new ArrayList<>();
	private @Getter ArrayList<String> friendsRequestList = new ArrayList<>();
	private @Getter ArrayList<String> pendingFriendsList = new ArrayList<>();

	public Friends(String namePlayer){
		try {
			ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM friend WHERE f_name = '"+ namePlayer +"'");
			while(result.next()){
				switch (Database.FORTYCUBE.getDatabase().getInt(result, 3)){
					case 0:
						pendingFriendsList.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
						break;
					case 1:
						friends.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
						break;
					case 2:
						friendsRequestList.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
						break;
					default:
						break;
				}
			}
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }

		friends.forEach((String friend) -> {
			if(PlayerData.isPlayerDataExits(friend)){
				friendsOnLineList.add(friend);
			}
		});
	}
}
