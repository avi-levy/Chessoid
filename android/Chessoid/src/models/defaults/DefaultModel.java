package models.defaults;

import models.Model;
import models.ModelDataPacket;
import structures.StateDataAtom;
import android.util.Log;

import compat.Compatibility_with_deprecated;

import controllers.Controller;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.game.ChessGame;

public class DefaultModel extends Model {

	/**
	 * @return the displayMessage
	 */
	public String getDisplayMessage() {
		return data.displayMessage;
	}

	/**
	 * @param displayMessage
	 *            the displayMessage to set
	 */
	public void setDisplayMessage(String displayMessage) {
		this.data.displayMessage = displayMessage;
	}

	public DefaultModel(views.View view, Controller controller) {
		super(view, controller);
		// TODO Auto-generated constructor stub

		data = Compatibility_with_deprecated.cast(ChessGame.INSTANCE
				.getChessBoard(), "Welcome to Chessoid, engine v2");
		Log.v("init","Everything has been constructed. "+this);
		postConstruction(this);
		view.update(data);
	}

	@Override
	public boolean update(ModelDataPacket packet) {
		// TODO Auto-generated method stub
		boolean stateChanged = false;
		StateDataAtom atom = packet.getStateDataAtom();
		if(atom == null) {
			Log.v("test","Received nothing in click, line 48 of defaultmodel");
			return false;
		}
		String message = null;
		// Given the data atom, we need to make modifications to the StateData
		// data

		short[] s = atom.location;
		if(s == null) {
			Log.v("test","atom had no location - probably an issue in line 57 of defaultmodel");
		}
		StateDataAtom localAtom;
		try{
		 localAtom = data.atoms[s[0]][s[1]];
		} catch(NullPointerException e) {
			Log.v("NPE","in defaultmodel update; data is " + data);
			Log.v("NPE","same as last "+  "local atom is " + data.atoms);
			//really confused why data.atoms is null...
		} finally {
			
			localAtom = null;
		}

		if (localAtom != null && !localAtom.equals(atom)) {
			stateChanged = true;
			localAtom = new StateDataAtom(atom);// replace our old local version
												// with up2date
		}

		if (data.clicked != null) {// we were making a move
			message = move(data.clicked, s);
			data = Compatibility_with_deprecated.cast(ChessGame.INSTANCE.getChessBoard(), message);//should make the later call to message useless;
			data.clicked = null;
		} else {
			data.clicked = s;
		}

		if (atom.message != null) {
//			Log.v("test","data message: "+data.displayMessage);
			data.displayMessage = new String(atom.message);
		}
		else {
//			Log.v("NPE","atom message was null line 88 of defaultmodel; anyway we had message of: " + data.displayMessage);
//			data.displayMessage = (message == null) ? null : new String(message);
		}
		if(data.displayMessage == null)
			data.displayMessage = "";
		view.update(data);
		return stateChanged;
	}

	private String move(short[] clicked, short[] s) {
		// TODO Auto-generated method stub
		ChessCoords from = Compatibility_with_deprecated.cast(clicked);
		ChessCoords to = Compatibility_with_deprecated.cast(s);
		return Compatibility_with_deprecated.emulateMove(from, to);
		
	}

}
