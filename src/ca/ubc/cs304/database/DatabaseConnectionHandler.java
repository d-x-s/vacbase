package ca.ubc.cs304.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import ca.ubc.cs304.model.vaccine.Vaccine;
import ca.ubc.cs304.sql.SQLUtil;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	
	private Connection connection = null;
	
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
		try  {
			connection.rollback();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void databaseSetup() {
		try {
			// resources/sql/databaseSetup.sql
			// resources/sql/create_db.sql
			dropBranchTableIfExists();
			SQLUtil.executeFile(connection, new File("resources/sql/databaseSetup.sql"));
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

			while(rs.next()) {
				if(rs.getString(1).toLowerCase().equals("branch")) {
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

	// END OF DATABASE HANDLERS ////////////////////////////////////////////////////////////////////////////////////////

	// BRANCH //////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void deleteVaccine(String vacName) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Vaccine WHERE vacname = ?");
			ps.setString(1, vacName);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Vaccine " + vacName + " does not exist!");
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
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Vaccine VALUES (?,?,?)");
			ps.setString(1, model.getVacName());
			ps.setString(2, model.getType());
			ps.setDouble(3, model.getDosage());

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM vaccine");
		
    		// get info on ResultSet
    		ResultSetMetaData rsmd = rs.getMetaData();

    		System.out.println(" ");

    		// display column names;
    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
    			// get column name and print it
    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
    		}
			
			while(rs.next()) {
				Vaccine model = new Vaccine(rs.getString("vaccine_vacname"),
											rs.getString("vaccine_type"),
											rs.getDouble("vaccine_dosage"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}	
		
		return result.toArray(new Vaccine[result.size()]);
	}

	public void updateVaccine(String vacName, String type, double dosage) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE Vaccine SET type = ?, dosage = ? WHERE vacname = ?");
			ps.setString(1, type);
			ps.setDouble(2, dosage);
			ps.setString(3, vacName);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Vaccine " + vacName + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// PATIENTACCOUNT /////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
