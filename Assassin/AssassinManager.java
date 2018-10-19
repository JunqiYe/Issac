// Junqi Ye
// CSE 143 with Sam Lee
// HW3
// AssassinManager class manages the player playing the assassin game and keep 
// track of the player that are alive and dead

import java.util.*;

public class AssassinManager {             

   private AssassinNode frontAlivePlayer;// stores the front of the node that 
                                         //contains the current alive players
                                         // and their target
   
   private AssassinNode frontGraveyard; // stores the front of the node of the
                                        // people that are dead.  
   
                           
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
         frontAlivePlayer = new AssassinNode(names.get(i), frontAlivePlayer);
      }
   }
   
   
   
   // post: output the name of the player that are still alive and the person 
   // they are stalking. The format will be "'someone' is stalking 'someone 
   // else'". If there are only one player left, the method will output the 
   // player is stalking him/herself.
   public void printKillRing() { 
      AssassinNode current = frontAlivePlayer;
      while (current.next != null) {
         System.out.println("    " + current.name + " is stalking " + current.next.name); 
         current = current.next;
      }
      System.out.println("    " + current.name + " is stalking " + frontAlivePlayer.name);
   }
   
   
   
   // post: output the names of the player that have been killed. The most 
   // recent kill will be the first line of the output statement. The format 
   // for the output is "'someone' is killed by 'their killer'". If no one has
   // has been killed, the method produce no outputs.
   public void printGraveyard() {
      AssassinNode current = frontGraveyard; 
      while (current != null) {
         System.out.println("    " + current.name + " was killed by " + current.killer);
         current = current.next;
      }  
   }
   

   
   // pre : takes a string that represents one of the name of the player.
   // the method is not case sensitive.
   // post: returns true is the name is in the killing ring and false otherwise
   public boolean killRingContains(String name) {
      return containsInNodes(frontAlivePlayer, name);
   }
   
   
   
   // pre : takes a string that represents the name of the player. The method
   // is not case sensitive.
   // post: return true if the player is inside the graveyard (is dead). 
   public boolean graveyardContains(String name){
      return containsInNodes(frontGraveyard, name);
   }
   
   
   
   // post: return true if only one player is alive and false otherwise.
   public boolean gameOver() {
      return frontAlivePlayer.next == null;
   }
   
   
   
   // post: return the name of the winner. If the game has not finished yet, 
   // return null.
   public String winner() {
      if (gameOver()) {
         return frontAlivePlayer.name;
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
      
      AssassinNode deadPlayer = null;
      if (frontAlivePlayer.name.equalsIgnoreCase(name)) {
         deadPlayer = frontAlivePlayer;
         frontAlivePlayer = frontAlivePlayer.next;
         AssassinNode lastPlayer = frontAlivePlayer;
         while (lastPlayer.next != null) {
            lastPlayer = lastPlayer.next;
         }
         deadPlayer.killer = lastPlayer.name;
      } else {
         AssassinNode current = frontAlivePlayer;
         while (!current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
         } 
         deadPlayer = current.next;
         current.next = deadPlayer.next;
         deadPlayer.killer = current.name;
      }
      deadPlayer.next = frontGraveyard; 
      frontGraveyard = deadPlayer;;      
   }
 
   
   
   // helpper method that reduces redundent codes
   // pre : takes a front variable to specify which front of the node the 
   // method is modifying and the name of the player. This method is not case
   // sensitive.
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