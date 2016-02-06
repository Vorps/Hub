package sirmc.vorps.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import sirmc.vorps.Hub;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandPreprocessListener implements Listener {

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){

        if(e.getMessage().length() >= 4){
            if(e.getMessage().substring(0, 4).equalsIgnoreCase("/ban")){
                e.setCancelled(true);
            }
        }
        if(e.getMessage().length() >= 7){
            if(e.getMessage().substring(0, 7).equalsIgnoreCase("/pardon")){
                e.setCancelled(true);
            }
        }
        boolean state , stateCommand = false;
        for(int i = 0; i < Hub.instance.getListCommands().size(); i++){
            state = true;
            stateCommand = false;
            if(!Hub.instance.getListCommands().get(i).getCommand().equals("*")){
                if(i != 0){
                    for(int y = 0; y < Hub.instance.getListCommands().get(i).getCommand().length(); y++){
                        if(Hub.instance.getListCommands().get(i).getCommand().charAt(y) == ' '){
                            state = false;
                        }
                    }
                    if(Hub.instance.getListCommands().get(i).getCommand().equals(Hub.instance.getListCommands().get(i).getCommand())){
                        state = false;
                    }
                }
                if(state){
                    if(e.getMessage().length() >= Hub.instance.getListCommands().get(i).getCommand().length()+1){
                        if(e.getMessage().substring(0, Hub.instance.getListCommands().get(i).getCommand().length()+1).equals("/"+Hub.instance.getListCommands().get(i).getCommand())){
                            stateCommand = true;
                            if(!e.getPlayer().hasPermission(Hub.instance.getListCommands().get(i).getPermission())){
                                e.setCancelled(true);
                            }
                            break;
                        }
                    }
                }
            }
        }
        if(!stateCommand && !e.getPlayer().isOp()){
            e.setCancelled(true);
        }
    }
}
