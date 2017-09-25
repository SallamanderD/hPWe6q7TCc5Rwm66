package ua.nure.dorotenko.Practice4.part1;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.dorotenko.Practice4.Part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part1Test {
    private final static String FILE_NAME = "part1test.txt";
    private final static String INFO = "asdegGED ASDcxcv b vb\n" +
            "asdgrgqef GEGvc VbV      gsrhwr";
    private final static String ANSW = "ASDEGGED ASDCXCV b vb\n" +
            "ASDGRGQEF GEGVC VbV      GSRHWR";
    @Test
    public void testPart1() throws IOException {

            Assert.assertEquals(ANSW, Part1.toUpper(FILE_NAME));

    }
    @Test(expected = IOException.class)
    public void testIO() throws IOException{
        Part1.toUpper("");
    }

    @Test
    public void testCatch(){
        Part1.main(new String[]{});
    }
    @BeforeClass
    public static void initialize(){
        try{
            Files.createFile(Paths.get(FILE_NAME));
            Files.write(Paths.get(FILE_NAME), INFO.getBytes("UTF-8"));
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
    @AfterClass
    public static void destroy(){
        try {
            Files.delete(Paths.get(FILE_NAME));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
