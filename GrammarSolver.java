// Issac Ye
// CSE 143 with Sam Lee
// HW5
// This programme takes a prewritten grammar and generate sentences with the
// given grammar.

import java.util.*;

public class GrammarSolver{
   
   private SortedMap<String, String> grammar;
   
   
   
   // pre : takes a list of strings representing the grammars. If the list is 
   // empty or the list contain two of the same grammar, throw 
   // IllegalArgumentException.
   // post: store the given grammar and create GrammarSolver object.
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
            this.grammar.put(grammarArr[0], grammarArr[1]); //might need trim() here
         }
      }
   }
   
   
   
   // pre : take a string, test if the grammar contains the string.
   // post: return true if the string is in the grammar, false otherwise.
   public boolean grammarContains(String symbol) { 
      return grammar.containsKey(symbol);
   }

   
   
   // pre : takes a string representing the grammar and an int representing the
   // number of sentences to generate. If the grammar does not contain the 
   // string or the int is less than 0, throw IllegalArgumentException.
   // post: using the string passes through the parameter as grammar, generate 
   // int number of senteces and return the strings in an string array.
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
   
   
   
   // public-provate pair for recursion methods. 
   // pre : takes a string representing the grammmar symbol.
   // post: return the word that is in the grammar list.
   private String generate(String symbol) {
      String sentence = "";
      String[] nonTerminals = grammar.get(symbol).split("\\s*\\|\\s*");   
      String[] individual = nonTerminals[(int)(Math.random() * nonTerminals.length)].split("\\s+");
      
      for (String str :individual) {
         str.trim();
      }
      
      for (int i = 0; i < individual.length; i++) {
         if (grammarContains(individual[i])) {
            sentence += generate(individual[i]);
         } else {
            sentence += individual[i] + " ";
         }
      }
      return sentence;
   }



   // post: return a string that contains all the nonterminal grammars. 
   // Enclosed in [ and ] separaded by commas.
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