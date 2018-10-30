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
         if (this.grammar.containsKey(grammarArr[0])) {
            throw new IllegalArgumentException();
         } else {
            this.grammar.put(grammarArr[0],grammarArr[1]);
         }
      }
   }
   
   
//    public boolean grammarContains(String symbol) {
//       return true;
//    }
//    
//    
//    public String[] generate(String symbol, int times) {
//       return null;
//    }
//    
//    public String getSymbols() {
//       return "";
//    }

}