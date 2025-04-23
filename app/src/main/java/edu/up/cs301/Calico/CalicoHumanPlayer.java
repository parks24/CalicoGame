package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.Serializable;

/**
 * A GUI of a Calico-player. The class handles user interaction with the UI
 * sending GameActions to update the GameState accordingly.

 * @author Brian Parks
 * @author Joseph Early
 * @version April 2025
 */
public class CalicoHumanPlayer extends GameHumanPlayer implements OnClickListener, Serializable {

	/* instance variables */

	private TextView testResultsTextView; // The TextView the displays the current Calico value
	private CalicoState state; // the most recent game state, as given to us by the CounterLocalGame
	private int displayNum; // indicates the players number for assessing if it is their turn
	private GameMainActivity myActivity; // the android activity that we are running
	private boolean objectiveMenuVisibility; //True if menu is open, false if closed


	//Board Buttons

	//Row 0
	protected ImageView board00 = null;
	protected ImageView board01 = null;
	protected ImageView board02 = null;
	protected ImageView board03 = null;
	protected ImageView board04 = null;
	protected ImageView board05 = null;

	//Row 1
	protected ImageView board10 = null;
	protected ImageView board11 = null;
	protected ImageView board12 = null;
	protected ImageView board13 = null;
	protected ImageView board14 = null;
	protected ImageView board15 = null;
	protected ImageView board16 = null;

	//Row 2
	protected ImageView board20 = null;
	protected ImageView board21 = null;
	protected ImageView board22 = null;
	protected ImageView objectiveTop = null;
	protected ImageView board24 = null;
	protected ImageView board25 = null;
	protected ImageView board26 = null;

	//Row 3
	protected ImageView board30 = null;
	protected ImageView board31 = null;
	protected ImageView objectiveMiddle = null;
	protected ImageView board33 = null;
	protected ImageView board34 = null;
	protected ImageView board35 = null;
	protected ImageView board36 = null;

	//Row 4
	protected ImageView board40 = null;
	protected ImageView board41 = null;
	protected ImageView board42 = null;
	protected ImageView board43 = null;
	protected ImageView objectiveBottom = null;
	protected ImageView board45 = null;
	protected ImageView board46 = null;

	//Row 5
	protected ImageView board50 = null;
	protected ImageView board51 = null;
	protected ImageView board52 = null;
	protected ImageView board53 = null;
	protected ImageView board54 = null;
	protected ImageView board55 = null;
	protected ImageView board56 = null;

	//Row 6
	protected ImageView board60 = null;
	protected ImageView board61 = null;
	protected ImageView board62 = null;
	protected ImageView board63 = null;
	protected ImageView board64 = null;
	protected ImageView board65 = null;

	//Cats
	protected ImageView cat1Pattern1 = null;
	protected ImageView cat1Pattern2 = null;
	protected ImageView cat2Pattern1 = null;
	protected ImageView cat2Pattern2 = null;
	protected ImageView cat3Pattern1 = null;
	protected ImageView cat3Pattern2 = null;

	protected TextView catCount1 = null;
	protected TextView catCount2 = null;
	protected TextView catCount3 = null;
	protected TextView buttonCount = null;

	//Community Pool Patches
	protected ImageView commonTile1 = null;
	protected ImageView commonTile2 = null;
	protected ImageView commonTile3 = null;

	//Player Inventory Patches
	protected ImageView playerTile1 = null;
	protected ImageView playerTile2 = null;

	protected ImageView confirm = null;
	protected ImageView undo = null;

	//View Player Buttons
	protected Button viewP1 = null;
	protected Button viewP2 = null;
	protected Button viewP3 = null;
	protected Button viewP4 = null;

	protected Button openObjectives = null;
	protected Button closeObjectives = null;


	/**
	 * Constructor for CalicoHumanPlayer.
	 *
	 * @param name the player's name
	 */
	public CalicoHumanPlayer(String name)
	{
		super(name);
		displayNum = 0;
		objectiveMenuVisibility = false;
	}

	/**
	 * Overloaded constructor that takes a specific player number
	 *
	 * @param name       the player's name
	 * @param _playerNum the player's number
	 */
	public CalicoHumanPlayer(String name, int _playerNum)
	{
		super(name);
		playerNum = _playerNum;
		displayNum = 0;
		objectiveMenuVisibility = false;
	}

