package net.vorps.hub.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.vorps.hub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.UUID;

public class PluginMessageReceived implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (channel.equals("BungeeCord")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            String subChannel = in.readUTF();
            switch (subChannel){
                case "FLY":
                    player.setFlying(Boolean.parseBoolean(in.readUTF()));
                    break;
                case "VANISH":
                    if(Boolean.parseBoolean(in.readUTF())) for (Player all : Bukkit.getOnlinePlayers()) all.hidePlayer(Hub.getInstance(), player);
                    else for (Player all : Bukkit.getOnlinePlayers()) all.showPlayer(Hub.getInstance(), player);
                    break;
                case "BUILD":
                    //Bukkit.getPlayer(namePlayer).getInventory().clear();
                    //Bukkit.getPlayer(namePlayer).setGameMode(GameMode.CREATIVE);
                    // TODO: 18/09/2020
                    break;
                    case "VISIBLE":
                    // TODO: 18/09/2020
                    break;
                case "INVENTORY_SEE":
                    Player player_see = Bukkit.getPlayer(UUID.fromString(in.readUTF()));
                    if(player_see != null) player.openInventory(player_see.getInventory());
                    break;
                default:
                    break;
            }
        }

    }
}
