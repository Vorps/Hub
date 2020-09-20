package me.vorps.hub.Object;

import lombok.Getter;
import net.vorps.api.databases.Database;
import net.vorps.api.lang.Lang;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.objects.Item;
import net.vorps.api.objects.MessageTitle;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class Jumps{
    private static @Getter ArrayList<Jumps> listJumps = new ArrayList<>();

    private @Getter String name;
    private String label;
    private @Getter Location location;
    private @Getter Location playerLocation;
    private String icon;
    private @Getter Menu menu;
    private String message;
    private String winMessage;
    private @Getter MessageTitle messageTitle;

    private @Getter ArrayList<JumpsSettings> jumpsSettings;


    private @Getter JumpDifficulty[] jumpDifficulty;

    private @Getter HashMap<String, HashMap<Integer, Integer>> jumpDifficultySetting;





    private @Getter HashMap<String, JumpEarning> jumpEarning;

    public static int nbrJump(){
        return listJumps.size();
    }

    public String getLabel(String lang){
        return Lang.getMessage(this.label, lang);
    }

    public ItemBuilder getIcon(String lang){
        return Item.getItem(this.icon, lang);
    }

	public Jumps(ResultSet results, Database database) throws SQLException {
        this.name = results.getString("jump_name");
        this.label = results.getString("jump_label");
        this.location = net.vorps.api.objects.Location.getLocation(results.getString("jump_location"));
        this.playerLocation = net.vorps.api.objects.Location.getLocation(results.getString("jump_player_location"));
        this.icon = results.getString("jump_icon");
        this.menu = Menu.getMenu(results.getString("jump_menu"));
        this.message = results.getString("jump_message");
        this.winMessage = results.getString("jump_win_message");
        this.messageTitle =  MessageTitle.getMessageTitle(results.getString("jump_win_message_title"));

/*

        this.jumpsSettings = new ArrayList<>();
        try {
            results = database.getDatabase().getData("jump_setting", "js_jump = '"+this.name+"'");
            while(results.next()){
                this.jumpsSettings.add(new JumpsSettings(results));
            }
        } catch (SQLException e) {
            //
        }


        this.jumpDifficultySetting = new HashMap<>();
        try {
            results = database.getDatabase().getData("jump_difficulty_setting", "jds_jump = '"+this.name+"' && jds_checkpoint = '"+1+"'");
            results.last();
            this.jumpDifficulty = new JumpDifficulty[results.getRow()];
            results.beforeFirst();
            for(int i = 0; results.next(); i++) this.jumpDifficulty[i] = JumpDifficulty.getJumpDifficulty(results.getString("jds_difficulty"));

            for(JumpDifficulty jumpDifficulty : this.jumpDifficulty){
                results = database.getDatabase().getData("jump_difficulty_setting", "jds_jump = '"+this.name+"'  && jds_difficulty = '"+jumpDifficulty.getName()+"'");
                HashMap<Integer, Integer> difficultySettingsHashMap = new HashMap<>();
                while (results.next()){
                    difficultySettingsHashMap.put(results.getInt(3), results.getInt(4));
                }
                jumpDifficultySetting.put(jumpDifficulty.getName(), difficultySettingsHashMap);
            }
        } catch (SQLException e) {
            //
        }

        jumpEarning = new HashMap<>();

        //EntityManager.entityManager(EntityType.VILLAGER, me.vorps.hub.Object.Location.getLocation(results.getString(results, 10)), label.get(Settings.getDefaultLang())+" §6Classement", Villager.Profession.LIBRARIAN);

        try {


            results = results.getData("jump_earning", "je_jump = '"+jump+"'");
            while (results.next()){
                jumpEarning.put(results.getString(2), new JumpEarning(results));
            }
		} catch (SQLException e) {
            //
        }*/
        listJumps.add(this);
	}
/*
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
*/
    public static void clear(){
        listJumps.clear();
    }
}
