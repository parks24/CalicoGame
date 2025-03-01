package edu.up.cs301.Calico;

public class Board
{
    public Patch[][] board = new Patch[5][5];

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

    public void setView()
    {
        //ToDo update XML to current board instance
    }
}//Board
