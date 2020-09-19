package me.vorps.hub.commands;

import lombok.Getter;
import net.vorps.api.commands.Command;
import net.vorps.api.commands.CommandListener;

public enum CommandManager {
    DOUBLE_JUMP(new Command("double_jump", 10, DoubleJump.class));

    private @Getter final Command command;

    CommandManager(Command command){
        this.command = command;
    }

    private void active(){
        new CommandListener(this.command);
    }

    public static void init(){
        for(CommandManager commandManager : CommandManager.values()) commandManager.active();
    }

}
