package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.utilities.MessageBox;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that represents the state of our Calico game. In our Calico game, the only
 * relevant piece of information is the value of the game's Calico. The
 * CounterState object is therefore very simple.
 * 
 * @author Joseph Early
 * @version April 2025
 */
public class CalicoLocalGame extends LocalGame implements Serializable {

	public static final int TARGET_MAGNITUDE = 10;

	private CalicoState gameState; //Current Calico Game State
	protected CalicoState savedState; //pre-change state for undoMove
	
	/**
	 * can this player move
	 * 
	 * @return
	 * 		true, because all player are always allowed to move at all times,
	 * 		as this is a fully asynchronous game
	 */
	@Override
	protected boolean canMove(int playerIdx) {
		return true;
	}

	/**
	 * Constructor for CalicoLocalGame.
	 * Initializes the game state either from a passed state or with default settings.
	 *
	 * @param state the initial GameState; if not a CalicoState, a new one will be created
	 */
	public CalicoLocalGame(GameState state)
	{
		// initialize the game state, with the Calico value starting at 0
		if (! (state instanceof CalicoState)) {
			state = new CalicoState(4);
		}
		this.gameState = (CalicoState)state;
		super.state = state;

		savedState = new CalicoState(gameState);
	}//CalicoLocalGame

	/**
	 * Sends GameAction to action methods in CalicoState to update the game accordingly.
	 *
	 * @param action the GameAction taken by the player
	 * @return true if the action was valid and applied successfully, false otherwise
	 */
	@Override
	protected boolean makeMove(GameAction action)
	{
		Log.i("action", action.getClass().toString());

		if (action instanceof SelectPatch) {
			return gameState.selectPatch(action);
		} else if (action instanceof PlacePatch) {
			return gameState.placePatch(action);
		} else if (action instanceof SelectCommunityPatch) {
			return gameState.selectCommunityPatch(action);
		}

		else if (action instanceof ConfirmMove)
		{
			//Confirm post-move state
			boolean result = gameState.confirmMove(action);
			if (result) {savedState = new CalicoState(this.gameState);}
			return result;
		} //Confirm Move

		else if (action instanceof UndoMove)
		{
			//Revert state to pre-move copy and set to start of turn
			boolean result = gameState.undoMove(action);
			if (result) {gameState = new CalicoState(this.savedState);}
			return result;
		}//UndoMove

		else if (action instanceof ViewObjectives) {
			return gameState.viewObjectives(action);
		} else if (action instanceof CloseMenu) {
			return gameState.closeMenu(action);
		} else if (action instanceof CalicoMoveAction) {
			boolean result = gameState.computerMove(action);
			if (result) {savedState = new CalicoState(this.gameState);}
			return result;
		} else {
			// denote that this was an illegal move
			return false;
		}
	}//makeMove
	
	/**
	 * Sends a deepcopy of the updated CalicoState to GamePlayer p
	 *
	 *  @param p the player to send the state to
	 */
	@Override
	protected void sendUpdatedStateTo(GamePlayer p)
	{
		// this is a perfect-information game, so we'll make a
		// complete copy of the state to send to the player
		p.sendInfo(new CalicoState(this.gameState));
		
	}//sendUpdatedSate
	
	/**
	 * Check if the game is over. It is over, return a string that tells
	 * who the winner(s), if any, are. If the game is not over, return null;
	 * 
	 * @return
	 * 		a message that tells who has won the game, or null if the
	 * 		game is not over
	 */
	@Override
	protected String checkIfGameOver()
	{

		//Log GameStage for debugging purposes
		Log.i("GameStage","GameStage:" + gameState.gameStage);

		if(gameState.gameStage == 2)
		{
			ArrayList<Integer> finalScores = gameState.getPlayerScores();
			int largestScore = finalScores.get(0);
			int winningPlayer = 1;

			for(int i = 0; i < finalScores.size(); i++)
			{
				if(finalScores.get(i) > largestScore)
				{
					largestScore = finalScores.get(i);
					winningPlayer = i + 1;
				}
			}

			String scoreMessage = "Player " + winningPlayer + " Won with a score of: " + largestScore + "\n"
								+ "Final Scores: \n"
								+ "Player One: " + finalScores.get(0) + "\n"
								+ "Player Two: " + finalScores.get(1) + "\n"
								+ "Player Three: " + finalScores.get(2) + "\n"
								+ "Player Four: " + finalScores.get(3) + "\n";
			return scoreMessage;
		}

		return null;
	}//checkIfGameOver

}// class CounterLocalGame
