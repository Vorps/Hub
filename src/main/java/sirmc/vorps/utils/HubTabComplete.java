package sirmc.vorps.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import sirmc.vorps.Hub;

public class HubTabComplete implements TabCompleter {
	
	
//	//0 | void; 1 | player online; 2 | player friend; 3 | player who request 
//	private Object[][] ListCommandFriends = {{"list", 0}, {"add", 1}, {"remove", 2}, {"accept", 3}};
//	
//	
//	//0 | void; 1 | name party; 2 | player party; 3 | player who request; 4 | player online
//	private Object[][] ListCommandParty = {{"list", 0}, {"leave", 0}, {"rename", 1}, {"remove", 2}, {"accept", 3}, {"invite", 4}};
//	
//	//0 |  player online;
//	private Object[][] ListGrades = {{"Administrateur", 0}, {"D�veloppeur", 0}, {"Architecte", 0}, {"Mod�rateur", 0}, {"Staff", 0}, {"Helper", 0}, {"Joueur", 0}};
//	
	public static ArrayList<String> getArgs(int i, String message){
		ArrayList<String> args = new ArrayList<String>();
		int y = i;
		for(; i < message.length(); i++){
			if(message.charAt(i) == ' '){
				args.add(message.substring(y, i--));
				y = i++;
			}
		}
		if(message.charAt(i) != ' '){
			args.add(message.substring(y, i));
		}
		return args;
	}
	
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		List<String> list = new ArrayList<>();
		if(sender instanceof Player){
			Player player = (Player) sender;
			boolean state;
			boolean stateCommand = false;
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
						if(cmd.getName().length() >= (Hub.instance.getListCommands().get(i).getCommand().length())){
							if(cmd.getName().equals(Hub.instance.getListCommands().get(i).getCommand())){
								stateCommand = true;
								if(!player.hasPermission(Hub.instance.getListCommands().get(i).getPermission())){
									list.clear();
									return list;
								}
								break;
							}
						}
					}
				}
			}
			if(!stateCommand){
				list.clear();
				return list;
			}
		}
		
		
		
		return list;
//		PlayerHub playerHub = Hub.playerHub.get(sender.getName());
//		
//		if(cmd.getName().equalsIgnoreCase("friends")){
//			if(args.length == 1){
//				for(int i = 0; i < ListCommandFriends.length; i++){
//					try{
//						if(args[0].equalsIgnoreCase((String) ((String) ListCommandFriends[i][0]).subSequence(0, args[0].length()))){
//							list.add((String) ListCommandFriends[i][0]);
//						}
//					} catch(Exception e){}
//				}
//			} else if(args.length == 2){
//				for(int i = 0; i < ListCommandFriends.length; i++){
//					try{
//						if(args[0].equalsIgnoreCase((String) ListCommandFriends[i][0])){
//							switch ((int) ListCommandFriends[i][1]) {
//							case 0:
//								list.clear();
//								break;
//							case 1:
//								Bukkit.getOnlinePlayers().forEach(p -> list.add(p.getName()));
//								list.remove(sender.getName());
//								for(int y = 0; y < playerHub.amis.size(); y++){
//									list.remove(playerHub.amis.get(y));
//								}
//								break;
//							case 2:
//								for(int y = 0; y < playerHub.amis.size(); y++){
//									list.add(playerHub.amis.get(y));
//								}
//								break;
//							case 3:
//								for(int y = 0; y < playerHub.demandeAmisList.size(); y++){
//									list.add(playerHub.demandeAmisList.get(y));
//								}
//								break;
//							default:
//								break;
//							}
//						}
//					} catch(Exception e){}
//				}
//			}
//		}
//		
//		
//		if(cmd.getName().equalsIgnoreCase("party")){
//			if(args.length == 1){
//				for(int i = 0; i < ListCommandParty.length; i++){
//					try{
//						if(args[0].equalsIgnoreCase((String) ((String) ListCommandParty[i][0]).subSequence(0, args[0].length()))){
//							list.add((String) ListCommandParty[i][0]);
//						}
//					} catch(Exception e){}
//				}
//			} else if(args.length == 2){
//				for(int i = 0; i < ListCommandParty.length; i++){
//					try{
//						if(args[0].equalsIgnoreCase((String) ListCommandParty[i][0])){
//							switch ((int) ListCommandParty[i][1]){
//							case 0:
//								list.clear();
//								break;
//							case 1:
//								if(playerHub.party.state && playerHub.party.owner.equals(sender.getName())){
//									list.add(playerHub.party.name);
//								} else {
//									list.clear();
//								}
//								break;
//							case 2:
//								if(playerHub.party.state && playerHub.party.owner.equals(sender.getName())){
//									for(int y = 0; y < playerHub.party.membres.size(); y++){
//										list.add(playerHub.party.membres.get(y));
//									}
//									list.remove(sender.getName());
//								} else {
//									list.clear();
//								}
//								break;
//							case 3:
//								for(int y = 0; y < playerHub.party.demandeMembresList.size(); y++){
//									list.add(playerHub.party.demandeMembresList.get(y));
//								}
//								break;
//							case 4:
//								Bukkit.getOnlinePlayers().forEach(p -> list.add(p.getName()));
//								list.remove(sender.getName());
//								for(int y = 0; y < playerHub.party.membres.size(); y++){
//									list.remove(playerHub.party.membres.get(y));
//								}
//								break;
//							default:
//								break;
//							}
//						}
//					} catch(Exception e){}
//				}
//			}
//		}
//		
//		if(cmd.getName().equalsIgnoreCase("build") || cmd.getName().equalsIgnoreCase("fly") || cmd.getName().equalsIgnoreCase("mute")){
//			if(args.length == 1){
//				Bukkit.getOnlinePlayers().forEach(p -> list.add(p.getName()));
//				list.remove(sender.getName());
//			}
//		}
//		
//		if(cmd.getName().equalsIgnoreCase("grades")){
//			if(args.length == 1){
//				for(int i = 0; i < ListGrades.length; i++){
//					list.add((String) ListGrades[i][0]);
//				}
//			} else if(args.length == 2){
//				Bukkit.getOnlinePlayers().forEach(p -> list.add(p.getName()));
//				list.remove(sender.getName());
//			}
//		}
//		
//		if(cmd.getName().equalsIgnoreCase("unmute")){
//			if(args.length == 1){
//				
//			}
//		}
	}
}
