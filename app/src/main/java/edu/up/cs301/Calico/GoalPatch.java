package edu.up.cs301.Calico;

public class GoalPatch extends Patch
{
    //Instance Variables
     protected int goal;
     protected boolean goalPatch;

     public GoalPatch()
     {
         super();
         goal = 0;
     }

     public int getGoal()
     {
         return goal;
     }

     public void setGoal(int _goal)
     {
         goal = _goal;
     }

}
