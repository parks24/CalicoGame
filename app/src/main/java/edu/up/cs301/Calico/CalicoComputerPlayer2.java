package edu.up.cs301.Calico;

import java.io.Serializable;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
* A computer-version of a Calico-player.  Since this is such a simple game,
* it just sends "+" and "-" commands with equal probability, at an average
* rate of one per second. This computer player does, however, have an option to
* display the game as it is progressing, so if there is no human player on the
* device, this player will display a GUI that shows the value of the Calico
* as the game is being played.
* 
* @author Steven R. Vegdahl
* @author Andrew M. Nuxoll
* @version September 2013
*/
public class CalicoComputerPlayer2 extends CalicoComputerPlayer1 implements Serializable {

	private CalicoState state;
	private ArrayList<int[]> availablePatches = new ArrayList<>();
	protected static final long serialVersionUID = 170425L;

	/**
	 * constructor
	 *
	 * @param name the player's name
	 */
	public CalicoComputerPlayer2(String name)
    {

        super(name);

        int[] patch;
        for(int i = 1; i<=5; i++)
        {
            for (int j = 1; j<=5; j++)
            {
                if(!(i==2 && j==3) && !(i==3 && j==2) && !(i==4 && j ==4))
                {
                    patch = new int[2];
                    patch[0] = i;
                    patch[1] = j;
                    availablePatches.add(patch);
                }
            }
        }
	}

	public CalicoComputerPlayer2(String name, int playerNum) {
		super(name, playerNum);
        int[] patch;
        for(int i = 1; i<=5; i++)
        {
            for (int j = 1; j<=5; j++)
            {
                if(!(i==2 && j==3) && !(i==3 && j==2) && !(i==4 && j ==4))
                {
                    patch = new int[2];
                    patch[0] = i;
                    patch[1] = j;
                    availablePatches.add(patch);
                }
            }
        }
	}

	/**
	 * callback method--game's state has changed
	 *
	 * @param info the information (presumably containing the game's state)
	 */
	@Override
	protected void receiveInfo(GameInfo info) {
		// perform superclass behavior
		this.state = (CalicoState) info;

		Log.i("PlayerTurn: ", "Game State Received \nPlayerTurn = " + String.valueOf(state.playerTurn));

		if (playerNum == state.playerTurn && state.gameStage == 1) {

			Patch placedPatch = state.playerHand[playerNum][0];
			Patch loopPatch;
			int placedPatchScore = 0;
            int patchToRemove = 0;
			for(int i = 0; i < availablePatches.size(); i++)
			{
				//gets potential placedPatch
				//loopPatch = state.playerBoard.get(state.playerTurn).getPatch(availablePatches.get(i)[0],availablePatches.get(i)[1]);
				for(int j = 0; j<2; j++) {
					ArrayList<int[]> similarPatches = new ArrayList<>();
					int loopPatchScore = 0;


                    loopPatch = new Patch(state.playerHand[playerNum][j]);
					Board boardCopy = new Board(state.playerBoard.get(playerNum));
                    boardCopy.setPatch(loopPatch,availablePatches.get(i)[0],availablePatches.get(i)[1]);

					boolean exists =
							boardCopy.getSimilarPatchesColor(similarPatches, availablePatches.get(i), loopPatch.patchColor);

					if(!exists && similarPatches.size() == 2)
					{
						loopPatchScore ++;
					}
					else if(!exists && similarPatches.size() >= 3)
					{
						loopPatchScore +=4;
					}

					similarPatches.clear();
					exists =
							boardCopy.getSimilarPatchesPattern
									(similarPatches, availablePatches.get(i), loopPatch.patchPattern, state.cats);


                    boolean addCat = false;
                    for (int k = 0; k < 3; k++)
                    {
                        if(state.cats[k].addCat(similarPatches, loopPatch.patchPattern))
                        {
                            addCat = true;
                            loopPatchScore+= state.cats[k].points + 1 ;
                        };

                    }
					if(!exists && similarPatches.size() >= 2 && !addCat)
					{
						loopPatchScore +=2;
					}

                    if(loopPatchScore > placedPatchScore)
                    {
                        placedPatchScore = loopPatchScore;
                        placedPatch = state.playerHand[playerNum][j];
                        patchToRemove = i;
                    }

                }
			}
			//randomly selects place on board, patch from inventory, and patch from community
			int[] locOnBoard = availablePatches.get(patchToRemove);
			availablePatches.remove(patchToRemove);

			int communityIndex = (int) (Math.random() * 3);
			Patch communityPatch = state.communityPool[communityIndex];

			//call necessary function (same as human player I think) to update game
			// send the move-action to the game
			game.sendAction(new CalicoMoveAction(this, locOnBoard, placedPatch, communityPatch));

		}
	}
}

