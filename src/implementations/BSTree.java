/**
 * BSTree.java
 *
 * Implements a generic binary search tree (BST) that stores comparable elements.
 * Supports adding, searching, removing minimum and maximum elements,
 * and providing different types of iterators for tree traversal.
 */

package implementations;

import java.io.Serializable;
import java.util.Iterator;
import utilities.BSTreeADT;

/**
 * Generic binary search tree structure for managing comparable elements.
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>, Serializable {
    private static final long serialVersionUID = 1L;

    private BSTreeNode<E> root; // root node of the BST
    private int size;           // total number of elements in the tree

    /**
     * Constructs an empty binary search tree.
     * Precondition: None.
     * Postcondition: Tree is initialized with no elements.
     */
    public BSTree() {
        root = null;
        size = 0;
    }

    /**
     * Constructs a binary search tree containing a single entry.
     * Precondition: entry must not be null.
     * Postcondition: Tree has one node as its root.
     * 
     * @param entry the initial element to insert
     */
    public BSTree(E entry) {
        root = new BSTreeNode<>(entry);
        size = 1;
    }

    /**
     * Returns the root node of the tree.
     * Precondition: Tree is not empty.
     * Postcondition: Root node is returned.
     * 
     * @return root node
     * @throws NullPointerException if the tree is empty
     */
    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException {
        if (root == null) {
            throw new NullPointerException("Tree is empty. No root node.");
        }
        return root;
    }

    /**
     * Calculates and returns the height of the tree.
     * Precondition: None.
     * Postcondition: Returns an integer representing height.
     * 
     * @return height of the tree
     */
    @Override
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * Helper method to recursively calculate height of a subtree.
     * 
     * @param node starting node
     * @return height of subtree rooted at node
     */
    private int getHeight(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    /**
     * Returns the total number of elements stored in the tree.
     * Precondition: None.
     * Postcondition: Size value is returned.
     * 
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks whether the tree is empty.
     * Precondition: None.
     * Postcondition: Returns true if tree has no elements.
     * 
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from the tree.
     * Precondition: None.
     * Postcondition: Tree is cleared and size is reset to 0.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Checks if a specific entry exists in the tree.
     * Precondition: entry must not be null.
     * Postcondition: Returns true if found, false otherwise.
     * 
     * @param entry the element to search for
     * @return true if entry exists, false otherwise
     * @throws NullPointerException if entry is null
     */
    @Override
    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException();
        }
        return search(entry) != null;
    }

    /**
     * Searches for a node containing the given entry.
     * Precondition: entry must not be null.
     * Postcondition: Returns the node if found, otherwise null.
     * 
     * @param entry element to locate
     * @return node containing the element or null
     * @throws NullPointerException if entry is null
     */
    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException();
        }
        return search(root, entry);
    }

    /**
     * Recursively searches for an element starting from a given node.
     * 
     * @param node starting node
     * @param entry element to find
     * @return node containing the element or null
     */
    private BSTreeNode<E> search(BSTreeNode<E> node, E entry) {
        if (node == null) {
            return null;
        }

        int cmp = entry.compareTo(node.getElement());

        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return search(node.getLeft(), entry);
        } else {
            return search(node.getRight(), entry);
        }
    }

    /**
     * Adds a new element to the tree in the correct position.
     * Precondition: entry must not be null.
     * Postcondition: Tree is updated and size increases if added successfully.
     * 
     * @param entry the element to add
     * @return true if added, false if duplicate
     * @throws NullPointerException if entry is null
     */
    @Override
    public boolean add(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException();
        }

        if (root == null) {
            root = new BSTreeNode<>(entry);
            size++;
            return true;
        } else {
            boolean added = add(root, entry);
            if (added) {
                size++;
            }
            return added;
        }
    }

    /**
     * Helper method to recursively insert a new element.
     * 
     * @param node current node
     * @param entry element to add
     * @return true if successfully inserted
     */
    private boolean add(BSTreeNode<E> node, E entry) {
        int cmp = entry.compareTo(node.getElement());

        if (cmp == 0) {
            return false; // no duplicates allowed
        } else if (cmp < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTreeNode<>(entry));
                return true;
            } else {
                return add(node.getLeft(), entry);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new BSTreeNode<>(entry));
                return true;
            } else {
                return add(node.getRight(), entry);
            }
        }
    }

    /**
     * Removes the smallest element from the tree.
     * Precondition: Tree must not be empty.
     * Postcondition: Minimum node is removed and returned.
     * 
     * @return node containing the minimum element or null if empty
     */
    @Override
    public BSTreeNode<E> removeMin() {
        if (root == null) {
            return null;
        }

        BSTreeNode<E> parent = null;
        BSTreeNode<E> current = root;

        while (current.getLeft() != null) {
            parent = current;
            current = current.getLeft();
        }

        if (parent == null) {
            root = current.getRight();
        } else {
            parent.setLeft(current.getRight());
        }

        size--;
        return current;
    }

    /**
     * Removes the largest element from the tree.
     * Precondition: Tree must not be empty.
     * Postcondition: Maximum node is removed and returned.
     * 
     * @return node containing the maximum element or null if empty
     */
    @Override
    public BSTreeNode<E> removeMax() {
        if (root == null) {
            return null;
        }

        BSTreeNode<E> parent = null;
        BSTreeNode<E> current = root;

        while (current.getRight() != null) {
            parent = current;
            current = current.getRight();
        }

        if (parent == null) {
            root = current.getLeft();
        } else {
            parent.setRight(current.getLeft());
        }

        size--;
        return current;
    }

    /**
     * Provides an in-order iterator over the tree.
     * Precondition: None.
     * Postcondition: Returns an iterator that visits elements in sorted order.
     * 
     * @return in-order iterator
     */
    @Override
    public utilities.Iterator inorderIterator() {
        return new InorderIterator<>(root);
    }

    /**
     * Provides a pre-order iterator over the tree.
     * Precondition: None.
     * Postcondition: Returns an iterator that visits root before children.
     * 
     * @return pre-order iterator
     */
    @Override
    public utilities.Iterator<E> preorderIterator() {
        return new PreorderIterator<>(root);
    }

    /**
     * Provides a post-order iterator over the tree.
     * Precondition: None.
     * Postcondition: Returns an iterator that visits children before root.
     * 
     * @return post-order iterator
     */
    @Override
    public utilities.Iterator<E> postorderIterator() {
        return new PostorderIterator<>(root);
    }
}