	/**
	 * Returns the GUI's top view object
	 * 
	 * @return
	 * 		the top object in the GUI's view heirarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.top_gui_layout);
	}
	
	/**
	 * Updates the display elements on the GUI to reflect the current game state.
	 */
	protected void updateDisplay() {

		Log.i("Update Display", "Update Display Called. DisplayNum = "+displayNum);

		int[][] patchAsset =
			{{R.drawable.red_dots, R.drawable.red_fract, R.drawable.red_heart,
			R.drawable.red_lines, R.drawable.red_smile, R.drawable.red_star},
			{R.drawable.orange_dots, R.drawable.orange_fract, R.drawable.orange_heart,
			R.drawable.orange_lines, R.drawable.orange_smile, R.drawable.orange_star},
			{R.drawable.yellow_dots, R.drawable.yellow_fract, R.drawable.yellow_heart,
			R.drawable.yellow_lines, R.drawable.yellow_smile, R.drawable.yellow_star},
			{R.drawable.green_dots, R.drawable.green_fract, R.drawable.green_heart,
			R.drawable.green_lines, R.drawable.green_smile, R.drawable.green_star},
			{R.drawable.blue_dots, R.drawable.blue_fract, R.drawable.blue_heart,
			R.drawable.blue_lines, R.drawable.blue_smile, R.drawable.blue_star},
			{R.drawable.pink_dots, R.drawable.pink_fract, R.drawable.pink_heart,
			R.drawable.pink_lines, R.drawable.pink_smile, R.drawable.pink_star}};

		int[] goalPatchAsset =
				{R.drawable.blank_goal,
				R.drawable.noteq_goal, R.drawable.two3kind_goal, R.drawable.three2kind_goal,
				R.drawable.fourkind_goal, R.drawable.threekind_goal, R.drawable.twokind_goal};

		// updating display of the game board
		ImageView[][] boardViews =
			{{board00,board01,board02,board03,board04,board05,null},
			{board10,board11,board12,board13,board14,board15,board16},
			{board20,board21,board22,objectiveTop,board24,board25,board26},
			{board30,board31,objectiveMiddle,board33,board34,board35,board36},
			{board40,board41,board42,board43,objectiveBottom,board45,board46},
			{board50,board51,board52,board53,board54,board55,board56},
			{board60,board61,board62,board63,board64,board65,null}};

		int i = 0;
		for (Patch[] row : state.playerBoard.get(displayNum).board) {
			int j = 0;
			for (Patch col : row) {
				if (col != null) {
//					Log.i("patch_info", col.toString());
					if (col.getPatchPattern() == 7){
						if (boardViews[i][j] != null) {
							boardViews[i][j].setImageResource(goalPatchAsset[col.getGoal()]);
						}
					}else if (col.getPatchColor() == 0){
						if (boardViews[i][j] != null) {
							boardViews[i][j].setImageResource(R.drawable.blank_blank);
						}
					}else{
						if (boardViews[i][j] != null) {
							boardViews[i][j].setImageResource(patchAsset[col.getPatchColor() - 1][col.getPatchPattern() - 1]);
						}
					}
				}
				j++;
			}
			i++;
		}

		// updating the display of the community patches
		ImageView[] communityViews = {commonTile1, commonTile2, commonTile3};

		i = 0;
		for ( Patch patch : state.communityPool) {
			if (communityViews[i] != null) {
				if (patch.getPatchColor() == 0){
					communityViews[i].setImageResource(R.drawable.blank_blank);
				} else {
					communityViews[i].setImageResource(patchAsset[patch.getPatchColor() - 1][patch.getPatchPattern() - 1]);
				}
				communityViews[i].setBackgroundColor(Color.LTGRAY);
			}
			i++;
		}

		// updating the display of the player hand patches
		ImageView[] handViews = {playerTile1, playerTile2};

		i = 0;
		for ( Patch patch : state.playerHand[displayNum]) {
			if (handViews[i] != null) {
				if (patch.getPatchColor() == 0){
					handViews[i].setImageResource(R.drawable.blank_blank);
				} else {
					handViews[i].setImageResource(patchAsset[patch.getPatchColor() - 1][patch.getPatchPattern() - 1]);
				}
			}
			i++;
		}
		i = 0;
		for (ImageView view : handViews) {
			if (i == state.selectedSlot){
				view.setBackgroundColor(Color.GREEN);
			}else{
				view.setBackgroundColor(Color.LTGRAY);
			}
			i++;
		}

		// updating the display of the cats and buttons
		ImageView[][] catPatchViews =
				{{cat1Pattern1,cat1Pattern2},
				{cat2Pattern1,cat2Pattern2},
				{cat3Pattern1,cat3Pattern2}};
		int[] catPatchAssets =
				{R.drawable.blank_dots,R.drawable.blank_fract,R.drawable.blank_heart,
				R.drawable.blank_lines,R.drawable.blank_smile,R.drawable.blank_star,};

		i=0;
		for (Cat cat : state.getCats()) {
			int j=0;
			for (int pattern : cat.getPatterns()) {
				if (catPatchViews[i][j] != null) {
					catPatchViews[i][j].setImageResource(catPatchAssets[pattern-1]);
				}
				j++;
			}
			i++;
		}


		TextView[] catViews = {catCount1, catCount2, catCount3};
		String[] cats = {"Cuddles: ","Smokey: ","Stripe: "};

		i = 0;
		for ( int numCat : state.playerBoard.get(displayNum).playerScore.catCount) {
			String text = cats[i] + numCat;
			if (catViews[i] != null) {
				catViews[i].setText(text);
			}
			i++;
		}

		int sum = 0;
		for (int num : state.playerBoard.get(displayNum).playerScore.buttonCount) {
			sum += num;
		}
		String text = "Buttons: " + sum;
		if (buttonCount != null) {
			buttonCount.setText(text);
		}

		Button[] playerViews = {viewP1,viewP2,viewP3,viewP4};

		for (int j = 0; j < 4; j++) {
			if (playerNum == j) {
					playerViews[j].setBackgroundColor(Color.GREEN);
			}else if (displayNum == j){
				playerViews[j].setBackgroundColor(Color.YELLOW);
			}else {
				playerViews[j].setBackgroundColor(Color.LTGRAY);
			}
		}
	}//updateDisplay

