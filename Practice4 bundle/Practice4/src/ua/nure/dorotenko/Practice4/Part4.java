package ua.nure.dorotenko.Practice4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {
    private static final String REGEXP = "(?U)[A-ZА-Я0-9].+?\\.";
    private List<String> input = new ArrayList<>();

    public Part4() throws IOException {
        String file = new String(Files.readAllBytes(Paths.get("part4.txt")), Charset.forName("cp1251"));
        Matcher m = Pattern.compile(REGEXP).matcher(file.replaceAll(System.lineSeparator(), ""));
        List<String> allMatches = new ArrayList<>();
        while (m.find()) {
            allMatches.add(m.group());
        }
        this.input = allMatches;
    }

    public static void main(String[] args) {
        Part4 part4;
        try {
            part4 = new Part4();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
        Iterator<String> iterator = part4.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int cursor = -1;
            @Override
            public boolean hasNext() {
                if (input.size() > cursor + 1) {
                    return true;
                }
                return false;
            }
            @Override
            public String next() {
                return input.get(++cursor);
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
