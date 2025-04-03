package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.actionMessage.GameAction;

/**
 * A CounterMoveAction is an action that is a "move" the game: either increasing
 * or decreasing the Calico value.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version September 2012
 */
public class CalicoMoveAction extends GameAction {
	
	// to satisfy the serializable interface
	private static final long serialVersionUID = 28062013L;

	//location on board for patch to be placed
	private int[] locOnBoard;

	//patch from inventory to place on board
	private Patch placedPatch;

	//community patch to put in hand
	private Patch communityPatch;
	
	/**
	 * Constructor for the CounterMoveAction class.
	 * 
	 * @param player
	 *            the player making the move
	 * @param _locOnBoard
	 * @param _placedPatch
	 * @param _communityPatch
	 *
	 */
	public CalicoMoveAction(GamePlayer player, int[] _locOnBoard, Patch _placedPatch, Patch _communityPatch) {
		super(player);
		this.locOnBoard = _locOnBoard;
		this.placedPatch = _placedPatch;
		this.communityPatch = _communityPatch;
	}
	
	/**
	 * getter method, to tell whether the move is a "plus"
	 * 
	 * @return
	 * 		a boolean that tells whether this move is a "plus"
	 */
	public Patch getCommunityPatch() {
		return communityPatch;
		
	}

	public int[] getLocOnBoard() {
		return locOnBoard;
	}

	public Patch getPlacedPatch() {
		return placedPatch;
	}
}//class CounterMoveAction
