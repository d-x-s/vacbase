package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.vaccine.Vaccine;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    DatabaseConnectionHandler dbh;

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
    TextField facilityIDField;
    TextField facilityNameField;
    TextField addressField;
    TableView<Facility> facilityView;
    ObservableList<Facility> facilityList;

    /*
    Vaccine tab
     */
    Button availabilityButton;
    TableView<Vaccine> vaccineListView;


    public TabPage(DatabaseConnectionHandler databaseConnectionHandler) {

        //region Setup Tabs
        dbh = databaseConnectionHandler;
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
        facilityIDField = new TextField();
        facilityNameField = new TextField();
        addressField = new TextField();
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

    public Tab getDistributor() {
        return distributor;
    }

    public void setDistributor(Tab distributor) {
        this.distributor = distributor;
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

    public Tab getFilter() {
        return filter;
    }

    public void setFilter(Tab filter) {
        this.filter = filter;
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(TextField searchBar) {
        this.searchBar = searchBar;
    }

    public Button getButton017() {
        return button017;
    }

    public void setButton017(Button button017) {
        this.button017 = button017;
    }

    public Button getButton1829() {
        return button1829;
    }

    public void setButton1829(Button button1829) {
        this.button1829 = button1829;
    }

    public Button getButton3044() {
        return button3044;
    }

    public void setButton3044(Button button3044) {
        this.button3044 = button3044;
    }

    public Button getButton4559() {
        return button4559;
    }

    public void setButton4559(Button button4559) {
        this.button4559 = button4559;
    }

    public Button getButton60() {
        return button60;
    }

    public void setButton60(Button button60) {
        this.button60 = button60;
    }

    public TableView<PatientAccount> getFilterView() {
        return filterView;
    }

    public void setFilterView(TableView<PatientAccount> filterView) {
        this.filterView = filterView;
    }

    public ObservableList<PatientAccount> getFilterList() {
        return filterList;
    }

    public void setFilterList(ObservableList<PatientAccount> filterList) {
        this.filterList = filterList;
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


}