	/**
	 *  Handles user interaction with the UI determining move validity with
	 *  CalicoState's turnStage and updating display accordingly.
	 *
	 * @param button
	 * 		the button that was clicked
	 */
	public void onClick(View button)
	{
		// if we are not yet connected to a game, ignore
		if (game == null) return;

		//display player boards
		//displayNum = Board to display
		//updateDisplay is then called.
		if (button.getId() == R.id.player1) {
			displayNum = 0;
			updateDisplay();
		} else if (button.getId() == R.id.player2 && state.numPlayers>=2) {
			displayNum = 1;
			updateDisplay();
		} else if (button.getId() == R.id.player3 && state.numPlayers>=3) {
			displayNum = 2;
			updateDisplay();
		} else if (button.getId() == R.id.player4 && state.numPlayers>=4) {
			displayNum = 3;
			updateDisplay();
		}

		//objectives menu
		if (button.getId() == R.id.objectives) {
			objectiveMenuVisibility = true;
			setAsGui(myActivity);
		} else if (button.getId() == R.id.closeMenu) {
			objectiveMenuVisibility = false;
			setAsGui(myActivity);
			updateDisplay();
		}

		// Board space buttons
		if (playerNum == state.playerTurn && displayNum == playerNum) {


			if(state.turnStage==1) {

				if (button.getId() == R.id.board11) {
					game.sendAction(new PlacePatch(this, 1, 1));
				} else if (button.getId() == R.id.board12) {
					game.sendAction(new PlacePatch(this, 1, 2));
				} else if (button.getId() == R.id.board13) {
					game.sendAction(new PlacePatch(this, 1, 3));
				} else if (button.getId() == R.id.board14) {
					game.sendAction(new PlacePatch(this, 1, 4));
				} else if (button.getId() == R.id.board15) {
					game.sendAction(new PlacePatch(this, 1, 5));
				} else if (button.getId() == R.id.board21) {
					game.sendAction(new PlacePatch(this, 2, 1));
				} else if (button.getId() == R.id.board22) {
					game.sendAction(new PlacePatch(this, 2, 2));
				} else if (button.getId() == R.id.board24) {
					game.sendAction(new PlacePatch(this, 2, 4));
				} else if (button.getId() == R.id.board25) {
					game.sendAction(new PlacePatch(this, 2, 5));
				} else if (button.getId() == R.id.board31) {
					game.sendAction(new PlacePatch(this, 3, 1));
				} else if (button.getId() == R.id.board33) {
					game.sendAction(new PlacePatch(this, 3, 3));
				} else if (button.getId() == R.id.board34) {
					game.sendAction(new PlacePatch(this, 3, 4));
				} else if (button.getId() == R.id.board35) {
					game.sendAction(new PlacePatch(this, 3, 5));
				} else if (button.getId() == R.id.board41) {
					game.sendAction(new PlacePatch(this, 4, 1));
				} else if (button.getId() == R.id.board42) {
					game.sendAction(new PlacePatch(this, 4, 2));
				} else if (button.getId() == R.id.board43) {
					game.sendAction(new PlacePatch(this, 4, 3));
				} else if (button.getId() == R.id.board45) {
					game.sendAction(new PlacePatch(this, 4, 5));
				} else if (button.getId() == R.id.board51) {
					game.sendAction(new PlacePatch(this, 5, 1));
				} else if (button.getId() == R.id.board52) {
					game.sendAction(new PlacePatch(this, 5, 2));
				} else if (button.getId() == R.id.board53) {
					game.sendAction(new PlacePatch(this, 5, 3));
				} else if (button.getId() == R.id.board54) {
					game.sendAction(new PlacePatch(this, 5, 4));
				} else if (button.getId() == R.id.board55) {
					game.sendAction(new PlacePatch(this, 5, 5));
				}
			}

			//check for select patch from player hand stage of turn
			if(state.turnStage == 0 || state.turnStage == 1) {

				// player patch buttons
				if (button.getId() == R.id.playerTile1) {
					game.sendAction(new SelectPatch(this, 0));
					Log.i("turnStage","Selecting Player Tile 0");
				} else if (button.getId() == R.id.playerTile2) {
					game.sendAction(new SelectPatch(this, 1));
					   Log.i("turnStage","Selecting Player Tile 1");
				}
			}

			//Check for select community patch stage of turn
			else if(state.turnStage == 2) {

				// community patch buttons
				 if (button.getId() == R.id.commonTile1) {
					game.sendAction(new SelectCommunityPatch(this, 0));
				} else if (button.getId() == R.id.commonTile2) {
					game.sendAction(new SelectCommunityPatch(this, 1));
				} else if (button.getId() == R.id.commonTile3) {
					game.sendAction(new SelectCommunityPatch(this, 2));
				}

			}

			// Confirm Move
			if(state.turnStage == 3)
			{
				if (button.getId() == R.id.confirm)
				{
					game.sendAction(new ConfirmMove(this));
				}
			}

			//Undo Move
			if (button.getId() == R.id.undo)
			{
				game.sendAction(new UndoMove(this));
			}

		}
		// change displayed board

	}// onClick
	
