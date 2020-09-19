package me.vorps.hub;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import me.vorps.hub.data.SettingsHub;
import net.vorps.api.data.Settings;
import net.vorps.api.lang.Lang;
import net.vorps.api.nms.TablistBuilder;
import me.vorps.hub.Object.*;
import net.vorps.api.databases.Database;
import me.vorps.hub.gadget.Gadgets;
import me.vorps.hub.particle.Particle;
import me.vorps.hub.scoreboard.ScoreBoard;
import me.vorps.hub.thread.ClassThread;
import org.bukkit.*;

import me.vorps.hub.menu.Navigator;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class PlayerData extends net.vorps.api.players.PlayerData {

	private @Getter @Setter int nbrClickVisiblePlayer;
	private @Getter @Setter int nbrDoubleJumps;
	private @Getter boolean doubleJumps;
	private @Getter ArrayList<ProductsPlayers> products;
    private @Getter net.vorps.api.scoreboard.ScoreBoard scoreBoard;
    private @Setter @Getter ClassThread file;
    private @Getter @Setter PlayerJump jump;

    private Bonus bonus;
    private @Getter Particle particle;
    private @Getter Gadgets gadget;



	public PlayerData(java.util.UUID uuid){
        super(uuid, Bukkit.getPlayer(uuid).getName());

        this.setScoreBoard(uuid, new ScoreBoard(this.getUUID()));
        Navigator.navigator(this.UUID);
	    new TablistBuilder(SettingsHub.getMessageTabListHeader(), SettingsHub.getMessageTabListFooter()).sendTo(Bukkit.getPlayer(uuid));

        Bukkit.getPlayer(this.UUID).setAllowFlight(false);
        Bukkit.getPlayer(this.UUID).setFoodLevel(20);
        Bukkit.getPlayer(this.UUID).setHealth(20);

        String hub = "";//DataHub.NB_SERVER < 10 ? "HUB_0"+ DataHub.NB_SERVER : "HUB_"+ DataHub.NB_SERVER;
        //updateParticle();
        //updateGadget();
        this.spawn();
	}

    @Override
    public void init() {
        this.bonus = PlayerData.getBonus(this.UUID);
    }

	public static void updatePlayerDataDataBase(UUID uuid){
        try {
            ResultSet result = Database.HUB.getDatabase().getData("player_setting", "ps_uuid = '" + uuid.toString() + "'");
            if (!result.next()) {
                CallableStatement call = Database.HUB.getDatabase().getConnection().prepareCall("{call ps_create_user(?)}");
                call.setString(1, uuid.toString());
                call.execute();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public void spawn(){
        /*if(jump.isInJump()){
            jump.stopJump( Bukkit.getPlayer(namePlayer), true);
        }*/
        Bukkit.getPlayer(this.UUID).teleport(SettingsHub.getSpawn_location());
    }

    public static Bonus getBonus(UUID uuid) {
        Bonus bonus = null;
        if(net.vorps.api.players.PlayerData.isPlayerDataCore(uuid)){
            bonus = PlayerData.getPlayerData(uuid).bonus;
        } else {
            try {
                bonus = Bonus.getBonus(Database.HUB.getDatabase().getDataUnique("player_setting", "ps_uuid = '" + uuid + "'").getString("ps_bonus"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bonus;
    }

    /*public void setParticle(Particle particle){
        if(this.particle != null && !jump.isInJump()){
            this.particle.removeParticle();
        }
        if(!jump.isInJump() && particle != null){
            particle.start();
        }
        try {
            Database.CORE.getDatabase().updateTable("player_setting", "ps_player = '"+namePlayer+"'", new DatabaseManager.Values("ps_particle", particle));
        }catch (SqlException e){
            e.printStackTrace();
        }
        this.particle = particle;
    }

    public void setGadgets(Gadgets gadget){
        try {
            Database.CORE.getDatabase().updateTable("player_setting", "ps_player = '"+namePlayer+"'", new DatabaseManager.Values("ps_gadget", gadget));
        }catch (SqlException e){
            e.printStackTrace();
        }
        this.gadget = gadget;
    }

    private void updateParticle(){
        try {
            ResultSet result = Database.CORE.getDatabase().getData("player_setting", "ps_player = '" + namePlayer + "'");
            result.next();
            String particle = Database.CORE.getDatabase().getString(result, 17);
            if(particle != null){
                this.particle = new Particle(Bukkit.getPlayer(namePlayer), particle);
                this.particle.start();
            }
        } catch (SQLException e){
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
    }

    private void updateGadget(){
        try {
            ResultSet result = Database.CORE.getDatabase().getData("player_setting" ,"ps_player = '" + namePlayer + "'");
            result.next();
            String gadget = Database.CORE.getDatabase().getString(result, 18);
            if(gadget != null){
                me.vorps.hub.Object.Gadgets.getListGadgets().get(gadget).setGadgets(Bukkit.getPlayer(namePlayer), gadget);
            }
        } catch (SQLException e){
            //
        } catch (SqlException e){
            e.printStackTrace();
        }
    }
    */

    /*public void initVariablePlayer(){
        jump = new PlayerJump();
        //getBonusFunction();
        //getProductsPlayerFunction();

    }*/

    public void setScoreBoard(UUID uuid , net.vorps.api.scoreboard.ScoreBoard scoreBoard){
        this.scoreBoard = scoreBoard;
        Bukkit.getPlayer(uuid).setScoreboard(scoreBoard.getScoreBoard());
    }

    public static PlayerData getPlayerData(String name) {
        return (PlayerData) PlayerData.getPlayerDataCore(name);
    }

    public static PlayerData getPlayerData(UUID uuid) {
        return (PlayerData) PlayerData.getPlayerDataCore(uuid);
    }

	/*public void getProductsPlayerFunction(){
        products = new ArrayList<>();
		try {
			ResultSet result = Database.CORE.getDatabase().getData("player_product", "pp_player = '"+namePlayer+"'");
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
            setBonus(Database.CORE.getDatabase().getString(getPlayerDatabases(), 3));
        } catch (SqlException e){
            e.printStackTrace();
        }
	}

    public void setBonus(String bonus){
        this.bonus = Bonus.getBonus(bonus);
        if(scoreBoard instanceof ScoreBoard){
            ((ScoreBoard) scoreBoard).updateBonus();
        }
    }
	
	public void setDoubleJumps(boolean state){
        try {
            Database.CORE.getDatabase().updateTable("player_setting", "ps_player = '"+namePlayer+"'", new DatabaseManager.Values("ps_double_jump", state));
            doubleJumps = state;
        } catch (SqlException e){
            e.printStackTrace();
            doubleJumps = !state;
        }
	}

	public void getDoubleJumpsFunction(){
		try {
			doubleJumps = Database.CORE.getDatabase().getBoolean( getPlayerDatabases(), 7);
		} catch (SqlException e) {
            e.printStackTrace();
        }
		if(doubleJumps){
			Bukkit.getPlayer(namePlayer).setAllowFlight(true);
		}
	}





	public void displayMessage(){
        /*Bukkit.getPlayer(namePlayer).sendMessage("§b✴§3 Bonjour "+namePlayer);
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
            //party.list(Bukkit.getPlayer(namePlayer));
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
            Database.CORE.getDatabase().delete("notification", "n_player = '" + namePlayer + "'");
        } catch (SqlException e){
            e.printStackTrace();
        }
        if(DataHub.isMessageGrade(rank.getRank())){
            stateMMessageGrade = true;
            Bukkit.getPlayer(namePlayer).sendMessage("§5✴--------------------------------------------------✴");
            Bukkit.getPlayer(namePlayer).sendMessage(DataHub.getMessageGrade(rank.getRank()));
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
        //party.getMembers().forEach((String nameMember) -> Bukkit.getPlayer(nameMember).sendMessage("§eLe membre §a"+namePlayer+"§e vient de se connecté"));
	}*/
	
	public void removePlayerHub(){
        Bukkit.getPlayer(UUID).getInventory().clear();
        if(particle != null){
            particle.removeParticle();
        }
        super.removePlayerData();
	}

	/*public void visiblePlayer(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
		new CoolDowns(namePlayer, Settings.getCoolDownVisible(), "visible_player");
		ThreadCoolDownsVisiblePlayer.getCooldownsThread().put(namePlayer, new ThreadCoolDownsVisiblePlayer(player));
        ThreadCoolDownsVisiblePlayer.getCooldownsThread().get(namePlayer).start();
		if(playerData.isVisiblePlayer()){
			Bukkit.getOnlinePlayers().forEach((Player playerVisible) -> {
                if(!(playerData.getFriends().getFriendsOnline().contains(playerVisible.getName())
                        || playerData.getParty().getMembersOnline().contains(playerVisible.getName())
                        || PlayerData.getPlayerData(playerVisible.getName()).rank.isVisibleRank())
                ){
                    player.hidePlayer(Hub.getInstance(), playerVisible);
                }
            });
			player.sendMessage("§eLes joueurs sont désormais §cinvisibles§e.");
			player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 100, 10);
		} else {
			Bukkit.getOnlinePlayers().forEach((Player playerVisible) -> player.showPlayer(Hub.getInstance(), playerVisible));
			player.sendMessage("§eLes joueurs sont désormais §avisibles§e.");
			player.playSound(player.getLocation(), Sound.ENTITY_CAT_PURREOW, 100, 10);
		}
        playerData.visiblePlayer = !playerData.visiblePlayer;
		Navigator.playerVisible(this, Bukkit.getPlayer(namePlayer));
	}*/

    @Override
    public void sendMessage(String key, Lang.Args... args) {
        Bukkit.getPlayer(super.UUID).sendMessage(Lang.getMessage(key, PlayerData.getLang(super.UUID), args));
    }
}
