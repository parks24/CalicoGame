package edu.up.cs301.Calico;

import java.util.ArrayList;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.gameConfiguration.*;

/**
 * The primary activity for Calico.
 * This class handles default configuration and starts the local game.
 *
 * @author Luca Sburlino
 * @version April 2025
 */
public class CalicoMainActivity extends GameMainActivity {
	
	// the port number that this game will use when playing over the network
	private static final int PORT_NUMBER = 2234;

	/**
	 * Creates the default configuration for Calico:
	 *
	 * - 1-4 Players available
	 * - Smart and Dumb AI
	 * - Network Play availability
	 * 
	 * @return
	 * 		the new configuration object, representing the default configuration
	 */
	@Override
	public GameConfig createDefaultConfig() {
		
		// Define the allowed player types
		ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
		
		// a human player player type (player type 0)
		playerTypes.add(new GamePlayerType("Local Human Player") {
			public GamePlayer createPlayer(String name) {
				return new CalicoHumanPlayer(name);
			}});
		
		// a computer player type (player type 1)
		playerTypes.add(new GamePlayerType("Dumb Computer") {
			public GamePlayer createPlayer(String name) {
				return new CalicoComputerPlayer1(name);
			}});

		// a computer player type (player type 1)
		playerTypes.add(new GamePlayerType("Smart Computer") {
			public GamePlayer createPlayer(String name) {
				return new CalicoComputerPlayer2(name);
			}});


		// Create a game configuration class for Counter:
		// - player types as given above
		// - from 1 to 4 players
		// - name of game is "Calico"
		// - port number as defined above
		GameConfig defaultConfig = new GameConfig(playerTypes, 1, 4, "Calico",
				PORT_NUMBER);

		// Add the default players to the configuration
		defaultConfig.addPlayer("Human", 0); // player 1: a human player
		defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
		defaultConfig.addPlayer("Computer", 1);
		defaultConfig.addPlayer("Computer", 1);

		// Set the default remote-player setup:
		// - player name: "Remote Player"
		// - IP code: (empty string)
		// - default player type: human player
		defaultConfig.setRemoteData("Remote Player", "", 0);
		
		// return the configuration
		return defaultConfig;
	}//createDefaultConfig

	/**
	 * create a local game
	 * 
	 * @return
	 * 		the local game, a Calico game
	 */
	@Override
	public LocalGame createLocalGame(GameState state) {
		if (state == null) state = new CalicoState(config.getNumPlayers());	//Fixed Constructor Call compilation 28/02/24
		return new CalicoLocalGame(state);
	}

}

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */
