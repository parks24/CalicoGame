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

	protected int playerNumber;
	
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

	//Overloaded constructor
	public CalicoHumanPlayer(String name, int _playerNum)
	{
		super(name);
		playerNum = _playerNum;
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
	protected void updateDisplay()
	{




		// set the text in the appropriate widget
		//counterValueTextView.setText("" + state.getCounter());
	}//updateDisplay

	/**
	 *
	 * @param button
	 * 		the button that was clicked
	 */
	public void onClick(View button)
	{
		// if we are not yet connected to a game, ignore
		if (game == null) return;

		//If it is users turn, sent event to CalicoLocalGame
		if(state.getPlayerTurn() == playerNum)
		{

		}

	}// onClick
	
	/**
	 * callback method when we get a message (e.g., from the game)
	 * 
	 * @param info
	 * 		the message
	 */
	@Override
	public void receiveInfo(GameInfo info)
	{
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

	public CalicoState getState() {
		return state;
	}

	public void setState(CalicoState state) {
		this.state = state;
	}
}// class CalicoHumanPlayer

