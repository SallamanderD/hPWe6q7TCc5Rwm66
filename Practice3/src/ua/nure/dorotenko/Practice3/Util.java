package ua.nure.dorotenko.Practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    private static final String ENCODING = "UTF-8";
    public static String readFile(String name){
        try{
            return new String(Files.readAllBytes(Paths.get(name)), ENCODING);
        } catch (IOException ex){
            System.out.println(ex);
        }
        return null;
    }
}
