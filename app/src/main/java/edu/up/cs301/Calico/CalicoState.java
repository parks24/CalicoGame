package edu.up.cs301.Calico;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GameComputerPlayer;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the Calico.
 * 
 * @author Brian Parks
 * @author Luca Sburlino
 * @author Joseph Early
 * @version 2025
 */

//TODO Commenting, Formatting, and Javadocs 
public class CalicoState extends GameState implements Serializable {

	//Instance Variables

	//UID for network play support
	protected static final long serialVersionUID = 170425L;

	/**
	 * Array holding the Cat objectives used in the game.
	 */
	protected Cat[] cats = new Cat[3];

	/**
	 * Number of players in the game.
	 */
	protected int numPlayers;

	/**
	 * Indicates which player's turn it currently is.
	 */
	protected int playerTurn;

	/**
	 * Indicates the stage of the current player's turn.
	 * 0 = Selecting patch
	 * 1 = Selecting where to place (place patch)
	 * 2 = draw patch from community pool
	 * 3 = confirm/end turn
	 */
	protected int turnStage;

	/**
	 * Stage of game
	 * 0̶ ̶=̶ ̶p̶l̶a̶c̶i̶n̶g̶ ̶g̶o̶a̶l̶ ̶t̶i̶l̶e̶s̶
	 * 1 = placing tiles
	 * 2 = board filled
	 */
	protected int gameStage;

	/**
	 * Pool of patches available for all players to draw from.
	 */
	protected Patch[] communityPool = new Patch[3];

	/**
	 * Each player's hand of 2 patches.
	 */
	protected Patch[][] playerHand;

	/**
	 * Main deck of available patches, used to fill communityPool.
	 */
	protected ArrayList<Patch> deck = new ArrayList<>();

	/**
	 * List of player boards, one for each player.
	 */
	protected ArrayList<Board> playerBoard = new ArrayList<>();

	/**
	 * The currently selected patch by the active player.
	 */
	protected Patch selectedPatch;

	/**
	 * Index of the selected slot on the player board for patch placement.
	 */
	protected int selectedSlot;

	/**
	 * Index of the patch selected from the community pool.
	 */
	protected int communitySlot;


	/**
	 * Constructor for the CalicoState object, sets up initial game state based on the number of players.
	 *
	 * @param _numPlayers Number of players in the game.
	 */
	public CalicoState(int _numPlayers)
	{

		//start of game stage of game values
		playerTurn = 0;
		turnStage = 0;
		gameStage = 1;
		numPlayers = _numPlayers;
		playerHand = new Patch[numPlayers][2];

		//create deck
		//6 of each tile
		//36 tiles made of 6 colors and 6 patterns
		for (int color = 1; color<7; color++)
		{
			for (int pattern = 1; pattern<7; pattern++)
			{
				for (int numTile = 0; numTile<6; numTile++)
				{
					deck.add(new Patch(pattern, color));
				}
			}
		}

		//initialize community pool
		drawNewCommunityPatch(0);
		drawNewCommunityPatch(1);
		drawNewCommunityPatch(2);

		//Initialize player hands to default patches
		for (int i = 0; i<numPlayers; i ++)
		{
			int patchInDeck = 1 + (int)(Math.random() * (deck.size()));
			playerHand[i][0] = deck.get(patchInDeck);
			deck.remove(patchInDeck);

			patchInDeck = 1 + (int)(Math.random() * (deck.size()));
			playerHand[i][1] = deck.get(patchInDeck);
			deck.remove(patchInDeck);
		}

		//Create and Initialize player boards
		for (int i = 0; i<numPlayers; i++) {
			playerBoard.add(new Board());
			initPlayerBoard(i);
		}


		//Initialize board edges for all players
		initializeBoardEdges();

        //Chooses the cats to add
        //assigns random patterns to each cat
        ArrayList<Integer> patterns = new ArrayList<>();
		int[] patternsSent = new int[2];
        for (int i = 1; i<=6; i++) patterns.add(i);

		//adds 2 random of the 6 patterns to each cat
        for (int i = 0; i<3; i++)
        {
			int patternIdx = (int) Math.floor(Math.random() * patterns.size());
			patternsSent[0] = patterns.get(patternIdx);
			patterns.remove((Integer) patternsSent[0]);

			patternIdx = (int) Math.floor(Math.random() * patterns.size());
			patternsSent[1] = patterns.get(patternIdx);
			patterns.remove((Integer) patternsSent[1]);

			cats[i] = new Cat(i+1, patternsSent);
        }

		selectedSlot = 3;
		selectedPatch = null;
		communitySlot = 4;

	}//default Constructor


