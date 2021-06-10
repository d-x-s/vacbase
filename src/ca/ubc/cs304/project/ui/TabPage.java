package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.delegates.TabPageDelegate;
import ca.ubc.cs304.model.PatientAccountModel;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.patient.PreExistingCondition;
import ca.ubc.cs304.model.patient.Facility;
import ca.ubc.cs304.model.vaccine.Vaccine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
    Tab filter;
    TextField searchBar;

    /*
    Filter tab
     */
    Button button017;
    Button button1829;
    Button button3044;
    Button button4559;
    Button button60;
    TableView<PatientAccount> filterView;
    ObservableList<PatientAccount> filterList;

    /*
    Facility Tab
     */
    Button insertFacilityButton;
    Button updateFacilityButton;
    TextField facilityNameField;
    TextField addressField;
    TableView<Facility> facilityView;
    ObservableList<Facility> facilityList;


    private TabPageDelegate delegate = null;
    private BufferedReader bufferedReader = null;
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final int INVALID_INT = Integer.MIN_VALUE;
    private static final double INVALID_DOUBLE = Double.MIN_VALUE;
    private static final int EMPTY_INPUT = 0;
    private static final String INVALID_STRING = "";


    public TabPage() {

        //region Setup Tabs
        initiateFields();
        setUpDistributorTab();
        setUpVaccineTab();
        setUpFacilitiesTab();
        setUpPatientTab();
        setUpFilterTab();

        //endregion
        StackPane backgroundPane = new StackPane();
        setBackgroundColor(backgroundPane);
        backgroundPane.getChildren().add(tabs);
        tabs.getTabs().addAll(vaccine, distributor, patient, facilities, filter);
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
        VBox mainPane = new VBox(20);
        setWhiteBackgroundColor(mainPane);
        facilityView = new TableView<>();

        //region Setting up tableView
        TableColumn<Facility, String> nameColumn = new TableColumn<>("Facility Name");
        TableColumn<Facility, String> addressColumn = new TableColumn<>("Address");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("facilityName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        facilityView.setItems(getFacilities());
        facilityView.getColumns().addAll(nameColumn, addressColumn);

        //endregion

        //region Setting up Layouts
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5));

        VBox vBox1 = new VBox(5);
        VBox vBox2 = new VBox(5);
        hBox.getChildren().addAll(vBox1, vBox2);
        //endregion

        //region Customizing textfields and buttons
        makeButtonOnWhite(insertFacilityButton);
        makeButtonOnWhite(updateFacilityButton);
        insertFacilityButton.setFont(commonFont(20));
        updateFacilityButton.setFont(commonFont(20));
        insertFacilityButton.setMinWidth(100);
        insertFacilityButton.setMaxWidth(100);
        updateFacilityButton.setMinWidth(100);
        updateFacilityButton.setMaxWidth(100);
        insertFacilityButton.setTranslateX(40);
        updateFacilityButton.setTranslateX(40);
        addressField.setPromptText("Input address: ");
        addressField.setFont(commonFont(20));
        facilityNameField.setPromptText("Input name: ");
        facilityNameField.setFont(commonFont(20));
        facilityNameField.setMinWidth(400);
        //endregion

        vBox1.getChildren().addAll(facilityNameField, addressField);

        vBox2.getChildren().addAll(insertFacilityButton, updateFacilityButton);
        mainPane.getChildren().addAll(facilityView, hBox);

        facilities.setContent(mainPane);

        insertFacilityButton.setOnAction(e -> {
            Facility temp;
            try {
                temp = new Facility(facilityNameField.getText(), addressField.getText());
                facilityList.add(temp);
                facilityNameField.clear();
                addressField.clear();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        });
        facilityView.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Facility selectedItem = facilityView.getSelectionModel().getSelectedItem();
                facilityView.getItems().remove(selectedItem);
            }
        });

        updateFacilityButton.setOnAction(event -> {
            Facility selectedItem = facilityView.getSelectionModel().getSelectedItem();
            //TODO: Add setters
//            selectedItem.setAddress(addressField.getText());
//            selectedItem.setFacilityName(facilityNameField.getText());
            addressField.clear();
            facilityNameField.clear();
        });


    }

    private void setUpFilterTab() {
        HBox hBox = new HBox();
        setWhiteBackgroundColor(hBox);
        hBox.setPadding(new Insets(10));
        filterView = new TableView<>();

        //region Setting up columns
        TableColumn<PatientAccount, String> usernameColumn = new TableColumn<>("Username");
        TableColumn<PatientAccount, String> careCardNumber = new TableColumn<>("Care Care Number");
        usernameColumn.setMinWidth((pageWidth >> 1) * .75);
        careCardNumber.setMinWidth(pageWidth - (pageWidth >> 1) * .75);
        usernameColumn.setMaxWidth((pageWidth >> 1) * .75);
        careCardNumber.setMaxWidth(pageWidth - (pageWidth >> 1) * .75);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        careCardNumber.setCellValueFactory(new PropertyValueFactory<>("careCardNumber"));
        //endregion

        VBox vBox = new VBox(10);
        BorderPane hBox1 = new BorderPane();
        BorderPane hBox2 = new BorderPane();
        BorderPane hBox3 = new BorderPane();
        BorderPane hBox4 = new BorderPane();

        //region Customizing Buttons
        makeButtonOnWhite(button017);
        makeButtonOnWhite(button1829);
        makeButtonOnWhite(button3044);
        makeButtonOnWhite(button4559);
        makeButtonOnWhite(button60);
        button017.setFont(commonFont(24));
        button1829.setFont(commonFont(24));
        button3044.setFont(commonFont(24));
        button4559.setFont(commonFont(24));
        button60.setFont(commonFont(24));
        //endregion

        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4);
        Label filterLabel = new Label("Filter by Age Bracket: ");
        filterLabel.setFont(commonFont(24));
        //region Adding nodes to rhs
        hBox1.setCenter(filterLabel);
        hBox2.setLeft(button017);
        hBox2.setRight(button1829);
        hBox3.setLeft(button3044);
        hBox3.setRight(button4559);
        hBox4.setCenter(button60);
        //endregion
        vBox.setTranslateX(50);






        hBox.getChildren().addAll(filterView, vBox);
        filter.setContent(hBox);
    }

    private void initiateFields() {
        searchBar = new TextField();
        filter = new Tab("Filter");
        tabs = new TabPane();
        vaccine = new Tab("Vaccines");
        distributor = new Tab("Distributors");
        patient = new Tab("Patients");
        facilities = new Tab("Facilities");
        button017 = new Button("0 - 17");
        button1829 = new Button("18 - 29");
        button3044 = new Button("30 - 44");
        button4559 = new Button("45 - 59");
        button60 = new Button("60+");
        insertFacilityButton = new Button("Insert");
        updateFacilityButton = new Button("Update");
        facilityNameField = new TextField();
        addressField = new TextField();
    }

    public Scene getPage() {
        return page;
    }

    public ObservableList<Facility> getFacilities() {
        facilityList = FXCollections.observableArrayList();
        facilityList.add(new Facility("Facility 1", "Address 1"));
        facilityList.add(new Facility("Facility 2", "Address 2"));
        facilityList.add(new Facility("Facility 3", "Address 3"));
        facilityList.add(new Facility("Facility 4", "Address 4"));
        facilityList.add(new Facility("Facility 5", "Address 5"));
        facilityList.add(new Facility("Facility 6", "Address 6"));
        return facilityList;
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
