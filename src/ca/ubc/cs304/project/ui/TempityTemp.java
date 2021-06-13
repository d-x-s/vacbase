package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.patient.PatientAccount;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class TempityTemp extends Application {

    Stage window;
    Scene scene;


    LoginPage loginPage;
    CreateAccountPage createPage;
    TabPage tabPage;
    ConditionPage conditionPage;
    PatientVaccineCarePage vaccineCarePage;

    PatientAccount currentUser;
    DatabaseConnectionHandler dbh;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;


//        patientPage = new PatientPage();
//        adminPage = new AdminPage();
//        loginPage = new LoginPage();
//        createPage = new CreateAccountPage();
//        tabPage = new TabPage(dbh);
//        conditionPage = new ConditionPage(dbh);
        vaccineCarePage = new PatientVaccineCarePage();

        scene = vaccineCarePage.getPage();

        window.setScene(scene);
        window.setTitle("VacBase");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}