package views.defaults;

import java.util.ArrayList;
import java.util.List;

import models.Model;
import models.defaults.DefaultSettings;
import structures.StateData;
import views.View;
import android.app.Activity;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DefaultView implements View {
	private Activity activity;
	private Model model;
	private List<StateData> history;
	// private DefaultViewTableLayout d;
	private DefaultChessSquare[][] chessSquares;
	private TextView status;

	// considering list of all non-square view elements

	/**
	 * Constructs a new DefaultView given a reference to the running Activity
	 * 
	 * @param activity
	 *            the running Activity
	 */
	public DefaultView(Activity activity, Model model) {
		this.activity = activity;
		this.model = model;
		this.status = new TextView(activity);
		this.history = new ArrayList<StateData>();
		// data = new StateData();

		// Initialize the table
		chessSquares = new DefaultChessSquare[DefaultSettings.BoardDimensions[0]][DefaultSettings.BoardDimensions[1]];
		// not sure if should be here or in subclass but see if fixes bug
	}

	@Override
	public List<StateData> getHistory() {
		// TODO Auto-generated method stub
		List<StateData> ret = new ArrayList<StateData>();
		ret.addAll(history);
		return ret;
	}

	@Override
	public StateData getState() {
		// TODO Auto-generated method stub
		return history.get(history.size() - 1);
	}

	@Override
	public boolean update(StateData data) {
		// TODO Auto-generated method stub
		boolean stateChanged = false;

		for (short i = 0; i < DefaultSettings.BoardDimensions[0]; i++) {
			for (short j = 0; j < DefaultSettings.BoardDimensions[1]; j++) {
				if (chessSquares[i][j].equals(data.atoms[i][j])) {
					Log.v("test", "No need to update data at: (" + i + "," + j
							+ ")");
				} else {
					stateChanged = true;

					// }
					// if (!chessSquares[i][j].atom.equals(data.atoms[i][j])
					// && data.atoms[i][j] != null) {
					// stateChanged = true;
					chessSquares[i][j].setStateDataAtom(data.atoms[i][j]);
				}
			}

		}

		if (data.displayMessage != null)
			log(data.displayMessage);
		if (stateChanged) {
			history.add(new StateData(data));
			return true;
		} else
			return false;
	}

	private class DefaultViewTableLayout extends TableLayout {

		public DefaultViewTableLayout() {
			// TODO Auto-generated constructor stub
			super(activity);

			boolean alternator = true;


			// short[] iterator = {1, 1};
//			Log.v("test", "got here in defaultviewtablelayout");

			for (short i = 0; i < DefaultSettings.BoardDimensions[0]; i++) {
				TableRow tr = new TableRow(activity);
				tr.setMinimumHeight(Settings.RowMinHeight);
				addView(tr);
				for (short j = 0; j < DefaultSettings.BoardDimensions[1]; j++) {
					DefaultChessSquare d = new DefaultChessSquare(activity,
							new short[] { j, i }, alternator);
					chessSquares[i][j] = d;
//					try {
						d.setOnClickListener(model.control);
//					} catch (NullPointerException e) {
//						Log.v("test", "model.controller is: " + model
//								+ "model.controller" + model.control);
//						Log.v("NPE", "for loop of defaultview");
//						d.setOnClickListener(new OnClickListener() {
//
//							@Override
//							public void onClick(android.view.View arg0) {
//								// TODO Auto-generated method stub
//								Log.v("test", "click handled");
//							}
//
//						});
//					}
					tr.addView(d);
					alternator = !alternator;
				}
				alternator = !alternator;
			}

			// Initialize the message box
			if (status != null)
				addView(status);
			else {
				// throw new NullPointerException("status was null");
				status = new TextView(activity);
			}
			// The status will be initialized with a string when we pass in the
			// initial state (including chessboard)
			// Custom startup message

			// Table Settings
			setStretchAllColumns(true);
			setPadding(Settings.TablePadding[0], Settings.TablePadding[1],
					Settings.TablePadding[2], Settings.TablePadding[3]);
			activity.setContentView(this);
		}
	}

	@Override
	public void log(String s) {
		// TODO Auto-generated method stub
		status.setText(s);
	}

	@Override
	public void initialize(Model m) {
		// TODO Auto-generated method stub
		this.model = m;
		new DefaultViewTableLayout();
	}
}
