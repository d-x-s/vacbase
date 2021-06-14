package ca.ubc.cs304.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.patient.PreExistingCondition;
import ca.ubc.cs304.model.vaccine.Vaccine;

/**
 * The class is only responsible for handling terminal text inputs.
 */
public class TerminalTransactions {
    private TerminalTransactionsDelegate delegate = null;
    private BufferedReader bufferedReader = null;
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final int INVALID_INT = Integer.MIN_VALUE;
    private static final double INVALID_DOUBLE = Double.MIN_VALUE;
    private static final int EMPTY_INPUT = 0;
    private static final String INVALID_STRING = "";


    public TerminalTransactions() {
    }

    /**
     * Sets up the database to have a branch table with two tuples so we can insert/update/delete from it.
     * Refer to the databaseSetup.sql file to determine what tuples are going to be in the table.
     */
    public void setupDatabase(TerminalTransactionsDelegate delegate) {
        this.delegate = delegate;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INT;

        while(choice != 1 && choice != 2) {
            System.out.println("If you have a table called Branch in your database (capitialization of the name does not matter), it will be dropped and a new Branch table will be created.\nIf you want to proceed, enter 1; if you want to quit, enter 2.");

            choice = readInteger(false);

            if (choice != INVALID_INT) {
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
        int choice = INVALID_INT;

        while (choice != 28) {
            System.out.println();
            System.out.println("1. Insert branch");
            System.out.println("2. Delete branch");
            System.out.println("3. Update branch name");
            System.out.println("4. Show branch");

            System.out.println("5. Insert Patient Account");
            System.out.println("6. Delete Patient Account");
            System.out.println("7. Show Patient Accounts");
            System.out.println("8. Update the username of a Patient Account");

            System.out.println("9. Insert Vaccine");
            System.out.println("10. Delete Vaccine");
            System.out.println("11. Show Vaccines");
            System.out.println("12. Update the dosage of a Vaccine");

            System.out.println("13. Insert a Condition");
            System.out.println("14. Delete a Condition");
            System.out.println("15. Show Conditions");
            System.out.println("16. Update the description of a Condition");

            System.out.println("17. Insert a Facility");
            System.out.println("18. Delete a Facility");
            System.out.println("19. Show Facilities");
            System.out.println("20. Update the address of a Facility");

            System.out.println("21. Selection Query: To discard (?)");
            System.out.println("22. Projection Query: Display available Vaccines");
            System.out.println("23. Search for Patient Account Query");
            System.out.println("24. Join VaccineRecord with the ER/D Aggregate, output Vaccine | Date | Location | Nurse");
            System.out.println("25. Aggregation Query: Show the total number of Vaccines administered so far");
            System.out.println("26. Division query: All patients who have gotten all vaccines");
            System.out.println("27. Nested Aggregation Query: Patients who have been vaccinated more times than the average number of vaccinations for a patient");


            System.out.println("28. Quit");
            System.out.print("Please choose one of the above 28 options: ");

            choice = readInteger(false);

            System.out.println(" ");

            if (choice != INVALID_INT) {
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

                    // PatientAccount
                    case 5:
                        handlePatientAccountInsertOption();
                        break;
                    case 6:
                        handlePatientAccountDeleteOption();
                        break;
                    case 7:
                        delegate.showPatientAccount();
                        break;
                    case 8:
                        handlePatientAccountUpdateOption();
                        break;

                    // Vaccine
                    case 9:
                        handleVaccineInsertOption();
                        break;
                    case 10:
                        handleVaccineDeleteOption();
                        break;
                    case 11:
                        delegate.showVaccine();
                        break;
                    case 12:
                        handleVaccineUpdateOption();
                        break;

                    // Condition
                    case 13:
                        handleConditionInsertOption();
                        break;
                    case 14:
                        handleConditionDeleteOption();
                        break;
                    case 15:
                        delegate.showCondition();
                        break;
                    case 16:
                        handleConditionUpdateOption();
                        break;

                    // Facility
                    case 17:
                        handleFacilityInsertOption();
                        break;
                    case 18:
                        handleFacilityDeleteOption();
                        break;
                    case 19:
                        delegate.showFacility();
                        break;
                    case 20:
                        handleFacilityUpdateOption();
                        break;

                    // Query
                    case 21:
                        handleSelectionQuery();
                        break;
                    case 22:
                        handleProjectionQuery();
                        break;
                    case 23:
                        handleSearchForPatientAccountQuery();
                        break;
                    case 24:
                        delegate.joinAggregateWithVaccineRecordQuery();
                        break;
                    case 25:
                        delegate.aggregationQueryTotalVaccines();
                        break;
                    case 26:
                        delegate.divisionQuery();
                        break;
                    case 27:
                        delegate.nestedAggregationQuery();

                    // Quit
                    case 28:
                        handleQuitOption();
                        break;
                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
                        break;
                }
            }
        }
    }

    // QUERIES /////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void handleSelectionQuery() {
        delegate.selectionQuery();
    }

