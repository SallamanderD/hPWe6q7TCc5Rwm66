package ua.nure.dorotenko.Practice4.part1;

import org.junit.After;
import org.junit.Test;
import ua.nure.dorotenko.Practice4.Part5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.MissingResourceException;

public class Part5Test {
    private static final InputStream STD_IN = System.in;
    @After
    public void defaultInput(){
        System.setIn(STD_IN);
    }
    @Test
    public void test() throws UnsupportedEncodingException {
        System.setIn(new ByteArrayInputStream("table ru\ntable en\napple ru\nstop".getBytes("UTF-8")));
        Part5.main(new String[]{});
    }

    @Test(expected = MissingResourceException.class)
    public void testWrongKey() throws UnsupportedEncodingException {
        System.setIn(new ByteArrayInputStream("asd en\nstop".getBytes("UTF-8")));
        Part5.main(new String[]{});
    }
}
