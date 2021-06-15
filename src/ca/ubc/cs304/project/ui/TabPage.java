package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.vaccine.Vaccine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.*;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class TabPage {
    private Scene page;
    private TabPane tabs;
    private Tab vaccine;
    private Tab patient;

    private Tab facilities;
    private TextField searchBar;
    private DatabaseConnectionHandler dbh;

    /*
    Facility Tab
     */
    private Button insertFacilityButton;
    private Button updateFacilityButton;
    private TextField facilityIDField;
    private TextField facilityNameField;
    private TextField addressField;
    private TableView<Facility> facilityView;
    private ObservableList<Facility> facilityList;

    /*
    Vaccine tab
     */
    private CheckBox typeBox;
    private CheckBox dosageBox;
    private Button viewButton;
    private TableView<Vaccine> vaccineListView;
    private ObservableList<Vaccine> vaccineList;
    private TableColumn<Vaccine, String> vacIDColumn;
    private TableColumn<Vaccine, String> vacNameColumn;

    public TabPage(DatabaseConnectionHandler databaseConnectionHandler) {

        //region Setup Tabs
        dbh = databaseConnectionHandler;
        initiateFields();
        setUpVaccineTab();
        setUpFacilitiesTab();
        setUpPatientTab();
        setUpVaccineTab();

        //endregion
        StackPane backgroundPane = new StackPane();
        setBackgroundColor(backgroundPane);
        backgroundPane.getChildren().add(tabs);
        tabs.getTabs().addAll(vaccine, patient, facilities);
        tabs.setMaxSize(pageWidth - 70, pageHeight - 70);
        tabs.setMinSize(pageWidth - 70, pageHeight - 70);
        page = new Scene(backgroundPane, pageWidth, pageHeight);
    }

    private void setUpVaccineTab() {
        HBox hBox = new HBox();
        setWhiteBackgroundColor(hBox);
        hBox.setPadding(new Insets(10));
        VBox vBox = new VBox(10);
        vBox.setTranslateX(30);
        vBox.setTranslateY(50);
        typeBox.setFont(commonFont(20));
        dosageBox.setFont(commonFont(20));
        makeButtonOnWhite(viewButton);
        viewButton.setFont(commonFont(20));
        hBox.getChildren().addAll(vaccineListView, vBox);
        vBox.getChildren().addAll(typeBox, dosageBox, viewButton);


        vacIDColumn = new TableColumn<>();
        vacNameColumn = new TableColumn<>();
        vacIDColumn.setCellValueFactory(new PropertyValueFactory<>("vacID"));
        vacNameColumn.setCellValueFactory(new PropertyValueFactory<>("vacName"));
        vaccineListView.setItems(vaccineList);
        vaccineListView.setMinWidth(pageWidth >> 1);
        vaccineListView.setMaxWidth(pageWidth >> 1);

        vaccine.setContent(hBox);
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
    }

    private void setUpFacilitiesTab() {
        VBox mainPane = new VBox(20);
        setWhiteBackgroundColor(mainPane);
        facilityView = new TableView<>();

        //region Setting up tableView
        TableColumn<Facility, String> IDColumn = new TableColumn<>("Facility ID");
        TableColumn<Facility, String> nameColumn = new TableColumn<>("Facility Name");
        TableColumn<Facility, String> addressColumn = new TableColumn<>("Address");
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("facilityID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("facilityName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        facilityView.setItems(getFacilities());
        facilityView.getColumns().addAll(IDColumn, nameColumn, addressColumn);

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

        facilityIDField.setPromptText("Input ID: ");
        facilityIDField.setFont(commonFont(20));
        facilityNameField.setPromptText("Input name: ");
        facilityNameField.setFont(commonFont(20));
        facilityNameField.setMinWidth(400);
        addressField.setPromptText("Input address: ");
        addressField.setFont(commonFont(20));
        //endregion

        vBox1.getChildren().addAll(facilityIDField, facilityNameField, addressField);

        vBox2.getChildren().addAll(insertFacilityButton, updateFacilityButton);
        mainPane.getChildren().addAll(facilityView, hBox);

        facilities.setContent(mainPane);
    }

    private void initiateFields() {
        searchBar = new TextField();
        tabs = new TabPane();
        vaccine = new Tab("Vaccines");
        patient = new Tab("Patients");
        facilities = new Tab("Facilities");
        insertFacilityButton = new Button("Insert");
        updateFacilityButton = new Button("Update");
        facilityIDField = new TextField();
        facilityNameField = new TextField();
        addressField = new TextField();
        typeBox = new CheckBox("Select Type");
        dosageBox = new CheckBox("Select Dosage");
        viewButton = new Button ("View Selections");
        vaccineListView = new TableView<>();
        vaccineList = FXCollections.observableArrayList();
    }

    public Scene getPage() {
        return page;
    }

    public ObservableList<Facility> getFacilities() {
        Facility[] models = dbh.getFacilityInfo();
        facilityList = FXCollections.observableArrayList();
        facilityList.addAll(Arrays.asList(models));
        return facilityList;
    }

    // region Getters and setters


    public TableColumn<Vaccine, String> getVacIDColumn() {
        return vacIDColumn;
    }

    public TableColumn<Vaccine, String> getVacNameColumn() {
        return vacNameColumn;
    }

    public void setPage(Scene page) {
        this.page = page;
    }

    public TabPane getTabs() {
        return tabs;
    }

    public void setTabs(TabPane tabs) {
        this.tabs = tabs;
    }

    public Tab getVaccine() {
        return vaccine;
    }

    public void setVaccine(Tab vaccine) {
        this.vaccine = vaccine;
    }

    public Tab getPatient() {
        return patient;
    }

    public void setPatient(Tab patient) {
        this.patient = patient;
    }

    public void setFacilities(Tab facilities) {
        this.facilities = facilities;
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(TextField searchBar) {
        this.searchBar = searchBar;
    }

    public Button getInsertFacilityButton() {
        return insertFacilityButton;
    }

    public void setInsertFacilityButton(Button insertFacilityButton) {
        this.insertFacilityButton = insertFacilityButton;
    }

    public Button getUpdateFacilityButton() {
        return updateFacilityButton;
    }

    public void setUpdateFacilityButton(Button updateFacilityButton) {
        this.updateFacilityButton = updateFacilityButton;
    }

    public TextField getFacilityIDField() {
        return facilityIDField;
    }

    public TextField getFacilityNameField() {
        return facilityNameField;
    }

    public void setFacilityNameField(TextField facilityNameField) {
        this.facilityNameField = facilityNameField;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public void setAddressField(TextField addressField) {
        this.addressField = addressField;
    }

    public TableView<Facility> getFacilityView() {
        return facilityView;
    }

    public void setFacilityView(TableView<Facility> facilityView) {
        this.facilityView = facilityView;
    }

    public ObservableList<Facility> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(ObservableList<Facility> facilityList) {
        this.facilityList = facilityList;
    }

    public CheckBox getTypeBox() {
        return typeBox;
    }

    public CheckBox getDosageBox() {
        return dosageBox;
    }

    public Button getViewButton() {
        return viewButton;
    }

    public TableView<Vaccine> getVaccineListView() {
        return vaccineListView;
    }

    public ObservableList<Vaccine> getVaccineList() {
        return vaccineList;
    }

    //endregion

    public int vaccineSelection() {
        int count = 0;
        if (typeBox.isSelected()) count = count + 1;
        if (dosageBox.isSelected()) count = count + 2;
        return count;
    }

    public String generateTableView() {
        TableColumn<Vaccine, String> typeColumn = new TableColumn<>();
        TableColumn<Vaccine, String> dosageColumn = new TableColumn<>();
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        switch(vaccineSelection()) {
            case 0:
                return " vacId, vacName ";
            case 1:
                vaccineListView.getColumns().add(typeColumn);
                return " vacId, vacName, Type ";
            case 2:
                vaccineListView.getColumns().add(dosageColumn);
                return " vacId, vacName, Dosage ";
            case 3:
                vaccineListView.getColumns().addAll(typeColumn, dosageColumn);
                return " * ";
            default:
                throw new IllegalStateException("Unexpected value: " + vaccineSelection());
        }
    }
}
