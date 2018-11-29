// Junqi Ye
// CSE 143 With Sam Lee
// HW 7
// This program simulates a yes and no game. The client will have to think of 
// an object and the program will asks the client series of question to find
// the correct answer. If the program fails to find the corrent answer, the
// program will learn from the user and become better and better at guessing
// the answer.
import java.io.*;
import java.util.*;

public class QuestionTree {

    private Scanner console;

    private QuestionNode tree;
   
    // post: creates a QestionTree object. If this is the first time initializing
    // the object, the first object that the program will guess is "computer".

    public QuestionTree() {
        console = new Scanner(System.in);
        tree = new QuestionNode("computer");
    }


    // pre : takes a Scanner as input. The input should link to a file that
    // contains the information regarding the questions and answers. The file
    // should be legal and in a standard format.
    // post: read through the file and store the information. The information
    // will later be used by the program to guess client's object.
    public void read(Scanner input) {
        tree = read(input, tree);
    }
   
   
    // public-private pair
    // pre : takes a scanner input and a QuestionNode reference.
    // post: The method will read through the input file and construct a
    // QuestionNode tree.
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
   
   
    // pre : takes PrintStream obejct as the parameter. The PrintStream should
    // be open for writing.
    // post: The method prints out and stores the the questions and answers in
    // a file defined when creating the PrintStream object. Each individual
    // output will be on separed lines. Each question will have a "Q:" in before
    // it and each answer will have a "A:" on a separed line before it. The
    // newly added questions and answers will be updated to the original file.
    public void write(PrintStream output) {
       write(output, tree);
    }
   
   
   
    // pre : takes a PrintStream object and a QuestionNode node.
    // post: the method goes through the QuestionNode tree and print out the
    // questions and answers in pre-order. Each question and answer will have
    // occupy exactly one line and there is a "Q:" and "A:" in front of the
    // question and answer repectiverly. The "Q:" and "A:" will occupy one line
    // as well.
    private void write(PrintStream output, QuestionNode node) {
        if (node != null) {
            if (node.isAnswerNode()) {
                output.println("A:");
                output.println(node.data);
            } else {
                output.println("Q:");
                output.println(node.data);
                write(output, node.left);
                write(output, node.right);
            }
        }
    }
   

    // post: this method will askes the client questions about the object. The
    // client have to respond with "y" or "n" for the program to narrow down the
    // the object and correctly guess the object. If the program fails to guess
    // the object, the program will asks and store the object the client is
    // thinking and store describtion in a yes or no question. If it is the
    // first time guessing the object, the program will always guess "computer"
    public void askQuestions() {
        tree = askQuestions(tree);
    }

   
    // public-private pair
    // pre : takes a QuestionNode node.
    // post: the method will go through the QuestionNode tree and ask the client
    // questions on the tree. If the clients object is not on the tree, the
    // method will add the object and the yes-no describtion of the object into
    // the tree.
    private QuestionNode askQuestions(QuestionNode node) {
        if (node.isAnswerNode()) {
            if (yesTo("Would your object happen to be " + node.data + "?")) {
                System.out.println("Great, I got it right!");
            } else {
                System.out.print("What is the name of your object? ");
                String object = console.nextLine().trim();
                System.out.printf("Please give me a yes/no question " +
                                  "that%ndistinguishes between your " +
                                  "object%nand mine--> ");
                String question = console.nextLine().trim();
                if (yesTo("And what is the answer for your object?")) {
                    return new QuestionNode(question, new QuestionNode(object), node);
                } else {
                    return new QuestionNode(question, node, new QuestionNode(object));
                }
            }
        } else {
            if (yesTo(node.data)) {
                node.left = askQuestions(node.left);
            } else {
                node.right = askQuestions(node.right);
            }
        }
        return node;
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