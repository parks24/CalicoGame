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
 * A class that represents the state of a game. In our Calico game, the only
 * relevant piece of information is the value of the game's Calico. The
 * CounterState object is therefore very simple.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class CalicoLocalGame extends LocalGame implements Serializable {

	// When a Calico game is played, any number of players. The first player
	// is trying to get the Calico value to TARGET_MAGNITUDE; the second player,
	// if present, is trying to get the Calico to -TARGET_MAGNITUDE. The
	// remaining players are neither winners nor losers, but can interfere by
	// modifying the Calico.
	public static final int TARGET_MAGNITUDE = 10;

	// the game's state
	private CalicoState gameState;
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
	 * This ctor should be called when a new Calico game is started
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
	}

	/**
	 * The only type of GameAction that should be sent is CounterMoveAction
	 */
	@Override
	protected boolean makeMove(GameAction action) {
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
	 * send the updated state to a given player
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
	protected String checkIfGameOver() {

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

	}

}// class CounterLocalGame
