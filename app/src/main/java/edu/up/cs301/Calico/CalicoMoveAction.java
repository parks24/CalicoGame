package edu.up.cs301.Calico;

import java.io.Serializable;

import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.actionMessage.GameAction;

/**
 * Default move action used by both computer players
 * sends state location on board, patch to place, and the community patch to put into the hand
 *
 * @author Luca Sburlino
 * @version 2025
 */
public class CalicoMoveAction extends GameAction implements Serializable {
	
	// to satisfy the serializable interface
	protected static final long serialVersionUID = 170425;

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
	 * @param _locOnBoard place to place _placedPatch
	 * @param _placedPatch patch to place
	 * @param _communityPatch patch to fill inventory with
	 *
	 */
	public CalicoMoveAction(GamePlayer player, int[] _locOnBoard, Patch _placedPatch, Patch _communityPatch) {
		super(player);
		this.locOnBoard = _locOnBoard;
		this.placedPatch = _placedPatch;
		this.communityPatch = _communityPatch;
	}
	
	/**
	 * getter method, to return community patch
	 * 
	 * @return
	 * 		community patch
	 */
	public Patch getCommunityPatch() {
		return communityPatch;
		
	}

	/**
	 * getter method, to return the location on board
	 *
	 * @return
	 * 		locOnBoard
	 */
	public int[] getLocOnBoard() {
		return locOnBoard;
	}


	/**
	 * getter method, to return the placed Patch
	 *
	 * @return
	 * 		placedPatch
	 */
	public Patch getPlacedPatch() {
		return placedPatch;
	}
}//class CounterMoveAction
