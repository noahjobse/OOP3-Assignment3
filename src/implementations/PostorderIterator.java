package implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import utilities.Iterator;

public class PostorderIterator<E> implements Iterator<E> {
    private List<E> elements;
    private int currentIndex;

    public PostorderIterator(BSTreeNode<E> root) {
        elements = new ArrayList<>();
        postorderTraversal(root);
        currentIndex = 0;
    }

    private void postorderTraversal(BSTreeNode<E> node) {
        if (node != null) {
            postorderTraversal(node.getLeft());
            postorderTraversal(node.getRight());
            elements.add(node.getElement()); // Visit root last
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
