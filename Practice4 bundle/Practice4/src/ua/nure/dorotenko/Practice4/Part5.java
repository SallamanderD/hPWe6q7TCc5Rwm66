package ua.nure.dorotenko.Practice4;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "cp1251");
        String command = sc.nextLine();
        while(!command.equals("stop")){
            String[] str = command.split("\\s+");
            ResourceBundle bundle = ResourceBundle.getBundle("resources", new Locale(str[1]));
            System.out.println(bundle.getString(str[0]));
            command = sc.nextLine();
        }
    }
}
