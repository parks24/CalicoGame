package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class CloseMenu extends GameAction
{
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CloseMenu(GamePlayer player) {
        super(player);
    }
}
