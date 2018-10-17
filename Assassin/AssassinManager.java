// Junqi Ye
// CSE 143 with Sam Lee
// HW3
// AssassinManager class manages the player playing the assassin game and keep 
// track of the player that are alive and dead

import java.util.*;

public class AssassinManager {             

   private AssassinNode playerAlive;// stores the front of the node that contains
                                    // the current alive players and their target
   
   private AssassinNode playerDead; // stores the front of the node of the people
                                    // that are dead.  
   
                           
   // pre : takes a List of Strings that stores the name of the players. Names
   // should be nonempty strings and should not have duplicate names. If the
   // list is empty, throw IllegalArgumentException.
   // post: construct the AssassinManagement object. Transfer and save the
   // player and the target they are trying to assassinate.
   public AssassinManager(List<String> names) {
      if (names.size() == 0) {
         throw new IllegalArgumentException();
      }
      
      for (int i = names.size() - 1; i >= 0 ; i--) {
         playerAlive = new AssassinNode(names.get(i), playerAlive);
      }
   }
   
   
   
   // post: output the names inside the killing ring. The format will be 
   // "'someone' is stalking 'someone else'" 
   // If there are only one player left, the method will output the player is 
   // stalking him/herself.
   public void printKillRing() {
      String firstPlayerName = playerAlive.name;
      AssassinNode current = playerAlive;
      while (current.next != null) {
         System.out.println("    " + current.name + " is stalking " + current.next.name); 
         current = current.next;
      }
      System.out.println("    " + current.name + " is stalking " + firstPlayerName);
   }
   
   
   
   // post: output the names inside the graveyard. The most recent kill will be
   // the first line of the output statement. The format for the output is 
   // "'someone' is killed by 'their killed'"
   public void printGraveyard() {
      AssassinNode current = playerDead; 
      while (current != null) {
         System.out.println("    " + current.name + " was killed by " + current.killer);
         current = current.next;
      }  
   }
   

   
   // pre : takes a string that represents one of the name of the player.
   // the method is not case sensitive.
   // post: returns true is the name is in the killing ring and false otherwise
   public boolean killRingContains(String name) {
      return containsInNodes(playerAlive, name);
   }
   
   
   
   // pre : takes a string that represents the name of the player. The method
   // is not case sensitive.
   // post: return true if the player is inside the graveyard (is dead).
   public boolean graveyardContains(String name){
      return containsInNodes(playerDead, name);
   }
   
   
   
   // post: return true if only one player is alive and false otherwise.
   public boolean gameOver() {
      return playerAlive.next == null;
   }
   
   
   
   // post: return the name of the winner. If the game has not finished yet, 
   // return null.
   public String winner() {
      if (gameOver()) {
         return playerAlive.name;
      } else {
         return null;
      }
   }
   
   
   
   // pre : takes the name of the player that will be killed. If the name is
   // not in the kill ring throws IllegalArgumentException. If the game is over
   // throw IllegalStateException. This method is case insensitive.
   // post: the name of the player will be removed from kill ring and moved
   // into the graveyard.
   public void kill(String name) {
      if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      } else if (gameOver()) {
         throw new IllegalStateException();
      }
      
      AssassinNode temp = null;
      if (playerAlive.name.equalsIgnoreCase(name)) {
         temp = playerAlive;
         playerAlive = playerAlive.next;
         AssassinNode lastPlayer = playerAlive;
         while (lastPlayer.next != null) {
            lastPlayer = lastPlayer.next;
         }
         temp.killer = lastPlayer.name;
         moveToGraveyard(temp);
      } else {
         AssassinNode current = playerAlive;
         while (!current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
         } 
         temp = current.next;
         current.next = temp.next;
         temp.killer = current.name;
         moveToGraveyard(temp);
      }      
   }
   
   
   
   // helper method 
   // pre : takes a player that has been killed.
   // post: move the player that was killed into the graveyard. 
   private void moveToGraveyard(AssassinNode temp) {
      if (temp == null) {
         temp.next = null;
         playerDead = temp;
      } else {
         temp.next = playerDead; 
         playerDead = temp;
      } 
   }
 
 
   
   // helpper method that reduces redundent codes
   // pre : takes a front variable to specify the front of the node and the
   // name of the player. This method is not case sensitive.
   // post: returns true if the name is in the node and false otherwise.
   private boolean containsInNodes(AssassinNode front, String name) {
      AssassinNode current = front;
      while (current != null) {
         if (current.name.equalsIgnoreCase(name)) {
            return true;
         }
         current = current.next;
      }
      return false;
   }
}