package sirmc.vorps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import sirmc.vorps.Object.Jumps;
import sirmc.vorps.menu.Navigator;
import sirmc.vorps.thread.ThreadCooldowns;
import sirmc.vorps.utils.*;

public class PlayerHub {
	private @Getter String playerName;
	private @Getter int hub;
	private @Getter @Setter int nbrClickVisiblePlayer;
	private @Getter @Setter int nbrDoubleJumps;
	private @Getter Party party;
	private @Getter Friends friends;
	private @Getter int numbersFriends;
	private @Getter int numbersMembers;
	private @Getter String grade;
	private @Getter @Setter String bonus;
	private @Getter @Setter boolean visiblePlayer;
	private @Getter @Setter boolean build;
	private @Getter @Setter boolean fly;
	private @Getter @Setter boolean doubleJumps;
	private @Getter @Setter String playerNameMessage;
	private @Getter @Setter ItemStack productTarget;
	private @Getter HashMap<String, Double> money = new HashMap<String, Double>();
	private @Getter ArrayList<ProductsPlayers> Products = new ArrayList<ProductsPlayers>();
	private @Getter @Setter Jumps jump = null;
	private @Getter @Setter Location locCheckPoint;
	private @Getter @Setter boolean inJumps;
	private @Getter @Setter int numberCheckPoint;
	private @Getter @Setter int numberTrying;

	public PlayerHub(Player player){
		this.playerName = player.getName();
		hub = Hub.instance.getNUMBRE_HUB();
		UpdatePlayerDatabases();
		UpdateFriends();
		UpdateParty();
		UpdateGrades();
		getBonusFunction();
		if(!Hub.instance.getPlayerHub().containsKey(playerName)){
			new Title(Settings.getWelcome_MsgTitle(), Settings.getWelcome_msgSubTitle()).send(player);
			DisplayMessage();
		}
		LoadVariable.ListPLayer();
	    TabList.setPlayerList(player, Settings.getMessageTabListHeader(), Settings.getMessageTabListFooter());
	    TeleportSpawn();
	    InitVariablePlayer();
        Hub.instance.getScoreBoard().getTeamDisplayName().get(grade).addPlayer(player);
        player.setScoreboard(Hub.instance.getScoreBoard().getBoard());
        player.setFoodLevel(20);
        Hub.instance.getScoreBoard().updatePlayer();
	}

	public void getMoneyFunction(){
		money.clear();
		try {
			ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM PlayerMoney WHERE namePlayer = '"+playerName+"'");
			while(result.next()){
				money.put(result.getString(2), result.getDouble(3));
			}
		} catch (Exception e) {}
	}

	public void getProductsPlayerFunction(){
		Products.clear();
		try {
			ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM PlayerProduit WHERE namePlayer = '"+playerName+"'");
			while(result.next()){
				Products.add(new ProductsPlayers(result, this));
			}

		} catch (Exception e) {}
	}

	public void getBonusFunction(){
		try {
			bonus = null;
			for(int i = 0; i < Hub.instance.getListBonus().size(); i++){
				if(Hub.instance.getListBonus().get(i).getBonus().equals(getPlayerDatabases().getString(3))){
					bonus = getPlayerDatabases().getString(3);
					break;
				}
			}
		} catch (SQLException e) {
		}
	}

	public void setBuild(boolean state){
		if(state){
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET modeBuild = '"+1+"' WHERE namePlayer = '"+playerName+"'");
		} else {
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET modeBuild = '"+0+"' WHERE namePlayer = '"+playerName+"'");
		}
		build = state;
	}
	
	public void setDoubleJumps(boolean state){
		if(state){
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET modeDoubleJump = '"+1+"' WHERE namePlayer = '"+playerName+"'");
		} else {
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET modeDoubleJump = '"+0+"' WHERE namePlayer = '"+playerName+"'");
		}
		doubleJumps = state;
	}
	
	public void setFly(boolean state){
		if(state){
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET modeFly = '"+1+"' WHERE namePlayer = '"+playerName+"'");
		} else {
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET modeFly = '"+0+"' WHERE namePlayer = '"+playerName+"'");
		}
		fly = state;
	}
	
