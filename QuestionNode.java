// Junqi Ye
// CSE 143 With Sam Lee
// HW 7
// 

public class QuestionNode {

   public String data;
   
   public QuestionNode left;
   
   public QuestionNode right;

   public QuestionNode() {
      data = "computer";
   }
   
   public QuestionNode(String data) {
      this.data = data;
   }
   
   public QuestionNode(String data, QuestionNode left, QuestionNode right) {
      this(data);
      this.left = left;
      this.right = right;
   }
   
   public boolean isAnswerNode() {
      return (left == null) && (right == null);
   }
}