package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.statistics.NestedAggregation;
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
    private Tab statistics;

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

    /*
    Statistics tab
     */
    private Button divisionButton;
    private Button nestedAggregationButton;
    private Button aggregationButton;
    private TableView<PatientAccount> divisionView;
    private ObservableList<PatientAccount> divisionList;
    private TableView<NestedAggregation> nestedAggregationView;
    private ObservableList<NestedAggregation> nestedAggregationList;
    private Label aggregationLabel;


    public TabPage(DatabaseConnectionHandler databaseConnectionHandler) {

        //region Setup Tabs
        dbh = databaseConnectionHandler;
        initiateFields();
        setUpVaccineTab();
        setUpFacilitiesTab();
        setUpPatientTab();
        setUpStatisticsTab();

        //endregion
        StackPane backgroundPane = new StackPane();
        setBackgroundColor(backgroundPane);
        backgroundPane.getChildren().add(tabs);
        tabs.getTabs().addAll(vaccine, patient, facilities, statistics);
        tabs.setMaxSize(pageWidth - 70, pageHeight - 70);
        tabs.setMinSize(pageWidth - 70, pageHeight - 70);
        page = new Scene(backgroundPane, pageWidth, pageHeight);
    }

    private void setUpStatisticsTab() {
        HBox hBox = new HBox(10);
        setWhiteBackgroundColor(hBox);
        VBox vBox1 = new VBox(10);
        VBox vBox2 = new VBox(10);
        VBox vBox3 = new VBox(10);
        vBox1.setPadding(new Insets(5));
        vBox2.setPadding(new Insets(5));
        vBox3.setPadding(new Insets(5));
        hBox.getChildren().addAll(vBox1, vBox2, vBox3);
        vBox1.setMinWidth(194);
        vBox2.setMinWidth(194);
        vBox3.setMinWidth(194);
        vBox1.setMaxWidth(194);
        vBox2.setMaxWidth(194);
        vBox3.setMaxWidth(194);
        makeButtonOnWhite(divisionButton);
        makeButtonOnWhite(nestedAggregationButton);
        makeButtonOnWhite(aggregationButton);
        divisionButton.setFont(commonFont(24));
        nestedAggregationButton.setFont(commonFont(24));
        aggregationButton.setFont(commonFont(24));
        divisionView.setMinHeight(202);
        divisionView.setMaxHeight(202);
        nestedAggregationView.setMinHeight(202);
        nestedAggregationView.setMaxHeight(202);
        divisionButton.setTranslateY(40);
        divisionView.setTranslateY(40);
        nestedAggregationButton.setTranslateY(15);
        nestedAggregationView.setTranslateY(15);
        divisionButton.setMinWidth(180);
        divisionButton.setMaxWidth(180);
        nestedAggregationButton.setMinWidth(180);
        nestedAggregationButton.setMaxWidth(180);
        aggregationButton.setMinWidth(165);
        aggregationButton.setMaxWidth(165);
        aggregationButton.setTranslateY(40);
        aggregationLabel.setTranslateY(100);
        aggregationLabel.setTranslateX(60);
        //region label stuff
        Label divisionLabel1 = new Label("Displays a list of fully ");
        Label divisionLabel2 = new Label("vaccinated individuals. ");
        divisionLabel1.setFont(commonFont(16));
        divisionLabel2.setFont(commonFont(16));
        VBox divisionBox = new VBox();
        divisionBox.getChildren().addAll(divisionLabel1, divisionLabel2);

        Label nestedLabel1 = new Label("Displays all patients who");
        Label nestedLabel2 = new Label("have been vaccinated");
        Label nestedLabel3 = new Label("more than average.");
        nestedLabel1.setFont(commonFont(16));
        nestedLabel2.setFont(commonFont(16));
        nestedLabel3.setFont(commonFont(16));
        VBox nestedBox = new VBox();
        nestedBox.getChildren().addAll(nestedLabel1, nestedLabel2, nestedLabel3);

        Label aggLabel1 = new Label("Displays the number");
        Label aggLabel2 = new Label("of vaccinations.");
        aggLabel1.setFont(commonFont(16));
        aggLabel2.setFont(commonFont(16));
        VBox aggBox = new VBox();
        aggBox.getChildren().addAll(aggLabel1, aggLabel2);
        //endregion

        //region tableViewStuff
        TableColumn<NestedAggregation, String> careCardColumn = new TableColumn<>("CCN");
        TableColumn<NestedAggregation, String> countColumn = new TableColumn<>("Count");
        careCardColumn.setCellValueFactory(new PropertyValueFactory<>("careCardNumber"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        nestedAggregationView.getColumns().addAll(careCardColumn, countColumn);
        nestedAggregationView.setItems(nestedAggregationList);

        TableColumn<PatientAccount, String> nameColumn = new TableColumn<>("Name");
        TableColumn<PatientAccount, String> careNumber = new TableColumn<>("CCN");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        careNumber.setCellValueFactory(new PropertyValueFactory<>("careCardNumber"));
        divisionView.setItems(divisionList);
        divisionView.getColumns().addAll(nameColumn, careNumber);

        //endregion

        aggregationLabel.setFont(commonFont(30));
        vBox1.getChildren().addAll(divisionBox, divisionButton, divisionView);
        vBox2.getChildren().addAll(nestedBox, nestedAggregationButton, nestedAggregationView);
        vBox3.getChildren().addAll(aggBox, aggregationButton, aggregationLabel);
        statistics.setContent(hBox);
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


        vacIDColumn = new TableColumn<>("ID");
        vacNameColumn = new TableColumn<>("Name");
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
        statistics = new Tab("Statistics");
        facilities = new Tab("Facilities");
        insertFacilityButton = new Button("Insert");
        updateFacilityButton = new Button("Update");
        facilityIDField = new TextField();
        facilityNameField = new TextField();
        addressField = new TextField();
        typeBox = new CheckBox("Select Type");
        dosageBox = new CheckBox("Select Dosage");
        viewButton = new Button("View Selections");
        vaccineListView = new TableView<>();
        vaccineList = FXCollections.observableArrayList();
        divisionButton = new Button("View");
        nestedAggregationButton = new Button("View");
        aggregationButton = new Button("View");
        divisionView = new TableView<>();
        divisionList = FXCollections.observableArrayList();
        nestedAggregationView = new TableView<>();
        nestedAggregationList = FXCollections.observableArrayList();
        aggregationLabel = new Label();
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

    public void setAggregationLabel(Label aggregationLabel) {
        this.aggregationLabel = aggregationLabel;
    }

    public Button getDivisionButton() {
        return divisionButton;
    }

    public Button getNestedAggregationButton() {
        return nestedAggregationButton;
    }

    public Button getAggregationButton() {
        return aggregationButton;
    }

    public TableView<PatientAccount> getDivisionView() {
        return divisionView;
    }

    public ObservableList<PatientAccount> getDivisionList() {
        return divisionList;
    }

    public TableView<NestedAggregation> getNestedAggregationView() {
        return nestedAggregationView;
    }

    public ObservableList<NestedAggregation> getNestedAggregationList() {
        return nestedAggregationList;
    }

    public Label getAggregationLabel() {
        return aggregationLabel;
    }

    //endregion

    public int vaccineSelection() {
        int count = 0;
        if (typeBox.isSelected()) count = count + 1;
        if (dosageBox.isSelected()) count = count + 2;
        return count;
    }

    public String generateTableView() {
        TableColumn<Vaccine, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Vaccine, String> dosageColumn = new TableColumn<>("Dosage");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        switch (vaccineSelection()) {
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
