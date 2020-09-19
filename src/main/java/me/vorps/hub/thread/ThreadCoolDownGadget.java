package me.vorps.hub.thread;

import me.vorps.hub.PlayerData;
import net.vorps.api.cooldowns.CoolDowns;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Project Hub Created by Vorps on 30/05/2016 at 18:50.
 */
public class ThreadCoolDownGadget extends Thread {

    private Player player;

    public ThreadCoolDownGadget(Player player){
        this.player = player;
    }

    public void run(){
        /*ItemStack itemStack = player.getInventory().getItem(4);
        while(CoolDowns.hasCoolDown(player.getName(), "gadget")){
            CoolDowns coolDowns = CoolDowns.getCoolDown(player.getName(), "gadget");
            if(coolDowns.getSecondsLeft() > 0){
                if(PlayerData.isPlayerDataExits(player.getName())){
                    ItemStack itemStackClone = itemStack.clone();
                    ItemMeta itemMeta =  itemStackClone.getItemMeta();
                    itemMeta.setDisplayName(itemStack.getItemMeta().getDisplayName()+" §7("+ coolDowns.getSecondsLeft()+" Seconde)§6.");
                    itemStackClone.setItemMeta(itemMeta);
                    itemStackClone.setAmount((int) -coolDowns.getSecondsLeft());
                    if(!PlayerData.getPlayerData(player.getName()).getJump().isInJump()){
                        player.getInventory().setItem(4, itemStackClone);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                coolDowns.removeCoolDown();
            }
        }
        player.getInventory().setItem(4, itemStack);
        interrupt();*/
    }
}
