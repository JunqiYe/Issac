// Junqi Ye
// CSE 143 AM with Sam Lee
// HW4 Evil Hangman
// This class manages the Hangman game. It manages details about all the 
// possible words, the current word pattern, the number of guesses left, and 
// feed back about the letter they guessed.

import java.util.*;

public class HangmanManager {

   // stores a "list" of the word that hangman will use.
   private Set<String> wordSet;
   
   // stores Character that have been guesses by the client.
   private Set<Character> guessedChar;
   
   // represents the remaining guesses for the client.
   private int guesses;
      
   // stores patterns and the words that follow that specific pattern.
   private Map<String, Set<String>> wordFamily;
   
   // stores the current pattern that client will see.
   private String pattern;
   
   
   
   // pre : take a Collectrion of String representing the word that will be in
   // the hangman game, an int length represent the length of the word that 
   // client will guess, and an int max represent the maximum try the client is
   // allowed to guess the word. If the length for the guessing word is less
   // than 1 or if the maximum trys allowed is less than 0, 
   // throw IllegalArgumentException.
   // post: Store all the word that have the length client is guessing, save 
   // the number of the trys user is allowed. 
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      this();
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      
      guesses = max;     
      for (String word: dictionary) {
         if (word.length() == length) {
            wordSet.add(word);
         }
      }      
      // initial pattern
      pattern = "-";
      for (int i = 0; i < length - 1; i++) {
         pattern += " -";
      }
   }   
   
   
   
   // post: default constructor that initilize the fields.
   private HangmanManager() {
      wordSet = new TreeSet<String>();
      guessedChar = new TreeSet<Character>();

   }
   
   
   
   // post: return the words that are currently being considered in the game
   public Set<String> words() {
      return wordSet;
   }
   
   
   
   // post: return a int represent the number of guessing chances are left for 
   // the client.
   public int guessesLeft() {
      return guesses;
   }
   
   
   
   // post: return a set of Characters that contains the characters that client
   // have guessed before.   
   public Set<Character> guesses() {
      return guessedChar;
   }
   
   
   
   // post: return a string represents the current pattern that the client will
   // see. The pattern is either an alphabet letter of a "-". If the set of 
   // word is empty, throw IllegalStateException.
   public String pattern() {
      if (wordSet.isEmpty()) {
         throw new IllegalStateException();
      }
      return pattern;
   }
   
   
 
   // pre : takes a char and test if the character is in the word. If the
   // set of words is empty or the number of guess is less than 1, throw 
   // IllegalStateException. If the set of words is not empty and a character
   // passed was guessed before, throw IllegalArgumentException.
   // post: returns the number of occurances of the character in the word.
   public int record(char guess) {
      if (guesses < 1 || wordSet.isEmpty()) {
         throw new IllegalStateException();
      } else if (!wordSet.isEmpty() && guessedChar.contains(guess)) {
         throw new IllegalArgumentException();
      }
     
      guessedChar.add(guess);     
      createMap(guess);
      String keyPattern = patternForSet();
      wordSet = new TreeSet<String>(wordFamily.get(keyPattern));
      return updatePattern(keyPattern);
   }
   
   
   
   // helper method 
   // pre : takes a char that represent the character that was guessed.
   // post: associate each word to a pattern. 
   private void createMap(char guess) {
      wordFamily = new TreeMap<String, Set<String>>();
      for (String word: wordSet) {
         String temp = pattern;
         for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i + 1).equals(guess + "")) {
               temp = temp.substring(0, i * 2) + word.substring(i, i + 1) + 
                      temp.substring(i * 2 + 1);
            }
         }
         if (!wordFamily.containsKey(temp)) {
            wordFamily.put(temp, new TreeSet<String>());
         }
         wordFamily.get(temp).add(word);        
      }
   }
   
   
   // helper method
   // post: return the pattern that have the most word in it.
   private String patternForSet() {
      int max = 0;
      String keyPattern = "";
      for (String key: wordFamily.keySet()) {
         if (wordFamily.get(key).size() > max) {
            max = wordFamily.get(key).size();
            keyPattern = key;
         }
      }
      return keyPattern;
   }
   
   
   
   // helper method 
   // pre : takes a string representing the pattern that has the most words.
   // post: return the number of differences between the previous pattern and 
   // the new pattern. Update the number of guesses avaliable. Update the 
   // current pattern.
   private int updatePattern(String keyPattern) {
      int count = 0;
      for (int i = 0; i < keyPattern.length(); i += 2) {
         if (!keyPattern.substring(i, i + 1).equals(pattern.substring(i, i + 1)) &&
            pattern.substring(i, i + 1).equals("-")) {
            
            pattern = pattern.substring(0, i) + keyPattern.substring(i, i + 1) +
                      pattern.substring(i + 1);
            count++;
         }
      }
      if (count == 0){
         guesses--;
      }
      return count;  
   }
}