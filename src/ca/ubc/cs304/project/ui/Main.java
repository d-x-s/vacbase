package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.AgeBracketLookup;
import ca.ubc.cs304.model.patient.LoginInfo;
import ca.ubc.cs304.model.patient.PatientAccount;
import ca.ubc.cs304.model.patient.VaccineRecordAggregation;
import ca.ubc.cs304.model.vaccine.Nurse;
import ca.ubc.cs304.model.vaccine.Vaccine;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static javafx.application.Platform.exit;

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
        window.setOnCloseRequest(e -> dbh.close());

        dbh = new DatabaseConnectionHandler();
        boolean isConnected = false;
        int count = 0;

        while (!isConnected) {
            if (count == 0) {
                isConnected = dbh.login("ora_akang28", "a74159187");
            } else if (count == 1) {
                isConnected = dbh.login("ora_dsong04", "a29241874");
            } else if (count == 2){
                isConnected = dbh.login("ora_jyu19", "a67758979");
            } else {
                count = 0; /* loop*/
            }

            System.out.println("Failed to login");
        }
        System.out.println("Successfully Logged in");


        patientPage = new PatientPage();
        adminPage = new AdminPage();
        loginPage = new LoginPage();
        createPage = new CreateAccountPage();
        tabPage = new TabPage(dbh);
        conditionPage = new ConditionPage(dbh);
        vaccineCarePage = new PatientVaccineCarePage();
        addFunctionality();

        scene = loginPage.getPage();

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
        addFunctionalityPatientVaccineCarePage();
    }

    //region tabPage subroutines
    private void addFunctionalityPatientTab() {

    }

    private void addFunctionalityFacilityTab() {
        // insertion
        tabPage.getInsertFacilityButton().setOnAction(e -> {
            Facility temp;
            try {
                temp = new Facility(Integer.parseInt(tabPage.getFacilityIDField().getText()),
                        tabPage.getFacilityNameField().getText(),
                        tabPage.getAddressField().getText());
                tabPage.getFacilityList().add(temp);
                dbh.insertFacility(temp);
                tabPage.getFacilityIDField().clear();
                tabPage.getFacilityNameField().clear();
                tabPage.getAddressField().clear();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        });

        // deletion
        tabPage.getFacilityView().setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Facility selectedItem = tabPage.getFacilityView().getSelectionModel().getSelectedItem();
                tabPage.getFacilityList().remove(selectedItem);
                dbh.deleteFacility(selectedItem.getFacilityID());
            }
        });

        // update
        tabPage.getUpdateFacilityButton().setOnAction(event -> {
            //TODO: Add setters
            int selectedIndex = tabPage.getFacilityView().getSelectionModel().getSelectedIndex();
            int IDToUpdate = Integer.parseInt(tabPage.getFacilityIDField().getText());
            String name = tabPage.getFacilityNameField().getText();
            String addressToUpdate = tabPage.getAddressField().getText();
            Facility updated = new Facility(IDToUpdate, name, addressToUpdate);
            dbh.updateFacility(IDToUpdate, addressToUpdate);

            tabPage.getFacilityList().set(selectedIndex, updated);
            tabPage.getFacilityIDField().clear();
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
            window.setScene(vaccineCarePage.getPage());
        });
        patientPage.getDeleteAccount().setOnAction(event -> {
            window.setScene(loginPage.getPage());
            if (currentUser == null) {
                exit();
            }
            int careCardNumber = currentUser.getCareCardNumber();
            currentUser = null;
            dbh.deletePatientAccount(careCardNumber);
        });
        patientPage.getViewConditions().setOnAction(event -> {
            window.setScene(conditionPage.getPage());
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
            window.setScene(loginPage.getPage());
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

            currentUser = dbh.loginToAccount(loginPage.getUsernameField().getText(), loginPage.getPasswordField().getText());
            patientPage.setPatientAccount(currentUser);

            System.out.println("Gets here");
            ArrayList<VaccineRecordAggregation> list = dbh.joinAggregateWithVaccineRecordQuery();
            for (VaccineRecordAggregation v : list) {
                vaccineCarePage.getVaccineRecordList().add(v);
            }
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
            window.setScene(createPage.getPage());
        });
    }

    private void addFunctionalityPatientVaccineCarePage() {
        vaccineCarePage.getBackButton().setOnAction(event -> {
            System.out.println("Should go back");
            window.setScene(patientPage.getPage());
        });
        vaccineCarePage.getInsertButton().setOnAction(event -> {
            Nurse nurse = dbh.getSpecificNurse(Integer.parseInt(vaccineCarePage.getNurseIDField().getText()));
            Vaccine vaccine = dbh.getSpecificVaccine(Integer.parseInt(vaccineCarePage.getVacIDField().getText()));
            Facility facility = dbh.getSpecificFacility(Integer.parseInt(vaccineCarePage.getFacilityIDField().getText()));
            int newID = dbh.getMaxVaccineCareCardID() + 1;
            int newEventID = dbh.getMaxEventID() + 1;
            Date currentDate = new java.sql.Date(System.currentTimeMillis());

            VaccineRecordAggregation newRecord = new VaccineRecordAggregation(currentUser.getCareCardNumber(), newID, newEventID, nurse.getNurseID(),
                    vaccine.getVacID(), facility.getFacilityID(), currentDate, vaccine.getVacName(), facility.getFacilityName(), nurse.getNurseName());
            dbh.insertAdministeredVaccGivenToPatient(newRecord.makeAdministeredVaccGivenToPatient());
            dbh.insertInclude(newRecord.makeInclude());
            dbh.insertHappensIn(newRecord.makeHappensIn());
            dbh.insertVaccineRecord(newRecord.makeVaccineRecord());
            vaccineCarePage.getVaccineRecordList().add(newRecord);

            vaccineCarePage.getFacilityIDField().clear();
            vaccineCarePage.getNurseIDField().clear();
            vaccineCarePage.getVacIDField().clear();
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
