package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * An action to place patch. Sends selected spot in players hand to the game state
 *
 * @author Joseph Early
 * @version 2025
 */
public class SelectPatch extends GameAction implements Serializable
{

    //Instance Variables
    protected static final long serialVersionUID = 170425;
    protected int selectedSlot;

    /**
     * default constructor for Select patch
     *
     * @param player the player who created the action
     */
    public SelectPatch(GamePlayer player)
    {
        super(player);
    }

    /**
     * constructor for Select patch
     *
     * @param player the player who created the action
     * @param slot the slot of a player's hand they selected
     */
    public SelectPatch(GamePlayer player, int slot)
    {
        super(player);
        selectedSlot = slot;
    }

}//SelectPatch
