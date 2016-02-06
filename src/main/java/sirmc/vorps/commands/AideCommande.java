package sirmc.vorps.commands;

import org.bukkit.Bukkit;
import sirmc.vorps.PlayerHub;

public class AideCommande {
	public static void AideBuild(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Build§e -------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/build §f> Change votre mode Build/Normal");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/build <Joueur> §f> Change le mode Build a un joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideFriends(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Friends§e -----------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/friends list §f> List vos amis");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/friends add <Joueur> §f> Ajoute un ami");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/friends remove <Joueur> §f> Retire un ami");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/friends accept <Joueur> §f> Accepte une invitation");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideParty(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Party§e -------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party list §f> Affiche la liste des joueurs du groupe");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party invite <Joueur> §f> Invite un joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party rename <Nom_party> §f> Renome votre groupe");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party remove <Joueur> §f> Retire un membre");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party accept <Joueur> §f> Accepte une invitation");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party leave §f>Quitte le groupe");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideGrade(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Grades§e ------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/grades <Grade> <Joueur>§f> Change le grade du joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideFly(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Fly§e ---------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/fly §f> Change le mode Fly/Normal");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/fly <> §f> Change le mode Fly/Normal a un joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideMute(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Mute§e --------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/mute <Joueur> <Temps> <Raison> §f> Mute un joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideUnmute(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide UnMute§e ------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/unmute <Joueur> §f> Unmute un joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideHub(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Hub§e --------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/hub §f> Vous t§l§porte au spawn du serveur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideBan(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Ban§e ---------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/ban <Joueur> <Temps> <Raison> §f> Ban un joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AidePardon(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Pardon§e ------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/pardon <Joueur> §f> Pardonne un joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideJumps(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Jumps§e -------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/jump §f> Change votre mode Jump/Normal");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/jump <Joueur> §f> Change le mode jump a un joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideWhisper(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Whisper§e -------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/w <Joueur> §f> Envoie un message au joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideReSend(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide ReSend§e -------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/r <Joueur> §f> Renvoie un message au joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideVisibleJoueur(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Visible Joueur§e ------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/joueur Active/D§sactive la visibilit§ des joueurs dans le hub");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideHubReload(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Reload Hub§e ----------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/reload Reload les variables du plugin hub");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
	
	public static void AideMoney(PlayerHub playerHub){
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e--------- §7Aide Money§e -------------------------------");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/money get <Money> <Joueur> §f> Affiche la money du joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party set <Money> <Joueur> <Montant> §f> Set la money du joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party add <Money> <Joueur> <Montant> §f> Ajoute de la money au joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e/party remove <Money> <Joueur> <Montant> §f> Retire de la money au joueur");
		Bukkit.getPlayer(playerHub.getPlayerName()).sendMessage("§e-----------------------------------------------------");
	}
}
