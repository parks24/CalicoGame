package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class UndoMove extends GameAction
{

    public GameAction lastPlayedMove;


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
