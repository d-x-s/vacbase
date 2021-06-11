package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.model.distributor.Facility;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene;

    PatientPage patientPage;
    AdminPage adminPage;
    LoginPage loginPage;
    CreateAccountPage createPage;
    TabPage tabPage;
    ConditionPage conditionPage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        patientPage = new PatientPage("Jon Yu");
        adminPage = new AdminPage();
        loginPage = new LoginPage(); /* Some Problem */
        createPage = new CreateAccountPage();
        tabPage = new TabPage();
        conditionPage = new ConditionPage();
        addFunctionality();

        //scene = patientPage.getPage();

        // scene = adminPage.getPage(); /* Depreciated */

        scene = loginPage.getPage();

        //scene = createPage.getPage();

        // scene = tabPage.getPage();

        // Testing conditionPage
//        scene = conditionPage.getPage();


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
        });
    }

    private void addFunctionalityLoginPage() {
        loginPage.getLoginPatient().setOnAction(event -> {
            /* TODO: Log into patientPage
            Utilize loginPage.getPasswordField() and loginPage.getUsernameField();
                - .getText()            (returns string)
                - .clear()              (Clears textField)
             */
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

    public static void main(String[] args) {
        launch(args);
    }

}
