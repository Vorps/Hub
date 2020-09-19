package me.vorps.hub.thread;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import me.vorps.hub.Hub;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Project COREBungee Created by Vorps on 10/04/2016 at 18:42.
 */
public class ThreadServer extends ClassThread {

    private static @Getter ArrayList<String> serverStart = new ArrayList<>();
    private long time;
    private Player player;

    public ThreadServer(long time, Player player, String nameServer){
        super(nameServer);
        this.time = time;
        this.player = player;
        serverStart.add(nameServer);
    }

    public void run(){
        serverStart.add(getNameServer());
        try {
            Thread.sleep(time);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(getNameServer());
        player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
        serverStart.remove(getNameServer());
        interrupt();
    }
}
