// Junqi Ye
// CSE 143 with Sam Lee
// HW6
// 

import java.util.*;

public class AnagramSolver {

   private Map<String, LetterInventory> map;
   
   private List<String> dict;
   
   // pre : takes a list of word that represent words in the dictionary.
   // post: creates an anagramsolver object. Save the list of word of 
   // dictionary. The original list will not be modified.
   public AnagramSolver(List<String> dict) {
      this();
      this.dict = dict;
      for(String word: dict){
         LetterInventory temp = new LetterInventory(word);
         map.put(word, temp);
      }
   }
   
   
   
   private AnagramSolver() {
      map = new HashMap<String, LetterInventory>();
   }
   
   
   
   
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      } 
      
      LetterInventory inventory = new LetterInventory(s);
      
      print(inventory, max, 0, new ArrayList<String>());
   }
   
   
   private void print(LetterInventory inventory, int max, int current, List<String> result){
      if ((inventory.size() == 0 && max == 0) || (current <= max && inventory.size() == 0)) {
      
         System.out.println(result);
         
      } else {
      
         for (String word : dict) {
            if (word.length() <= inventory.size()) {
               LetterInventory tempInventory = inventory.subtract(map.get(word));
               if (tempInventory != null) {
                  result.add(word);
                  print(tempInventory, max, current + 1, result);
                  result.remove(result.size() - 1);
               }
            }
         }
         
      }
   }
//    public static void main(String[] args){
//       List<String> list = new ArrayList<String>();
//       list.add("bee");
//       list.add("go");
//       list.add("gush");
//       list.add("shrug");
//       
//       AnagramSolver as = new AnagramSolver(list);
//    }

}