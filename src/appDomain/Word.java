/**
 * Word.java
 *
 * Represents a single word and tracks the occurrences of that word
 * across multiple text files and line numbers.
 */

package appDomain;

import java.io.Serializable;
import java.util.*;

/**
 * Models a word and its locations inside various files.
 */
public class Word implements Comparable<Word>, Serializable {
    private static final long serialVersionUID = 1L;

    private String wordText; // the actual word in lowercase
    private Map<String, List<Integer>> occurrences; // maps filename to list of line numbers

    /**
     * Constructs a new Word object and normalizes the text to lowercase.
     * Precondition: wordText must not be null.
     * Postcondition: wordText is stored in lowercase and occurrences map is initialized.
     */
    public Word(String wordText) {
        this.wordText = wordText.toLowerCase(); // normalize to lowercase for consistent comparison
        this.occurrences = new HashMap<>();
    }

    /**
     * Adds an occurrence of the word for a specific file and line number.
     * Precondition: filename is valid, lineNumber is positive.
     * During: Adds the line number to the list for the corresponding file.
     * Postcondition: The occurrence map is updated, allowing duplicates.
     */
    public void addOccurrence(String filename, int lineNumber) {
        occurrences.putIfAbsent(filename, new ArrayList<>());
        occurrences.get(filename).add(lineNumber);
    }

    /**
     * Retrieves the text of the word.
     * Precondition: None.
     * Postcondition: Returns the lowercase version of the word text.
     * 
     * @return the word text
     */
    public String getWordText() {
        return wordText;
    }

    /**
     * Retrieves the map of occurrences for this word.
     * Precondition: None.
     * Postcondition: Returns the mapping of file names to line number lists.
     * 
     * @return map of file names to line numbers
     */
    public Map<String, List<Integer>> getOccurrences() {
        return occurrences;
    }

    /**
     * Calculates the total number of times the word appears across all files.
     * Precondition: None.
     * Postcondition: Returns the total count of all line numbers stored.
     * 
     * @return the total number of occurrences
     */
    public int getFrequency() {
        return occurrences.values().stream().mapToInt(List::size).sum();
    }

    /**
     * Compares this word to another based on alphabetical order.
     * Precondition: other is not null.
     * Postcondition: Returns a negative, zero, or positive integer.
     * 
     * @param other the other word to compare
     * @return result of the comparison
     */
    @Override
    public int compareTo(Word other) {
        return this.wordText.compareTo(other.wordText);
    }

    /**
     * Checks if another object is equal to this word based on text.
     * Precondition: obj can be any object.
     * Postcondition: Returns true if both words match ignoring case sensitivity.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            return this.wordText.equals(((Word) obj).wordText);
        }
        return false;
    }

    /**
     * Computes a hash code for the word.
     * Precondition: None.
     * Postcondition: Hash code based on lowercase word text is returned.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return wordText.hashCode();
    }

    /**
     * Returns the string representation of the word.
     * Precondition: None.
     * Postcondition: Returns the word text.
     * 
     * @return the word as a string
     */
    @Override
    public String toString() {
        return wordText;
    }
}
