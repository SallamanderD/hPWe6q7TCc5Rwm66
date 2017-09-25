package ua.nure.dorotenko.Practice6.part5;

public class Part5 {

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(new Tree.Node<>(3));
        System.out.println(tree.add(3));

        System.out.println("~~~~~~~");
        tree.add(new Integer[]{1, 2, 5, 4, 6, 0});
        tree.add(-3);
        tree.printNode();
        tree.remove(5);
        tree.printNode();

    }

}
