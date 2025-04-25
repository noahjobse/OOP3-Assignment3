/**
 * WordTracker.java
 * Authors: Chris Racicot, Noah Jobse, Jacob Jobse, Parth Dave
 * Version 2025-04-25
 * Course: CPRG 304 - OOP3
 * 
 * A program that reads text files, extracts words, and tracks 
 * their occurrences using a Binary Search Tree (BST). It supports options 
 * to display files, line numbers, and frequency counts, and can persist data.
 */

package appDomain;

import implementations.BSTree;
import utilities.BSTreeADT;
import utilities.Iterator;

import java.io.*;
import java.util.*;

public class WordTracker {

    /** Serialized repository file for persisting the tree across sessions */
    private static final String REPO_FILE = "repository.ser";

    /**
     * Main entry point for the WordTracker application.
     * Parses arguments, loads tree, processes the input file,
     * updates the tree, saves it, and then prints output or writes to file.
     * 
     * @param args Command-line arguments (input file, flags, optional output file)
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar WordTracker.jar <input.txt> -pf|-pl|-po [-f<output.txt>]");
            return;
        }

        String inputFile = args[0];
        String flag = args[1];
        String outputFile = (args.length == 3 && args[2].startsWith("-f")) ? args[2].substring(2) : null;

        // Load previously saved BST or start a new one if repo file doesn't exist
        BSTreeADT<Word> bst = loadTree();

        // Parse the input file and update the BST accordingly
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                // Tokenize line by stripping punctuation and whitespace
                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f.,!?;:\"()[]{}<>");

                while (tokenizer.hasMoreTokens()) {
                    String wordText = tokenizer.nextToken().toLowerCase();
                    Word temp = new Word(wordText);

                    // Search for existing word in the BST
                    Word found = (bst.contains(temp)) ? bst.search(temp).getElement() : null;

                    if (found != null) {
                        // If found, add this new occurrence to existing Word
                        found.addOccurrence(inputFile, lineNumber);
                    } else {
                        // Otherwise, create new Word entry and add to BST
                        temp.addOccurrence(inputFile, lineNumber);
                        bst.add(temp);
                    }
                }

                lineNumber++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Save updated tree back to the repository
        saveTree(bst);
        System.out.println("File processed and tree updated.");

        // Generate output in alphabetical order
        Iterator<Word> iterator = bst.inorderIterator();
        StringBuilder output = new StringBuilder();
        int wordIndex = 1;

        while (iterator.hasNext()) {
            Word word = iterator.next();
            output.append(wordIndex++).append(" Key : ").append(word.getWordText()).append("\n");

            Map<String, List<Integer>> occurrences = word.getOccurrences();

            if (flag.equals("-pf")) {
                for (String file : occurrences.keySet()) {
                    output.append("  Found in file: ").append(file).append("\n");
                }

            } else if (flag.equals("-pl")) {
                for (String file : occurrences.keySet()) {
                    output.append("  Found in file: ").append(file)
                          .append(" on lines: ").append(occurrences.get(file)).append("\n");
                }

            } else if (flag.equals("-po")) {
                for (String file : occurrences.keySet()) {
                    output.append("  Found in file: ").append(file)
                          .append(" on lines: ").append(occurrences.get(file)).append("\n");
                }
                output.append("  Total occurrences: ").append(word.getFrequency()).append("\n");
            }
        }

        // Output to file if -f flag is set, otherwise print to console
        if (outputFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(output.toString());
                System.out.println("Output written to file: " + outputFile);
            } catch (IOException e) {
                System.err.println("Failed to write to output file: " + e.getMessage());
            }
        } else {
            System.out.println(output.toString());
        }
    }

    /**
     * Loads a previously saved BSTree object from the serialized file.
     * 
     * @return the loaded BST, or a new BST if file does not exist or fails to load
     */
    private static BSTreeADT<Word> loadTree() {
        File file = new File(REPO_FILE);
        if (!file.exists()) return new BSTree<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (BSTreeADT<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Could not load existing repository. Starting new tree.");
            return new BSTree<>();
        }
    }

    /**
     * Serializes the BSTree to the repository file for reuse across executions.
     * 
     * @param tree the BSTree instance to save
     */
    private static void saveTree(BSTreeADT<Word> tree) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(REPO_FILE))) {
            out.writeObject(tree);
        } catch (IOException e) {
            System.err.println("Failed to save tree: " + e.getMessage());
        }
    }
}
