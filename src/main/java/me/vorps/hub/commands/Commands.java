package me.vorps.hub.commands;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;

/**
 * Project Hub Created by Vorps on 04/03/2016 at 16:25.
 */
public abstract class Commands {
    private @Getter @Setter boolean stateExec;
    private @Getter CommandSender sender;
    private @Getter String permission;

    protected abstract void help();

    public Commands(CommandSender sender, String permission){
        this.sender = sender;
        this.permission = permission;
    }
}
