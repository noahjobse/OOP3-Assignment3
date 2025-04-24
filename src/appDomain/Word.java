package appDomain;

import java.io.Serializable;
import java.util.*;

public class Word implements Comparable<Word>, Serializable {
    private static final long serialVersionUID = 1L;

    private String wordText;
    private Map<String, List<Integer>> occurrences; // filename → list of line numbers

    public Word(String wordText) {
        this.wordText = wordText.toLowerCase(); // normalize to lowercase
        this.occurrences = new HashMap<>();
    }

    public void addOccurrence(String fileName, int lineNumber) {
        occurrences.putIfAbsent(fileName, new ArrayList<>());
        occurrences.get(fileName).add(lineNumber);
    }

    public String getWordText() {
        return wordText;
    }

    public Map<String, List<Integer>> getOccurrences() {
        return occurrences;
    }

    public int getFrequency() {
        return occurrences.values().stream().mapToInt(List::size).sum();
    }

    @Override
    public int compareTo(Word other) {
        return this.wordText.compareTo(other.wordText);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            return this.wordText.equals(((Word) obj).wordText);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return wordText.hashCode();
    }

    @Override
    public String toString() {
        return wordText;
    }
}
