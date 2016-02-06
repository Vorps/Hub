package sirmc.vorps.utils;

import sirmc.vorps.PlayerHub;

public class ChatInteract {
	public static void ChatInteractAcceptAmi(PlayerHub playerHub, String nameOwner){
		/**
		IChatBaseComponent comp = ChatSerializer.a("{\"text\":\"�eFaites �a/friends accept �b"+nameOwner+" �aou \",\"extra\":[{\"text\":\"�bCliquer ici ! \",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"�eAccepter l'invitation !\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/friends accept "+nameOwner+"\"}}]}");
		PacketPlayOutChat packet = new PacketPlayOutChat(comp);
		((CraftPlayer) playerHub.player).getHandle().playerConnection.sendPacket(packet);
	*/
		 }
	
	public static void ChatInteractAcceptParty(PlayerHub playerHub, String nameOwner){
	/**
			IChatBaseComponent comp = ChatSerializer.a("{\"text\":\"�eFaites �a/party accept �b"+nameOwner+" �aou \",\"extra\":[{\"text\":\"�bCliquer ici ! \",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"�eAccepter l'invitation !\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/party accept "+nameOwner+"\"}}]}");
		PacketPlayOutChat packet = new PacketPlayOutChat(comp);
		((CraftPlayer) playerHub.player).getHandle().playerConnection.sendPacket(packet);
	*/
	 }
}
