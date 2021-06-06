package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.exceptions.AlreadyAUsername;
import ca.ubc.cs304.exceptions.InvalidCareCardNumber;
import ca.ubc.cs304.exceptions.PasswordsDontMatch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class CreateAccountPage {
    Scene page;
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
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

        fieldPane.getChildren().addAll(usernameField,careCardNumberField, passwordField, confirmPasswordField);
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


//    private void checkValidity() {
//        try {
//
//        } catch (NumberFormatException num) {
//            // TODO: Highlight the careCareNumber textfield red -> have the write a new one
//        } catch (InvalidCareCardNumber iCCN) {
//            // TODO: Highlight the careCardNumber textField red -> have them write a new one
//        } catch (AlreadyAUsername aAU) {
//
//        } catch (PasswordsDontMatch pDM) {
//            // TODO: Highlight the second password field -> have them rewrite.
//        }
//    }

    private void checkUsername() throws AlreadyAUsername{
        // TODO: Check DB

    }

    private void checkPassword() throws PasswordsDontMatch {
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            throw new PasswordsDontMatch();
        }
    }

    private void checkCareCardNumber() throws InvalidCareCardNumber, NumberFormatException {
        if (careCardNumberField.getText().length() != 10) throw new InvalidCareCardNumber();
        Long.parseLong(careCardNumberField.getText());
    }

    private void highlightField(TextField field) {
        field.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    }

    private void instantiateFields() {
        usernameField = new TextField();
        careCardNumberField = new TextField();
        passwordField = new PasswordField();
        confirmPasswordField = new PasswordField();
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
        confirmPasswordField.setFont(fieldFont);
    }

    /*
        Sets Default prompt text
     */
    private void setPrompts() {
        usernameField.setPromptText("Enter a Username");
        careCardNumberField.setPromptText("Enter 10 digit BC Care Card Number");
        passwordField.setPromptText("Enter Password");
        confirmPasswordField.setPromptText("Reenter Password");
    }

    /*
        Sets final size of fields
     */
    private void setFieldDimension() {
        usernameField.setMaxSize(400, 66);
        careCardNumberField.setMaxSize(400, 66);
        passwordField.setMaxSize(400, 66);
        confirmPasswordField.setMaxSize(400, 66);
        usernameField.setMinSize(400, 66);
        careCardNumberField.setMinSize(400, 66);
        passwordField.setMinSize(400, 66);
        confirmPasswordField.setMinSize(400, 66);
    }

}
