// Junqi Ye
// CSE 143 with Sam Lee
// HW6
// This program takes a word and finds all the possible combination of words
// that would make up the it with the same letters. The client can limit the
// amount of words inside each combination.

import java.util.*;

public class AnagramSolver {

   private Map<String, LetterInventory> map;
   
   private List<String> originalDict;
   
      
   // pre : takes a list of word that represent words in the dictionary. The
   // list of dictionary words should not be null.
   // post: creates an anagramsolver object. Save the list of word of 
   // dictionary. The original list will not be modified.
   public AnagramSolver(List<String> dict) {
      map = new HashMap<String, LetterInventory>();
      originalDict = dict;
      
      for (String word: dict) {
         LetterInventory temp = new LetterInventory(word);
         map.put(word, temp);
      }
   }
   
   
   // pre : takes a string and a integer. The method will find all the possible
   // combination of word from the dictionary that make up the string word with 
   // the same letters. The integre represents the maximum number that will be
   // in the combination. If the integer is 0, it represents that there are no 
   // maximum number and will perduce all the possible combination. If the int
   // is less than 0, IllegalArgumentException will be thrown. The String should
   // not be a null string.
   // post: the method will output all the result on the screen. The combination
   // of words displayed will be inside two square brackets([]) and separeted by
   // commas. The order of the combination will follow the dictionary that was
   // passed in the contructor. If there are no possible combination, nothing 
   // will be outputed.
   public void print(String s, int max) {   
      if (max < 0) {
         throw new IllegalArgumentException();
      } 
      
      LetterInventory inventory = new LetterInventory(s);
      List<String> shorterDict = new ArrayList<String>(); // creating a temperary shorter 
                                                          // dictionary of words
      
      for (String word: originalDict) {
         if (inventory.subtract(map.get(word)) != null) {// putting words that can be subtracted
            shorterDict.add(word);                       // from the inventory into the temperary
         }                                               // dictionary of words.
      }
      print(inventory, max, new ArrayList<String>(), shorterDict);
   }
   
   
   // public-private pair of the print method 
   // pre : takes a LetterInventory representing the current letters that are
   // left in the word, an integer max representing the maximum number of words
   // in the particular combination, and a list of string that saves the 
   // combination. Another list of string contains the dictionary of word 
   // that the method will go over and find the anagram from.
   // post: output the combination of word.
   private void print(LetterInventory inventory, int max, List<String> result, 
                      List<String> dict) {

      if (inventory.isEmpty()) {
         System.out.println(result);
      } else if(max == 0 || result.size() < max) {
         for (String word : dict) {
            if (word.length() <= inventory.size()) {
               LetterInventory tempInventory = inventory.subtract(map.get(word));
               if (tempInventory != null) {
                  result.add(word);
                  print(tempInventory, max, result, dict);
                  result.remove(result.size() - 1);
               }
            }
         }
      }
   }
}
