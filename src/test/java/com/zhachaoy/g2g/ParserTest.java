package com.zhachaoy.g2g;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ParserTest class
 *
 * @author zhachaoy@163.com
 * @date 2019/09/08
 */
public class ParserTest {

    protected Character[] characterArray;
    protected Character character;

    @Before
    public void setUp() throws Exception {
        character = 'P';
        characterArray = new Character[]{'I', 'X', 'V', 'L'};

    }

    @Test
    public void testOutputFormatter() {
        boolean result = Parser.checkIfLiteralPresent(characterArray, character);
        Assert.assertEquals(false, result);
    }

    @Test

    /**
     * Test whether the subtraction logic is handled correctly.
     */
    public void testSubtractionLogic() {
        float result = Parser.subtractionLogic(52f, 10f, 50f);
        Assert.assertEquals(42f, result, 00.00);
    }
}
