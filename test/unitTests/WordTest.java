/**
 * WordTest.java
 *
 * Unit tests for the Word class to verify word storage, normalization,
 * occurrence tracking, frequency calculation, and comparison behavior.
 */

package unitTests;

import appDomain.Word;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests core functionality of the Word class.
 */
public class WordTest {
    private Word word;    // Word object used for testing

    /**
     * Sets up a fresh Word object before each test runs.
     * Precondition: None.
     * Postcondition: Word object initialized with text "Example".
     */
    @Before
    public void setUp() {
        word = new Word("Example");
    }

    /**
     * Tests that the Word class correctly normalizes input to lowercase.
     * Precondition: Word is initialized with mixed-case text.
     * Postcondition: getWordText() returns all lowercase.
     */
    @Test
    public void testWordTextNormalization() {
        assertEquals("example", word.getWordText());
    }

    /**
     * Tests that adding a new occurrence creates a new entry in the map.
     * Precondition: No prior occurrences.
     * Postcondition: Map should have the file and line number.
     */
    @Test
    public void testAddOccurrenceCreatesNewEntry() {
        word.addOccurrence("file1.txt", 1);
        Map<String, List<Integer>> occurrences = word.getOccurrences();
        assertTrue(occurrences.containsKey("file1.txt"));
        assertEquals(1, occurrences.get("file1.txt").get(0).intValue());
    }

    /**
     * Tests that adding multiple occurrences appends new line numbers.
     * Precondition: Initial occurrence already exists.
     * Postcondition: List for the file contains multiple line numbers.
     */
    @Test
    public void testAddOccurrenceAppendsLine() {
        word.addOccurrence("file1.txt", 1);
        word.addOccurrence("file1.txt", 5);
        List<Integer> lines = word.getOccurrences().get("file1.txt");
        assertEquals(2, lines.size());
        assertTrue(lines.contains(1));
        assertTrue(lines.contains(5));
    }

    /**
     * Tests that frequency counts total occurrences across all files.
     * Precondition: Word added to multiple files and lines.
     * Postcondition: Frequency reflects all occurrences combined.
     */
    @Test
    public void testGetFrequencyAcrossFiles() {
        word.addOccurrence("file1.txt", 1);
        word.addOccurrence("file1.txt", 2);
        word.addOccurrence("file2.txt", 3);
        assertEquals(3, word.getFrequency());
    }

    /**
     * Tests that comparison and equality methods are consistent.
     * Precondition: Two Word objects with identical text.
     * Postcondition: They compare equal and have matching hash codes.
     */
    @Test
    public void testCompareToAndEquality() {
        Word other = new Word("example");
        assertEquals(0, word.compareTo(other));
        assertTrue(word.equals(other));
        assertEquals(word.hashCode(), other.hashCode());
    }
}
