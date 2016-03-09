package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class Party {
	private @Getter boolean state;
	private @Getter String name;
	private @Getter String owner;
	private @Getter ArrayList<String> members = new ArrayList<>();
	private @Getter ArrayList<String> membersOnLineList = new ArrayList<>();
	private @Getter ArrayList<String> membersRequestList = new ArrayList<>();
	private @Getter ArrayList<String> pendingMembersList = new ArrayList<>();

	public Party(String namePlayer) {
		try {
            ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM party WHERE party_member = '" + namePlayer + "' || party_leader = '" + namePlayer + "'");
            if (result.next()) {
                state = true;
                name = Database.FORTYCUBE.getDatabase().getString(result, 1);
                owner = Database.FORTYCUBE.getDatabase().getString(result, 5);
                do {
                    switch (Database.FORTYCUBE.getDatabase().getInt(result, 3)) {
                        case 0:
                            pendingMembersList.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
                            break;
                        case 1:
                            members.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
                            break;
                        case 2:
                            membersRequestList.add(Database.FORTYCUBE.getDatabase().getString(result, 2));
                            break;
                        default:
                            break;
                    }
                } while (result.next());
            }
        } catch (SQLException | SqlException e){
            //
        }
        members.forEach((String member) ->  {
            if (PlayerData.isPlayerDataExits(member)) {
                membersOnLineList.add(member);
            }
        });
	}
}