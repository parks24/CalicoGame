package edu.up.cs301.Calico;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;


/**
 * A computer-version of a Calico-player. This player makes decisions about
 * patch selection and placement off random chance.
 *
 * @author Luca Sburlino
 * @version April 2025
 */

public class CalicoComputerPlayer1 extends GameComputerPlayer implements Tickable, Serializable {


	private CalicoState state;
	private ArrayList<int[]> availablePatches = new ArrayList<>();
	protected static final long serialVersionUID = 170425L;


	/**
	 * Constructor for the CalicoComputerPlayer1 class.
	 * Initializes the player's name and generates a list of valid patch positions.
	 *
	 * @param name the name of the player
	 */
    public CalicoComputerPlayer1(String name)
	{
        // invoke superclass constructor
        super(name);

		//create arrayList of all available patches
		int[] patch;
		for(int i = 1; i<=5; i++)
		{
			for (int j = 1; j<=5; j++)
			{
				if(!(i==2 && j==3) && !(i==3 && j==2) && !(i==4 && j ==4))
				{
					patch = new int[2];
					patch[0] = i;
					patch[1] = j;
					availablePatches.add(patch);
				}
			}
		}
    }//CalicoComputerPlayer1

	/**
	 * Constructor for the CalicoComputerPlayer1 class with specified player number.
	 * Initializes the player's name, number, and the list of valid patch positions.
	 *
	 * @param name the name of the player
	 * @param playerNum the number assigned to the specified player
	 */
	public CalicoComputerPlayer1(String name, int playerNum) {
		// invoke superclass constructor
		super(name);
		this.playerNum = playerNum;


		//create arrayList of all available patches
		int[] patch;
		for(int i = 1; i<=5; i++)
		{
			for (int j = 1; j<=5; j++)
			{
				if(!(i==2 && j==3) && !(i==3 && j==2) && !(i==4 && j ==4))
				{
					patch = new int[2];
					patch[0] = i;
					patch[1] = j;
					availablePatches.add(patch);
				}
			}
		}
	}
    
    /**
     * callback method--game's state has changed
     * 
     * @param info
     * 		the information (presumably containing the game's state)
     */
	@Override
	protected void receiveInfo(GameInfo info) {
		// Make move for computer player
		this.state = (CalicoState)info;

		Log.i("PlayerTurn: ", "Game State Received \nPlayerTurn = " +String.valueOf(state.playerTurn));

		if (playerNum == state.playerTurn && state.gameStage == 1) {

			//randomly selects place on board, patch from inventory, and patch from community
			int avaliablePatchIndex =(int) (Math.random() * (availablePatches.size()));
			int[] locOnBoard = availablePatches.get(avaliablePatchIndex);
			availablePatches.remove(avaliablePatchIndex);

			int inventoryIndex = (int) (Math.random() * (2));
			Patch placedPatch = state.playerHand[playerNum][inventoryIndex];

			int communityIndex = (int) (Math.random() * 3);
			Patch communityPatch = state.communityPool[communityIndex];

			//call necessary function (same as human player I think) to update game
			// send the move-action to the game
			game.sendAction(new CalicoMoveAction(this, locOnBoard, placedPatch, communityPatch));
		}
	}
	
	/**
	 * callback method: the timer ticked
	 */
	protected void timerTicked() {
	}
}

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */
