package ua.nure.dorotenko.Practice6.part1;

public class Word implements Comparable<Word> {

    private String word;

    private int frequency;

    public Word(String word) {
        this.word = word;
        frequency = 1;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Word) {
            if (this.getWord().equals(((Word) o).getWord()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 27;
        if (word != null) {
            result = result * 35 + word.hashCode();
        }
        return result;
    }

    @Override
    public int compareTo(Word word) {
        return this.getWord().compareTo(word.getWord());
    }
}
