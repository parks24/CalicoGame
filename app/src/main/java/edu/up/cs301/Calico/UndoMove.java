package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;


/**
 * An action to undo move. It resets the game state to the state at the start of move
 *
 * @author Joseph Early
 * @version 2025
 */
public class UndoMove extends GameAction implements Serializable
{
    //Instance Variables
    public GameAction lastPlayedMove;
    protected static final long serialVersionUID = 170425;


    /**
     * constructor for UndoMove
     *
     * @param player the player who created the action
     */
    public UndoMove(GamePlayer player)
    {
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