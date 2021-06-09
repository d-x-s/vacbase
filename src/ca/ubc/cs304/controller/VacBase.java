package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TabPageDelegate;
import ca.ubc.cs304.model.vaccine.Vaccine;
import ca.ubc.cs304.project.ui.TabPage;
import ca.ubc.cs304.ui.LoginWindow;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class VacBase implements LoginWindowDelegate, TabPageDelegate {
	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public VacBase() {
		dbHandler = new DatabaseConnectionHandler();
	}
	
	private void start() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
	}
	
	/**
	 * LoginWindowDelegate Implementation
	 * 
     * connects to Oracle database with supplied username and password
     */ 
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
			// Once connected, remove login window and start text transaction flow
			loginWindow.dispose();

			TabPage vacPage = new TabPage();
			vacPage.setupDatabase(this);
		} else {
			loginWindow.handleLoginFailed();

			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}
	
	/**
	 * TabPageDelegate Implementation
	 * 
	 * Insert a vaccine with the given info
	 */
	public void insertVaccine(Vaccine model) {
		dbHandler.insertVaccine(model);
	}
    /**
	 * TabPageDelegate Implementation
	 * 
	 * Delete vaccine with given vaccine name.
	 */
	public void deleteVaccine(String vacName) {
		dbHandler.deleteVaccine(vacName);
	}
    
    /**
	 * TabPageDelegate Implementation
	 * 
	 * Update the vaccine type, dosage for a name
	 */

	public void updateVaccine(String vacName, String type, double dosage) {
		dbHandler.updateVaccine(vacName, type, dosage);
	}

	/**
	 * TabPageDelegate Implementation
	 *
	 * The TabPage instance tells us that it is done with what it's
	 * doing so we are cleaning up the connection since it's no longer needed.
	 */
	public void tabPageFinished() {
		this.dbHandler.close();
		this.dbHandler = null;
		System.exit(0);
	}

	/**
	 * TabPageDelegate Implementation
	 * 
	 * Displays information about various vaccines.
	 */
    public void showVaccine() {
		Vaccine[] models = dbHandler.getVaccineInfo();
    	
    	for (int i = 0; i < models.length; i++) {
			Vaccine model = models[i];

    		// simplified output formatting; truncation may occur
    		System.out.printf("%-10.10s", model.getVacName());
    		System.out.printf("%-20.20s", model.getType());
			System.out.printf("%-20.20s", model.getDosage());

    		System.out.println();
    	}
    }
    
    /**
	 * TabPageDelegate Implementation
	 * 
     * The TabPage instance tells us that the user is fine with dropping any existing table
     * called vaccine and creating a new one for this project to use
     */ 
	public void databaseSetup() {
		dbHandler.databaseSetup();
	}
    
	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		VacBase vacBase = new VacBase();
		vacBase.start();
	}
}
