package edu.up.cs301.Calico;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable
{
    public Patch[][] board = new Patch[7][7];

    public PlayerScore playerScore;

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

    public Patch getPatch(int row, int col)
    {
        return board[row][col];
    }//getPatch

    public void setPatch(Patch selectedPatch, int row, int col)
    {
        board[row][col] = selectedPatch;
    }

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
    public boolean getSimilarPatchesColor(ArrayList<int[]> similarPatches, int[] patch, int color)
    {
//        //gets patch to check
//        Patch patchToCheck = this.getPatch(patch[0], patch[1]);
//
//        //checks if patches are the correct color and not a goal patch
//        if (patchToCheck != null) {
//            if (patchToCheck.patchColor == color && patchToCheck.isNotGoal()) {
//
//                //checks if patches are already checked in the array
//                for (int i = 0; i < similarPatches.size(); i++)
//                    if (similarPatches.get(i) == patch) {
//                        return false;
//                    }
//
//                //add patch to arrayList
//                boolean buttonExists = false;
//                similarPatches.add(patch);
//
//                //check if patch already has button
//                if (patchToCheck.hasButton) return true;
//
//                //call on all ajacent patches
//                int[] checkPatch = {(patch[0] - 1), patch[1] - (patch[1] % 2)};
//                if (this.validPatchLocation(checkPatch))
//                    buttonExists = this.getSimilarPatchesColor(similarPatches, checkPatch, color);
//
//                checkPatch[1] = patch[1] - (patch[1] % 2) + 1;
//                if (this.validPatchLocation(checkPatch))
//                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);
//
//                checkPatch[0] = patch[0] + 1;
//                if (this.validPatchLocation(checkPatch))
//                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);
//
//                checkPatch[1] = patch[1] - (patch[1] % 2);
//                if (this.validPatchLocation(checkPatch))
//                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);
//
//                checkPatch[1] = patch[1] - 1;
//                checkPatch[0] = patch[0];
//                if (this.validPatchLocation(checkPatch))
//                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);
//
//                checkPatch[1] = patch[1] + 1;
//                if (this.validPatchLocation(checkPatch))
//                    buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);
//
//
//                //updates patch's button status
//                if (similarPatches.size() >= 3 || buttonExists) {
//                    patchToCheck.hasButton = true;
//                }
//
//                return buttonExists;
//
//            }
//        }
        return false;
    }

    public boolean getSimilarPatchesPattern(ArrayList<int[]> similarPatches, int[] patch, int pattern)
    {
//        //gets patch to check
//        Patch patchToCheck = this.getPatch(patch[0], patch[1]);
//
//        if (patchToCheck != null) {
//            if (patchToCheck.patchPattern == pattern && patchToCheck.isNotGoal()) {
//                for (int i = 0; i < similarPatches.size(); i++)
//                    if (similarPatches.get(i) == patch) {
//                        return false;
//                    }
//
//
//                //check if patch already has button
//                if (patchToCheck.hasCat) return true;
//
//                boolean catExists = false;
//
//                similarPatches.add(patch);
//                //down left
//                int[] checkPatch = {(patch[0] - 1), patch[1] - (patch[1] % 2)};
//                if (this.validPatchLocation(checkPatch))
//                    catExists = this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);
//
//                //down right
//                checkPatch[1]++;
//                if (this.validPatchLocation(checkPatch))
//                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);
//
//                //up right
//                checkPatch[0] += 2;
//                if (this.validPatchLocation(checkPatch))
//                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);
//
//                //up left
//                checkPatch[1]--;
//                if (this.validPatchLocation(checkPatch))
//                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);
//
//                //left
//                checkPatch[1] = patch[1] - 1;
//                checkPatch[0] = patch[0];
//                if (this.validPatchLocation(checkPatch))
//                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);
//
//                //right
//                checkPatch[1] = patch[1] + 1;
//                if (this.validPatchLocation(checkPatch))
//                    catExists = catExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);
//
//                if (similarPatches.size() >= 3 || catExists) {
//                    this.getPatch(patch[0], patch[1]).hasCat = true;
//                }
//
//                return catExists;
//
//            }
//        }
        return false;
    }

    public boolean validPatchLocation(int[] patch)
    {
        return patch[0] <= 6 && patch[0] >= 0 && patch[1] <= 6 && patch[1] >= 0;
    }

    public int getGoalPatchScore()
    {
        //check goal patch 1 [2,3]
        int[][] surroundingPatches = new int[6][2];//check [2,3] [3,2] [4,4]
        int[] patchToCheck = {2,3};
        int[][] sumPatches = new int[2][7];
        int points = 0;

        getSurrounding(surroundingPatches, patchToCheck);



        sumSurrounding(surroundingPatches, sumPatches);

        points = getPatch(2,3).getGoalPatchPoints(sumPatches);


        //Check goal patch 2 [3,2]
        patchToCheck[0] = 3;
        patchToCheck[1] = 2;

        getSurrounding(surroundingPatches, patchToCheck);

        sumSurrounding(surroundingPatches, sumPatches);

        points += getPatch(3,2).getGoalPatchPoints(sumPatches);


        //Check goal patch 3 [4,4]
        patchToCheck[0] = 4;
        patchToCheck[1] = 4;

        getSurrounding(surroundingPatches, patchToCheck);

        sumSurrounding(surroundingPatches, sumPatches);

        points += getPatch(4,4).getGoalPatchPoints(sumPatches);

        return points;
    }

    //getSurrounding
    //fills an integer array containing the coordinates of the 6 surrounding patches
    private void getSurrounding(int[][] surrounding, int[] patch)
    {
        //left
        surrounding[0][0] = patch[0];
        surrounding[0][1] = patch[1]-1;

        //right
        surrounding[1][0] = patch[0];
        surrounding[1][1] = patch[1]+1;

        //Up left
        surrounding[2][0] = patch[0]+1;
        surrounding[2][1] = patch[1] - patch[1]%2;

        //Up right
        surrounding[3][0] = patch[0]+1;
        surrounding[3][1] = patch[1] - patch[1]%2 +1;

        //Down left
        surrounding[2][0] = patch[0]-1;
        surrounding[2][1] = patch[1] - patch[1]%2;

        //Down right
        surrounding[3][0] = patch[0]-1;
        surrounding[3][1] = patch[1] - patch[1]%2 +1;
    }

    //SumSurrounding
    //Sums the number of colors and patterns that are in an an array;
    private void sumSurrounding(int[][] surrounding, int[][] sum)
    {
        for (int[] ints : surrounding) {
            Patch patchToCheck = getPatch(ints[0], ints[1]);

            //increases  value corresponding to patch color and pattern
            sum[0][patchToCheck.getPatchColor()]++;
            sum[1][patchToCheck.getPatchPattern()]++;
        }
    }
    public void setView()
    {
        //ToDo update XML to current board instance
    }
}//Board
