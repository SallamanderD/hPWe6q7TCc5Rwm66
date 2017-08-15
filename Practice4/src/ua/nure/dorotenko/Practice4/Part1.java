package ua.nure.dorotenko.Practice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String FILE_NAME = "part1.txt";
    private static final String REGEXP = "(?U)\\w{4,}";

    public static void main(String[] args) {
        try {
            System.out.println(toUpper(FILE_NAME));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static String toUpper(String fileName) throws IOException {
        List<String> input;
        StringBuilder result = new StringBuilder();
        input = Files.readAllLines(Paths.get(fileName));
        Pattern p = Pattern.compile(REGEXP, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m;
        for (String s : input) {
            m = p.matcher(s);
            while (m.find()) {
                s = s.replace(m.group(), m.group().toUpperCase());
            }
            result.append(s + System.lineSeparator());
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }
}
