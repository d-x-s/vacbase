package ca.ubc.cs304.project.ui;

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

import static ca.ubc.cs304.project.ui.HelpfulFunctions.*;

public class ConditionPage {
    Scene page;
    TableView<PreExistingCondition> viewConditions;
    TextField conditionInput;
    Button backButton;
    Button insertButton;
    long careCardNumber;
    ObservableList<PreExistingCondition> conditions;

    public ConditionPage() {
        VBox pane = new VBox();

        //TODO: set the careCardNumber when scene is created
        careCardNumber = 1;

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
        addFunctionality();

    }

    public ObservableList<PreExistingCondition> getConditions() {
        conditions = FXCollections.observableArrayList();
        conditions.add(new PreExistingCondition(1, "Condition 1"));
        conditions.add(new PreExistingCondition(1, "Condition 2"));
        conditions.add(new PreExistingCondition(1, "Condition 3"));
        conditions.add(new PreExistingCondition(1, "Condition 4"));
        return conditions;
    }

    public Scene getPage() {
        return page;
    }

    public void setPage(Scene page) {
        this.page = page;
    }

    public TableView<PreExistingCondition> getViewConditions() {
        return viewConditions;
    }

    public void setViewConditions(TableView<PreExistingCondition> viewConditions) {
        this.viewConditions = viewConditions;
    }

    public TextField getConditionInput() {
        return conditionInput;
    }

    public void setConditionInput(TextField conditionInput) {
        this.conditionInput = conditionInput;
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public Button getInsertButton() {
        return insertButton;
    }

    public void setInsertButton(Button insertButton) {
        this.insertButton = insertButton;
    }

    public long getCareCardNumber() {
        return careCardNumber;
    }

    public void setCareCardNumber(long careCardNumber) {
        this.careCardNumber = careCardNumber;
    }

    public void setConditions(ObservableList<PreExistingCondition> conditions) {
        this.conditions = conditions;
    }

    // TODO: zero validation. add some somewhere or something idk
    private void addFunctionality() {
        insertButton.setOnAction(e -> {
            PreExistingCondition temp;
            try {
                temp = new PreExistingCondition((int)careCardNumber, conditionInput.getText());
                conditions.add(temp);
                conditionInput.clear();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        });
        viewConditions.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                PreExistingCondition selectedItem = viewConditions.getSelectionModel().getSelectedItem();
                viewConditions.getItems().remove(selectedItem);
            }
        });
    }
}