	public void initPlayerBoard(int player)
	{
		Board tempPlayer = playerBoard.get(player);

//		//Row One
//		tempPlayer.setPatch(new Patch(6,4),1,1); //1,1 (star, green)
//		tempPlayer.setPatch(new Patch(6,3),1,2); //1,2 (star, yellow)
//		tempPlayer.setPatch(new Patch(2,1),1,3); //1,3 (fract, red)
//
//		if(player != 3) //Check for Player Four
//		{
//			tempPlayer.setPatch(new Patch(5,6),1,4); //1,4 (smile, pink)
//		}
//
//		//Row Two
//		tempPlayer.setPatch(new Patch(3,6),2,1); //2,1
//		tempPlayer.setPatch(new Patch(5, 3),2,2); //2,2
		tempPlayer.setPatch(new GoalPatch(2),2,3); //2,3 GOAL
//		tempPlayer.setPatch(new Patch(4, 1),2,4); //2,4
//		tempPlayer.setPatch(new Patch(1,2),2,5); //2,5
//
//		//Row Three
//		tempPlayer.setPatch(new Patch(3, 4),3,1); //3,1
		tempPlayer.setPatch(new GoalPatch(1),3,2); //3,2 GOAL
//		tempPlayer.setPatch(new Patch(5,4),3,3); //3,3
//		tempPlayer.setPatch(new Patch(5,6),3,4); //3,4
//		tempPlayer.setPatch(new Patch(1,1),3,5); //3,5
//
//		//Row Four
//		tempPlayer.setPatch(new Patch(2,1),4,1); //4,1
//		tempPlayer.setPatch(new Patch(4,5),4,2); //4,2
//		tempPlayer.setPatch(new Patch(3,5),4,3); //4,3
		tempPlayer.setPatch(new GoalPatch(3),4,4); //4,4 GOAL
//		tempPlayer.setPatch(new Patch(3,6),4,5); //4,5
//
//		//Row Five
//		tempPlayer.setPatch(new Patch(2,5),5,1); //5,1
//		tempPlayer.setPatch(new Patch(4,1),5,2); //5,2
//		tempPlayer.setPatch(new Patch(4,2),5,3); //5,3
//		tempPlayer.setPatch(new Patch(5,6),5,4); //5,4
//		tempPlayer.setPatch(new Patch(3,1),5,5); //5,5

	}


