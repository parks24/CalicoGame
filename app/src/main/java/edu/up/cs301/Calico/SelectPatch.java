package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class SelectPatch extends GameAction implements Serializable
{

    //Instance Variables
    protected Patch selectedPatch;
    protected static final long serialVersionUID = 170425;
    protected int selectedSlot;

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

    public SelectPatch(GamePlayer player, int slot)
    {
        super(player);
        selectedSlot = slot;
    }

    public Patch getSelectedPatch()
    {
        return selectedPatch;
    }

    public int getSelectedSlot()
    {
        return selectedSlot;
    }

}//SelectPatch
