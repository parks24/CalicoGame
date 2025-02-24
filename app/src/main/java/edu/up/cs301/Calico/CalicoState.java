package edu.up.cs301.Calico;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the Calico.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class CalicoState extends GameState {
	
	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;
	
	// the value of the Calico
	private int counter;
	
	/**
	 * constructor, initializing the Calico value from the parameter
	 * 
	 * @param counterVal
	 * 		the value to which the Calico's value should be initialized
	 */
	public CalicoState(int counterVal) {
		counter = counterVal;
	}
	
	/**
	 * copy constructor; makes a copy of the original object
	 * 
	 * @param orig
	 * 		the object from which the copy should be made
	 */
	public CalicoState(CalicoState orig) {
		// set the Calico to that of the original
		this.counter = orig.counter;
	}

	/**
	 * getter method for the Calico
	 * 
	 * @return
	 * 		the value of the Calico
	 */
	public int getCounter() {
		return counter;
	}
	
	/**
	 * setter method for the Calico
	 * 
	 * @param counter
	 * 		the value to which the Calico should be set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
}
