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
