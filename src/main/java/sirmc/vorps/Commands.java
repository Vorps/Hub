package sirmc.vorps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Getter;
import sirmc.vorps.utils.HubTabComplete;

public class Commands {

	private @Getter	String command;
	private @Getter String permission;
	private @Getter ArrayList<String> args = new ArrayList<>();
    private @Getter int type;

	public Commands(ResultSet results) throws SQLException{
		String commandTmp = results.getString(1);
		boolean state = false;
        StringBuilder stringTmp = new StringBuilder("");
		for(int i = 0; i < commandTmp.length(); i++){
			if(commandTmp.charAt(i) == ' '){
				if(!state){
					command = stringTmp.toString().trim();
					state = true;
				} else {
					args.add(stringTmp.toString().trim());
				}
                stringTmp = new StringBuilder("");
			}
			stringTmp.append(commandTmp.charAt(i));
		}
        if(!state){
            command = results.getString(1);
        } else {
            args.add(stringTmp.toString().trim());
        }
		permission = results.getString(2);
        type = results.getInt(3);
	}
	
	public static void LoadCommand(){
        ArrayList<String> commandBuff = new ArrayList<>();
		Hub.instance.getListCommands().values().forEach((Commands commands) -> {
            if(!commandBuff.contains(commands.getCommand())){
                commandBuff.add(commands.getCommand());
                Hub.instance.getCommand(commands.getCommand()).setTabCompleter(new HubTabComplete());
            }
		});
	}
}
