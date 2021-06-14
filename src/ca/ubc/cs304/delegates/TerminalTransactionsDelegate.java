package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.patient.PreExistingCondition;
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

	public void deleteVaccine(int vacID);
	public void insertVaccine(Vaccine model);
	public void showVaccine();
	public void updateVaccine(int vacID, double dosage);

	public void insertCondition(PreExistingCondition model);
	public void deleteCondition(int careCardNumber);
	public void showCondition();
	public void updateCondition(int careCardNumber, String condition);

	public void insertFacility(Facility model);
	public void deleteFacility(int FID);
	public void showFacility();
	public void updateFacility(int FID, String newAddress);

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

	public void selectionQuery(); // ! fulfilled by PatientAccount search; can discard this later

	public void searchForPatientAccountQuery(int CareCardNumber); // search for a unique PatientAccount

	public void projectionQuery(); // gets the names of available vaccines
	                               // TODO: the user should be able to choose which column to display
	                               //       need to add option for user: display all VacNames or all VacIDs?
	                               //       otherwise, this query is considered to be hardcoded

	public void joinAggregateWithVaccineRecordQuery(); // joins VaccineRecord with the big aggregation in our ER/D diagram

	public void aggregationQueryTotalVaccines(); // the total number of vaccinations administered

//	public void nestedAggregationQuery();

	public void divisionQuery(); // searches for people who have had all vaccines

	public void terminalTransactionsFinished();
}
