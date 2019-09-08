package com.zhachaoy.g2g;

public class Appliation {

    /**
     * If no input file is provided, the configuration file in the root directory will be loaded.
     *
     * @param args
     */
    public static void main(String[] args) {
        String filePath = null;
        if (args.length != 0) {
            filePath = args[0];
        }
        try {
            InputProcessor.ProcessFile(filePath);
            InputProcessor.ConvertTokentoValue();
            OutputProcessor.processReplyForQuestion();
        } catch (Exception e) {
            System.out.println("Input File Not Found");
        }
    }
}