package controllers;

import internals.ui.Msg;
import models.Model;
import models.ModelDataPacket;
import structures.StateDataAtom;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Controller implements OnClickListener {
	private Activity activity;
	private Model model;
	private ModelDataPacket referencePacket = new ModelDataPacket() {

		@Override
		public StateDataAtom getStateDataAtom() {
			// TODO Auto-generated method stub
			return referenceAtom;
		}

		@Override
		public void refreshAppearance() {
			// TODO Auto-generated method stub

		}

		@Override
		public void setStateDataAtom(StateDataAtom atom) {
			// TODO Auto-generated method stub

		}

	};
	private StateDataAtom referenceAtom;

	/**
	 * Uhoh, the model field is becoming rapidly unused in the constructors...
	 * general fad happening in view also.
	 * 
	 * @param activity
	 * @param model
	 */
	public Controller(Activity activity, Model model) {
		this.activity = activity;
		this.model = model;
		this.referenceAtom = new StateDataAtom();
	}

	@Override
	public void onClick(View paramView) {
		// TODO Auto-generated method stub
		if (paramView instanceof ModelDataPacket) {
			model.update((ModelDataPacket) paramView);
		} else {// pass our own "spoofed" data packet
			Log.v("test","User clicked somewhere unsupported");
			referenceAtom.message = Msg.BUG;
			model.update(referencePacket);
		}
		// Log that the user clicked on something unaccountable
		// To do that we need to have a log!!
		// Implemented above
	}

	public void initialize(Model m) {
		// TODO Auto-generated method stub
		model = m;
	}
}
