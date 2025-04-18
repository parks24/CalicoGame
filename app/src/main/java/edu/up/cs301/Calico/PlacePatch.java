package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class PlacePatch extends GameAction implements Serializable
{
    //Instance Variables
    protected static final long serialVersionUID = 170425;
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





}//PlacePatch
