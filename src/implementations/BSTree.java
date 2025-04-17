package implementations;

import java.io.Serializable;
import java.util.Iterator;
import utilities.BSTreeADT;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>, Serializable {
    private static final long serialVersionUID = 1L;

    private BSTreeNode<E> root;
    private int size;

    public BSTree() {
        root = null;
        size = 0;
    }

    // New constructor to fix the test
    public BSTree(E entry) {
        root = new BSTreeNode<>(entry);
        size = 1;
    }

    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException {
        if (root == null) {
            throw new NullPointerException("Tree is empty. No root node.");
        }
        return root;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException();
        }
        return search(entry) != null;
    }

    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException();
        }
        return search(root, entry);
    }

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

    @Override
    public utilities.Iterator inorderIterator() {
        return new InorderIterator<>(root);
    }

    @Override
    public utilities.Iterator<E> preorderIterator() {
        return new PreorderIterator<>(root);
    }

    @Override
    public utilities.Iterator<E> postorderIterator() {
        return new PostorderIterator<>(root);
    }
}
