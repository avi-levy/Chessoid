package structures;

import models.defaults.DefaultSettings;
import android.util.Log;

/**
 * A snapshot of the model
 * 
 * @author avius
 * 
 */
public class StateData {
	// public DefaultChessSquare[][] chessSquares;
	public StateDataAtom[][] atoms;
	public String displayMessage;
	public short[] clicked;

	public StateData(StateData data) {
		// TODO Auto-generated constructor stub
		
		atoms = new StateDataAtom[DefaultSettings.BoardDimensions[0]][DefaultSettings.BoardDimensions[1]];
		for (short i = 0; i < DefaultSettings.BoardDimensions[0]; i++) {
			for (short j = 0; j < DefaultSettings.BoardDimensions[1]; j++) {
				atoms[i][j] = new StateDataAtom(data.atoms[i][j]);

			}

		}
		displayMessage = (data.displayMessage == null) ? null : new String(data.displayMessage);
		// To be used later on
		// String[][] fen = new String[][]
		// {
		// { "r", "n", "b", "q", "k", "b", "n", "r" },
		// { "p", "p", "p", "p", "p", "p", "p", "p" },
		// { " ", " ", " ", " ", " ", " ", " ", " " },
		// { " ", " ", " ", " ", " ", " ", " ", " " },
		// { " ", " ", " ", " ", " ", " ", " ", " " },
		// { " ", " ", " ", " ", " ", " ", " ", " " },
		// { " ", " ", " ", " ", " ", " ", " ", " " },
		// { "P", "P", "P", "P", "P", "P", "P", "P" },
		// { "R", "N", "B", "Q", "K", "B", "N", "R" },
		// };
		// Initialize our board in the "chess" package by passing in a string
		// array like this one and having the constructor parse each string into
		// a chesspiece.
	}

	/**
	 * This constructor is mostly provided for the Compatibility_with_deprecated
	 * class<br>
	 * It does NOT produce a state that is fit for use; all its fields must be
	 * set in order to avoid lots of nullpointerexceptions.
	 */
	public StateData() {
		// TODO Auto-generated constructor stub
		
		atoms = new StateDataAtom[DefaultSettings.BoardDimensions[0]][DefaultSettings.BoardDimensions[1]];
		for (short i = 0; i < DefaultSettings.BoardDimensions[0]; i++) {
			for (short j = 0; j < DefaultSettings.BoardDimensions[1]; j++) {
				try{
					atoms[i][j] = new StateDataAtom();
				} catch( NullPointerException e) {
					Log.v("NPE", "NPe in statedata line 56... confusing");
				}
				displayMessage = null;

			}

		}
	}

}
