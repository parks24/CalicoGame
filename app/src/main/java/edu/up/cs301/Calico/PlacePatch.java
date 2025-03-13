package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class PlacePatch extends GameAction
{
    //Instance Variables
    protected boolean objectivePatch;
    protected Patch selectedPatch;
    protected int boardRow;
    protected int boardCol;


    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlacePatch(GamePlayer player)
    {
        super(player);
    }

    public PlacePatch(GamePlayer player, int row, int col)
    {
        super(player);
        boardRow = row;
        boardCol = col;
    }

    public int getBoardRow()
    {
        return boardRow;
    }

    public int getBoardCol()
    {
        return boardCol;
    }

    public Patch getSelectedPatch()
    {
        return selectedPatch;
    }

    /** Places a patch onto a player board
     *
     *
     * @param board Player Board
     * @param row Row of Board to place patch
     * @param col Column of board to place patch
     * @param patch Patch from player inventory to place on board
     */
    public void placePatch(Patch[][] board, int row, int col, Patch patch)
    {
        board[row][col] = patch;
    }//placePatch



}//PlacePatch