	/**
	 * Initialize edges of the player boards
	 */
	public void initializeBoardEdges()
	{
		//Initialize board edges
		for(int i = 0; i < numPlayers; i++)
		{
			//pattern then color
			Board tempPlayer = playerBoard.get(i); //get Board

			//Top Row (row 0)
			tempPlayer.setPatch(new Patch(4, 3),0 , 0); //0,0 (lines, yellow)
			tempPlayer.setPatch(new Patch(3, 5),0 , 1); //0,1 (heart, blue)
			tempPlayer.setPatch(new Patch(1, 1),0 , 2); //0,2 (dots, red)
			tempPlayer.setPatch(new Patch(2, 6),0 , 3); //0,3 (fract, pink)
			tempPlayer.setPatch(new Patch(5, 3),0 , 4); //0,4 (smile, yellow)
			tempPlayer.setPatch(new Patch(6, 2),0 , 5); //0,5 (star, orange)

			//Left Col (col 0)
			tempPlayer.setPatch(new Patch(5, 6),1 , 0); //1,0 (smile, pink)
			tempPlayer.setPatch(new Patch(6, 4),2 , 0); //2,0 (star, green)
			tempPlayer.setPatch(new Patch(4, 1),3 , 0); //3,0 (line, red)
			tempPlayer.setPatch(new Patch(2, 2),4 , 0); //4,0 (fract,orange)
			tempPlayer.setPatch(new Patch(3, 3),5 , 0); //5,0 (heart,yellow)
			tempPlayer.setPatch(new Patch(1, 5),6 , 0); //6,0 (dot,blue)

			//Right Rol (col 6)
			tempPlayer.setPatch(new Patch(3, 4),1 , 6); //1,6 (heart,green)
			tempPlayer.setPatch(new Patch(2, 3),2 , 6); //2,6 (fract,yellow)
			tempPlayer.setPatch(new Patch(1, 5),3 , 6); //3,6 (dot,blue)
			tempPlayer.setPatch(new Patch(4, 2),4 , 6); //4,6 (line,orange)
			tempPlayer.setPatch(new Patch(6, 6),5 , 6); //5,6 (star,pink)

			//Bottom Row (row 6)
			tempPlayer.setPatch(new Patch(5, 6),6 , 1); //6,1 (smile,pink)
			tempPlayer.setPatch(new Patch(3, 1),6 , 2); //6,2 (heart,red)
			tempPlayer.setPatch(new Patch(6, 4),6 , 3); //6,3 (star,green)
			tempPlayer.setPatch(new Patch(5, 3),6 , 4); //6,4 (smile,yellow)
			tempPlayer.setPatch(new Patch(2, 2),6 , 5); //6,5 (fract,orange)

			tempPlayer.setPatch(new Patch(8,8),0,6);
			tempPlayer.setPatch(new Patch(8,8),6,6);
		}
	}//initializeBoardEdges

	/** Copy Constructor
	 *
	 * @param other object to be copied
	 */
	public CalicoState(CalicoState other)
	{

		//start of game stage of game values
		this.playerTurn = other.playerTurn;
		this.turnStage = other.turnStage;
		this.gameStage = other.gameStage;
		this.numPlayers = other.numPlayers;
		this.playerHand = new Patch[numPlayers][2];
		//initialize community pool to default patches
		this.communityPool[0] = new Patch(other.communityPool[0]);
		this.communityPool[1] = new Patch(other.communityPool[1]);
		this.communityPool[2] = new Patch(other.communityPool[2]);

		//copy player hands
		for (int i = 0; i<numPlayers; i ++)
		{
			for (int j = 0; j<2; j++)
			{
				this.playerHand[i][j] = new Patch(other.playerHand[i][j]);
			}
		}

		//copy deck
		for (int i = 0; i < other.deck.size(); i++)
		{
			this.deck.add(new Patch(other.deck.get(i)));
		}

		//copy Boards
		for (int i = 0; i < other.playerBoard.size(); i++)
		{
			this.playerBoard.add(new Board(other.playerBoard.get(i)));
		}

		//copy cats
		for (int i = 0; i < other.cats.length; i++)
		{
			this.cats[i] = new Cat(other.cats[i]);
		}

		selectedPatch = other.selectedPatch;
		selectedSlot = other.selectedSlot;
		communitySlot = other.communitySlot;
	}


	/**
	 * Confirms the player's move and ends their turn if the game is in the correct stage.
	 *
	 * @param move the {@link GameAction} attempted by the player.
	 * @return true if the move was confirmed successfully, false otherwise.
	 */
	public boolean confirmMove(GameAction move)
	{
		if(move instanceof ConfirmMove)
		{
			//Change Player after Round
			if(turnStage == 3)
			{
				playerTurn = (playerTurn +1) % numPlayers;
				drawNewCommunityPatch(communitySlot);
				turnStage = 0;

				//Log turnStage for debugging purposes
				Log.i("TurnStage","Turn Stage: " + turnStage);
				Log.i("PlayerTurn","PlayerTurn: " + playerTurn);

				endGameCheck();
				return true;
			}else{
				Log.i("Timing","confirm move pressed at wrong time");
				return false;
			}
		}

		return false;
	}//confirmMove


