package il.ac.tau.cs.sw1.ex5;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14000;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		String[] vocab = new String[MAX_VOCABULARY_SIZE];
		int wordCount = 0;
		boolean hasChar = false;
		boolean isInt = false;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String line;
	    while ((line = br.readLine()) != null) {
	       String[] splited = line.split("\\s+");
	       for(int i=0;i<splited.length;i++){
	    	   hasChar = hasChar(splited[i]);
	    	   if(hasChar == false){
	    		   isInt = isInt(splited[i]);
	    	   }
	    	   if(hasChar == true || isInt == true){
	    		   if(isInt == true){
	    			   splited[i] = SOME_NUM;
	    		   }
	    		   int pos = stringInArray(vocab, splited[i]);
	    		   if(pos == -1){
	    			   if(wordCount == MAX_VOCABULARY_SIZE){
	    				   break;
	    			   }
	    			   vocab[wordCount] = splited[i].toLowerCase();
	    			   wordCount++;
	    		   }
	    	   }
	       }
	    }
	    br.close();
		String[] finalWordArray = new String[wordCount]; // final word array with size of only the valid words
		for(int i=0;i<wordCount;i++){
			finalWordArray[i] = vocab[i];
		}
		//System.out.println(Arrays.toString(finalWordArray));
		return finalWordArray;
	}
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int[][] bigramCounts = new int[vocabulary.length][vocabulary.length];
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String line;
	    //System.out.println(Arrays.toString(vocabulary));
	    while ((line = br.readLine()) != null) {
	    	String[] splited = line.split("\\s+");
	    	for(int i=0;i<splited.length-1;i++){
	    		//System.out.print(splited[i] + " ");
	    		//System.out.println(splited[i+1]);
	    		int x = stringInArray(vocabulary, splited[i]);
	    		int y = stringInArray(vocabulary, splited[i+1]);
	    		if(x >= 0 && y>= 0){
	    			bigramCounts[x][y]++;
	    		}
	    	}
	    }
	    br.close();
	    
	    //for(int i=0;i<vocabulary.length;i++)
	    	//System.out.println(Arrays.toString(bigramCounts[i]));
		return bigramCounts;

	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		// add your code here
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		// add your code here
	}

	
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: word is in lowercase
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		// replace with your code
		return 0;
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		// replace with your code
		return 0;
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		// replace with your code
		return null;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		// replace with your code
		return false;
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = 0, otherwise
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		// replace with your code
		return 0.;
	}

	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		// replace with your code
		return null;
	}
	
	/*
	 * Gets array of strings and a string and check if the string array contains the string,
	 * If it does, return its place
	 * If the array is full return -2
	 * if the word is not in the array, return -1
	 */
	private int stringInArray(String[] array, String word){
		int j = 0;
		for(j=0;j<array.length;j++){ // sacn the array
		   if(array[j] == null){ // if we got a null value this means there are no more words in the array
			   return -1;
		   }
		   if(array[j].equals(word.toLowerCase()) == true){ // we found the word
			   return j;
		   }
	   }
		return -2;
	}
	
	private boolean hasChar(String str){
		for(int j=0;j<str.length();j++){ // scan the string
 		   if(Character.isLetter(str.charAt(j))){ // if the currnet char is a Letter return true
 			   return true;
 		   }
 	   	}
		return false; // no Letters found
	}
	
	private boolean isInt(String str){
		try { // try to parse the string to an integer, if successful, return true 
		   Integer.parseInt(str);
		   return true;
		}catch(NumberFormatException e){
			   return false; // if we got NumberFormatException, return false
		}
	}
	
}

