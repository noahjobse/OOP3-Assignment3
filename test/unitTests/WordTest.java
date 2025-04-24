package unitTests;

import appDomain.Word;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class WordTest {
    private Word word;

    @Before
    public void setUp() {
        word = new Word("Example");
    }

    @Test
    public void testWordTextNormalization() {
        assertEquals("example", word.getWordText()); // lowercase
    }

    @Test
    public void testAddOccurrenceCreatesNewEntry() {
        word.addOccurrence("file1.txt", 1);
        Map<String, List<Integer>> occurrences = word.getOccurrences();
        assertTrue(occurrences.containsKey("file1.txt"));
        assertEquals(1, occurrences.get("file1.txt").get(0).intValue());
    }

    @Test
    public void testAddOccurrenceAppendsLine() {
        word.addOccurrence("file1.txt", 1);
        word.addOccurrence("file1.txt", 5);
        List<Integer> lines = word.getOccurrences().get("file1.txt");
        assertEquals(2, lines.size());
        assertTrue(lines.contains(1));
        assertTrue(lines.contains(5));
    }

    @Test
    public void testGetFrequencyAcrossFiles() {
        word.addOccurrence("file1.txt", 1);
        word.addOccurrence("file1.txt", 2);
        word.addOccurrence("file2.txt", 3);
        assertEquals(3, word.getFrequency());
    }

    @Test
    public void testCompareToAndEquality() {
        Word other = new Word("example");
        assertEquals(0, word.compareTo(other));
        assertTrue(word.equals(other));
        assertEquals(word.hashCode(), other.hashCode());
    }
}
