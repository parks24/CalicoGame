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


		//Initialize board edges for all players
		initializeBoardEdges();

		//Initialize player boards
		initPlayerBoard(0); //Player One
		initPlayerBoard(1); //Player Two
		initPlayerBoard(2); //Player Three
		initPlayerBoard(3); //Player Four





	}//default Constructor

	public void initPlayerBoard(int player)
	{
		Board tempPlayer = playerBoard.get(player);

		//Row One
		tempPlayer.setPatch(new Patch(6,4),1,1); //1,1 (star, green)
		tempPlayer.setPatch(new Patch(6,3),1,2); //1,2 (star, yellow)
		tempPlayer.setPatch(new Patch(2,1),1,3); //1,3 (fract, red)

		if(player != 3) //Check for Player Four
		{
			tempPlayer.setPatch(new Patch(5,6),1,4); //1,4 (smile, pink)
		}

		//Row Two
		tempPlayer.setPatch(new Patch(3,6),2,1); //2,1
		tempPlayer.setPatch(new Patch(5, 3),2,2); //2,2
		tempPlayer.setPatch(new Patch(4, 1),2,3); //2,3
		tempPlayer.setPatch(new GoalPatch(),2,4); //2,4 GOAL
		tempPlayer.setPatch(new Patch(1,2),2,5); //2,5

		//Row Three
		tempPlayer.setPatch(new Patch(3, 4),3,1); //3,1 (,)
		tempPlayer.setPatch(new GoalPatch(),3,2); //3,2 GOAL
		tempPlayer.setPatch(new Patch(5,4),3,3); //3,3 (,)
		tempPlayer.setPatch(new Patch(5,6),3,4); //3,4 (,)
		tempPlayer.setPatch(new Patch(1,1),3,5); //3,5 (,)

		//Row Four
		tempPlayer.setPatch(new Patch(2,1),4,1); //4,1 (,)
		tempPlayer.setPatch(new Patch(4,5),4,2); //4,2 (,)
		tempPlayer.setPatch(new Patch(3,5),4,3); //4,3 (,)
		tempPlayer.setPatch(new GoalPatch(),4,4); //4,4 GOAL
		tempPlayer.setPatch(new Patch(3,6),4,5); //4,5 (,)

		//Row Five
		tempPlayer.setPatch(new Patch(2,5),5,1); //5,1 (,)
		tempPlayer.setPatch(new Patch(4,1),5,2); //5,2 (,)
		tempPlayer.setPatch(new Patch(4,2),5,3); //5,3 (,)
		tempPlayer.setPatch(new Patch(5,6),5,4); //5,4 (,)
		tempPlayer.setPatch(new Patch(3,1),5,5); //5,5 (,)

	}

	public void initializeBoardEdges()
	{
		//Initialize board edges
		for(int i = 0; i < 4; i++)
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
		}
	}//initializeBoardEdges

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
		return turnInfo + currentScores + communityPoolInfo + playerHandInfo;
	}//toString
}
