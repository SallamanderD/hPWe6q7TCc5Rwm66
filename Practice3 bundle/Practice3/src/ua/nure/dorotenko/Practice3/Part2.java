package ua.nure.dorotenko.Practice3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part2 {
    public static void main(String[] args) {
        String input = Util.readFile("part2.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input){
        Pattern p = Pattern.compile("(?U)(\\w+)");
        Matcher m = p.matcher(input);
        List<String> words = new ArrayList<String>();
        while (m.find()) {
            words.add(m.group());
        }
        int minLength = words.get(0).length();
        int maxLength = words.get(0).length();
        List<String> shortest = new ArrayList<>();
        List<String> longest = new ArrayList<>();
        shortest.add(words.get(0));
        longest.add(words.get(0));
        for(int i = 1; i < words.size(); i++){
            if(words.get(i).length() < minLength){
                shortest.clear();
                shortest.add(words.get(i));
                minLength = words.get(i).length();
            } else if(words.get(i).length() == minLength && !shortest.contains(words.get(i))){
                shortest.add(words.get(i));
            }
            if(words.get(i).length() > maxLength){
                longest.clear();
                longest.add(words.get(i));
                maxLength = words.get(i).length();
            } else if(words.get(i).length() == maxLength && !longest.contains(words.get(i))){
                longest.add(words.get(i));
            }
        }
        StringBuilder result = new StringBuilder("Min: ");
        for(String str : shortest){
            result.append(str + ", ");
        }
        result.setLength(result.length() - 2);
        result.append(System.lineSeparator() + "Max: ");
        for(String str : longest){
            result.append(str + ", ");
        }
        result.setLength(result.length() - 2);
        return result.toString();
    }
}
