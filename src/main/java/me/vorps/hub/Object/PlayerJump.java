package me.vorps.hub.Object;

import lombok.Getter;
import lombok.Setter;
import me.vorps.hub.PlayerData;
import me.vorps.hub.menu.Navigator;
import me.vorps.hub.scoreboard.ScoreBoard;
import me.vorps.hub.scoreboard.ScoreBoardJump;
import me.vorps.hub.thread.ThreadJump;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.nms.TitleBuilder;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 20/05/2016 at 05:13.
 */
public class PlayerJump {
    /*private @Getter PlayerData playerData;
    private @Getter @Setter Jumps jump;
    private @Setter @Getter JumpDifficulty jumpDifficulty;
    private @Setter @Getter int checkPoint;
    private @Setter @Getter Location[] location;
    private @Setter @Getter int life;
    private ThreadJump threadJump;

    public boolean isInJump(){
        return jump != null;
    }

    private void init(){
        location = new Location[jump.nbrChecpoint()+1];
        checkPoint = 0;
        life = 0;
    }


    public void jumpManager(Player player, Block block){
        playerData = PlayerData.getPlayerData(player.getName());
        Location location = block.getLocation();
        if(block.getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE){
            if(!isInJump()){
                Jumps jumps = Jumps.getJump(location);
                if(jumps != null){
                    startJump(player, jumps);
                }
            } else if(checkPoint == jump.nbrChecpoint()){
                win(player);
            }
        } else if(block.getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE && isInJump()){
            checkPoint(location, player);
        }
    }

    private void win(Player player){
        player.sendMessage(jump.getWinMessage().get(playerData.getLang()));
        new TitleBuilder(jump.getMessageTitle().getTitle(playerData.getLang()), jump.getMessageTitle().getSubTitle(playerData.getLang())).sendTo(player);
        jump.getJumpEarning().get(jumpDifficulty.getName()).getEarning(player);
        PlayerJumpRecord.record(player.getName(), jump.getJump(), jumpDifficulty.getName(), threadJump.getTime());
        stopJump(player, true);
    }

    private boolean contentLocation(Location location){
        for(Location locations : this.location){
            if(locations != null && location.getBlockX() == locations.getBlockX() && location.getBlockY() == locations.getBlockY() && location.getBlockZ() ==  locations.getBlockZ()){
                return true;
            }
        }
        return false;
    }

    private void teleport(Player player){
        Location location = this.location[checkPoint].clone();
        location.setYaw(jump.getJumpsSettings().get(checkPoint-1).getYaw());
        location.setY(location.getBlockY()+1);
        location.setX(location.getBlockX() + +0.5);
        location.setZ(location.getBlockZ() + +0.5);
        player.teleport(location);
    }

    private void fail(Player player){
        teleport(player);
        life--;
        player.sendMessage("§7Il vous reste §6"+life+"§7 vies");
        playerData.getScoreBoard().updateValue("life", "§aVie : §6"+life);
    }

    public void failManager(Player player){
        player.setFallDistance(0);
        if(checkPoint > 0 && life > 0){
            fail(player);
        } else {
            loose(player);
        }
    }

    private void loose(Player player){
        if(checkPoint == 0){
            player.teleport(jump.getJumpLocationPlayer());
            stopJump(player, false);
        } else {
            stopJump(player, true);
            player.sendMessage("§7Vous avez échoué )=");
        }
    }

    private void checkPoint(Location location, Player player){
        if(!contentLocation(location)){
            checkPoint++;
            this.location[checkPoint] = location;
            player.sendMessage("§7CheckPoint : §6"+checkPoint+" §e-> §7Vie §6+"+jump.getJumpDifficultySetting().get(jumpDifficulty.getName()).get(checkPoint));
            life += jump.getJumpDifficultySetting().get(jumpDifficulty.getName()).get(checkPoint);
            playerData.getScoreBoard().updateValue("life", "§aVie : §6"+life);
            threadJump.resetTimeCheckPoint();
            playerData.getScoreBoard().add("checkPoint_"+checkPoint, "§a"+(checkPoint+1)+" §7-> §700:00", 14-checkPoint);
        }
    }

    private void initJump(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(playerData.isFly()){
            player.sendMessage("§6Fly (§4Désactivé§6).");
        }
        if(playerData.isDoubleJumps()){
            player.sendMessage("§6Double Jump (§4Désactivé§6).");
        }
        if(playerData.isBuild() || player.getGameMode() == GameMode.CREATIVE){
            player.sendMessage("§6Mode build (§4Désactivé§6).");
            player.getInventory().clear();
            Navigator.navigator(playerData, player);
            player.setGameMode(GameMode.ADVENTURE);
        }
        player.setAllowFlight(false);
    }


    private void startJump(Player player, Jumps jumps){
        if(playerData.getParticle() != null){
            playerData.getParticle().removeParticle();
        }
        if(playerData.getGadget() != null){
            player.getInventory().clear(4);
        }
        this.jump = jumps;
        initJump(player);
        init();
        location[0] = jumps.getJumpLocationPlayer();
        player.sendMessage(jumps.getMessage().get(playerData.getLang()));
        if(jumpDifficulty == null){
            jumpDifficulty = jumps.getJumpDifficulty()[0];
        }
        player.sendMessage("§7Mode : "+jumpDifficulty.toString(playerData.getLang()));
        player.getInventory().setItem(3, new ItemBuilder(Material.BARRIER).withName("§6Fin du jump").withLore(new String[] {"§7Mettre fin au jump"}).get());
        player.getInventory().setItem(5, new ItemBuilder(Material.ARROW).withName("§6Respawn").withLore(new String[] {"§7Respawn au checkpoint précédent"}).get());
        playerData.setScoreBoard(player, new ScoreBoardJump(jumps, this));
        threadJump = new ThreadJump(playerData.getScoreBoard(), playerData.getNamePlayer());
        threadJump.start();
    }

    public void stopJump(Player player, boolean state){
        if(playerData.getParticle() != null){
            playerData.getParticle().restart();
        }
        if(playerData.getGadget() != null){
            player.getInventory().setItem(4, Products.getProduct(playerData.getGadget().getGadgets().getName()).getItem().get(playerData.getLang()).withLore(new String[] {}).get());
        }
        jump = null;
        if(playerData.isFly()){
            player.sendMessage("§6Fly (§aActivé§6).");
            player.setAllowFlight(true);
        }
        if(playerData.isDoubleJumps()){
            player.sendMessage("§6Double Jump (§aActivé§6).");
            player.setAllowFlight(true);
            playerData.setNbrDoubleJumps(0);
        }
        if(playerData.isBuild()){
            player.sendMessage("§6Mode build (§aActivé§6).");
            player.getInventory().clear();
            player.setGameMode(GameMode.CREATIVE);
            player.closeInventory();
        }
        player.getInventory().setItem(3, new ItemStack(Material.AIR));
        player.getInventory().setItem(5, new ItemStack(Material.AIR));
        ScoreBoard scoreBoard = new ScoreBoard(playerData);
        scoreBoard.updateGrade();
        scoreBoard.updateMoney();
        scoreBoard.updateBonus();
        PlayerData.getPlayerData(player.getName()).setScoreBoard(player, scoreBoard);
        if(state){
            playerData.teleportSpawn();
        }
    }

    public String toString(String lang){
        return "§7Quitte le jump §6"+jump.toString(lang);
    }*/
}
