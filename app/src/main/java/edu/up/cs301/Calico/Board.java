package edu.up.cs301.Calico;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The {@code Board} class represents a 7x7 game board in Calico.
 * It handles patch placement, board evaluation, and goal scoring logic.
 * The board tracks the patches, validates locations, and evaluates game state completeness.
 * @author Luca Sburlino
 * @version 2025
 */
public class Board implements Serializable
{
    /** 7x7 grid representing the game board */
    public Patch[][] board = new Patch[7][7];

    /** The player's score tracker */
    public PlayerScore playerScore;

    protected static final long serialVersionUID = 170425;

    /**
     * Default constructor.
     * Initializes a 7x7 board of empty patches and a new player score.
     */
    public Board()
    {
        for (int i = 0; i<7; i ++)
        {
            for (int j = 0; j<7; j++)
            {
                board[i][j] = new Patch();
            }
        }

        playerScore = new PlayerScore();
    }

    /**
     * Copy constructor.
     * Creates a deep copy of another {@code Board} object.
     *
     * @param other the board to copy
     */
    public Board(Board other)
    {
        for (int i = 0; i<7; i ++)
        {
            for (int j = 0; j<7; j++)
            {
                board[i][j] = new Patch(other.board[i][j]);
            }
        }

        playerScore = new PlayerScore(other.playerScore);
    }
    /**
     * Returns the patch at the specified row and column.
     *
     * @param row the row index
     * @param col the column index
     * @return the patch at the specified location
     */
    public Patch getPatch(int row, int col)
    {
        return board[row][col];
    }//getPatch


    /**
     * Sets the patch at the specified row and column.
     *
     * @param selectedPatch the patch to place
     * @param row the row index
     * @param col the column index
     */
    public void setPatch(Patch selectedPatch, int row, int col)
    {
        board[row][col] = selectedPatch;
    }

