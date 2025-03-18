package edu.up.cs301.Calico;

import java.util.ArrayList;

public class Board
{
    public Patch[][] board = new Patch[6][6];

    public Board()
    {
        for (int i = 0; i<5; i ++)
        {
            for (int j = 0; j<5; j++)
            {
                board[i][j] = new Patch();
            }
        }
    }

    public Board(Board other)
    {
        for (int i = 0; i<5; i ++)
        {
            for (int j = 0; j<5; j++)
            {
                board[i][j] = new Patch(other.board[i][j]);
            }
        }
    }

    public Patch getPatch(int row, int col)
    {
        return board[row][col];
    }//getPatch

    public void setPatch(Patch selectedPatch, int row, int col)
    {
        board[row][col] = selectedPatch;
    }
    public boolean getSimilarPatchesColor(ArrayList<int[]> similarPatches, int[] patch, int color)
    {

        if(this.getPatch(patch[0], patch[1]).patchColor == color)
        {
            for(int i = 0; i<similarPatches.size(); i++)
                if (similarPatches.get(i) == patch)
                {
                    return false;
                }


            boolean buttonExists = false;

            similarPatches.add(patch);
            int[] checkPatch = {(patch[0]-1),patch[1]-(patch[1]%2)};
            if(this.validPatchLocation(checkPatch))
                buttonExists = this.getSimilarPatchesColor(similarPatches, checkPatch, color);

            checkPatch[1] = patch[1]-(patch[1]%2)+1;
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);

            checkPatch[0] = patch[0]+1;
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);

            checkPatch[1] = patch[1]-(patch[1]%2);
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);

            checkPatch[1] = patch[1]-1;
            checkPatch[0] = patch[0];
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);

            checkPatch[1] = patch[1]+1;
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesColor(similarPatches, checkPatch, color);

            if (similarPatches.size()>=3 || buttonExists)
            {
                this.getPatch(patch[0], patch[1]).hasButton = true;
            }

            return buttonExists;

        }
        return false;
    }

    public boolean getSimilarPatchesPattern(ArrayList<int[]> similarPatches, int[] patch, int pattern)
    {

        if(this.getPatch(patch[0], patch[1]).patchPattern == pattern)
        {
            for(int i = 0; i<similarPatches.size(); i++)
                if (similarPatches.get(i) == patch)
                {
                    return false;
                }


            boolean buttonExists = false;

            similarPatches.add(patch);
            int[] checkPatch = {(patch[0]-1),patch[1]-(patch[1]%2)};
            if(this.validPatchLocation(checkPatch))
                buttonExists = this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);

            checkPatch[1] = patch[1]-(patch[1]%2)+1;
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);

            checkPatch[0] = patch[0]+1;
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);

            checkPatch[1] = patch[1]-(patch[1]%2);
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);

            checkPatch[1] = patch[1]-1;
            checkPatch[0] = patch[0];
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);

            checkPatch[1] = patch[1]+1;
            if(this.validPatchLocation(checkPatch))
                buttonExists = buttonExists || this.getSimilarPatchesPattern(similarPatches, checkPatch, pattern);

            if (similarPatches.size()>=3 || buttonExists)
            {
                this.getPatch(patch[0], patch[1]).hasButton = true;
            }

            return buttonExists;

        }
        return false;
    }

    public boolean validPatchLocation(int[] patch)
    {
        return patch[0] <= 6 && patch[0] >= 0 && patch[1] <= 6 && patch[1] >= 0;
    }

    public void setView()
    {
        //ToDo update XML to current board instance
    }
}//Board
