package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class CloseMenu extends GameAction implements Serializable
{
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    protected static final long serialVersionUID = 170425;
    public CloseMenu(GamePlayer player) {
        super(player);
    }
}