    /**
     * Checks whether the board is fully filled (no empty patches).
     *
     * @return true if all patches are filled, false otherwise
     */
    public boolean isComplete()
    {

        //Scan board for empty patches
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col++)
            {

                if(board[row][col] != null)
                {
                    if (board[row][col].getPatchColor() == 0 && board[row][col].getPatchPattern() == 0)
                    {
                        return false; //Empty Patch Found
                    }
                }

            }//col iteration
        }//row iteration

        return true; //Board Filled
    }//isComplete

    /**
     * Recursively finds and checks adjacent patches of the same color.
     * Returns true if button for that clump of patches already exists
     *
     * @param similarPatches list to store found patches
     * @param patch coordinates of the patch to start with
     * @param color color to match
     * @return true if a button exists in the group or is added, false otherwise
     */
    public boolean getSimilarPatchesColor(ArrayList<int[]> similarPatches, int[] patch, int color)
    {
        //gets patch to check
        Patch patchToCheck = this.getPatch(patch[0], patch[1]);

        //checks if patches are the correct color and not a goal patch
        if (patchToCheck != null) {
            if (patchToCheck.patchColor == color && patchToCheck.isNotGoal()) {

                //checks if patches are already checked in the array
                for (int i = 0; i < similarPatches.size(); i++)
                    if (similarPatches.get(i)[0] == patch[0] && similarPatches.get(i)[1]==patch[1]) {
                        return false;
                    }

                //add patch to arrayList
                boolean buttonExists = false;
                similarPatches.add(patch);

                //check if patch already has button
                if (patchToCheck.hasButton) return true;

                //call on all ajacent patches
                int[] checkPatchUL = {(patch[0] - 1), patch[1] - (patch[0] % 2)};
                if (this.validPatchLocation(checkPatchUL))
                    buttonExists = this.getSimilarPatchesColor(similarPatches, checkPatchUL, color);

                int[] checkPatchUR = {(patch[0] - 1), patch[1] - (patch[0] % 2)+1};
                if (this.validPatchLocation(checkPatchUR))
                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatchUR, color);

                int[] checkPatchDL = {(patch[0] + 1), patch[1] - (patch[0] % 2)};
                if (this.validPatchLocation(checkPatchDL))
                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatchDL, color);

                int[] checkPatchDR = {(patch[0] + 1), patch[1] - (patch[0] % 2) + 1};
                if (this.validPatchLocation(checkPatchDR))
                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatchDR, color);

                int[] checkPatchL = {patch[0], patch[1] - 1};
                if (this.validPatchLocation(checkPatchL))
                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatchL, color);

                int[] checkPatchR = {patch[0], patch[1] + 1};
                if (this.validPatchLocation(checkPatchR))
                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatchR, color);


                //updates patch's button status
                if (similarPatches.size() >= 3 || buttonExists) {
                    patchToCheck.hasButton = true;
                }

                return buttonExists;
            }
        }
        return false;
    }


    /**
     * Recursively finds and checks adjacent patches of the same pattern.
     * Returns true if cat already exists on patches
     *
     * @param similarPatches list to store found patches
     * @param patch coordinates of the patch to start with
     * @param pattern pattern to match
     * @param cats array of cat objectives
     * @return true if a cat is awarded or already exists, false otherwise
     */
    public boolean getSimilarPatchesPattern(ArrayList<int[]> similarPatches, int[] patch, int pattern, Cat[] cats)
    {
        //gets patch to check
        Patch patchToCheck = this.getPatch(patch[0], patch[1]);

        if (patchToCheck != null) {
            if (patchToCheck.patchPattern == pattern && patchToCheck.isNotGoal()) {
                for (int i = 0; i < similarPatches.size(); i++)
                    if (similarPatches.get(i)[0] == patch[0] && similarPatches.get(i)[1] == patch[1]) {
                        return false;
                    }


                //check if patch already has button
                if (patchToCheck.hasCat) return true;

                boolean catExists = false;

                similarPatches.add(patch);
                //down left
                int[] checkPatchUL = {(patch[0] - 1), patch[1] - (patch[0] % 2)};
                if (this.validPatchLocation(checkPatchUL))
                    catExists = this.getSimilarPatchesPattern(similarPatches, checkPatchUL, pattern,cats);

                //down right
                int[] checkPatchUR = {(patch[0] - 1), patch[1] - (patch[0] % 2)+1};
                if (this.validPatchLocation(checkPatchUR))
                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatchUR, pattern,cats);

                //up right
                int[] checkPatchDL = {(patch[0] + 1), patch[1] - (patch[0] % 2)};
                if (this.validPatchLocation(checkPatchDL))
                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatchDL, pattern,cats);

                //up left
                int[] checkPatchDR = {(patch[0] + 1), patch[1] - (patch[0] % 2) + 1};
                if (this.validPatchLocation(checkPatchDR))
                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatchDR, pattern,cats);

                //left
                int[] checkPatchL = {patch[0], patch[1] -1};
                if (this.validPatchLocation(checkPatchL))
                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatchL, pattern, cats);

                //right
                int[] checkPatchR = {patch[0], patch[1]+1};
                if (this.validPatchLocation(checkPatchR))
                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatchR, pattern, cats);


                //addCat = true if conditions are met
                boolean addCat = false;
                for(int i = 0; i<3; i++)
                {
                    addCat = addCat || cats[i].addCat(similarPatches, pattern);
                }

                //marks cats hasCat to true if conditions are met
                if (catExists || addCat) {
                    this.getPatch(patch[0], patch[1]).hasCat = true;
                }

                return catExists;

            }
        }
        return false;
    }

    /**
     * Validates whether the patch coordinates are within board bounds.
     *
     * @param patch array of [row, col]
     * @return true if coordinates are on the board, false otherwise
     */
    public boolean validPatchLocation(int[] patch)
    {
        return patch[0] <= 6 && patch[0] >= 0 && patch[1] <= 6 && patch[1] >= 0;
    }

    /**
     * Calculates the total score from all three goal patches on the board.
     * of its surrounding patches. These patches are located at [2,3], [3,2], and [4,4].
     *
     * @return the combined score from all three goal patches
     */
    public int getGoalPatchScore()
    {
        //check goal patch 1 [2,3]
        int[][] surroundingPatches = new int[6][2];//check [2,3] [3,2] [4,4]
        int[] patchToCheck = {2,3};
        int[][] sumPatches = new int[2][7];
        int points = 0;

        getSurrounding(surroundingPatches, patchToCheck);
        sumSurrounding(surroundingPatches, sumPatches);

        Patch goalLocation = getPatch(2,3);
        GoalPatch patchOne = new GoalPatch(goalLocation.getGoal());

        points = patchOne.getGoalPatchPoints(sumPatches);
        resetSumPatch(sumPatches);



        //Check goal patch 2 [3,2]
        patchToCheck[0] = 3;
        patchToCheck[1] = 2;

        getSurrounding(surroundingPatches, patchToCheck);
        sumSurrounding(surroundingPatches, sumPatches);

        goalLocation = getPatch(3,2);
        GoalPatch patchTwo = new GoalPatch(goalLocation.getGoal());

        points += patchTwo.getGoalPatchPoints(sumPatches);
        resetSumPatch(sumPatches);



        //Check goal patch 3 [4,4]
        patchToCheck[0] = 4;
        patchToCheck[1] = 4;

        getSurrounding(surroundingPatches, patchToCheck);
        sumSurrounding(surroundingPatches, sumPatches);

        goalLocation = getPatch(4,4);
        GoalPatch patchThree = new GoalPatch(goalLocation.getGoal());

        points += patchThree.getGoalPatchPoints(sumPatches);
        resetSumPatch(sumPatches);

        return points;
    }

    /**
     * Resets all values in the provided 2D sum array to zero.
     * Used to clear color and pattern tallies between goal patch calculations.
     *
     * @param sumPatch a 2D integer array representing counts of colors and patterns
     */
    public void resetSumPatch(int[][] sumPatch)
    {
        for(int i = 0; i < sumPatch.length; i++)
        {
            for(int j = 0; j < sumPatch[i].length; j++)
            {
                sumPatch[i][j] = 0;
            }
        }
    }

    /**
     * Fills the provided 2D array with coordinates of the 6 adjacent patches
     *
     * @param surrounding a 2D array to be populated with surrounding patch coordinates
     * @param patch an array of [row, col] representing the patch of interest
     */
    private void getSurrounding(int[][] surrounding, int[] patch)
    {

        //NOTES FOR DEBUGGING PURPOSES
        /* Y,X format
         * Board 2D array does not account for shift in GUI
         * %2 for even row returns 0, odd returns 1
         */


        //left
        surrounding[0][0] = patch[0];
        surrounding[0][1] = patch[1]-1;

        //right
        surrounding[1][0] = patch[0];
        surrounding[1][1] = patch[1]+1;

        //Up left
        surrounding[2][0] = patch[0]-1;
        surrounding[2][1] = patch[1] - patch[0]%2;

        //Up right
        surrounding[3][0] = patch[0]-1;
        surrounding[3][1] = patch[1] - patch[0]%2 + 1;

        //Down left
        surrounding[4][0] = patch[0]+1;
        surrounding[4][1] = patch[1] - patch[0]%2;

        //Down right
        surrounding[5][0] = patch[0]+1;
        surrounding[5][1] = patch[1] - patch[0]%2 + 1;
    }

    /**
     * Tallies the number of each color and pattern type from a set of surrounding patches.
     * Increments corresponding indices in the sum array based on patch color and pattern.
     *
     * @param surrounding a 2D array of coordinates representing neighboring patches
     * @param sum a 2D array where sum[0][color] and sum[1][pattern] are incremented
     */
    private void sumSurrounding(int[][] surrounding, int[][] sum)
    {
        for (int[] ints : surrounding) {
            Patch patchToCheck = getPatch(ints[0], ints[1]);

            //increases  value corresponding to patch color and pattern
            sum[0][patchToCheck.getPatchColor()]++;
            sum[1][patchToCheck.getPatchPattern()]++;
        }
    }

}//Board

/**
 External Citation
     Date: 23 April 2025
     Problem: Javadocs
     Resource: https://chatgpt.com
     Solution: Used chatGPT to generate Javadocs
 */

