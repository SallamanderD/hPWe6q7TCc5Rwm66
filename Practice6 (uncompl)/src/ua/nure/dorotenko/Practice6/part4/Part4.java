package ua.nure.dorotenko.Practice6.part4;

import java.util.Arrays;

public class Part4 {
	
	public static void main(String[] args) {
		Graph<Integer> gr = new Graph<>();
		gr.addEdge(3, 7);
		gr.addEdge(7, 4);
		System.out.println(gr.getMap());
	}

}