	/**
	 * Checks if all player boards are filled and updates the game stage if the game is complete.
	 */
	public void endGameCheck()
	{
		//Perform endgame check
		if(playerBoard.get(0).isComplete() && playerBoard.get(1).isComplete() && playerBoard.get(2).isComplete() && playerBoard.get(3).isComplete())
		{
			gameStage = 2;
		}
	}

	/**
	 * Reverts the game to the pre-move state.
	 * Actual undo occurs in CalicoLocalGame.
	 *
	 * @param move the {@link GameAction} to undo.
	 * @return true if undo was initiated, false otherwise.
	 */
	public boolean undoMove(GameAction move)
	{
		if(move instanceof UndoMove)
		{

			turnStage = 0;
			selectedSlot = 3;
			//Log turnStage for debugging purposes
			Log.i("TurnStage","UNDO to Turn Stage: " + turnStage);

			return true;
		}

		return false;
	}//undoMove

	/** Places selectedPatch from player inventory to player board
	 *
	 * @param move gameAction
	 * @return true if the patch was successfully placed, false otherwise.
	 */
	public boolean placePatch(GameAction move)
	{
		if(move instanceof PlacePatch)
		{
			//Get players board
			Board currentBoard = playerBoard.get(playerTurn);


			//Get selected row col position on board
			int selectedRow = ((PlacePatch) move).getBoardRow();
			int selectedCol = ((PlacePatch) move).getBoardCol();

			//Get selected patch on board
			Patch selectedMove = currentBoard.getPatch(selectedRow, selectedCol);

			//Check to make sure the selected patch on board is empty
			if(selectedMove.getPatchPattern() == 0 && selectedPatch != null)
			{
				//Make the move
				currentBoard.setPatch(selectedPatch, selectedRow, selectedCol);

				playerHand[playerTurn][selectedSlot] = new Patch();
				selectedPatch = null;
				selectedSlot = 3;
				turnStage = 2;

				//Log turnStage for debugging purposes
				Log.i("TurnStage","Turn Stage: " + turnStage);

				int[] locOnBoard = {((PlacePatch) move).boardRow, ((PlacePatch) move).boardCol};
				checkButtonCat(locOnBoard, playerTurn);
				//confirm move validity
				return true;
			}

		}//placePatch

		return false;
	}//placePatch


	/**
	 * Handles the action of selecting a patch from the player’s hand.
	 *
	 * @param move the {@link GameAction}, expected to be of type {@link SelectPatch}.
	 * @return true if the patch was successfully selected, false otherwise.
	 */
	public boolean selectPatch(GameAction move)
	{
		if(move instanceof SelectPatch)
		{
			SelectPatch selectMove = (SelectPatch) move;
			playerHand[playerTurn][selectMove.selectedSlot].selectPatch(); //Select Patch from Hand
			selectedPatch = playerHand[playerTurn][selectMove.selectedSlot];
			selectedSlot = selectMove.selectedSlot;

			turnStage = 1; //Move to placepatch phase of turn

			//Log turnStage for debugging purposes
			Log.i("TurnStage","Turn Stage: " + turnStage);
			return true;
		}

		return false;

	}//selectPatch


	/**
	 * Handles selection of a patch from the community pool by the current player.
	 *
	 * @param move the {@link GameAction}, expected to be of type {@link SelectCommunityPatch}.
	 * @return true if a patch was taken and community updated, false otherwise.
	 */
	public boolean selectCommunityPatch(GameAction move)
	{
		if(move instanceof SelectCommunityPatch)
		{
			SelectCommunityPatch selectMove = (SelectCommunityPatch) move;

			//Check which position in the player hand is empty and replace slot
			if(playerHand[playerTurn][0].getPatchColor() == 0 && playerHand[playerTurn][0].getPatchPattern() == 0)
			{
				playerHand[playerTurn][0] = communityPool[selectMove.selectedSlot];
			}

			else if(playerHand[playerTurn][1].getPatchColor() == 0 && playerHand[playerTurn][1].getPatchPattern() == 0)
			{
				playerHand[playerTurn][1] = communityPool[selectMove.selectedSlot];
			}

			//set to blank tile
			communitySlot = selectMove.selectedSlot;
			communityPool[communitySlot] = new Patch();
			turnStage = 3;

			//Log turnStage for debugging purposes
			Log.i("TurnStage","Turn Stage: " + turnStage);

			return true;
		}
		return false;
	}//selectCommunityPatch


