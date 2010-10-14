package internals.app;


import models.Model;
import models.defaults.DefaultModel;
import views.defaults.DefaultView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import controllers.Controller;

public class CleanMain extends Activity {
	public Model model;
//	public CustomLog log; see displayMessage field on StateData

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {//write junit test
		super.onCreate(savedInstanceState);
		Log.v("init", "onCreate was called: Chessoid, this is your genesis!");
		model = new DefaultModel(new DefaultView(this, model), new Controller(
				this, model));
	}	
}
