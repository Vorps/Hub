package sirmc.vorps;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import sirmc.vorps.utils.HubTabComplete;

public class Commands {

	private @Getter	String command;
	private @Getter String permission;
	private int nombreArgs;
	
	public Commands(ResultSet resultats) throws SQLException{
		command = resultats.getString(1);
		permission = resultats.getString(2);
		nombreArgs = resultats.getInt(3);
	}
	
	public static void LoadCommand(Hub plugin){
		boolean state;
		for(int i = 0; i < Hub.instance.getListCommands().size(); i++){
			state = true;
			if(!Hub.instance.getListCommands().get(i).command.equals("*")){
				if(i != 0){
					for(int y = 0; y < Hub.instance.getListCommands().get(i).command.length(); y++){
						if(Hub.instance.getListCommands().get(i).command.charAt(y) == ' '){
							state = false;
						}
					}
					if(Hub.instance.getListCommands().get(i-1).command.equals(Hub.instance.getListCommands().get(i).command)){
						state = false;
					}
				}
				if(state){
					plugin.getCommand(Hub.instance.getListCommands().get(i).command).setTabCompleter(new HubTabComplete());
				}
			}
		}
	}
}