	/**
	 * Performs a move automatically on behalf of a computer player.
	 *
	 * @param move the {@link GameAction}, expected to be of type {@link CalicoMoveAction}.
	 * @return true if the move was successfully processed, false otherwise.
	 */
	public boolean computerMove(GameAction move) {
		if (move instanceof CalicoMoveAction && move.getPlayer() instanceof GameComputerPlayer)
		{
			//get player num
			int player = ((GameComputerPlayer)move.getPlayer()).getPlayerNum();

			//place patch
			playerBoard.get(player).setPatch(((CalicoMoveAction) move).getPlacedPatch(),
					((CalicoMoveAction) move).getLocOnBoard()[0], ((CalicoMoveAction) move).getLocOnBoard()[1]);

			//move communityPatch to hand
			for(int i = 0; i < 2; i++)
			{
				if(playerHand[player][i].patchColor == ((CalicoMoveAction) move).getPlacedPatch().patchColor
				&& playerHand[player][i].patchPattern == ((CalicoMoveAction) move).getPlacedPatch().patchPattern)
				{
					playerHand[player][i] = ((CalicoMoveAction) move).getCommunityPatch();
					break;
				}
			}

			//get new community patch and update deck
			for(int i = 0; i < 3; i++)
			{
				if(communityPool[i].patchColor == ((CalicoMoveAction) move).getCommunityPatch().patchColor
						&& communityPool[i].patchPattern == ((CalicoMoveAction) move).getCommunityPatch().patchPattern)
				{
					drawNewCommunityPatch(i);
					break;
				}
			}

			checkButtonCat(((CalicoMoveAction) move).getLocOnBoard(), player);
			playerTurn = (playerTurn +1) %numPlayers;
			endGameCheck();
			return true;
		}
		return false;
	}


	/**
	 * Checks if a button or cat should be awarded to the player based on the recently placed patch.
	 *
	 * @param patchToCheck the coordinates of the patch that was just placed.
	 * @param player       the player number placing the patch.
	 */
	public void checkButtonCat(int[] patchToCheck, int player)
	{

		//gets Arraylist of all patches with same color
		//and if a button already exists for those patches
		ArrayList<int[]> similarPatchesColor = new ArrayList<>();
		Board board = playerBoard.get(player);
		int placedPatchColor = board.getPatch(patchToCheck[0],patchToCheck[1]).patchColor;
		boolean buttonExists =
				board.getSimilarPatchesColor(similarPatchesColor,patchToCheck,placedPatchColor);


		//increases the player button count
		//if enough patches and no button already
		if(similarPatchesColor.size()>=3 && !buttonExists)
		{
			board.playerScore.increaseButtonCount(placedPatchColor);
		}

		//gets Arraylist of all patches with same pattern
		//and if a cat already exists for those patches
		ArrayList<int[]> similarPatchesPattern = new ArrayList<>();
		int placedPatchPattern = board.getPatch(patchToCheck[0],patchToCheck[1]).patchPattern;
		boolean catExists =
				board.getSimilarPatchesPattern(similarPatchesPattern,patchToCheck,placedPatchPattern,this.cats);


		//calls cat function to see if cat should be added
		if(!catExists)
		{
			for (int i = 0; i < 3; i++)
				if (this.cats[i].addCat(similarPatchesPattern, placedPatchPattern))
					board.playerScore.increaseCatCount(i);
		}

	}//checkButton



