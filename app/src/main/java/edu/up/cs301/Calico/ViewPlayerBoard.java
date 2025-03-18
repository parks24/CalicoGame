package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class ViewPlayerBoard extends GameAction
{
    //Instance Variables
    protected int boardToShow;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ViewPlayerBoard(GamePlayer player)
    {
        super(player);
    }

    public ViewPlayerBoard(GamePlayer player, int playerToShow){
        super(player);
        boardToShow = playerToShow;
    }

    public int getBoardToShow() {
        return boardToShow;
    }
}
