package me.vorps.hub.dispatcher;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.vorps.fortycube.type.GameState;
import me.vorps.hub.Hub;
import me.vorps.hub.PlayerData;
import me.vorps.hub.thread.ThreadFile;
import me.vorps.hub.thread.ThreadServer;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Hub Created by Vorps on 01/04/2016 at 00:41.
 */
public class Dispatcher {

    private static @Getter HashMap<String, ArrayList<String>> listServer = new HashMap<>();

    public static void updateListServer(Player player){
        if(listServer.isEmpty()){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("GetServers");
            player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
        }
    }

    public static void listServer(String[] server){
        for(Type type : Type.values()){
            ArrayList<String> nameServer = new ArrayList<>();
            for(String name : server){
                if(type.parameter.type.equals(name.substring(0, name.length()-3).toLowerCase())){
                    nameServer.add(name);
                }
            }
            listServer.put(type.parameter.type, nameServer);
        }
    }

    @AllArgsConstructor
    public enum Type{
        RV(new Parameter("512M", "1024M", "rv", "Rush Volcano", 10000, 1)),
        TC(new Parameter("512M", "1024M", "tc", "The Culling", 10000, 1));

        @Getter Parameter parameter;

        private static Type getType(String type){
            Type resultType = null;
            for (Type typeList : Type.values()){
                if(typeList.parameter.type.equals(type)){
                    resultType = typeList;
                    break;
                }
            }
            return resultType;
        }
    }

    @AllArgsConstructor
    public static class Parameter {
        private String xms;
        private String xmx;
        private @Getter String type;
        private @Getter String typeLabel;
        private long time;
        private int nbrServer;
    }

    private @Getter static String path = Paths.get( System.getProperty("user.dir")).getParent().toString().substring(0, Paths.get( System.getProperty("user.dir")).getParent().toString().length()-3);
    public static final char DS = '/';

    public static void startServer(Type type, String nameServer, Player player){
        if(!ThreadServer.getServerStart().contains(nameServer)){
            try {
                Runtime.getRuntime().exec("screen -dmS "+nameServer+" java -Xms"+type.parameter.xms+" -Xmx"+type.parameter.xmx+" -jar spigot.jar", null,  new File(path+type.parameter.type+DS+nameServer+DS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void connectServer(String type, Player player, boolean play){
        int state = 0;
        String nameServer = null;
        try {
            for(String name : listServer.get(type)){
                me.vorps.fortycube.type.Parameter parameter = deSerialisation(path+type+DS+name);
                if((parameter.getCanPlay() && play) || (parameter.getCanSpect() && !play)){
                    state = 1;
                    nameServer = name;
                    break;
                }
            }
            if(state == 0 && play){
                Type type1 = Type.getType(type);
                for(int id = 1; id <= type1.parameter.nbrServer; id++){
                    String name;
                    if(id < 10){
                        name = type+"_0"+id;
                    } else {
                        name = type+"_"+id;
                    }
                    name = name.toUpperCase();
                    me.vorps.fortycube.type.Parameter parameter = deSerialisation(path+type+DS+name);
                    if(!parameter.getCanPlay()){
                        state = 2;
                        nameServer = name;
                        break;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        file(player, state, type, nameServer, play);
    }

    public static void connectServer(String nameServer, Player player){
        int state = 0;
        try {
            System.out.println(path+nameServer.substring(0, nameServer.length()-3).toLowerCase()+DS+nameServer);
            me.vorps.fortycube.type.Parameter parameter = deSerialisation(path+nameServer.substring(0, nameServer.length()-3).toLowerCase()+DS+nameServer);
            if(parameter.getStatus() != GameState.STOP){
                if(parameter.getCanPlay() || parameter.getCanSpect()){
                    state = 1;
                }
            } else {
                Type type = Type.getType(nameServer.substring(0, nameServer.length()-3).toLowerCase());
                state = 2;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        file(player, state, nameServer.substring(0, nameServer.length()-3).toLowerCase(), nameServer, true);
    }

    public static void connectServer(String type, String map, Player player, boolean play) {
        int state = 0;
        String nameServer = null;
        try {
            for(String name : listServer.get(type)){
                me.vorps.fortycube.type.Parameter parameter = deSerialisation(path+type+DS+name.toUpperCase());
                if(parameter.getMap().equals(map) && ((parameter.getCanPlay() && play) || (parameter.getCanSpect() && !play))){
                    state = 1;
                    nameServer = name;
                    break;
                }
            }
            if(state == 0 && play){
                Type type1 = Type.getType(type);
                for(int id = 1; id <= type1.parameter.nbrServer; id++){
                    String name;
                    if(id < 10){
                        name = type+"_0"+id;
                    } else {
                        name = type+"_"+id;
                    }
                    name = name.toUpperCase();
                    me.vorps.fortycube.type.Parameter parameter = deSerialisation(path+type+DS+name);
                    if(!parameter.getCanPlay() && parameter.getMap().equals(map)){
                        state = 2;
                        nameServer =  name;
                        break;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        file(player, state, type, nameServer, play);
    }

    public static me.vorps.fortycube.type.Parameter deSerialisation(String path) throws Exception{
        me.vorps.fortycube.type.Parameter parameter = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path+"/game_parameter/parameter.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            parameter = (me.vorps.fortycube.type.Parameter) objectInputStream.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }
        return parameter;
    }

    public static me.vorps.hub.dispatcher.Parameter deSerialisationHub(String path) throws Exception{
        me.vorps.hub.dispatcher.Parameter parameter = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path+"/parameter/parameter.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            parameter = (me.vorps.hub.dispatcher.Parameter) objectInputStream.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }
        return parameter;
    }

    public static void file(Player player, int state, String type, String nameServer, boolean play){
        System.out.println(PlayerData.getPlayerData(player.getName()).getFile());
        if(PlayerData.getPlayerData(player.getName()).getFile() == null || !PlayerData.getPlayerData(player.getName()).getFile().getNameServer().equals(nameServer)){
            player.sendMessage("§e[§6Files§e]§f -> §eVous rejoigner la file §a"+Type.getType(type).getParameter().typeLabel);
            switch (state){
                case 0:
                    if(play){
                        player.sendMessage("§e[§6Files§e] §f§cAucun server n'est disponible pour le moment");
                    } else {
                        player.sendMessage("§e[§6Files§e] §f§cAucune partie en cour");
                    }
                    break;
                case 1:
                    String message = "dans §7"+PlayerData.getPlayerData(player.getName()).getGrade().getPendingGame()+"§e secondes";
                    if(PlayerData.getPlayerData(player.getName()).getGrade().getPendingGame() == 0){
                        message = "";
                    }
                    player.sendMessage("§e[§6Files§e] §f§eConnection a la partie"+message);
                    PlayerData.getPlayerData(player.getName()).setFile(new ThreadFile(nameServer,PlayerData.getPlayerData(player.getName()).getGrade().getPendingGame(), player));
                    PlayerData.getPlayerData(player.getName()).getFile().start();
                    break;
                case 2:
                    startServer(Type.getType(type), nameServer, player);
                    PlayerData.getPlayerData(player.getName()).setFile(new ThreadServer(Type.getType(type).parameter.time, player, nameServer));
                    PlayerData.getPlayerData(player.getName()).getFile().start();
                    player.sendMessage("§e[§6Files§e] §f§eConnection a la partie dans §7"+Type.getType(type).getParameter().time/1000+"§e secondes");
                    break;
                default:
                    break;
            }
        }
    }
}