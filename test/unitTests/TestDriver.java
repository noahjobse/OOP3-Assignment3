package unitTests;

import implementations.BSTree;

import utilities.Iterator;

public class TestDriver {
    public static void main(String[] args) {
        // Build a manual small tree
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

        System.out.println("Inorder traversal (alphabetical):");
        Iterator<String> inorder = tree.inorderIterator();
        while (inorder.hasNext()) {
            System.out.print(inorder.next() + " ");
        }

        System.out.println("\n\nPreorder traversal:");
        Iterator<String> preorder = tree.preorderIterator();
        while (preorder.hasNext()) {
            System.out.print(preorder.next() + " ");
        }

        System.out.println("\n\nPostorder traversal:");
        Iterator<String> postorder = tree.postorderIterator();
        while (postorder.hasNext()) {
            System.out.print(postorder.next() + " ");
        }
    }
}
