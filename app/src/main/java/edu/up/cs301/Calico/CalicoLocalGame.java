package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import android.util.Log;

/**
 * A class that represents the state of a game. In our Calico game, the only
 * relevant piece of information is the value of the game's Calico. The
 * CounterState object is therefore very simple.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class CalicoLocalGame extends LocalGame {

	// When a Calico game is played, any number of players. The first player
	// is trying to get the Calico value to TARGET_MAGNITUDE; the second player,
	// if present, is trying to get the Calico to -TARGET_MAGNITUDE. The
	// remaining players are neither winners nor losers, but can interfere by
	// modifying the Calico.
	public static final int TARGET_MAGNITUDE = 10;

	// the game's state
	private CalicoState gameState;
	
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
	public CalicoLocalGame(GameState state) {
		// initialize the game state, with the Calico value starting at 0
		if (! (state instanceof CalicoState)) {
			state = new CalicoState();
		}
		this.gameState = (CalicoState)state;
		super.state = state;
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
		} else if (action instanceof ConfirmMove) {
			return gameState.confirmMove(action);
		} else if (action instanceof UndoMove) {
			return gameState.undoMove(action);
		} else if (action instanceof ViewObjectives) {
			return true;
		} else if (action instanceof CloseMenu) {
			return true;
		} else if (action instanceof ViewPlayerBoard) {
			return true;
		} else {
			// denote that this was an illegal move
			return false;
		}
	}//makeMove
	
	/**
	 * send the updated state to a given player
	 */
	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {
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
		
//		// get the value of the Calico
//		int counterVal = this.gameState.getCounter();
//
//		if (counterVal >= TARGET_MAGNITUDE) {
//			// Calico has reached target magnitude, so return message that
//			// player 0 has won.
//			return playerNames[0]+" has won.";
//		}
//		else if (counterVal <= -TARGET_MAGNITUDE) {
//			// Calico has reached negative of target magnitude; if there
//			// is a second player, return message that this player has won,
//			// otherwise that the first player has lost
//			if (playerNames.length >= 2) {
//				return playerNames[1]+" has won.";
//			}
//			else {
//				return playerNames[0]+" has lost.";
//			}
//		}else {
//			// game is still between the two limit: return null, as the game
//			// is not yet over
//			return null;
//		}
		return null;

	}

}// class CounterLocalGame
