package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class ViewObjectives extends GameAction implements Serializable
{
    //Instance Variables
    protected GoalPatch objectivePatch;
    protected static final long serialVersionUID = 170425;
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
