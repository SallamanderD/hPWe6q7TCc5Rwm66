package Practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part6 extends Thread {

	private static final Object MONITOR = new Object();

	private static final int THREADS_NUMBER = 10;

	private static final int COLUMNS = 20;

	private static final int EOL_LENGTH = System.lineSeparator().length();

	private static String fileName = "test.txt";

	private RandomAccessFile out;

	private int digit;

	public Part6(int digit, RandomAccessFile raf) {
		this.digit = digit;
		this.out = raf;
	}

	// TODO place your code here

	@Override
	public void run() {
		try {
			write(out, digit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(RandomAccessFile f, int i) throws IOException {
		synchronized (MONITOR) {
			for (int j = 0; j <= 20; j++) {
				f.seek(i * 21 + j * 1);
				f.write('0'+ i);
			}
			f.seek(i * 21 + 20);
			f.write('\n');
		}
	}

	public static void go(){
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(new File(fileName), "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < THREADS_NUMBER; i++){
			threads.add(new Part6(i, raf));
			threads.get(i).start();
		}
		String result = null;
		try {
			result = new String(Files.readAllBytes(Paths.get("test.txt")), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		go();
	}

}