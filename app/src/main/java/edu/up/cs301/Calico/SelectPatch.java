package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class SelectPatch extends GameAction
{

    //Instance Variables
    protected Patch selectedPatch;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectPatch(GamePlayer player)
    {
        super(player);
    }

    public SelectPatch(GamePlayer player, Patch patch)
    {
        super(player);
        selectedPatch = patch;
    }

    public Patch getSelectedPatch()
    {
        return selectedPatch;
    }

}//SelectPatch
