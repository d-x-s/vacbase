package ca.ubc.cs304.project.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class LoginPage {
    private Scene login;
    private TextField usernameField;
    private PasswordField passwordField;
    private String username;
    private String password;
    private Button loginAdmin;
    private Button loginPatient;

    public LoginPage() {
        Pane root = new Pane();

        // Creates the username and password fields
        usernameField = new TextField();
        makeLoginCredentials(usernameField, 28, pageHeight - 186, "Enter Username: ");

        passwordField = new PasswordField();
        makeLoginCredentials(passwordField, 28, pageHeight - 102, "Enter Password: ");

        // Sets logo
        Label vacBase = new Label("VacBase");
        setLayout(vacBase, 38, 10);
        vacBase.setFont(new Font("Montserrat", 90));

        String shotImageDirectory = "data/Needle.png";
        ImageView shotImage = createImage(shotImageDirectory);
        setLayout(shotImage, 440, 30);

        loginAdmin = makeButton(new Button("Login Admin"));
        loginAdmin.setFont(new Font("Montserrat", 30));
        setLayout(loginAdmin, 40, pageHeight - 275);

        loginPatient = makeButton(new Button("Login Patient"));
        loginPatient.setFont(new Font("Montserrat", 30));
        setLayout(loginPatient, 40, pageHeight - 350);

        addButtonFunctionality();



        root.getChildren().addAll(vacBase, usernameField, passwordField, shotImage, loginAdmin, loginPatient);

        // Sets background color
        login = new Scene(root, pageWidth, pageHeight);
        setBackgroundColor(root);
    }

    private void makeLoginCredentials(TextField node, int x, int y, String prompt) {
        setLayout(node, x, y);

        node.setPromptText(prompt);
        node.setMaxSize(538, 66);
        node.setMinSize(538, 66);
        node.setFont(new Font("Montserrat", 35));
    }

    private void addButtonFunctionality() {
        loginAdmin.setOnAction((event -> {
            //TODO: Add functionality
            System.out.println("Goodbye World");
            username = usernameField.getText();
            System.out.println(username);
        }));

        loginPatient.setOnAction((event -> {
            //TODO: Add functionality
            System.out.println("Hello World");
            password = passwordField.getText();
            System.out.println(password);
        }));
    }

    public Scene getLoginIn() {
        return login;
    }

}