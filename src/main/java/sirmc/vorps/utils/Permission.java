package sirmc.vorps.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;


public class Permission {
	
    private Map<String, PermissionAttachment> attachments = new HashMap<>();
    private Plugin registeringPlugin;

    public Permission(Plugin plugin) {
        registeringPlugin = plugin;
        attachments = new HashMap<>();
    }

    public void addPermission(Player p, String permission) {
        if (!attachments.containsKey(p.getName())) {
            attachments.put(p.getName(), p.addAttachment(registeringPlugin));
        }
        attachments.get(p.getName()).setPermission(permission, true);
    }

    public void denyPermission(Player p, String permission) {
        if (!attachments.containsKey(p.getName())) {
            attachments.put(p.getName(), p.addAttachment(registeringPlugin));
        }
        attachments.get(p.getName()).setPermission(permission, false);
    }

    public void unsetPermission(Player p, String permission) {
        if (!attachments.containsKey(p.getName())) {
            attachments.put(p.getName(), p.addAttachment(registeringPlugin));
        }
        attachments.get(p.getName()).unsetPermission(permission);
    }

    public void unsetAllPermissions(String pName) {
        try {
            attachments.remove(pName).remove();
        } catch (NullPointerException e) {}
    }

    public void removeAllPermissions() {
        String[] names  = attachments.keySet().toArray(new String[0]);
        for (String name : names) {
            this.unsetAllPermissions(name);
        }
    }
}