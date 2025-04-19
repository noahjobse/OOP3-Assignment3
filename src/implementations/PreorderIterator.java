package implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import utilities.Iterator;

public class PreorderIterator<E> implements Iterator<E> {
    private List<E> elements;
    private int currentIndex;

    public PreorderIterator(BSTreeNode<E> root) {
        elements = new ArrayList<>();
        preorderTraversal(root);
        currentIndex = 0;
    }

    private void preorderTraversal(BSTreeNode<E> node) {
        if (node != null) {
            elements.add(node.getElement()); // Visit root first
            preorderTraversal(node.getLeft());
            preorderTraversal(node.getRight());
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < elements.size();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elements.get(currentIndex++);
    }
}
