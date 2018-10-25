// Junqi Ye
// CSE 143 AM with Sam Lee
// HW4 Evil Hangman

import java.util.*;

public class HangmanManager {

   private Set<String> wordSet;
   
   private Set<Character> guessedChar;
   
   private int guesses;
   
   private int wrongGuesses;
   
   private Map<String, Set<String>> wordFamily;
   
   private int length;
   
   private String pattern;
   

   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      this.length = length;
      guesses = max;
      wrongGuesses = max;
      wordSet = new TreeSet<String>();
      guessedChar = new TreeSet<Character>();
      for (String word: dictionary) {
         if (word.length() == length) {
            wordSet.add(word);
         }
      }
      
      pattern = "-";
      for (int i = 0; i < length - 1; i++) {
         pattern += " -";
      }
   }   
   
   
   
   public Set<String> words() {
      return wordSet;
   }
   
   
   
   public int guessesLeft() {
      return wrongGuesses;
   }
   
   
   
   public Set<Character> guesses() {
      return guessedChar;
   }
   
   
   
   public String pattern() {
      if (wordSet.isEmpty()) {
         throw new IllegalStateException();
      }
      
      return pattern;
   }
   
   
 
 
   
//    public int record(char guess) {
//       if (guesses < 1 || wordSet.isEmpty()) {
//          throw new IllegalStateException();
//       } else if (!wordSet.isEmpty() && guessedChar.contains(guess)) {
//          throw new IllegalArgumentException();
//       }
//       
//       guessedChar.add(guess);
//       wordFamily = new TreeMap<String, Set<String>>();
//       for (String word: wordSet) {
//          String temp = "";
//          for (int i = 0; i < word.length(); i++) {
//             if (word.substring(i, i + 1).equals(guess + "")) {
//                temp += word.substring(i, i + 1);
//                if (i != word.length() - 1) {
//                   temp += " ";
//                }
//             } else if(i != word.length() - 1){
//                temp += "- ";
//             } else {
//                temp += "-";
//             }
//          }
//          if (!wordFamily.containsKey(temp)) {
//             wordFamily.put(temp, new TreeSet<String>());
//          }
//          wordFamily.get(temp).add(word);        
//       }
//       
//       
//       String maxlength = "";
//       int maxlen = 0;
//       for (String key: wordFamily.keySet()) {
//          if (wordFamily.get(key).size() > maxlen) {
//             maxlen = wordFamily.get(key).size();
//             maxlength = key;
//          }
//       }
//       
//       int count = 0;
//       for (int i = 0; i < maxlength.length(); i++) {
//          if (maxlength.substring(i, i + 1).equals(guess + "")){
//             count++;
//          }
//       }
//       
//       if (count == 0) {
//          wrongGuesses--;
//       }
//       
//       wordSet.clear();
//       for (String word: wordFamily.get(maxlength)) {
//          wordSet.add(word);   
//       }
//       System.out.println(wordFamily);
//       return count;
//    }


   public int record(char guess) {
      if (guesses < 1 || wordSet.isEmpty()) {
         throw new IllegalStateException();
      } else if (!wordSet.isEmpty() && guessedChar.contains(guess)) {
         throw new IllegalArgumentException();
      }
      
      int count = 0;
      guessedChar.add(guess);
      
      createMap(guess);
      
      int max = 0;
      String patternKey = "";
      for (String key: wordFamily.keySet()) {
         if (wordFamily.get(key).size() > 0) {
            max = wordFamily.get(key).size();
            patternKey = key;
         }
      }
      
      wordSet = wordFamily.get(patternKey);
      
      for (int i = 0; i < patternKey.length(); i++) {
         if (patternKey.substring(i, i + 1).equals(pattern.substring(i, i + 1)) || 
            patternKey.substring(i, i + 1).equals("-")) {
            pattern = pattern.substring(0, i) + patternKey.substring(i, i + 1) + pattern.substring(i + 1);
            count++;
         }
      }
      
      return count;
   }
   
   
   private void createMap(char guess) {
      wordFamily = new TreeMap<String, Set<String>>();
      for (String word: wordSet) {
         String temp = "";
         for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i + 1).equals(guess + "")) {
               temp += word.substring(i, i + 1);
               if (i != word.length() - 1) {
                  temp += " ";
               }
            } else if(i != word.length() - 1){
               temp += "- ";
            } else {
               temp += "-";
            }
         }
         if (!wordFamily.containsKey(temp)) {
            wordFamily.put(temp, new TreeSet<String>());
         }
         wordFamily.get(temp).add(word);        
      }
   }
   
   
   
   
   
   
   
   public static void main(String[] args){
   
      List<String> str = new ArrayList<String>();
      str.add("ally");
      str.add("beta");
      str.add("cool");
      str.add("deal");
      str.add("else");
      str.add("flew");
      str.add("good");
      str.add("hope");
      str.add("ibex");

      HangmanManager hg = new HangmanManager(str, 4,6);
      hg.record('e');
      hg.record('g');


   }
}