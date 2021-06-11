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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class PatientVaccineCarePage {
    Scene page;
    TableView<VaccineRecordAggregation> vaccineRecordView;
    ObservableList<VaccineRecordAggregation> vaccineRecordList;

    Button backButton;

    public PatientVaccineCarePage() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(50, 10, 50, 50));

        //region Setting up tableview
        vaccineRecordView = new TableView<>();
        TableColumn<VaccineRecordAggregation, String> vacNameColumn = new TableColumn<>("Vaccine");
        TableColumn<VaccineRecordAggregation, String> vacDateColumn = new TableColumn<>("Date");
        TableColumn<VaccineRecordAggregation, String> locationColumn = new TableColumn<>("Location");
        TableColumn<VaccineRecordAggregation, String> nurseColumn = new TableColumn<>("Nurse");
        vacNameColumn.setMinWidth((pageWidth >> 2));
        vacDateColumn.setMinWidth((pageWidth >> 2));
        locationColumn.setMinWidth((pageWidth >> 2));
        nurseColumn.setMinWidth((pageWidth >> 2));
        vacNameColumn.setCellValueFactory(new PropertyValueFactory<>("vacName"));
        vacDateColumn.setCellValueFactory(new PropertyValueFactory<>("vacDate"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("facilityName"));
        nurseColumn.setCellValueFactory(new PropertyValueFactory<>("nurseName"));
        vaccineRecordView.setItems(getVaccineRecordList());
        vaccineRecordView.getColumns().addAll(vacNameColumn, vacDateColumn, locationColumn, nurseColumn);
        //endregion

        hBox.getChildren().addAll(vaccineRecordView);
        page = new Scene(hBox, pageWidth, pageHeight);

    }

    private ObservableList<VaccineRecordAggregation> getVaccineRecordList() {
        vaccineRecordList = FXCollections.observableArrayList();
        vaccineRecordList.add(new VaccineRecordAggregation(1,1,1,1, new java.sql.Date(System.currentTimeMillis()), "Chicken", "Fa", "Jon"));
        vaccineRecordList.add(new VaccineRecordAggregation(1,1,1,1, new java.sql.Date(System.currentTimeMillis()), "fsdf", "B", "Jon"));
        vaccineRecordList.add(new VaccineRecordAggregation(1,1,1,1, new java.sql.Date(System.currentTimeMillis()), "Ch", "V", "Jon"));
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
}
