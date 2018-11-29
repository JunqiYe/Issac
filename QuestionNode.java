// Junqi Ye
// CSE 143 With Sam Lee
// HW 7
// This program simulates a tree node with two subbranches. This class is
// encapsulated within the QuestionTree class which mean the clients will not
// have access to this class. The node saves information regarding to the
// object. It can be either a question node that has a yes branch and a no
// branch, or a answer node with no branches.

public class QuestionNode {

    public String data; // stores the information. It can be the actual
                        // object or the yes/no question

    public QuestionNode left; // left branch of the node which represents
                              // the yes path for the question. If the node is
                              // the actual object, this is null.
   
    public QuestionNode right; // left branch of the node which represents
                               // the no path for the question. If the node is
                               // the actual object, this is null.


    // pre : takes a string data representing the value to put into the node.
    // The String should either be an object for client to guess, or a yes/no
    // question for client to answer.
    // post: creates a single QuestionNode object that contains the string value
    // with no subbranches. If the node is a question node, there should
    // always be a yes and and no branch that are not null. If the node is a
    // answer node, there should always be two null branches.
    public QuestionNode(String data) {
        this(data, null, null);
    }


    // pre : takes a String data representing the value to put into the node.
    // The String data should be an object fo rhte client to guess, or a
    // yes/no question for client to answer. Also takes a QuestionNode left
    // and a QuestionNode right for the two subbranches. The left branch
    // means the question is answered with yes and the right branch means the
    // question is answered with no.
    // post: creates a single QuestionNode object that contains the String
    // data, the yes branch of the question, and the no branch of the
    // question. If the node is a question node, there should always be a yes
    // and and no branch that are not null. If the node is a answer node, there
    // should always be two null branches.
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }


    // post: the method returns true if the node is a answer node and false
    // otherwise.
    public boolean isAnswerNode() {
        return (left == null) && (right == null);
    }
}