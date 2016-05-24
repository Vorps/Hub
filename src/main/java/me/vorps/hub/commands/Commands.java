package me.vorps.hub.commands;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;

/**
 * Project Hub Created by Vorps on 04/03/2016 at 16:25.
 */
public abstract class Commands{
    public enum Command{
        BUILD("build", "fortycube.build"),
        DOUBLE_JUMP("doublejump", "fortycube.double_jump"),
        FLY("fly", "fortycube.fly"),
        JUMP("jump", "fortycube.jump"),
        RELOAD_HUB("reloadhub", "fortycube.reloadhub"),
        VISIBLE_PLAYER("visibleplayer", "fortycube.visible_player");

        private @Getter String command;
        private @Getter String permissions;

        Command(String command, String permissions){
            this.command = command;
            this.permissions = permissions;
        }
    }

    private @Getter @Setter boolean stateExec;
    private @Getter CommandSender sender;
    private @Getter String permission;

    protected abstract void help();

    public Commands(CommandSender sender, String permission){
        this.sender = sender;
        this.permission = permission;
    }
}
