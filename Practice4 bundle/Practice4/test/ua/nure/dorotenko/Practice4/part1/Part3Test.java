package ua.nure.dorotenko.Practice4.part1;

import org.junit.*;
import ua.nure.dorotenko.Practice4.Part3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by kingsdwarf on 08.08.17.
 */
public class Part3Test {
    private static final InputStream STD_IN = System.in;
    @Before
    public void initialize() throws UnsupportedEncodingException{
        System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble\nstop".getBytes("UTF-8")));
    }

    @After
    public void destroy(){
        System.setIn(STD_IN);
    }
    @Test
    public void testPart3(){
        Part3.main(new String[]{});
    }

    @Test
    public void testUnknownCommand() throws UnsupportedEncodingException {
        System.setIn(new ByteArrayInputStream("asdasd\nstop\n".getBytes("UTF-8")));
        Part3.run();
    }
}
