package me.vorps.hub.chanel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.vorps.fortycube.chanel.ChanelManager;
import me.vorps.hub.Object.Server;
import me.vorps.hub.PlayerData;
import me.vorps.hub.dispatcher.Dispatcher;
import me.vorps.hub.menu.MenuHub;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Project Hub Created by Vorps on 15/03/2016 at 15:59.
 */
public class PluginMessageReceived  implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        if (channel.equals("BungeeCord")) {
            String[] signal = ChanelManager.readSignal(data);
            if(signal.length != 0){
                if(signal[0].equals("grade")){
                    if(PlayerData.isPlayerDataExits(signal[1])){
                        PlayerData playerData = PlayerData.getPlayerData(signal[1]);
                        playerData.updateGrades();
                    }
                }
                if(signal[0].equals("money")){
                    if(PlayerData.isPlayerDataExits(signal[1])){
                        PlayerData playerData = PlayerData.getPlayerData(signal[1]);
                        playerData.getMoneyFunction();
                    }
                }
                if(signal[0].equals("hub")){
                    if(PlayerData.isPlayerDataExits(signal[1])){
                        PlayerData playerData = PlayerData.getPlayerData(signal[1]);
                        playerData.teleportSpawn();
                    }
                }
            } else {
                ByteArrayDataInput in = ByteStreams.newDataInput(data);
                String subChannel = in.readUTF();
                if (subChannel.equals("PlayerList")) {
                    String server = in.readUTF();
                    String[] playerList = in.readUTF().split(", ");
                    if(playerList[0].equals("")){
                        playerList = new String[0];
                    }
                    Server.getServer().get(server).updatePlayers(playerList, player);
                }
                if (subChannel.equals("PlayerCount")) {
                    String server = in.readUTF();
                    int playerCount = in.readInt();
                    MenuHub.connectServer(player, server, playerCount);
                }
                if (subChannel.equals("GetServers")) {
                    Dispatcher.listServer(in.readUTF().split(", "));
                }
            }
        }
    }
}
