package internals.ui;

/**
 * Class full of static constants for user messages, etc.
 * TODO: externalize this stuff into res
 * @author troy
 *
 */
public class Msg {
	
	/**
	 * User message: unexpected thing happened.
	 */
	public static final String BUG = "You've discovered a bug.  Contact the developers!\n";
	
	/**
	 * User message: ChessCoords are occupied
	 */
	public static final String COORDS_OCC = "Can't move there (that guy is on your side)";
	
	/**
	 * User message: illegal move
	 */
	public static final String ILLEGAL_MOVE = "Can't move there (illegal move for that piece)";
	
	/**
	 * User message: ChessCoords invalid...somehow
	 */
	public static final String INVALID_COORDS = "Invalid coordinates!  How'd that happen?";
	
	/**
	 * User message: ChessPiece cannot attack itself
	 */
	public static final String SUICIDE = "Suicide is not legal in Chessoid.";
	
	/**
	 * User message: Can't attack your own team
	 */
	public static final String HOMICIDE = COORDS_OCC;
	
}
