
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Test Text Manipulator
// Files:           TextManipulator.java, Config.java, TestTextManipulator
// Course:          CS 200 Fall 2017
//
// Author:          Omjaa Rai
// Email:           orai@wisc.edu
// Lecturer's Name: Marc Renault
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
//import java.util.Arrays;
/**
 * This class contains a few methods for testing methods in the TextManipulator
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Marc Renault
 * @author Omjaa Rai
 *
 */

public class TestTextManipulator {
    public static void main(String[] args) {
        testMatchCase();
        testTranslate();
        testPigLatin();
        testReverseWord();
        testReverseArrayList();
        testManipulate();

    }



    ArrayList<String> testing=new ArrayList<String>();
    //test for matchCase()
    private static void testMatchCase(){
        boolean error=false;

        String template1="testing";
        String template2="TestIng";

        String original1="One";
        String original2="fish";
        String original3="little";
        String original4="THEre";

        String result1=TextManipulator.matchCase(template1, original1);
        String result2=TextManipulator.matchCase(template1, original2);
        String result3=TextManipulator.matchCase(template2, original3);
        String result4=TextManipulator.matchCase(template2, original4);

        if(!result1.equals ("one")) {
            error=true;
            System.out.print("1");
        }
        if(!result2 .equals("fish")) {
            error=true;
            System.out.print("2");
        }
        if(!result3 .equals("LittLe")) {
            error=true;
            System.out.print("3");
        }
        if(!result4 .equals ("TherE")) {
            error=true;
            System.out.print("4");
        }

        if(error) {
            System.out.println("TestMatchCase: failed");
        }else {
            System.out.println("TestMatchCase: passed");
        }
    }

    //test for translate()
    private static void testTranslate() {

        boolean error=false;

        String word1="know";
        String word2="speak";
        String word3="I";
        String word4="hair";
        String data1[] = {"know","weiB"};
        String data2[] = {"speak","sprechen"};
        String data3[] = {"I","Ich"};
        String data4[] = {"hair","Haare"};

        ArrayList<String[]> wordList=new ArrayList<String[]>();

        wordList.add(data1);
        wordList.add(data2);
        wordList.add(data3);
        wordList.add(data4);

        String result1= TextManipulator.translate(word1, wordList);
        String result2= TextManipulator.translate(word2, wordList);
        String result3= TextManipulator.translate(word3, wordList);
        String result4= TextManipulator.translate(word4, wordList);

        if(!result1 .equals ("weib")) {
            error=true;
        }
        if(!result2.equals ("sprechen")) {
            error=true;
        }
        if(!result3 .equals ("Ich")) {
            error=true;
        }

        if(!result4 .equals ("haare")) {
            error=true;
        }

        if(error) {
            System.out.println("TestTranslate: failed");
        }else {
            System.out.println("TestTranslate: passed");
        }


    }


    //test for pigLatin()
    private static void testPigLatin() {
        boolean error=false;

        String word1="hello world";
        String word2="Two";
        String word3="ghost";
        String word4="yes please";

        String result1=TextManipulator.pigLatin(word1);
        String result2=TextManipulator.pigLatin(word2);
        String result3=TextManipulator.pigLatin(word3);
        String result4=TextManipulator.pigLatin(word4);


        if(!result1 .equals ("ello worldhay")) {
            error=true;
        }
        if(!result2.equals ("oTway")) {
            error=true;
        }
        if(!result3 .equals ("ostghay")) {
            error=true;
        }

        if(!result4 .equals ("yes pleaseway")) {
            error=true;
        }

        if(error) {
            System.out.println("TestPigLatin: failed");
        }else {
            System.out.println("TestPigLatin: passed");
        }

    }

