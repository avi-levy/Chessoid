package models;

import structures.StateData;
import views.View;
import controllers.Controller;

/**
 * Describes the defining properties for what constitutes a model
 * 
 * @author avius
 * 
 */
public abstract class Model {
	public View view;
	public Controller control;
	protected StateData data;

	/**
	 * Should only be called with already initialized views and controllers
	 * 
	 * @param view
	 * @param controller
	 * @param data
	 */
	public Model(View view, Controller controller) {

		this.control = controller;
		this.view = view;
		this.data = new StateData();

	}

	/**
	 * @return the data
	 */
	public StateData getData() {
		return data;
	}

	/**
	 * Eventually, this should be replaced with a true Event delegation type
	 * method
	 * 
	 * @param packet
	 * @return
	 */
	public abstract boolean update(ModelDataPacket packet);

	public void postConstruction(Model m) {
		view.initialize(m);
		control.initialize(m);
	}

}
