package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.model.patient.PatientAccount;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class PatientPage {
    private Scene page;
    private Button viewRecord;
    private Button deleteAccount;
    private Button viewConditions;
    private Button logOut;
    private PatientAccount currentUser;
    private Label fullName;

    public PatientPage() {
        VBox pane = new VBox(30);
        pane.setAlignment(Pos.CENTER);
        VBox menuButtons = new VBox(10);
        menuButtons.setAlignment(Pos.CENTER);
        this.currentUser = null;

        fullName = new Label("d");

        fullName.setFont(new Font("Montserrat", 64));

        viewRecord = makeButton(new Button("View Vaccine Record"));
        setButtonSpecs(viewRecord);

        deleteAccount = makeButton(new Button("Delete Account"));
        setButtonSpecs(deleteAccount);

        viewConditions = makeButton(new Button("View Preexisting Conditions"));
        setButtonSpecs(viewConditions);

        logOut = makeButton(new Button("Log out"));
        setButtonSpecs(logOut);

        menuButtons.getChildren().addAll(viewRecord, deleteAccount, viewConditions, logOut);

        pane.getChildren().addAll(fullName, menuButtons);
        setBackgroundColor(pane);
        page = new Scene(pane, pageWidth, pageHeight);
    }

    public Button getViewRecord() {
        return viewRecord;
    }

    public Button getDeleteAccount() {
        return deleteAccount;
    }

    public Button getViewConditions() {
        return viewConditions;
    }

    public Button getLogOut() {
        return logOut;
    }

    private void setButtonSpecs(Button button) {
        button.setFont(new Font("Montserrat", 24));
        button.setMinSize(420, 56);
        button.setMaxSize(420, 56);
    }

    public Scene getPage() {
        return page;
    }

    public void setCurrentUser(PatientAccount currentUser) {
        this.currentUser = currentUser;
        setFullName(currentUser.getFullName());
    }

    public void setFullName(String name) {
        fullName.setText(name);
    }

}
