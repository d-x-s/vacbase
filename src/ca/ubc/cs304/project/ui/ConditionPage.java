package ca.ubc.cs304.project.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.distributor.Facility;
import ca.ubc.cs304.model.patient.PreExistingCondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Arrays;

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class ConditionPage {
    private Scene page;
    private TableView<PreExistingCondition> viewConditions;
    private TextField conditionInput;
    private Button backButton;
    private Button insertButton;
    private int careCardNumber;
    private ObservableList<PreExistingCondition> conditions;
    private DatabaseConnectionHandler dbh;

    public ConditionPage(DatabaseConnectionHandler databaseConnectionHandler) {
        VBox pane = new VBox();
        dbh = databaseConnectionHandler;

        //Name column
        TableColumn<PreExistingCondition, String> nameColumn = new TableColumn<>("Condition");
        nameColumn.setMinWidth(pageWidth);
        nameColumn.setMaxWidth(pageWidth);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("condition")); /* Needs to be exact name as class property*/

        viewConditions = new TableView<>();
        viewConditions.setItems(getConditions());
        viewConditions.getColumns().add(nameColumn);
        viewConditions.setMinWidth(pageWidth);
        viewConditions.setMaxWidth(pageWidth);

        conditionInput = new TextField();
        conditionInput.setPromptText("Write Notes: ");
        conditionInput.setFont(new Font("Montserrat", 24));
        conditionInput.setTranslateX(135);

        backButton = new Button("Back");
        makeButton(backButton);
        insertButton = new Button("Insert");
        makeButton(insertButton);
        backButton.setFont(new Font("Montserrat", 24));
        insertButton.setFont(new Font("Montserrat", 24));
        insertButton.setTranslateX(135);


        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.getChildren().addAll(backButton, conditionInput, insertButton);

        pane.getChildren().addAll(viewConditions, hBox);
        page = new Scene(pane, pageWidth, pageHeight);
        setBackgroundColor(pane);

    }

    public void setUpTable() {
        viewConditions.setItems(loadConditionsToTable());
    }

    public ObservableList<PreExistingCondition> loadConditionsToTable() {
        PreExistingCondition[] models = dbh.getConditionInfo(careCardNumber);
        conditions = FXCollections.observableArrayList();
        conditions.addAll(Arrays.asList(models));
        return conditions;
    }

    public Scene getPage() {
        return page;
    }

    public TableView<PreExistingCondition> getViewConditions() {
        return viewConditions;
    }

    public ObservableList<PreExistingCondition> getConditions() {
        return conditions;
    }

    public TextField getConditionInput() {
        return conditionInput;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getInsertButton() {
        return insertButton;
    }

    public int getCareCardNumber() {
        return careCardNumber;
    }

    public void setCareCardNumber(int careCardNumber) {
        this.careCardNumber = careCardNumber;
    }

}
