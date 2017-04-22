package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;
	
	
	public static String[] scanVocabulary(Scanner scanner){          // Q - 1
		String[] words = new String[MAX_VOCABULARY_SIZE]; // Create a word list with the max size allowed.
		String temp; // temp string.
		int legalWordCount = 0; // count only valid words.
		
		while(scanner.hasNext() && legalWordCount <= MAX_VOCABULARY_SIZE){// checks if the scanner reached the end
			temp = scanner.next().toLowerCase(); //get the next word.
			// checks if the current word in the scanner doesn't exists in the word list and it contains only letters.
			if(containOnlyLetters(temp) && !wordExists(words, temp)){ 
				words[legalWordCount] = temp; // put the word in the list
				legalWordCount++; // increment the valid word counter
			}			
		}
		for(int i=0;i<legalWordCount;i++){ // sort the words in lexicographic order - i used the code i worte in hw3
			temp = words[i];
			for(int j=0;j<legalWordCount;j++){
				if(temp.compareTo(words[j]) < 0){ // compare the i'th word to all other words in the string
					temp = words[j]; // if so, swap
					words[j] = words[i];
					words[i] = temp;
				}
			}
		}
		String[] finalWordArray = new String[legalWordCount]; // final word array with size of only the valid words
		for(int i=0;i<legalWordCount;i++){
			finalWordArray[i] = words[i];
		}
		//System.out.println(Arrays.toString(finalWordArray));
		//System.out.println(finalWordArray.length);
		return finalWordArray;
	}
	
	
	
	public static boolean isInVocabulary(String[] vocabulary, String word){ // Q - 2
		return wordExists(vocabulary, word);
	}

	
	public static boolean isLegalPuzzleStructure(char[] puzzle){  // Q - 3
		//System.out.println(puzzle);
		boolean hasHidden = false;
		for(int i=0;i<puzzle.length;i++){ // loop all chars in puzzle input
			if(!Character.isLetter(puzzle[i]) && puzzle[i] != HIDDEN_CHAR){ // check if the current char if a letter or the Hidden char, if not return false.
				return false;
			}
			if(puzzle[i] == HIDDEN_CHAR){ // if Hidden char found, mark hasHidden as true, still we need to check all the chars in puzzels.
				hasHidden = true;
			}
		}
		return hasHidden;
	}
	
	
	public static int countHiddenInPuzzle(char[] puzzle){ // Q - 4
		int hiddenCounter = 0; // init the hidden char counter
		for(int i=0;i<puzzle.length;i++){
			if(puzzle[i] == HIDDEN_CHAR){
				hiddenCounter++; // increment the hidden char counter
			}
		}
		return hiddenCounter;
	}
	
	
	public static boolean checkSolution(char[] puzzle, String word){ // Q - 5
		//System.out.print(puzzle);
		//System.out.println(" " + word);
		if(puzzle.length != word.length()){ // if the word and the puzzle are not the same length return false.
			return false;
		}
		for(int i=0;i<puzzle.length;i++){
			if(puzzle[i] != HIDDEN_CHAR && puzzle[i] != word.charAt(i)){ // if the current char is not a hidden char and it does not match the char in the word, return false
				return false;
			}
			 // if the current char is not a hidden char, check all the chars in the word, if we find duplicates and in that location the puzzel char is hidden, return false.
			else if(puzzle[i] != HIDDEN_CHAR){
				for(int j=0;j<word.length();j++){
					if(word.charAt(j) == word.charAt(i) && puzzle[j] == HIDDEN_CHAR){
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	
	public static String checkSingleSolution(char[] puzzle, String[] vocabulary){ // Q - 6
		int numOfSolutions = 0;
		int solution = 0; // will be the location of the solution
		for(int i=0;i<vocabulary.length;i++){
			if(checkSolution(puzzle, vocabulary[i])){ // use checkSolution to see if the current word is a valid solution to the puzzle
				solution = i;
				numOfSolutions++;
			}
		}
		if(numOfSolutions == 1){ // if we found only 1 solution, return it otherwise return null
			return vocabulary[solution];
		}
		else{
			return null;
		}
		
	}
	
	
	public static int applyGuess(char guess, String solution, char[] puzzle){ // Q - 7
		// replace with your code
		return 0;
	}


	public static void main(String[] args) throws Exception{ //Q - 8
		// add your code here
	}

	/**
	 * 
	 * @param str
	 * @return true if str contain only words and false otherwise
	 */
	public static boolean containOnlyLetters(String str) {
	    return str.matches("[a-zA-Z]+");
	}
	
	/**
	 * 
	 * @param wordList
	 * @param word
	 * @return true if the word exists in the list and false otherwise
	 */
	public static boolean wordExists(String[] wordList, String word){
		for(int i=0;i<MAX_VOCABULARY_SIZE;i++){
			if(word.equals(wordList[i])){
				return true;
			}
		}
		return false;
	}

	/*************************************************************/
	/*********************  Don't change this ********************/
	/*************************************************************/
	
	public static void printReadVocabulary(String vocabularyFileName, int numOfWords){
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void printSettingsMessage(){
		System.out.println("--- Settings stage ---");
	}
	
	public static void printEnterPuzzleMessage(){
		System.out.println("Enter your puzzle:");
	}
	
	public static void printIllegalPuzzleMessage(){
		System.out.println("Illegal puzzle, try again!");
	}
	
	public static void printIllegalSolutionsNumberMessage(){
		System.out.println("Puzzle doesn't have a single solution, try again!");
	}
	
	
	public static void printGameStageMessage(){
		System.out.println("--- Game stage ---");
	}
	
	public static void printPuzzle(char[] puzzle){
		System.out.println(puzzle);
	}
	
	public static void printEnterYourGuessMessage(){
		System.out.println("Enter your guess:");
	}
	
	public static void printCorrectGuess(int attemptsNum){
		System.out.println("Correct Guess, " + attemptsNum + " guesses left");
	}
	
	public static void printWrongGuess(int attemptsNum){
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
	}

	public static void printWinMessage(){
		System.out.println("Congratulations! You solved the puzzle");
	}
	
	public static void printGameOver(){
		System.out.println("Game over!");
	}

}
