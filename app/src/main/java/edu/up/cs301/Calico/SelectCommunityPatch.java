package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class SelectCommunityPatch extends GameAction
{

    //Instance Variables
    protected Patch selectedPatch;
    protected int selectedSlot;

    public SelectCommunityPatch(GamePlayer player)
    {
        super(player);
    }

    public SelectCommunityPatch(GamePlayer player, Patch patch)
    {
        super(player);
        selectedPatch = patch;
    }

    public SelectCommunityPatch(GamePlayer player, int slot)
    {
        super(player);
        selectedSlot = slot;
    }

    public Patch getSelectedPatch()
    {
        return selectedPatch;
    }

}//SelectCommunityPatch
