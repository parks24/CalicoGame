package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * An action to select community patch.
 * Sends selected spot in community patch array to game state.
 * game state will move the tile in that spot to the player's hand
 *
 * @author Joseph Early
 * @version 2025
 */
public class SelectCommunityPatch extends GameAction implements Serializable
{

    //Instance Variables
    protected static final long serialVersionUID = 170425;
    protected int selectedSlot;

    /**
     * default constructor for Select patch
     *
     * @param player the player who created the action
     */
    public SelectCommunityPatch(GamePlayer player)
    {
        super(player);
    }

    /**
     * constructor for Select patch
     *
     * @param player the player who created the action
     * @param slot the slot of the community patch pool to add to hand
     */
    public SelectCommunityPatch(GamePlayer player, int slot)
    {
        super(player);
        selectedSlot = slot;
    }

}//SelectCommunityPatch

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */