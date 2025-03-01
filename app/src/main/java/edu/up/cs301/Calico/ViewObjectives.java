package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class ViewObjectives extends GameAction
{
    //Instance Variables
    protected goalPatch objectivePatch;
    protected Board playerBoard;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ViewObjectives(GamePlayer player) {
        super(player);
    }
}
