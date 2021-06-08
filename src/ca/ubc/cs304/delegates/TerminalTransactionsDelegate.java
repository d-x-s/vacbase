package ca.ubc.cs304.delegates;

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
	
	public void deleteVaccine(String vacName);
	public void insertVaccine(Vaccine model);
	public void showVaccine();
	public void updateVaccine(int branchId, String name);
	
	public void terminalTransactionsFinished();
}
