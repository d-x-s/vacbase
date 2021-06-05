package ca.ubc.cs304.project.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LoginPage {
    Scene login;
    TextField usernameField;
    PasswordField passwordField;
    String username;
    String password;
    private final int pageWidth = 654;
    private final int pageHeight = 474;

    public LoginPage() {
        Pane root = new Pane();
        usernameField = new TextField();
        makeLoginCredentials(usernameField, 28, pageHeight - 186, "Enter Username: ");

        passwordField = new PasswordField();
        makeLoginCredentials(passwordField, 28, pageHeight - 102, "Enter Password: ");

        // Sets logo
        Label vacBase = new Label("VacBase");
        HelpfulFunctions.setLayout(vacBase, 38, 10);
        vacBase.setFont(new Font("Montserrat", 90));

        root.getChildren().addAll(vacBase, usernameField, passwordField);

        // Sets background color
        login = new Scene(root, pageWidth, pageHeight);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(196, 227, 199),
                CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void makeLoginCredentials(TextField node, int x, int y, String prompt) {
        HelpfulFunctions.setLayout(node, x, y);

        node.setPromptText(prompt);
        node.setMaxSize(538, 66);
        node.setMinSize(538, 66);
        node.setFont(new Font("Montserrat", 35));
    }

    public Scene getLoginIn() {
        return login;
    }

}