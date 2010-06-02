package structures;

import edu.utd.chess.pieces.ChessPiece;

/**
 * An atom of StateData<br>
 * In other words, the smallest indivisible packet of StateData. The StateData
 * is built out of a collection of StateDataAtoms<br>
 * Currently using data structures that are deprecated
 * 
 * @author avius
 * 
 */
public class StateDataAtom {
	public ChessPiece piece;
	public String message;
	public short[] location;

	public StateDataAtom(StateDataAtom stateDataAtom) {
		// TODO Auto-generated constructor stub
		piece = stateDataAtom.piece;// again, should use copy constructor or
									// clone method
		message = (stateDataAtom.message == null) ? null : new String(
				stateDataAtom.message);
		if (stateDataAtom.location == null)
			location = null;
		else
			location = new short[] { stateDataAtom.location[0],
					stateDataAtom.location[1] };
	}

	public StateDataAtom() {
		// TODO Auto-generated constructor stub
	}

	public StateDataAtom(ChessPiece chessPiece, short[] location) {
		// TODO Auto-generated constructor stub
		piece = chessPiece;// normally I would use a copy constructor, but
		// deprecated data structure in "chess" folder
		// doesn't allow for that. If we accidentally modify piece, it could
		// cause side effects
		location = new short[] { location[0], location[1] };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (!(o instanceof StateDataAtom))
			return false;
		StateDataAtom other = ((StateDataAtom) o);
		return piece.equals(other.piece) && message.equals(other.message)
				&& location.equals(other.location);
	}

}
