package ua.nure.dorotenko.Practice3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String LS = System.lineSeparator();
    private static final String REGEXP = "(?U)^(.+);(.+);(.+)$";

    public static void main(String[] args) {
        String input = Util.readFile("part1.txt");
        System.out.println("===CONVERT1===");
        System.out.println(convert1(input));
        System.out.println("===CONVERT2===");
        System.out.println(convert2(input));
        System.out.println("===CONVERT3===");
        System.out.println(convert3(input));
        System.out.println("===CONVERT4===");
        System.out.println(convert4(input));

    }

    private static String convert4(String input) {
        List<String> inputList = splitByLS(input);
        StringBuilder result = new StringBuilder(inputList.get(0) + ";Password" + LS);
        for(int i = 1; i < inputList.size(); i++){
            StringBuilder pass = new StringBuilder();
            for(int k = 0; k < 4; k++){
                pass.append(ThreadLocalRandom.current().nextInt(0, 10));
            }
            result.append(inputList.get(i) + ";" + pass + LS);
            pass.setLength(0);
        }
        return result.toString();
    }

    public static String convert1(String input){
        List<String> inputList = splitByLS(input);
        Pattern pattern = Pattern.compile(REGEXP);
        Matcher m;
        StringBuilder result = new StringBuilder();
        for(int i = 1; i < inputList.size(); i++){
            m = pattern.matcher(inputList.get(i));
            if(m.find()){
                result.append(m.group(1) + " ==> " + m.group(3) + LS);
            }
        }
        return result.toString();
    }

    public static String convert2(String input){
        List<String> inputList = splitByLS(input);
        Pattern pattern = Pattern.compile(REGEXP);
        Matcher m;
        StringBuilder result = new StringBuilder();
        for(int i = 1; i < inputList.size(); i++){
            m = pattern.matcher(inputList.get(i));
            if(m.find()){
                result.append(m.group(2) + " (email: " + m.group(3) + ")" + LS);
            }
        }
        return result.toString();
    }

    public static String convert3(String input){
        List<String> inputList = splitByLS(input);
        String domenRegexp = "@(.+)";
        List<String> domens = new ArrayList<>();
        Pattern pattern = Pattern.compile(domenRegexp);
        Matcher m;
        StringBuilder result = new StringBuilder();
        for(int i = 1; i < inputList.size(); i++){
            m = pattern.matcher(inputList.get(i));
            if (m.find() && !domens.contains(m.group(1))) {
                    domens.add(m.group(1));
            }
        }
        pattern = Pattern.compile(REGEXP);
        for(String domen : domens){
            result.append(domen + " ==> ");
            for(int i = 1; i < inputList.size(); i++){
                if(inputList.get(i).contains(domen)){
                    m = pattern.matcher(inputList.get(i));
                    m.find();
                    result.append(m.group(1) + ", ");
                }
            }
            result.setLength(result.length() - 2);
            result.append(LS);
        }
        return result.toString();

    }



    public static List<String> splitByLS(String input){
        return Arrays.asList(input.split(LS));
    }
}
