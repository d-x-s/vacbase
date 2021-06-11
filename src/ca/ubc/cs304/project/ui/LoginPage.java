package ca.ubc.cs304.project.ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class LoginPage {
    private Scene page;
    private TextField usernameField;
    private PasswordField passwordField;
    private String username;
    private String password;
    private Button loginAdmin;
    private Button loginPatient;
    private Hyperlink createAccount;

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


        createAccount = new Hyperlink("Create Account");
        createAccount.setFont(new Font("Montserrat", 15));
        createAccount.setOpacity(0.5);
        setLayout(createAccount, 28, pageHeight - 35);


        root.getChildren().addAll(vacBase, usernameField, passwordField, shotImage, loginAdmin, loginPatient, createAccount);

        // Sets background color
        page = new Scene(root, pageWidth, pageHeight);
        setBackgroundColor(root);
    }

    private void makeLoginCredentials(TextField node, int x, int y, String prompt) {
        setLayout(node, x, y);

        node.setPromptText(prompt);
        node.setMaxSize(538, 66);
        node.setMinSize(538, 66);
        node.setFont(new Font("Montserrat", 35));
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Button getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(Button loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public Button getLoginPatient() {
        return loginPatient;
    }

    public void setLoginPatient(Button loginPatient) {
        this.loginPatient = loginPatient;
    }

    public Hyperlink getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Hyperlink createAccount) {
        this.createAccount = createAccount;
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

        createAccount.setOnAction((event -> {
            // TODO: Bring up a popup
        }));

    }

    public Scene getPage() {
        return page;
    }

}