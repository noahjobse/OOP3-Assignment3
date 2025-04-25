/**
 * TestDriver.java
 *
 * A simple testing program that builds a small binary search tree,
 * then prints the contents in in-order, pre-order, and post-order traversals.
 */

package unitTests;

import implementations.BSTree;
import utilities.Iterator;

/**
 * Manually tests basic functionality of the BSTree and iterators.
 */
public class TestDriver {
    public static void main(String[] args) {
        // Precondition: None. We are manually building a small tree.
        // Postcondition: Tree is populated and traversals are printed.

        // Create a new binary search tree and add some sample strings
        BSTree<String> tree = new BSTree<>();
        tree.add("Hello");
        tree.add("my");
        tree.add("name");
        tree.add("is");
        tree.add("Kitty");
        tree.add("it's");
        tree.add("nice");
        tree.add("to");
        tree.add("meet");
        tree.add("you");

        // Perform and print in-order traversal
        System.out.println("Inorder traversal (alphabetical):");
        Iterator<String> inorder = tree.inorderIterator();
        while (inorder.hasNext()) {
            System.out.print(inorder.next() + " ");
        }

        // Perform and print pre-order traversal
        System.out.println("\n\nPreorder traversal:");
        Iterator<String> preorder = tree.preorderIterator();
        while (preorder.hasNext()) {
            System.out.print(preorder.next() + " ");
        }

        // Perform and print post-order traversal
        System.out.println("\n\nPostorder traversal:");
        Iterator<String> postorder = tree.postorderIterator();
        while (postorder.hasNext()) {
            System.out.print(postorder.next() + " ");
        }
    }
}
