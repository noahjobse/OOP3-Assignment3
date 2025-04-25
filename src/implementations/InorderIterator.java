/**
 * InorderIterator.java
 *
 * Provides an iterator to traverse a binary search tree in in-order sequence.
 * The in-order traversal visits left child, node itself, then right child recursively.
 */

package implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import utilities.Iterator;

/**
 * Inorder iterator for binary search trees.
 */
public class InorderIterator<E> implements Iterator<E> {
    private List<E> elements;   // list to hold tree elements in order
    private int currentIndex;   // current position in the list

    /**
     * Builds an in-order iterator starting from the provided root.
     * Precondition: root may be null (empty tree).
     * Postcondition: List of elements is populated in in-order sequence.
     * 
     * @param root the root node of the tree
     */
    public InorderIterator(BSTreeNode<E> root) {
        elements = new ArrayList<>();
        inorderTraversal(root);
        currentIndex = 0;
    }

    /**
     * Recursive helper to perform in-order traversal and store elements.
     * Precondition: node may be null.
     * Postcondition: Elements are collected left-to-right.
     * 
     * @param node current node being visited
     */
    private void inorderTraversal(BSTreeNode<E> node) {
        if (node != null) {
            inorderTraversal(node.getLeft());
            elements.add(node.getElement());
            inorderTraversal(node.getRight());
        }
    }

    /**
     * Checks if there are more elements to iterate.
     * Precondition: None.
     * Postcondition: Returns true if more elements are available.
     * 
     * @return true if next element exists, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currentIndex < elements.size();
    }

    /**
     * Returns the next element in in-order sequence.
     * Precondition: hasNext() must be true.
     * Postcondition: Current index is advanced by one.
     * 
     * @return next element in the list
     * @throws NoSuchElementException if no elements remain
     */
    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elements.get(currentIndex++);
    }
}
