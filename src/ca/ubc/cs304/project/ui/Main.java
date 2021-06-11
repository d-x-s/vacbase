package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.AgeBracketLookup;
import ca.ubc.cs304.model.patient.LoginInfo;
import ca.ubc.cs304.model.patient.PatientAccount;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    Stage window;
    Scene scene;

    PatientPage patientPage;
    AdminPage adminPage;
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

        dbh = new DatabaseConnectionHandler();
        boolean isConnected = false;

//        while (!isConnected) {
//            //isConnected = dbh.login("ora_jyu19", "a67758979");
//            isConnected = dbh.login("ora_dsong04", "a29241874");
//            System.out.println("Failed to login");
//        }
//        System.out.println("Successfully Logged in");


        patientPage = new PatientPage();
        adminPage = new AdminPage();
        loginPage = new LoginPage();
        createPage = new CreateAccountPage();
        tabPage = new TabPage();
        conditionPage = new ConditionPage();
        vaccineCarePage = new PatientVaccineCarePage();
        addFunctionality();

        scene = vaccineCarePage.getPage();

        window.setScene(scene);
        window.setTitle("VacBase");
        window.show();
    }


    private void addFunctionality() {
        addFunctionalityTabPage();
        addFunctionalityPatientPage();
        addFunctionalityCreatePage();
        addFunctionalityLoginPage();
    }

    private void addFunctionalityTabPage() {
        addFunctionalityPatientTab();
        addFunctionalityFacilityTab();
        addFunctionalityFilterTab();
        addFunctionalityDistributorTab();
        addFunctionalityVaccineTab();
    }

    //region tabPage subroutines
    private void addFunctionalityPatientTab() {

    }

    private void addFunctionalityFacilityTab() {
        tabPage.getInsertFacilityButton().setOnAction(e -> {
            Facility temp;
            try {
                temp = new Facility(tabPage.getFacilityNameField().getText(), tabPage.getAddressField().getText());
                tabPage.getFacilityList().add(temp);
                tabPage.getFacilityNameField().clear();
                tabPage.getAddressField().clear();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        });
        tabPage.getFacilityView().setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Facility selectedItem = tabPage.getFacilityView().getSelectionModel().getSelectedItem();
                tabPage.getFacilityView().getItems().remove(selectedItem);
            }
        });

        tabPage.getUpdateFacilityButton().setOnAction(event -> {
            Facility selectedItem = tabPage.getFacilityView().getSelectionModel().getSelectedItem();
            //TODO: Add setters
//            selectedItem.setAddress(addressField.getText());
//            selectedItem.setFacilityName(facilityNameField.getText());
            tabPage.getAddressField().clear();
            tabPage.getFacilityNameField().clear();
        });
    }

    private void addFunctionalityFilterTab() {
        tabPage.getButton017().setOnAction(event -> {
            /* TODO: db stuff

             */
        });
        tabPage.getButton1829().setOnAction(event -> {
            /* TODO: db stuff

             */
        });
        tabPage.getButton3044().setOnAction(event -> {
            /* TODO: db stuff

             */
        });
        tabPage.getButton4559().setOnAction(event -> {
            /* TODO: db stuff

             */
        });
        tabPage.getButton60().setOnAction(event -> {
            /* TODO: db stuff

             */
        });
    }

    private void addFunctionalityDistributorTab() {

    }

    private void addFunctionalityVaccineTab() {

    }
    //endregion

    private void addFunctionalityPatientPage() {
        patientPage.getViewRecord().setOnAction(event -> {
            // TODO: View record
        });
        patientPage.getViewChildrenRecord().setOnAction(event -> {
            // TODO: Possibly remove
        });
        patientPage.getViewConditions().setOnAction(event -> {
            // TODO: view preexisting
        });
        patientPage.getVacLocations().setOnAction(event -> {
            // TODO: view locations
        });
    }

    private void addFunctionalityCreatePage() {
        createPage.getBackButton().setOnAction(event -> {
            // TODO: go back to LoginPage
            window.setScene(loginPage.getPage());
        });
        createPage.getConfirmButton().setOnAction(event -> {
            /* TODO: Creates account.
            Utilize createPage.getFullNameField(); createPage.getCareCardNumberField();
                createPage.getPasswordField(); createPage.getUsernameField();
              - .getText()
              - .getClear()
              There's a function called highlightField(TextField field) in helpful functions that turns a textfield red
              might be useful for indicating errors
             */
            String name = createPage.getFullNameField().getText();
            int ccn = Integer.parseInt(createPage.getCareCardNumberField().getText());
            Date dob = Date.valueOf(createPage.getDobField().getValue());
            String password = createPage.getPasswordField().getText();
            String username = createPage.getUsernameField().getText();

            PatientAccount newUser = new PatientAccount(ccn, name, dob, username);
            LoginInfo newUserLogin = new LoginInfo(username, password);
            AgeBracketLookup newUserAgeBracket = new AgeBracketLookup(dob, getAgeBracket(dob));
            dbh.insertPatientAccount(newUser);
            dbh.insertLoginInfo(newUserLogin);
            dbh.insertAgeBracket(newUserAgeBracket);
        });
    }

    private void addFunctionalityLoginPage() {
        loginPage.getLoginPatient().setOnAction(event -> {
            /* TODO: Log into patientPage
            Utilize loginPage.getPasswordField() and loginPage.getUsernameField();
                - .getText()            (returns string)
                - .clear()              (Clears textField)
             */
            // This should go to whatever patientAccount they logged in as

            //patientPage = new PatientPage(new PatientAccount(1, "Jon U", Date.valueOf("2001-04-06"), "Jonomuffin"));
            patientPage = new PatientPage(dbh.loginToAccount(loginPage.getUsernameField().getText(), loginPage.getPasswordField().getText()));
            System.out.println("Gets here");
            loginPage.getUsernameField().clear();
            loginPage.getPasswordField().clear();
            window.setScene(patientPage.getPage());
        });
        loginPage.getLoginAdmin().setOnAction(event -> {
            /* TODO: Log into tabPage
            Utilize loginPage.getPasswordField() and loginPage.getUsernameField();
                - .getText()            (returns string)
                - .clear()              (Clears textField)
             */
            window.setScene(tabPage.getPage());
        });

        loginPage.getCreateAccount().setOnAction(event -> {
            /* TODO: Log into createAccountPage

             */
            window.setScene(createPage.getPage());
        });
    }

    public String getAgeBracket(Date date) {
        long diff = TimeUnit.DAYS.convert(Date.valueOf(java.time.LocalDate.now()).getTime() - date.getTime(), TimeUnit.MILLISECONDS);
        if (diff > 21900) {
            return "60+";
        } else if (diff > 16425) {
            return "45-59";
        } else if (diff > 10950) {
            return "30-44";
        } else if (diff > 6570) {
            return "18-29";
        } else {
            return "18-";
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
