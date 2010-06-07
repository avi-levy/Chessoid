package views.defaults;

import models.ModelDataPacket;
import structures.StateDataAtom;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;

import compat.Compatibility_with_deprecated;

/**
 * The replacement for ChessTileView.<br>
 * As before, this is an extended TextView. It implements the ModelDataPacket
 * interface for now (although I might need to change it up so that
 * ModelDataPacket is an abstract class that extends <? extends View> or
 * something like that.<br>
 * We'll see if I can get by with just an interface.<br>
 * The name is prepended with "Default" because this class is just part of the
 * defaultview "theme".
 * 
 * @author avius
 * 
 */
public class DefaultChessSquare extends TextView implements ModelDataPacket {

	private int color;
	public StateDataAtom atom;
	public DefaultChessSquare(Context context, short[] location, boolean alternator) {
		super(context);
		// TODO Auto-generated constructor stub
		atom = new StateDataAtom();//we don't know the chesspiece yet, right?
		atom.location = location;
		String s0 = Character.toString(Settings.Enumerators[0].charAt(location[0]));
		String s1 = Character.toString(Settings.Enumerators[1].charAt(location[1]));
//		Log.v("init", "Initialized chessboard square with value: [" + s0 + s1 +"]");
//		try{

		setText(s0+s1);
//		} catch(Exception e) {
//			Log.v("test", "uh oh in chesssquare: "+s0+s1);
//		}
		int i = 0;
		if(alternator)
			i = 1;
		setBackgroundColor(Settings.BoardColors[i]);
		setMinHeight(Settings.SquareMinDimensions[0]);
		setMinWidth(Settings.SquareMinDimensions[1]);
		setClickable(true);
		
	}

	@Override
	public StateDataAtom getStateDataAtom() {
		// TODO Auto-generated method stub
		return atom;
	}

	@Override
	public void refreshAppearance() {
		// TODO Auto-generated method stub
		
		//should change to return 0 if bad
		Integer ret = Compatibility_with_deprecated.cast(atom);
		
		if( ret == null )
			setBackgroundColor(color);
		else
//			setBackgroundDrawable(ret);
			setBackgroundResource(ret);
	}

	@Override
	public void setStateDataAtom(StateDataAtom atom) {
		// TODO Auto-generated method stub
		if(atom.location == null) {
//			Log.v("test","Ambled upon line 76 of DefaultChessSquare");
			atom.location = this.atom.location;
		}
		this.atom = atom;
		
		refreshAppearance();
	}

	/* (non-Javadoc)
	 * @see android.view.View#setBackgroundColor(int)
	 */
	@Override
	public void setBackgroundColor(int color) {
		// TODO Auto-generated method stub
		super.setBackgroundColor(color);
		this.color = color;
	}

}