    private void handleSearchForPatientAccountQuery() {
        int CareCardNumber = INVALID_INT;
        while (CareCardNumber == INVALID_INT) {
            System.out.print("Please enter the CareCardNumber of the Patient Account you wish to find: ");
            CareCardNumber = readInteger(false);
            if (CareCardNumber != INVALID_INT) {
                delegate.searchForPatientAccountQuery(CareCardNumber);
            }
        }

    }

    private void handleProjectionQuery() {
        delegate.projectionQuery();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BRANCH //////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void handleDeleteOption() {
        int branchId = INVALID_INT;
        while (branchId == INVALID_INT) {
            System.out.print("Please enter the branch ID you wish to delete: ");
            branchId = readInteger(false);
            if (branchId != INVALID_INT) {
                delegate.deleteBranch(branchId);
            }
        }
    }

    private void handleInsertOption() {
        int id = INVALID_INT;
        while (id == INVALID_INT) {
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

        int phoneNumber = INVALID_INT;
        while (phoneNumber == INVALID_INT) {
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

    private void handleUpdateOption() {
        int id = INVALID_INT;
        while (id == INVALID_INT) {
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

    // PATIENT ACC //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void handlePatientAccountDeleteOption() {
        int CareCardNumber = INVALID_INT;
        while (CareCardNumber == INVALID_INT) {
            System.out.print("Please enter the CareCardNumber you wish to delete: ");
            CareCardNumber = readInteger(false);
            if (CareCardNumber != INVALID_INT) {
                delegate.deletePatientAccount(CareCardNumber);
            }
        }
    }

    private void handlePatientAccountInsertOption() {
        int CareCardNumber = INVALID_INT;
        while (CareCardNumber == INVALID_INT) {
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

    private void handlePatientAccountUpdateOption() {
        int CareCardNumber = INVALID_INT;
        while (CareCardNumber == INVALID_INT) {
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VACCINE /////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * The following methods handle insertion, deletion, and updates in the DB
     */

    private void handleVaccineDeleteOption() {
        int vacID = INVALID_INT;
        while (vacID == INVALID_INT) {
            System.out.print("Please enter the vaccine ID you wish to delete: ");
            vacID = readInteger(false);
            if (vacID != INVALID_INT) {
                delegate.deleteVaccine(vacID);
            }
        }
    }

    private void handleVaccineInsertOption() {
        int vacID = INVALID_INT;
        while (vacID == INVALID_INT) {
            System.out.print("Please enter the vaccine ID you wish to insert: ");
            vacID = readInteger(false);
        }

        String vacName = INVALID_STRING;
        while (vacName == INVALID_STRING) {
            System.out.print("Please enter the vaccine name you wish to insert: ");
            vacName = readString(false);
        }

        String type = null;
        while (type == null || type.length() <= 0) {
            System.out.print("Please enter the vaccine type you wish to insert: ");
            type = readLine().trim();
        }

        double dosage = 0.0;
        while (dosage == 0.0 || dosage == INVALID_DOUBLE) {
            System.out.print("Please enter the vaccine dosage you wish to insert: ");
            dosage = readDouble(false);
        }

        Vaccine model = new Vaccine(vacID, vacName, type, dosage);
        delegate.insertVaccine(model);
    }

    private void handleVaccineUpdateOption() {
        int vacID = INVALID_INT;
        while (vacID == INVALID_INT) {
            System.out.print("Please enter the vaccine ID you wish to update: ");
            vacID = readInteger(false);
        }

        double dosage = 0.0;
        while (dosage == 0.0 || dosage == INVALID_DOUBLE) {
            System.out.print("Please enter the vaccine dosage you wish to update: ");
            dosage = readDouble(false);
        }

        delegate.updateVaccine(vacID, dosage);
    }

    // FACILITY ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void handleFacilityDeleteOption() {
        int FID = INVALID_INT;
        while (FID == INVALID_INT) {
            System.out.print("Please enter the facility ID you wish to delete: ");
            FID = readInteger(false);
            if (FID != INVALID_INT) {
                delegate.deleteFacility(FID);
            }
        }
    }

    private void handleFacilityInsertOption() {
        int FID = INVALID_INT;
        while (FID == INVALID_INT) {
            System.out.print("Please enter the facility ID you wish to insert: ");
            FID = readInteger(false);
        }

        String fName = INVALID_STRING;
        while (fName == INVALID_STRING) {
            System.out.print("Please enter the facility name you wish to insert: ");
            fName = readString(false);
        }

        String address = null;
        while (address == null || address.length() <= 0) {
            System.out.print("Please enter the facility address you wish to insert: ");
            address = readLine().trim();
        }

        Facility model = new Facility(FID, fName, address);
        delegate.insertFacility(model);
    }

    private void handleFacilityUpdateOption() {
        int FID = INVALID_INT;
        while (FID == INVALID_INT) {
            System.out.print("Please enter the facility ID you wish to update: ");
            FID = readInteger(false);
        }

        String address = INVALID_STRING;
        while (address == INVALID_STRING) {
            System.out.print("Please enter the facility address you wish to update: ");
            address = readString(false);
        }

        delegate.updateFacility(FID, address);
    }
    // CONDITION ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void handleConditionDeleteOption() {
        int careCardNum = INVALID_INT;
        while (careCardNum == INVALID_INT) {
            System.out.print("Please enter the care card number of patient you wish to delete the condition of: ");
            careCardNum = readInteger(false);
            if (careCardNum != INVALID_INT) {
                delegate.deleteCondition(careCardNum);
            }
        }
    }

    private void handleConditionInsertOption() {
        int careCardNum = INVALID_INT;
        while (careCardNum == INVALID_INT) {
            System.out.print("Please enter the care card number you wish to insert: ");
            careCardNum = readInteger(false);
        }

        String condition = INVALID_STRING;
        while (condition == INVALID_STRING || condition.length() <= 0) {
            System.out.print("Please enter the condition you wish to insert: ");
            condition = readLine().trim();
        }
        PreExistingCondition model = new PreExistingCondition(careCardNum, condition);
        delegate.insertCondition(model);
    }

    private void handleConditionUpdateOption() {
        int careCardNum = INVALID_INT;
        while (careCardNum == INVALID_INT) {
            System.out.print("Please enter the care card number of patient you wish to update: ");
            careCardNum = readInteger(false);
        }

        String condition = INVALID_STRING;
        while (condition == INVALID_STRING || condition.length() <= 0) {
            System.out.print("Please enter the condition you wish to update: ");
            condition = readLine().trim();
        }

        delegate.updateCondition(careCardNum, condition);
    }


    // Helper functions //

    private int readInteger(boolean allowEmpty) {
        String line = null;
        int input = INVALID_INT;
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

    private String readString(boolean allowEmpty) {
        String line = null;
        String input = INVALID_STRING;
        try {
            line = bufferedReader.readLine();
            input = line;
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (NumberFormatException e) {
            if (allowEmpty && line.length() == 0) {
                input = INVALID_STRING;
            } else {
                System.out.println(WARNING_TAG + " Your input was not a string");
            }
        }
        return input;
    }

    private double readDouble(boolean allowEmpty) {
        String line = null;
        double input = INVALID_DOUBLE;
        try {
            line = bufferedReader.readLine();
            input = Double.parseDouble(line);
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (NumberFormatException e) {
            if (allowEmpty && line.length() == 0) {
                input = EMPTY_INPUT;
            } else {
                System.out.println(WARNING_TAG + " Your input was not a double");
            }
        }
        return input;
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
}
