package ua.nure.dorotenko.Practice5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Note!!! Without synchronization this application not work properly!! Most
 * likely a runtime exception will be thrown..
 * 
 */
public class Part51 {

	private static final int ITERATION_NUMBER = 3;
	private static final int READERS_NUMBER = 3;
	private static final StringBuilder BUFFER = new StringBuilder();
	private static final int BUFFER_LENGTH = 5;
	private static final int PAUSE = 5;
	private static boolean stop;
	private static int activeReaders = 0;

	private static class Reader extends Thread {
		public void run() {
			while (!stop) {
				try {
					read(getName());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Writer extends Thread {
		public void run() {
			int tact = 0;
			while (!stop) {
				try {
					write();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (++tact == ITERATION_NUMBER) {
						stop = true;
					}
				}
			}
		}
	}

	private synchronized static void read(String threadName) throws InterruptedException {
		synchronized (BUFFER){
			if(activeReaders > 0){
				System.out.printf("Reader %s:", threadName);
				for (int j = 0; j < BUFFER_LENGTH; j++) {
					Thread.sleep(PAUSE);
					System.out.print(BUFFER.charAt(j));
				}
				System.out.println();
				Thread.sleep(PAUSE);
				activeReaders--;
			} else{
				BUFFER.notifyAll();
			}
		}
	}

	private static void write() throws InterruptedException {
		synchronized (BUFFER){
			if(activeReaders != 0){
				BUFFER.wait();
			}
			activeReaders = READERS_NUMBER;
			BUFFER.setLength(0);
			System.err.print("Writer writes:");
			Random random = new Random();
			for (int j = 0; j < BUFFER_LENGTH; j++) {
				char ch = (char) ('A' + random.nextInt(26));
				System.err.print(ch);
				BUFFER.append(ch);
			}
			System.err.println();
			Thread.sleep(PAUSE);
			BUFFER.notifyAll();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Writer writer = new Writer();
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader());
		}
		Thread.sleep(10);
		writer.start();
		Thread.sleep(10);
		for (Thread reader : readers) {
			reader.start();
		}
		writer.join();
		for (Thread reader : readers) {
			reader.join();
		}
	}

}