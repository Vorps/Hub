package me.vorps.hub.events;

import me.vorps.hub.PlayerData;
import me.vorps.hub.utils.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Project Bungee Created by Vorps on 24/02/2016 at 03:34.
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        String message = ChatColor.chatColor(player, e.getMessage());
        Bukkit.getOnlinePlayers().forEach((Player playerServer) -> {
            if(PlayerData.getPlayerData(playerServer.getName()).isChat()){
                if(playerData.getGrade().isVisibleGrade()){
                    playerServer.sendMessage(playerData.getGrade().toString()+" "+player.getName() + playerData.getGrade().getColorChat()+" : " +message);
                } else {
                    playerServer.sendMessage(playerData.getGrade().getColorGrade()+" "+player.getName() + playerData.getGrade().getColorChat()+" : " +message);
                }
            } else {
                if(playerData.getFriends().getFriends().keySet().contains(playerServer.getName())
                        || playerData.getParty().getMembers().keySet().contains(playerServer.getName())
                        || playerData.getGrade().isVisibleGrade()
                        ){
                    if(playerData.getGrade().isVisibleGrade()){
                        playerServer.sendMessage(playerData.getGrade().toString()+" "+player.getName() + playerData.getGrade().getColorChat()+" : " +message);
                    } else {
                        playerServer.sendMessage(playerData.getGrade().getColorGrade()+" "+player.getName() + playerData.getGrade().getColorChat()+" : " +message);
                    }
                }
            }
        });
        e.setCancelled(true);
    }
}
