package ua.nure.dorotenko.Practice4.part1;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runners.JUnit4;
import ua.nure.dorotenko.Practice4.Part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2Test {
    @Test
    public void testPart2() throws IOException{
        Part2.run();
        List<Integer> input = new ArrayList<>();
        for(String s : Files.readAllLines(Paths.get("part2.txt"))){
            for(String x : s.split("\\s+")){
                input.add(Integer.valueOf(x));
            }
        }
        List<Integer> output = new ArrayList<>();
        for(String s : Files.readAllLines(Paths.get("part2_sorted.txt"))){
            for(String x : s.split("\\s+")){
                output.add(Integer.valueOf(x));
            }
        }
        Collections.sort(input);
        Assert.assertArrayEquals(output.toArray(), input.toArray());
    }

    @Test(expected = IOException.class)
    public void testNoFile() throws IOException {
        Part2.generate();
        Files.delete(Paths.get("part2.txt"));
        Part2.sort();

    }

}
