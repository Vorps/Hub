package me.vorps.hub.data;

import lombok.Getter;
import me.vorps.hub.Object.Bonus;
import net.vorps.api.data.DataCore;
import net.vorps.api.data.SettingCore;
import net.vorps.api.data.Settings;
import net.vorps.api.objects.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:43.
 */
public class SettingsHub extends SettingCore {

    private static @Getter Bonus defaultBonus;
    private static @Getter String site;
    private static @Getter String messageTabListHeader;
    private static @Getter String messageTabListFooter;
    private static @Getter String IP;
    private static @Getter org.bukkit.Location spawn_location;
    private static @Getter List<String> message_server;
    private static @Getter int time_thread_message;

    public static void initSettings(){
        SettingCore.initFunction = () -> {
            SettingsHub.defaultBonus =  Bonus.getBonus(Settings.getSettings("default_bonus").getMessage());
            SettingsHub.site = Settings.getSettings("site").getMessage();
            SettingsHub.messageTabListHeader = Settings.getSettings("message_tablist_header").getMessage();
            SettingsHub.messageTabListFooter = Settings.getSettings("message_tablist_footer").getMessage();
            SettingsHub.IP = Settings.getSettings("IP").getMessage();
            SettingsHub.spawn_location = Location.getLocation(Settings.getSettings("spawn_location").getMessage());
            SettingsHub.message_server = Arrays.asList(Settings.getSettings("message_server").getMessage().split(";"));
            SettingsHub.time_thread_message = Settings.getSettings("time_thread_message").getValueInt();
        };
        DataCore.loadSetting();
    }
}
