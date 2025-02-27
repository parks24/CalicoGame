package edu.up.cs301.Calico;

import java.util.ArrayList;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the Calico.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class CalicoState extends GameState
{

	//Instance Variables
	protected ArrayList<Integer> playerScores = new ArrayList<>();
	protected int playerTurn;
	protected int turnStage; //Player selecting or placing during turn
	protected int gameStage; //Stage of game

	protected ArrayList<Patch> communityPool = new ArrayList<>();
	protected ArrayList<Patch[]> playerHand;
	protected ArrayList<Patch> deck;
	protected ArrayList<Board> playerBoard = new ArrayList<>();

	public CalicoState()
	{

	}
}
