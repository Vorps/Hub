package me.vorps.hub;

import lombok.Getter;
import me.vorps.hub.Object.Bonus;
import me.vorps.hub.Object.Grades;
import me.vorps.hub.Object.Money;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.hub.thread.ThreadCoolDownsVisiblePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class
Settings {

    private static @Getter HashMap<String, Settings> settings = new HashMap<>();
    private static @Getter HashMap<String, Integer> defaultAmountMoney = new HashMap<>();

    private @Getter HashMap<String, ThreadCoolDownsVisiblePlayer> coolDownsThread = new HashMap<>();

    private static @Getter Bonus defaultBonus;
    private static @Getter Grades defaultGrade;
    private static @Getter String defaultLang;
	private static @Getter int speedThread;
	private static @Getter int coolDownVisible;
	private static @Getter int coolDownDoubleJump;
	private static @Getter int nbrClickMax;
	private static @Getter int TimeConfusion;
	private static @Getter int TimeMessage;
	private static @Getter int NbrDoubleJumpsMax;
	private static @Getter String nameServer;
	private static @Getter String ip;
	private static @Getter ArrayList<String> messageServer = new ArrayList<>();
	private static @Getter String welcome_MsgTitle;
	private static @Getter String welcome_msgSubTitle;
	private static @Getter String messageTabListHeader;
	private static @Getter String messageTabListFooter;
	private static @Getter double xMore;
    private static @Getter double xLess;
    private static @Getter double yMore;
    private static @Getter double yLess;
    private static @Getter double zMore;
    private static @Getter double zLess;
    private static @Getter Location spawnHub;
    private static @Getter String siteWeb;

	public boolean valueBoolean;
	private int valueInt;
    private String message;
    private double valueDouble;

	public Settings(ResultSet result) throws SqlException {
        String nameSetting = Database.FORTYCUBE.getDatabase().getString(result, 1);
		valueInt = Database.FORTYCUBE.getDatabase().getInt(result, 3);
		message = Database.FORTYCUBE.getDatabase().getString(result, 4);
        valueDouble = Database.FORTYCUBE.getDatabase().getDouble(result, 5);
        if(!settings.containsKey(nameSetting)){
            settings.put(Database.FORTYCUBE.getDatabase().getString(result, 1), this);
        } else {
            settings.replace(nameSetting, this);
        }
	}

	public static void initSettings(){
		nameServer = Settings.settings.get("name_server").message;
		ip = Settings.settings.get("ip").message;
		messageServer.clear();
		for (int i = 0; Settings.settings.containsKey("message_server_" + i); i++) {
			messageServer.add(Settings.settings.get("message_server_" + i).message);
		}
		welcome_MsgTitle = Settings.settings.get("welcome_msgtitle").message;
		welcome_msgSubTitle = Settings.settings.get("welcome_msgsubtitle").message;
		messageTabListHeader = Settings.settings.get("message_tablist_header").message;
		messageTabListFooter = Settings.settings.get("message_tablist_footer").message;
		speedThread = Settings.settings.get("speed_thread").valueInt;
        coolDownVisible = Settings.settings.get("cooldown_visible_player").valueInt;
        coolDownDoubleJump = Settings.settings.get("cooldown_double_jump").valueInt;
		nbrClickMax = Settings.settings.get("click_max_visible_player").valueInt;
		TimeConfusion = Settings.settings.get("time_confusion").valueInt;
		TimeMessage = Settings.settings.get("time_message").valueInt;
		NbrDoubleJumpsMax = Settings.settings.get("double_jumps_max").valueInt;
		Money.getListMoney().values().forEach((Money money) -> {
			if (Settings.settings.containsKey(money.getMoney())) {
                defaultAmountMoney.put("default_"+money.getMoney().toLowerCase(), Settings.settings.get(money.getMoney()).valueInt);
			}
		});
        xMore = Settings.settings.get("distance_x+").valueDouble;
        xLess = Settings.settings.get("distance_x-").valueDouble;
        yMore = Settings.settings.get("distance_y+").valueDouble;
        yLess = Settings.settings.get("distance_y-").valueDouble;
        zMore = Settings.settings.get("distance_z+").valueDouble;
        zLess = Settings.settings.get("distance_z-").valueDouble;
        spawnHub = new Location(Bukkit.getWorlds().get(0), Settings.settings.get("spawn_x").valueDouble, Settings.settings.get("spawn_y").valueDouble, Settings.settings.get("spawn_z").valueDouble);
        siteWeb =  Settings.settings.get("site_web").message;
        defaultBonus =  Bonus.getBonus(Settings.settings.get("default_bonus").message);
        defaultGrade = Grades.getGrades(Settings.settings.get("default_grade").message);
        defaultLang = Settings.settings.get("default_lang").message;
    }
}
