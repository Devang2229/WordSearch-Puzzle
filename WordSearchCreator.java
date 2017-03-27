/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsearchcreator;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author janid
 */
public class WordSearchCreator {

    //keyboard is a Scanner
    private Scanner keyboard = new Scanner(System.in);

    //Reserves Memory in the RAM for the userinput
    private String userType;

    //reserves the space in Ram for the number enterd by user  
    private static int numberOfWords;

    //reserves the ram for the wordlist.
    private static String[] words;

    //reserves the space in the ram for the word puzzle.
    private static char[][] wordPuzzle;

    public WordSearchCreator() {

        //Repeats the question until it doesnot get in the valid range.(5-20)   
        do {
            //ask for the number of words from user.
            System.out.println("How many words will go in the puzzle? (5-20) ");

            //Accepts the integer and saves to the Ram (numberOfWords).
            userType = keyboard.nextLine();
        } while (checkNumber(userType));

        //reserves the space in Ram for the number enterd by user  
        numberOfWords = Integer.parseInt(userType);

        //reserves the ram for the wordlist.
        words = new String[numberOfWords];

        //reserves the space in the ram for the word puzzle.
        wordPuzzle = new char[numberOfWords][numberOfWords];

    }

    public static void main(String[] args) {
        WordSearchCreator myPuzzle = new WordSearchCreator();
        myPuzzle.getWords(); 
       myPuzzle.fillWords();
       myPuzzle.fillEmpties();
      
      
        myPuzzle.displayPuzzle();
        System.out.printf("Here are the Words to find: \n");
        myPuzzle.displayWordlist();
       }

    public void getWords() {

        for (int count = 0; count < numberOfWords; count++) {
            System.out.println("Please enter a new Word: ");
            String newWord = keyboard.nextLine().toUpperCase();

            try {
                validator(newWord);
                words[count] = newWord;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                count--;
            }
        }
        Arrays.sort(words);
    }

    public void validator(String newWord) {

        if (newWord.length() < 2) {
            throw new IllegalArgumentException("The word entered is too small.");
        }
        if (newWord.length() > numberOfWords) {
            throw new IllegalArgumentException("The word entered is too big.");
        }

        if (newWord.contains(" ")) {
            throw new IllegalArgumentException("The word has Space.");
        }

    }

    private void displayWordlist() {
        for (int count = 0; count < numberOfWords; count++) {
            System.out.println(words[count]);
        }
    }

    //checking the do while loop statemnts .
    private static boolean checkNumber(String checkNum) {
        try {
            //checks if its empty
            if (checkNum.isEmpty()) {
                throw new IllegalArgumentException("Please enter a valid number (5-20).");
            }
            //check if it was a string 
            if (!tryParseInt(checkNum)) {
                throw new IllegalArgumentException("Please enter a valid number (5-20).");
            }
            int numberOfWords = Integer.parseInt(checkNum);
            if (numberOfWords > 20) {
                throw new IllegalArgumentException("Its too high. Please Enter the number from 5-20");
            }
            if (numberOfWords < 5) {
                throw new IllegalArgumentException("Its too low. Please eneter the number from  5-20");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    //true if a number and false if other than a number 
    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
/*
    this method fills the word in the puzzle 
    */
    public void fillWords() {
        for (int row = 0; row < words.length; row++) {
            char[] letterName = words[row].toCharArray();
            Random rng = new Random();
            int fillLetters = rng.nextInt(wordPuzzle.length - (words[row].length()-1));
            for (int col = 0; col < letterName.length; col++) {
                wordPuzzle[col + fillLetters][row] = letterName[col];
            }

        }
       
    }
/*
    this method fills the excess space left in the puzzle after we fill the words in puzzle.
    */
    public void fillEmpties() {
        for (int row = 0; row < words.length; row++) {
            for (int col = 0; col < words.length; col++) {
                if (wordPuzzle[row][col] == 0) {
                    Random rng = new Random();
                 char alphabet = (char)(rng.nextInt(26)+65);
                 wordPuzzle[row][col] = alphabet;
                }
                
            }

        }

    }
    
    
    /*
            This method diplays the word puzzle as a grid in the console.
    */
    public void displayPuzzle(){
        for( int row = 0; row < wordPuzzle.length; row++){
            for (int col = 0; col < wordPuzzle[row].length; col++){
                System.out.printf("   %s   ", wordPuzzle[row][col]);
            }
        System.out.println();
        }
    }
}