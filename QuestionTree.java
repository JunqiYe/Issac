// Junqi Ye
// CSE 143 With Sam Lee
// HW 7
// 

import java.io.*;
import java.util.*;

public class QuestionTree {
   
   private Scanner console;
   
   private QuestionNode tree;
   
   
   public QuestionTree() {
      console = new Scanner(System.in);
      tree = new QuestionNode();
   }
   
   
   
   public void read(Scanner input) {
      tree = read(input, tree);
   }
   
   
   
   private QuestionNode read(Scanner input, QuestionNode node) {
      QuestionNode tempNode = node;
      if (input.hasNext()) {
         String temp = input.nextLine();
         if (temp.equals("Q:")) {
            tempNode = new QuestionNode(input.nextLine());
            tempNode.left = read(input, tempNode.left);
            tempNode.right = read(input, tempNode.right);
         } else {
            tempNode = new QuestionNode(input.nextLine());
         }
      }
      return tempNode;
   }
   
   
   
   public void write(PrintStream output) {
      write(output, tree);
   }
   
   
   
   
   public void write(PrintStream output, QuestionNode node) {
      if (node != null)
         if(node.isAnswerNode()) {
            output.println("A:");
            output.println(node.data);
         } else {
            output.println("Q:");
            output.println(node.data);
            write(output, node.left);
            write(output, node.right);
         }
   }
   
   
   
   public void askQuestions() {
      tree = askQuestions(tree);
   }
   
   
   
   
   private QuestionNode askQuestions(QuestionNode node){
      QuestionNode temp = null;
      if (node.isAnswerNode()) {
         if (yesTo("Would your object happen to be " + node.data + "?")) {
            System.out.println("Great, I got it right!");
            return node;
         } else {
            System.out.print("What is the name of your object? ");
            String object = console.nextLine().trim();
            System.out.print("Please give me a yes/no question that\ndistinguishes" +
                             "between your object\nand mine--> ");
            String question = console.nextLine().trim();
            if (yesTo("And what is the answer for your object?")) {
               temp = new QuestionNode(question, new QuestionNode(object), node);
            } else {
               temp = new QuestionNode(question, node, new QuestionNode(object));
            }
         }

      } else {
         if (yesTo(node.data)) {
            node.left = askQuestions(node.left);
         } else {
            node.right = askQuestions(node.right);
         }
         return node;
      }
      
      return temp;
   }
   
   
   
   
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    public boolean yesTo(String prompt) {
       System.out.print(prompt + " (y/n)? ");
       String response = console.nextLine().trim().toLowerCase();
       while (!response.equals("y") && !response.equals("n")) {
           System.out.println("Please answer y or n.");
           System.out.print(prompt + " (y/n)? ");
           response = console.nextLine().trim().toLowerCase();
       }
       return response.equals("y");
   }


}