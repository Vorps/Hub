package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.ChatInteract;

import java.sql.Timestamp;

/**
 * Created by Vorps on 01/02/2016.
 */
public class CommandParty {

    public static boolean CommandParty(String args[], PlayerHub playerHub){
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("invite")){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.party.invite")){
                    if(!Bukkit.getPlayer(playerHub.getPlayerName()).getName().equals(args[1])){
                        if(!playerHub.getParty().isState()){
                            if(Hub.instance.getListPlayer().contains(args[1])){
                                String nameParty = Bukkit.getPlayer(playerHub.getPlayerName()).getName()+" party";
                                Hub.instance.database.SendDatabase("INSERT INTO Party VALUES ('"+nameParty+"', '"+args[1]+"', '"+0+"', '"+new Timestamp(System.currentTimeMillis())+"', '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"')");
                                playerHub.UpdateParty();
                                if(Hub.instance.getPlayerHub().containsKey(args[1])){
                                    Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[1]).getPlayerName()).sendMessage("§2"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§e vous a invité dans sa party.");
                                    ChatInteract.ChatInteractAcceptParty( Hub.instance.getPlayerHub().get(args[1]), Bukkit.getPlayer(playerHub.getPlayerName()).getName());
                                }
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eNouvelle party créé : §a"+nameParty+"§e !!!");
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eInvitation envoyée § §a"+args[1]+"§e.");
                                return true;
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e"+args[1]+"§c ne c'est jamais connecté sur ce serveur.");
                            }
                        } else {
                            if(playerHub.getParty().getOwner().equals(Bukkit.getPlayer(playerHub.getPlayerName()).getName())){
                                if(playerHub.getParty().getMembers().size() <= playerHub.getNumbersMembers()){
                                    String nameParty = Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'s party";
                                    Hub.instance.database.SendDatabase("INSERT INTO Party VALUES ('"+nameParty+"', '"+args[1]+"', '"+0+"', '"+new Timestamp(System.currentTimeMillis())+"', '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"')");
                                    playerHub.UpdateParty();
                                    if(Hub.instance.getPlayerHub().containsKey(args[1])){
                                        Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[1]).getPlayerName()).sendMessage("§a"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§e vous a invité dans sa party : §a"+nameParty+"§e.");
                                        ChatInteract.ChatInteractAcceptParty( Hub.instance.getPlayerHub().get(args[1]), Bukkit.getPlayer(playerHub.getPlayerName()).getName());
                                    }
                                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eInvitation envoyée § §a"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§e.");
                                    return true;
                                } else {
                                    Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous avez atteind le nombre maximal de membres dans votre groupe.");
                                }
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'êtes pas le créateur du groupe vous ne pouvez pas inviter de joueur faite /leave party");
                            }
                        }
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous ne pouvez pas vous inviter dans votre prope party");
                    }
                }
            } else if(args[0].equalsIgnoreCase("rename")){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.party.rename")){
                    if(playerHub.getParty().isState()){
                        if(playerHub.getParty().getOwner().equals(Bukkit.getPlayer(playerHub.getPlayerName()).getName())){
                            if(args[1].length() <= 50){
                                Hub.instance.database.SendDatabase("UPDATE Party SET nameParty = '"+args[1]+"' WHERE leader = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                                playerHub.UpdateParty();
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eLe groupe a §t§ renom§ en : §a"+args[1]+"§e.");
                                return true;
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cLe nom de la party est trop long.");
                            }
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'êtes pas le créateur du groupe vous ne pouvez pas le renomer");
                        }
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'avez pas de party.");
                    }
                }
            } else if(args[0].equalsIgnoreCase("remove")){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.party.remove")){
                    if(playerHub.getParty().isState()){
                        if(playerHub.getParty().getOwner().equals(Bukkit.getPlayer(playerHub.getPlayerName()).getName())){
                            Hub.instance.database.SendDatabase("DELETE FROM Party WHERE membre = '"+args[1]+"'");
                            playerHub.UpdateParty();
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eLe membre §"+args[1]+" § §t§ supprimé du groupe.");
                            return true;
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'êtes pas le créateur du groupe vous ne pouvez pas supprimer un membre");
                        }
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'avez pas de party.");
                    }
                }
            } else if(args[0].equalsIgnoreCase("accept")){
                playerHub.UpdateParty();
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.party.accept")){
                    if(playerHub.getParty().isState()){
                        if(playerHub.getParty().getMembersRequestList().contains(args[1])){
                            if(playerHub.getParty().getOwner().equals(Bukkit.getPlayer(playerHub.getPlayerName()).getName())){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez dissout le groupe "+playerHub.getParty().getName()+" et faite désormait party du groupe de §a."+args[1]);
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez quitter le groupe "+playerHub.getParty().getName()+" et faite désormait party du groupe de §a."+args[1]);
                            }
                            Hub.instance.database.SendDatabase("UPDATE Party SET variable = '"+2+"', date = '"+new Timestamp(System.currentTimeMillis())+"' WHERE leader = '"+args[1]+"' &&  membre = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                            if(Hub.instance.getPlayerHub().containsKey(args[1])){
                                Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[1]).getPlayerName()).sendMessage("§a"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§e vient de rejoindre la party.");
                                Hub.instance.database.SendDatabase("UPDATE Party SET variable = '"+1+"' WHERE leader = '"+args[1]+"' &&  membre = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                            }
                            playerHub.UpdateParty();
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous faite désormais partie du groupe : §a"+playerHub.getParty().getName()+"§e Bienvenue.");
                            return true;
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[1]+"§e ne vous a pas invité a son groupe.");
                        }
                    } else {
                        if(playerHub.getParty().getMembersRequestList().contains(args[1])){
                            Hub.instance.database.SendDatabase("UPDATE Party SET variable = '"+2+"', date = '"+new Timestamp(System.currentTimeMillis())+"' WHERE leader = '"+args[1]+"' &&  membre = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                            if(Hub.instance.getPlayerHub().containsKey(args[1])){
                                Bukkit.getPlayer(Hub.instance.getPlayerHub().get(args[1]).getPlayerName()).sendMessage("§a"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"§e vient de rejoindre la party.");
                                Hub.instance.database.SendDatabase("UPDATE Party SET variable = '"+1+"' WHERE leader = '"+args[1]+"' &&  membre = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                            }
                            playerHub.UpdateParty();
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous faite desormais partie du groupe : §a"+playerHub.getParty().getName()+"§e Bienvenue.");
                            return true;
                        } else {
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+args[1]+"§e ne vous a pas invité a son groupe.");
                        }
                    }
                }
            } else {
                AideCommande.AideParty(playerHub);
            }
        } else if(args.length == 1){
            if(args[0].equalsIgnoreCase("leave")){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.party.leave")){
                    if(playerHub.getParty().isState()){
                        if(playerHub.getParty().getOwner().equals(Bukkit.getPlayer(playerHub.getPlayerName()).getName())){
                            Hub.instance.database.SendDatabase("DELETE FROM Party WHERE leader = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez dissout le groupe §a"+playerHub.getParty().getName());
                            playerHub.UpdateParty();
                        } else {
                            Hub.instance.database.SendDatabase("DELETE FROM Party WHERE membre = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"' || leader = '"+Bukkit.getPlayer(playerHub.getPlayerName()).getName()+"'");
                            Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§eVous avez quitté le groupe §a"+playerHub.getParty().getName());
                            playerHub.UpdateParty();
                        }
                        return true;
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'avez pas de party.");
                    }
                }
            } else if(args[0].equalsIgnoreCase("list")){
                if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.party.list")){
                    if(playerHub.getParty().isState()){
                        for(int i = 0; i < playerHub.getParty().getMembers().size(); i++){
                            if(Hub.instance.getPlayerHub().containsKey(playerHub.getParty().getMembers().get(i))){
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+playerHub.getParty().getMembers().get(i)+"§f <> §eHub n§"+playerHub.getHub()+"§e.");
                            } else {
                                Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§a"+playerHub.getParty().getMembers().get(i)+"§f <> §7Offline§e.");
                            }
                        }
                        return true;
                    } else {
                        Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§cVous n'avez pas de party.");
                    }
                }
            } else {
                AideCommande.AideParty(playerHub);
            }
        } else {
            AideCommande.AideParty(playerHub);
        }
        return false;
    }
}
