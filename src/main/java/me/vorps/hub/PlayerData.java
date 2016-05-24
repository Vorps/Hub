package me.vorps.hub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import me.vorps.fortycube.cooldown.CoolDowns;
import me.vorps.fortycube.utils.TabList;
import me.vorps.fortycube.utils.Title;
import me.vorps.hub.Object.*;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.dispatcher.Dispatcher;
import me.vorps.hub.scoreboard.ScoreBoard;
import me.vorps.hub.thread.ClassThread;
import me.vorps.hub.thread.ThreadCoolDownsVisiblePlayer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.vorps.hub.menu.Navigator;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class PlayerData {
    private static @Getter HashMap<String, PlayerData> playersData = new HashMap<>();

	private @Getter String namePlayer;
	private @Getter @Setter int nbrClickVisiblePlayer;
	private @Getter @Setter int nbrDoubleJumps;
	private @Getter Party party;
	private @Getter Friends friends;
	private @Getter Grades grade;
	private @Getter Bonus bonus;
	private @Getter boolean visiblePlayer;
	private @Getter boolean build;
	private @Getter boolean fly;
	private @Getter boolean doubleJumps;
    private @Getter boolean chat;
	private @Getter @Setter String namePlayerMessage;
	private @Getter HashMap<String, Double> money;
	private @Getter ArrayList<ProductsPlayers> products;
    private ArrayList<String> notification;
    private @Setter Mute mute;
    private @Getter String lang;
    private @Getter me.vorps.fortycube.scoreboard.ScoreBoard scoreBoard;
    private @Setter @Getter ClassThread file;
    private @Getter @Setter PlayerJump jump;

	public PlayerData(Player player){
		this.namePlayer = player.getName();
        updateLang();
		updateFriends();
		updateParty();
        setScoreBoard(player, new ScoreBoard(this));
        initVariablePlayer();
	    TabList.setPlayerList(player, Settings.getMessageTabListHeader(), Settings.getMessageTabListFooter());
		teleportSpawn();
        player.setFoodLevel(20);
        player.setHealth(20);
        String hub = Data.NB_SERVER < 10 ? "HUB_0"+Data.NB_SERVER : "HUB_"+Data.NB_SERVER;
        try {
            Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player SET p_hub = '"+hub+"' WHERE p_name = '"+player.getName()+"'");
            ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player WHERE p_name = '"+namePlayer+"'");
            result.next();
            if(!result.getBoolean(5)){
                Database.FORTYCUBE.getDatabase().sendDatabase("UPDATE player SET p_online = '1', p_hub = '"+hub+"' WHERE p_name = '"+player.getName()+"'");
                new Title(Settings.getWelcome_MsgTitle(), Settings.getWelcome_msgSubTitle()).send(player);
                displayMessage();
            }
        } catch (SQLException e){
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
        playersData.put(namePlayer , this);
        Dispatcher.updateListServer(player);
	}

    public void setScoreBoard(Player player , me.vorps.fortycube.scoreboard.ScoreBoard scoreBoard){
        this.scoreBoard = scoreBoard;
        player.setScoreboard(scoreBoard.getScoreBoard());
    }

    private void updateLang(){
        try {
            ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_setting WHERE ps_player = '" + namePlayer + "'");
            result.next();
            lang = Database.FORTYCUBE.getDatabase().getString(result, 16);
        } catch (SQLException e){
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
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
        money = new HashMap<>();
		try {
			ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_money WHERE pm_player = '"+namePlayer+"'");
			while(result.next()){
                money.put(Database.FORTYCUBE.getDatabase().getString(result, 2), Database.FORTYCUBE.getDatabase().getDouble(result, 3));
                Navigator.navigator(this, Bukkit.getPlayer(namePlayer));
			}
		} catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
        if(!jump.isInJump()){
            int i = 9;
            for(Money money : Money.getListMoney().values()){
                scoreBoard.add(money.getMoney(), " §7"+money.getMoney()+" : "+money.getColor()+ this.money.get(money.getMoney())+" "+money.getAlias(), i);
                i--;
            }
        }
	}

	public void getProductsPlayerFunction(){
        products = new ArrayList<>();
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
            setBonus(Database.FORTYCUBE.getDatabase().getString(getPlayerDatabases(), 3), true);
        } catch (SqlException e){
            e.printStackTrace();
        }
	}

    public void setBonus(String bonus, boolean state){
        this.bonus = Bonus.getBonus(bonus);
        if(!jump.isInJump() && !bonus.equals(Settings.getDefaultBonus().getBonus())){
            if (state){
                scoreBoard.add("bonus", " §eBonus : §a"+ bonus, 2);
            } else {
                scoreBoard.remove("bonus");
            }
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

    public void getChatFunction(){
        try {
            chat = Database.FORTYCUBE.getDatabase().getBoolean(getPlayerDatabases(), 8);
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }

    public void getMute(){
        ResultSet result;
        try {
            result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM mute WHERE mute_name = '"+namePlayer+"'");
            if(result.next()){
                mute = new Mute(result);
            } else {
                mute = null;
            }
        } catch (SQLException e) {
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
    }

    public void getNotification(){
        notification = new ArrayList<>();
        try {
            ResultSet results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM notification WHERE n_player = '" + namePlayer + "'");
            while (results.next()) {
                notification.add(Database.FORTYCUBE.getDatabase().getString(results, 2));
            }
        } catch (SQLException e){
            //
        } catch (SqlException e) {
            e.printStackTrace();
        }
    }


	public void teleportSpawn(){
        if(jump.isInJump()){
            jump.stopJump( Bukkit.getPlayer(namePlayer), true);
        }
        Bukkit.getPlayer(namePlayer).teleport(Settings.getSpawnHub());
	}

	public void initVariablePlayer(){
        jump = new PlayerJump();
        getBonusFunction();
        getMoneyFunction();
        updateGrades();
        getProductsPlayerFunction();
        Bukkit.getPlayer(namePlayer).setAllowFlight(false);
        Navigator.navigator(this, Bukkit.getPlayer(namePlayer));
		getBuildFunction();
		getFlyFunction();
		getVisiblePlayerFunction();
        getChatFunction();
        getNotification();
        getMute();
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
        Permission.permissionGradeInit(this);
        Bukkit.getPlayer(namePlayer).setPlayerListName(grade.toString()+" "+namePlayer);
		Navigator.profil(this, Bukkit.getPlayer(namePlayer), null);
        if(jump.isInJump()){
            scoreBoard.add("grade", " §7Grade : "+grade, 10);
        }
	}

	public void displayMessage(){
        Bukkit.getPlayer(namePlayer).sendMessage("§b✴§3 Bonjour "+namePlayer);
        boolean stateFriends = false;
        boolean stateParty = false;
        boolean stateAction = false;
        boolean stateMuteBan = false;
        boolean stateNotification = false;
        boolean stateMMessageGrade = false;
        if(friends.getFriends().size() > 0) {
            stateFriends = true;
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            friends.list(Bukkit.getPlayer(namePlayer));
        }
        if(party.getMembers().size() > 0) {
            stateParty = true;
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
            party.list(Bukkit.getPlayer(namePlayer));
        }
		if(build){
			stateAction = true;
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Mode build (§aActivé§6).");
		}
		if(doubleJumps){
			if(!stateAction){
				Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
			}
            stateAction = true;
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Mode doubleJump (§aActivé§6).");
		}
		if(fly){
			if(!stateAction){
				Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
			}
            stateAction = true;
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Mode fly (§aActivé§6).");
		}
		if(!visiblePlayer){
			if(!stateAction){
				Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
			}
            stateAction = true;
			Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Joueur (§4Désactivé§6).");
		}
        if(!chat){
            if(!stateAction){
                Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
            }
            stateAction = true;
            Bukkit.getPlayer(namePlayer).sendMessage("§c✴§6Chat (§4Désactivé§6).");
        }

        if(mute != null){
            stateMuteBan = true;
            Bukkit.getPlayer(namePlayer).sendMessage("§4✴--------------------------------------------------✴");
            Bukkit.getPlayer(namePlayer).sendMessage("§cVous avez "+mute.toString());
        }

        if(!notification.isEmpty()){
            stateNotification = true;
            Bukkit.getPlayer(namePlayer).sendMessage("§a✴--------------------------------------------------✴");
            notification.forEach((String notificationPlayer) -> Bukkit.getPlayer(namePlayer).sendMessage(notificationPlayer));
        }
        try {
            Database.FORTYCUBE.getDatabase().sendDatabase("DELETE FROM notification WHERE n_player = '" + namePlayer + "'");
        } catch (SqlException e){
            e.printStackTrace();
        }
        if(Data.isMessageGrade(grade.getGrade())){
            stateMMessageGrade = true;
            Bukkit.getPlayer(namePlayer).sendMessage("§5✴--------------------------------------------------✴");
            Bukkit.getPlayer(namePlayer).sendMessage(Data.getMessageGrade(grade.getGrade()));
        }
        if((stateFriends || stateParty) && (!stateAction && !stateMuteBan && !stateNotification && !stateMMessageGrade)){
            Bukkit.getPlayer(namePlayer).sendMessage("§6✴--------------------------------------------------✴");
        } else if(stateAction && (!stateMuteBan && !stateNotification && !stateMMessageGrade)){
            Bukkit.getPlayer(namePlayer).sendMessage("§c✴--------------------------------------------------✴");
        } else if(stateMuteBan && (!stateNotification && !stateMMessageGrade)){
            Bukkit.getPlayer(namePlayer).sendMessage("§4✴--------------------------------------------------✴");
        } else if(stateNotification && !stateMMessageGrade){
            Bukkit.getPlayer(namePlayer).sendMessage("§a✴--------------------------------------------------✴");
        } else if(stateMMessageGrade){
            Bukkit.getPlayer(namePlayer).sendMessage("§5✴--------------------------------------------------✴");
        }
        friends.getFriendsOnline().forEach((String nameFriend) -> Bukkit.getPlayer(nameFriend).sendMessage("§eVotre amis §a"+namePlayer+"§e vient de se connecté."));
        party.getMembersOnline().forEach((String nameMember) -> Bukkit.getPlayer(nameMember).sendMessage("§eLe membre §a"+namePlayer+"§e vient de se connecté"));
	}
	
	public void removePlayerHub(boolean state){
		Bukkit.getPlayer(namePlayer).getInventory().clear();
		if(state){
			playersData.remove(namePlayer);
		}
	}

	private ResultSet getPlayerDatabases() throws SqlException {
        try {
            ResultSet result = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM player_setting WHERE ps_player = '" + namePlayer + "'");
            result.next();
            return result;
        } catch (SQLException e){
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
        return null;
	}

	public void visiblePlayer(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
		new CoolDowns(namePlayer, Settings.getCoolDownVisible(), "visible_player");
		ThreadCoolDownsVisiblePlayer.getCooldownsThread().put(namePlayer, new ThreadCoolDownsVisiblePlayer(player));
        ThreadCoolDownsVisiblePlayer.getCooldownsThread().get(namePlayer).start();
		if(playerData.isVisiblePlayer()){
			Bukkit.getOnlinePlayers().forEach((Player playerVisible) -> {
                if(!(playerData.getFriends().getFriendsOnline().contains(playerVisible.getName())
                        || playerData.getParty().getMembersOnline().contains(playerVisible.getName())
                        || PlayerData.getPlayerData(playerVisible.getName()).grade.isVisibleGrade())
                ){
                    player.hidePlayer(playerVisible);
                }
            });
			player.sendMessage("§eLes joueurs sont désormais §cinvisibles§e.");
			player.playSound(player.getLocation(), Sound.CAT_HISS, 100, 10);
		} else {
			Bukkit.getOnlinePlayers().forEach((Player playerVisible) -> player.showPlayer(playerVisible));
			player.sendMessage("§eLes joueurs sont désormais §avisibles§e.");
			player.playSound(player.getLocation(), Sound.CAT_MEOW, 100, 10);
		}
        playerData.visiblePlayer = !playerData.visiblePlayer;
		Navigator.playerVisible(this, Bukkit.getPlayer(namePlayer));
	}
}
