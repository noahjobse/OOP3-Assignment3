/**
 * Word.java
 *
 * Defines a Word object that keeps track of the files and line numbers
 * where the word appears. Words are normalized to lowercase to ensure
 * consistent tracking and comparison.
 */

package appDomain;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a single word and all its occurrences across multiple files.
 */
public class Word implements Comparable<Word>, Serializable {
    private static final long serialVersionUID = 1L;

    private String wordText; // the text content of the word
    private Map<String, List<Integer>> occurrences; // map of filenames to line numbers

    /**
     * Creates a Word object with the given text.
     * Precondition: wordText is not null.
     * Postcondition: Word is initialized and stored in lowercase.
     * 
     * @param wordText the text of the word to store
     */
    public Word(String wordText) {
        this.wordText = wordText.toLowerCase();
        this.occurrences = new HashMap<>();
    }

    /**
     * Records a new appearance of the word.
     * Precondition: filename is not null, lineNumber is a positive integer.
     * During: If the file does not exist in the map, it is added.
     * Postcondition: The line number is stored under the correct file.
     * 
     * @param filename the file where the word was found
     * @param lineNumber the line number where the word appeared
     */
    public void addOccurrence(String filename, int lineNumber) {
        occurrences.putIfAbsent(filename, new ArrayList<>());
        occurrences.get(filename).add(lineNumber);
    }

    /**
     * Retrieves the word text.
     * Precondition: None.
     * Postcondition: Returns the lowercase word text.
     * 
     * @return the stored word text
     */
    public String getWordText() {
        return wordText;
    }

    /**
     * Retrieves the mapping of all files and line numbers.
     * Precondition: None.
     * Postcondition: Returns the internal map of occurrences.
     * 
     * @return map of filenames to line number lists
     */
    public Map<String, List<Integer>> getOccurrences() {
        return occurrences;
    }

    /**
     * Calculates how many times the word was recorded overall.
     * Precondition: None.
     * During: Sums all lists of line numbers.
     * Postcondition: Returns the total count of appearances.
     * 
     * @return total number of occurrences
     */
    public int getFrequency() {
        return occurrences.values().stream().mapToInt(List::size).sum();
    }

    /**
     * Compares this word to another word alphabetically.
     * Precondition: other is not null.
     * Postcondition: Returns negative, zero, or positive integer.
     * 
     * @param other the other Word object to compare against
     * @return comparison result
     */
    @Override
    public int compareTo(Word other) {
        return this.wordText.compareTo(other.wordText);
    }

    /**
     * Checks if two Word objects are equal based on their text.
     * Precondition: obj may be any object.
     * Postcondition: Returns true if obj is a Word with the same text.
     * 
     * @param obj the object to compare
     * @return true if the texts are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            return this.wordText.equals(((Word) obj).wordText);
        }
        return false;
    }

    /**
     * Generates a hash code based on the word text.
     * Precondition: None.
     * Postcondition: Returns hash code consistent with equals().
     * 
     * @return hash code for this Word
     */
    @Override
    public int hashCode() {
        return wordText.hashCode();
    }

    /**
     * Returns the word text as its string representation.
     * Precondition: None.
     * Postcondition: Returns the stored text.
     * 
     * @return the word as a String
     */
    @Override
    public String toString() {
        return wordText;
    }
}
