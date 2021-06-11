package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;

import ca.ubc.cs304.model.BranchModel;

import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.PatientAccount;

import ca.ubc.cs304.model.patient.PreExistingCondition;
import ca.ubc.cs304.model.vaccine.Vaccine;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.TerminalTransactions;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class VacBase implements LoginWindowDelegate, TerminalTransactionsDelegate {
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

			TerminalTransactions transaction = new TerminalTransactions();
			transaction.setupDatabase(this);
			transaction.showMainMenu(this);
		} else {
			loginWindow.handleLoginFailed();

			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}

	// QUERIES /////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void selectionQuery() {
		dbHandler.selectionQuery();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	// BRANCH //////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Insert a branch with the given info
	 */
	public void insertBranch(BranchModel model) {
		dbHandler.insertBranch(model);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Delete branch with given branch ID.
	 */
	public void deleteBranch(int branchId) {
		dbHandler.deleteBranch(branchId);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Update the branch name for a specific ID
	 */

	public void updateBranch(int branchId, String name) {
		dbHandler.updateBranch(branchId, name);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 *
	 * Displays information about varies bank branches.
	 */
	public void showBranch() {
		BranchModel[] models = dbHandler.getBranchInfo();

		for (int i = 0; i < models.length; i++) {
			BranchModel model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getId());
			System.out.printf("%-20.20s", model.getName());
			if (model.getAddress() == null) {
				System.out.printf("%-20.20s", " ");
			} else {
				System.out.printf("%-20.20s", model.getAddress());
			}
			System.out.printf("%-15.15s", model.getCity());
			if (model.getPhoneNumber() == 0) {
				System.out.printf("%-15.15s", " ");
			} else {
				System.out.printf("%-15.15s", model.getPhoneNumber());
			}

			System.out.println();
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





	// PATIENTACCOUNT //////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertPatientAccount(PatientAccount model) { dbHandler.insertPatientAccount(model); }
	public void deletePatientAccount(int careCardNumber) { dbHandler.deletePatientAccount(careCardNumber); }
	public void updatePatientAccount(int CareCardNumber, String newUserName)  {dbHandler.updatePatientAccount(CareCardNumber, newUserName); }

	public void showPatientAccount() {
		PatientAccount[] models = dbHandler.getPatientAccountInfo();

		for (int i = 0; i < models.length; i++) {
			PatientAccount model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getCareCardNumber());
			System.out.printf("%-20.20s", model.getFullName());
//			if (model.getAddress() == null) {
//				System.out.printf("%-20.20s", " ");
//			} else {
//				System.out.printf("%-20.20s", model.getAddress());
//			}
			System.out.printf("%-15.15s", model.getDate());
//			if (model.getPhoneNumber() == 0) {
//				System.out.printf("%-15.15s", " ");
//			} else {
//				System.out.printf("%-15.15s", model.getPhoneNumber());
//			}
			System.out.printf("%-20.20s", model.getUsername());

			System.out.println();
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





	// VACCINE /////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void deleteVaccine(String vacName) {
		dbHandler.deleteVaccine(vacName);
	}

	public void insertVaccine(Vaccine model) {
		dbHandler.insertVaccine(model);
	}

	public void showVaccine() {
		Vaccine[] models = dbHandler.getVaccineInfo();

		for (int i = 0; i < models.length; i++) {
			Vaccine model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getVacName());
			System.out.printf("%-20.20s", model.getType());
			System.out.printf("%-15.15s", model.getDosage());

			System.out.println();
		}
	}

	public void updateVaccine(String vacName, double dosage) {
		dbHandler.updateVaccine(vacName, dosage);
	}

	// FACILITY ////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void deleteFacility(String name) {
		dbHandler.deleteFacility(name);
	}

	public void insertFacility(Facility model) {
		dbHandler.insertFacility(model);
	}

	public void showFacility() {
		Facility[] models = dbHandler.getFacilityInfo();

		for (int i = 0; i < models.length; i++) {
			Facility model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getFacilityName());
			System.out.printf("%-20.20s", model.getAddress());

			System.out.println();
		}
	}

	public void updateFacility(String name, String address) {
		dbHandler.updateFacility(name, address);
	}

	// CONDITION ///////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertCondition(PreExistingCondition model) {
		dbHandler.insertCondition(model);
	}

	public void deleteCondition(int careCardNumber) {
		dbHandler.deleteCondition(careCardNumber);
	}

	public void showCondition() {
		PreExistingCondition[] models = dbHandler.getConditionInfo();

		for (int i = 0; i < models.length; i++) {
			PreExistingCondition model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getCareCardNumber());
			System.out.printf("%-20.20s", model.getCondition());

			System.out.println();
		}
	}

	public void updateCondition(int careCardNumber, String condition) {
		dbHandler.updateCondition(careCardNumber, condition);
	}

	/**
	 * TerminalTransactionsDelegate Implementation
	 *
	 * The TerminalTransaction instance tells us that it is done with what it's
	 * doing so we are cleaning up the connection since it's no longer needed.
	 */
	public void terminalTransactionsFinished() {
		dbHandler.close();
		dbHandler = null;

		System.exit(0);
	}

	/**
	 * TerminalTransactionsDelegate Implementation
	 *
	 * The TerminalTransaction instance tells us that the user is fine with dropping any existing table
	 * called branch and creating a new one for this project to use
	 */
	public void databaseSetup() {
		dbHandler.databaseSetup();;

	}

	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		VacBase vacBase = new VacBase();
		vacBase.start();
	}
}
