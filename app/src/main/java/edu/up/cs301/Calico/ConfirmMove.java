package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * An action to confirm move
 * increments turn to next player's turn
 *
 * @author Joseph Early
 * @version 2025
 */
public class ConfirmMove extends GameAction implements Serializable
{
    protected static final long serialVersionUID = 170425;

    /**
     * constructor for Confirm Move
     *
     * @param player the player who created the action
     */
    public ConfirmMove(GamePlayer player) {
        super(player);
    }
}

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */