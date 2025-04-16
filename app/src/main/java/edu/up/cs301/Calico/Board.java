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


                boolean addCat = false;
                for(int i = 0; i<3; i++)
                {
                    addCat = cats[i].addCat(similarPatches, pattern);
                }

                if (catExists || addCat) {
                    this.getPatch(patch[0], patch[1]).hasCat = true;
                }

                return catExists;

            }
        }
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
        int[][] sumPatches = new int[2][7 ];
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
        surrounding[2][0] = patch[0]+1;
        surrounding[2][1] = patch[1] - patch[0]%2;

        //Down right
        surrounding[3][0] = patch[0]+1;
        surrounding[3][1] = patch[1] - patch[0]%2 + 1;
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
