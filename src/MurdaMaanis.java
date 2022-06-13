/**
 * @author Sonu Gupta
 * @version 1.5
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MurdaMaanis {

	public static void main(String[] args) throws FileNotFoundException {
	    // File&Scanner function reads the file from the director - make sure your path is appropriate
        File dictionary = new File("D:\\EclipseProj\\MurdaMaanis\\src\\wordlistDx.txt");
        Scanner read = new Scanner(dictionary); //read from wordlist file
        Scanner input = new Scanner(System.in); //read from Keyboard
        
        //Creating list to store String of words - if needed we can loop through it
        ArrayList<String> words = new ArrayList<>();
        
        //Loops till the end of wordlist file and adds to the ArrayList (words) we created^
        while(read.hasNext()) {
        	words.add(read.nextLine());
        	}
        //picks random word from our ArrayList - get passes index as parameter
        Random rand = new Random();
        String guessWord = words.get(rand.nextInt(words.size()));
        
        //Creating arrayList of Char to compare and store chars of guessWord
        ArrayList<Character> Guesses = new ArrayList<>();
        
        /**
         * This while loop is where I implementing most of my methods that I created!
         * So, we are creating invalidCount variable to keep track of invalid entries user makes 
         * and when invalid count is 6 or greater we want to stop the Game
         * And also we are passing our invalidCount as a parameter to printMurdaMaanis method, which will make our CLI MurdaMaanis (dead person)
         */
        int invalidCount = 0;
        while(true) {
        	printMurdaMaanis(invalidCount);
            
        	if (invalidCount >=6) {
        		System.out.println("Sorry! You lose!");
        	}
        	printGameState(guessWord, Guesses);
           //if return value from method is false
          if (!getGuess(input, guessWord, Guesses)) 
        	  invalidCount++;
        	  
           //if validguess_count == guessWord.length()
           if (printGameState(guessWord, Guesses) == true) {
        	   System.out.println("Congratulation, you win!");
        	   break;
           }
           //giving user chance to guess out the complete word, when user's validCount is >= 2 
           if (GuessEntireWordChoice(guessWord, Guesses)) { //if true
           System.out.println("Enter your guess for the word: ");
           if(input.nextLine().equals(guessWord)) {
        	   System.out.println("Congratulation, you win!");
        	   break;
           }
           else 
        	   System.out.println("Incorrect, please keep playing!");  
	      }
        }
       
	}
	/**
	 * This method prints dead person or MurdaMaanis for each invalid choice we make till 5 invalid choices!
	 * We are passing our invalidCount as a parameter to printMurdaMaanis method, which will make our CLI MurdaMaanis (dead person)
	 * @param invalidCount
	 */
	private static void printMurdaMaanis(int invalidCount) {
		System.out.println(" *******");
		System.out.println(" |     |");
        if (invalidCount >= 1) 
		  System.out.println(" O");
		
		if (invalidCount >= 2) 
		 {
		   System.out.print("\\ ");
		      if (invalidCount >= 3) 
		         System.out.println("/");
		     
		      else 
		          System.out.println("");
		  }
		
         if (invalidCount >= 4) 
		  System.out.println(" |");
		
		 if (invalidCount >= 5) 
		 {
		    System.out.print("/ ");
		       if (invalidCount >= 6)
		          System.out.println("\\");
		  
		       else 
		          System.out.println("");
		  }
		System.out.println("");
		System.out.println("");
	}
	/**
	 * Get's letter input from user and fills it into our Guesses Arraylist
	 * @param input
	 * @param guessWord
	 * @param Guesses
	 * @return
	 */
	private static boolean getGuess(Scanner input, String guessWord, ArrayList<Character> Guesses) {
		System.out.println("Please enter a letter: ");
        String UserInput = input.nextLine();
        Guesses.add(UserInput.charAt(0)); //filling guesses(list) with userInput - just the first index(0)
	    return guessWord.contains(UserInput); //returns true. if not then false
	}
    /**
     * This function prints GameState and will be intended to run after every user input
     * Also, it returns boolean which will be used to declare the gameState: stop or continue
     * @param guessWord: iterating through it and comparing it to Guesses
     * @param Guesses: when Guesses equals a char of guessWord
     * we are printing out guessWord. If not then we still keeping it decrypted
     */
	private static boolean printGameState(String guessWord, ArrayList<Character> Guesses) {
		int validGuess_count = 0;
		for (int i = 0; i < guessWord.length(); i++) {
        	if (Guesses.contains(guessWord.charAt(i))) {
        		System.out.print(guessWord.charAt(i));
        		validGuess_count++;
        	}
        	else
        		System.out.print("-");	
        }
        System.out.println();
        return (validGuess_count == guessWord.length());
	}
	
	/**
	 * Specialty of MurdaMaanis Game!
     * This method is special inclusion this version of the game where we want to start game giving 
     * a chance to user to make a guess which is an entire word, when they make it to two or more valid letter guesses!
     * It returns boolean: true or false!
     * @param guessWord: iterating through it and comparing it to Guesses
     * @param Guesses: when Guesses equals a char of guessWord
     */
	private static boolean GuessEntireWordChoice(String guessWord, ArrayList<Character> Guesses) {
		boolean state = false;
		int validGuess_count = 0;
		for (int i = 0; i < guessWord.length(); i++) {
        	if (Guesses.contains(guessWord.charAt(i))) 
        		validGuess_count++;
        	if (validGuess_count >=2)  
        		state = true;	
        	}
		return state;
	      }
}