	public void setVisiblePlayer(boolean state){
		if(state){
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET visiblePlayer = '"+1+"' WHERE namePlayer = '"+playerName+"'");
		} else {
			Hub.instance.database.SendDatabase("UPDATE PlayersSettings SET visiblePlayer = '"+0+"' WHERE namePlayer = '"+playerName+"'");
		}
		visiblePlayer = state;
	}
	
	public void getBuildFunction(boolean state){
		try {
			build = getPlayerDatabases().getBoolean(5);
		} catch (SQLException e) {}
		if(build){
			Bukkit.getPlayer(playerName).getInventory().clear();
         	Bukkit.getPlayer(playerName).setGameMode(GameMode.CREATIVE);
			if(state) Bukkit.getPlayer(playerName).sendMessage("§6Mode build (§2Activé§6).");
		}
	}

	public void getDoubleJumpsFunction(boolean state){
		try {
			doubleJumps = getPlayerDatabases().getBoolean(7);
		} catch (SQLException e) {}
		if(doubleJumps){
			Bukkit.getPlayer(playerName).setAllowFlight(true);
			if(state) Bukkit.getPlayer(playerName).sendMessage("§6Mode doubleJump (§2Activé§6).");
		}
	}
	
	public void getFlyFunction(boolean state){
		try {
			fly = getPlayerDatabases().getBoolean(6);
		} catch (SQLException e) {}
		if(fly){
			Bukkit.getPlayer(playerName).setAllowFlight(true);
			if(state) Bukkit.getPlayer(playerName).sendMessage("§6Mode fly (§2Activé§6).");
		} else {
			getDoubleJumpsFunction(true);
		}
	}
	
