package appDomain;

import implementations.BSTree;
import utilities.BSTreeADT;
import utilities.Iterator;

import java.io.*;
import java.util.*;

public class WordTracker {
    private static final String REPO_FILE = "repository.ser";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar WordTracker.jar <input.txt> -pf|-pl|-po [-f<output.txt>]");
            return;
        }

        String inputFile = args[0];
        String flag = args[1];
        String outputFile = (args.length == 3 && args[2].startsWith("-f")) ? args[2].substring(2) : null;

        BSTreeADT<Word> bst = loadTree();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f.,!?;:\"()[]{}<>");

                while (tokenizer.hasMoreTokens()) {
                    String wordText = tokenizer.nextToken().toLowerCase();
                    Word temp = new Word(wordText);
                    Word found = (bst.contains(temp)) ? bst.search(temp).getElement() : null;

                    if (found != null) {
                        found.addOccurrence(inputFile, lineNumber);
                    } else {
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

        saveTree(bst);
        System.out.println("✅ File processed and tree updated.");

        // === Output generation ===
        Iterator<Word> iterator = bst.inorderIterator();
        StringBuilder output = new StringBuilder();

        while (iterator.hasNext()) {
            Word word = iterator.next();
            output.append(word.getWordText());

            Map<String, List<Integer>> occurrences = word.getOccurrences();

            if (flag.equals("-pf")) {
                output.append(" - ").append(occurrences.keySet());
            } else if (flag.equals("-pl")) {
                for (String file : occurrences.keySet()) {
                    output.append("\n  ").append(file).append(": ").append(occurrences.get(file));
                }
            } else if (flag.equals("-po")) {
                for (String file : occurrences.keySet()) {
                    output.append("\n  ").append(file).append(": ").append(occurrences.get(file));
                }
                output.append("\n  Total occurrences: ").append(word.getFrequency());
            }

            output.append("\n");
        }

        if (outputFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(output.toString());
                System.out.println("📁 Output written to file: " + outputFile);
            } catch (IOException e) {
                System.err.println("⚠️ Failed to write to output file: " + e.getMessage());
            }
        } else {
            System.out.println(output.toString());
        }
    }

    private static BSTreeADT<Word> loadTree() {
        File file = new File(REPO_FILE);
        if (!file.exists()) return new BSTree<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (BSTreeADT<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("⚠️ Failed to load existing tree. Starting fresh.");
            return new BSTree<>();
        }
    }

    private static void saveTree(BSTreeADT<Word> tree) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(REPO_FILE))) {
            out.writeObject(tree);
        } catch (IOException e) {
            System.err.println("⚠️ Failed to save tree: " + e.getMessage());
        }
    }
}
