package ua.nure.dorotenko.Practice6.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordContainer {
    Set<Word> words;

    public WordContainer() {
        words = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        for (String s : input.split("\\s+")) {
            addWord(new Word(s));
        }
    }

    public WordContainer(String filename) {
        words = new HashSet<>();
        String input = "";
        try {
            input = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : input.split("\\s+")) {
            addWord(new Word(s));
        }

    }

    public void addWord(Word word) {
        if (!words.add(word)) {
            for (Word w : words) {
                if (w.equals(word)) {
                    w.setFrequency(w.getFrequency() + 1);
                }
            }
        }
    }

    public List<Word> sortedList() {
        List<Word> result = new ArrayList<>(words);
        Collections.sort(result, new Comparator<Word>() {
            @Override
            public int compare(Word word, Word t1) {
                if (word.getFrequency() != t1.getFrequency()) {
                    return -(word.getFrequency() - t1.getFrequency());
                } else {
                    return word.getWord().compareTo(t1.getWord());
                }
            }
        });
        return result;
    }
}
