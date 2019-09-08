package com.zhachaoy.g2g;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * AppliationTest class
 *
 * @author zhachaoy@163.com
 * @date 2019/09/08
 */
public class AppliationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected String filePath;

    @Before
    public void setUpStreams() {
        filePath = null;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    /**
     * Method tests a positive scenario of the application. input file by default picked up by the input.
     */
    public void testProgram() throws IOException {
        Lexer.processFile(filePath);
        Lexer.convertTokentoValue();
        Processor.processReplyForQuestion();
        Assert.assertEquals("glob prok Iron is 782.0 Credits\n" +
                        "pish tegj glob glob is 42.0\n" +
                        "glob prok Gold is 57800.0 Credits\n" +
                        "glob prok Silver is 68.0 Credits\n"
                , outContent.toString());
    }

}
