package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.exceptions.AlreadyAUsername;
import ca.ubc.cs304.exceptions.InvalidCareCardNumber;
import ca.ubc.cs304.exceptions.PasswordsDontMatch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class CreateAccountPage {
    Scene page;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField fullNameField;
    private TextField careCardNumberField;
    private Button confirmButton;
    private Button backButton;

    public CreateAccountPage() {
        StackPane pane = new StackPane();
        VBox fieldPane = new VBox(20);
        Font commonFont = new Font("Montserrat", 30);
        Font fieldFont = new Font("Montserrat", 20);

        instantiateFields();

        setBackgroundColor(pane);
        setPrompts();

        setFonts(commonFont, fieldFont);
        setFieldDimension();

        fieldPane.setAlignment(Pos.TOP_CENTER);
        fieldPane.setTranslateY(50);

        fieldPane.getChildren().addAll(usernameField,careCardNumberField, passwordField, fullNameField);
        pane.getChildren().addAll(fieldPane, confirmButton, backButton);
        pane.setAlignment(Pos.CENTER);

        StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        backButton.setTranslateY(-20);
        backButton.setTranslateX(150);
        StackPane.setAlignment(confirmButton, Pos.BOTTOM_RIGHT);
        confirmButton.setTranslateY(-20);
        confirmButton.setTranslateX(-150);

        //highlightField(usernameField);

        page = new Scene(pane, pageWidth, pageHeight);
    }

    public Scene getPage() {
        return page;
    }


    public void setPage(Scene page) {
        this.page = page;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public TextField getFullNameField() {
        return fullNameField;
    }

    public void setFullNameField(TextField fullNameField) {
        this.fullNameField = fullNameField;
    }

    public TextField getCareCardNumberField() {
        return careCardNumberField;
    }

    public void setCareCardNumberField(TextField careCardNumberField) {
        this.careCardNumberField = careCardNumberField;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(Button confirmButton) {
        this.confirmButton = confirmButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    private void checkUsername() throws AlreadyAUsername{
        // TODO: Check DB

    }

    private void checkPassword() throws PasswordsDontMatch {
        if (!passwordField.getText().equals(fullNameField.getText())) {
            throw new PasswordsDontMatch();
        }
    }

    private void checkCareCardNumber() throws InvalidCareCardNumber, NumberFormatException {
        if (careCardNumberField.getText().length() != 10) throw new InvalidCareCardNumber();
        Long.parseLong(careCardNumberField.getText());
    }

    private void instantiateFields() {
        usernameField = new TextField();
        careCardNumberField = new TextField();
        passwordField = new PasswordField();
        fullNameField = new TextField();
        confirmButton = makeButton(new Button("Confirm"));
        backButton = makeButton(new Button("Back"));
    }

    /*
        Sets font
     */
    private void setFonts(Font commonFont, Font fieldFont) {
        confirmButton.setFont(commonFont);
        backButton.setFont(commonFont);
        usernameField.setFont(fieldFont);
        careCardNumberField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        fullNameField.setFont(fieldFont);
    }

    /*
        Sets Default prompt text
     */
    private void setPrompts() {
        usernameField.setPromptText("Enter a Username");
        careCardNumberField.setPromptText("Enter 10 digit BC Care Card Number");
        passwordField.setPromptText("Enter Password");
        fullNameField.setPromptText("Enter Full Name");
    }

    /*
        Sets final size of fields
     */
    private void setFieldDimension() {
        usernameField.setMaxSize(400, 66);
        careCardNumberField.setMaxSize(400, 66);
        passwordField.setMaxSize(400, 66);
        fullNameField.setMaxSize(400, 66);
        usernameField.setMinSize(400, 66);
        careCardNumberField.setMinSize(400, 66);
        passwordField.setMinSize(400, 66);
        fullNameField.setMinSize(400, 66);
    }

}