	/**
	 * callback method when we get a message (e.g., from the game)
	 * 
	 * @param info
	 * 		the message
	 */
	@Override
	public void receiveInfo(GameInfo info) {
		// ignore the message if it's not a CalicoState message
		if (!(info instanceof CalicoState)) return;

		// update our state; then update the display
		this.state = (CalicoState)info;
		this.displayNum = state.playerTurn;
		Log.i("PlayerTurn: ", String.valueOf(state.playerTurn));
		updateDisplay();
	}
	
	/**
	 * callback method--our game has been chosen/rechosen to be the GUI,
	 * called from the GUI thread
	 * 
	 * @param activity
	 * 		the activity under which we are running
	 */
	public void setAsGui(GameMainActivity activity) {
		
		// remember the activity
		this.myActivity = activity;

		if (objectiveMenuVisibility) {
			activity.setContentView(R.layout.calico_objectives_menu);
		} else {
			// Load the layout resource for our GUI
			activity.setContentView(R.layout.calico_human_player);
		}
		setListeners(objectiveMenuVisibility);
	}

	/**
	 * Initializes all view references and registers OnClickListeners.
	 *
	 * @param objView Menu visibility (True if open, false if closed)
	 */
	private void setListeners(boolean objView) {
		if (objView)
		{
			this.closeObjectives = (Button) myActivity.findViewById(R.id.closeMenu);
			closeObjectives.setOnClickListener(this);
		}

		else
		{
			//Register Board
			//Row 0
			this.board00 = (ImageView) myActivity.findViewById(R.id.board00);
			this.board01 = (ImageView) myActivity.findViewById(R.id.board01);
			this.board02 = (ImageView) myActivity.findViewById(R.id.board02);
			this.board03 = (ImageView) myActivity.findViewById(R.id.board03);
			this.board04 = (ImageView) myActivity.findViewById(R.id.board04);
			this.board05 = (ImageView) myActivity.findViewById(R.id.board05);
			board00.setOnClickListener(this);
			board01.setOnClickListener(this);
			board02.setOnClickListener(this);
			board03.setOnClickListener(this);
			board04.setOnClickListener(this);
			board05.setOnClickListener(this);

			//Row 1
			this.board10 = (ImageView) myActivity.findViewById(R.id.board10);
			this.board11 = (ImageView) myActivity.findViewById(R.id.board11);
			this.board12 = (ImageView) myActivity.findViewById(R.id.board12);
			this.board13 = (ImageView) myActivity.findViewById(R.id.board13);
			this.board14 = (ImageView) myActivity.findViewById(R.id.board14);
			this.board15 = (ImageView) myActivity.findViewById(R.id.board15);
			this.board16 = (ImageView) myActivity.findViewById(R.id.board16);
			board10.setOnClickListener(this);
			board11.setOnClickListener(this);
			board12.setOnClickListener(this);
			board13.setOnClickListener(this);
			board14.setOnClickListener(this);
			board15.setOnClickListener(this);
			board16.setOnClickListener(this);

			//Row 2
			this.board20 = (ImageView) myActivity.findViewById(R.id.board20);
			this.board21 = (ImageView) myActivity.findViewById(R.id.board21);
			this.board22 = (ImageView) myActivity.findViewById(R.id.board22);
			this.objectiveTop = (ImageView) myActivity.findViewById(R.id.objectiveTop); //Top Goal Patch
			this.board24 = (ImageView) myActivity.findViewById(R.id.board24);
			this.board25 = (ImageView) myActivity.findViewById(R.id.board25);
			this.board26 = (ImageView) myActivity.findViewById(R.id.board26);
			board20.setOnClickListener(this);
			board21.setOnClickListener(this);
			board22.setOnClickListener(this);
			objectiveTop.setOnClickListener(this);
			board24.setOnClickListener(this);
			board25.setOnClickListener(this);
			board26.setOnClickListener(this);

			//Row 3
			this.board30 = (ImageView) myActivity.findViewById(R.id.board30);
			this.board31 = (ImageView) myActivity.findViewById(R.id.board31);
			this.objectiveMiddle = (ImageView) myActivity.findViewById(R.id.objectiveMiddle); //Middle Goal Patch
			this.board33 = (ImageView) myActivity.findViewById(R.id.board33);
			this.board34 = (ImageView) myActivity.findViewById(R.id.board34);
			this.board35 = (ImageView) myActivity.findViewById(R.id.board35);
			this.board36 = (ImageView) myActivity.findViewById(R.id.board36);
			board30.setOnClickListener(this);
			board31.setOnClickListener(this);
			objectiveMiddle.setOnClickListener(this);
			board33.setOnClickListener(this);
			board34.setOnClickListener(this);
			board35.setOnClickListener(this);
			board36.setOnClickListener(this);

			//Row 4
			this.board40 = (ImageView) myActivity.findViewById(R.id.board40);
			this.board41 = (ImageView) myActivity.findViewById(R.id.board41);
			this.board42 = (ImageView) myActivity.findViewById(R.id.board42);
			this.board43 = (ImageView) myActivity.findViewById(R.id.board43);
			this.objectiveBottom = (ImageView) myActivity.findViewById(R.id.objectiveBottom);
			this.board45 = (ImageView) myActivity.findViewById(R.id.board45);
			this.board46 = (ImageView) myActivity.findViewById(R.id.board46);
			board40.setOnClickListener(this);
			board41.setOnClickListener(this);
			board42.setOnClickListener(this);
			board43.setOnClickListener(this);
			objectiveBottom.setOnClickListener(this);
			board45.setOnClickListener(this);
			board46.setOnClickListener(this);

			//Row 5
			this.board50 = (ImageView) myActivity.findViewById(R.id.board50);
			this.board51 = (ImageView) myActivity.findViewById(R.id.board51);
			this.board52 = (ImageView) myActivity.findViewById(R.id.board52);
			this.board53 = (ImageView) myActivity.findViewById(R.id.board53);
			this.board54 = (ImageView) myActivity.findViewById(R.id.board54);
			this.board55 = (ImageView) myActivity.findViewById(R.id.board55);
			this.board56 = (ImageView) myActivity.findViewById(R.id.board56);
			board50.setOnClickListener(this);
			board51.setOnClickListener(this);
			board52.setOnClickListener(this);
			board53.setOnClickListener(this);
			board54.setOnClickListener(this);
			board55.setOnClickListener(this);
			board56.setOnClickListener(this);

			//Row 6
			this.board60 = (ImageView) myActivity.findViewById(R.id.board60);
			this.board61 = (ImageView) myActivity.findViewById(R.id.board61);
			this.board62 = (ImageView) myActivity.findViewById(R.id.board62);
			this.board63 = (ImageView) myActivity.findViewById(R.id.board63);
			this.board64 = (ImageView) myActivity.findViewById(R.id.board64);
			this.board65 = (ImageView) myActivity.findViewById(R.id.board65);
			board60.setOnClickListener(this);
			board61.setOnClickListener(this);
			board62.setOnClickListener(this);
			board63.setOnClickListener(this);
			board64.setOnClickListener(this);
			board65.setOnClickListener(this);


			//Community Pool
			this.commonTile1 = (ImageView) myActivity.findViewById(R.id.commonTile1);
			this.commonTile2 = (ImageView) myActivity.findViewById(R.id.commonTile2);
			this.commonTile3 = (ImageView) myActivity.findViewById(R.id.commonTile3);
			commonTile1.setOnClickListener(this);
			commonTile2.setOnClickListener(this);
			commonTile3.setOnClickListener(this);

			//Player Inventory
			this.playerTile1 = (ImageView) myActivity.findViewById(R.id.playerTile1);
			this.playerTile2 = (ImageView) myActivity.findViewById(R.id.playerTile2);
			playerTile1.setOnClickListener(this);
			playerTile2.setOnClickListener(this);

			//Cat patterns
			this.cat1Pattern1 = (ImageView) myActivity.findViewById(R.id.cat1pattern1);
			this.cat1Pattern2 = (ImageView) myActivity.findViewById(R.id.cat1pattern2);
			this.cat2Pattern1 = (ImageView) myActivity.findViewById(R.id.cat2pattern1);
			this.cat2Pattern2 = (ImageView) myActivity.findViewById(R.id.cat2pattern2);
			this.cat3Pattern1 = (ImageView) myActivity.findViewById(R.id.cat3pattern1);
			this.cat3Pattern2 = (ImageView) myActivity.findViewById(R.id.cat3pattern2);


			//Cat and Button count
			this.catCount1 = (TextView) myActivity.findViewById(R.id.catCount1);
			this.catCount2 = (TextView) myActivity.findViewById(R.id.catCount2);
			this.catCount3 = (TextView) myActivity.findViewById(R.id.catCount3);
			this.buttonCount = (TextView) myActivity.findViewById(R.id.buttonCount);

			//Confirm and undo buttons
			this.confirm = (ImageView) myActivity.findViewById((R.id.confirm));
			this.undo = (ImageView) myActivity.findViewById((R.id.undo));
			confirm.setOnClickListener(this);
			undo.setOnClickListener(this);

			//Player board buttons
			this.viewP1 = (Button) myActivity.findViewById(R.id.player1);
			this.viewP2 = (Button) myActivity.findViewById(R.id.player2);
			this.viewP3 = (Button) myActivity.findViewById(R.id.player3);
			this.viewP4 = (Button) myActivity.findViewById(R.id.player4);
			viewP1.setOnClickListener(this);
			viewP2.setOnClickListener(this);
			viewP3.setOnClickListener(this);
			viewP4.setOnClickListener(this);

			//Objective menu stuff
			this.openObjectives = (Button) myActivity.findViewById(R.id.objectives);
			openObjectives.setOnClickListener(this);
		}
	}


	public CalicoState getState() {
		return state;
	}

	public void setState(CalicoState state) {
		this.state = state;
	}

}//CalicoHumanPlayer

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */