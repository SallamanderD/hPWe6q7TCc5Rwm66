package ua.nure.dorotenko.Practice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Part2 {
    public static void main(String[] args) {
        try {
            run();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
    }

    public static void generate() {
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            numbers.append(ThreadLocalRandom.current().nextInt(0, 51) + " ");
        }
        try {
            Files.write(Paths.get("part2.txt"), numbers.toString().getBytes());
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void sort() throws IOException {
        List<String> input;
        input = Files.readAllLines(Paths.get("part2.txt"));
        List<Integer> intList = new ArrayList<>();
        for (String s : input) {
            for (String x : s.split("\\s+")) {
                intList.add(Integer.valueOf(x));
            }
        }
        bubbleSort(intList);
        StringBuilder result = new StringBuilder();
        for (Integer number : intList) {
            result.append(number + " ");
        }
        try {
            Files.write(Paths.get("part2_sorted.txt"), result.toString().getBytes());
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void run() throws IOException {
        generate();
        sort();
        List<String> str;
        List<String> str2;
        try {
            str = Files.readAllLines(Paths.get("part2.txt"));
            str2 = Files.readAllLines(Paths.get("part2_sorted.txt"));
        } catch (IOException ex) {
            System.out.println(ex);
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String string : str) {
            builder.append(string);
        }
        System.out.println("input ==> " + builder.toString());
        builder = new StringBuilder();
        for (String string : str2) {
            builder.append(string);
        }
        System.out.println("output ==> " + builder.toString());
    }

    public static void bubbleSort(List<Integer> lst) {

        int n = lst.size();
        int temp;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (lst.get(j - 1) > lst.get(j)) {
                    temp = lst.get(j - 1);
                    lst.set(j - 1, lst.get(j));
                    lst.set(j, temp);
                }

            }
        }
    }
}
