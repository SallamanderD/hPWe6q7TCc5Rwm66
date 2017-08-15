package ua.nure.dorotenko.Practice4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Demo {
    private static final InputStream STD_IN = System.in;
    public static void main(String[] args) throws IOException {
        System.out.println("~~~ Part 1 ~~~");
        Part1.main(args);
        System.out.println("~~~ Part 2 ~~~");
        Part2.main(args);
        System.out.println("~~~ Part 3 ~~~");
        System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble\nstop".getBytes("UTF-8")));
        Part3.main(args);
        System.setIn(STD_IN);
        System.out.println("~~~ Part 4 ~~~");
        Part4.main(args);
        System.out.println("~~~ Part 5 ~~~");
        System.setIn(new ByteArrayInputStream("table ru\ntable en\napple ru\nstop".getBytes("UTF-8")));
        Part5.main(args);
        System.setIn(STD_IN);
    }
}
