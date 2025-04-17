package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class ConfirmMove extends GameAction implements Serializable
{
    protected static final long serialVersionUID = 170425;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ConfirmMove(GamePlayer player) {
        super(player);
    }
}
