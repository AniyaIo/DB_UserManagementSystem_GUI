package com.example.db_usermanagementsystem_gui;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
public class Controller_AI_Refactaring implements Initializable {
    @FXML
    private ComboBox<String> companyName;
    @FXML
    private TextField userNameInput;
    @FXML
    private TextField userScoreInput;
    @FXML
    private TableView<UserData> dataTable;
    @FXML
    private TableColumn<UserData, IntegerProperty> idColumn;
    @FXML
    private TableColumn<UserData, StringProperty> nameColumn;
    @FXML
    private TableColumn<UserData, StringProperty> companyColumn;
    @FXML
    private TableColumn<UserData, IntegerProperty> scoreColumn;
    private ObservableList<UserData> data;
    private int id = 0;
    private ObservableList<String> companyList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        companyName.getItems().addAll(companyList);
        companyName.getSelectionModel().select(0);
        data = FXCollections.observableArrayList(
                new UserData(id++, companyList.get(0), "Tanaka", 45),
                new UserData(id++, companyList.get(2), "Yamada", 80),
                new UserData(id++, companyList.get(4), "Satou", 72)
        );
        dataTable.setEditable(true);
        dataTable.getSelectionModel().setCellSelectionEnabled(false);
        dataTable.setItems(data);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
    }
    @FXML
    protected void insertUserEvent() {
        String userCompany = companyName.getValue();
        String addName = userNameInput.getText();
        String addScore = userScoreInput.getText();
        if (!isInputData(addName, addScore)) {
            return;
        }
        data.add(new UserData(id++, userCompany, addName, Integer.valueOf(addScore)));
    }
    @FXML
    protected void deleteUserEvent() {
        UserData itemList = dataTable.getSelectionModel().getSelectedItem();
        data.remove(itemList);
    }
    @FXML
    protected void updateUserEvent() {
        UserData itemList = dataTable.getSelectionModel().getSelectedItem();
        if (!isInputData(userNameInput.getText(), userScoreInput.getText())) {
            return;
        }
        itemList.setCompany(companyName.getValue());
        itemList.setName(userNameInput.getText());
        itemList.setScore(Integer.parseInt(userScoreInput.getText()));
    }
    @FXML
    protected void selectColumnEvent() {
        UserData itemList = dataTable.getSelectionModel().getSelectedItem();
        try {
            companyName.setValue(itemList.getCompany().getValue());
            userNameInput.setText(itemList.getName().getValue());
            userScoreInput.setText(itemList.getScore().getValue().toString());
        } catch (NullPointerException e) {
            itemList = dataTable.getItems().get(0);
            companyName.setValue(itemList.getCompany().getValue());
            userNameInput.setText(itemList.getName().getValue());
            userScoreInput.setText(itemList.getScore().getValue().toString());
        }
    }
    private void alertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.show();
    }
    private boolean isInputData(String name, String score) {
        boolean isName = isName(name);
        boolean isScore = isScore(score);
        if (!isName && !isScore) {
            alertMessage("Name and score are not entered correctly");
        } else if (!isName) {
            alertMessage("Name is not entered correctly");
        } else if (!isScore) {
            alertMessage("Score is not entered correctly");
        } else if (Integer.valueOf(score) < 0 || Integer.valueOf(score) > 100) {
            alertMessage("Score must be between 0 and 100");
            return false;
        }
        return isName && isScore;
    }
    private boolean isName(String data) {
        return !(data.isEmpty() || data.isBlank());
    }
    private boolean isScore(String data) {
        boolean isScore = !(data.isEmpty() || data.isBlank());
        for (int i = 0; i < data.length(); i++) {
            if (!Character.isDigit(data.charAt(i))) {
                isScore = false;
                break;
            }
        }
        return isScore;
    }
}