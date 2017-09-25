package ua.nure.dorotenko.Practice6.part1;

public class Part1 {

    public static void main(String[] args) {
        WordContainer wc = new WordContainer();
        for (Word w : wc.sortedList()) {
            System.out.println(w.getWord() + " : " + w.getFrequency());
        }
    }

}
