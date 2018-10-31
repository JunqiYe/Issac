// Issac Ye
// CSE 143 with Sam Lee
// HW5
//

import java.util.*;

public class GrammarSolver{
   
   private SortedMap<String, String> grammar;
   
   public GrammarSolver(List<String> grammar) {
   
      if (grammar.size() == 0 ) {
         throw new IllegalArgumentException();
      }
   
      this.grammar = new TreeMap<String, String>();
      
      for (String string: grammar) {
         String[] grammarArr = string.split("::=");
         if (this.grammarContains(grammarArr[0])) {
            throw new IllegalArgumentException();
         } else {
            this.grammar.put(grammarArr[0], grammarArr[1]);
         }
      }
   }
   
   
   public boolean grammarContains(String symbol) { 
      return grammar.containsKey(symbol);
   }

   
   
   public String[] generate(String symbol, int times) {
      if (!grammarContains(symbol) || times < 0) {
         throw new IllegalArgumentException();
      }

      String[] sentence = new String[times];
      for (int i = 0; i < times; i++) {
         sentence[i] = generate(symbol);
      }
      return sentence;
   }
   
   
   
   private String generate(String symbol) {
      String sentence = "";
      String[] terminals = grammar.get(symbol).split("\\|");   
      String[] individual = terminals[(int)(Math.random() * terminals.length)].split("\\s+");
      
      for (int i = 0; i < individual.length; i++) {
         if (grammarContains(individual[i])) {
            sentence += generate(individual[i]);
         } else {
            sentence += individual[i] + " ";
         }
      }
      return sentence;
   }


   
   public String getSymbols() {
      String symbols = "[";
      for (String key: grammar.keySet()) {
         symbols += key;
         symbols += ",";
      }
      symbols = symbols.substring(0,symbols.length() - 1) + "]";
      return symbols; 
   }
   
//    
//    public static void main(String[] args) {
//       String str =" E::=    T     |		E		OP     T    ";
//       String[] strs = str.split("::=");
//       for(String s: strs){
//          System.out.println(s);
//       }
//    }


}