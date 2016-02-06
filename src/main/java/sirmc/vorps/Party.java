package sirmc.vorps;

import lombok.Getter;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Party {
	private @Getter boolean state;
	private @Getter String name;
	private @Getter String owner;
	private @Getter ArrayList<String> members = new ArrayList<>();
	private @Getter ArrayList<String> membersOnLineList = new ArrayList<>();
	private @Getter ArrayList<String> membersRequestList = new ArrayList<>();
	private @Getter ArrayList<String> pendingMembersList = new ArrayList<>();

	private PlayerHub playerHub;

	public Party(PlayerHub playerHub) {
		this.playerHub = playerHub;
		try {
			ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Party WHERE membre = '" + playerHub.getPlayerName() + "' || owner = '" + playerHub.getPlayerName() + "'");
			if (result.next()) {
				state = true;
				name = result.getString(1);
				owner = result.getString(5);
				do {
					switch (result.getInt(3)) {
						case 0:
							pendingMembersList.add(result.getString(2));
							break;
						case 1:
							members.add(result.getString(2));
							break;
						case 2:
							membersRequestList.add(result.getString(2));
							break;
						default:
							break;
					}
				} while (result.next());
			}
		} catch (Exception e) {
		}
		for (int i = 0; i < members.size(); i++) {
			if (Hub.instance.getPlayerHub().containsKey(members.get(i))) {
				membersOnLineList.add(members.get(i));
			}
		}
	}
}