package com.zhachaoy.g2g;

/**
 * Parser class
 *
 * @author zhachaoy@163.com
 * @date 2019/09/08
 */
public class Parser {

    /**
     * romannumeric to decimal
     *
     * @param romanNumber
     * @return
     */
    public float romanToDecimal(String romanNumber) {

        float decimal = 0;
        float lastNumber = 0;
        char[] romanNumeral = romanNumber.toUpperCase().toCharArray();

        //Operation to be performed on upper cases even if user enters romannumeric values in lower case chars
        for (int x = romanNumeral.length - 1; x >= 0; x--) {
            Character convertToDecimal = romanNumeral[x];

            switch (convertToDecimal) {
                case 'M':
                    Ruler.checkLiteralCountValidity(convertToDecimal);
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;

                case 'D':
                    Ruler.checkLiteralCountValidity(convertToDecimal);
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;

                case 'C':
                    Ruler.checkLiteralCountValidity(convertToDecimal);
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    Ruler.checkLiteralCountValidity(convertToDecimal);
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    Ruler.checkLiteralCountValidity(convertToDecimal);
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    Ruler.checkLiteralCountValidity(convertToDecimal);
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    Ruler.checkLiteralCountValidity(convertToDecimal);
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        Ruler.resetLiteralsCounter();
        return decimal;
    }

    /**
     * processDecimal() applied all subtraction logic and returns the result
     *
     * @param decimal
     * @param lastNumber
     * @param lastDecimal
     * @return
     */
    public float processDecimal(float decimal, float lastNumber, float lastDecimal) {
        if (lastNumber > decimal) {
            return Ruler.subtractionLogic(lastDecimal, decimal, lastNumber);
        } else {
            return lastDecimal + decimal;
        }
    }
}
