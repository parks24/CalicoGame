package edu.up.cs301.Calico;

import java.util.ArrayList;

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
	protected int playerTurn;
	protected int turnStage; //Player selecting or placing during turn
	protected int gameStage; //Stage of game

	protected Patch[] communityPool = new Patch[3];
	protected Patch[][] playerHand = new Patch[4][2];
	protected ArrayList<Patch> deck;
	protected ArrayList<Board> playerBoard = new ArrayList<>();

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

		//initilize player hands to default patches
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

	}

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
}
