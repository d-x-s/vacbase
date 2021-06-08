package ca.ubc.cs304.project.ui;

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

    // Example data
    ArrayList<ArrayList<String>> distributorData;


    public TabPage() {
        initiateFields();
        setUpDistributorTab();
        setUpVaccineTab();
        setUpFacilitiesTab();
        setUpPatientTab();
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
}
