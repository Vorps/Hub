package sirmc.vorps;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Settings {
	private static @Getter HashMap<String, Integer> defaultMontantMoney = new HashMap<>();
	private static @Getter String defaultGrade;
	private static @Getter String defaultBonus;

	private static @Getter int speedThread;
	private static @Getter int cooldownVisible;
	private static @Getter int cooldownDoubleJump;
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

	public boolean valueBoolean;
	public int valueInt;
	public String message;

	public Settings(ResultSet result) throws SQLException{
		valueBoolean = result.getBoolean(2);
		valueInt = result.getInt(3);
		message = result.getString(4);
	}

	public static void initSettings(){
		nameServer = Hub.instance.getSettings().get("NameServer").message;
		ip = Hub.instance.getSettings().get("ip").message;
		messageServer.clear();
		for (int i = 0; Hub.instance.getSettings().containsKey("MessageServer_" + i); i++) {
			messageServer.add(Hub.instance.getSettings().get("MessageServer_" + i).message);
		}
		welcome_MsgTitle = Hub.instance.getSettings().get("welcome_MsgTitle").message;
		welcome_msgSubTitle = Hub.instance.getSettings().get("welcome_msgSubTitle").message;
		messageTabListHeader = Hub.instance.getSettings().get("messageTabListHeader").message;
		messageTabListFooter = Hub.instance.getSettings().get("messageTabListFooter").message;
		speedThread = Hub.instance.getSettings().get("speedThread").valueInt;
		cooldownVisible = Hub.instance.getSettings().get("cooldownVisible").valueInt;
		cooldownDoubleJump = Hub.instance.getSettings().get("cooldownDoubleJump").valueInt;
		nbrClickMax = Hub.instance.getSettings().get("nbrClickMax").valueInt;
		TimeConfusion = Hub.instance.getSettings().get("TimeConfusion").valueInt;
		TimeMessage = Hub.instance.getSettings().get("TimeMessage").valueInt;
		NbrDoubleJumpsMax = Hub.instance.getSettings().get("NbrDoubleJumpsMax").valueInt;
		defaultGrade = Hub.instance.getSettings().get("defaultGrade").message;
		for (int i = 0; i < Hub.instance.getListMoney().size(); i++) {
			if (Hub.instance.getSettings().containsKey(Hub.instance.getListMoney().get(i).getMoney())) {
				defaultMontantMoney.put(Hub.instance.getListMoney().get(i).getMoney(), Hub.instance.getSettings().get(Hub.instance.getListMoney().get(i).getMoney()).valueInt);
			}
		}
		defaultBonus = Hub.instance.getSettings().get("defaultBonus").message;

	}
}
