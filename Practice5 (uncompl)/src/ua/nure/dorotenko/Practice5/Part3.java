package ua.nure.dorotenko.Practice5;

import java.util.concurrent.atomic.AtomicInteger;

public class Part3 {
	public static void main(String[] args) {
		AtomicInteger x = new AtomicInteger(0);
		AtomicInteger y = new AtomicInteger(0);
		Thread t = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                check(x, y);
            }
        });
		Thread t1 = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                check(x, y);
            }
        });

		t.start();
		t1.start();
	}

	public synchronized static void check(AtomicInteger x, AtomicInteger y){
		System.out.println(x.get() + " == " + y.get() + " is " + (x.get() == y.get()));
		x.incrementAndGet();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		y.incrementAndGet();
	}
}
