package me.vorps.hub.dispatcher;
import java.io.Serializable;

/**
 * Project GameManager Created by Vorps on 05/04/2016 at 00:08.
 */
public class Parameter implements Serializable{

    private static final long serialVersionUID = -4072024704477723945L;
    private boolean online;

    public Parameter(boolean online){
        this.online = online;
    }

    public boolean getOnline(){
        return online;
    }
}