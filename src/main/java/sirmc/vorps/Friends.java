package sirmc.vorps;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Friends {
	private PlayerHub playerHub;

	private @Getter ArrayList<String> friends = new ArrayList<>();
	private @Getter ArrayList<String> friendsOnLineList = new ArrayList<>();
	private @Getter ArrayList<String> friendsRequestList = new ArrayList<>();
	private @Getter ArrayList<String> pendingFriendsList = new ArrayList<>();

	public Friends(PlayerHub playerHub){
		this.playerHub = playerHub;
		try {
			ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Friends WHERE namePlayer = '"+playerHub.getPlayerName()+"'");
			while(result.next()){
				switch (result.getInt(3)){
					case 0:
						pendingFriendsList.add(result.getString(2));
						break;
					case 1:
						friends.add(result.getString(2));
						break;
					case 2:
						friendsRequestList.add(result.getString(2));
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {}

		for(int i = 0; i < friends.size(); i++ ){
			if(Hub.instance.getPlayerHub().containsKey(friends.get(i))){
				friendsOnLineList.add(friends.get(i));
			}
		}
	}
}
