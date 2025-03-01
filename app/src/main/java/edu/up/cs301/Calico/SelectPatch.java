package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class SelectPatch extends GameAction
{

    //Instance Variables
    protected Patch selectedPatch;
    protected int xTouch;
    protected int yTouch;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectPatch(GamePlayer player)
    {
        super(player);
    }

    public Patch getSelectedPatch()
    {
        return selectedPatch;
    }

}//SelectPatch
