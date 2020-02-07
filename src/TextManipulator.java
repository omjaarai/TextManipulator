//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Text Manipulator
// Files:           TextManipulator.java, Config.java
// Course:          CS 200 Fall 2017
//
// Author:          Omjaa Rai
// Email:           orai@wisc.edu
// Lecturer's Name: Marc Renault
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//This class takes input from the user and performs actions according to  it.It's a text-menu-driven program that reads text files and manipulates the input as
//directed by the user via the text menu. In addition, the program will both display the modifications on the
//screen and save them to a file.


public class TextManipulator {

    /**
     * Matches the case of the original string to that of the template as follows. 
     *
     * 1) If the length of template is the same or longer than the length of the original, the 
     *    returned string will match the case of the prefix of the template up to the length of the
     *    original string. 
     *    For example:
     *         template: XXxxxXxX
     *         original: azertYU
     *         returns:  AZertYu
     *
     * 2) If the length of the template is shorter than the length of the original, the prefix of the
     *    returned string up to the length of the template will match the case of the template. The 
     *    remaining characters should all be lower case.
     *    For example: 
     *         template: WxCv
     *         original: azertYU
     *         returns:  AzErtyu
     *
     * @param template Template of which characters should be which case.
     * @param original String to change the case of, based on the template.
     * @return A string containing the characters of original with the case matching that of 
     *         template.
     */
    public static String matchCase(String template, String original) {
        //using a for loop we check if i is less than the length of template. If it is,
        // and the character at position i in  template is lower case then we keep adding
        //the characters at position i in the original after converting them to lower case.
        // else, we make it upper case and add it to finWord.
        // if it is not less than the length of the template, we make it lower case and add
        // it to finWord.

        String finWord = ""; //variable to store the final word

        for(int i = 0; i < original.length(); i++) {
            if(i < template.length()) {
                if(Character.isLowerCase(template.charAt(i))){
                    finWord = finWord+ Character.toLowerCase(original.charAt(i));
                }
                else {
                    finWord += Character.toUpperCase(original.charAt(i));
                }
            }
            else {
                finWord += Character.toLowerCase(original.charAt(i));
            }
        }
        return finWord;
    }

    /**
     * Translates a word according to the data in wordList then matches the case. The parameter 
     * wordList contains the mappings for the translation. The data is organized in an ArrayList 
     * containing String arrays of length 2. The first cell (index 0) contains the word in the 
     * original language, called the key, and the second cell (index 1) contains the translation.
     *
     * It is assumed that the items in the wordList are sorted in ascending order according to 
     * the keys in the first cell. 
     *
     * @param word The word to translate.
     * @param wordList An ArrayList containing the translation mappings.          
     * @return The mapping in the wordList with the same case as the original. If no match is found
     *         in wordList, it returns a string of Config.LINE_CHAR of the same length as word.
     */
    public static String translate(String word, ArrayList<String[]> wordList) {
        for(int i = 0; i < wordList.size(); i++) {
            // if the word is equal to the word in the ArayList then returns the translation.
            if(wordList.get(i)[0].equalsIgnoreCase(word)) {
                return matchCase(word, wordList.get(i)[1]);
            }
        }
        //if the first character of word is not a letter then it returns the word.
        if(!Character.isLetter(word.charAt(0))){
            return word;
        }
        // if there is no match, then it returns characters as specified.
        String noMatchFound = "";
        for(int i = 0; i < word.length(); i++) {
            noMatchFound += Config.LINE_CHAR;
        }
        return noMatchFound;
    }

