package com.example.db_usermanagementsystem_gui;

import DB.DBUtil;
import DB.dataRecord.UsersRecord;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.db_usermanagementsystem_gui.InputCheck.isInputData;
import DB.service.CompaniesService;
import DB.service.UsersService;
import static com.example.db_usermanagementsystem_gui.DisplayWindow.alertMessage;


public class DB_UserManagementSystemController implements Initializable {
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
    private TableColumn<UserData,StringProperty> companyColumn;
    @FXML
    private TableColumn<UserData, IntegerProperty> scoreColumn;

    private ObservableList<String> companyList;


    /**
     * 初期化処理
     * @param url
     * @param resourceBundle
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var companiesTable=new CompaniesService(); //テーブル操作オブジェクト

        //企業名をリストに格納
        companyList = FXCollections.observableArrayList(companiesTable.getAllName());
        //コンボボックスに企業名を入れる
        companyName.getItems().setAll(companyList);
        companyName.getSelectionModel().select(0);

        //////ユーザ一覧表示//////
        this.dataTable.setEditable(true);
        this.dataTable.getSelectionModel().setCellSelectionEnabled(false);
        updateUsersData();
        //行要素とフィールド紐づけ
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    /**
     * データ追加処理
     */
    @FXML
    protected void insertUserEvent() {
        var usersTable=new UsersService();  //テーブル操作オブジェクト
        String userCompany= companyName.getValue();
        String addName=userNameInput.getText();
        String addScore=userScoreInput.getText();

        //スコアと名前のチェック
        if(!isInputData(addName,addScore)){
            return;
        }

        try {
            usersTable.insertUserData(new UserData(
                    1,
                    userCompany,
                    addName,
                    Integer.parseInt(addScore)));
            updateUsersData();
        }catch (SQLException e){
            alertMessage("データの挿入に失敗しました。");
        }
    }

    /**
     * データ削除処理
     */
    @FXML
    protected void deleteUserEvent(){
        var usersTable=new UsersService();  //テーブル操作オブジェクト
        UserData itemList=dataTable.getSelectionModel().getSelectedItem();
        try {
            usersTable.delete(itemList.getId().getValue());
            updateUsersData();
        }catch (SQLException e){
            alertMessage("データの削除に失敗しました。");
        }catch (IndexOutOfBoundsException | NullPointerException e){
            alertMessage("データを選択してください。");
        }

    }

    /**
     * データ更新処理
     */
    @FXML
    protected void updateUserEvent(){
        //選択されたカラムのデータ取得
        UserData itemList=dataTable.getSelectionModel().getSelectedItem();
        if(!isInputData(nameColumn.getText(),userScoreInput.getText())){
            return;
        }

        try {
            var userTable = new UsersService();
            userTable.update(new UsersRecord(
                    itemList.getId().getValue(),
                    userNameInput.getText(),
                    companyName.getSelectionModel().getSelectedIndex(),
                    Integer.parseInt(userScoreInput.getText())));
            updateUsersData();
        }catch (NullPointerException e){
            alertMessage("データを選択してください。");
        }

    }

    /**
     * テーブルのアイテム選択処理
     */
    @FXML
    protected void selectColumnEvent(){
        //選択されているアイテム(UserData型)を取得
        UserData itemList=dataTable.getSelectionModel().getSelectedItem();
        //取得したアイテムをtextAreaにset
        try {
            companyName.setValue(itemList.getCompany().getValue());
            userNameInput.setText(itemList.getName().getValue());
            userScoreInput.setText(itemList.getScore().getValue().toString());
        }catch (NullPointerException e){
            itemList=dataTable.getItems().getFirst();
            companyName.setValue(itemList.getCompany().getValue());
            userNameInput.setText(itemList.getName().getValue());
            userScoreInput.setText(itemList.getScore().getValue().toString());
        }

    }

    //ユーザの追加・編集・削除の度に呼び出す
    private void updateUsersData(){
        var usersTable=new UsersService();  //テーブル操作オブジェクト
        this.dataTable.setItems(FXCollections.observableArrayList(usersTable.readAllUserData()));
    }


}