public class Tree<T extends Comparable<T>> {
	Node root;
	public Tree(T value){
		Node<T> node = new Node<>(value);
		node.left = null;
		node.right = null;
		this.root = node;
	}

	public void print(){
		System.out.println("      " + root.value + "      ");
	}

	class Node<T extends Comparable>{
		public Node(T value) {
			this.value = value;
		}
		T value;
		Node left;
		Node right;

		public boolean add(T value){
			if(this.value != null){
				if(this.value.compareTo(value) > 0){
					this.left.add(value);
				} else if(this.value.compareTo(value) < 0){
					this.right.add(value);
				} else if(this.value.compareTo(value) == 0){
					return false;
				}
			}
			this.value = value;
			return true;
		}
	}
}