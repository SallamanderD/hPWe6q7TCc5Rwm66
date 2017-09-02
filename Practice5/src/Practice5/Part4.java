package Practice5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Part4 {
	public Part4(int[][] matrix){
		this.matrix = matrix;
	}
	public int[][] matrix;
	
	public static void main(String[] args) {
		Part4 p4 = new Part4(Part4.generate());
		System.out.println(p4.max());
		System.out.println(p4.maxSync());
	}

	public int max(){
		long current = System.currentTimeMillis();
		List<FindMax> lst = new ArrayList<>();
		for(int j = 0; j < matrix.length; j++){
			lst.add(new FindMax(matrix[j]));
		}
		for(FindMax x : lst){
			x.start();
		}
		for(FindMax x : lst){
			try {
				x.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int max = lst.get(0).maxMatr;
		for(int i = 1; i < lst.size(); i++){
			if(lst.get(i).maxMatr > max){
				max = lst.get(i).maxMatr;
			}
		}
		System.out.println("AsyncTime - " + (System.currentTimeMillis() - current));
		return max;
	}

	public int maxSync(){
		long current = System.currentTimeMillis();
		int max = matrix[0][0];
		for(int i = 0; i < 4; i++){
			for(int k = 0; k < 100; k++){
				if(max < matrix[i][k]){
					max = matrix[i][k];
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("SyncTime - " + (System.currentTimeMillis() - current));
		return max;
	}

	public static int[][] generate(){
		int[][] matrix = new int[4][100];
		for(int i = 0; i < 4; i++){
			for(int k = 0; k < 100; k++){
				matrix[i][k] = ThreadLocalRandom.current().nextInt(0, 1001);
			}
		}
		return matrix;
	}

}

class FindMax extends Thread{
	int[] matrixRow;
	int maxMatr;
	public FindMax(int[] matrixRow){
		this.matrixRow = matrixRow;
	}
	@Override
	public void run() {
		int max = matrixRow[0];
		for(int i = 1; i < matrixRow.length; i++){
			if(matrixRow[i] > max){
				max = matrixRow[i];
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		maxMatr = max;
	}
}
