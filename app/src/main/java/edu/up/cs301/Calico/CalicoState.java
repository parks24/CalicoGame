package edu.up.cs301.Calico;

import java.util.ArrayList;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the Calico.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class CalicoState extends GameState {

	//Instance Variables
	protected ArrayList<Integer> playerScores = new ArrayList<>();

	protected boolean objectiveMenuVisibility;
	protected int playerTurn;
	protected int turnStage; //Player selecting or placing during turn
	protected int gameStage; //Stage of game

	protected Patch[] communityPool = new Patch[3];
	protected Patch[][] playerHand = new Patch[4][2];
	protected ArrayList<Patch> deck = new ArrayList<>();
	protected ArrayList<Board> playerBoard = new ArrayList<>();

	protected int[] lastPatchPlacement = {0,0}; //x,y coords of last patch placement for Undo Move

	public CalicoState()
	{
		//initialize player scores to 0 for each player
		playerScores.add(0);
		playerScores.add(0);
		playerScores.add(0);
		playerScores.add(0);

		//start of game stage of game values
		playerTurn = 0;
		turnStage = 0;
		gameStage = 0;

		//initialize community pool to default patches
		communityPool[0] = new Patch();
		communityPool[1] = new Patch();
		communityPool[2] = new Patch();

		//Initialize player hands to default patches
		for (int i = 0; i<4; i ++)
		{
			for (int j = 0; j<2; j++)
			{
				playerHand[i][j] = new Patch();
			}
		}

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

		//add a new board for each player
		playerBoard.add(new Board());
		playerBoard.add(new Board());
		playerBoard.add(new Board());
		playerBoard.add(new Board());

	}//default Constructor

	/** Copy Constructor
	 *
	 * @param other object to be copied
	 */
	public CalicoState(CalicoState other)
	{
		//copy player scores
        this.playerScores.addAll(other.playerScores);

		//start of game stage of game values
		this.playerTurn = other.playerTurn;
		this.turnStage = other.turnStage;
		this.gameStage = other.gameStage;

		//initialize community pool to default patches
		this.communityPool[0] = new Patch(other.communityPool[0]);
		this.communityPool[1] = new Patch(other.communityPool[0]);
		this.communityPool[2] = new Patch(other.communityPool[0]);

		//copy player hands
		for (int i = 0; i<4; i ++)
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
	}

	public boolean confirmMove(GameAction move)
	{
		if(move instanceof ConfirmMove)
		{

			//Check for player four's turn
			if(playerTurn == 3)
			{
				playerTurn = 0;
			}

			//Move onto next player
			else
			{
				playerTurn++;
			}

			return true;
		}

		return false;
	}//confirmMove

	public boolean undoMove(GameAction move)
	{
		if(move instanceof UndoMove)
		{
			//Get players board
			Board currentBoard = playerBoard.get(playerTurn);

			//Set last placed patch to empty
			Patch emptyPatch = new Patch();
			currentBoard.setPatch(emptyPatch, lastPatchPlacement[0], lastPatchPlacement[1]);

			return true;
		}

		return false;
	}//undoMove

	public boolean placePatch(GameAction move)
	{
		if(move instanceof PlacePatch)
		{
			move = (PlacePatch) move; //Cast move to PlacePatch type for method use

			//Get players board
			Board currentBoard = playerBoard.get(playerTurn);

			//Get selected row col position on board
			int selectedRow = ((PlacePatch) move).getBoardRow();
			int selectedCol = ((PlacePatch) move).getBoardCol();

			//Get selected patch on board
			Patch selectedMove = currentBoard.getPatch(selectedRow, selectedCol);
			//Get selected patch from player inventory
			Patch selectedPatch = ((PlacePatch) move).getSelectedPatch();


			//Check to make sure the selected patch on board is empty
			if(selectedMove.getPatchPattern() == 0)
			{
				//Make the move
				currentBoard.setPatch(selectedPatch, selectedRow, selectedCol);
				return true;
			}

			//Store x,y coordinates of patch placement
			lastPatchPlacement[0] = selectedRow;
			lastPatchPlacement[1] = selectedCol;
		}

		return false;
	}//placePatch

	public boolean selectPatch(GameAction move)
	{
		if(move instanceof SelectPatch)
		{
			playerHand[playerTurn][0].selectPatch(); //Select Patch from Hand
			return true;
		}

		return false;

	}//selectPatch

	public boolean closeMenu(GameAction move)
	{
		if(move instanceof CloseMenu)
		{
			objectiveMenuVisibility = false;
			return true;
		}

		return false;
	}

	public boolean viewPlayerBoard(GameAction move)
	{
		if(move instanceof ViewPlayerBoard)
		{
			//Get players board
			Board currentBoard = playerBoard.get(playerTurn);
			currentBoard.setView(); //Update Current View to Board
			return true;
		}

		return false;
	}//viewPlayerBoard

	public boolean viewObjectives(GameAction move)
	{
		if(move instanceof ViewObjectives)
		{
			objectiveMenuVisibility = true;
			return true;
		}

		return false;
	}




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
		for(int i = 0; i < playerScores.size(); i++)
		{
			int playerNumber = i+1; //Index by one for accurate player number (1-4 not 0-3)
			String playerScoreTemp = "Player " + playerNumber + " : " + playerScores.get(i) + "\n";
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
		String formattedInfo = turnInfo + currentScores + communityPoolInfo + playerHandInfo;
		return formattedInfo;
	}//toString
}
