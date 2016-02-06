package sirmc.vorps.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.Grades;
import sirmc.vorps.utils.Item;
import sirmc.vorps.Hub;
import sirmc.vorps.Settings;

public class MenuHub {
	private static List<String> des = new ArrayList<>();

	public static void HubmenuHub(PlayerHub playerHub){
        Inventory menuHub = Bukkit.createInventory(null, 27, "§6Hub ("+Hub.instance.getNUMBRE_HUB()+")");
        menuHub.setItem(0, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(1, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(2, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(3, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(4, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(5, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(6, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(7, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(8, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(9, new Item(160).withData((byte) 10).withName(" ").get());
        des.add("§7Présence : §f"+Hub.instance.getPlayerHub().size());
        for(Grades listGrades : Grades.getGradesList()){
            int nbrPlayerGrade = 0;
            for(Player player : Bukkit.getOnlinePlayers()){
                if(listGrades.getGrade().equals(Hub.instance.getPlayerHub().get(player.getName()).getGrade())){
                    nbrPlayerGrade++;
                }
            }
            if(nbrPlayerGrade != 0){
                des.add(listGrades.getColorGrade()+listGrades.getGradeDisplay()+"§f "+nbrPlayerGrade);
            }
        }
        if(Hub.instance.getPlayerHub().size() <= 15){
            menuHub.setItem(10, new Item(159).withData((byte) 5).withName("§6Hub N°"+Hub.instance.getNUMBRE_HUB()+ " - §aCharge faible").withAmount(Hub.instance.getNUMBRE_HUB()).withLore(des.toArray(new String[des.size()])).get());
        } else if(Hub.instance.getPlayerHub().size() > 15 && Hub.instance.getPlayerHub().size() <= 25){
            menuHub.setItem(10, new Item(159).withData((byte) 4).withName("§6Hub N°"+Hub.instance.getNUMBRE_HUB()+ " - §eCharge moyenne").withAmount(Hub.instance.getNUMBRE_HUB()).withLore(des.toArray(new String[des.size()])).get());
        }  else if(Hub.instance.getPlayerHub().size() > 25 && Hub.instance.getPlayerHub().size() <= 35){
            menuHub.setItem(10, new Item(159).withData((byte) 1).withName("§6Hub N°"+Hub.instance.getNUMBRE_HUB()+ " - §6Charge §levée").withAmount(Hub.instance.getNUMBRE_HUB()).withLore(des.toArray(new String[des.size()])).get());
        }  else if(Hub.instance.getPlayerHub().size() > 35 && Hub.instance.getPlayerHub().size() <= 45){
            menuHub.setItem(10, new Item(159).withData((byte) 14).withName("§6Hub N°"+Hub.instance.getNUMBRE_HUB()+ " - §cCharge complet").withAmount(Hub.instance.getNUMBRE_HUB()).withLore(des.toArray(new String[des.size()])).get());
        } else if(Hub.instance.getPlayerHub().size() > 45){
            menuHub.setItem(10, new Item(Material.TNT).withName("§6Hub N§"+Hub.instance.getNUMBRE_HUB()+ " - §4Indisponible").get());
        }
        des.clear();
        menuHub.setItem(16, new Item(Material.ENDER_PEARL).withName("§6Centre du hub").withLore(new String[] {"§7Retourner au spawn de "+Settings.getNameServer()+"."}).get());
        menuHub.setItem(17, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        menuHub.setItem(19, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(20, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(21, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(22, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(23, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(24, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(25, new Item(160).withData((byte) 10).withName(" ").get());
        menuHub.setItem(26, new Item(160).withData((byte) 10).withName(" ").get());
        Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuHub);
	}
}
