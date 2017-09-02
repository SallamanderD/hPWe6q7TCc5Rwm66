package ua.nure.dorotenko.Practice4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kingsdwarf on 25.07.17.
 */
public class Part3 {
    private static final String SPLITTER = "\\s+";
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        List<String> input = new ArrayList<>();
        try{
            input = Files.readAllLines(Paths.get("part3.txt"), Charset.forName("cp1251"));
        } catch (IOException ex){
            System.out.println(ex);
        }
        Scanner sc = new Scanner(System.in, "utf-8");
        String cmd = sc.nextLine();
        while (!cmd.equals("stop")){
            switch (cmd) {
                case "char":
                    for(String s : input){
                        for(String str : s.split(SPLITTER)){
                            if(str.length() == 1 && Character.isAlphabetic(str.charAt(0))){
                                System.out.print(str + " ");
                            }
                        }
                    }
                    break;
                case "String":
                    for(String s : input){
                        for(String str : s.split(SPLITTER)){
                            if(!str.matches("(?U).*\\d+.*") && str.length() != 1){
                                System.out.print(str + " ");
                            }
                        }
                    }
                    break;
                case "int":
                    for(String s : input){
                        for(String str : s.split(SPLITTER)){
                            if(str.matches("^-?\\d+$")){
                                System.out.print(str + " ");
                            }
                        }
                    }
                    break;
                case "double":
                    for(String s : input){
                        for(String str : s.split(SPLITTER)){
                            if(str.matches("^-?\\.?\\d+\\.?\\d*") && str.contains(".")){
                                System.out.print(str + " ");
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Unknown Command");
                    break;
            }
            System.out.println();
            cmd = sc.nextLine();
        }
    }
}
