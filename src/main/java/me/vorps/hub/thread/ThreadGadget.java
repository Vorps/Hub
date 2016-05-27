package me.vorps.hub.thread;

import org.bukkit.entity.Entity;

/**
 * Project Hub Created by Vorps on 26/05/2016 at 15:37.
 */
public class ThreadGadget extends Thread {

    private Entity entity;

    public ThreadGadget(Entity entity){
        this.entity = entity;
    }

    public void run(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            //
        }
        entity.remove();
    }
}
