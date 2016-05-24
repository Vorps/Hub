package me.vorps.hub.dispatcher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

/**
 * Project GameManager Created by Vorps on 05/04/2016 at 00:39.
 */
public class ServerParameter {
    private static String path = Paths.get( System.getProperty("user.dir")).toString();

    public static void serialisation(Parameter parameter){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path+"/parameter/parameter.ser");
            ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
            objectInputStream.writeObject(parameter);
            objectInputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
