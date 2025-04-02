package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI of a Calico-player. The GUI displays the current value of the Calico,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 * 
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the Calico-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public class CalicoHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */
	
	// The TextView the displays the current Calico value
	private TextView testResultsTextView;
	
	// the most recent game state, as given to us by the CounterLocalGame
	private CalicoState state;

	protected int playerNum;
	
	// the android activity that we are running
	private GameMainActivity myActivity;

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
	protected ImageView board23 = null;
	protected ImageView objectiveTop = null;
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

	protected TextView catCount1 = null;
	protected TextView catCount2 = null;
	protected TextView catCount3 = null;
	protected TextView buttonCount = null;

	protected ImageView commonTile1 = null;
	protected ImageView commonTile2 = null;
	protected ImageView commonTile3 = null;

	protected ImageView playerTile1 = null;
	protected ImageView playerTile2 = null;

	
	/**
	 * constructor
	 * @param name
	 * 		the player's name
	 */
	public CalicoHumanPlayer(String name)
	{
		super(name);
	}

	/**
	 * Returns the GUI's top view object
	 * 
	 * @return
	 * 		the top object in the GUI's view heirarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.top_test_layout);
	}
	
	/**
	 * sets the Calico value in the text view
	 */
	protected void updateDisplay() {
		// set the text in the appropriate widget
		//counterValueTextView.setText("" + state.getCounter());
	}

	/**
	 * this method gets called when the user clicks the '+' or '-' button. It
	 * creates a new CounterMoveAction to return to the parent activity.
	 * 
	 * @param button
	 * 		the button that was clicked
	 */
	public void onClick(View button) {
		// if we are not yet connected to a game, ignore
		if (game == null) return;

		if(playerNum == state.playerTurn)
		{
			if(state.turnStage == 0)
			{
				//check id for inventory
			}

			else if(state.turnStage == 1)
			{
				//check id for board
			}

			else if(state.turnStage == 2)
			{
				//check id for confirm
			}

			else if(state.turnStage == 3)
			{
				//check id for community pool
			}

			else if(state.turnStage == 4)
			{
				//check id for confirm
			}

		}



	}// onClick
	
	/**
	 * callback method when we get a message (e.g., from the game)
	 * 
	 * @param info
	 * 		the message
	 */
	@Override
	public void receiveInfo(GameInfo info) {
		// ignore the message if it's not a CounterState message
		if (!(info instanceof CalicoState)) return;
		
		// update our state; then update the display
		this.state = (CalicoState)info;
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
		
	    // Load the layout resource for our GUI
		activity.setContentView(R.layout.game_state_test);

		Button testButton = (Button) activity.findViewById(R.id.testButton);
		testButton.setOnClickListener(this);

		this.testResultsTextView = (TextView) activity.findViewById(R.id.testResultsTextView);


		//Register Board
		//Row 0
		this.board00 = (ImageView) activity.findViewById(R.id.board00);
		this.board01 = (ImageView) activity.findViewById(R.id.board01);
		this.board02 = (ImageView) activity.findViewById(R.id.board02);
		this.board03 = (ImageView) activity.findViewById(R.id.board03);
		this.board04 = (ImageView) activity.findViewById(R.id.board04);
		this.board05 = (ImageView) activity.findViewById(R.id.board05);

		//Row 1
		this.board10 = (ImageView) activity.findViewById(R.id.board10);
		this.board11 = (ImageView) activity.findViewById(R.id.board11);
		this.board12 = (ImageView) activity.findViewById(R.id.board12);
		this.board13 = (ImageView) activity.findViewById(R.id.board13);
		this.board14 = (ImageView) activity.findViewById(R.id.board14);
		this.board15 = (ImageView) activity.findViewById(R.id.board15);
		this.board16 = (ImageView) activity.findViewById(R.id.board16);

		//Row 2
		this.board20 = (ImageView) activity.findViewById(R.id.board20);
		this.board21 = (ImageView) activity.findViewById(R.id.board21);
		this.board22 = (ImageView) activity.findViewById(R.id.board22);
		this.board23 = (ImageView) activity.findViewById(R.id.board23);
		this.objectiveTop = (ImageView) activity.findViewById(R.id.objectiveTop); //Top Goal Patch
		this.board25 = (ImageView) activity.findViewById(R.id.board25);
		this.board26 = (ImageView) activity.findViewById(R.id.board26);

		//Row 3
		this.board30 = (ImageView) activity.findViewById(R.id.board30);
		this.board31 = (ImageView) activity.findViewById(R.id.board31);
		this.objectiveMiddle = (ImageView) activity.findViewById(R.id.objectiveMiddle); //Middle Goal Patch
		this.board33 = (ImageView) activity.findViewById(R.id.board33);
		this.board34 = (ImageView) activity.findViewById(R.id.board34);
		this.board35 = (ImageView) activity.findViewById(R.id.board35);
		this.board36 = (ImageView) activity.findViewById(R.id.board36);

		//Row 4
		this.board40 = (ImageView) activity.findViewById(R.id.board40);
		this.board41 = (ImageView) activity.findViewById(R.id.board41);
		this.board42 = (ImageView) activity.findViewById(R.id.board42);
		this.board43 = (ImageView) activity.findViewById(R.id.board43);
		this.objectiveBottom = (ImageView) activity.findViewById(R.id.objectiveBottom);
		this.board45 = (ImageView) activity.findViewById(R.id.board45);
		this.board46 = (ImageView) activity.findViewById(R.id.board46);

		//Row 5
		this.board50 = (ImageView) activity.findViewById(R.id.board50);
		this.board51 = (ImageView) activity.findViewById(R.id.board51);
		this.board52 = (ImageView) activity.findViewById(R.id.board52);
		this.board53 = (ImageView) activity.findViewById(R.id.board53);
		this.board54 = (ImageView) activity.findViewById(R.id.board54);
		this.board55 = (ImageView) activity.findViewById(R.id.board55);
		this.board56 = (ImageView) activity.findViewById(R.id.board56);

		//Row 6
		this.board60 = (ImageView) activity.findViewById(R.id.board60);
		this.board61 = (ImageView) activity.findViewById(R.id.board61);
		this.board62 = (ImageView) activity.findViewById(R.id.board62);
		this.board63 = (ImageView) activity.findViewById(R.id.board63);
		this.board64 = (ImageView) activity.findViewById(R.id.board64);
		this.board65 = (ImageView) activity.findViewById(R.id.board65);

		//Community Pool
		this.commonTile1 = (ImageView) activity.findViewById(R.id.commonTile1);
		this.commonTile2 = (ImageView) activity.findViewById(R.id.commonTile2);
		this.commonTile3 = (ImageView) activity.findViewById(R.id.commonTile3);

		//Player Inventory
		this.playerTile1 = (ImageView) activity.findViewById(R.id.playerTile1);
		this.playerTile2 = (ImageView) activity.findViewById(R.id.playerTile2);

		//Cat and Button count
		this.catCount1 = (TextView) activity.findViewById(R.id.catCount1);
		this.catCount2 = (TextView) activity.findViewById(R.id.catCount2);
		this.catCount3 = (TextView) activity.findViewById(R.id.catCount3);
		this.buttonCount = (TextView) activity.findViewById(R.id.buttonCount);


	}

	public CalicoState getState() {
		return state;
	}

	public void setState(CalicoState state) {
		this.state = state;
	}
}// class CalicoHumanPlayer

