package ua.nure.dorotenko.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static void main(String[] args) {
        String input = Util.readFile("part3.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input){
        Pattern p = Pattern.compile("(?mU)((.)\\w*)\\s?");
        Matcher matcher = p.matcher(input);
        StringBuffer result = new StringBuffer(input);
        while (matcher.find()){
            if(matcher.group(1).length() > 2){
                result.replace(matcher.start(), matcher.start() + 1, matcher.group(2).toUpperCase());
            }
        }
        return result.toString();
    }
}