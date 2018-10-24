// Junqi Ye
// CSE 143 AM with Sam Lee
// HW4 Evil Hangman

import java.util.*;

public class HangmanManager {

   private Set<String> wordSet;
   
   private Set<Character> guessedChar;
   
   private int guesses;
   
   private Map<String, Set<String>> wordFamily;
   


   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      
      guesses = max;
      wordSet = new TreeSet<String>();
      guessedChar = new TreeSet<Character>();
      for (String word: dictionary) {
         if (word.length() == length) {
            wordSet.add(word);
         }
      }
   
   }   
   
   
   public Set<String> words() {
      return wordSet;
   }
   
   
   public int guessesLeft() {
      return guesses;
   }
   
   public Set<Character> guesses() {
      return guessedChar;
   }
   
   public String pattern() {
      if (!wordSet.isEmpty()) {
         throw new IllegalStateException();
      }
      
      String temp = "";
      return temp;
   }
   
   
   public int record(char guess) {
      if (guesses < 1 || wordSet.isEmpty()) {
         throw new IllegalStateException();
      } else if (!wordSet.isEmpty() && guessedChar.contains(guess)) {
         throw new IllegalArgumentException();
      }
      
      guessedChar.add(guess);
      wordFamily = new TreeMap<String, Set<String>>();
      for (int i = 0; i < ) {
         for (String word: dictionary) {
            if (word.length() == length) {
               wordSet.add(word);
            }
         }
      }

   }

}