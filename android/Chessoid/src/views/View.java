package views;

import java.util.List;

import models.Model;

import structures.StateData;

/**
 * This will need to be able to process custom events at some point, when MVC
 * gets running full swing.
 * 
 * @author avius
 * 
 */
public interface View {
	/**
	 * Updates the view with the latest chessboard data. Returns {@code true} if
	 * any changes were made to the view.
	 * 
	 * @param data
	 *            updated chessboard data
	 * @return {@code true} if the view changed
	 */
	boolean update(StateData data);

	/**
	 * Returns the latest state we saw
	 * 
	 * @return the latest state we saw
	 */
	StateData getState();

	/**
	 * Returns the state history
	 * 
	 * @return the state history
	 */
	List<StateData> getHistory();

	/**
	 * Logs the specified string to the display
	 * 
	 * @param s
	 *            the string to be logged
	 */
	void log(String s);

	/**
	 * Added to handle issues with constructor orders; the actions performed in
	 * the initialize method differ from those in the constructor because these
	 * methods rely on the construction of the entire MVC framework, whereas the
	 * actions in the constructor only rely on a partial MVC framework (because
	 * in there it is still being constructed)
	 * @param m 
	 */
	void initialize(Model m);
}
