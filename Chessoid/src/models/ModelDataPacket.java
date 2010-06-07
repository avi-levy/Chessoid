package models;

import structures.StateDataAtom;

/**
 * The naming is a little tricky here, but it seems natural.<br>
 * A model data packet is some sort of android.view.View (add in link) that
 * contains additional information that can be parsed by a model.<br>
 * Might need to change so that this contains get/set message methods - right
 * now the message is stored as a field in the atom, but that might not be the
 * best place for it.
 * 
 * @author avius
 * 
 */
public interface ModelDataPacket {
	StateDataAtom getStateDataAtom();

	void setStateDataAtom(StateDataAtom atom);

	void refreshAppearance();
}
