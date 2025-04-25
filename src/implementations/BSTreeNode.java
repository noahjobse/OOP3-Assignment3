/**
 * BSTreeNode.java
 *
 * Defines a node for use in a binary search tree (BST).
 * Each node stores a single element and links to its left and right child nodes.
 */

package implementations;

import java.io.Serializable;

/**
 * Represents a single node in a binary search tree structure.
 */
public class BSTreeNode<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    private E element;                // data stored in this node
    private BSTreeNode<E> left;        // link to the left child
    private BSTreeNode<E> right;       // link to the right child

    /**
     * Creates a new BSTreeNode containing the given element.
     * Precondition: element must not be null.
     * Postcondition: Node is created with null children.
     * 
     * @param element the value to store in the node
     */
    public BSTreeNode(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    /**
     * Retrieves the element stored in this node.
     * Precondition: None.
     * Postcondition: Returns the stored element.
     * 
     * @return element contained in the node
     */
    public E getElement() {
        return element;
    }

    /**
     * Retrieves the left child of this node.
     * Precondition: None.
     * Postcondition: Returns the left subtree link, or null if none exists.
     * 
     * @return left child node
     */
    public BSTreeNode<E> getLeft() {
        return left;
    }

    /**
     * Retrieves the right child of this node.
     * Precondition: None.
     * Postcondition: Returns the right subtree link, or null if none exists.
     * 
     * @return right child node
     */
    public BSTreeNode<E> getRight() {
        return right;
    }

    /**
     * Updates the element stored in this node.
     * Precondition: element must not be null.
     * Postcondition: The node's stored element is replaced.
     * 
     * @param element the new value to store
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Sets a new left child for this node.
     * Precondition: None.
     * Postcondition: Left link points to the specified node.
     * 
     * @param left the new left child node
     */
    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    /**
     * Sets a new right child for this node.
     * Precondition: None.
     * Postcondition: Right link points to the specified node.
     * 
     * @param right the new right child node
     */
    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }
}
