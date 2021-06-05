package ca.ubc.cs304.project.ui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import oracle.jdbc.logging.annotations.Log;

public class Main extends Application {

    Stage window;
    Scene loginScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        // Testing PatientPage
        PatientPage patientPage = new PatientPage("Jon Yu");
        loginScene = patientPage.getPage();

        // Testing loginPage
//        LoginPage loginPage = new LoginPage();
//        loginScene = loginPage.getLoginIn();

        window.setScene(loginScene);
        window.setTitle("Title");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
