package me.vorps.hub.Object;

import lombok.Getter;
import me.vorps.fortycube.Exceptions.SqlException;
import me.vorps.fortycube.databases.Database;
import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.utils.Lang;
import me.vorps.fortycube.utils.LangSetting;
import me.vorps.hub.Settings;
import me.vorps.hub.utils.EntityManager;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Jumps{
    private static @Getter ArrayList<Jumps> listJumps = new ArrayList<>();

    private @Getter ArrayList<JumpsSettings> jumpsSettings;
    private @Getter JumpDifficulty[] jumpDifficulty;
    private @Getter HashMap<String, HashMap<Integer, Integer>> jumpDifficultySetting;
	private @Getter String jump;
    private @Getter HashMap<String, String> label;
	private @Getter Location jumpLocation;
    private @Getter Location jumpLocationPlayer;
    private @Getter HashMap<String ,Item> icon;
    private @Getter Menu menu;
    private @Getter HashMap<String, String> winMessage;
    private @Getter MessageTitle messageTitle;
	private @Getter HashMap<String, String> message;
    private @Getter HashMap<String, JumpEarning> jumpEarning;

    public static int nbrJump(){
        return listJumps.size();
    }

	public Jumps(ResultSet results) throws SqlException {
        icon = new HashMap<>();
        label = new HashMap<>();
        winMessage = new HashMap<>();
        message = new HashMap<>();
        jumpsSettings = new ArrayList<>();
        jumpDifficultySetting = new HashMap<>();
        jumpEarning = new HashMap<>();
		jump =  Database.FORTYCUBE.getDatabase().getString(results, 1);
		jumpLocation = me.vorps.hub.Object.Location.getLocation(Database.FORTYCUBE.getDatabase().getString(results, 3));
        jumpLocationPlayer = me.vorps.hub.Object.Location.getLocation(Database.FORTYCUBE.getDatabase().getString(results, 4));
        for(LangSetting langSetting : LangSetting.getListLangSetting().values()) {
            icon.put(langSetting.getName(), me.vorps.hub.Object.Item.getItem(Database.FORTYCUBE.getDatabase().getString(results, 5), langSetting.getName()));
            label.put(langSetting.getName(), Lang.getMessage(Database.FORTYCUBE.getDatabase().getString(results, 2), langSetting.getName()));
            winMessage.put(langSetting.getName(), Lang.getMessage(Database.FORTYCUBE.getDatabase().getString(results, 8), langSetting.getName()));
            message.put(langSetting.getName(), Lang.getMessage(Database.FORTYCUBE.getDatabase().getString(results, 7), langSetting.getName()));
        }
        menu = Menu.getMenu(Database.FORTYCUBE.getDatabase().getString(results, 6));
        messageTitle = MessageTitle.getMessageTitle(Database.FORTYCUBE.getDatabase().getString(results, 9));
        //EntityManager.entityManager(EntityType.VILLAGER, me.vorps.hub.Object.Location.getLocation(Database.FORTYCUBE.getDatabase().getString(results, 10)), label.get(Settings.getDefaultLang())+" §6Classement", Villager.Profession.LIBRARIAN);

        try {
			results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM jump_setting WHERE js_jump = '"+jump+"'");
			while(results.next()){
                jumpsSettings.add(new JumpsSettings(results));
			}
            results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM jump_difficulty_setting WHERE jds_jump = '"+jump+"' && jds_checkpoint = '"+1+"'");
            results.last();
            jumpDifficulty = new JumpDifficulty[results.getRow()];
            results.beforeFirst();
            for(int i = 0; results.next(); i++){
                jumpDifficulty[i] = JumpDifficulty.getJumpDifficulty(Database.FORTYCUBE.getDatabase().getString(results, 2));
            }
            Arrays.sort(jumpDifficulty);
            for(JumpDifficulty jumpDifficulty : this.jumpDifficulty){
                results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM jump_difficulty_setting WHERE jds_jump = '"+jump+"'  && jds_difficulty = '"+jumpDifficulty.getName()+"'");
                HashMap<Integer, Integer> difficultySettingsHashMap = new HashMap<>();
                while (results.next()){
                    difficultySettingsHashMap.put(Database.FORTYCUBE.getDatabase().getInt(results, 3), Database.FORTYCUBE.getDatabase().getInt(results, 4));
                }
                jumpDifficultySetting.put(jumpDifficulty.getName(), difficultySettingsHashMap);
            }
            results = Database.FORTYCUBE.getDatabase().getData("SELECT * FROM jump_earning WHERE je_jump = '"+jump+"'");
            while (results.next()){
                jumpEarning.put(Database.FORTYCUBE.getDatabase().getString(results, 2), new JumpEarning(results));
            }
		} catch (SQLException e) {
            //
        }
        listJumps.add(this);
	}

    public static Jumps getJump(String label, String lang){
        for (Jumps jumps : listJumps){
            if(jumps.icon.get(lang).get().getItemMeta().getDisplayName().equals(label)){
                return jumps;
            }
        }
        return null;
    }

    public static Jumps getJump(Location location){
        for(Jumps jump :  listJumps){
            if(location.getBlockX() == jump.jumpLocation.getBlockX() && location.getBlockY()  == jump.jumpLocation.getBlockY()  && location.getBlockZ()  == jump.jumpLocation.getBlockZ()){
                return jump;
            }
        }
        return null;
    }

    public int nbrChecpoint(){
        return jumpsSettings.size();
    }

    public String toString(String lang){
        return label.get(lang);
    }

    public static void clear(){
        listJumps.clear();
    }
}
