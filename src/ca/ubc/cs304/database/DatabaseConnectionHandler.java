package ca.ubc.cs304.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.AgeBracketLookup;
import ca.ubc.cs304.model.patient.LoginInfo;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.patient.PreExistingCondition;
import ca.ubc.cs304.model.vaccine.Vaccine;
import ca.ubc.cs304.sql.SQLUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * This class handles all database related transactions
 * <p>
 * !!! NOTE !!!
 * SCROLL DOWN FOR THE IMPLEMENTATION OF PATIENTACCOUNT, VACCINE, CONDITION, AND FACILITY
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    // CONNECTION OPERATIONS ///////////////////////////////////////////////////////////////////////////////////////////
    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // NOTE: This is an old copy of databaseSetup that hardcoded branch creation
//	public void databaseSetup() {
//		dropBranchTableIfExists();
//
//		try {
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate("CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)");
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
//		insertBranch(branch1);
//
//		BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
//		insertBranch(branch2);
//	}

    // NOTE: This database setup runs a SQL script to create the tables
    public void databaseSetup() {
        try {
            // resources/sql/databaseSetup.sql
            // resources/sql/create_db.sql
            dropBranchTableIfExists();

            // drop tables: YOU WILL GET AN ERROR IF THE TABLES DO NOT EXIST!
            //SQLUtil.executeFile(connection, new File("resources/sql/databaseDrop.sql"));

            // add tables: YOU WILL GET AN ERROR IF THE TABLES ALREADY EXIST!
            //SQLUtil.executeFile(connection, new File("resources/sql/databaseSetup.sql"));

            // populate tables:
            //SQLUtil.executeFile(connection, new File("resources/sql/databasePopulate.sql"));

            // placeholder: DOES NOTHING
            SQLUtil.executeFile(connection, new File("resources/sql/databaseClear.sql"));

            //createTriggers(connection);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    private void dropBranchTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).equalsIgnoreCase("branch")) {
                    stmt.execute("DROP TABLE branch");
                    break;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // QUERIES /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void selectionQuery() {
        String query = "SELECT * FROM PatientAccount, AgeBracketLookup WHERE PatientAccount.DOB = ageBracketLookup.DOB";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            System.out.println(" ");

            while (rs.next()) {
                int CareCardNumber = rs.getInt("CareCardNumber");
                String FullName = rs.getString("FullName");
                Date DOB = rs.getDate("DOB");
                String Username = rs.getString("Username");
                String AgeBracket = rs.getString("AgeBracket");
                System.out.println(CareCardNumber + ", " + FullName + ", " + DOB +
                        ", " + Username + ", " + AgeBracket);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void searchForPatientAccountQuery(int SearchForThisCareCardNumber) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PatientAccount WHERE CareCardNumber = ?");
            ps.setInt(1, SearchForThisCareCardNumber);

            ResultSet rs = ps.executeQuery();

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            System.out.println(" ");

            while (rs.next()) {
                int CareCardNumber = rs.getInt("CareCardNumber");
                String FullName = rs.getString("FullName");
                Date DOB = rs.getDate("DOB");
                String Username = rs.getString("Username");
                System.out.println(CareCardNumber + ", " + FullName + ", " + DOB +
                        ", " + Username);
            }

            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            // System.out.println(WARNING_TAG + " Patient Account: " + SearchForThisCareCardNumber + " does not exist!");
            rollbackConnection();
        }


    }

    public void projectionQuery() {
        String query = "SELECT DISTINCT vacName FROM Vaccine, VacDosage WHERE Availability = 'Y'";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            System.out.println(" ");

            while (rs.next()) {
                String vacName = rs.getString("vacName");
                System.out.println(vacName);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void joinAggregateWithVaccineRecordQuery() {
        String query =
                "SELECT " +
                    "VaccineRecord.CareCardNumber, " +
                    "VaccineRecord.ID, " +
                    "VaccineRecord.EventID, " +
                    "AdministeredVaccineGivenToPatient.NurseID, " +
                    "AdministeredVaccineGivenToPatient.VacDate, " +
                    "Include.VacID, " +
                    "Vaccine.VacName, " +
                    "HappensIn.FacilityID, " +
                    "Facility.FacilityName, " +
                    "Nurse.NurseName FROM VaccineRecord " +
                "INNER JOIN AdministeredVaccineGivenToPatient " +
                     "ON VaccineRecord.EventID = AdministeredVaccineGivenToPatient.EventID " +
                     "and VaccineRecord.CareCardNumber = AdministeredVaccineGivenToPatient.CareCardNumber " +
                "INNER JOIN Include " +
                     "ON AdministeredVaccineGivenToPatient.EventID = Include.EventID " +
                "INNER JOIN HappensIn " +
                     "ON AdministeredVaccineGivenToPatient.EventID = HappensIn.EventID " +
                "INNER JOIN NURSE " +
                     "ON AdministeredVaccineGivenToPatient.NurseID = Nurse.NurseID " +
                "INNER JOIN FACILITY " +
                     "ON HappensIn.FacilityID = Facility.FacilityID " +
                "INNER JOIN VACCINE " +
                     "ON Include.VacID = Vaccine.VacID";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            System.out.println(" ");

            while (rs.next()) {

                // TODO: only need to output certain variables, need a way to save other data for later?
                int CareCardNumber = rs.getInt("CareCardNumber");
                int ID = rs.getInt("ID");
                int EventID = rs.getInt("EventID");

                int NurseID = rs.getInt("NurseID");
                String NurseName = rs.getString("NurseName");

                Date VacDate = rs.getDate("VacDate");
                int VacID = rs.getInt("VacID");
                String VacName = rs.getString("VacName");

                int FacilityID = rs.getInt("NurseID");
                String FacilityName = rs.getString("FacilityName");

                System.out.println(CareCardNumber + ", " + ID + ", " + EventID +
                        ", " + NurseID +  ", " + VacDate + ", " + VacID + ", " + VacName +
                        ", " + FacilityID + ", " + FacilityName + ", " + NurseName);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void aggregationQueryTotalVaccines() {
        String query = "SELECT COUNT(*) FROM VaccineRecord";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String count = rs.getString("COUNT(*)");
                System.out.println("The total number of vaccines administered so far is: " + count);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // BRANCH //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteBranch(int branchId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
            ps.setInt(1, branchId);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertBranch(BranchModel model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
            ps.setInt(1, model.getId());
            ps.setString(2, model.getName());
            ps.setString(3, model.getAddress());
            ps.setString(4, model.getCity());
            if (model.getPhoneNumber() == 0) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, model.getPhoneNumber());
            }

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public BranchModel[] getBranchInfo() {
        ArrayList<BranchModel> result = new ArrayList<BranchModel>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM branch");

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            while (rs.next()) {
                BranchModel model = new BranchModel(rs.getString("branch_addr"),
                        rs.getString("branch_city"),
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getInt("branch_phone"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new BranchModel[result.size()]);
    }

    public void updateBranch(int id, String name) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
            ps.setString(1, name);
            ps.setInt(2, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // PATIENTACCOUNT //////////////////////////////////////////////////////////////////////////////////////////////////
    public void deletePatientAccount(int careCardNumber) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PatientAccount WHERE CareCardNumber = ?");
            ps.setInt(1, careCardNumber);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Account " + careCardNumber + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println("Problem here");
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertPatientAccount(PatientAccount model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PatientAccount VALUES (?,?,?,?)");
            ps.setInt(1, model.getCareCardNumber());
            ps.setString(2, model.getFullName());
            ps.setDate(3, model.getDate());
            ps.setString(4, model.getUsername());
//			if (model.getPhoneNumber() == 0) {
//				ps.setNull(5, java.sql.Types.INTEGER);
//			} else {
//				ps.setInt(5, model.getPhoneNumber());
//			}

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    public PatientAccount[] getPatientAccountInfo() {
        ArrayList<PatientAccount> result = new ArrayList<PatientAccount>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PatientAccount");

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            while (rs.next()) {
                PatientAccount model = new PatientAccount(
                        rs.getInt("CareCardNumber"),
                        rs.getString("FullName"),
                        rs.getDate("DOB"),
                        rs.getString("Username"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new PatientAccount[result.size()]);
    }

    public void updatePatientAccount(int CareCardNumber, String newUserName) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE PatientAccount SET Username = ? WHERE CareCardNumber = ?");
            ps.setString(1, newUserName);
            ps.setInt(2, CareCardNumber);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " PatientAccount with CareCardNumber" + CareCardNumber + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // VACCINE /////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteVaccine(int ID) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Vaccine WHERE VacID = ?");
            ps.setInt(1, ID);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Vaccine " + ID + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertVaccine(Vaccine model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Vaccine VALUES (?,?,?,?)");
            ps.setInt(1, model.getVacID());
            ps.setString(2, model.getVacName());
            ps.setString(3, model.getType());
            ps.setDouble(4, model.getDosage());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Vaccine[] getVaccineInfo() {
        ArrayList<Vaccine> result = new ArrayList<Vaccine>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vaccine");

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            while (rs.next()) {
                Vaccine model = new Vaccine(rs.getInt("vacID"),
                                            rs.getString("vacName"),
                                            rs.getString("type"),
                                            rs.getDouble("dosage"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Vaccine[result.size()]);
    }

    // Don't think we need, considering that type and dosage are both static but I'll just put it here
    public void updateVaccine(int vacID, double newDosage) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Vaccine SET dosage = ? WHERE vacID = ?");
            ps.setDouble(1, newDosage);
            ps.setInt(2, vacID);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Vaccine with ID " + vacID + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // FACILITY ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteFacility(int FID) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Facility WHERE FacilityID = ?");
            ps.setInt(1, FID);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Facility with ID " + FID + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertFacility(Facility model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Facility VALUES (?,?,?)");
            ps.setInt(1, model.getFacilityID());
            ps.setString(2, model.getFacilityName());
            ps.setString(3, model.getAddress());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Facility[] getFacilityInfo() {
        ArrayList<Facility> result = new ArrayList<Facility>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Facility");

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            while (rs.next()) {
                Facility model = new Facility(rs.getInt("FacilityID"),
                        rs.getString("FacilityName"),
                        rs.getString("Address"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Facility[result.size()]);
    }

//    public ComboBox<Facility> makeFacilitiesObservable(Facility[] arr) {
//        ComboBox<Facility> facilities = new ComboBox<Facility>(FXCollections.observableArrayList(arr));
//        return facilities;
//    }

    public void updateFacility(int FID, String newAddress) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Facility SET address = ? WHERE FacilityID = ?");
            ps.setString(1, newAddress);
            ps.setInt(2, FID);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Facility with ID " + FID + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // CONDITION ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteCondition(int careCardNum) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PreExistingCondition WHERE CareCardNumber = ?");
            ps.setInt(1, careCardNum);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Patient with care card number " + careCardNum + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertCondition(PreExistingCondition model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PreExistingCondition VALUES (?,?)");
            ps.setInt(1, model.getCareCardNumber());
            ps.setString(2, model.getCondition());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public PreExistingCondition[] getConditionInfo() {
        ArrayList<PreExistingCondition> result = new ArrayList<PreExistingCondition>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PreExistingCondition");

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            }

            while (rs.next()) {
                PreExistingCondition model = new PreExistingCondition(rs.getInt("CareCardNumber"),
                        rs.getString("condition"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new PreExistingCondition[result.size()]);
    }

    public void updateCondition(int careCardNum, String newCond) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE PreExistingCondition SET condition = ? WHERE careCardNumber = ?");
            ps.setString(1, newCond);
            ps.setInt(2, careCardNum);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Patient with care card number " + careCardNum + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // JONATHAN's STUFF IDK TESTING ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PatientAccount loginToAccount(String username, String password) {
        // Need to execute this statement
        // Select p.careCardNumber, p.fullName, p.DOB, p.Username from PatientAccount p, LoginInfo l Where l.username = p.username and l.username = 'Spiderman' and l.userpassword = 'Password123'
        String sql = String.format("Select p.careCardNumber, p.fullName, p.DOB, p.Username from PatientAccount p, LoginInfo l Where l.username = p.username and l.username = \'%s\' and l.userpassword = \'%s\'", username, password);
        //System.out.println(sql);
        PatientAccount model = null;
        try {
            System.out.println(connection);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                model = new PatientAccount(rs.getInt("careCardNumber"),
                        rs.getString("fullName"),
                        rs.getDate("DOB"),
                        rs.getString("username"));
            }
            return model;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return null;
    }

    public void insertLoginInfo(LoginInfo model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO LoginInfo VALUES (?,?)");
            ps.setString(1, model.getUsername());
            ps.setString(2, model.getUserPassword());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertAgeBracket(AgeBracketLookup model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO AgeBracketLookup VALUES (?,?)");
            ps.setDate(1, model.getDOB());
            ps.setString(2, model.getAgeBracket());


            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }
}
