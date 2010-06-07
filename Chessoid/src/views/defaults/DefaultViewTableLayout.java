package views.defaults;

import android.app.Activity;
import android.widget.TableLayout;
import android.widget.TableRow;

@Deprecated
public class DefaultViewTableLayout extends TableLayout {
	private Activity context;

	public DefaultViewTableLayout(Activity context) {
		// TODO Auto-generated constructor stub
		super(context);
		this.context = context;

		//Initialize the table
		boolean alternator = true;
		for(int i = 1; i<=8; i++) {
			TableRow tr = new TableRow(context);
			tr.setMinimumHeight(Settings.RowMinHeight);
			addView(tr);
			
		}
	}

}
