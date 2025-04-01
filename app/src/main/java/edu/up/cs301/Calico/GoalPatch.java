package edu.up.cs301.Calico;

import java.util.Arrays;

/**
 * @author Luca Sburlino
 * Goal Patch Class
 * Establishes the goal patches and calculates how many points they score at the end
 * of the game
 *
 * goal values table
 * 0 - no goal
 * 1 - !=
 * 2 - AAA BBB
 * 3 - AA BB CC
 * 4 - AAAA BB
 * 5 - AAA BB C
 * 6 - AA BB C D
 */
public class GoalPatch extends Patch
{
    //Instance Variables
     protected int goal;

     //creates empty goal patch
     public GoalPatch()
     {
         super();
         goal = 0;
     }

     //creates specific goal patch
     public GoalPatch(int _goal)
     {
         super();
         goal = _goal;
     }

     //gets the goal
     public int getGoal()
     {
         return goal;
     }

     //is the object not a goal?
     @Override
     public boolean isNotGoal(){return false;}

     //sets goal
     public void setGoal(int _goal)
     {
         goal = _goal;
     }

     //returns the number of points a specific goal patch has
     @Override
     public int getGoalPatchPoints(int[][] numEach)
     {
         //returns 0 if surrounded with empty patches (not completed board)
         if(numEach[0][0] == 0 || numEach[1][0] == 0) return 0;

         //keeps track of if patterns and numbers are completed
         int[] checkArray = new int[7];
         Arrays.fill(checkArray,0);
         boolean colorRequirement;
         boolean patternRequirement;
         switch(goal)
         {
             case 1:
                 Arrays.fill(checkArray,1);
                 checkArray[0] = 0;

                 colorRequirement = checkEquals(checkArray, numEach[0]);
                 patternRequirement = checkEquals(checkArray,numEach[1]);

                 if (colorRequirement && patternRequirement)
                     return 15; //num for both completed
                 else if (colorRequirement || patternRequirement)
                     return 10; // num for 1 completed
                 break;
             case 2:
                 //AAA BBB
                 checkArray[1] = 3;
                 checkArray[2] = 3;

                 //Gets if color or patch requirement was met
                 colorRequirement = checkEquals(checkArray, numEach[0]);
                 patternRequirement = checkEquals(checkArray,numEach[1]);

                 //returns correct points for specific patch
                 if (colorRequirement && patternRequirement)
                     return 13; //num for both completed
                 else if (colorRequirement || patternRequirement)
                     return 8; // num for 1 completed
                 break;
             case 3:
                 //AA BB CC
                 checkArray[1] = 2;
                 checkArray[2] = 2;
                 checkArray[3] = 2;

                 //Gets if color or patch requirement was met
                 colorRequirement = checkEquals(checkArray, numEach[0]);
                 patternRequirement = checkEquals(checkArray,numEach[1]);

                 //returns correct points for specific patch
                 if (colorRequirement && patternRequirement)
                     return 11; //num for both completed
                 else if (colorRequirement || patternRequirement)
                     return 7; // num for 1 completed
                 break;

             case 4:
                 //AAAA BB
                 checkArray[1] = 4;
                 checkArray[2] = 2;

                 //Gets if color or patch requirement was met
                 colorRequirement = checkEquals(checkArray, numEach[0]);
                 patternRequirement = checkEquals(checkArray,numEach[1]);

                 //returns correct points for specific patch
                 if (colorRequirement && patternRequirement)
                     return 14; //num for both completed
                 else if (colorRequirement || patternRequirement)
                     return 8; // num for 1 completed
                 break;
             case 5:
                 //AAA BB C
                 checkArray[1] = 3;
                 checkArray[2] = 3;

                 //Gets if color or patch requirement was met
                 colorRequirement = checkEquals(checkArray, numEach[0]);
                 patternRequirement = checkEquals(checkArray,numEach[1]);

                 //returns correct points for specific patch
                 if (colorRequirement && patternRequirement)
                     return 11; //num for both completed
                 else if (colorRequirement || patternRequirement)
                     return 7; // num for 1 completed
                 break;
             case 6:
                 //AA BB C D
                 checkArray[1] = 3;
                 checkArray[2] = 3;

                 //Gets if color or patch requirement was met
                 colorRequirement = checkEquals(checkArray, numEach[0]);
                 patternRequirement = checkEquals(checkArray,numEach[1]);

                 //returns correct points for specific patch
                 if (colorRequirement && patternRequirement)
                     return 13; //num for both completed
                 else if (colorRequirement || patternRequirement)
                     return 8; // num for 1 completed
                 break;
         }
         return 0; //when not completed
     }

    private boolean checkEquals(int[] a, int[] b)
    {
        if (a.length != b.length) return false;
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i<a.length; i++)
        {
            if (a[i] != b[i]) return false;
        }

        return true;
    }


}
