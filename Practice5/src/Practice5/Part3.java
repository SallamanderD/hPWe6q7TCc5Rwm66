package Practice5;

import java.util.ArrayList;
import java.util.List;

public class Part3 {
    int first;
    int second;

    public Part3(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static void main(String[] args) {
        Part3 p3 = new Part3(0, 0);
        p3.incrementAsync();
    }

    public void incrementSync() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    if (count++ == 30) {
                        Thread.currentThread().interrupt();
                    }
                    synchronized (this) {
                        System.out.print(first + " == " + second + " = ");
                        System.out.println(first == second);
                        first++;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.out.println("finish " + Thread.currentThread().getName());
                            return;
                        }
                        second++;
                    }
                }
            }
        };
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(r));
            threads.get(i).start();
        }
    }

    public void incrementAsync() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    if (count++ == 30) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.print(first + " == " + second + " = ");
                    System.out.println(first == second);
                    first++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("finish " + Thread.currentThread().getName());
                        return;
                    }
                    second++;
                }
            }
        };
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(r));
            threads.get(i).start();
        }
    }
}
