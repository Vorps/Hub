package me.vorps.hub.thread;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.vorps.hub.Hub;
import org.bukkit.entity.Player;

/**
 * Project FortycubeBungee Created by Vorps on 13/04/2016 at 00:34.
 */
public class ThreadFile extends ClassThread {

    private long time;
    private Player player;

    public ThreadFile(String nameServer, long time, Player player){
        super(nameServer);
        this.time = time;
        this.player = player;
    }
    public void run(){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(getNameServer());
        player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
        interrupt();
    }
}
