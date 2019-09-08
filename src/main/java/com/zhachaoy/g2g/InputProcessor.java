package com.zhachaoy.g2g;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * InputProcessor class
 *
 * @author zhachaoy@163.com
 * @date 2019/09/08
 */
public class InputProcessor {

    /**
     * Defining mappings
     * tokenValueMapping: {pish=X, tegj=L, prok=V, glob=I}
     * tokenValue: {pish=10.0, tegj=50.0, prok=5.0, glob=1.0}
     * questionAndReply: {how much is pish tegj glob glob ?}
     * missingValues: [glob glob Silver is 34 Credits]
     * elementValue: {Gold=14450.0, Iron=195.5, Silver=17.0}
     */
    static Map<String, String> tokenValueMapping = new HashMap<String, String>();
    static Map<String, Float> tokenValue = new HashMap<String, Float>();
    static Map<String, String> questionAndReply = new HashMap<String, String>();
    static ArrayList<String> missingValues = new ArrayList<String>();
    static Map<String, Float> elementValue = new HashMap<String, Float>();


    /**
     * If no input file is provided, the configuration file in the root directory will be loaded.
     *
     * @param filePath
     * @throws IOException
     */
    public static void processFile(String filePath) throws IOException {
        BufferedReader bufferedReader = null;
        if (filePath == null) {
            InputStream in = InputProcessor.class.getClassLoader().getResourceAsStream("Input");
            bufferedReader = new BufferedReader(new InputStreamReader(in));
        } else {
            FileReader fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
        }
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            processLine(line);
        }
        bufferedReader.close();
    }

    /**
     * Parsing the token value
     * {pish=X, tegj=L, prok=V, glob=I}
     */
    public static void convertTokentoValue() {
        Iterator it = tokenValueMapping.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry token = (Map.Entry) it.next();
            float integerValue = new Lexer().romanToDecimal(token.getValue().toString());
            tokenValue.put(token.getKey().toString(), integerValue);
        }
        mapMissingEntities();
    }


    /**
     * Parsing the input content
     *
     * @param line
     */
    private static void processLine(String line) {
        String arr[] = line.split("((?<=:)|(?=:))|( )");

        if (line.endsWith("?")) {
            questionAndReply.put(line, "");
        } else if (arr.length == 3 && "is".equalsIgnoreCase(arr[1])) {
            tokenValueMapping.put(arr[0], arr[arr.length - 1]);
        } else if (line.toLowerCase().endsWith("credits")) {
            missingValues.add(line);
        }
    }

    /**
     * Decode by known conditions like [glob glob Silver is 34 Credits]
     */
    private static void mapMissingEntities() {
        for (int i = 0; i < missingValues.size(); i++) {
            decodeMissingQuery(missingValues.get(i));
        }
    }

    /**
     * Calculating monetary value
     * elementValue :{Gold=14450.0, Iron=195.5, Silver=17.0}
     *
     * @param query
     */
    private static void decodeMissingQuery(String query) {
        String array[] = query.split("((?<=:)|(?=:))|( )");
        int splitIndex = 0;
        int creditValue = 0;
        String element = null;
        String[] valueofElement = null;
        for (int i = 0; i < array.length; i++) {
            if ("credits".equals(array[i].toLowerCase())) {
                creditValue = Integer.parseInt(array[i - 1]);
            }
            if ("is".equals(array[i].toLowerCase())) {
                splitIndex = i - 1;
                element = array[i - 1];
            }
            valueofElement = java.util.Arrays.copyOfRange(array, 0, splitIndex);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < valueofElement.length; j++) {
            stringBuilder.append(tokenValueMapping.get(valueofElement[j]));
        }
        float valueOfElementInDecimal = new Lexer().romanToDecimal(stringBuilder.toString());
        elementValue.put(element, creditValue / valueOfElementInDecimal);
    }
}