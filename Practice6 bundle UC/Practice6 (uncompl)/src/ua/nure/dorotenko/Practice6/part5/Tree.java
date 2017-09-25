package ua.nure.dorotenko.Practice6.part5;

import java.util.*;

public class Tree<T extends Comparable> {
    public Node<T> root;

    public Tree(Node<T> root) {
        this.root = root;
    }

    public static class Node<T extends Comparable>{
        Node left;
        Node right;
        T data;
        Node(T newData) {
            left = null;
            right = null;
            data = newData;
        }
    }

    public boolean add(T data) {
        Node<T> result = add(root, data);
        if(result == null){
            return false;
        }
        root = result;
        return true;
    }

    public void add(T[] elements){
        for(T t : elements){
            add(t);
        }
    }

    public void remove(T value) {
        this.root = remove(this.root, value);
    }

    public Node remove(Node node, T value) {
        if(node.data.compareTo(value) < 0) {
            node.right = remove(node.right, value);
        } else if(node.data.compareTo(value) > 0) {
            node.left = remove(node.left, value);
        } else {
            if(node.right == null) {
                return node.left;
            }
            if(node.left == null) {
                return node.right;
            }
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    private Node min(Node node) {
        if(node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    private Node deleteMin(Node node) {
        if(node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private Node add(Node<T> node, T data) {
        if (node==null) {
            node = new Node(data);
        }
        else {
            if (data.compareTo(node.data) < 0) {
                node.left = add(node.left, data);
            }else if(data.compareTo(node.data) > 0) {
                node.right = add(node.right, data);
            } else{
                return null;
            }
        }
        return(node);

    }

    public <T extends Comparable<?>> void printNode() {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}

