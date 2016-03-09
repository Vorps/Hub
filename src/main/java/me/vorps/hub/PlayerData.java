package me.vorps.hub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import me.vorps.fortycube.cooldown.CoolDowns;
import me.vorps.fortycube.utils.ChatInteract;
import me.vorps.fortycube.utils.TabList;
import me.vorps.fortycube.utils.Title;
import me.vorps.hub.Object.*;
import me.vorps.fortycube.Execeptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.thread.ThreadCoolDowns;
import me.vorps.hub.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.vorps.hub.menu.Navigator;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class PlayerData {
    private static HashMap<String, PlayerData> playersData = new HashMap<>();

	private @Getter String namePlayer;

	private @Getter @Setter int nbrClickVisiblePlayer;
	private @Getter @Setter int nbrDoubleJumps;
	private @Getter Party party;
	private @Getter Friends friends;
	private @Getter Grades grade;
	private @Getter @Setter Bonus bonus;
	private @Getter boolean visiblePlayer;
	private @Getter boolean build;
	private @Getter boolean fly;
	private @Getter boolean doubleJumps;
	private @Getter @Setter String namePlayerMessage;
	private @Getter @Setter ItemStack productTarget;
	private @Getter HashMap<String, Double> money = new HashMap<>();
	private @Getter ArrayList<ProductsPlayers> products = new ArrayList<>();
	private @Getter @Setter Jumps jump = null;
	private @Getter @Setter Location locCheckPoint;
	private @Getter @Setter boolean inJumps;
	private @Getter @Setter int numberCheckPoint;
	private @Getter @Setter int numberTrying;

	public PlayerData(Player player){
		this.namePlayer = player.getName();
		updateFriends();
		updateParty();
		getBonusFunction();
        updateGrades();
	    TabList.setPlayerList(player, Settings.getMessageTabListHeader(), Settings.getMessageTabListFooter());
		teleportSpawn();
	    initVariablePlayer();
        player.setScoreboard(Hub.getInstance().getScoreBoard().getScoreBoard());
        player.setFoodLevel(20);
		if(!playersData.containsKey(namePlayer)){
			new Title(Settings.getWelcome_MsgTitle(), Settings.getWelcome_msgSubTitle()).send(player);
			displayMessage();
		}
        playersData.put(namePlayer , this);
	}

    public static boolean isPlayerDataExits(String namePlayer){
        return playersData.containsKey(namePlayer);
    }

    public static PlayerData getPlayerData(String namePlayer){
        return playersData.get(namePlayer);
    }

    public static int nbrConnect(){
        return playersData.size();
    }
	public void getMoneyFunction(){
		money.clear();
		try {
			ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_money WHERE pm_player = '"+namePlayer+"'");
			while(result.next()){
                money.put(Database.FORTYCUBE.getDatabase().getString(result, 2), Database.FORTYCUBE.getDatabase().getDouble(result, 3));
			}
		} catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}

	public void getProductsPlayerFunction(){
        products.clear();
		try {
			ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_product WHERE pp_player = '"+namePlayer+"'");
			while(result.next()){
                products.add(new ProductsPlayers(result, namePlayer));
			}

            Collections.sort(products, new Comparator<ProductsPlayers>() {
                @Override
                public int compare(ProductsPlayers p1, ProductsPlayers p2) {
                    return Long.compare(p2.getDate(), p1.getDate());
                }
            });
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
	}

	public void getBonusFunction(){
        try {
            bonus = Bonus.getBonus(Database.FORTYCUBE.getDatabase().getString(getPlayerDatabases(), 3));
        } catch (SqlException e){
            e.printStackTrace();
        }
	}

	public void setBuild(boolean state){
        try {
            if(state){
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_build = '"+1+"' WHERE ps_player = '"+namePlayer+"'");
            } else {
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_build = '"+0+"' WHERE ps_player = '"+namePlayer+"'");
            }
            build = state;
        } catch (SqlException e){
            e.printStackTrace();
            build = !state;
        }
	}
	
	public void setDoubleJumps(boolean state){
        try {
		    if(state){
			    Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_double_jump = '"+1+"' WHERE ps_player = '"+namePlayer+"'");
		    } else {
			    Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_double_jump = '"+0+"' WHERE ps_player = '"+namePlayer+"'");
		    }
            doubleJumps = state;
        } catch (SqlException e){
            e.printStackTrace();
            doubleJumps = !state;
        }
	}
	
	public void setFly(boolean state){
        try {
            if(state){
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_fly = '"+1+"' WHERE ps_player = '"+namePlayer+"'");
            } else {
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_fly = '"+0+"' WHERE ps_player = '"+namePlayer+"'");
            }
            fly = state;
        } catch (SqlException e){
            e.printStackTrace();
            fly = !state;
        }
	}
	
	public void setVisiblePlayer(boolean state){
        try {
            if(state){
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_visible_player = '"+1+"' WHERE ps_player = '"+namePlayer+"'");
            } else {
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player_setting SET ps_visible_player = '"+0+"' WHERE ps_player = '"+namePlayer+"'");
            }
            visiblePlayer = state;
        } catch (SqlException e){
            e.printStackTrace();
            visiblePlayer = !state;
        }
	}
	
	public void getBuildFunction(){
		try {
			build = Database.FORTYCUBE.getDatabase().getBoolean(getPlayerDatabases(), 5);
		} catch (SqlException e) {
            e.printStackTrace();
        }
		if(build){
			Bukkit.getPlayer(namePlayer).getInventory().clear();
         	Bukkit.getPlayer(namePlayer).setGameMode(GameMode.CREATIVE);
		}
	}

	public void getDoubleJumpsFunction(){
		try {
			doubleJumps = Database.FORTYCUBE.getDatabase().getBoolean( getPlayerDatabases(), 7);
		} catch (SqlException e) {
            e.printStackTrace();
        }
		if(doubleJumps){
			Bukkit.getPlayer(namePlayer).setAllowFlight(true);
		}
	}
	
	public void getFlyFunction(){
		try {
			fly = Database.FORTYCUBE.getDatabase().getBoolean(getPlayerDatabases(), 6);
		} catch (SqlException e) {
            e.printStackTrace();
        }
		if(fly){
			Bukkit.getPlayer(namePlayer).setAllowFlight(true);
		} else {
			getDoubleJumpsFunction();
		}
	}
	
	public void getVisiblePlayerFunction(){
		try {
			visiblePlayer = Database.FORTYCUBE.getDatabase().getBoolean(getPlayerDatabases(), 4);
		} catch (SqlException e) {
            e.printStackTrace();
		}
		if(!visiblePlayer){
			Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPlayer(namePlayer).hidePlayer(p));
		}
	}

	public void teleportSpawn(){
		 Location spawn = Settings.getSpawnHub();
		 spawn.setWorld(Bukkit.getPlayer(namePlayer).getWorld());
		 Bukkit.getPlayer(namePlayer).teleport(spawn);
	}

	public void initVariablePlayer(){
        getMoneyFunction();
        getProductsPlayerFunction();
        Bukkit.getPlayer(namePlayer).setAllowFlight(false);
        Navigator.navigator(this, Bukkit.getPlayer(namePlayer));
		getBuildFunction();
		getFlyFunction();
		getVisiblePlayerFunction();
		locCheckPoint = null;
		jump = null;
		inJumps = false;
		numberCheckPoint = 0;
		numberTrying = 0;
		nbrClickVisiblePlayer = 0;
		nbrDoubleJumps = 0;
		namePlayerMessage = null;
	}

	public void updateFriends(){
		friends = new Friends(namePlayer);
	}
	
	public void updateParty(){
		party = new Party(namePlayer);
	}


	public void updateGrades(){
		try {
			grade = Grades.getGrades(Database.FORTYCUBE.getDatabase().getString(getPlayerDatabases(), 2));
		} catch (SqlException e){
            e.printStackTrace();
        }

		Hub.getInstance().getScoreBoard().addPlayerTeam(grade.getGrade(), Bukkit.getPlayer(namePlayer));
		Grades gradePlayer = Grades.getGradesList().get(grade.getGrade());
        PermissionsPlayer.permissionGradeInit(this);
		if(!gradePlayer.getGradeDisplay().equals("")){
			if(!gradePlayer.getGradeAlias().equals("")){
				Bukkit.getPlayer(namePlayer).setPlayerListName(gradePlayer.getColorGrade()+gradePlayer.getGradeAlias()+" "+namePlayer);
			} else {
				Bukkit.getPlayer(namePlayer).setPlayerListName(gradePlayer.getColorGrade()+""+namePlayer);
			}
			Bukkit.getPlayer(namePlayer).setDisplayName(gradePlayer.getColorGrade()+gradePlayer.getGradeDisplay()+" "+namePlayer);
			Bukkit.getPlayer(namePlayer).setCustomName(gradePlayer.getColorGrade()+gradePlayer.getGradeDisplay()+" "+namePlayer);
			Bukkit.getPlayer(namePlayer).setCustomNameVisible(true);
		} else {
			if(!gradePlayer.getGradeAlias().equals("")){
				Bukkit.getPlayer(namePlayer).setPlayerListName(gradePlayer.getColorGrade()+gradePlayer.getGradeAlias()+" "+namePlayer);
			} else {
				Bukkit.getPlayer(namePlayer).setPlayerListName(gradePlayer.getColorGrade()+""+namePlayer);
			}
			Bukkit.getPlayer(namePlayer).setDisplayName(gradePlayer.getColorGrade()+""+namePlayer);
			Bukkit.getPlayer(namePlayer).setCustomName(gradePlayer.getColorGrade()+""+namePlayer);
			Bukkit.getPlayer(namePlayer).setCustomNameVisible(true);
		}
		Navigator.profil(this, Bukkit.getPlayer(namePlayer), null);
	}

	public void displayMessage(){
		String friendsOnlineMessage = "§eAmis en ligne : §a["; String friendsOnlineMessageTmp = friendsOnlineMessage;
		String membersOnlineMessage = "§eMembres en ligne : §a["; String membersOnlineMessageTmp = membersOnlineMessage;
		
		String friendsPendingMessage = "§eAmis en attente : ["; String friendsPendingMessageTmp = friendsPendingMessage;
		String friendsAcceptMessage = "§eNouveau amis : ["; String friendsAcceptMessageTmp = friendsAcceptMessage;
		
		String membersPendingMessage = "§eMembres en attente : ["; String membersPendingMessageTmp = membersPendingMessage;
		String membersAcceptMessage = "§eNouveau membres : ["; String membersAcceptMessageTmp = membersAcceptMessage;
        if(friends.getFriends().size() > 0){
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴§e Amis en ligne : §a"+friends.getFriendsOnLineList().size()+"§e/§b"+friends.getFriends().size());
            for(String friendsOnLine : friends.getFriendsOnLineList()){
                friendsOnlineMessageTmp += "§a"+friendsOnLine+"§e,";
            }
            if(!friendsOnlineMessage.equals(friendsOnlineMessageTmp)){
                Bukkit.getPlayer(namePlayer).sendMessage(friendsOnlineMessageTmp.substring(0, friendsOnlineMessageTmp.length()-1)+"§e].");
            }

            for(String pendingFriends : friends.getPendingFriendsList()){
                ResultSet resultSet;
                try {
                    resultSet = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM friends WHERE f_name = '" + pendingFriends + "' && f_friend = '" + namePlayer + "'");
                    resultSet.next();
                    if (Database.FORTYCUBE.getDatabase().getInt(resultSet, 3) == 1) {
                        friendsAcceptMessageTmp += "§a" + pendingFriends + "§e,";
                        Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE friends SET f_state = '" + 1 + "', f_date = '" + new Timestamp(System.currentTimeMillis()) + "' WHERE f_name = '" + namePlayer + "' && f_friend = '" + pendingFriends + "'");
                    } else {
                        friendsPendingMessageTmp += "§a" + pendingFriends + "§e,";
                    }
                } catch (SQLException e){
                    //
                } catch (SqlException e) {
                    e.printStackTrace();
                }
            }
        }
        boolean state = false;
        if(!friendsAcceptMessage.equals(friendsAcceptMessageTmp)){
            if(friends.getFriends().size() == 0){
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            }
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴"+friendsAcceptMessageTmp.substring(0, friendsAcceptMessageTmp.length()-1)+"]");
            state = true;
        }
        if(!friendsPendingMessage.equals(friendsPendingMessageTmp)){
            if(friends.getFriends().size() == 0 && !state){
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            }
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴"+friendsPendingMessageTmp.substring(0, friendsPendingMessageTmp.length()-1)+"]");
            state = true;
        }

        for(String friendsRequest : friends.getFriendsRequestList()){
            if(friends.getFriends().size() == 0 && !state){
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            }
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴ §2"+friendsRequest+"§e vous a demandé en ami.");
            ChatInteract.chatInteractAcceptAmi(Bukkit.getPlayer(namePlayer), friendsRequest);
            state = true;
        }

        if(friends.getFriends().size() == 0){
            if(state){
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            }
        } else {
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
        }
        if(party.getMembers().size() > 0){
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴ §eMembres en ligne : §a"+party.getMembersOnLineList().size()+"§e/§b"+party.getMembers().size());
            for(String membersOnline : party.getMembersOnLineList()){
                if(!membersOnline.equals(namePlayer)){
                    membersOnlineMessageTmp += "§a"+membersOnline+"§e,";
                }
            }

            if(!membersOnlineMessage.equals(membersOnlineMessageTmp)){
                Bukkit.getPlayer(namePlayer).sendMessage(membersOnlineMessageTmp.substring(0, membersOnlineMessageTmp.length()-1)+"§e].");
            }

            for(String pendingMember : party.getPendingMembersList()){
                ResultSet result;
                try {
                    result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM party WHERE party_leader = '" + namePlayer + "' && party_member = '" + pendingMember + "'");
                    result.next();
                    if (Database.FORTYCUBE.getDatabase().getInt(result, 3) == 1) {
                        membersAcceptMessageTmp += "§a" + pendingMember + "§e,";
                        Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE party SET party_state = '" + 1 + "', party_date = '" + new Timestamp(System.currentTimeMillis()) + "' WHERE party_leader = '" + namePlayer + "' && party_member = '" + pendingMember + "'");
                    } else {
                        membersPendingMessageTmp += "§a" + pendingMember + "§e,";
                    }
                } catch (SQLException e){
                    //
                } catch (SqlException e) {
                    e.printStackTrace();
                }
            }
        }
        state = false;
        if(!membersAcceptMessage.equals(membersAcceptMessageTmp)){
            if(party.getMembers().size() > 0){
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            }
            Bukkit.getPlayer(namePlayer).sendMessage(membersAcceptMessageTmp.substring(0, membersAcceptMessageTmp.length()-1)+"]");
            state = true;
        }
        if(!membersPendingMessage.equals(membersPendingMessageTmp)){
            if(party.getMembers().size() > 0){
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            }
            Bukkit.getPlayer(namePlayer).sendMessage(membersPendingMessageTmp.substring(0, membersPendingMessageTmp.length()-1)+"]");
            state = true;
        }

        for(String membersRequest : party.getMembersRequestList()){
            if(party.getMembers().size() > 0){
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            }
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴§2"+membersRequest+"§e vous a invité dans sa party.");
            ChatInteract.chatInteractAcceptParty(Bukkit.getPlayer(namePlayer), membersRequest);
            state = true;
        }
		if(Mute.isMute(namePlayer)){
            Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
            Bukkit.getPlayer(namePlayer).sendMessage(Mute.getMute(namePlayer).toString());
            Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
		}
        if(party.getMembers().size() == 0){
            if(state){
                if(Mute.isMute(namePlayer)){
                    Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
                } else {
                    Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
                }
            }
        } else {
            if(Mute.isMute(namePlayer)){
                Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
            } else {
                Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            }
        }

		if(Data.isMessageGrade(grade.getGrade())){
			Bukkit.getPlayer(namePlayer).sendMessage("§5✴--------------------------------------------------✴");
			Bukkit.getPlayer(namePlayer).sendMessage(Data.getMessageGrade(grade.getGrade()));
		}
        state = false;
		if(build){
			state = true;
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Mode build (§aActivé§6).");
		}
		if(doubleJumps){
			if(!state){
				Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
			}
			state = true;
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Mode doubleJump (§aActivé§6).");
		}
		if(fly){
			if(!state){
				Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
			}
			state = true;
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Mode fly (§aActivé§6).");
		}

		if(!visiblePlayer){
			if(!state){
				Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
			}
			state = true;
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Joueur (§4Désactivé§6).");
		}
		if(state) {
            Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
        }
		friends.getFriends().forEach((String nameFriend) -> {
			Bukkit.getPlayer(nameFriend).sendMessage("§eVotre amis §a"+namePlayer+"§e vient de se connecté.");
		});

		party.getMembers().forEach((String nameMember) -> {
			Bukkit.getPlayer(nameMember).sendMessage("§eLe membre §a"+namePlayer+"§e vient de se connecté");
		});
	}
	
	public void removePlayerHub(boolean state){
		Bukkit.getPlayer(namePlayer).getInventory().clear();
		if(state){
			playersData.remove(namePlayer);
		}
	}

	private ResultSet getPlayerDatabases() throws SqlException {
        ResultSet result = null;
        try {
            result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_setting WHERE ps_player = '" + namePlayer + "'");
            result.next();
        } catch (SQLException e){
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
        return result;
	}

	public void visiblePlayer(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
		new CoolDowns(namePlayer, Settings.getCoolDownVisible(), "visible_player");
		ThreadCoolDowns.getCooldownsThread().put(namePlayer, new ThreadCoolDowns(player));
		ThreadCoolDowns.getCooldownsThread().get(namePlayer).start();
		if(playerData.isVisiblePlayer()){
			playerData.setVisiblePlayer(false);
			Bukkit.getOnlinePlayers().forEach(p -> player.hidePlayer(p));
			player.sendMessage("§e§lLes joueurs sont désormais §cinvisibles§e.");
			player.playSound(player.getLocation(), Sound.CAT_HISS, 100, 10);

		} else {
			playerData.setVisiblePlayer(true);
			Bukkit.getOnlinePlayers().forEach(p -> player.showPlayer(p));
			player.sendMessage("§e§lLes joueurs sont désormais §avisibles§e.");
			player.playSound(player.getLocation(), Sound.CAT_MEOW, 100, 10);
		}
		Navigator.playerVisible(this, Bukkit.getPlayer(namePlayer));
	}
}