	@Override
	public String toString()
	{
		//Current Player turn
		String turnInfo = "Player Turn: " + playerTurn + "\n"
				+ "Turn Stage: " + turnStage + "\n"
				+ "Game Stage: " + gameStage + "\n";


		//current Player scoring
		String currentScores = "Player Scores\n";

		//Append player scores to currentScores string
		for(int i = 0; i < playerBoard.size(); i++)
		{
			int playerNumber = i+1; //Index by one for accurate player number (1-numPlayers not 0-3)
			String playerScoreTemp = "Player " + playerNumber + " : " + playerBoard.get(i).playerScore + "\n";
			currentScores = currentScores.concat(playerScoreTemp); //Add player score to list
		}

		//Community Pool Info
		String communityPoolInfo = "Patch One = Pattern: " + communityPool[0].getPatchPattern() + " Patch Color: " + communityPool[0].getPatchColor() + "\n"
				+ "Patch Two = Pattern: " + communityPool[1].getPatchPattern() + " Patch Color: " + communityPool[1].getPatchColor() + "\n"
				+ "Patch Three = Pattern: " + communityPool[2].getPatchPattern() + " Patch Color: " + communityPool[2].getPatchColor() + "\n";

		//PlayerHand Info
		String playerOneHand = "Player One's Hand: \n" +
				"Patch One = Pattern:" + playerHand[0][0].getPatchPattern() + " Color: " + playerHand[0][0].getPatchColor() + "\n" +
				"Patch Two = Pattern:" + playerHand[0][1].getPatchPattern() + " Color: " + playerHand[0][1].getPatchColor() + "\n";

		String playerTwoHand = "Player Two's Hand: \n" +
				"Patch One = Pattern:" + playerHand[1][0].getPatchPattern() + " Color: " + playerHand[1][0].getPatchColor() + "\n" +
				"Patch Two = Pattern:" + playerHand[1][1].getPatchPattern() + " Color: " + playerHand[1][1].getPatchColor() + "\n";

		String playerThreeHand = "Player Three's Hand: \n" +
				"Patch One = Pattern:" + playerHand[2][0].getPatchPattern() + " Color: " + playerHand[2][0].getPatchColor() + "\n" +
				"Patch Two = Pattern:" + playerHand[2][1].getPatchPattern() + " Color: " + playerHand[2][1].getPatchColor() + "\n";

		String playerFourHand = "Player Four's Hand: \n" +
				"Patch One = Pattern:" + playerHand[3][0].getPatchPattern() + " Color: " + playerHand[3][0].getPatchColor() + "\n" +
				"Patch Two = Pattern:" + playerHand[3][1].getPatchPattern() + " Color: " + playerHand[3][1].getPatchColor() + "\n";

		String playerHandInfo = playerOneHand + playerTwoHand + playerThreeHand + playerFourHand;

		//Compile and return all info
		return turnInfo + currentScores + communityPoolInfo + playerHandInfo;
	}//toString

	/**
	 * drawNewCommunityPatch
	 * Cycles deck
	 * @param communityIndex is int of index of patch to get rid of
	 */
	private void drawNewCommunityPatch(int communityIndex)
	{
		int patchInDeck = (int)(Math.random() * (deck.size()));
		communityPool[communityIndex] = deck.get(patchInDeck);
		deck.remove(patchInDeck);
	}


	/**
	 * getPlayerScores
	 * @return arraylist of playerscores
	 */
	public ArrayList<Integer> getPlayerScores()
	{

		ArrayList<Integer> playerScores = new ArrayList<>();

		//Calculate a score for each player board
		for(int i = 0; i < numPlayers; i++)
		{
			Board currentBoard = playerBoard.get(i);
			int playerScore = currentBoard.getGoalPatchScore();
			playerScore += currentBoard.playerScore.getPlayerScore(cats);
			playerScores.add(playerScore);
		}

		return playerScores;
	}

	/**
	 * get cats array
	 * @return cats array
	 */
	public Cat[] getCats() {
		return cats;
	}
}
