package implementations;

import java.util.ArrayList;
import utilities.Iterator;

import java.util.List;
import java.util.NoSuchElementException;

public class InorderIterator<E> implements Iterator<E> {
	
    private List<E> elements;
    private int currentIndex;

    public InorderIterator(BSTreeNode<E> root) {
        elements = new ArrayList<>();
        inorderTraversal(root);
        currentIndex = 0;
    }

    private void inorderTraversal(BSTreeNode<E> node) {
        if (node != null) {
            inorderTraversal(node.getLeft());
            elements.add(node.getElement());
            inorderTraversal(node.getRight());
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
