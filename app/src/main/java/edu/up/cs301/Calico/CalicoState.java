package edu.up.cs301.Calico;

import java.util.ArrayList;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GameComputerPlayer;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the Calico.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class CalicoState extends GameState {

	//Instance Variables
    protected Cat[] cats = new Cat[3];

	protected boolean objectiveMenuVisibility;
	protected int playerTurn;
	protected int turnStage; //Player selecting or placing during turn
	/* 0 = Selecting patch
	 * 1 = Selecting where to place
	 * 2 = confirm move
	 * 3 = draw patch from community pool
	 * 4 = confirm/end turn
	 */
	protected int gameStage; //Stage of game
	/* 0 = placing goal tiles
	 * 1 = placing tiles
	 * 2 = board filled
	 */

	protected Patch[] communityPool = new Patch[3];
	protected Patch[][] playerHand = new Patch[4][2];
	protected ArrayList<Patch> deck = new ArrayList<>();
	protected ArrayList<Board> playerBoard = new ArrayList<>();
	protected Patch selectedPatch;
	protected Patch[][] preMovePlayerHand;
	protected Board preMoveBoard;


	public CalicoState()
	{

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


        //Chooses the cats to add
        //assigns random patterns to each cat
        ArrayList<Integer> patterns = new ArrayList<>();
		int[] patternsSent = new int[2];
        for (int i = 1; i<=6; i++) patterns.add(i);

		//adds 2 random of the 6 patterns to each cat
        for (int i = 0; i<3; i++)
        {
			patternsSent[0] = patterns.get((int) (Math.random() * (patterns.size()-1)));
			patternsSent[1] = patterns.get((int) (Math.random() * (patterns.size()-1)));
            cats[0] = new Cat(i+1, patternsSent);
        }



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
		tempPlayer.setPatch(new Patch(3, 4),3,1); //3,1
		tempPlayer.setPatch(new GoalPatch(),3,2); //3,2 GOAL
		tempPlayer.setPatch(new Patch(5,4),3,3); //3,3
		tempPlayer.setPatch(new Patch(5,6),3,4); //3,4
		tempPlayer.setPatch(new Patch(1,1),3,5); //3,5

		//Row Four
		tempPlayer.setPatch(new Patch(2,1),4,1); //4,1
		tempPlayer.setPatch(new Patch(4,5),4,2); //4,2
		tempPlayer.setPatch(new Patch(3,5),4,3); //4,3
		tempPlayer.setPatch(new GoalPatch(),4,4); //4,4 GOAL
		tempPlayer.setPatch(new Patch(3,6),4,5); //4,5

		//Row Five
		tempPlayer.setPatch(new Patch(2,5),5,1); //5,1
		tempPlayer.setPatch(new Patch(4,1),5,2); //5,2
		tempPlayer.setPatch(new Patch(4,2),5,3); //5,3
		tempPlayer.setPatch(new Patch(5,6),5,4); //5,4
		tempPlayer.setPatch(new Patch(3,1),5,5); //5,5

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
			playerTurn = (playerTurn +1) %4;

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


			return true;
		}

		return false;
	}//undoMove

	/** Places selectedPatch from player inventory to player board
	 *
	 * @param move gameAction
	 * @return
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
			if(selectedMove.getPatchPattern() == 0)
			{
				//Make the move
				currentBoard.setPatch(selectedPatch, selectedRow, selectedCol);
				return true;
			}

		}//placePatch

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

	public boolean computerMove(GameAction move)
	{
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
				if(playerHand[player][i].patchColor == ((CalicoMoveAction) move).getCommunityPatch().patchColor
				&& playerHand[player][i].patchPattern == ((CalicoMoveAction) move).getCommunityPatch().patchPattern)
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

			return true;
		}
		return false;
	}

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

	public boolean selectCommunityPatch(GameAction move)
	{
		if(move instanceof SelectCommunityPatch)
		{
			//ToDo implementation
			return true;
		}

		return false;
	}//selectCommunityPatch


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
				board.getSimilarPatchesPattern(similarPatchesPattern,patchToCheck,placedPatchPattern);


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
			int playerNumber = i+1; //Index by one for accurate player number (1-4 not 0-3)
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

	//Cycle deck
	//param is int of index of patch to get rid of
	private void drawNewCommunityPatch(int communityIndex)
	{
		int patchInDeck = 1 + (int)(Math.random() * (deck.size()));
		communityPool[communityIndex] = deck.get(patchInDeck);
		deck.remove(patchInDeck);
	}

	//Getters
	public int getPlayerTurn()
	{
		return playerTurn;
	}
}
