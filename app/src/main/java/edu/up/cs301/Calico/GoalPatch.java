package edu.up.cs301.Calico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Luca Sburlino
 * @version 2025
 * Goal Patch Class
 * Establishes the goal patches and calculates how many points they score at the end
 * of the game

 * goal values table
 * 0 - no goal
 * 1 - !=
 * 2 - AAA BBB
 * 3 - AA BB CC
 * 4 - AAAA BB
 * 5 - AAA BB C
 * 6 - AA BB C D
 */
public class GoalPatch extends Patch implements Serializable
{
    //Instance Variables
    protected static final long serialVersionUID = 170425;

    /**
     * Default Constructor
     * creates empty goal patch
     */
    public GoalPatch()
     {
         patchColor = 0;
         patchPattern = 7;
         goal = 0;
     }

    /**
     * Constructs a GoalPatch with a specific goal type.
     *
     * @param _goal The int representing the goal condition (1-6).
     */
     public GoalPatch(int _goal)
     {
         patchColor = 0;
         patchPattern = 7;
         goal = _goal;
     }

    /**
     * Indicates that this patch is a goal patch.
     *
     * @return false
     */
     @Override
     public boolean isNotGoal(){return false;}

    /**
     * Evaluates the surrounding patches based on the current goal type and
     * returns the number of points this goal patch scores.
     *
     * @param numEach A 2D array where:
     *                - index [0] contains color counts
     *                - index [1] contains pattern counts
     * @return Points scored based on if surrounding patches match the goal.
     */
     @Override
     public int getGoalPatchPoints(int[][] numEach)
     {

         //returns 0 if surrounded with empty patches (not completed board)
         if(numEach[0][0] != 0 || numEach[1][0] != 0) return 0;

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
                 checkArray[2] = 2;
                 checkArray[3] = 1;

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
                 checkArray[1] = 2;
                 checkArray[2] = 2;
                 checkArray[3] = 1;
                 checkArray[4] = 1;

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


    /**
     * Helper method to compare two arrays for equality after sorting.
     *
     * @param a First array to compare.
     * @param b Second array to compare.
     * @return true if arrays have the same elements in the same counts, false otherwise.
     */
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

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */