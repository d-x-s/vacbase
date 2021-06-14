package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.VaccineRecord;
import ca.ubc.cs304.model.patient.VaccineRecordAggregation;
import ca.ubc.cs304.model.vaccine.Vaccine;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class PatientVaccineCarePage {
    Scene page;
    TableView<VaccineRecordAggregation> vaccineRecordView;
    ObservableList<VaccineRecordAggregation> vaccineRecordList;

    Button backButton;
    Button insertButton;
    TextField nurseIDField;
    TextField facilityIDField;
    TextField vacIDField;

    public PatientVaccineCarePage() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(30, 0, 30, 30));
        hBox.setSpacing(30);

        //region Setting up tableview
        vaccineRecordView = new TableView<>();
        TableColumn<VaccineRecordAggregation, String> vacNameColumn = new TableColumn<>("Vaccine");
        TableColumn<VaccineRecordAggregation, String> vacDateColumn = new TableColumn<>("Date");
        TableColumn<VaccineRecordAggregation, String> locationColumn = new TableColumn<>("Location");
        TableColumn<VaccineRecordAggregation, String> nurseColumn = new TableColumn<>("Nurse");
        vacNameColumn.setMinWidth((pageWidth >> 3));
        vacDateColumn.setMinWidth((pageWidth >> 3));
        locationColumn.setMinWidth((pageWidth >> 3));
        nurseColumn.setMinWidth((pageWidth >> 3));
        vaccineRecordView.setMinWidth(pageWidth >> 2);
        vacNameColumn.setCellValueFactory(new PropertyValueFactory<>("vacName"));
        vacDateColumn.setCellValueFactory(new PropertyValueFactory<>("vacDate"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("facilityName"));
        nurseColumn.setCellValueFactory(new PropertyValueFactory<>("nurseName"));
        vaccineRecordView.setItems(getVaccineRecordList());
        vaccineRecordView.getColumns().addAll(vacNameColumn, vacDateColumn, locationColumn, nurseColumn);
        //endregion

        //region Setting up insert
        backButton = new Button("Back");
        insertButton = new Button("Insert");
        nurseIDField = new TextField();
        facilityIDField = new TextField();
        vacIDField = new TextField();
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(30, 0, 0, 0));
        nurseIDField.setPromptText("Enter Nurse ID: ");
        facilityIDField.setPromptText("Enter Facility ID: ");
        vacIDField.setPromptText("Enter Vaccine ID: ");
        nurseIDField.setFont(commonFont(18));
        facilityIDField.setFont(commonFont(18));
        vacIDField.setFont(commonFont(18));
        backButton.setFont(commonFont(24));
        insertButton.setFont(commonFont(24));
        backButton.setMinWidth(pageWidth >> 3);
        backButton.setTranslateY(100);
        insertButton.setMinWidth(pageWidth >> 3);
        makeButton(backButton);
        makeButton(insertButton);
        vBox.getChildren().addAll(nurseIDField, facilityIDField, vacIDField, insertButton, backButton);
        //endregion

        hBox.getChildren().addAll(vaccineRecordView, vBox);
        page = new Scene(hBox, pageWidth, pageHeight);
        setBackgroundColor(hBox);
        setPatientView(true);

    }


    public ObservableList<VaccineRecordAggregation> getVaccineRecordList() {
        vaccineRecordList = FXCollections.observableArrayList();
        //vaccineRecordList.add(new VaccineRecordAggregation(1,1,1,1, new java.sql.Date(System.currentTimeMillis()), "Chicken", "Fa", "Jon"));
        //vaccineRecordList.add(new VaccineRecordAggregation(1,1,1,1, new java.sql.Date(System.currentTimeMillis()), "fsdf", "B", "Jon"));
        //vaccineRecordList.add(new VaccineRecordAggregation(1,1,1,1, new java.sql.Date(System.currentTimeMillis()), "Ch", "V", "Jon"));
        return vaccineRecordList;
    }

    public Scene getPage() {
        return page;
    }

    public void setPage(Scene page) {
        this.page = page;
    }

    public TableView<VaccineRecordAggregation> getVaccineRecordView() {
        return vaccineRecordView;
    }

    public void setVaccineRecordView(TableView<VaccineRecordAggregation> vaccineRecordView) {
        this.vaccineRecordView = vaccineRecordView;
    }

    public void setVaccineRecordList(ObservableList<VaccineRecordAggregation> vaccineRecordList) {
        this.vaccineRecordList = vaccineRecordList;
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public void setPatientView(boolean isPatient) {
        insertButton.setVisible(!isPatient);
        nurseIDField.setVisible(!isPatient);
        facilityIDField.setVisible(!isPatient);
        vacIDField.setVisible(!isPatient);
    }

    public Button getInsertButton() {
        return insertButton;
    }

    public void setInsertButton(Button insertButton) {
        this.insertButton = insertButton;
    }

    public TextField getNurseIDField() {
        return nurseIDField;
    }

    public void setNurseIDField(TextField nurseIDField) {
        this.nurseIDField = nurseIDField;
    }

    public TextField getFacilityIDField() {
        return facilityIDField;
    }

    public void setFacilityIDField(TextField facilityIDField) {
        this.facilityIDField = facilityIDField;
    }

    public TextField getVacIDField() {
        return vacIDField;
    }

    public void setVacIDField(TextField vacIDField) {
        this.vacIDField = vacIDField;
    }
}
