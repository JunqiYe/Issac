// Junqi Ye
// CSE 143 With Sam Lee
// HW 7
// This program simulates a tree node with two subbranches. This class is
// encapsulated within the QuestionTree class which mean the clients will not
// have access to this class. The node saves information regarding to the
// object. It can be either a question node that has a yes branch and a no
// branch, or a answer node with no branches.

public class QuestionNode {

    public String data;
   
    public QuestionNode left;
   
    public QuestionNode right;

    public QuestionNode() {
        this("computer", null, null);
    }

    public QuestionNode(String data) {
        this(data, null, null);
    }

    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public boolean isAnswerNode() {
        return (left == null) && (right == null);
    }
}