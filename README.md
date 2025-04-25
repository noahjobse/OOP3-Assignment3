OOP3 Assignment 3 – WordTracker
Authors Chris Racicot, Noah Jobse, Jacob Jobse, Parth Dave
Version 2025-04-25

Overview The WordTracker program processes .txt files and tracks all the words inside. It records which files the words appear in, on which lines, and how often they occur.
The data is stored inside a binary search tree (BST) and preserved between runs using a serialized repository file.

How to Run WordTracker

You’ll need:

Java (JDK 17 or compatible)

The provided WordTracker.jar

Text files (.txt) you wish to analyze

Run the program using this command format:

java -jar WordTracker.jar <input.txt> -pf|-pl|-po [-f<output.txt>]

Command Line Flags

-pf : Display each word and the file(s) it appears in.

-pl : Display each word, file(s), and line numbers where it appears.

-po : Display each word, file(s), line numbers, and the total frequency.

-f<output.txt> : (Optional) Save the output into a file instead of printing to the console.

Examples

Running WordTracker with different options:

java -jar WordTracker.jar res/test1.txt -pf

java -jar WordTracker.jar res/test2.txt -pl -foutput.txt

java -jar WordTracker.jar res/test3.txt -po -fresults.txt

How the Program Works

Reads a .txt file and extracts every word.

Records where each word appears (file name + line numbers).

Stores words alphabetically in a Binary Search Tree (BST).

Saves all collected data to repository.ser for use in future runs.

Merges data seamlessly when multiple files are processed over time.

Additional Information

The word tracking logic is handled in WordTracker.java.

Javadoc documentation for the project is available in the /doc folder.

Unit tests for key components are included under /unitTests.
