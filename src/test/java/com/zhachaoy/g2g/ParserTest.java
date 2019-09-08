package com.zhachaoy.g2g;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * LexerTest class
 *
 * @author zhachaoy@163.com
 * @date 2019/09/08
 */
public class ParserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected String romanNumeral, anotherRomanNumeral;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Before
    public void setUp() throws Exception {
        romanNumeral = "MCMXLIV";
        anotherRomanNumeral = "MMMM";
    }

    @Test
    /**
     * Test the scenario of Roman to Numeric Conversion.
     */
    public void testRomanToDecimal() {
        Parser parser = new Parser();
        float numericValue = parser.romanToDecimal(romanNumeral);
        Assert.assertEquals(1944.00, numericValue, 00.00);
    }

    @Test
    /**
     * Test the scenario where Non repeatable Roman Numeral repeats 4 times successively thereby throwing error.
     */
    public void testRomanToDecimalFailing() {
        Parser parser = new Parser();
        float value = parser.romanToDecimal(anotherRomanNumeral);
        Assert.assertEquals("Error : Roman Numeral M cannot repeat 4 times successively", errContent.toString());
    }

}