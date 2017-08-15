package ua.nure.dorotenko.Practice4.part1;


import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ua.nure.dorotenko.Practice4.Part4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class Part4Test {
    public static final String AFTER = "Ad asd xzve.  .     .  . U are pinguin. 123. 3126 asdasd 12415   . Мама мыла раму.";
    public static final String BEFORE = "   ";
    @After
    public void loadFile() throws IOException{
        Files.write(Paths.get("part4.txt"), AFTER.getBytes("UTF-8"));
    }
    @Test
    public void testClass(){
        Part4.main(new String[]{});
    }

    @Test
    public void testClassWithoutFile() throws IOException {
        Files.delete(Paths.get("part4.txt"));
        Part4.main(new String[]{});

    }

    @Test
    public void testNoSentences() throws IOException{
        Files.write(Paths.get("part4.txt"), BEFORE.getBytes("UTF-8"));
        Part4 part4 = new Part4();
        Iterator<String> iterator = part4.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() throws IOException{
        Part4 part4 = new Part4();
        Iterator<String> iterator = part4.iterator();
        iterator.next();
        iterator.remove();
    }

    @Test(expected = IOException.class)
    public void testNoFile() throws IOException{
        Files.delete(Paths.get("part4.txt"));
        Part4 part4 = new Part4();
    }
}
