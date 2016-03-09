package me.vorps.hub.menu;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.vorps.hub.Object.Grades;
import me.vorps.hub.Hub;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuHub extends Menu{

    public enum Charge {
        LOW(45),
        MEDIUM(25),
        HIGH(35),
        FULL(45);

        private @Getter int charge;

        Charge(int charge){
            this.charge = charge;
        }
    }
	public MenuHub(Player player){
        super(new byte[] {10}, Bukkit.createInventory(null, 27, "§6Hub ("+Hub.getInstance().getNUMBER_HUB()+")"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}});
        List<String> des = new ArrayList<>();
        des.add("§7Présence : §f"+PlayerData.nbrConnect());
        for(Grades listGrades : Grades.getGradesList().values()){
            int nbrPlayerGrade = 0;
            for(Player players : Bukkit.getOnlinePlayers()){
                if(listGrades.getGrade().equals(PlayerData.getPlayerData(players.getName()).getGrade().getGrade())){
                    nbrPlayerGrade++;
                }
            }
            if(nbrPlayerGrade != 0){
                if(listGrades.getGradeDisplay().equals("")){
                    des.add(listGrades.getColorGrade()+listGrades.getGrade()+"§f "+nbrPlayerGrade);
                } else {
                    des.add(listGrades.getColorGrade()+listGrades.getGradeDisplay()+"§f "+nbrPlayerGrade);
                }
            }
        }
        if(PlayerData.nbrConnect() <= Charge.LOW.charge){
            menu.setItem(10, new Item(159).withData((byte) 5).withName("§6Hub N°"+Hub.getInstance().getNUMBER_HUB()+ " - §aCharge faible").withAmount(Hub.getInstance().getNUMBER_HUB()).withLore(des.toArray(new String[des.size()])).get());
        } else if(PlayerData.nbrConnect() <= Charge.MEDIUM.charge){
            menu.setItem(10, new Item(159).withData((byte) 4).withName("§6Hub N°"+Hub.getInstance().getNUMBER_HUB()+ " - §eCharge moyenne").withAmount(Hub.getInstance().getNUMBER_HUB()).withLore(des.toArray(new String[des.size()])).get());
        }  else if(PlayerData.nbrConnect() <= Charge.HIGH.charge){
            menu.setItem(10, new Item(159).withData((byte) 1).withName("§6Hub N°"+Hub.getInstance().getNUMBER_HUB()+ " - §6Charge §levée").withAmount(Hub.getInstance().getNUMBER_HUB()).withLore(des.toArray(new String[des.size()])).get());
        }  else if(PlayerData.nbrConnect() <= Charge.FULL.charge){
            menu.setItem(10, new Item(159).withData((byte) 14).withName("§6Hub N°"+Hub.getInstance().getNUMBER_HUB()+ " - §cCharge complet").withAmount(Hub.getInstance().getNUMBER_HUB()).withLore(des.toArray(new String[des.size()])).get());
        } else {
            menu.setItem(10, new Item(Material.TNT).withName("§6Hub N§"+Hub.getInstance().getNUMBER_HUB()+ " - §4Indisponible").get());
        }
        menu.setItem(16, new Item(Material.ENDER_PEARL).withName("§6Centre du hub").withLore(new String[] {"§7Retourner au spawn de "+ Settings.getNameServer()+"."}).get());
        menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        player.openInventory(menu);
	}
}
