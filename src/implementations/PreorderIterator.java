/**
 * PreorderIterator.java
 *
 * Provides an iterator to traverse a binary search tree in pre-order sequence.
 * In pre-order traversal, each node is visited before its left and right children.
 */

package implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import utilities.Iterator;

/**
 * Preorder iterator for binary search trees.
 */
public class PreorderIterator<E> implements Iterator<E> {
    private List<E> elements;     // list storing the tree elements in traversal order
    private int currentIndex;     // points to the next element to return

    /**
     * Creates a preorder iterator starting at the given root node.
     * Precondition: root may be null if tree is empty.
     * Postcondition: Elements list populated in preorder sequence.
     * 
     * @param root the root node of the tree
     */
    public PreorderIterator(BSTreeNode<E> root) {
        elements = new ArrayList<>();
        preorderTraversal(root);
        currentIndex = 0;
    }

    /**
     * Recursively performs preorder traversal to collect elements.
     * Precondition: node may be null.
     * Postcondition: Root added first, followed by left and right subtrees.
     * 
     * @param node current node to visit
     */
    private void preorderTraversal(BSTreeNode<E> node) {
        if (node != null) {
            elements.add(node.getElement());
            preorderTraversal(node.getLeft());
            preorderTraversal(node.getRight());
        }
    }

    /**
     * Checks if there are any remaining elements to iterate.
     * Precondition: None.
     * Postcondition: Returns true if another element exists.
     * 
     * @return true if another element is available, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currentIndex < elements.size();
    }

    /**
     * Returns the next element in preorder sequence.
     * Precondition: hasNext() must be true.
     * Postcondition: Moves index forward by one.
     * 
     * @return next element from preorder traversal
     * @throws NoSuchElementException if no more elements exist
     */
    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elements.get(currentIndex++);
    }
}
