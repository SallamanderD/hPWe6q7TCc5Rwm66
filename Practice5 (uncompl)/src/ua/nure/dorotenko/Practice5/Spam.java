package ua.nure.dorotenko.Practice5;

import java.util.Scanner;

public class Spam {
    static long[] times = {300, 500, 770, 1467, 123, 567};
    static String[] strings = {"abc", "123", "axc", "zxve", "321", "bbb"};

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for(int i = 0; i < times.length; i++){
                System.out.println(strings[i]);
                try {
                    Thread.sleep(times[i]);
                } catch (InterruptedException e) {
                    System.out.println("~~~end~~~");
                    return;
                }
            }
        });
        t.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        t.interrupt();
    }

}
