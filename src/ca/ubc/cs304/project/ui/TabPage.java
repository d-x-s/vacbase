package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.delegates.TabPageDelegate;
import ca.ubc.cs304.model.vaccine.Vaccine;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class TabPage {
    Scene page;
    TabPane tabs;
    Tab vaccine;
    Tab distributor;
    Tab patient;
    Tab facilities;
    TextField searchBar;

    private TabPageDelegate delegate = null;
    private BufferedReader bufferedReader = null;
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final int INVALID_INT = Integer.MIN_VALUE;
    private static final double INVALID_DOUBLE = Double.MIN_VALUE;
    private static final int EMPTY_INPUT = 0;
    private static final String INVALID_STRING = "";

    // Example data
    ArrayList<ArrayList<String>> distributorData;

    public TabPage() {

        //region Setup Tabs
        initiateFields();
        setUpDistributorTab();
        setUpVaccineTab();
        setUpFacilitiesTab();
        setUpPatientTab();

        //endregion
        StackPane backgroundPane = new StackPane();
        setBackgroundColor(backgroundPane);
        backgroundPane.getChildren().add(tabs);
        tabs.getTabs().addAll(vaccine, distributor, patient, facilities);
        tabs.setMaxSize(pageWidth - 70, pageHeight - 70);
        tabs.setMinSize(pageWidth - 70, pageHeight - 70);
        page = new Scene(backgroundPane, pageWidth, pageHeight);


    }

    private void setUpVaccineTab() {

    }

    private void setUpDistributorTab() {

    }

    private void setUpPatientTab() {
        VBox vBox = new VBox(20);
        setWhiteBackgroundColor(vBox);
        Label message = new Label("Search by Care Card Number");
        message.setFont(new Font("Montserrat", 24));
        searchBar.setPromptText("Search... ");
        searchBar.setMaxSize(420, 56);
        searchBar.setMinSize(420, 56);
        searchBar.setFont(new Font("Montserrat", 24));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(message, searchBar);
        patient.setContent(vBox);

        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("Testing to see if it works.");
            }
        });

    }

    private void setUpFacilitiesTab() {

    }

    private void initiateFields() {
        searchBar = new TextField();
        tabs = new TabPane();
        vaccine = new Tab("Vaccines");
        distributor = new Tab("Distributors");
        patient = new Tab("Patients");
        facilities = new Tab("Facilities");
    }

    public Scene getPage() {
        return page;
    }

    /**
     * The following methods handle insertion, deletion, and updates in the DB
     */
    public void setupDatabase(TabPageDelegate delegate) {
        this.delegate = delegate;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INT;

        while (choice != 1 && choice != 2) {
            System.out.println("If you have a table called Vaccine in your database (capitialization of the name does not matter), it will be dropped and a new Vaccine table will be created.\nIf you want to proceed, enter 1; if you want to quit, enter 2.");

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

    private String readString(boolean allowEmpty) {
        String line = null;
        String input = INVALID_STRING;
        try {
            line = bufferedReader.readLine();
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

    private double readDouble (boolean allowEmpty) {
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

    private String readLine() {
        String result = null;
        try {
            result = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }

    private void handleDeleteOption() {
        String vacName = INVALID_STRING;
        while (vacName == INVALID_STRING) {
            System.out.print("Please enter the vaccine name you wish to delete: ");
            vacName = readString(false);
            if (vacName != INVALID_STRING) {
                delegate.deleteVaccine(vacName);
            }
        }
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

        delegate.tabPageFinished();
    }

    private void handleInsertOption() {
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

        Vaccine model = new Vaccine(vacName, type, dosage);
        delegate.insertVaccine(model);
    }

    private void handleUpdateOption() {
        String vacName = INVALID_STRING;
        while (vacName == INVALID_STRING) {
            System.out.print("Please enter the vaccine name you wish to update: ");
            vacName = readString(false);
        }

        String type = null;
        while (type == null || type.length() <= 0) {
            System.out.print("Please enter the vaccine type you wish to update: ");
            type = readLine().trim();
        }

        double dosage = 0.0;
        while (dosage == 0.0 || dosage == INVALID_DOUBLE) {
            System.out.print("Please enter the vaccine dosage you wish to update: ");
            dosage = readDouble(false);
        }

        delegate.updateVaccine(vacName, type, dosage);
    }


}
