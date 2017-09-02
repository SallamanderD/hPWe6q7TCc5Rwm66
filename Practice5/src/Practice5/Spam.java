package Practice5;

import java.io.IOException;

public class Spam {
    public Spam(long[] times, String[] strings) {
        this.times = times;
        this.strings = strings;
    }

    public Spam() {
        times = new long[] {300, 500, 770, 1467, 123, 567};
        strings = new String[] {"abc", "123", "axc", "zxve", "321", "bbb"};
    }

    long[] times;
    String[] strings;

    public void print(){
        Thread t = new Thread(() -> {
            for(int i = 0; i < times.length; i++){
                System.out.println(strings[i]);
                try {
                    Thread.sleep(times[i]);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void execute(){
        print();
        new Thread(){
            @Override
            public void run() {
                byte[] buffer = new byte[10];
                int count;
                try{
                    do{
                        while((count = System.in.read(buffer)) == -1);
                    } while (!System.lineSeparator().equals(new String(buffer, 0, count)));
                } catch (IOException ex){
                    System.out.println(ex);
                }
                System.out.println("ENTER obtained");
            }
        }.start();
    }


    public static void main(String[] args) throws Exception {
        Spam spam = new Spam();
        spam.print();
        new Thread(){
            @Override
            public void run() {
                byte[] buffer = new byte[10];
                int count;
                try{
                    do{
                        while((count = System.in.read(buffer)) == -1);
                    } while (!System.lineSeparator().equals(new String(buffer, 0, count)));
                } catch (IOException ex){
                    System.out.println(ex);
                }
                System.out.println("ENTER obtained");
            }
        }.start();
    }

}
