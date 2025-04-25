/**
 * PostorderIterator.java
 *
 * Provides an iterator to traverse a binary search tree in post-order sequence.
 * Post-order traversal visits left child, right child, and then the node itself.
 */

package implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import utilities.Iterator;

/**
 * Postorder iterator for binary search trees.
 */
public class PostorderIterator<E> implements Iterator<E> {
    private List<E> elements;    // stores elements in post-order
    private int currentIndex;    // keeps track of next element to return

    /**
     * Constructs a post-order iterator starting from the given root node.
     * Precondition: root may be null if the tree is empty.
     * Postcondition: Elements are loaded into the list in post-order.
     * 
     * @param root the root of the tree
     */
    public PostorderIterator(BSTreeNode<E> root) {
        elements = new ArrayList<>();
        postorderTraversal(root);
        currentIndex = 0;
    }

    /**
     * Helper method to recursively perform post-order traversal.
     * Precondition: node may be null.
     * Postcondition: Elements are added after visiting left and right subtrees.
     * 
     * @param node the current node being processed
     */
    private void postorderTraversal(BSTreeNode<E> node) {
        if (node != null) {
            postorderTraversal(node.getLeft());
            postorderTraversal(node.getRight());
            elements.add(node.getElement());
        }
    }

    /**
     * Checks if there are remaining elements to visit.
     * Precondition: None.
     * Postcondition: Returns true if more elements exist.
     * 
     * @return true if next() can be called, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currentIndex < elements.size();
    }

    /**
     * Returns the next element in post-order sequence.
     * Precondition: hasNext() must return true.
     * Postcondition: Current index moves forward after returning the element.
     * 
     * @return next element from the traversal
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
