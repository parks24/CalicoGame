package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import android.view.View;
import android.widget.Button;
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
	
	// the android activity that we are running
	private GameMainActivity myActivity;
	
	/**
	 * constructor
	 * @param name
	 * 		the player's name
	 */
	public CalicoHumanPlayer(String name) {
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
		TextView textVeiw = myActivity.findViewById(R.id.testResultsTextView);
		textVeiw.setText("");

		//Deepcopy
		CalicoState firstInstance = new CalicoState();
		CalicoState firstCopy = new CalicoState(firstInstance);

		Patch patch1 = new Patch(3,6);
		Patch patch2 = new Patch(2,6);

		// turn 1 player 4
		game.sendAction(new SelectPatch(this,patch1));
		textVeiw.append("player 4 selects patch to place\n");
		game.sendAction(new PlacePatch(this,1,4));
		textVeiw.append("player 4 places patch on board\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 4 confirms placement\n");
		game.sendAction(new SelectCommunityPatch(this, patch2));
		textVeiw.append("player 4 selects a community tile to take\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 4 confirms selection\n");

		// turn 2 player 1
		game.sendAction(new SelectPatch(this, patch2));
		textVeiw.append("player 1 selects patch to place\n");
		game.sendAction(new PlacePatch(this,1,5));
		textVeiw.append("player 1 places patch on board\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 1 confirms placement\n");
		game.sendAction(new SelectCommunityPatch(this, patch1));
		textVeiw.append("player 1 selects a community tile to take\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 1 confirms selection\n");

		// turn 3 player 2
		game.sendAction(new SelectPatch(this, patch2));
		textVeiw.append("player 2 selects patch to place\n");
		game.sendAction(new PlacePatch(this,1,5));
		textVeiw.append("player 2 places patch on board\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 2 confirms placement\n");
		game.sendAction(new SelectCommunityPatch(this, patch1));
		textVeiw.append("player 2 selects a community tile to take\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 2 confirms selection\n");

		// turn 4 player 3
		game.sendAction(new SelectPatch(this, patch2));
		textVeiw.append("player 3 selects patch to place\n");
		game.sendAction(new PlacePatch(this,1,5));
		textVeiw.append("player 3 places patch on board\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 3 confirms placement\n");
		game.sendAction(new SelectCommunityPatch(this, patch1));
		textVeiw.append("player 3 selects a community tile to take\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 3 confirms selection\n");

		// turn 5 player 4
		game.sendAction(new SelectPatch(this, patch2));
		textVeiw.append("player 4 selects patch to place\n");
		game.sendAction(new PlacePatch(this,1,5));
		textVeiw.append("player 4 places patch on board\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 4 confirms placement\n");
		game.sendAction(new SelectCommunityPatch(this, patch1));
		textVeiw.append("player 4 selects a community tile to take\n");
		game.sendAction(new ConfirmMove(this));
		textVeiw.append("player 4 confirms selection\n");

		CalicoState secondInstance = new CalicoState();
		CalicoState secondCopy = new CalicoState(secondInstance);

		textVeiw.append("\n copy 1\n");
		textVeiw.append(firstCopy.toString());
		textVeiw.append("\n copy 2\n");
		textVeiw.append(secondCopy.toString());
		if (firstCopy.toString().equals(secondCopy.toString())){
			textVeiw.append("\nfirst and second copy are the same\n");
		}else{
			textVeiw.append("\nfirst and second copy are different\n");
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

	}

}// class CalicoHumanPlayer

