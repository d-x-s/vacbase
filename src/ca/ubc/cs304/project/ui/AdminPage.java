package ca.ubc.cs304.project.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;


public class AdminPage {
    Scene page;
    Button vaccineButton;
    Button distributorButton;
    Button patientButton;
    Button facilitiesButton;

    public AdminPage() {
        VBox pane = new VBox(30);
        pane.setAlignment(Pos.CENTER);
        VBox menuButtons = new VBox(10);
        menuButtons.setAlignment(Pos.CENTER);

        Label text = new Label("What would you like to look at?");
        text.setFont(new Font("Montserrat", 30));

        vaccineButton = makeButton(new Button("Vaccine"));
        setButtonSpecs(vaccineButton);

        distributorButton = makeButton(new Button("Distributor"));
        setButtonSpecs(distributorButton);

        patientButton = makeButton(new Button("Patient"));
        setButtonSpecs(patientButton);

        facilitiesButton = makeButton(new Button("Facilities"));
        setButtonSpecs(facilitiesButton);

        menuButtons.getChildren().addAll(vaccineButton, distributorButton, patientButton, facilitiesButton);

        pane.getChildren().addAll(text, menuButtons);
        setBackgroundColor(pane);
        addButtonFunctionality();

        page = new Scene(pane, pageWidth, pageHeight);

    }

    private void addButtonFunctionality() {
        vaccineButton.setOnAction((event -> {
            // Todo: Add functionality
            System.out.println("View Vaccine Record");
        }));
        distributorButton.setOnAction((event -> {
            // Todo: Add functionality
            System.out.println("View Minor's Record");
        }));
        patientButton.setOnAction((event -> {
            // Todo: Add functionality
            System.out.println("View Preexisting Conditions");
        }));
        facilitiesButton.setOnAction((event -> {
            // Todo: Add functionality
            System.out.println("Where can I get vaccinated?");
        }));
    }

    private void setButtonSpecs(Button button) {
        button.setFont(new Font("Montserrat", 36));
        button.setMinSize(250, 63);
        button.setMaxSize(250, 63);
    }

    public void setPage(Scene page) {
        this.page = page;
    }

    public Button getVaccineButton() {
        return vaccineButton;
    }

    public void setVaccineButton(Button vaccineButton) {
        this.vaccineButton = vaccineButton;
    }

    public Button getDistributorButton() {
        return distributorButton;
    }

    public void setDistributorButton(Button distributorButton) {
        this.distributorButton = distributorButton;
    }

    public Button getPatientButton() {
        return patientButton;
    }

    public void setPatientButton(Button patientButton) {
        this.patientButton = patientButton;
    }

    public Button getFacilitiesButton() {
        return facilitiesButton;
    }

    public void setFacilitiesButton(Button facilitiesButton) {
        this.facilitiesButton = facilitiesButton;
    }

    public Scene getPage() {
        return page;
    }
}