	public void getVisiblePlayerFunction(){
		try {
			visiblePlayer = getPlayerDatabases().getBoolean(4);
		} catch (SQLException e) {
		}
		System.out.println(visiblePlayer);
		if(!visiblePlayer){
			Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPlayer(playerName).hidePlayer(p));
			Bukkit.getPlayer(playerName).sendMessage("§6Joueur (§4Dsactivé§6).");
		}
	}

	public void TeleportSpawn(){
		 Location spawn = Hub.instance.getSpawnHub();
		 spawn.setWorld(Bukkit.getPlayer(playerName).getWorld());
		 Bukkit.getPlayer(playerName).teleport(spawn);
	}

	public void InitVariablePlayer(){
        getMoneyFunction();
        getProductsPlayerFunction();
        Bukkit.getPlayer(playerName).setAllowFlight(false);
        Navigator.HubNavigator(this);
		getBuildFunction(true);
		getFlyFunction(true);
		getVisiblePlayerFunction();

		locCheckPoint = null;
		jump = null;
		inJumps = false;
		numberCheckPoint = 0;
		numberTrying = 0;
		nbrClickVisiblePlayer = 0;
		nbrDoubleJumps = 0;
		playerNameMessage = null;
		
	}
	public void UpdateFriends(){
		friends = new Friends(this);
	}
	
	public void UpdateParty(){
		party = new Party(this);
	}
	

	public void UpdateGrades(){
		try {
			grade = getPlayerDatabases().getString(2);
            numbersFriends = Grades.GetGrade(grade).getNumbersFriends();
            numbersMembers = Grades.GetGrade(grade).getNumbersMembres();
		} catch (SQLException e) {}
		for(int i = 0; i < Grades.getGradesList().size(); i++){
			if(Grades.getGradesList().get(i).getGrade().equals(grade)){
				Permissions.PermissionGradeInit(this);
				if(!Grades.getGradesList().get(i).getGradeDisplay().equals("")){
					if(!Grades.getGradesList().get(i).getGradeAlias().equals("")){
						Bukkit.getPlayer(playerName).setPlayerListName(Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeAlias()+" "+playerName);
					} else {
						Bukkit.getPlayer(playerName).setPlayerListName(Grades.getGradesList().get(i).getColorGrade()+""+playerName);
					}
					Bukkit.getPlayer(playerName).setDisplayName(Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeDisplay()+" "+playerName);
					Bukkit.getPlayer(playerName).setCustomName(Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeDisplay()+" "+playerName);
					Bukkit.getPlayer(playerName).setCustomNameVisible(true);
				} else {
					if(!Grades.getGradesList().get(i).getGradeAlias().equals("")){
						Bukkit.getPlayer(playerName).setPlayerListName(Grades.getGradesList().get(i).getColorGrade()+Grades.getGradesList().get(i).getGradeAlias()+" "+playerName);
					} else {
						Bukkit.getPlayer(playerName).setPlayerListName(Grades.getGradesList().get(i).getColorGrade()+""+playerName);
					}
					Bukkit.getPlayer(playerName).setDisplayName(Grades.getGradesList().get(i).getColorGrade()+""+playerName);
					Bukkit.getPlayer(playerName).setCustomName(Grades.getGradesList().get(i).getColorGrade()+""+playerName);
					Bukkit.getPlayer(playerName).setCustomNameVisible(true);
				}
			}
		}
		Navigator.Profil(this, null);
	}

	public void DisplayMessage(){
		String friendsOnlineMessage = "§eAmis en ligne : §a["; String friendsOnlineMessageTmp = friendsOnlineMessage;
		String membersOnlineMessage = "§eMembres en ligne : §a["; String membersOnlineMessageTmp = membersOnlineMessage;
		
		String friendsPendingMessage = "§eAmis en attente : ["; String friendsPendingMessageTmp = friendsPendingMessage;
		String friendsAcceptMessage = "§eNouveau amis : ["; String friendsAcceptMessageTmp = friendsAcceptMessage;
		
		String membersPendingMessage = "§eMembres en attente : ["; String membersPendingMessageTmp = membersPendingMessage;
		String membersAcceptMessage = "§eNouveau membres : ["; String membersAcceptMessageTmp = membersAcceptMessage;
        Bukkit.getPlayer(playerName).sendMessage("§6✴--------------------------------------------------✴");
		Bukkit.getPlayer(playerName).sendMessage("§6✴§e Amis en ligne : §a"+friends.getFriendsOnLineList().size()+"§e/§b"+friends.getFriends().size());
		for(String friendsOnLine : friends.getFriendsOnLineList()){
			friendsOnlineMessageTmp += "§a"+friendsOnLine+"§e,";
		}
		if(!friendsOnlineMessage.equals(friendsOnlineMessageTmp)){
			Bukkit.getPlayer(playerName).sendMessage(friendsOnlineMessageTmp.substring(0, friendsOnlineMessageTmp.length()-1)+"§e].");
		}

        for(String pendingFriends : friends.getPendingFriendsList()){
            ResultSet resultSet;
            try {
                resultSet = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Friends WHERE namePlayer = '"+pendingFriends+"' && friend = '"+playerName+"'");
                resultSet.next();
                if(resultSet.getInt(3) == 1){
                    friendsAcceptMessageTmp += "§a"+pendingFriends+"§e,";
                    Hub.instance.database.SendDatabase("UPDATE Friends SET variable = '"+1+"', date = '"+new Timestamp(System.currentTimeMillis())+"' WHERE namePlayer = '"+playerName+"' && friend = '"+pendingFriends+"'");
                } else {
                    friendsPendingMessageTmp += "§a"+pendingFriends+"§e,";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(!friendsAcceptMessage.equals(friendsAcceptMessageTmp)){
            Bukkit.getPlayer(playerName).sendMessage("§6✴"+friendsAcceptMessageTmp.substring(0, friendsAcceptMessageTmp.length()-1)+"]");
        }
        if(!friendsPendingMessage.equals(friendsPendingMessageTmp)){
            Bukkit.getPlayer(playerName).sendMessage("§6✴"+friendsPendingMessageTmp.substring(0, friendsPendingMessageTmp.length()-1)+"]");
        }

        for(String friendsRequest : friends.getFriendsRequestList()){
            Bukkit.getPlayer(playerName).sendMessage("§6✴ §2"+friendsRequest+"§e vous a demand§ en ami.");
            ChatInteract.ChatInteractAcceptAmi(this, friendsRequest);
        }



        Bukkit.getPlayer(playerName).sendMessage("§6✴--------------------------------------------------✴");
        Bukkit.getPlayer(playerName).sendMessage("§6✴ §eMembres en ligne : §a"+party.getMembersOnLineList().size()+"§e/§b"+party.getMembers().size());
		for(String membersOnline : party.getMembersOnLineList()){
			if(!membersOnline.equals(playerName)){
				membersOnlineMessageTmp += "§a"+membersOnline+"§e,";
			}
		}

		if(!membersOnlineMessage.equals(membersOnlineMessageTmp)){
			Bukkit.getPlayer(playerName).sendMessage(membersOnlineMessageTmp.substring(0, membersOnlineMessageTmp.length()-1)+"§e].");
		}
		
		for(String pendingMember : party.getPendingMembersList()){
			ResultSet resultSet;
			try {
				resultSet = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Friends WHERE namePlayer = '"+pendingMember+"' && friend = '"+playerName+"'");
				resultSet.next();
				if(resultSet.getInt(3) == 1){
					membersAcceptMessageTmp += "§a"+pendingMember+"§e,";
					Hub.instance.database.SendDatabase("UPDATE Party SET variable = '"+1+"', date = '"+new Timestamp(System.currentTimeMillis())+"' WHERE namePlayer = '"+playerName+"' && membre = '"+pendingMember+"'");
				} else {
					membersPendingMessageTmp += "§a"+pendingMember+"§e,";
				}
			} catch (SQLException e) {}
		}
		
		if(!membersAcceptMessage.equals(membersAcceptMessageTmp)){
			Bukkit.getPlayer(playerName).sendMessage(membersAcceptMessageTmp.substring(0, membersAcceptMessageTmp.length()-1)+"]");
		}
		if(!membersPendingMessage.equals(membersPendingMessageTmp)){
			Bukkit.getPlayer(playerName).sendMessage(membersPendingMessageTmp.substring(0, membersPendingMessageTmp.length()-1)+"]");
		}
		
		for(String membersRequest : party.getMembersRequestList()){
			Bukkit.getPlayer(playerName).sendMessage("§2"+membersRequest+"§e vous a invit§ dans sa party.");
			ChatInteract.ChatInteractAcceptParty(this, membersRequest);
		}
		
		if(Hub.instance.getListMute().contains(playerName)){
			ResultSet resultSet;
			try {
				resultSet = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Mute WHERE namePlayer = '"+playerName+"'");
				resultSet.next();
				Bukkit.getPlayer(playerName).sendMessage("§cVous avez §t§ mut§ pour raison : §e"+resultSet.getString(3)+"§c jusqu'au : §a"+Hub.instance.getDATE_FORMAT().format(resultSet.getTimestamp(2))+"§c.");
			} catch (SQLException e) {}
		}

		if(Hub.instance.getMessageGradePlayer().containsKey(grade)){
			Bukkit.getPlayer(playerName).sendMessage(Hub.instance.getMessageGradePlayer().get(grade));
		}

		friends.getFriends().forEach((String nameFriend) -> {
			Bukkit.getPlayer(nameFriend).sendMessage("§eVotre amis §a"+playerName+"§e vient de se connecté.");
		});

		party.getMembers().forEach((String nameMember) -> {
			Bukkit.getPlayer(nameMember).sendMessage("§eLe membre §a"+playerName+"§e vient de se connecté");
		});
	}
	
	public void removePlayerHub(boolean state){
		Bukkit.getPlayer(playerName).getInventory().clear();
		Permissions.PermissionGradeQuit(this);
		if(state){
			Hub.instance.getPlayerHub().remove(playerName);
		}
	}

	private ResultSet getPlayerDatabases(){
		try {
				ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM PlayersSettings WHERE namePlayer = '"+playerName+"'");
		        while(result.next()){
					return result;
		        }
			} catch (Exception e) {}
        return null;
	}
	
	private boolean isNewPlayerDatabases(){
        try {
			ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Players");
	        while(result.next()){
	        	if(result.getString(1).equals(playerName)){
	                return false;
	        	}
	        }
		} catch (Exception e) {}
        return true;
	}
	
	
	private boolean isChangeAddressePlayerDatabases(){
        try {
			ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Players");
	        while(result.next()){
	        	if(result.getString(2).equals(Bukkit.getPlayer(playerName).getAddress().getHostString()) && result.getString(1).equals(playerName)){
	                return false;
	        	}
	        }
		} catch (Exception e) {}
        return true;
	}
	
	public static boolean isChangeDoubleCountPlayerDatabases(Player player){
        try {
			ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Players");
	        while(result.next()){
	        	 if(result.getString(2).equals(Bukkit.getPlayer(player.getName()).getAddress().getHostString())){
	        		 if(!result.getString(1).equals(player.getName())){
	        			 return true;
	        		 }
	        	 }
	        }
		} catch (Exception e) {}
        return false;
	}
	
	public static boolean getBanPlayer(Player player){
        try {
			ResultSet result = Hub.instance.database.getConn().createStatement().executeQuery("SELECT * FROM Players");
	        while(result.next()){
	        	if(result.getString(1).equals(player.getName())){
	                if(!result.getString(2).equals(Bukkit.getPlayer(player.getName()).getAddress().getHostString())){
	                	return true;
	                }
	        		return false;
	        	}
	        }
		} catch (Exception e) {}
        return false;
	}
	
	private void UpdatePlayerDatabases(){
		if(isNewPlayerDatabases()){
			Hub.instance.database.SendDatabase("INSERT INTO Players VALUES ('"+playerName+"', '"+Bukkit.getPlayer(playerName).getAddress().getHostString()+"', '"+new Timestamp(System.currentTimeMillis())+"', '"+new Timestamp(System.currentTimeMillis())+"', '"+1+"')");
			for(int i = 0; i < Hub.instance.getListMoney().size(); i++){
				Hub.instance.database.SendDatabase("INSERT INTO PlayerMoney VALUES ('"+playerName+"', '"+Hub.instance.getListMoney().get(i).getMoney()+"', '"+Settings.getDefaultMontantMoney().get(Hub.instance.getListMoney().get(i).getMoney())+"')");
			}
			Hub.instance.database.SendDatabase("INSERT INTO PlayersSettings VALUES ('"+playerName+"', '"+Settings.getDefaultGrade()+"', '"+Settings.getDefaultBonus()+"', '1', '0', '0', '0', '1', '1', '1', '1', '1', '1')");
		} else {
			if(isChangeAddressePlayerDatabases()){
				Hub.instance.database.SendDatabase("UPDATE Players SET ip = '"+Bukkit.getPlayer(playerName).getAddress().getHostString()+"' WHERE namePlayer = '"+playerName+"'");
			}
		}
		Hub.instance.database.SendDatabase("UPDATE Players SET dateAfter = '"+new Timestamp(System.currentTimeMillis())+"' WHERE namePlayer = '"+playerName+"'");
		Hub.instance.database.SendDatabase("UPDATE Players SET Online = '"+1+"' WHERE namePlayer = '"+playerName+"'");
	}

	public static void VisiblePlayer(PlayerHub playerHub){
		Cooldowns.addCooldownsVisible(playerHub);
		Hub.instance.getCooldownsThread().put(playerHub.getPlayerName(), new ThreadCooldowns(playerHub));
		Hub.instance.getCooldownsThread().get(playerHub.getPlayerName()).start();
		if(playerHub.isVisiblePlayer()){
			playerHub.setVisiblePlayer(false);
			Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPlayer(playerHub.getPlayerName()).hidePlayer(p));
			Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("�e�lLes joueurs sont d�sormais �cinvisibles�e.");
			Bukkit.getPlayer(playerHub.getPlayerName()).playSound(Bukkit.getPlayer(playerHub.getPlayerName()).getLocation(), Sound.CAT_HISS, 100, 10);

		} else {
			playerHub.setVisiblePlayer(true);
			Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPlayer(playerHub.getPlayerName()).showPlayer(p));
			Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("�e�lLes joueurs sont d�sormais �avisibles�e.");
			Bukkit.getPlayer(playerHub.getPlayerName()).playSound(Bukkit.getPlayer(playerHub.getPlayerName()).getLocation(), Sound.CAT_MEOW, 100, 10);
		}
		Navigator.PlayerVisible(playerHub);
	}
}