    //test for reverse( String)
    private static void testReverseWord() {

        boolean error=false;

        String word1="hello world";
        String word2="Two";
        String word3="ghost";
        String word4="yes please";

        String result1=TextManipulator.reverse(word1);
        String result2=TextManipulator.reverse(word2);
        String result3=TextManipulator.reverse(word3);
        String result4=TextManipulator.reverse(word4);


        if(!result1 .equals ("dlrow olleh")) {
            error=true;

        }
        if(!result2.equals ("Owt")) {
            error=true;

        }
        if(!result3 .equals ("tsohg")) {
            error=true;

        }

        if(!result4 .equals ("esaElp sey")) {
            error=true;

        }

        if(error) {
            System.out.println("TestReverseWord: failed");
        }else {
            System.out.println("TestReverseWord: passed");
        }
    }

    //test for reverse ( arrayList<String>>)
    private static void testReverseArrayList() {

        boolean error=false;

        ArrayList<String> arrL1=new ArrayList<String>();
        arrL1.add("My");
        arrL1.add("name");
        arrL1.add("is");
        arrL1.add("Olive");

        ArrayList<String> arrL2=new ArrayList<String>();
        arrL2.add("Your");
        arrL2.add("name");
        arrL2.add("is");
        arrL2.add("Supriya");

        ArrayList<String> arrL3=new ArrayList<String>();
        arrL3.add("Your");
        arrL3.add("name");
        arrL3.add("is");
        arrL3.add("Supriya");

        ArrayList<String> arrL4=new ArrayList<String>();
        arrL4.add("People");
        arrL4.add("like");
        arrL4.add("apple");
        arrL4.add("pies");



        ArrayList<String> result1=new ArrayList<String>();
        ArrayList<String> result2=new ArrayList<String>();
        ArrayList<String> result3=new ArrayList<String>();
        ArrayList<String> result4=new ArrayList<String>();

        result1=TextManipulator.reverse(arrL1);
        result2=TextManipulator.reverse(arrL2);
        result3=TextManipulator.reverse(arrL3);
        result4=TextManipulator.reverse(arrL4);

        if(!result1.get(0).equals("Olive")) {
            error=true;

        }
        if(!result2.get(1).equals("is")) {
            error=true;

        }
        if(!result3.get(2).equals("name")) {
            error=true;

        }
        if(!result4.get(3).equals("People")) {
            error=true;

        }

        if(error) {
            System.out.println("TestReverseArrayList: failed");
        } else {
            System.out.println("TestReverseArrayList: passed");
        }

    }

    //test for manipulate()
    private static void testManipulate() {
        try {  
            ArrayList<ArrayList<String> > fileByLine=new ArrayList<ArrayList<String> > ();
            //ArrayList<ArrayList<String> > fileByLine1=new ArrayList<ArrayList<String> > ();
            ArrayList<String[]> dict=new ArrayList<String[]>();
            boolean[] modFlags=new boolean[Config.NUM_MODS ];

            TextManipulator.readInputFile("OneFish.txt", fileByLine);

            TextManipulator.display(fileByLine, TextManipulator.manipulate(fileByLine, dict, modFlags), true);
            modFlags[Config.MOD_REV_LINE] = true;
            TextManipulator.display(fileByLine, TextManipulator.manipulate(fileByLine, dict, modFlags), true);
            modFlags[Config.MOD_REV_WORD] = true;
            modFlags[Config.MOD_REV_LINE] = false;
            TextManipulator.display(fileByLine, TextManipulator.manipulate(fileByLine, dict, modFlags), true);
            modFlags[Config.MOD_TRANS] = true;
            modFlags[Config.MOD_REV_WORD] = false;
            TextManipulator.display(fileByLine, TextManipulator.manipulate(fileByLine, dict, modFlags), true);
            modFlags[Config.MOD_PIG] = true;
            modFlags[Config.MOD_TRANS] = false;
            TextManipulator.display(fileByLine, TextManipulator.manipulate(fileByLine, dict, modFlags), true);
            modFlags[Config.MOD_PIG] = false;
            TextManipulator.display(fileByLine, TextManipulator.manipulate(fileByLine, dict, modFlags), true);
            System.out.println("TestManipulate: passed");
        }
        catch(Exception e) {
            System.out.print("TestManipulate: failed");
        }

    }
}
