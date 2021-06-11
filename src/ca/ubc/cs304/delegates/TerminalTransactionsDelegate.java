package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.vaccine.Vaccine;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {

	public void databaseSetup();

	// TODO: TABLE METHODS:
	/**
		Criteria satisfied:
	 	- Insert Operation
	 	- Delete Operation
	 	- Update Operation
	 */
	public void deleteBranch(int branchId);
	public void insertBranch(BranchModel model);
	public void showBranch();
	public void updateBranch(int branchId, String name);

	public void insertPatientAccount(PatientAccount model);
	public void deletePatientAccount(int careCardNumber);
	public void showPatientAccount();
	public void updatePatientAccount(int CareCardNumber, String newUsername);

//	public void insertVaccine();
//	public void deleteVaccine();
//	public void showVaccine();
//	public void updateVaccine();

//	public void insertCondition();
//	public void deleteCondition();
//	public void showCondition();
//	public void updateCondition();

//	public void insertFacility();
//	public void deleteFacility();
//	public void showFacility();
//	public void updateFacility();

	// TODO: QUERY METHODS:
	/**
	 * Criteria satisfied:
	 * - Selection
	 * - Projection
	 * - Join
	 * - Aggregation
	 * - Nested Aggregation
	 * - Division
	 */

	public void selectionQuery();


	public void terminalTransactionsFinished();
}
