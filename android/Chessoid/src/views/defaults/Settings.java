package views.defaults;

/**
 * The settings corresponding to this particular view.<br>
 * Note: need a new naming convention, also consider mergin ui.Msg to java into here when we get rid of ui folder
 * @author avius
 *
 */
public class Settings {
	/**
	 * The minimum height of each of the TableRows
	 */
	public final static int RowMinHeight = 35;
	/**
	 * The minimum dimensions of each chess square.<br>
	 * <ul>
	 * Reference:
	 * <li>SquareMinDimensions[0] - the minimum width</li>
	 * <li>SquareMinDimensions[1] - the minimum height</li>
	 * </ul>
	 */
	public final static int[] SquareMinDimensions = new int[] { 40, 40 };
	/**
	 * The padding of the table.<br>
	 * Reference: same as those in TableLayout.setPadding(); <br>
	 * Note to self: Add in reference info, also add in "see" tag
	 */
	public final static int[] TablePadding = { 3, 3, 3, 3 };
	/**
	 * The board colors.<br>
	 * <ul>
	 * Reference:
	 * <li>BoardColors[0] - the light color</li>
	 * <li>BoardColors[1] - the dark color</li>
	 * </ul>
	 */

	public final static int[] BoardColors = new int[] { 0xffffffff, 0x00000000 };
	/**
	 * The text to be used to enumerate tiles.<br>
	 * <ul>
	 * Reference:
	 * <li>Enumerators[0] - the column enumerators</li>
	 * <li>Enumerators[1] - the row enumerators</li>
	 * </ul>
	 * <br>
	 * Note: Dimensions should match models.<current model
	 * package>.Settings.BoardDimensions;<br>
	 * Note to self: should flip for consistency?
	 * 
	 * @see models.DefaultSettings.defaults.Settings#BoardDimensions
	 */

	public final static String[] Enumerators = new String[] { "abcdefgh",
			"12345678" };

}
