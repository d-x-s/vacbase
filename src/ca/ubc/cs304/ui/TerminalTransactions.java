package ca.ubc.cs304.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.patient.PatientAccount;

/**
 * The class is only responsible for handling terminal text inputs.
 */
public class TerminalTransactions {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final int INVALID_INPUT = Integer.MIN_VALUE;
    private static final int EMPTY_INPUT = 0;

    private BufferedReader bufferedReader = null;
    private TerminalTransactionsDelegate delegate = null;

    public TerminalTransactions() {
    }

    /**
     * Sets up the database to have a branch table with two tuples so we can insert/update/delete from it.
     * Refer to the databaseSetup.sql file to determine what tuples are going to be in the table.
     */
    public void setupDatabase(TerminalTransactionsDelegate delegate) {
        this.delegate = delegate;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INPUT;

        while(choice != 1 && choice != 2) {
            System.out.println("If you have a table called Branch in your database (capitialization of the name does not matter), it will be dropped and a new Branch table will be created.\nIf you want to proceed, enter 1; if you want to quit, enter 2.");

            choice = readInteger(false);

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    case 1:
                        delegate.databaseSetup();
                        break;
                    case 2:
                        handleQuitOption();
                        break;
                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.\n");
                        break;
                }
            }
        }
    }

    /**
     * Displays simple text interface
     */
    public void showMainMenu(TerminalTransactionsDelegate delegate) {
        this.delegate = delegate;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INPUT;

        while (choice != 9) {
            System.out.println();
            System.out.println("1. Insert branch");
            System.out.println("2. Delete branch");
            System.out.println("3. Update branch name");
            System.out.println("4. Show branch");

            System.out.println("5. Insert Patient Account");
            System.out.println("6. Delete Patient Account");
            System.out.println("7. Show Patient Accounts");
            System.out.println("8. Update the username of a Patient Account");


            System.out.println("9. Quit");
            System.out.print("Please choose one of the above 9 options: ");

            choice = readInteger(false);

            System.out.println(" ");

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    // Branch
                    case 1:
                        handleInsertOption();
                        break;
                    case 2:
                        handleDeleteOption();
                        break;
                    case 3:
                        handleUpdateOption();
                        break;
                    case 4:
                        delegate.showBranch();
                        break;
                    case 5:
                        handlePatientAccountInsertOption();
                        break;

                    // PatientAccount
                    case 6:
                        handlePatientAccountDeleteOption();
                        break;
                    case 7:
                        delegate.showPatientAccount();
                        break;
                    case 8:
                        handlePatientAccountUpdateOption();
                        break;
                    case 9:
                        handleQuitOption();
                        break;

                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
                        break;
                }
            }
        }
    }

    private void handleDeleteOption() {
        int branchId = INVALID_INPUT;
        while (branchId == INVALID_INPUT) {
            System.out.print("Please enter the branch ID you wish to delete: ");
            branchId = readInteger(false);
            if (branchId != INVALID_INPUT) {
                delegate.deleteBranch(branchId);
            }
        }
    }

    private void handlePatientAccountDeleteOption() {
        int CareCardNumber = INVALID_INPUT;
        while (CareCardNumber == INVALID_INPUT) {
            System.out.print("Please enter the CareCardNumber you wish to delete: ");
            CareCardNumber = readInteger(false);
            if (CareCardNumber != INVALID_INPUT) {
                delegate.deletePatientAccount(CareCardNumber);
            }
        }
    }

    private void handleInsertOption() {
        int id = INVALID_INPUT;
        while (id == INVALID_INPUT) {
            System.out.print("Please enter the branch ID you wish to insert: ");
            id = readInteger(false);
        }

        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter the branch name you wish to insert: ");
            name = readLine().trim();
        }

        // branch address is allowed to be null so we don't need to repeatedly ask for the address
        System.out.print("Please enter the branch address you wish to insert: ");
        String address = readLine().trim();
        if (address.length() == 0) {
            address = null;
        }

        String city = null;
        while (city == null || city.length() <= 0) {
            System.out.print("Please enter the branch city you wish to insert: ");
            city = readLine().trim();
        }

        int phoneNumber = INVALID_INPUT;
        while (phoneNumber == INVALID_INPUT) {
            System.out.print("Please enter the branch phone number you wish to insert: ");
            phoneNumber = readInteger(true);
        }

        BranchModel model = new BranchModel(address,
                city,
                id,
                name,
                phoneNumber);
        delegate.insertBranch(model);
    }

    private void handlePatientAccountInsertOption() {
        int CareCardNumber = INVALID_INPUT;
        while (CareCardNumber == INVALID_INPUT) {
            System.out.print("Please enter the CareCardNumber you wish to insert: ");
            CareCardNumber = readInteger(false);
        }

        String FullName = null;
        while (FullName == null || FullName.length() <= 0) {
            System.out.print("Please enter the FullName you wish to insert: ");
            FullName = readLine().trim();
        }

        // branch address is allowed to be null so we don't need to repeatedly ask for the address
        System.out.print("Please enter the DOB you wish to insert: ");
        String input_DOB = readLine().trim();
        Date DOB = Date.valueOf(input_DOB); // conver string into SQL date

//		if (DOB.length() == 0) {
//			address = null;
//		}

        String Username = null;
        while (Username == null || Username.length() <= 0) {
            System.out.print("Please enter the Username you wish to insert: ");
            Username = readLine().trim();
        }

        PatientAccount model = new PatientAccount(
                CareCardNumber,
                FullName,
                DOB,
                Username);
        delegate.insertPatientAccount(model);
    }

    private void handleQuitOption() {
        System.out.println("Good Bye!");

        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("IOException!");
            }
        }

        delegate.terminalTransactionsFinished();
    }

    private void handleUpdateOption() {
        int id = INVALID_INPUT;
        while (id == INVALID_INPUT) {
            System.out.print("Please enter the branch ID you wish to update: ");
            id = readInteger(false);
        }

        String name = null;
        while (name == null || name.length() <= 0) {
            System.out.print("Please enter the branch name you wish to update: ");
            name = readLine().trim();
        }

        delegate.updateBranch(id, name);
    }

    private void handlePatientAccountUpdateOption() {
        int CareCardNumber = INVALID_INPUT;
        while (CareCardNumber == INVALID_INPUT) {
            System.out.print("Please enter the CareCardNumber of the account you wish to update: ");
            CareCardNumber = readInteger(false);
        }

        String newUserName = null;
        while (newUserName == null || newUserName.length() <= 0) {
            System.out.print("Please enter the new username: ");
            newUserName = readLine().trim();
        }

        delegate.updatePatientAccount(CareCardNumber, newUserName);
    }

    private int readInteger(boolean allowEmpty) {
        String line = null;
        int input = INVALID_INPUT;
        try {
            line = bufferedReader.readLine();
            input = Integer.parseInt(line);
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (NumberFormatException e) {
            if (allowEmpty && line.length() == 0) {
                input = EMPTY_INPUT;
            } else {
                System.out.println(WARNING_TAG + " Your input was not an integer");
            }
        }
        return input;
    }

    private String readLine() {
        String result = null;
        try {
            result = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }
}
