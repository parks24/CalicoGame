package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class UndoMove extends GameAction implements Serializable
{
    //Instance Variables
    public GameAction lastPlayedMove;
    protected static final long serialVersionUID = 170425;


    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UndoMove(GamePlayer player)
    {
        super(player);
    }

    public GameAction getLastPlayedMove()
    {
        return lastPlayedMove;
    }

    public void setLastPlayedMove(GameAction move)
    {
        lastPlayedMove = move;
    }
}