    /**
     * Converts a string to simplified Pig Latin then matching the case. The rules for simplified Pig 
     * Latin are as follows:
     * 1) If the word begins with a vowel (a, e, i, o, u, or y), then the string "way" is appended. 
     * 2) If the word begins with a consonant (any letter that is not a vowel as defined above), then
     *    the group of consonants at the beginning of the word are moved to the end of the string,
     *    and then the string "ay" is appended.
     * 3) If the word begins with any other character, the original string is returned.
     * Note 1: 'y' is always considered a vowel.
     * Note 2: An apostrophe that is not the first character of a word is treated as a consonant. 
     *
     * Some examples:
     *   Pig -> Igpay
     *   Latin -> Atinlay
     *   Scram -> Amscray
     *   I'd -> I'dway
     *   you -> youway
     *   crypt -> yptcray 
     *
     * @param word The word to covert to simplified Pig Latin.
     * @return The simplified Pig Latin of the parameter word with matching case.
     */
    public static String pigLatin(String word) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};//array to store the vowels
        Arrays.sort(vowels);//sorts the array
        char c = Character.toLowerCase(word.charAt(0));//converting the first character of word to lower case and storing it in variable c
        //checks if c is not a letter and the length of word is 1, if it is true, then returns the word
        if(!Character.isLetter(c) && word.length()==1) {
            return word;
        }//checks if c is a vowel, if it is then returns the word + "way"
        if(Arrays.binarySearch(vowels, c) >= 0) {
            return word + "way";
        }
        //otherwise moves all the consonants after the first vowel in the string and adds "ay".
        else {
            int cnt = 0;
            String finalResult = "";
            while (cnt < word.length()) {
                c = Character.toLowerCase(word.charAt(cnt));
                if(Arrays.binarySearch(vowels, c) >= 0) {
                    break;
                }
                cnt++;
            }
            finalResult += word.substring(cnt);
            finalResult += word.substring(0, cnt);
            finalResult += "ay";
            //finalResult = matchCase(word, finalResult);
            return finalResult;
        }
    }

    /**
     * Reverses a String, then matches the case.
     * For example: aZErty returns yTReza
     *
     * @param word The String to reverse.
     * @return The reverse of word with matching case.
     */
    public static String reverse(String word) {
        String result = "";//string variable to store and return the final word
        //uses for loop to reverse the word
        for(int i=0;i< word.length();i++) {
            char ch= word.charAt(i);
            result= ch+ result;
        }
        result= matchCase(word, result);
        return result;

    }

    /**
     * Builds a new ArrayList of Strings that contains the items of the ArrayList passed in, arrL,
     * but in reverse order.
     *
     * @param arrL The ArrayList of Strings to reverse.
     * @return A new ArraList of Strings that is the reverse of arrL.
     */
    public static ArrayList<String> reverse(ArrayList<String> arrL) {
        ArrayList<String> rev = new ArrayList<String>();
        for(int i = 0; i < arrL.size(); i++) {
            rev.add(arrL.get(arrL.size() - i - 1));
        }
        return rev;
    }

    /**
     * The method that displays the main program menu and reads the user's menu choice.
     * The full menu has the following format where the (assuming that Config.MISSING_CHAR is '-'):
     * 
     * --------------------------------------------------------------------------------
     * Text Manipulator Program
     * --------------------------------------------------------------------------------
     * Current input file: ---
     * Current output file: ---
     * Current dictionary: ---
     * Current mode: Interleaved
     * Current mods: TPWL
     * --------------------------------------------------------------------------------
     * Main menu:
     * 1) Display Output
     * 2) Save Output
     * Manipulations:
     *        T)ranslate
     *        P)ig latin
     *        W)ord reverse
     *        L)ine reverse
     * Configuration:
     *        I)nput file.
     *        O)utput file.
     *        D)ictionary file.
     *        M)ode Toggle.
     *        H)ide/show Menu.
     * Q)uit
     * Enter action: 
     *
     * Notes:
     *   - The lines consist of 80 Config.LINE_CHAR characters.
     *   - The input file, output file and dictionary names are 3 Config.LINE_CHAR characters if 
     *     appropriate value in files is null, otherwise display the appropriate value in files.
     *   - The Current mode displays "Interleaved" when modeBoth is true and "Modified" when false.
     *   - The Current mods displays (in the following order) 'T' if translate is toggled, 'P' if
     *     Pig Latin is toggled, 'W' if word reverse is toggled, and 'L' if line reverse is toggled.
     *   - The manipulation and configuration options are preceded by a single tab.
     *   - There is no new line following the final "Enter action: " prompt.
     *
     * @param sc The reference to the Scanner object for reading input from the user.
     * @param files A string array containing the input file name, the output file name, and the
     *              dictionary file name in that exact order. The entries may be null. The length
     *              of the array is Config.NUM_FILES. The input file name is at index 
     *              Config.FILE_IN, the output file name is at index Config.FILE_OUT, and the
     *              dictionary file name is at index Config.FILE_DICT.
     * @param modFlags A boolean array where the values are true if the given modification is set
     *                 to be applied. 
     * @param modeBoth True if the display command will alternate lines from the modified text and
     *                 the original text.
     * @param showMenu If true the entire menu is shown, otherwise only the "Enter Action: " line is
                       shown.
     * @return The first character of the line inputed by the user.
     */
    public static char promptMenu(Scanner sc, String[] files, boolean[] modFlags, boolean modeBoth,
        boolean showMenu) {
        if(showMenu) {
            //Prints first three lines
            for(int i = 0; i < 80; i++) {
                System.out.print(Config.LINE_CHAR);
            }
            System.out.println("\nText Manipulator Program");
            for(int i = 0; i < 80; i++) {
                System.out.print(Config.LINE_CHAR);
            }
            //Prints input file
            System.out.print("\nCurrent input file: ");
            if(files[Config.FILE_IN] != null && files[Config.FILE_IN] != "" && files[Config.FILE_IN] != "null") {
                System.out.print(files[Config.FILE_IN]);
            }
            else {
                for(int i = 0; i < 3; i++) {
                    System.out.print(Config.LINE_CHAR);
                }
            }
            //Prints output file
            System.out.print("\nCurrent output file: ");
            if(files[Config.FILE_OUT] != null && files[Config.FILE_OUT] != "" && files[Config.FILE_OUT] != "null") {
                System.out.print(files[Config.FILE_OUT]);
            }
            else {
                for(int i = 0; i < 3; i++) {
                    System.out.print(Config.LINE_CHAR);
                }
            }
            //Prints dictionary
            System.out.print("\nCurrent dictionary: ");
            if(files[Config.FILE_DICT] != null && files[Config.FILE_DICT] != "" && files[Config.FILE_DICT] != "null") {
                System.out.print(files[Config.FILE_DICT]);
            }
            else {
                for(int i = 0; i < 3; i++) {
                    System.out.print(Config.LINE_CHAR);
                }

            }
            //Prints Current mode and mods
            System.out.println("\nCurrent mode: " + (modeBoth ? "Interleaved" : "Modified"));
            System.out.println("Current mods: " + (modFlags[Config.MOD_TRANS] ? "T" : "") + (modFlags[Config.MOD_PIG] ? "P" : "")
                + (modFlags[Config.MOD_REV_WORD] ? "W" : "") + (modFlags[Config.MOD_REV_LINE] ? "L" : ""));
            for(int i = 0; i < 80; i++) {
                System.out.print(Config.LINE_CHAR);
            }
            //Prints out options and asks for input
            System.out.println("\nMain menu:\n1) Display Output\n2) Save Output\nManipulations:");
            System.out.println("\tT)ranslate\n" +
                "\tP)ig latin\n" +
                "\tW)ord reverse\n" +
                "\tL)ine reverse");
            System.out.println("Configuration:\n" + 
                "\tI)nput file.\n" + 
                "\tO)utput file.\n" + 
                "\tD)ictionary file.\n" + 
                "\tM)ode Toggle.\n" + 
                "\tH)ide/show Menu.");
            System.out.print("Q)uit\n");
        }
        System.out.print("Enter action: ");
        return Character.toUpperCase(sc.nextLine().charAt(0));

    }

    /**
     * Prompts the user for a new file name. The prompt should be:
     * "Enter file name [curFileName]: ", where curFileName is the value from the parameter of the
     * same name. There should not be a new line following the prompt.
     *
     * @param sc The reference to the Scanner object for reading input from the user.
     * @param curFileName The current file name.
     * @return The value input by the user excluding any leading or trailing white space. If the
     *         input is an empty string, then curFileName is returned.  
     */
    public static String updateFileName(Scanner sc, String curFileName) {
        if(curFileName.equals("")) {
            curFileName = null;
        }
        System.out.print("Enter file name [" + curFileName + "]: ");
        String newFileName = sc.nextLine();
        newFileName = newFileName.trim();
        if(newFileName == "") {
            return curFileName;
        }
        else {
            return newFileName;
        }
    }

    /**
     * Opens and reads the contents of the dictionary file specified in fileName. The file is 
     * assumed to be a text file encoded in UTF-8. It is assumed that there is one translation 
     * mapping per line. Each line contains a key and its translation separated by a tab.
     * Note: The dictionary file is assumed to be sorted by the keys in ascending order. 
     *
     * For each line in the dictionary file, an entry is added into wordList. The entry is a String
     * array of length 2, where the value of index 0 is the key and the value of index 1 is the
     * translation.
     *
     * When opening the file, any FileNotFoundException is caught and the error message 
     * "Exception: File 'fileName' not found." followed by a new line is output,
     * where fileName is the name of the file that the method attempted to open.
     *
     * @param fileName
     * @param wordList Reference to ArrayList to contain the translation mappings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *                     caught when opening the file.
     */
    public static void readDictFile(String fileName,
        ArrayList<String[]> wordList) throws IOException {
        for(int i = wordList.size(); i > 0; i--) {
            wordList.remove(0);
        }
        try {
            FileInputStream ob = new FileInputStream(fileName);
            Scanner sc = new Scanner(ob);

            while(sc.hasNextLine()) {
                String entry = sc.nextLine();
                String[] def = new String[2];
                Scanner scnr = new Scanner(entry);
                def[0] = scnr.next().trim();
                def[1] = scnr.nextLine().trim();
                wordList.add(def);
                scnr.close();
            }
            sc.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Exception: File \'" + fileName + "\' not found.");
        }
    }

    /**
     * Opens and reads the contents of the input file specified in fileName. The input file is read
     * line by line. Each line is split into words and punction (excluding the apostrophe) and
     * stored in an ArrayList of Strings. These ArrayLists representing the line are stored in an
     * ArrayList of ArrayLists of Strings. Specifically, they are put in the ArrayList fileByLine
     * that is passed in as a parameter.
     *
     * For example, a file containing the following: 
     * Lorem ipsum dolor sit amet, consectetur adipiscing elit. Don'ec elementum tortor in mauris 
     * consequat vulputate.
     *
     * Would produce an ArrayList of ArrayLists containing 2 ArrayLists of Strings.
     * The first ArrayList would contain:
     *   "Lorem", "ipsum", "dolor", "sit", "amet", ",", "consectetur", "adipiscing", "elit", ".", 
     *   "Don'ec", "elementum", "tortor", "in", "mauris"
     * The second Arraylist would contain:
     *   "consequat", "vulputate", "."
     *
     * Note 1: The text file is assumed to be UTF-8.
     * Note 2: There are no assumption about the length of the file or the length of the lines.
     * Note 3: All single quotes (') are assumed to be apostrophes.
     *
     * When opening the file, any FileNotFoundException is caught and the error message 
     * "Exception: File 'fileName' not found." followed by a new line is output,
     * where fileName is the name of the file that the method attempted to open.
     *
     * @param fileName The name of the input text file to parse.
     * @param fileByLine Reference to ArrayList to contain the contents of the file line by line, 
     *                   where each line is an ArrayList of Strings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *                     caught when opening the file.
     */
    public static void readInputFile(String fileName,
        ArrayList<ArrayList<String> > fileByLine) throws IOException {

        for(int i = fileByLine.size(); i > 0; i--) {
            fileByLine.remove(0);
        }
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            Scanner sc = new Scanner(inStream);

            while(sc.hasNextLine()) {
                ArrayList<String>lineArr = new ArrayList<String>();


                String line = sc.nextLine();
                Scanner in = new Scanner(line);
                while(in.hasNext()) {
                    String temp = in.next();
                    char c = temp.charAt(temp.length()-1);
                    if(Character.isLetter(c) || c=='\'') {
                        lineArr.add(temp);
                    }
                    else{
                        lineArr.add(temp.substring(0, temp.length()-1));
                        lineArr.add(Character.toString(temp.charAt(temp.length()-1)));
                    }
                }
                in.close();
                fileByLine.add(lineArr);
            }

            sc.close();
            inStream.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Exception: File \'" + fileName + "\' not found.");
        }
    }

    /**
     * Opens and writes (overwrites if the file already exits) the modified contents of the input 
     * file specified contained in modFileByLine to a file called filename. medFileByLine is an 
     * ArrayList containing one ArrayList of String for each output line. 
     *
     * When producing the output file, each line should be terminated by a new line. Each 
     * non-punctuation should be Moreover, the
     * spacing around the punctuation should be as follows:
     * - Excluding, double quotes ("), no space between the preceding string and the punctuation and
     *   a space following.
     * - Double quotes (") will be treated as pairs:
     *    - the first double quote will be preceded by a space and will not have a space following.
     *    - the next double quote will not be preceded by space and will have a space following.  
     *
     * If modFileByLine is an ArrayList of ArrayLists containt 2 ArrayLists of Strings, such that:
     * - The first ArrayList contains:
     *   "Lorem", "ipsum", "\"", "dolor", "sit", "\"", "amet", ",", "consectetur", "adipiscing", 
     *   "elit", ".", "Don'ec", "elementum", "tortor", "in", "mauris"
     * - The second Arraylist contains:
     *   "consequat", "vulputate", "."
     *
     * The output to the file would be:
     * Lorem ipsum "dolor sit" amet consectetur adipiscing elit. Don'ec elementum tortor in mauris
     * consequat vulputate.
     *
     * Note 1: The output to the file is UTF-8.
     *
     * When opening the file, any FileNotFoundException is caught and the error message 
     * "Exception: File 'fileName' not found." followed by a new line is output,
     * where fileName is the name of the file that the method attempted to open.
     *
     * @param fileName The name of the output file.
     * @param modFileByLine Reference to ArrayList to contain the modified contents of the file line
     *                      by line, where each line is an ArrayList of Strings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *                     caught when opening the file.
     */
    public static void saveToFile(String fileName,
        ArrayList<ArrayList<String> > modFileByLine) throws IOException {

        try {
            FileOutputStream outStream = new FileOutputStream(fileName);
            PrintWriter out = new PrintWriter (outStream);
        
           for (ArrayList<String> line : modFileByLine)
           {
               for (int i=0; i < line.size(); i++)
               {
                   String word = line.get(i);
                  
                   boolean quoteOpen = false;
                   if (i!=0)
                   {
                       if(word == "\"")
                       {
                           if (quoteOpen)
                           {
                               //no space
                           }
                           else
                           {
                               out.print(" ");
                           }
                           quoteOpen = !quoteOpen;
                       }
                       else
                       {
                           if (word == "," || word == "." || word == "'")
                           {
                               //no space
                           }
                           else{
                               out.print(" ");
                           }
                       }
                   }
                   out.print(word);                 
               }
               out.println("");
           }
           out.flush();
           outStream.close();
       
        } catch (FileNotFoundException e) {
               e.printStackTrace();
               
        } catch (IOException e) {
           e.printStackTrace();
          
        } finally {
      
            
        }

  
    }

    /**
     * Prints out the modified file (and possibly interleaved with the original file) to the screen.
     *
     * If modeBoth is false, then the contents of modFileByLine is output line by line. Each word is
     * output followed by a space except for the last word. Each line is terminated with a new line
     * character.
     *
     * For the non-interleaved mode, consider the following example:
     * modFileByLine contains 2 ArrayList of Strings: 
     *          1: "Où", "est", "la", "bibliothèque", "?"
     *          2: "Aucune", "idée", "."
     *
     * The output would be:
     * Où est la bibliothèque ?
     * Aucune idée .
     *
     * Otherwise, modeBoth is true, then the contents of modFileByLine is interleaved with fileByline. 
     * Lines are printed out in order. First, a line from modFileByLine is output followed by a new
     * line character followed by the corresponding line in fileByLine. In order to better match up
     * the corresponding words in the corresponding lines, the short word is appended with spaces to
     * the length of the longer word. Between each word adjusted for length is an additional space.
     *
     * For the interleaved mode, consider the following example:
     * 
     * modFileByLine contains 2 ArrayList of Strings: 
     *          1: "Où", "est", "la", "bibliothèque", "?"
     *          2: "Aucune", "idée", "."
     * fileByLine contains 1 ArrayList of Strings: 
     *          1: "Where", "is", "the", "library", "?"
     *          2: "No", "idea", "."
     *
     * The output would be:
     * Où    est la  bibliothèque ?
     * Where is  the library      ?   
     * Aucune idée .
     * No     idea . 
     * 
     * @param fileByLine An ArrayList of ArrayList of Strings containing the original content.
     * @param modFileByLine An ArrayList of ArrayList of Strings containing the modified content.
     * @param modeBoth Flag to indicate if the original file should be interleaved.
     * @throws Exception Throws an Exception with the message "Number of lines in modified version
     *                   differs from original." if the size of fileByLine differes from 
     *                   modFileByLine.
     * @throws Exception Throws an Exception with the message "Number of words on line i in modified
     *                   version differs from original.", where i should be the 0-based line index
     *                   where the size of the ArrayList at index i in fileByLine differes from the
     *                   ArrayList at index i in modFileByLine.
     */
    public static void display(ArrayList<ArrayList<String> > fileByLine,
        ArrayList<ArrayList<String> > modFileByLine,
        boolean modeBoth) throws Exception {
        try {
            if(modeBoth) {
                //throw exception if there aren't the same # of lines
                if(fileByLine.size() != modFileByLine.size()) {
                    throw new Exception("Difference in number of lines.");
                }

                for(int line = 0; line < fileByLine.size(); line++) {

                    //throw exception if there aren't the same # of words on the line
                    if(fileByLine.get(line).size() != modFileByLine.get(line).size()) {
                        throw new Exception("Number of words on line " + line + " in modified version differs from original.");
                    }

                    //print modified file line
                    for(int word = 0; word < fileByLine.get(line).size()-1; word++) {
                        int origLen = fileByLine.get(line).get(word).length();
                        int modLen = modFileByLine.get(line).get(word).length();
                        String modWord = modFileByLine.get(line).get(word);
                        int numSpaces = Math.abs(origLen-modLen);
                        System.out.print(modWord + " ");
                        if(origLen > modLen) {
                            for(int i = 0; i < numSpaces; i++) {
                                System.out.print(" ");
                            }
                        }
                    }
                    System.out.print(modFileByLine.get(line).get(modFileByLine.get(line).size()-1));
                    System.out.println();

                    //print original file line
                    for(int word = 0; word < fileByLine.get(line).size()-1; word++) {
                        int origLen = fileByLine.get(line).get(word).length();
                        String origWord = fileByLine.get(line).get(word);
                        int modLen = modFileByLine.get(line).get(word).length();
                        int numSpaces = Math.abs(origLen-modLen);
                        System.out.print(origWord + " ");
                        if(origLen < modLen) {
                            for(int i = 0; i < numSpaces; i++) {
                                System.out.print(" ");
                            }
                        }
                    }
                    System.out.print(fileByLine.get(line).get(fileByLine.get(line).size()-1));
                    System.out.println();
                    System.out.println();


                }
            }
            else {
                for(int line = 0; line < modFileByLine.size(); line++) {
                    for(int word = 0; word < modFileByLine.get(line).size()-1; word++) {
                        System.out.print(modFileByLine.get(line).get(word) + " ");
                    }
                    System.out.print(modFileByLine.get(line).get(modFileByLine.get(line).size()-1));
                    System.out.println();
                }
            }
        }
        finally {}
    }

    /**
     * Performs the actions specified by the modFlags to the input file stored in the ArrayList of
     * ArrayLists of Strings fileByLine. This method stores the modified string in a new ArrayList
     * of ArrayLists of Strings which it returns.
     *
     * There are 4 modifications that may be performed. They are to be process in the following 
     * order if indicated in modFlags:
     *   1 - Translation
     *   2 - To Pig Latin
     *   3 - Reverse the letters in each word
     *   4 - Reverse the words in each line
     *
     * @param fileByLine The original file stored as an ArrayList of ArrayLists of Strings.
     * @param dict An ArrayList of String arrays of length two. The ArrayList is assumed to be
     *             sorted in ascending order according to the strings at index 0. This will be the
     *             second argument for the translate method.
     * @param modFlags A boolean array of length Config.NUM_MODS that indicates which translation 
     *                 to perform by having a value of true in the appropriate cell as follows:
     *                 Index                  Modification
     *                 -------------------    --------------------------------
     *                 Config.MOD_TRANS       Translation
     *                 Config.MOD_PIG         To Pig Latin
     *                 Config.MOD_REV_WORD    Reverse the letters in each word
     *                 Config.MOD_REV_LINE    Reverse the words in each line
     *@return An ArrayList of ArrayLists of Strings, where each internal ArrayList is a line 
     *        which corresponds to the data in fileByLine but with the transformations applied in 
     *        the order specified above.
     */
    public static ArrayList<ArrayList<String> > manipulate(ArrayList<ArrayList<String> > fileByLine,
        ArrayList<String[]> dict,
        boolean[] modFlags) {
        //Leave the method as is for Milestone 1.
        //return fileByLine;

        //For Milestone 2, you will need to build a new ArrayList<ArrayList<String>> that will be
        //returned. Go through the each string in fileByLine and, if the boolean at
        //Config.MOD_TRANS in modFlags is true, then call the translate method, storing the modified
        //strings (otherwise store the original string) line by line as they are organized in fileByLine.
        ArrayList<ArrayList<String>> modFileByLine = new ArrayList<ArrayList<String>>();
        for(int line = 0; line < fileByLine.size(); line++) {
            modFileByLine.add(new ArrayList<String>());
            for(int wordN = 0; wordN < fileByLine.get(line).size(); wordN++) {
                String word = fileByLine.get(line).get(wordN);
                if(modFlags[Config.MOD_TRANS]) {
                    word = translate(word, dict);
                }
                if(modFlags[Config.MOD_PIG]) {
                    word = matchCase(word, pigLatin(word));
                }
                if(modFlags[Config.MOD_REV_WORD]) {
                    word = reverse(word);
                }
                modFileByLine.get(line).add(word);
            }
            if(modFlags[Config.MOD_REV_LINE]) {
                modFileByLine.set(line, reverse(modFileByLine.get(line)));
            }
        }
        return modFileByLine;

        //For Milestone 3, you will need to build a new ArrayList<ArrayList<String>> that will be
        //returned. For each string in fileByLine, you will need to check the booleans at
        //Config.MOD_TRANS, Config.MOD_PIG, and Config.MOD_REV_WORD in modFlags in that order. For
        //each one that is true, apply the appropriate transformation, storing them as in
        //Milestone 2. After having processed each string, if the boolean at Config.MOD_REV_LINE
        //in modFlags is true, reverse each line in the returning ArrayList<ArrayList<String>>.
    }


    /**
     * This is the main method for the TextManipulator program. This method contains the loop that
     * runs the prompt. It handles the user response and calls the methods that are necessary in
     * order to perform the actions requested by the user.
     *
     * The initial default behavior of the program is to show the full menu, to be in the "Modified"
     * mode, to have no modifications selected, and to have no values for the various file names.
     *
     * @param args (unused)
     */
    public static void main(String[] args) {
        //FIXME
        Scanner sc = new Scanner(System.in);
        String[] files = new String[Config.NUM_FILES];
        boolean[] modFlags = new boolean[Config.NUM_MODS];
        boolean modeBoth = false;
        boolean showMenu = true;
        ArrayList<ArrayList<String>> fileByLine = new ArrayList<ArrayList<String>>();
        ArrayList<String[]> dictionary = new ArrayList<String[]>();
        String currentFileName = "";
        String currentDictName = "";
        String currentOutName = "";
        boolean textLoop = true;
        //uses a while loop to run if textLoop is true
        while (textLoop){
            //calls promptMenu() to take input from the user and stores it in the variable action
            char action=promptMenu(sc, files, modFlags, modeBoth, showMenu);
            //condition to check if action==1
            if(action== '1') {
                for(int i = 0; i < 80; i++) {
                    System.out.print(Config.LINE_CHAR);
                }
                System.out.println();

                try {
                    display(fileByLine, manipulate(fileByLine, dictionary, modFlags), modeBoth);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }

                for(int i = 0; i < 80; i++) {
                    System.out.print(Config.LINE_CHAR);
                }
                System.out.println();
            }
            //condition to check if action==2
            else if(action== '2') {
                try {
                    saveToFile(files[Config.FILE_OUT], manipulate( fileByLine , dictionary, modFlags));
                }
                catch(IOException e) {
                    System.out.println("Error closing file stream");
                }
            }
            //condition to check if action == T
            else if(action== 'T') {
                modFlags[Config.MOD_TRANS] = !modFlags[Config.MOD_TRANS];

            }
            //condition to check if action == P
            else if(action== 'P') {
                modFlags[Config.MOD_PIG] = !modFlags[Config.MOD_PIG];
            }
            //condition to check if action == W
            else if(action== 'W') {
                modFlags[Config.MOD_REV_WORD] = !modFlags[Config.MOD_REV_WORD];
            }
            //condition to check if action == L
            else if(action=='L') {
                modFlags[Config.MOD_REV_LINE] = !modFlags[Config.MOD_REV_LINE];
            }
            //condition to check if action == I
            else if(action== 'I') {
                try {
                    String temp = updateFileName(sc, currentFileName);
                    if(!temp.equals(currentFileName)) {
                        currentFileName = temp;
                        readInputFile(currentFileName, fileByLine);
                    }
                    files[Config.FILE_IN]=currentFileName;
                }
                catch(IOException e) {
                    System.out.println("Error closing file stream");
                }
            }
            //condition to check if action == D
            else if(action== 'D') {
                try {
                    String temp2 = updateFileName(sc, currentDictName);
                    if(!temp2.equals(currentDictName)) {
                        files[Config.FILE_DICT] = temp2;
                        readDictFile(files[Config.FILE_DICT], dictionary);
                    }
                }
                catch(IOException e) {
                    System.out.println("Error closing file stream");
                }
            }
            //condition to check if action == M
            else if(action== 'M') {
                modeBoth = !modeBoth;
            }
            //condition to check if action == H
            else if(action== 'H') {
                showMenu = !showMenu;
            }
            //condition to check if action == Q
            else if(action== 'Q') {
                textLoop = false;
            }
            //condition to check if action == O
            else if(action== 'O') {
                String tempNewName = updateFileName(sc, currentOutName);
                if(!tempNewName.equals(currentOutName)) {
                    files[Config.FILE_OUT]=tempNewName;
                }
            }
            else {
                System.out.print("Unknown option.\n");
            }
        }
    }
}


