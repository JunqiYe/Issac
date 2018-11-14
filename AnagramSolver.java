// Junqi Ye
// CSE 143 with Sam Lee
// HW6
// This program takes a word and finds all the possible combination of words
// that would make up the it with the same letters.

import java.util.*;

public class AnagramSolver {

   private Map<String, LetterInventory> map;
   
   private List<String> dict;
   
   
   // pre : takes a list of word that represent words in the dictionary.
   // post: creates an anagramsolver object. Save the list of word of 
   // dictionary. The original list will not be modified.
   public AnagramSolver(List<String> dict) {

      map = new HashMap<String, LetterInventory>();
      this.dict = dict;
      
      for(String word: dict){
         LetterInventory temp = new LetterInventory(word);
         map.put(word, temp);
      }
   }
   
   
   // pre : takes a string and a integer. The method will find all the possible
   // combination of word from the dictionary and make up the string word with 
   // the same letters. The integre represents the maximum number that will be
   // in the combination. If the integer is 0, it represents that there are no 
   // maximum number and will perduce all the possible combination. If the int
   // is less than 0, IllegalArgumentException will be thrown.
   // post: the method will output all the result on the screen.
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      } 
      
      LetterInventory inventory = new LetterInventory(s);
      print(inventory, max, new ArrayList<String>());
   }
   
   
   // public-private pair of the print method 
   // pre : takes a LetterInventory representing the current letters that are
   // left in the word, an integer max representing the maximum number of words
   // in the particular combination, and a list of string that saves the 
   // combination.
   private void print(LetterInventory inventory, int max, List<String> result){
      if (inventory.isEmpty()) {
         System.out.println(result);
      } else if(max == 0 || result.size() < max) {
         for (String word : dict) {
            if (word.length() <= inventory.size()) {
               LetterInventory tempInventory = inventory.subtract(map.get(word));
               if (tempInventory != null) {
                  result.add(word);
                  print(tempInventory, max, result);
                  result.remove(result.size() - 1);
               }
            }
         }
         
      }
   }
}
