package sirmc.vorps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import sirmc.vorps.Object.Bonus;
import sirmc.vorps.Object.Jumps;
import sirmc.vorps.Object.Money;
import sirmc.vorps.Object.Products;
import sirmc.vorps.commands.*;
import sirmc.vorps.database.Database;
import sirmc.vorps.events.*;
import sirmc.vorps.thread.ThreadCooldowns;
import sirmc.vorps.thread.ThreadHub;
import sirmc.vorps.utils.Permission;
import sirmc.vorps.utils.ScoreBoard;

public class Hub extends JavaPlugin{
	public static Hub instance;

	private final @Getter int NUMBRE_HUB= 1;
	private final @Getter DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");

	private @Getter	HashMap<String, String> messageGradePlayer = new HashMap<>();
	private @Getter ArrayList<Commands> listCommands = new ArrayList<>();
	private @Getter HashMap<String, Products> listProducts= new HashMap<>();
	private @Getter HashMap<String, ArrayList<String>> listProductsGrade = new HashMap<>();
	private @Getter ArrayList<Money> listMoney = new ArrayList<>();
	private @Getter ArrayList<Bonus> listBonus = new ArrayList<>();
	private @Getter ArrayList<Jumps> listJumps = new ArrayList<>();
	private @Getter HashMap<String, PlayerHub> playerHub = new HashMap<>();
	private @Getter HashMap<String, Settings> settings = new HashMap<>();
	private @Getter ArrayList<String> listPlayer = new ArrayList<>();
	private @Getter ArrayList<String> listMute = new ArrayList<>();
	private @Getter ArrayList<String> listBan = new ArrayList<>();
	private @Getter HashMap<String, ThreadCooldowns> cooldownsThread = new HashMap<>();
	public 	Database database;
	private @Getter
	Permission permission;
	private ThreadHub threadHub;
	private @Getter Location spawnHub;
	private @Getter boolean interrupt;
	private @Getter ScoreBoard scoreBoard;

	@Override
	public void onEnable() {
		instance = this;

		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getServer().getPluginManager().registerEvents(new CommandPreprocessListener(), this);
		getServer().getPluginManager().registerEvents(new DamageListener(), this);
		getServer().getPluginManager().registerEvents(new DropPlayerListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
		getServer().getPluginManager().registerEvents(new FlyOnJumpListener(), this);
		getServer().getPluginManager().registerEvents(new InteractListener(), this);
		getServer().getPluginManager().registerEvents(new InterractInventoryListener(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new QuitListener(), this);
		getServer().getPluginManager().registerEvents(new WeatherListener(), this);
		getServer().getPluginManager().registerEvents(new FoodChangeLevelListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);



		database = new Database();
		permission = new Permission(this);
		LoadVariable.LoadVariables(this);
		spawnHub = new Location(null, 8.5, 70, -1.5, -90, 0);
		scoreBoard = new ScoreBoard();
		Bukkit.getOnlinePlayers().forEach(p -> playerHub.put(p.getName(),  new PlayerHub(p)));
		Hub.instance.getScoreBoard().updatePlayer();
		threadHub = new ThreadHub();
		threadHub.start();
	}
	
	@Override
	public void onDisable() {
		interrupt = true;
		threadHub.interrupt();
		Bukkit.getOnlinePlayers().forEach(p -> playerHub.get(p.getName()).removePlayerHub(false));
		Hub.instance.getScoreBoard().updatePlayer();
		playerHub.clear();
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender , Command command, String label, String args[]){
		if(sender instanceof Player){
			Player player = (Player) sender;
			PlayerHub playerHub = Hub.instance.getPlayerHub().get(player.getName());
			if(label.equalsIgnoreCase("build")){
				return CommandBuild.CommandBuild(args, playerHub);
			}

			if(label.equalsIgnoreCase("friends")){
				return CommandFriends.CommandFriends(args, playerHub);
			}

			if(label.equalsIgnoreCase("party")){
				return CommandParty.CommandParty(args, playerHub);
			}

			if(label.equalsIgnoreCase("grade")){
				return CommandGrade.CommandGrade(args, playerHub);
			}

			if(label.equalsIgnoreCase("fly")){
				return CommandFly.CommandFly(args, playerHub);
			}

			if(label.equalsIgnoreCase("mute")){
				return CommandMute.CommandMute(args, playerHub);
			}

			if(label.equalsIgnoreCase("unmute")){
				return CommandUnMute.CommandUnMute(args, playerHub);
			}

			if(label.equalsIgnoreCase("hub")){
				return CommandHub.CommandHub(args, playerHub);
			}

			if(label.equalsIgnoreCase("jump")){
				return CommandDoubleJumps.CommandDoubleJumps(args, playerHub);
			}

			if(label.equalsIgnoreCase("whisper")){
				return CommandWhisper.CommandWhisper(args, playerHub);
			}

			if(label.equalsIgnoreCase("resend")){
				return CommandReSend.CommandReSend(args, playerHub);
			}

			if(label.equalsIgnoreCase("joueur")){
				return CommandVisiblePlayer.CommandVisiblePlayer(args, playerHub);
			}

			if(label.equalsIgnoreCase("reloadHub")){
				return CommandReload.CommandHubReload(args, playerHub, this);
			}

			if(label.equalsIgnoreCase("money")){
				return CommandMoney.CommandMoney(args, playerHub);
			}
		}
		return false;
	}

}
